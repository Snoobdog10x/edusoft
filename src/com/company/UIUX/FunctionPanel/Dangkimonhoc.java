package com.company.UIUX.FunctionPanel;

import com.company.Process.ProcessDKMH;
import com.company.UIUX.Dangkimonhoc.DangkimonhocFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dangkimonhoc extends JPanel implements ActionListener {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    private JPanel BottomPanel;
    private JButton add = new JButton("Đăng ký môn học");
    private JButton reload = new JButton("Tải lại bảng");
    private JButton update = new JButton("Cập nhật đăng kí");
    private TableRowSorter<TableModel> rowSorter;
    private JTable MainTable;
    private JScrollPane MainScroll;
    private BorderLayout MainLayout = new BorderLayout();
    private ProcessDKMH processDKMH = new ProcessDKMH();

    public Dangkimonhoc() {
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

        addEvent();
    }

    private void loadTable() {
        DefaultTableModel tableModel = processDKMH.loadTableModel();
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
        BottomPanel.add(reload);
        BottomPanel.add(new JLabel("Nhập từ để tìm kiếm trong bảng"));
        BottomPanel.add(jtfFilter);
    }

    private void CenterPanel() {
        CenterPanel = new JPanel();
        CenterPanel.setLayout(MainLayout);
        CenterPanel.add(MainScroll, BorderLayout.CENTER);
        CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
    }

    private JLabel[] LeftLabels = {new JLabel("Mã SV"), new JLabel("Tên Sinh Viên"), new JLabel("Mã Nhóm"), new JLabel("Nhóm")
            , new JLabel("TH"), new JLabel("Mã MH"), new JLabel("Tên Môn Học"), new JLabel("Ngày Đăng ký")};
    private JTextField[] LeftTextfields = {new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    private void LeftPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) (size.width);
        int screenheight = (int) (size.height);
        LeftTextfields[0].setEnabled(false);
        LeftTextfields[2].setEnabled(false);
        LeftTextfields[5].setEnabled(false);
        LeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LeftPanel.setPreferredSize(new Dimension((int) (screenwidth * 0.15), screenheight));
        for (int i = 0; i < 8; i++) {
            LeftTextfields[i].setPreferredSize(new Dimension((int) (screenwidth * 0.14), (int) (screenheight * 0.02)));
            LeftPanel.add(LeftLabels[i]);
            LeftPanel.add(LeftTextfields[i]);
        }
    }

    private void addEvent() {
        add.addActionListener(this);
        update.addActionListener(this);
        reload.addActionListener(this);
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
        MainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try {

                    int j = 0;
                    for (JTextField i : LeftTextfields) {
                        int a = MainTable.convertRowIndexToModel(MainTable.getSelectedRow());
                        System.out.println(a);
                        i.setText(MainTable.getValueAt(a, j++).toString());
                    }
                }catch (Exception e){

                }
            }
        });

    }

    //End Init Panel
    //Start Event
    @Override
    public void actionPerformed(ActionEvent b) {
        if (b.getSource() == add) {
            new DangkimonhocFrame();
        }
        if (b.getSource() == update) {
            System.out.println("hiih");
        }
        if (b.getSource() == reload) {
            System.out.println("hiih");
        }
    }
    //End Event
}
