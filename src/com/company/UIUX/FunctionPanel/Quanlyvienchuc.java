package com.company.UIUX.FunctionPanel;


import com.company.Class.Vienchuc;
import com.company.Process.ProcessDKMH;
import com.company.Process.ProcessQLVC;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class Quanlyvienchuc extends JPanel implements ActionListener, MouseListener {

    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JDatePickerImpl datePicker;
    private JButton add = new JButton("Thêm Viên chức");
    private JButton reload = new JButton("Tải Lại Bảng");
    private JButton update = new JButton("Cập nhật viên chức");
    private JButton clear = new JButton("Làm mới miền nhập");
    private JButton export = new JButton("Xuất file báo cáo");
    private BorderLayout MainLayout = new BorderLayout();
    private TableRowSorter<TableModel> rowSorter;
    private JTable MainTable;
    private JScrollPane MainScroll;
    private ProcessQLVC processQLVC = new ProcessQLVC();


    public Quanlyvienchuc() {
        init();
    }


    private JTextField jtfFilter = new JTextField();

    private void init() {
        loadTable();
        addEvent();
        BottomPanel();
        LeftPanel();
        CenterPanel();
        setLayout(Mainlayout);
        add(LeftPanel, BorderLayout.WEST);
        add(CenterPanel, BorderLayout.CENTER);
    }

    private void loadTable() {
        MainTable = new JTable(processQLVC.loadTableModel()) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }

        };

        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainTable.setRowSorter(rowSorter);
        MainScroll = new JScrollPane(MainTable);
    }

    private void BottomPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
        BottomPanel.add(reload);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
        BottomPanel.add(export);
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
    }

    private JLabel[] LeftLabels = {new JLabel("Mã Viên Chức"), new JLabel("Loại Viên Chức"), new JLabel("Tên Viên Chức"), new JLabel("Họ lót")
            , new JLabel("SĐT"), new JLabel("Ngày Sinh"), new JLabel("Nơi Sinh"), new JLabel("Email")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    private void LeftPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth * 0.15), screenheight));
        LeftTextfields[0].enable(false);
        for (int i = 0; i < 8; i++) {
            if (i == 5) {
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
        LeftPanel.add(clear);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
        reload.addActionListener(this);
        MainTable.addMouseListener(this);
        clear.addActionListener(this);
        export.addActionListener(this);
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
        if (e.getSource() == clear) {
            clearTextFields();
        }
        if (e.getSource() == export) {
            processQLVC.ExportPDF();
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
        }
    }

    private void addAction() {
        String loaivc = "";
        String ten = "";
        String holot = "";
        String sdt = "";
        Date date = null;
        String noisinh = "";
        String email = "";

        if (!LeftTextfields[0].getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã Viên chức phải để trống");
            return;
        }
        for (int i = 1; i < 8; i++) {
            if (LeftTextfields[i].getText().equals("")) {
                if (i == 5) {
                    continue;
                } else {
                    JOptionPane.showMessageDialog(this, "Các thông tin không được để trống");
                    return;
                }
            }
        }

        try {
            loaivc = LeftTextfields[1].getText().toString();
            ten = LeftTextfields[2].getText().toString();
            holot = LeftTextfields[3].getText().toString();
            sdt = LeftTextfields[4].getText().toString();
            date = new Date(datePicker.getModel().getYear() - 1900, datePicker.getModel().getMonth(), datePicker.getModel().getDay());
            noisinh = LeftTextfields[6].getText().toString();
            email = LeftTextfields[7].getText().toString();
            System.out.println("date:" + datePicker.getModel().getYear());
        } catch (Exception ex) {
            System.out.println("xin loi");
        }

        Vienchuc vienchuc = new Vienchuc(loaivc, ten, holot, sdt, date, noisinh, email);
        boolean check = processQLVC.addVC(vienchuc);
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Thêm Viên chức thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Viên chức thất bại  (lỗi hệ thống)");
        }

    }

    private void updateAction() {
        int mavc = 0;
        String loaivc = "";
        String ten = "";
        String holot = "";
        String sdt = "";
        Date date = null;
        String noisinh = "";
        String email = "";
        for (int i = 0; i < 8; i++) {
            if (LeftTextfields[i].getText().equals("")) {
                if (i == 5) {
                    continue;
                } else {
                    JOptionPane.showMessageDialog(this, "Các thông tin không được để trống");
                    return;
                }
            }
        }
        try {
            mavc = Integer.parseInt(LeftTextfields[0].getText().toString());
            loaivc = LeftTextfields[1].getText().toString();
            ten = LeftTextfields[2].getText().toString();
            holot = LeftTextfields[3].getText().toString();
            sdt = LeftTextfields[4].getText().toString();
            date = new Date(datePicker.getModel().getYear() - 1900, datePicker.getModel().getMonth(), datePicker.getModel().getDay());
            noisinh = LeftTextfields[6].getText().toString();
            email = LeftTextfields[7].getText().toString();

        } catch (Exception ex) {
            System.out.println("xin loi");
        }


        Vienchuc vienchuc = new Vienchuc(mavc, loaivc, ten, holot, sdt, date, noisinh, email);
        boolean check = processQLVC.updateVC(vienchuc);
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin Viên chức thành công");

        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin Viên chứcs thất bại (lỗi hệ thống)");
        }
        System.out.println("hiih");
    }

    private void reloadtable() {
        MainTable.setModel(processQLVC.reloadTableModel((DefaultTableModel) MainTable.getModel(), MainTable.getRowCount()));
    }

    private void clickTable() {
        int row = MainTable.getSelectedRow();
        if (row >= 0) {
            for (int i = 0; i < 8; i++) {
                if (i == 5) {
                    String ngaysinh[] = MainTable.getValueAt(row, i).toString().split("-");
                    this.datePicker.getModel().setDate(Integer.parseInt(ngaysinh[0]), Integer.parseInt(ngaysinh[1]) - 1, Integer.parseInt(ngaysinh[2]));
                    this.datePicker.getModel().setSelected(true);
                } else
                    LeftTextfields[i].setText(MainTable.getValueAt(row, i).toString());
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickTable();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void clearTextFields() {
        for (int i = 0; i < 8; i++) {
            if (i == 5) {
                //this.datePicker.getModel().setSelected();
                this.datePicker.getModel().setSelected(false);
            } else
                LeftTextfields[i].setText("");
        }
    }
}
