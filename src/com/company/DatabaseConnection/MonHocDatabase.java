package com.company.DatabaseConnection;

import com.company.Class.HocPhan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonHocDatabase extends Database {


    public List<HocPhan> getListHP() {
        List<HocPhan> list = new ArrayList<>();
        String SQL = "SELECT * FROM hocphan";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                HocPhan hocphan = new HocPhan(rs.getInt("MMH"), rs.getString("tenmonhoc")
                        , rs.getString("mabomon"), rs.getInt("sotinchi"), rs.getInt("sotiet"),
                        rs.getInt("sotietthuchanh"));
                list.add(hocphan);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public boolean addMH(HocPhan hocPhan) {
        boolean check = false;
        String SQL = "Insert into hocphan(tenmonhoc,mabomon,sotinchi,sotiet,sotietthuchanh) " +
                "values('" + hocPhan.getTenmonhoc() + "','" + hocPhan.getMabomon() + "'," +
                "" + hocPhan.getSotinchi() + "," + hocPhan.getSotiet() + "," + hocPhan.getSotietthuchanh() + ") ";
        //System.out.println(SQL);
        int rowcount = updatetoDatabasebySQL(SQL);
        if (rowcount == 1) {
            check = true;
        }
        return check;
    }

    public boolean updateMH(HocPhan hocPhan) {
        boolean check = false;
        String SQL = "Update hocphan " +
                "set tenmonhoc ='" + hocPhan.getTenmonhoc() + "',mabomon = '" + hocPhan.getMabomon() + "'" +
                ",sotinchi = " + hocPhan.getSotinchi() + ",sotiet = " + hocPhan.getSotiet() + ",sotietthuchanh = " + hocPhan.getSotietthuchanh() + " " +
                "where mmh =" + hocPhan.getMMH();
        //System.out.println(SQL);
        int rowcount = updatetoDatabasebySQL(SQL);
        if (rowcount == 1) {
            check = true;
        }
        return check;
    }
}
