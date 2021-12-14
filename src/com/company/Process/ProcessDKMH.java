package com.company.Process;

import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessDKMH {
    private List<lichsudangky> lsdk;
    public ProcessDKMH(){
    }
    private void loadlsdk(){
        Database db=new Database();
        lsdk=db.getListLSDK();
        db.closedb();
    }
    public DefaultTableModel loadTableModel(){
        loadlsdk();
        String[] col = new String[]{"Mã SV","Tên Sinh Viên","Mã Nhóm", "Nhóm", "TH", "Mã MH", "Tên Môn Học", "Ngày Đăng ký"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(lichsudangky i:lsdk){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
}
