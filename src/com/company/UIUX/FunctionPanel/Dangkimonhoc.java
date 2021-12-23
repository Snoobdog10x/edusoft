package com.company.UIUX.FunctionPanel;

import com.company.Class.lichsudangky;
import com.company.Main;
import com.company.Process.ProcessDKMH;
import com.company.UIUX.Dangkimonhoc.DangkimonhocFrame;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Dangkimonhoc extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    private JPanel BottomPanel;
    private JButton add = new JButton("Đăng ký môn học");
    private JButton reload = new JButton("Tải lại bảng");
    private JButton update = new JButton("Cập nhật đăng kí");
    private JButton export = new JButton("xuất PDF");
    private TableRowSorter<TableModel> rowSorter;
    private JTable MainTable;
    private JScrollPane MainScroll;
    private BorderLayout MainLayout = new BorderLayout();
    private ProcessDKMH processDKMH = new ProcessDKMH();

    public Dangkimonhoc() {
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

    public void loadTable() {
        DefaultTableModel tableModel = processDKMH.loadTableModel();
        MainTable = new JTable(tableModel) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }

        };
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainTable.setRowSorter(rowSorter);
        MainScroll = new JScrollPane(MainTable);
    }

    public void reloadtable() {
        MainTable.setModel(processDKMH.reloadTableModel((DefaultTableModel) MainTable.getModel(), MainTable.getRowCount()));
    }

    private JTextField jtfFilter = new JTextField();

    private void BottomPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
        BottomPanel.add(reload);
        BottomPanel.add(export);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private JLabel[] LeftLabels = {new JLabel("ID"), new JLabel("Mã SV"), new JLabel("Tên Sinh Viên"), new JLabel("Mã Nhóm"), new JLabel("Nhóm")
            , new JLabel("TH"), new JLabel("Mã MH"), new JLabel("Tên Môn Học"), new JLabel("Ngày Đăng ký")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    private void LeftPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth * 0.15), screenheight));
        for (int i = 0; i < LeftLabels.length; i++) {
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
            LeftPanel.add(LeftLabels[i]);
            if (i != 3)
                LeftTextfields[i].setEnabled(false);
            LeftPanel.add(LeftTextfields[i]);
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
        MainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                try {
                    int j = 0;
                    for (JTextField i : LeftTextfields) {
                        int a = MainTable.convertRowIndexToModel(MainTable.getSelectedRow());
                        i.setText(MainTable.getValueAt(a, j++).toString());
                    }
                } catch (Exception e) {

                }
            }
        });

    }

    private void clearLeftText() {
        for (JTextField i : LeftTextfields) {
            i.setText("");
        }
    }

    private void updatelsdk() {
        try {
            String ID = LeftTextfields[0].getText();
            String MSSV = LeftTextfields[1].getText();
            String NMH = LeftTextfields[3].getText();
            if (ID.trim() == "" || ID == null) {
                JOptionPane.showMessageDialog(this, "Chưa chọn lịch sử để sửa đổi");
                return;
            }
            if (NMH.trim() == "" || NMH == null) {
                JOptionPane.showMessageDialog(this, "Nhập mã môn học cần sửa đổi");
                return;
            }
            lichsudangky ls = new lichsudangky(Integer.parseInt(ID), Integer.parseInt(MSSV), Integer.parseInt(NMH));
            int value = processDKMH.updatelsdk(ls);
            if (value > 0) {
                JOptionPane.showMessageDialog(this, "cập nhật thành công");
            } else {
                if (value == -1)
                    JOptionPane.showMessageDialog(this, "Trùng nhóm lớp đăng kí");
                else
                    JOptionPane.showMessageDialog(this, "cập nhật không thành công");
            }
            clearLeftText();
            MainTable.getSelectionModel().clearSelection();
            reloadtable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã nhóm môn học phải là số!");
        }
    }

    //End Init Panel
    //Start Event
    @Override
    public void actionPerformed(ActionEvent b) {
        if (b.getSource() == add) {
            new DangkimonhocFrame(processDKMH, this);
        }
        if (b.getSource() == update) {
            updatelsdk();
        }
        if (b.getSource() == reload) {
            reloadtable();
            clearLeftText();
        }
        if (b.getSource() == export) {
        }
    }
    public static final String FONT = "C:\\Windows\\Fonts\\times.ttf";
    private void exportToPDF(){
        try{
            int count=MainTable.getRowCount();
            com.itextpdf.text.Document document=new Document();
            String fname = java.time.LocalDate.now().toString()+" - Danh sách Đăng kí.pdf";
            PdfWriter.getInstance(document, new FileOutputStream("E:\\"+fname));
            document.open();
            Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Chunk chunk = new Chunk("",font);
            document.add(chunk);
            PdfPTable tab=new PdfPTable(6);
            tab.addCell(new Paragraph("Mã môn học", font));
            tab.addCell(new Paragraph("Tên môn học", font));
            tab.addCell(new Paragraph("Mã bộ môn", font));
            tab.addCell(new Paragraph("Số tín chỉ", font));
            tab.addCell(new Paragraph("Số tiết", font));
            tab.addCell(new Paragraph("Số tiết thực hành", font));
            for(int i=0;i<count;i++){
                Object obj1 = GetData(MainTable, i, 0);
                Object obj2 = GetData(MainTable, i, 1);
                Object obj3 = GetData(MainTable, i, 2);
                Object obj4 = GetData(MainTable, i, 3);
                Object obj5 = GetData(MainTable, i, 4);
                Object obj6 = GetData(MainTable, i, 5);
                String value1=obj1.toString();
                String value2=obj2.toString();
                String value3=obj3.toString();
                String value4=obj4.toString();
                String value5=obj5.toString();
                String value6=obj6.toString();
                tab.addCell(new Paragraph(value1, font));
                tab.addCell(new Paragraph(value2, font));
                tab.addCell(new Paragraph(value3, font));
                tab.addCell(new Paragraph(value4, font));
                tab.addCell(new Paragraph(value5, font));
                tab.addCell(new Paragraph(value6, font));
            }
            document.add(tab);
            document.close();
        }
        catch(Exception e){}
    }
    public Object GetData(JTable table, int row_index, int col_index){
        return table.getModel().getValueAt(row_index, col_index);
    }
}
//End Event
