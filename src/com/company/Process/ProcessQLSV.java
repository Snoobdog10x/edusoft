package com.company.Process;

import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;
import com.company.DatabaseConnection.Database;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcessQLSV {
    private List<SinhVien> qlsv;
    public ProcessQLSV(){
    }
    private void loadqlsv(){
        Database db=new Database();
        qlsv=db.getListQLSV();
        db.closedb();
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
