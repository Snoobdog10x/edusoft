package com.company.Process;

import com.company.Class.lichsudangky;
import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessLKHGD {
    private List<lichsudangky> lsdk;
    public ProcessLKHGD(){
    }
    private void loadlsdk(){
        Database db=new Database();
        lsdk=db.getListLSDK();
    }
    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        loadlsdk();
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        for(lichsudangky i:lsdk){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
    }
    public DefaultTableModel loadTableModel(){
        loadlsdk();
        String[] col = new String[]{"Mã GV","Tên Giảng Viên","Mã MH","Tên Môn Học","Mã học phần", "Nhóm", "TH"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(lichsudangky i:lsdk){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
}
