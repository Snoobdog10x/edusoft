package com.company.UIUX.KHGD;

import com.company.Class.Vienchuc;
import com.company.Process.ProcessLKHGD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameUpdate extends JFrame implements ActionListener {
    private ProcessLKHGD pss = new ProcessLKHGD();
    private String[] listTen=  pss.getMVCHotenGV();
    private String[] listMNL={""};
    private String[] listNhom={""};
    private String[] listThucHanh={""};
    private String[] listMMH={""};
    private String[] listMPH={""};
    private JLabel[] listLabel = {new JLabel("Số lượng đăng kí"),new JLabel("Số lượng thời khóa biểu")};
    JTextField[] listSL= {new JTextField(),new JTextField()};
    JComboBox[] listComboBox = {new JComboBox(listTen), new JComboBox(listMNL)
            , new JComboBox(listNhom), new JComboBox(listThucHanh), new JComboBox(listMMH),new JComboBox(listMPH)};
    private JLabel[] listLabelComboBox = {new JLabel("Mã VC - Họ Tên GV"), new JLabel("Mã nhóm lớp"),
            new JLabel("Nhóm"), new JLabel("Thực hành"), new JLabel("Mã môn học"),new JLabel("Phòng học")};
    private JButton commit= new JButton("Thêm");
    public FrameUpdate() {

    }

    public void uiux(String a[]) {
        setTitle("Thêm kế hoạch giảng dạy");
        setLayout(new FlowLayout());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        int witdhFrame = (int) (screenwidth * 0.5);
        int heightFrame = (int) (screenwidth * 0.3);
        addJComboBox(a);
        setSize(witdhFrame, heightFrame);
        centreWindow();
        setVisible(true);
    }
    private int SoSanhchuoi(String[] list, String value){
        for(int i=0; i<list.length;i++){
            if(list[i].equals(value)) return i;
        } return 0;

    }
    private void addJComboBox(String a[]) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);

        listComboBox[0].getModel().setSelectedItem(listTen[SoSanhchuoi(listTen,"2")]);
        listComboBox[1].getModel().setSelectedItem(listMNL[SoSanhchuoi(listMNL,a[0])]);
        listComboBox[2].getModel().setSelectedItem(listNhom[SoSanhchuoi(listNhom,a[1])]);
        listComboBox[3].getModel().setSelectedItem(listThucHanh[SoSanhchuoi(listThucHanh,a[2])]);
        listComboBox[4].getModel().setSelectedItem(listMMH[SoSanhchuoi(listMMH,a[3])]);
        listComboBox[5].getModel().setSelectedItem(listMPH[SoSanhchuoi(listMPH,a[10])]);
        listSL[0].setText(a[4]);
        listSL[1].setText(a[5]);
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
            Vienchuc vc = new Vienchuc();

            JOptionPane.showMessageDialog( this,
                    "Notification");
        }
    }
}
