package com.company.UIUX.FunctionPanel;

import com.company.Class.HocPhan;
import com.company.Process.ProcessQLMH;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quanlymonhoc extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JButton add = new JButton("Thêm môn học");
    private JButton reload = new JButton("Tải lại bảng");
    private JButton update = new JButton("Cập nhật môn học");
    private JTable MainTable;
    private JScrollPane MainScroll;
    private TableRowSorter<TableModel> rowSorter;
    private ProcessQLMH processQLMH= new ProcessQLMH();
    private BorderLayout MainLayout = new BorderLayout();

    public Quanlymonhoc() { init();}

    //Start Init Panel
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
        MainTable = new JTable(processQLMH.loadTableModel());
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainScroll = new JScrollPane(MainTable);
    }

    private JTextField jtfFilter = new JTextField();
    public void BottomPanel(){
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
        BottomPanel.add(reload);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
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
    private JLabel[] LeftLabels = {new JLabel("Mã Môn Học"), new JLabel("Tên Môn Học"), new JLabel("Mã Bộ Môn"), new JLabel("Số Tín Chỉ")
            , new JLabel("Số Tiết"), new JLabel("Số Tiết Thực Hành")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField()};

    private void LeftPanel(){
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth*0.15),screenheight));
        LeftTextfields[0].enable(false);
        for(int i=0;i<6;i++){
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.02)));
            LeftPanel.add(LeftLabels[i]);
            LeftPanel.add(LeftTextfields[i]);
        }
    }

    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
        reload.addActionListener(this);
    }

    //End Init Panel
    //Start Event
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
    }
    private void reloadtable(){
        MainTable.setModel(processQLMH.reloadTableModel((DefaultTableModel) MainTable.getModel(),MainTable.getRowCount()));
    }
    //End Event
    private void addAction(){
        String tenmonhoc = LeftTextfields[1].getText().toString();
        String mabomon = LeftTextfields[2].getText().toString();
        int sotinchi=0;
        int sotiet=0;
        int sotietthuchanh=0;
        try{
            sotinchi = Integer.parseInt(LeftTextfields[3].getText().toString()) ;
            sotiet = Integer.parseInt(LeftTextfields[4].getText().toString()) ;
            sotietthuchanh = Integer.parseInt(LeftTextfields[5].getText().toString()) ;
        }catch(Exception ex){
            System.out.println("loi");
            return;
        }
        HocPhan monhoc = new HocPhan(tenmonhoc,mabomon,sotinchi,sotiet,sotietthuchanh);
        boolean check = processQLMH.addMH(monhoc);
        if(check==true){
            System.out.println("thanh cong");
        }
        else{
            System.out.println("huhu");
        }
        System.out.println("hiih");
    }
    private void updateAction(){
        String tenmonhoc = LeftTextfields[1].getText().toString();
        String mabomon = LeftTextfields[2].getText().toString();
        int mmh;
        int sotinchi=0;
        int sotiet=0;
        int sotietthuchanh=0;
        try{
            mmh = Integer.parseInt(LeftTextfields[0].getText().toString()) ;
            sotinchi = Integer.parseInt(LeftTextfields[3].getText().toString()) ;
            sotiet = Integer.parseInt(LeftTextfields[4].getText().toString()) ;
            sotietthuchanh = Integer.parseInt(LeftTextfields[5].getText().toString()) ;
        }catch(Exception ex){
            System.out.println("loi");
            return;
        }
        HocPhan monhoc = new HocPhan(mmh,tenmonhoc,mabomon,sotinchi,sotiet,sotietthuchanh);
        boolean check = processQLMH.updateMH(monhoc);
        if(check==true){
            System.out.println("thanh cong");
        }
        else{
            System.out.println("huhu");
        }
        System.out.println("hiih");
    }

}
