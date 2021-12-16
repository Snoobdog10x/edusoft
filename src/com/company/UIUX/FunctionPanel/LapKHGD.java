package com.company.UIUX.FunctionPanel;

import com.company.Class.HocPhan;
import com.company.Process.ProcessLKHGD;
import com.company.Process.ProcessQLMH;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LapKHGD extends JPanel {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel;
    private JPanel CenterPanel;
    public JPanel BottomPanel;
    private JButton add = new JButton("thêm");
    private JButton reload = new JButton("Tải lại bảng");
    private JButton update = new JButton("Cập nhật kế hoạch");
    private JTable MainTable;
    private JScrollPane MainScroll;
    private TableRowSorter<TableModel> rowSorter;
    private ProcessQLMH processQLMH= new ProcessQLMH();
    private BorderLayout MainLayout = new BorderLayout();

    public LapKHGD() {}


}

