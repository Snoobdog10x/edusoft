package com.company.UIUX.FunctionPanel;

import com.company.Process.ProcessLKHGD;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.company.UIUX.KHGD.FrameAdd;
import com.company.UIUX.KHGD.FrameUpdate;

public class LapKHGD extends JPanel implements ActionListener, MouseListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JButton add = new JButton("Thêm kế hoạch");
    private JButton reload = new JButton("Tải lại bảng");
    private JTable MainTable;
    private JScrollPane MainScroll;
    private TableRowSorter<TableModel> rowSorter;
    private ProcessLKHGD ProcessLKHGD = new ProcessLKHGD();
    private BorderLayout MainLayout = new BorderLayout();

    public LapKHGD() {
        init();
    }

    //Start Init Panel
    private void init() {
        loadTable();
        addEvent();
        BottomPanel();
        CenterPanel();
        setLayout(Mainlayout);
        add(CenterPanel, BorderLayout.CENTER);
    }

    private void loadTable() {
        MainTable = new JTable(ProcessLKHGD.loadTableModel()) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainScroll = new JScrollPane(MainTable);
    }

    private JTextField jtfFilter = new JTextField();

    public void BottomPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(reload);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private void addEvent() {
        add.addActionListener(this);
        reload.addActionListener(this);
        MainTable.addMouseListener(this);
    }

    //End Init Panel
    //Start Event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            FrameAdd FA = new FrameAdd();
        }
        if (e.getSource() == reload) {
            reloadtable();
        }
    }

    private void reloadtable() {
        MainTable.setModel(ProcessLKHGD.loadTableModel());
    }

    //End Event
    private void addAction() {

    }

    private void clickTable() {
        FrameUpdate up = new FrameUpdate();
        int row = MainTable.getSelectedRow();
        String a[] = new String[11];
        if (row >= 0) {
            for (int i = 0; i < 11; i++) {
                a[i] = MainTable.getValueAt(row, i).toString();
                System.out.println(MainTable.getValueAt(row, i).toString());
            }

        }
        up.uiux(a);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickTable();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

