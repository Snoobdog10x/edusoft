package com.company.UIUX.FunctionPanel;

import javax.swing.*;
import java.awt.*;

public class LapKHGD extends JPanel {
    JTable KHGD;
    JButton add,search;
    JTextField searchAll;
    JScrollPane sp = new JScrollPane();
    public LapKHGD() {
        InitUI();
    }
    private void InitUI(){
        searchAll = new JTextField("nhập thông tin cần tìm");
        search = new JButton("Tìm kiếm");
        add(searchAll,BorderLayout.NORTH);
        add(search, BorderLayout.NORTH);
        add = new JButton("Them");
        //sp.add(KHGD);
        //add(KHGD,BorderLayout.CENTER);
        KHGD = new JTable();
        setVisible(true);

    }
}
