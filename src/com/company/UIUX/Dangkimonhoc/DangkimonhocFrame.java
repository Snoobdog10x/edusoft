package com.company.UIUX.Dangkimonhoc;

import com.company.Class.SinhVien;
import com.company.Process.ProcessDKMH;
import com.company.UIUX.FunctionPanel.Dangkimonhoc;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangkimonhocFrame extends JFrame implements ActionListener {
    private int framewidth;
    private int frameheight;
    private DefaultTableModel svmodel;
    private TableRowSorter<TableModel> svRowsorter;
    private JTable svtable;
    private JScrollPane svScroll;
    private DefaultTableModel nmhmodel;
    private TableRowSorter<TableModel> nmhRowsorter;
    private JTable nmhtable;
    private JScrollPane nmhScroll;
    private JTextField filter_sv = new JTextField();
    private JTextField filter_nmh = new JTextField();
    private Dangkimonhoc panel;
    public DangkimonhocFrame(ProcessDKMH processDKMH, Dangkimonhoc panel) {
        this.panel=panel;
        init(processDKMH);
    }

    private void init(ProcessDKMH processDKMH) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        framewidth = (int) (screenwidth * 0.5);
        frameheight = (int) (screenheight * 0.5);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(framewidth, frameheight);
        sv_inittable(processDKMH);
        nmh_inittable(processDKMH);
        button();
        event();
        centreWindow();
        setBackground(Color.blue);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void sv_inittable(ProcessDKMH processDKMH) {
        svmodel = processDKMH.loadTableModelsv();
        svtable = new JTable(svmodel) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        svtable.setPreferredSize(new Dimension((int) (framewidth * 0.97), (int) (frameheight * 0.3)));
        svRowsorter = new TableRowSorter<>(svtable.getModel());
        svtable.setRowSorter(svRowsorter);
        svtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        svScroll = new JScrollPane(svtable);
        filter_sv.setPreferredSize(new Dimension((int) (framewidth * 0.6), (int) (frameheight * 0.05)));
        svScroll.setPreferredSize(new Dimension((int) (framewidth * 0.97), (int) (frameheight * 0.3)));
        add(new JLabel("Sinh Viên"));
        add(filter_sv);
        add(svScroll);
    }

    private void nmh_inittable(ProcessDKMH processDKMH) {
        nmhmodel = processDKMH.loadTableModelKHGD();
        nmhtable = new JTable(nmhmodel) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        nmhtable.setPreferredSize(new Dimension((int) (framewidth * 0.97), (int) (frameheight * 0.3)));
        nmhRowsorter = new TableRowSorter<>(nmhtable.getModel());
        nmhtable.setRowSorter(nmhRowsorter);
        nmhtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nmhScroll = new JScrollPane(nmhtable);
        nmhScroll.setPreferredSize(new Dimension((int) (framewidth * 0.97), (int) (frameheight * 0.3)));
        filter_nmh.setPreferredSize(new Dimension((int) (framewidth * 0.6), (int) (frameheight * 0.05)));
        add(new JLabel("Nhóm môn học"));
        add(filter_nmh);
        add(nmhScroll);
    }

    private void event() {
        filter_sv.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = filter_sv.getText();
                if (text.trim().length() == 0) {
                    svRowsorter.setRowFilter(null);
                } else {
                    svRowsorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = filter_sv.getText();
                if (text.trim().length() == 0) {
                    svRowsorter.setRowFilter(null);
                } else {
                    svRowsorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        filter_nmh.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = filter_nmh.getText();
                if (text.trim().length() == 0) {
                    nmhRowsorter.setRowFilter(null);
                } else {
                    nmhRowsorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = filter_nmh.getText();
                if (text.trim().length() == 0) {
                    nmhRowsorter.setRowFilter(null);
                } else {
                    nmhRowsorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        dangki.addActionListener(this);
        Cancel.addActionListener(this);
    }

    private JButton dangki = new JButton("Đăng ký");
    private JButton Cancel = new JButton("Hủy");

    private void button() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dangki.setPreferredSize(new Dimension((int) (framewidth * 0.3), (int) (frameheight * 0.07)));
        Cancel.setPreferredSize(new Dimension((int) (framewidth * 0.3), (int) (frameheight * 0.07)));
        panel.setPreferredSize(new Dimension(framewidth, (int) (frameheight * 0.1)));
        panel.add(dangki);
        panel.add(Cancel);
        add(panel);
    }

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    private void DKMH() {
        try {
            int rowsv = svtable.getSelectedRow();
            Object mssv = svtable.getValueAt(rowsv, 0).toString();
            int rowmonhoc = nmhtable.getSelectedRow();
            Object MMH = nmhtable.getValueAt(rowmonhoc, 3);
            Object MNL = nmhtable.getValueAt(rowmonhoc, 0);
            ProcessDKMH DKMH=new ProcessDKMH();
            int value=DKMH.Dangkymonhoc(mssv,MMH,MNL);
            if(value!=0&&value!=-1){
                JOptionPane.showMessageDialog(this,"Đăng ký thành công");
                dispose();
                return;
            }
            if(value==-1){
                JOptionPane.showMessageDialog(this,"Sinh Viên đăng kí trùng");
                return;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Vui lòng chọn sinh viên và môn cần đăng ký");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dangki) {
            DKMH();
            panel.reloadtable();
        }
        if (e.getSource() == Cancel) {
            dispose();
        }
    }
}
