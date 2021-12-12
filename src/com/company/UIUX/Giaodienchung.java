package com.company.UIUX;

import com.company.Main;
import com.company.UIUX.MainUIPanel.MainPanel;
import com.company.UIUX.MainUIPanel.TopMenuBar;

import javax.swing.*;
import java.awt.*;

public class Giaodienchung extends JFrame {
    private JPanel Mainpanel = new MainPanel();
    private JPanel MenuBarPannel=new TopMenuBar(this);
    private BorderLayout mainlayout = new BorderLayout();
    public Giaodienchung() {
        initUI();
    }
    private void initUI(){
        setTitle("SGU EduSoft");
        setLayout(mainlayout);
        add(MenuBarPannel, BorderLayout.NORTH);
        add(Mainpanel, BorderLayout.CENTER);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void repalaceMainPanel(JPanel panel){
        remove(Mainpanel);
        Mainpanel=panel;
        add(Mainpanel);
        revalidate();

    }
}
