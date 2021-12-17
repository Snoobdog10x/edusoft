package com.company.UIUX.KHGD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameUpdate extends JFrame implements ActionListener {
    private String[] listTen={""};
    private String[] listMNL={""};
    private String[] listNhom={""};
    private String[] listThucHanh={""};
    private String[] listMMH={""};
    private String[] listMPH={""};
    private JLabel[] listLabel = {new JLabel("Số lượng đăng kí"),new JLabel("Số lượng thời khóa biểu")};
    private JTextField[] listSL= {new JTextField(),new JTextField()};
    private JComboBox[] listComboBox = {new JComboBox(listTen), new JComboBox(listMNL)
            , new JComboBox(listNhom), new JComboBox(listThucHanh), new JComboBox(listMMH),new JComboBox(listMPH)};
    private JLabel[] listLabelComboBox = {new JLabel("Họ và tên GV"), new JLabel("Mã nhóm lớp"),
            new JLabel("Nhóm"), new JLabel("Thực hành"), new JLabel("Mã môn học"),new JLabel("Phòng học")};
    private JButton update= new JButton("Cập nhật");
    public FrameUpdate() {
        uiux();
    }

    private void uiux() {
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
        update.setPreferredSize(new Dimension((int) (screenwidth * 0.15), (int) (screenheight * 0.04)));
        add(update);
    }

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
    public void setJComboBox(String[] a){
        for(int i = 0;i<listComboBox.length+2;i++){
            listComboBox[i].setSelectedItem(a[i]);
            if(i>=1 && i<5){
                listComboBox[i].enable(false);
            }
            if(i >= 5){
                listSL[i].setText(a[i]);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==update){
        }
    }
}
