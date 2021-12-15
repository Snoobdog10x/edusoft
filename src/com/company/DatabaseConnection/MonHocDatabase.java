package com.company.DatabaseConnection;

import com.company.Class.HocPhan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonHocDatabase extends Database{


    public List<HocPhan> getListHP() {
        List<HocPhan> list = new ArrayList<>();
        String SQL = "SELECT * FROM hocphan ";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                Object ls = rs.getObject(i++);
                list.add((HocPhan) ls);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

}
