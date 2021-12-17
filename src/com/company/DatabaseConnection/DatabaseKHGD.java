package com.company.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.company.Class.*;

public class DatabaseKHGD extends Database {
    private Connection con;

    public List<KHGD> getKHGD() {
        List<KHGD> list = new ArrayList<>();
        String SQL = "SELECT n.*,a.ten,nlph.MPH " +
                "FROM nhomlophoc n, nhomlopphonghoc nlph ,(SELECT v.ten, vn.Manhomlop " +
                "FROM vienchuc v,vienchucnhomlop vn " +
                "WHERE v.MVC=vn.MVC) a " +
                "WHERE n.Manhomlop=a.Manhomlop && n.Manhomlop = nlph.Manhomlop";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                KHGD PlanTeching = new KHGD(rs.getString("holot"),
                        rs.getString("ten"), rs.getInt("Manhomlop"),
                        rs.getInt("nhom"), rs.getInt("thuchanh")
                        , rs.getInt("MMH"), rs.getInt("SLdangki"), rs.getInt("SLtkb"),rs.getInt("MPH"));
                list.add(PlanTeching);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public boolean addVCNL(VienChucNhomLop VCNL) {
        try {
            String sql = "INSERT INTO vienchunhomlop VALUES(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, VCNL.getMVC());
            stmt.setInt(2, VCNL.getManhomlop());
            stmt.executeUpdate();
        } catch (Exception aC) {
            System.err.println(aC.getMessage());
        } finally {
            closedb();
        }
        return true;
    }

    public boolean addNLPH(NhomLopPhongHoc NLPH) {
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
        return true;
    }

    public ArrayList getListHoTenGV() {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            String sql = "select * from vienchuc";
            ResultSet rs = getResultsetbySQL(sql);
            while (rs.next()) {
                arrayList.add(rs.getString("holot") + " " + rs.getString("ten"));
            }
        } catch (Exception gC) {
            System.err.println(gC.getMessage());
        } finally {
            closedb();
        }
        return arrayList;
    }

    public static void main(String[] args) {
        DatabaseKHGD DB = new DatabaseKHGD();
        System.out.println(DB.getListHoTenGV());
    }
}
