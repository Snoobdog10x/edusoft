package com.company.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.Class.*;

public class DatabaseKHGD extends Database {
    private Connection con;
    public List<KHGD> getKHGD() {
        List<KHGD> list = new ArrayList<>();
        String SQL = "SELECT n.*,a.ten " +
                "FROM nhomlophoc n, (SELECT v.ten, vn.Manhomlop " +
                "FROM vienchuc v,vienchucnhomlop vn " +
                "WHERE v.MVC=vn.MVC) a " +
                "WHERE n.Manhomlop=a.Manhomlop ";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                KHGD PlanTeching = new KHGD(rs.getString("holot"),
                        rs.getString("ten"), rs.getInt("Manhomlop"),
                        rs.getInt("nhom"), rs.getInt("thuchanh")
                        , rs.getInt("MMH"), rs.getInt("SLdangki"), rs.getInt("SLtkb"));
                list.add(PlanTeching);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public boolean addVCNL(VienChucNhomLop VCNL){
       boolean check = false;
        String SQL = "";
        int rowcount = updatetoDatabasebySQL(SQL);
        if(rowcount == 1) {
            check = true;
        }
        return check;
    }
    public boolean addNLPH(NhomLopPhongHoc NLPH){
            try {
                String sql = "INSERT INTO nhomlopphonghoc VALUES(?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, NLPH.getMaNhomLop());
                stmt.setInt(2, NLPH.getMPH());
                stmt.executeUpdate();
            } catch (Exception aC) {
                System.err.println(aC.getMessage());
            } finally {
                closedb();
            }

    }

}
