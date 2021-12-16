package com.company.DatabaseConnection;

import com.company.Class.HocPhan;
import com.company.Class.Vienchuc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VienchucDatabse extends Database {
    public List<Vienchuc> getListVC() {
        List<Vienchuc> listVC = new ArrayList<Vienchuc>();
        String SQL = "SELECT * " +
                "FROM vienchuc " ;
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                Vienchuc vienchuc = new Vienchuc(rs.getInt("MVC"), rs.getString("loaivc"), rs.getString("ten")
                        , rs.getString("holot"), rs.getString("sdt"), rs.getDate("ngaysinh"),
                        rs.getString("noisinh"),rs.getString("email"));
                listVC.add(vienchuc);
            }
            return listVC;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return listVC;
        }
    }
    public boolean addVC (Vienchuc vienchuc) {
        boolean check = false;

        String SQL = "Insert into vienchuc(loaivc,ten,holot,sdt,ngaysinh,noisinh,email) " +
                "values('" + vienchuc.getLoaivc() + "','" + vienchuc.getTen() + "'," +
                    "'" + vienchuc.getHolot() + "','" + vienchuc.getSdt() +"','" + vienchuc.getNgaysinh() +  "','" + vienchuc.getNoisinh()+ "','" + vienchuc.getEmail() + "') ";
        //System.out.println(SQL);
        int rowcount = updatetoDatabasebySQL(SQL);
        if (rowcount == 1) {
            check = true;
        }
        return check;
    }

    public boolean updateVC(Vienchuc vienchuc)  {
        boolean check = false;

        String SQL = "Update vienchuc " +
                "set loaivc ='" + vienchuc.getLoaivc() + "',ten = '" + vienchuc.getTen() + "'" +
                ",holot =' " + vienchuc.getHolot() + "',sdt = '" + vienchuc.getSdt() + "',ngaysinh =' " + vienchuc.getNgaysinh() +
                "',noisinh = '" + vienchuc.getNoisinh() +"',email = '" + vienchuc.getEmail() + "' " +
                "where mvc =" + vienchuc.getMVC();
        //System.out.println(SQL);
        int rowcount = updatetoDatabasebySQL(SQL);
        if (rowcount == 1) {
            check = true;
        }
        return check;
    }
}
