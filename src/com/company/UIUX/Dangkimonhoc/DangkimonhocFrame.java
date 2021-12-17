package com.company.UIUX.Dangkimonhoc;

import com.company.Class.SinhVien;
import com.company.Process.ProcessDKMH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class DangkimonhocFrame extends JFrame {
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
    public DangkimonhocFrame(ProcessDKMH processDKMH) {
        init(processDKMH);
    }

    private void init(ProcessDKMH processDKMH) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = size.width;
        int screenheight = size.height;
        framewidth=(int) (screenwidth * 0.5);
        frameheight=(int) (screenheight * 0.5);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(framewidth,frameheight);
        sv_inittable(processDKMH);
        nmh_inittable(processDKMH);
        centreWindow();
        setBackground(Color.blue);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void sv_inittable(ProcessDKMH processDKMH){
        svmodel=processDKMH.loadTableModelsv();
        svtable=new JTable(svmodel){
            public boolean editCellAt (int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        svtable.setPreferredSize(new Dimension((int)(framewidth*0.97),(int)(frameheight*0.3)));
        svRowsorter=new TableRowSorter<>(svtable.getModel());
        svtable.setRowSorter(svRowsorter);
        svtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        svScroll=new JScrollPane(svtable);
        svScroll.setPreferredSize(new Dimension((int)(framewidth*0.97),(int)(frameheight*0.3)));
        add(new JLabel("Sinh Viên"));
        add(svScroll);
    }
    private void nmh_inittable(ProcessDKMH processDKMH){
        nmhmodel=processDKMH.loadTableModelKHGD();
        nmhtable=new JTable(nmhmodel){
            public boolean editCellAt (int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        nmhtable.setPreferredSize(new Dimension((int)(framewidth*0.97),(int)(frameheight*0.3)));
        nmhRowsorter=new TableRowSorter<>(nmhtable.getModel());
        nmhScroll=new JScrollPane(nmhtable);
        nmhScroll.setPreferredSize(new Dimension((int)(framewidth*0.97),(int)(frameheight*0.3)));
        add(new JLabel("Nhóm môn học"));
        add(nmhScroll);
    }
    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
}
