package com.company.UIUX.KHGD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAdd extends JFrame implements ActionListener {
    private String[] listTen={""};
    private String[] listMNL={""};
    private String[] listNhom={""};
    private String[] listThucHanh={""};
    private String[] listMMH={""};
    private JLabel[] listLabel = {new JLabel("Số lượng đăng kí"),new JLabel("Số lượng thời khóa biểu")};
    private JTextField[] listSL= {new JTextField(),new JTextField()};
    private JComboBox[] listComboBox = {new JComboBox(listTen), new JComboBox(listMNL)
            , new JComboBox(listNhom), new JComboBox(listThucHanh), new JComboBox(listMMH)};
    private JLabel[] listLabelComboBox = {new JLabel("Họ và tên GV"), new JLabel("Mã nhóm lớp"),
            new JLabel("Nhóm"), new JLabel("Thực hành"), new JLabel("Mã môn học")};
    private JButton commit= new JButton("Thêm");
    public FrameAdd() {
        uiux();
    }

    private void uiux() {
        setTitle("Thêm kế hoạch giảng dạy");
        setLayout(new FlowLayout());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        int witdhFrame = (int) (screenwidth * 0.5);
        int heightFrame = (int) (screenwidth * 0.3);
        addJComboBox();
        setSize(witdhFrame, heightFrame);
        centreWindow();
        setVisible(true);
    }

    private void addJComboBox() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        for (int i = 0; i < listComboBox.length; i++) {
            listLabelComboBox[i].setPreferredSize(new Dimension((int) (screenwidth * 0.1), (int) (screenheight * 0.04)));
            listComboBox[i].setPreferredSize(new Dimension((int) (screenwidth * 0.3), (int) (screenheight * 0.04)));
            add(listLabelComboBox[i]);
            add(listComboBox[i]);
        }
        for (int j = 0; j<listLabel.length;j++){
            listLabel[j].setPreferredSize(new Dimension((int) (screenwidth * 0.2), (int) (screenheight * 0.04)));
            listSL[j].setPreferredSize(new Dimension((int) (screenwidth * 0.15), (int) (screenheight * 0.04)));
            add(listLabel[j]);
            add(listSL[j]);
        }
        commit.setPreferredSize(new Dimension((int) (screenwidth * 0.15), (int) (screenheight * 0.04)));
        add(commit);
    }

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
    private void addEvent() {
        commit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==commit){
            JOptionPane.showMessageDialog( this,
                    "Notification");
        }
    }
}