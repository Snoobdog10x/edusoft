package com.company.UIUX;

import com.company.UIUX.FunctionPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Giaodienchung extends JFrame {
    private JPanel Mainpanel = new MainPanel();
    private JPanel MenuBarPannel = new TopMenuBar();
    private BorderLayout mainlayout = new BorderLayout();
    public Giaodienchung() {
        initUI();
    }

    private void initUI() {
        setTitle("SGU EduSoft");
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

    private class TopMenuBar extends JPanel implements ActionListener {
        private JLabel Banner = new JLabel("SGU EDUSOFT");
        private JButton QuanlySV = new JButton("Quản lý sinh viên");
        private JButton QuanlyVC = new JButton("Quản lý viên chức");
        private JButton QuanlyMH = new JButton("Quản lý môn học");
        private JButton LapkHGD = new JButton("Lập kế hoạch giảng dạy");
        private JButton DangkiMH = new JButton("Đăng ký môn học");
        private JButton Home = new JButton("Trang Chủ");

        private TopMenuBar() {
            add(Banner);
            add(Home);
            add(QuanlySV);
            add(QuanlyVC);
            add(QuanlyMH);
            add(LapkHGD);
            add(DangkiMH);
            Home.addActionListener(this);
            QuanlyMH.addActionListener(this);
            QuanlySV.addActionListener(this);
            QuanlyVC.addActionListener(this);
            LapkHGD.addActionListener(this);
            DangkiMH.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Home) {
                Giaodienchung.this.repalaceMainPanel(new MainPanel());
            }
            if (e.getSource() == QuanlySV) {
                Giaodienchung.this.repalaceMainPanel(new Quanlysinhvien());
            }
            if (e.getSource() == QuanlyVC) {
                Giaodienchung.this.repalaceMainPanel(new Quanlyvienchuc());
            }
            if (e.getSource() == QuanlyMH) {
                Giaodienchung.this.repalaceMainPanel(new Quanlymonhoc());
            }
            if (e.getSource() == LapkHGD) {
                Giaodienchung.this.repalaceMainPanel(new LapKHGD());
            }
            if (e.getSource() == DangkiMH) {
                Giaodienchung.this.repalaceMainPanel(new Dangkimonhoc());
            }
        }
    }

    private class MainPanel extends JPanel {
        private JLabel welcome = new JLabel("Chào mừng đến với SGU EduSoft");
        private BorderLayout MainLayout = new BorderLayout();

        private MainPanel() {
            setLayout(MainLayout);
            add(welcome);
            setBackground(Color.BLUE);
        }
    }
}
