package com.company.UIUX.KHGD;

import com.company.Class.*;
import com.company.Process.ProcessLKHGD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameUpdate extends JFrame implements ActionListener {
    private ProcessLKHGD pss = new ProcessLKHGD();
    private ArrayList<Vienchuc> mvc = pss.getMVCList();
    private ArrayList<HocPhan> HP = pss.getlistMH();
    private String[] listTen = pss.getMVCHotenGV();
    private String[] listThucHanh = {"Có", "Không"};
    private String[] listMMH = pss.getListMH();
    private String[] listMPH = pss.getListPH();
    private JLabel[] listLabel = {new JLabel("Nhóm"), new JLabel("Số lượng đăng kí"), new JLabel("Số lượng thời khóa biểu")};
    JTextField[] listSL = {new JTextField(), new JTextField(), new JTextField()};
    JComboBox[] listComboBox = {new JComboBox(listTen)
            , new JComboBox(listThucHanh), new JComboBox(listMMH), new JComboBox(listMPH)};
    private JLabel[] listLabelComboBox = {new JLabel("Mã VC - Họ Tên GV"), new JLabel("Thực hành"), new JLabel("Mã môn học"), new JLabel("Phòng học")};
    private JButton commit = new JButton("Cập nhật");
    private JLabel MNL ;
    public FrameUpdate() {

    }

    public void uiux(String a[]) {
        setTitle("cập nhật kế hoạch giảng dạy");
        setLayout(new FlowLayout());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        int witdhFrame = (int) (screenwidth * 0.5);
        int heightFrame = (int) (screenwidth * 0.3);
        AddJComboBox();
        setField(a);
        setSize(witdhFrame, heightFrame);
        centreWindow();
        addEvent();
        setVisible(true);

    }
    private int SoSanhchuoi(String[] list, String value) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(value)) return i;
        }
        return 0;

    }

    public void setField(String a[]){
        MNL = new JLabel(a[0].toString());
        for(int j = 0;j<mvc.size();j++){
                if(mvc.get(j).getTen().toString().equalsIgnoreCase(a[9]) && listTen[j].equalsIgnoreCase(mvc.get(j).toString())){
                    listComboBox[0].getModel().setSelectedItem(listTen[j]);
            }
        }
        if (Integer.parseInt(a[2].toString()) != 1) {
            listComboBox[1].getModel().setSelectedItem(listThucHanh[1]);
        }else
            listComboBox[1].getModel().setSelectedItem(listThucHanh[0]);
        listComboBox[1].enable(false);
        for(int i = 0;i<HP.size();i++){
            if(a[3].equalsIgnoreCase(String.valueOf(HP.get(i).getMMH()))&&
            listMMH[i].equalsIgnoreCase(HP.get(i).toString())){
                listComboBox[2].getModel().setSelectedItem(listMMH[i]);
            }
        }
        listComboBox[3].getModel().setSelectedItem(listMPH[SoSanhchuoi(listMPH, a[10])]);
        listComboBox[2].enable(false);
        listComboBox[3].enable(false);
        listSL[0].setText(a[1]);
        listSL[1].setText(a[4]);
        listSL[2].setText(a[5]);
    }
    private void AddJComboBox() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        for (int i = 0; i < listComboBox.length; i++) {
            listLabelComboBox[i].setPreferredSize(new Dimension((int) (screenwidth * 0.1), (int) (screenheight * 0.04)));
            listComboBox[i].setPreferredSize(new Dimension((int) (screenwidth * 0.3), (int) (screenheight * 0.04)));
            add(listLabelComboBox[i]);
            add(listComboBox[i]);
        }

        for (int j = 0; j < listLabel.length; j++) {
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
        if (e.getSource() == commit) {
            Vienchuc vc = new Vienchuc();
            try{
                int mavc = 0;
                int manl = Integer.parseInt(MNL.getText());
                pss.deleteVCNL(String.valueOf(manl));
                for (int i = 0; i < mvc.size(); i++) {
                    if (listTen[listComboBox[0].getSelectedIndex()].equalsIgnoreCase(mvc.get(i).toString())) {
                        mavc = mvc.get(i).getMVC();
                    }
                }
                VienChucNhomLop vcnl = new VienChucNhomLop(manl, mavc);
                pss.addVCNL(vcnl);
                NhomLopHoc nlh = new NhomLopHoc(manl,Integer.parseInt(listSL[0].getText()),
                        Integer.parseInt(listSL[1].getText()),Integer.parseInt(listSL[2].getText()));
                pss.updateNLH(nlh);
                JOptionPane.showMessageDialog(this, "Cap nhat Thanh Cong -");
            }catch (Exception sx){
                JOptionPane.showMessageDialog(this, "ERROR");
            }
        }
    }

}
