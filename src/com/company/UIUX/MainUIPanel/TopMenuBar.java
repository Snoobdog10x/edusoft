package com.company.UIUX.MainUIPanel;

import com.company.UIUX.FunctionPanel.*;
import com.company.UIUX.Giaodienchung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenuBar extends JPanel{
    private JLabel Banner = new JLabel("SGU EDUSOFT");
    public JButton QuanlySV = new JButton("Quản lý sinh viên");
    public JButton QuanlyVC = new JButton("Quản lý viên chức");
    public JButton QuanlyMH = new JButton("Quản lý môn học");
    public JButton LapkHGD = new JButton("Lập kế hoạch giảng dạy");
    public JButton DangkiMH = new JButton("Đăng ký môn học");
    public JButton Home = new JButton("Trang Chủ");
    public TopMenuBar() {
        add(Banner);
        add(Home);
        add(QuanlySV);
        add(QuanlyVC);
        add(QuanlyMH);
        add(LapkHGD);
        add(DangkiMH);
    }
    public void addAction(ActionListener e){
        Home.addActionListener(e);
        QuanlyMH.addActionListener(e);
        QuanlySV.addActionListener(e);
        QuanlyVC.addActionListener(e);
        LapkHGD.addActionListener(e);
        DangkiMH.addActionListener(e);
    }

}
