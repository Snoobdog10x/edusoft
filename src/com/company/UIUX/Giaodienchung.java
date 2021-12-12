package com.company.UIUX;
import com.company.UIUX.FunctionPanel.*;
import com.company.UIUX.MainUIPanel.MainPanel;
import com.company.UIUX.MainUIPanel.TopMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Giaodienchung extends JFrame implements ActionListener {
    private JPanel Mainpanel = new MainPanel();
    private TopMenuBar MenuBarPannel = new TopMenuBar();
    private BorderLayout mainlayout = new BorderLayout();
    public Giaodienchung() {
        initUI();
    }

    private void initUI() {
        setTitle("SGU EduSoft");
        MenuBarPannel.addAction(this);
        setLayout(mainlayout);
        add(MenuBarPannel, BorderLayout.NORTH);
        add(Mainpanel, BorderLayout.CENTER);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void repalaceMainPanel(JPanel panel) {
        remove(Mainpanel);
        Mainpanel = panel;
        add(Mainpanel);
        revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MenuBarPannel.Home) {
            repalaceMainPanel(new MainPanel());
        }
        if (e.getSource() == MenuBarPannel.QuanlySV) {
            repalaceMainPanel(new Quanlysinhvien());
        }
        if (e.getSource() == MenuBarPannel.QuanlyVC) {
            repalaceMainPanel(new Quanlyvienchuc());
        }
        if (e.getSource() == MenuBarPannel.QuanlyMH) {
            repalaceMainPanel(new Quanlymonhoc());
        }
        if (e.getSource() == MenuBarPannel.LapkHGD) {
            repalaceMainPanel(new LapKHGD());
        }
        if (e.getSource() == MenuBarPannel.DangkiMH) {
            repalaceMainPanel(new Dangkimonhoc());
        }
    }
}
