package com.company.Process;

import com.company.Class.KHGD;
import com.company.Class.NhomLopPhongHoc;
import com.company.Class.*;
import com.company.DatabaseConnection.DatabaseKHGD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProcessLKHGD {
    private List<Object[]> lsKHGD;
    public ProcessLKHGD(){
    }
    private void loadlsKHGD(){
        DatabaseKHGD db= new DatabaseKHGD();
        lsKHGD=db.getkhgd();
        db.closedb();
    }

    public DefaultTableModel loadTableModel(){
        loadlsKHGD();
        String[] col = new String[]{"Mã nhóm lớp","Nhóm","Thực Hành","MMh","Số lượng ĐK","Số lượng TKB",
                "tenmonhoc","số tín chỉ","Số tiết","Tên Giảng viên","MPH"};

        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(Object[] i:lsKHGD){
            defaultTableModel.addRow(i);
        }
        return defaultTableModel;
    }

    public boolean addVCNL(VienChucNhomLop VCNL){
        DatabaseKHGD db = new DatabaseKHGD();
        boolean check = db.addVCNL(VCNL);
        return check;
    }
    public boolean addNLPH(NhomLopPhongHoc NLPH){
        DatabaseKHGD db = new DatabaseKHGD();
        boolean check = db.addNLPH(NLPH);
        return check;
    }
    public String[] getMVCHotenGV(){
        DatabaseKHGD DB = new DatabaseKHGD();
        ArrayList mv = DB.getListMVCHoTenGV();
        String [] a=new String[mv.size()];
        for(int i=0;i<mv.size();i++){
            a[i] = mv.get(i).toString();
        }
        return a;
    }
    public String[] getListPH(){
        DatabaseKHGD DB = new DatabaseKHGD();
        ArrayList mv = DB.getListPH();
        String [] a=new String[mv.size()];
        for(int i=0;i<mv.size();i++){
            a[i] = mv.get(i).toString();
        }
        return a;
    }
    public ArrayList<Vienchuc> getMVCList(){
        DatabaseKHGD DB = new DatabaseKHGD();
        return DB.getListMVCHoTenGV();
    }
    public String[] getListMH(){
        DatabaseKHGD DB = new DatabaseKHGD();
        ArrayList mv = DB.getMH();
        String [] a=new String[mv.size()];
        for(int i=0;i<mv.size();i++){
            a[i] = mv.get(i).toString();
        }
        return a;
    }
    public ArrayList<HocPhan> getlistMH(){
        DatabaseKHGD DB = new DatabaseKHGD();
        return DB.getMH();
    }
    public boolean AddNLH(NhomLopHoc NLH){
        DatabaseKHGD db = new DatabaseKHGD();
        boolean check = db.AddNL(NLH);
        return check;
    }

    public int getMNL() {
        DatabaseKHGD db = new DatabaseKHGD();
        int check = db.getlastNLH();
        return check;
    }
}
