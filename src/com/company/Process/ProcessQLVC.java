package com.company.Process;

import com.company.Class.lichsudangky;
import com.company.Class.vienchuc;
import com.company.DatabaseConnection.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessQLVC {
    private List<vienchuc> lvc;
    public ProcessQLVC(){

    }
    private void loadListVienChuc(){
        Database db=new Database();
        lvc=db.getListVC();
        db.closedb();
    }
    public DefaultTableModel loadTableModel(){
        loadListVienChuc();
        String[] col = new String[]{"Mã Viên Chức","Loại Viên Chức", "Tên Viên Chức","Họ lót", "SĐT","Ngày Sinh", "Nơi Sinh", "Email"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(vienchuc i:lvc){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
}
