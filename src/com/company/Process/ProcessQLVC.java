package com.company.Process;

import com.company.Class.HocPhan;
import com.company.Class.Vienchuc;
import com.company.DatabaseConnection.MonHocDatabase;
import com.company.DatabaseConnection.VienchucDatabse;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessQLVC {
    private List<Vienchuc> lvc;
    public ProcessQLVC(){

    }
    private void loadListVienChuc(){
        VienchucDatabse vcdb=new VienchucDatabse();
        lvc=vcdb.getListVC();
        vcdb.closedb();
    }
    public DefaultTableModel loadTableModel(){
        loadListVienChuc();
        String[] col = new String[]{"Mã Viên Chức","Loại Viên Chức", "Tên Viên Chức","Họ lót", "SĐT","Ngày Sinh", "Nơi Sinh", "Email"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(Vienchuc i:lvc){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        loadListVienChuc();
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        for(Vienchuc i:lvc){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
    }
    public boolean addVC(Vienchuc vienchuc){
        VienchucDatabse db= new VienchucDatabse();
        boolean check = db.addVC(vienchuc);
        return check;
    }

    public boolean updateVC(Vienchuc vienchuc){
        VienchucDatabse db= new VienchucDatabse();
        boolean check = db.updateVC(vienchuc);
        return check;
    }
}
