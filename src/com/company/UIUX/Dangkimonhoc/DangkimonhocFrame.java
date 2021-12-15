package com.company.UIUX.Dangkimonhoc;

import javax.swing.*;
import java.awt.*;

public class DangkimonhocFrame extends JFrame {
    private int framewidth;
    private int frameheight;
    private JPanel MainPanel;

    public DangkimonhocFrame() {
        init();
    }

    private void init() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        framewidth=(int) (screenwidth * 0.3);
        frameheight=(int) (screenheight * 0.5);
        setLayout(new BorderLayout());
        setSize(framewidth,frameheight);
        initMainpanel();
        centreWindow();
        setBackground(Color.blue);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initMainpanel() {
        MainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(MainPanel);
    }

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
}
