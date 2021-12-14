package com.company.UIUX.FunctionPanel;

import com.company.Process.ProcessDKMH;
import com.company.UIUX.Dangkimonhoc.DangkimonhocFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dangkimonhoc extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JButton add = new JButton("Đăng ký môn học");
    private JButton reload = new JButton("Tải lại bảng");
    private JButton update = new JButton("Cập nhật đăng kí");
    private JTable MainTable;
    private JScrollPane MainScroll;
    private BorderLayout MainLayout = new BorderLayout();
    private ProcessDKMH processDKMH = new ProcessDKMH();

    public Dangkimonhoc() {
        init();
    }

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
        MainTable = new JTable(processDKMH.loadTableModel());
        MainScroll = new JScrollPane(MainTable);
    }

    private void BottomPanel() {
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
        BottomPanel.add(reload);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private JLabel[] LeftLabels = {new JLabel("<html>Mã SV<br/></html>"), new JLabel("Tên Sinh Viên"), new JLabel("Mã Nhóm"), new JLabel("Nhóm")
            , new JLabel("TH"), new JLabel("Mã MH"), new JLabel("Tên Môn Học"), new JLabel("Ngày Đăng ký")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    private void LeftPanel() {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth*0.15),screenheight));
        for(int i=0;i<8;i++){
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
            new DangkimonhocFrame();
        }
        if (e.getSource() == update) {
            System.out.println("hiih");
        }
        if (e.getSource() == reload) {
            System.out.println("hiih");
        }
    }
    //End Event
}
