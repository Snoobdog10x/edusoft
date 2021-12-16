package com.company.UIUX.FunctionPanel;

import com.company.UIUX.MainUIPanel.TopMenuBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LapKHGD extends JPanel {
    private JPanel LeftPanel, CenterPanel, TopPanel, BottomPanel;
    private JTable TableKHGD;
    private JButton[] northButtonArray = {new JButton("DS Dữ liệu học kì"), new JButton("")};
    private JLabel[] LeftLabelArray = {new JLabel("Mã học phần"), new JLabel("Tên nhóm lớp")
            , new JLabel("Tên môn học"), new JLabel("họ tên GV"), new JLabel("Số tiết")};
    private JTextField[] LeftTextFieldArray = {new JTextField(), new JTextField()
            , new JTextField(), new JTextField(), new JTextField()};
    private JScrollPane sp = new JScrollPane(TableKHGD);
    private DefaultTableModel model = new DefaultTableModel();

    public LapKHGD() {
        InitUI();
    }

    private void InitUI() {
        TopPanel();
        lefPanel();

        add(TopPanel, BorderLayout.NORTH);
        add(TableKHGD, BorderLayout.CENTER);
        add(LeftPanel, BorderLayout.WEST);


    }

    private void TopPanel() {
        TopPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < northButtonArray.length; i++) {

        }

    }

    private void lefPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth * 0.15), screenheight));
        for (int i = 0; i < 6; i++) {
            LeftTextFieldArray[i].setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
            LeftPanel.add(LeftLabelArray[i]);
            LeftPanel.add(LeftTextFieldArray[i]);
        }
    }

    private void TableCenter(String titleTable[]) {
        TableKHGD.setModel(model);
        for (int i = 0; i < titleTable.length; i++) {
            model.addColumn(titleTable[i]);
        }
    }

    //Action Buton in north Panel.
    private void ActionNortheButtonPanel(JButton a) {
        if (a.getText().equalsIgnoreCase("DS Dữ liệu học kì")) {
            String DLHKtable[] = {"Mã môn học", "Tên môn học", "Mã học phần", ""};
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TableCenter(DLHKtable);
                }
            });
        }
        else if(a.getText().equalsIgnoreCase("")){
            String x[]= {};
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }

    }
}

