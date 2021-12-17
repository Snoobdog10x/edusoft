package com.company.Process;

import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;
import com.company.DatabaseConnection.SinhvienDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessDKMH {
    private List<lichsudangky> lsdk;
    private List<SinhVien> qlsv;

    public ProcessDKMH(){
    }
    private void loadlsdk(){
        Database db=new Database();
        lsdk=db.getListLSDK();
        db.closedb();
    }
    private void loadsv(){
        Database db=new Database();
        lsdk=db.getListLSDK();
        db.closedb();
    }
    private void loadqlsv(){
        SinhvienDatabase db=new SinhvienDatabase();
        qlsv=db.getListQLSV();
        db.closedb();
    }
    public JComboBox getsinhvien(){
        loadqlsv();
        JComboBox<SinhVien> comboBox=new JComboBox<SinhVien>();
        for(SinhVien i:qlsv)
        comboBox.addItem(i);
        return comboBox;
    }
    public DefaultTableModel loadTableModel(){
        loadlsdk();
        String[] col = new String[]{"ID","Mã SV","Tên Sinh Viên","Mã Nhóm", "Nhóm", "TH", "Mã MH", "Tên Môn Học", "Ngày Đăng ký"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(lichsudangky i:lsdk){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
    public void updatelsdk(lichsudangky ls){
        Database db=new Database();
        db.updatelsdk(ls);
        db.closedb();
    }
}
