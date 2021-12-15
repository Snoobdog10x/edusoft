package com.company.UIUX.Vienchuc;

import javax.swing.*;
import java.awt.*;

public class VienchucFrameAdd extends JFrame {

    private int framewidth;
    private int frameheight;
    private JButton confirm = new JButton("Xác nhận");
    private JPanel MainPanel;

    public VienchucFrameAdd() {
        init();
    }

    private void init() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Thêm Viên Chức");
        int screenwidth = size.width;
        int screenheight = size.height;
        framewidth=(int) (screenwidth*0.16);
        frameheight=(int) (screenheight * 0.66);
        setLayout(new BorderLayout());
        setSize(framewidth,frameheight);
        initMainpanel();
        centreWindow();
        setBackground(Color.blue);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private JLabel[] LeftLabels = {new JLabel("Mã Viên Chức"), new JLabel("Loại Viên Chức"), new JLabel("Tên Viên Chức"), new JLabel("Họ lót")
            , new JLabel("SĐT"), new JLabel("Ngày Sinh"), new JLabel("Nơi Sinh"), new JLabel("Email")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    private void initMainpanel() {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        MainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        MainPanel.setPreferredSize(new Dimension((int) (screenwidth*0.15),screenheight));
        for(int i=0;i<8;i++){
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.03)));
            MainPanel.add(LeftLabels[i]);
            MainPanel.add(LeftTextfields[i]);
        }
        MainPanel.add(confirm);
        add(MainPanel);
    }

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
}
