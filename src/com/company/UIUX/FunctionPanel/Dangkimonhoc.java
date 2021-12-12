package com.company.UIUX.FunctionPanel;

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
    public JTable MainTable;
    public JScrollPane MainScroll;
    public BorderLayout MainLayout = new BorderLayout();

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
        MainTable = new JTable();
        MainScroll = new JScrollPane(MainTable);
    }

    private void BottomPanel() {
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private void LeftPanel() {
        LeftPanel = new JPanel();
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
            System.out.println("hiih");
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
