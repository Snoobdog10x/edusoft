package com.company.UIUX.MainUIPanel;

import com.company.Main;
import com.company.UIUX.FunctionPanel.*;
import com.company.UIUX.Giaodienchung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenuBar extends JPanel implements ActionListener {
    private JButton QuanlySV=new JButton("Quản lý sinh viên");
    private JButton QuanlyVC=new JButton("Quản lý viên chức");
    private JButton QuanlyMH=new JButton("Quản lý môn học");
    private JButton LapkHGD=new JButton("Lập kế hoạch giảng dạy");
    private JButton DangkiMH=new JButton("Đăng ký môn học");
    private Giaodienchung MainFrame;
    public TopMenuBar(Giaodienchung MainFrame){
        add(QuanlySV);
        add(QuanlyVC);
        add(QuanlyMH);
        add(LapkHGD);
        this.MainFrame= MainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==QuanlySV){
            MainFrame.repalaceMainPanel(new Quanlysinhvien());
        }
        if(e.getSource()==QuanlyVC){
            MainFrame.repalaceMainPanel(new Quanlyvienchuc());
        }
        if(e.getSource()==QuanlyMH){
            MainFrame.repalaceMainPanel(new Quanlymonhoc());
        }
        if(e.getSource()==LapkHGD){
            MainFrame.repalaceMainPanel(new LapKHGD());
        }
        if(e.getSource()==DangkiMH){
            MainFrame.repalaceMainPanel(new Dangkimonhoc());
        }
    }
}
