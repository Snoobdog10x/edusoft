package com.company.Process;

import com.company.Class.HocPhan;
import com.company.Class.SinhVien;
import com.company.Class.Vienchuc;
import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;
import com.company.DatabaseConnection.MonHocDatabase;
import com.company.DatabaseConnection.SinhvienDatabase;
import com.company.DatabaseConnection.VienchucDatabse;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessQLSV {
    private List<SinhVien> qlsv;
    public ProcessQLSV(){
    }
    private void loadqlsv(){
        SinhvienDatabase db=new SinhvienDatabase();
        qlsv=db.getListQLSV();
        db.closedb();
    }
    public boolean addsinhvien(SinhVien sv){
        SinhvienDatabase db = new SinhvienDatabase();
        boolean check = db.addsv(sv);
        return check;
    }
    public boolean updateSV(SinhVien sv){
        SinhvienDatabase db= new SinhvienDatabase();
        boolean check = db.updateSV(sv);
        return check;
    }
    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        loadqlsv();
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        for(SinhVien i: qlsv){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
    }
    public DefaultTableModel loadTableModel(){
        loadqlsv();
        String[] col = new String[]{"Mã SV","Mã lớp","Họ lót", "Tên", "Ngày sinh", "SĐT", "Mã ngành", "Nơi sinh","Email"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(SinhVien i : qlsv){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        System.out.println(qlsv.toString());
        return defaultTableModel;
    }
}
