package com.company.Process;

import com.company.Class.HocPhan;
import com.company.Class.KHGD;
import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;
import com.company.DatabaseConnection.DatabaseKHGD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ProcessDKMH {
    private List<lichsudangky> lsdk;
    private List<SinhVien> qlsv;
    private List<Object[]> lsKHGD;
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
    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        loadlsdk();
        for(lichsudangky i:lsdk){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
    }

    public int updatelsdk(lichsudangky ls){
        Database db=new Database();
        int values=db.updatelsdk(ls);
        db.closedb();
        return values;
    }
    private void loadlsKHGD(){
        Database db= new Database();
        lsKHGD=db.getkhgd();
        db.closedb();
    }

    public DefaultTableModel loadTableModelKHGD(){
        loadlsKHGD();
        String[] col = new String[]{"Mã nhóm lớp","Nhóm","Thực Hành","MMH","Số lượng ĐK","Số lượng TKB",
                "Tên môn học","số tín chỉ","Số tiết","Tên Giảng viên","MPH"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(Object[] i:lsKHGD){
            defaultTableModel.addRow(i);
        }
        return defaultTableModel;
    }
    public int Dangkymonhoc(Object MSSV, Object MMH,Object MNL){
        int mssv=Integer.parseInt(MSSV.toString());
        int mmh=Integer.parseInt(MMH.toString());
        int mnl=Integer.parseInt(MNL.toString());
        Database db=new Database();
        try {
            int value=db.DKMH(mssv,mmh,mnl);
            return value;
        } catch (SQLException e) {
            return -1;
        }
    }
}
