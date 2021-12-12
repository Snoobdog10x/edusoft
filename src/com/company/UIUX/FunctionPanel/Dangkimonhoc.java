package com.company.UIUX.FunctionPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dangkimonhoc extends JPanel {
    private BorderLayout Mainlayout = new BorderLayout();
    private JPanel LeftPanel = new LeftPanel();
    private JPanel CenterPanel = new CenterPanel();

    public Dangkimonhoc() {
        setLayout(Mainlayout);
        add(LeftPanel, BorderLayout.WEST);
        add(CenterPanel, BorderLayout.CENTER);
    }
    //CenterPanel
    private class CenterPanel extends JPanel {
        private JTable MainTable = new JTable();
        private JScrollPane MainScroll = new JScrollPane(MainTable);
        private BorderLayout MainLayout = new BorderLayout();
        private BottomPanel BottomPanel = new BottomPanel();

        public CenterPanel() {
            setLayout(MainLayout);
            add(MainScroll, BorderLayout.CENTER);
            add(BottomPanel, BorderLayout.SOUTH);
        }
    }
    //BottomPanel
    private class BottomPanel extends JPanel implements ActionListener {
        private JButton add = new JButton("Đăng ký môn học");
        private JButton update = new JButton("Cập nhật đăng kí");
        private JPanel ParentPanel;

        public BottomPanel() {
            add(add);
            add(update);
            add.addActionListener(this);
            update.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {

            }
            if (e.getSource() == update) {

            }

        }
    }
    //LeftPanel
    private class LeftPanel extends JPanel {
    }

}
