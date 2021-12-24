package com.company.UIUX.FunctionPanel;

import com.company.Class.HocPhan;
import com.company.Class.SinhVien;
import com.company.Class.Vienchuc;
import com.company.Process.ProcessQLSV;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class Quanlysinhvien extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    private JPanel BottomPanel;
    private JDatePickerImpl datePicker;
    private JButton add = new JButton("Đăng ký sinh viên");
    private JButton update = new JButton("Cập nhật");
    private JButton export = new JButton("Xuất file pdf");
    private JButton reload = new JButton("Tải lại bảng");
    private TableRowSorter<TableModel> rowSorter;
    private JTable MainTable;
    private JScrollPane MainScroll;
    private BorderLayout MainLayout = new BorderLayout();
    private ProcessQLSV processQLSV = new ProcessQLSV();

    public Quanlysinhvien() {
        init();
    }

    //Start Init Panel
    private void init() {
        BottomPanel();
        LeftPanel();
        loadTable();
        CenterPanel();
        setLayout(Mainlayout);
        add(LeftPanel, BorderLayout.WEST);
        add(CenterPanel, BorderLayout.CENTER);
        addEvent();
    }

    private void loadTable() {
        DefaultTableModel tableModel = processQLSV.loadTableModel();
        MainTable = new JTable(tableModel) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainTable.setRowSorter(rowSorter);
        MainScroll = new JScrollPane(MainTable);
    }

    private JTextField jtfFilter = new JTextField();
    private void BottomPanel() {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
        BottomPanel.add(export);
        BottomPanel.add(reload);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private JLabel[] LeftLabels = {new JLabel("Mã SV"), new JLabel("Mã lớp"), new JLabel("Tên lót"), new JLabel("Tên")
            , new JLabel("Ngày sinh"), new JLabel("SĐT"), new JLabel("Mã ngành"), new JLabel("Nơi sinh"),new JLabel("Email")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField(),new JTextField()};

    private void LeftPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        LeftTextfields[0].setEnabled(false);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth * 0.15), screenheight));
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                UtilDateModel model = new UtilDateModel();
//model.setDate(20,04,2014);
// Need this...
                Properties p = new Properties();
                p.put("text.today", "Today");
                p.put("text.month", "Month");
                p.put("text.year", "Year");
                JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
// Don't know about the formatter, but there it is...
                JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {

                    private String datePattern = "yyyy-MM-dd";
                    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

                    @Override
                    public Object stringToValue(String text) throws ParseException {
                        return dateFormatter.parseObject(text);
                    }

                    @Override
                    public String valueToString(Object value) throws ParseException {
                        if (value != null) {
                            Calendar cal = (Calendar) value;
                            return dateFormatter.format(cal.getTime());
                        }

                        return "";
                    }
                });


                LeftPanel.add(LeftLabels[i]);
                this.datePicker = datePicker;
                LeftPanel.add(this.datePicker);
            } else {
                LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.03)));
                LeftPanel.add(LeftLabels[i]);
                LeftPanel.add(LeftTextfields[i]);
            }
        }
    }
    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
        reload.addActionListener(this);
        export.addActionListener(this);
        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        MainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try {
                    int j = 0;
                    for (JTextField i : LeftTextfields) {
                        int a = MainTable.convertRowIndexToModel(MainTable.getSelectedRow());
                        i.setText(MainTable.getValueAt(a, j++).toString());
                    }
                }catch (Exception e){
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            addAction();
        }
        if (e.getSource() == update) {
            updateAction();
        }
        if (e.getSource() == reload) {
            reloadtable();
        }
        if (e.getSource() == export) {
            ExportPDF();
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
        }
    }
    private void addAction() {
        String malop ="";
        String holot="";
        String ten="";
        Date date = null;
        String sdt="";
        String manganh="";
        String noisinh="";
        String email="";

        if (!LeftTextfields[0].getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã Sinh Viên");
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (LeftTextfields[i].getText().equals("")) {
                if (i == 4) {
                    continue;
                } else {
                    JOptionPane.showMessageDialog(this, "Các thông tin không được để trống");
                    return;
                }
            }
        }

        try {
            malop = LeftTextfields[1].getText().toString();
            holot = LeftTextfields[2].getText().toString();
            ten = LeftTextfields[3].getText().toString();
            date = new Date(datePicker.getModel().getYear()-1900,datePicker.getModel().getMonth(),datePicker.getModel().getDay());
            sdt = LeftTextfields[5].getText().toString();
            manganh = LeftTextfields[6].getText().toString();
            noisinh = LeftTextfields[7].getText().toString();
            email = LeftTextfields[8].getText().toString();
            System.out.println("date:" +datePicker.getModel().getYear());
        } catch (Exception ex) {
            System.out.println("xin loi");
        }

        SinhVien sv = new SinhVien( malop,holot,ten,date,sdt,manganh,noisinh,email);
        boolean check = processQLSV.addsinhvien(sv);
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại  (lỗi hệ thống)");
        }
    }
    private void updateAction() {
        int MSSV = 0;
        String malop = "";
        String holot = "";
        String ten = "";
        Date date = null;
        String sdt = "";
        String manganh = "";
        String noisinh = "";
        String email = "";


        for (int i = 0; i < 9; i++) {
            if (LeftTextfields[i].getText().equals("")) {
                if (i == 4) {
                    continue;
                } else {
                    JOptionPane.showMessageDialog(this, "Các thông tin không được để trống");
                    return;
                }
            }
        }
        try {
            MSSV = Integer.parseInt(LeftTextfields[0].getText().toString());
            malop = LeftTextfields[1].getText().toString();
            holot = LeftTextfields[2].getText().toString();
            ten = LeftTextfields[3].getText().toString();
            date = new Date(datePicker.getModel().getYear() - 1900, datePicker.getModel().getMonth(), datePicker.getModel().getDay());
            sdt = LeftTextfields[5].getText().toString();
            manganh = LeftTextfields[6].getText().toString();
            noisinh = LeftTextfields[7].getText().toString();
            email = LeftTextfields[8].getText().toString();
            System.out.println("date:" + datePicker.getModel().getYear());

        } catch (Exception ex) {
            System.out.println("xin loi");
        }


        SinhVien sv = new SinhVien(MSSV,malop, holot, ten, date, sdt, manganh, noisinh, email);
        boolean check = processQLSV.updateSV(sv);
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thành công");
        } else {
            JOptionPane.showMessageDialog(this, "cập nhật sinh viên thất bại  (lỗi hệ thống)");
        }
    }
    private void reloadtable() {
        MainTable.setModel(processQLSV.reloadTableModel((DefaultTableModel) MainTable.getModel(), MainTable.getRowCount()));
    }
    public String FONT = "C:\\Windows\\Fonts\\times.ttf";
    public void ExportPDF() {
        try {
            int count = MainTable.getRowCount();
            Document document = new Document();
            String fname = java.time.LocalDate.now().toString() + " Danh sách sinh viên.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(".\\src\\com\\company\\ExportFile\\" + fname));
            document.open();
            Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Chunk chunk = new Chunk("", font);
            document.add(chunk);
            PdfPTable tab = new PdfPTable(9);
            tab.addCell(new Paragraph("Mã sinh viên", font));
            tab.addCell(new Paragraph("Mã lớp", font));
            tab.addCell(new Paragraph("Tên lót", font));
            tab.addCell(new Paragraph("Tên", font));
            tab.addCell(new Paragraph("Ngày sinh", font));
            tab.addCell(new Paragraph("SĐT", font));
            tab.addCell(new Paragraph("Mã ngành", font));
            tab.addCell(new Paragraph("Nơi sinh", font));
            tab.addCell(new Paragraph("Email", font));

            for (int i = 0; i < count; i++) {
                Object obj1 = GetData(MainTable, i, 0);
                Object obj2 = GetData(MainTable, i, 1);
                Object obj3 = GetData(MainTable, i, 2);
                Object obj4 = GetData(MainTable, i, 3);
                Object obj5 = GetData(MainTable, i, 4);
                Object obj6 = GetData(MainTable, i, 5);
                Object obj7 = GetData(MainTable, i, 6);
                Object obj8 = GetData(MainTable, i, 7);
                Object obj9 = GetData(MainTable, i, 8);
                String value1 = obj1.toString();
                String value2 = obj2.toString();
                String value3 = obj3.toString();
                String value4 = obj4.toString();
                String value5 = obj5.toString();
                String value6 = obj6.toString();
                String value7 = obj7.toString();
                String value8 = obj8.toString();
                String value9 = obj9.toString();
                tab.addCell(new Paragraph(value1, font));
                tab.addCell(new Paragraph(value2, font));
                tab.addCell(new Paragraph(value3, font));
                tab.addCell(new Paragraph(value4, font));
                tab.addCell(new Paragraph(value5, font));
                tab.addCell(new Paragraph(value6, font));
                tab.addCell(new Paragraph(value7, font));
                tab.addCell(new Paragraph(value8, font));
                tab.addCell(new Paragraph(value9, font));
            }
            document.add(tab);
            document.close();
        } catch (Exception e) {
        }
    }
    public Object GetData(JTable table, int row_index, int col_index){
        return table.getModel().getValueAt(row_index, col_index);
    }

}
