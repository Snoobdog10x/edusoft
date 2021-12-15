package com.company.UIUX.FunctionPanel;

import com.company.Process.ProcessDKMH;
import com.company.Process.ProcessQLVC;
import com.company.UIUX.Vienchuc.VienchucFrameAdd;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class Quanlyvienchuc extends JPanel implements ActionListener {

    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JButton add = new JButton("Thêm Viên chức");
    private JButton reload = new JButton("Tải Lại Bảng");
    private JButton update = new JButton("Cập nhật viên chức");
    private BorderLayout MainLayout = new BorderLayout();
    private TableRowSorter<TableModel> rowSorter;
    private JTable MainTable;
    private JScrollPane MainScroll;
    private ProcessQLVC processQLVC = new ProcessQLVC();


    public Quanlyvienchuc() {
        init();
    }
    private JTextField jtfFilter = new JTextField();
    private void init() {
        loadTable();
        addEvent();
        BottomPanel();
        LeftPanel();
        CenterPanel();
        setLayout(Mainlayout);
        add(LeftPanel, BorderLayout.WEST);
        add(CenterPanel, BorderLayout.CENTER);
    }
    private void loadTable() {
        MainTable = new JTable(processQLVC.loadTableModel());
        rowSorter = new TableRowSorter<>(MainTable.getModel());
        MainScroll = new JScrollPane(MainTable);
    }
    private void BottomPanel() {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        jtfFilter.setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.02)));
        BottomPanel = new JPanel();
        BottomPanel.add(add);
        BottomPanel.add(update);
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
    private JLabel[] LeftLabels = {new JLabel("Mã Viên Chức"), new JLabel("Loại Viên Chức"), new JLabel("Tên Viên Chức"), new JLabel("Họ lót")
            , new JLabel("SĐT"), new JLabel("Ngày Sinh"), new JLabel("Nơi Sinh"), new JLabel("Email")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    private void LeftPanel() {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth= (int) (size.width);
        int screenheight= (int) (size.height);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth*0.15),screenheight));
        for(int i=0;i<8;i++){
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth*0.14), (int) (screenheight*0.03)));
            LeftPanel.add(LeftLabels[i]);
            LeftPanel.add(LeftTextfields[i]);
        }
    }
    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }
    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
        reload.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            new VienchucFrameAdd();
        }
        if (e.getSource() == update) {
            System.out.println("hiih");
        }
        if (e.getSource() == reload) {
            System.out.println("hiih");
        }
    }
}
