package com.company.UIUX.MainUIPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel  {
    private JLabel welcome = new JLabel("Chào mừng đến với SGU EduSoft");
    private ImageIcon logo = new ImageIcon("./src/image/logo.png");
    private JLabel l1 = new JLabel("",logo,JLabel.CENTER);
    private BorderLayout MainLayout = new BorderLayout();

    public MainPanel() {
        setLayout(MainLayout);
        welcome.setBounds(565,100,500,50);
        welcome.setFont(new Font("Serif", Font.PLAIN, 30));
        add(welcome);
        add(l1);
        setBackground(Color.CYAN);
    }
}
