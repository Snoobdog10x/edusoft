package com.company.UIUX.FunctionPanel;

import com.company.Class.HocPhan;
import com.company.Class.SinhVien;
import com.company.Process.ProcessQLSV;

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
import java.sql.Date;

public class Quanlysinhvien extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    private JPanel BottomPanel;
    private JButton add = new JButton("Đăng ký sinh viên");
    private JButton update = new JButton("Cập nhật");
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
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
            LeftPanel.add(LeftLabels[i]);
            LeftPanel.add(LeftTextfields[i]);
        }
    }
    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
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
    if(e.getSource()==add){
        addaction();
    }
    }
    public void addaction(){
        int MSSV = 0;
        String malop;
        String holot;
        String ten;
        Date ngaysinh;
        String sdt;
        String manganh;
        String noisinh;
        String email;
        try {
            malop = LeftTextfields[1].getText().toString();
            holot = LeftTextfields[2].getText().toString();
            ten = LeftTextfields[3].getText().toString();
            ngaysinh = Date.valueOf(LeftTextfields[4].getText().toString());
            sdt = LeftTextfields[5].getText().toString();
            manganh = LeftTextfields[5].getText().toString();
            noisinh = LeftTextfields[5].getText().toString();
            email = LeftTextfields[5].getText().toString();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số tín chỉ, số tiết, " );
            return;
        }
        SinhVien sv = new SinhVien(MSSV,malop,holot,ten,ngaysinh,sdt,manganh,noisinh,email);
        boolean check = processQLSV.addsinhvien(sv);
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Thêm môn học thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm môn học thất bại  (lỗi hệ thống)");
        }
        System.out.println("hiih");
    }

}
