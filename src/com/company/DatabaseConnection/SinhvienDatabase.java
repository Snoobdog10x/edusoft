package com.company.DatabaseConnection;

import com.company.Class.SinhVien;
import com.company.Class.Vienchuc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhvienDatabase extends Database{

    public List<SinhVien> getListQLSV() {
        List<SinhVien> list = new ArrayList<SinhVien>();
        String SQL = "SELECT * " +
                "FROM  sinhvien ";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            while (rs.next()) {
                SinhVien sv = new SinhVien(rs.getInt("MSSV"),rs.getString("malop"),rs.getString("holot"), rs.getString("ten"),rs.getDate("ngaysinh"),
                        rs.getString("sdt"),rs.getString("manganh"),rs.getString("noisinh"),rs.getString("email"));
                list.add(sv);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }
    public boolean addsv(SinhVien sv){
        boolean check = false;
        String SQL = "Insert into sinhvien(malop,holot,ten,ngaysinh,sdt,manganh,noisinh,email) " +
                "values('" + sv.getMalop() + "','" + sv.getHolot() + "','" + sv.getTen() + "','" + sv.getNgaysinh() +
                "','" + sv.getSdt()+ "','" + sv.getManganh() + "','"  + sv.getNoisinh() + "','" + sv.getEmail() +" ') ";
        //System.out.println(SQL);
        int rowcount = updatetoDatabasebySQL(SQL);
        if (rowcount == 1) {
            check = true;
        }
        return check;
    }
    public boolean updateSV(SinhVien sv)  {
        boolean check = false;

        String SQL = "Update sinhvien " +
                "set malop ='" + sv.getMalop() + "',holot = '" + sv.getHolot() +
                "',ten ='" + sv.getTen() + "',ngaysinh ='" + sv.getNgaysinh() + "',sdt ='" + sv.getSdt() +
                "',manganh = '" + sv.getManganh() +"',noisinh = '" + sv.getNoisinh() + "',email ='" + sv.getEmail() + "' " +
                "where mssv =" + sv.getMSSV();
        //System.out.println(SQL);
        int rowcount = updatetoDatabasebySQL(SQL);
        if (rowcount == 1) {
            check = true;
        }
        return check;
    }
}
