package com.company.Process;

import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;
import com.company.Class.HocPhan;
import com.company.DatabaseConnection.MonHocDatabase;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessQLMH {
    private List<HocPhan> lshp;
    public ProcessQLMH(){
    }
    private void loadlsdk(){
        MonHocDatabase db= new MonHocDatabase();
        lshp=db.getListHP();
        db.closedb();
    }
    public DefaultTableModel loadTableModel(){
        loadlsdk();
        String[] col = new String[]{"Mã môn học","Tên Môn Học","Mã Bộ Môn", "Số Tín Chỉ", "Số Tiết", "Số Tiết Thực Hành"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(HocPhan i:lshp){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
}
