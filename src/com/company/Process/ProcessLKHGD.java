package com.company.Process;

import com.company.Class.KHGD;
import com.company.Class.NhomLopPhongHoc;
import com.company.Class.VienChucNhomLop;
import com.company.DatabaseConnection.DatabaseKHGD;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessLKHGD {
    private List<KHGD> lsKHGD;
    public ProcessLKHGD(){
    }
    private void loadlsKHGD(){
        DatabaseKHGD db= new DatabaseKHGD();
        lsKHGD=db.getKHGD();
        db.closedb();
    }

    public DefaultTableModel loadTableModel(){
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

    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        loadlsKHGD();
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        for(KHGD i:lsKHGD){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
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

}
