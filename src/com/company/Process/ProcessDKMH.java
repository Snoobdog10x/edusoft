package com.company.Process;

import com.company.Class.KHGD;
import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;
import com.company.DatabaseConnection.DatabaseKHGD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessDKMH {
    private List<lichsudangky> lsdk;
    private List<SinhVien> qlsv;
    private List<KHGD> lsKHGD;
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
        Database db=new Database();
        qlsv=db.getListQLSV();
        db.closedb();
    }
    public DefaultTableModel loadTableModelsv(){
        loadqlsv();
        String[] col = new String[]{"Mã SV","Mã lớp","Họ lót", "Tên", "Ngày sinh", "SĐT", "Mã ngành", "Nơi sinh","Email"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(SinhVien i : qlsv){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
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
    private void loadlsKHGD(){
        DatabaseKHGD db= new DatabaseKHGD();
        lsKHGD=db.getKHGD();
        db.closedb();
    }

    public DefaultTableModel loadTableModelKHGD(){
        loadlsKHGD();
        String[] col = new String[]{"Họ lót","Tên GV","Mã nhóm lớp","Nhóm","Thực hành","Mã môn Học",
                "Số lượng ĐK","Số lượng tkb"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(KHGD i:lsKHGD){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
}
