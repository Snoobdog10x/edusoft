package com.company.UIUX.MainUIPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JLabel welcome=new JLabel("Chào mừng đến với SGU EduSoft");
    private BorderLayout MainLayout=new BorderLayout();
    public MainPanel(){
        setLayout(MainLayout);
        add(welcome);
        setBackground(Color.BLUE);
    }
}
