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
    private void loadlsmh(){
        MonHocDatabase db= new MonHocDatabase();
        lshp=db.getListHP();
        db.closedb();
    }

    public DefaultTableModel loadTableModel(){
        loadlsmh();
        String[] col = new String[]{"Mã môn học","Tên Môn Học","Mã Bộ Môn", "Số Tín Chỉ", "Số Tiết", "Số Tiết Thực Hành"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(HocPhan i:lshp){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }

    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        loadlsmh();
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        for(HocPhan i:lshp){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
    }

    public boolean addMH(HocPhan monhoc){
        MonHocDatabase db= new MonHocDatabase();
        boolean check = db.addMH(monhoc);
        return check;
    }

    public boolean updateMH(HocPhan monhoc){
        MonHocDatabase db= new MonHocDatabase();
        boolean check = db.updateMH(monhoc);
        return check;
    }

}
