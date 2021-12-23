package com.company.UIUX.FunctionPanel;

import com.company.Process.ProcessLKHGD;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;

import com.company.UIUX.KHGD.FrameAdd;
import com.company.UIUX.KHGD.FrameUpdate;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class LapKHGD extends JPanel implements ActionListener, MouseListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JButton add = new JButton("Thêm kế hoạch");
    private JButton reload = new JButton("Tải lại bảng");
    private JTable MainTable;
    private JScrollPane MainScroll;
    private TableRowSorter<TableModel> rowSorter;
    private ProcessLKHGD ProcessLKHGD = new ProcessLKHGD();
    private BorderLayout MainLayout = new BorderLayout();
    private JButton export = new JButton("Xuất file pdf");
    public LapKHGD() {
        init();
    }

    //Start Init Panel
    public void init() {
        loadTable();
        addEvent();
        BottomPanel();

        CenterPanel();
        setLayout(Mainlayout);
        add(CenterPanel, BorderLayout.CENTER);
    }

    private void loadTable() {
        MainTable = new JTable(ProcessLKHGD.loadTableModel()) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainTable.setRowSorter(rowSorter);
        MainScroll = new JScrollPane(MainTable);
    }

    private JTextField jtfFilter = new JTextField();

    public void BottomPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
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

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private void addEvent() {
        add.addActionListener(this);
        reload.addActionListener(this);
        MainTable.addMouseListener(this);
        export.addActionListener(this);
    }

    //End Init Panel
    //Start Event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            new FrameAdd();
        }
        if (e.getSource() == reload) {
            reloadtable();
        }
        if(e.getSource() == export){
            exportToPDF();
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
        }
    }

    private void reloadtable() {
        MainTable.setModel(ProcessLKHGD.loadTableModel());
    }

    //End Event

    private void clickTable() {
        FrameUpdate up = new FrameUpdate();
        int row = MainTable.getSelectedRow();
        String a[] = new String[11];
        if (row >= 0) {
            for (int i = 0; i < 11; i++) {
                a[i] = MainTable.getValueAt(row, i).toString();
                System.out.println(MainTable.getValueAt(row, i).toString());
            }

        }
        up.uiux(a);
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
    public static final String FONT = "C:\\Windows\\Fonts\\times.ttf";
    private void exportToPDF(){
        try{
            int count=MainTable.getRowCount();
            Document document=new Document();
            String fname = java.time.LocalDate.now().toString()+" - DS kế hoạch giảng dạy.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(".\\src\\com\\company\\ExportFile\\LKHGD\\"+fname));
            document.open();
            Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Chunk chunk = new Chunk("",font);
            document.add(chunk);
            PdfPTable tab=new PdfPTable(11);
            tab.addCell(new Paragraph("Mã nhóm lớp", font));
            tab.addCell(new Paragraph("Nhóm", font));
            tab.addCell(new Paragraph("Thực Hành", font));
            tab.addCell(new Paragraph("Mã môn học", font));
            tab.addCell(new Paragraph("Số lượng ĐK", font));
            tab.addCell(new Paragraph("Số lượng TKB", font));
            tab.addCell(new Paragraph("tên môn học", font));
            tab.addCell(new Paragraph("số tín chỉ", font));
            tab.addCell(new Paragraph("Số tiết", font));
            tab.addCell(new Paragraph("Tên Giảng viên", font));
            tab.addCell(new Paragraph("Mã Phòng Học", font));
            for(int i=0;i<count;i++){
                Object obj1 = GetData(MainTable, i, 0);
                Object obj2 = GetData(MainTable, i, 1);
                Object obj3 = GetData(MainTable, i, 2);
                Object obj4 = GetData(MainTable, i, 3);
                Object obj5 = GetData(MainTable, i, 4);
                Object obj6 = GetData(MainTable, i, 5);
                Object obj7 = GetData(MainTable, i, 6);
                Object obj8 = GetData(MainTable, i, 7);
                Object obj9 = GetData(MainTable, i, 8);
                Object obj10 = GetData(MainTable, i, 9);
                Object obj11 = GetData(MainTable, i, 10);
                String value1=obj1.toString();
                String value2=obj2.toString();
                String value3=obj3.toString();
                String value4=obj4.toString();
                String value5=obj5.toString();
                String value6=obj6.toString();
                String value7=obj7.toString();
                String value8=obj8.toString();
                String value9=obj9.toString();
                String value10=obj10.toString();
                String value11=obj11.toString();
                tab.addCell(new Paragraph(value1, font));
                tab.addCell(new Paragraph(value2, font));
                tab.addCell(new Paragraph(value3, font));
                tab.addCell(new Paragraph(value4, font));
                tab.addCell(new Paragraph(value5, font));
                tab.addCell(new Paragraph(value6, font));
                tab.addCell(new Paragraph(value7, font));
                tab.addCell(new Paragraph(value8, font));
                tab.addCell(new Paragraph(value9, font));
                tab.addCell(new Paragraph(value10, font));
                tab.addCell(new Paragraph(value11, font));

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

