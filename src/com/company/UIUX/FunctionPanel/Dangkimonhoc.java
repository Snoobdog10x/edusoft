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
    private JPanel CenterPanel;
    private JPanel LeftPanel;
    private JButton add = new JButton("Đăng ký môn học");
    private JButton reload = new JButton("Tải lại bảng");
    private JButton export = new JButton("xuất PDF");
    private JButton Delete = new JButton("Xóa lịch sử đăng ký");
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
        LeftPanel();
        loadTable();
        CenterPanel();
        setLayout(Mainlayout);
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

    private void LeftPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (size.width * 0.14);
        int height = (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.025)));
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        LeftPanel.setPreferredSize(new Dimension(width, height));
        LeftPanel.add(new JLabel("Nhập từ khóa để tìm kiếm trong bảng"));
        LeftPanel.add(jtfFilter);
        add.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.03)));
        reload.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.03)));
        export.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.03)));
        Delete.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.03)));
        LeftPanel.add(add);
        LeftPanel.add(reload);
        LeftPanel.add(export);
        LeftPanel.add(Delete);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(LeftPanel, BorderLayout.WEST);
    }

    private void addEvent() {
        add.addActionListener(this);
        reload.addActionListener(this);
        export.addActionListener(this);
        Delete.addActionListener(this);
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

    public void DeleteLSDK(){
        try {
            int rowsv = MainTable.getSelectedRow();
            Object mssv = MainTable.getValueAt(rowsv, 0).toString();
            Object MNL = MainTable.getValueAt(rowsv, 2);
            Object MMH = MainTable.getValueAt(rowsv, 5);
            String Message=processDKMH.DeleteLSDK(mssv,MNL,MMH);
            JOptionPane.showMessageDialog(this,Message);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Vui lòng lịch sử đăng ký cần xóa");
        }
    }
    //End Init Panel
    //Start Event
    @Override
    public void actionPerformed(ActionEvent b) {
        if (b.getSource() == add) {
            new DangkimonhocFrame(processDKMH, this);
        }
        if (b.getSource() == Delete) {
            DeleteLSDK();
            reloadtable();
        }
        if (b.getSource() == export) {
            exportToPDF();
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
        }
    }

    public static final String FONT = "C:\\Windows\\Fonts\\times.ttf";

    private void exportToPDF() {
        try {
            int count = MainTable.getRowCount();
            com.itextpdf.text.Document document = new Document();
            String fname = java.time.LocalDate.now().toString() + " - Danh sách Đăng kí.pdf";
            PdfWriter.getInstance(document, new FileOutputStream("./src/com/company/ExportFile/DKMH/" + fname));
            document.open();
            Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Chunk chunk = new Chunk("", font);
            document.add(chunk);
            PdfPTable tab = new PdfPTable(9);
            tab.addCell(new Paragraph("ID", font));
            tab.addCell(new Paragraph("MSSV", font));
            tab.addCell(new Paragraph("Tên Sinh Viên", font));
            tab.addCell(new Paragraph("Mã Nhóm", font));
            tab.addCell(new Paragraph("Nhóm", font));
            tab.addCell(new Paragraph("Thực hành", font));
            tab.addCell(new Paragraph("Mã môn học", font));
            tab.addCell(new Paragraph("Tên Môn Học", font));
            tab.addCell(new Paragraph("Ngày Đăng kí", font));
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

    public Object GetData(JTable table, int row_index, int col_index) {
        return table.getModel().getValueAt(row_index, col_index);
    }
}
//End Event
