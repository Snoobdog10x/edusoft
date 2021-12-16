package com.company.UIUX.Dangkimonhoc;

import com.company.Process.ProcessDKMH;

import javax.swing.*;
import java.awt.*;

public class DangkimonhocFrame extends JFrame {
    private int framewidth;
    private int frameheight;
    private JPanel MainPanel;
    public DangkimonhocFrame(ProcessDKMH processDKMH) {
        init(processDKMH);
    }

    private void init(ProcessDKMH processDKMH) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        framewidth=(int) (screenwidth * 0.3);
        frameheight=(int) (screenheight * 0.5);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(processDKMH.getsinhvien());
        setSize(framewidth,frameheight);
        centreWindow();
        setBackground(Color.blue);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
}
