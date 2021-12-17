package com.company.UIUX.KHGD;

import com.company.Class.*;
import com.company.Process.ProcessLKHGD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameAdd extends JFrame implements ActionListener {
    private ProcessLKHGD pss = new ProcessLKHGD();
    private ArrayList<Vienchuc> mvc = pss.getMVCList();
    private ArrayList<HocPhan> HP = pss.getlistMH();
    private String[] listTen = pss.getMVCHotenGV();
    private String[] listThucHanh = {"Có","Không"};
    private String[] listMMH = pss.getListMH();
    private String[] listMPH = pss.getListPH();
    private JLabel[] listLabel = {new JLabel("Nhóm"),new JLabel("Số lượng đăng kí"), new JLabel("Số lượng thời khóa biểu")};
    JTextField[] listSL = {new JTextField(),new JTextField(), new JTextField()};
    JComboBox[] listComboBox = {new JComboBox(listTen)
            , new JComboBox(listThucHanh), new JComboBox(listMMH), new JComboBox(listMPH)};
    private JLabel[] listLabelComboBox = {new JLabel("Mã VC - Họ Tên GV"), new JLabel("Thực hành"), new JLabel("Mã môn học"), new JLabel("Phòng học")};
    private JButton commit = new JButton("Thêm");

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
        addEvent();
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
            try {
                int mavc = 0;
                int manl = 0;
                int maph = Integer.parseInt(listMPH[listComboBox[3].getSelectedIndex()]);
                int MMH=0;
                int thuchanh=0;
                if(listThucHanh[listComboBox[1].getSelectedIndex()].equalsIgnoreCase("Có")){
                    thuchanh=1;
                }
                for (int i = 0; i < HP.size(); i++) {
                    if (listMMH[listComboBox[2].getSelectedIndex()].equalsIgnoreCase(HP.get(i).toString())){
                        MMH = HP.get(i).getMMH();

                    }
                }
                NhomLopHoc nlh = new NhomLopHoc(Integer.parseInt(listSL[0].getText().toString()),thuchanh,MMH,Integer.parseInt(listSL[1].getText().toString()),Integer.parseInt(listSL[2].getText().toString()));
                pss.AddNLH(nlh);
                manl = pss.getMNL();
                for (int i = 0; i < mvc.size(); i++) {
                    if (listTen[listComboBox[0].getSelectedIndex()].equalsIgnoreCase(mvc.get(i).toString())){
                        mavc = mvc.get(i).getMVC();

                    }
                }
                manl = pss.getMNL();
                VienChucNhomLop vcnl = new VienChucNhomLop(manl,mavc);
                pss.addVCNL(vcnl);
                NhomLopPhongHoc NLPH = new NhomLopPhongHoc(manl,maph);
                pss.addNLPH(NLPH);
                JOptionPane.showMessageDialog(this,"Them Thanh Cong");
            }catch (Exception s){
                JOptionPane.showMessageDialog(this,"ERROR");
            }
        }
    }
}
