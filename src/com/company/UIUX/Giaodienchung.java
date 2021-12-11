package com.company.UIUX;

import com.company.Main;
import com.company.UIUX.MainUIPanel.TopMenuBar;

import javax.swing.*;
import java.awt.*;

public class Giaodienchung extends JFrame {
    private JPanel Mainpanel = new JPanel();
    private JPanel MenuBarPannel=new TopMenuBar(this);
    private BorderLayout mainlayout = new BorderLayout();
    public Giaodienchung() {
        initUI();
    }
    private void initUI(){
        this.setLayout(mainlayout);
        this.add(MenuBarPannel, BorderLayout.NORTH);
        this.add(Mainpanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void repalaceMainPanel(JPanel panel){
        remove(Mainpanel);
        Mainpanel=panel;
        add(Mainpanel);
    }
}
