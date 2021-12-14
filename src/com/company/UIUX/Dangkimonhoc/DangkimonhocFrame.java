package com.company.UIUX.Dangkimonhoc;

import com.company.UIUX.FunctionPanel.Dangkimonhoc;

import javax.swing.*;

import java.awt.*;

public class DangkimonhocFrame extends JFrame {
    private Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
    private int screenwidth=  size.width;
    private int screenheight= size.height;
    public DangkimonhocFrame(){
        setLayout(new BorderLayout());
        setSize((int)(screenwidth*0.3),(int)(screenheight*0.5));
        centreWindow();
        setVisible(true);
        setResizable(false);
        setBackground(Color.blue);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
}
