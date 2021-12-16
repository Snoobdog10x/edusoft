package com.company.UIUX.FunctionPanel;

import com.company.Process.ProcessQLSV;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Quanlysinhvien extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    private JPanel BottomPanel;
    private JButton add = new JButton("Đăng ký sinh viên");
    private JButton update = new JButton("Cập nhật");
    private TableRowSorter<TableModel> rowSorter;
    private JTable MainTable;
    private JScrollPane MainScroll;
    private BorderLayout MainLayout = new BorderLayout();
    private ProcessQLSV processQLSV = new ProcessQLSV();

    public Quanlysinhvien() {
        init();
    }

    //Start Init Panel
    private void init() {
        BottomPanel();
        LeftPanel();
        loadTable();
        CenterPanel();
        setLayout(Mainlayout);
        add(LeftPanel, BorderLayout.WEST);
        add(CenterPanel, BorderLayout.CENTER);
    }

    private void loadTable() {
        DefaultTableModel tableModel = processQLSV.loadTableModel();
        MainTable = new JTable(tableModel) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }

        };
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainTable.setRowSorter(rowSorter);
        MainScroll = new JScrollPane(MainTable);
    }

    private JTextField jtfFilter = new JTextField();
    private void BottomPanel() {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private JLabel[] LeftLabels = {new JLabel("Mã SV"), new JLabel("Mã lớp"), new JLabel("Tên lót"), new JLabel("Tên")
            , new JLabel("Ngày sinh"), new JLabel("SĐT"), new JLabel("Mã ngành"), new JLabel("Nơi sinh"),new JLabel("Email")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField(),new JTextField()};

    private void LeftPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        LeftTextfields[0].setEnabled(false);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth * 0.15), screenheight));
        for (int i = 0; i < 9; i++) {
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
            LeftPanel.add(LeftLabels[i]);
            LeftPanel.add(LeftTextfields[i]);
        }
    }
    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
