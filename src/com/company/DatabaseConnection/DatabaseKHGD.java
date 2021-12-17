package com.company.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.company.Class.*;

public class DatabaseKHGD extends Database {
    private Connection con;

    public List<Object[]> getkhgd() {
        List<Object[]> list = new ArrayList<>();
        String SQL = "SELECT n.*,a.ten,nlph.MPH FROM (SELECT nl.*,hp.tenmonhoc,hp.sotinchi,hp.sotiet FROM nhomlophoc nl,hocphan hp WHERE nl.MMH=hp.MMH) n, nhomlopphonghoc nlph ,(SELECT v.ten, vn.Manhomlop FROM vienchuc v,vienchucnhomlop vn WHERE v.MVC=vn.MVC) a WHERE n.Manhomlop=a.Manhomlop && n.Manhomlop = nlph.Manhomlop && nlph.Manhomlop=a.Manhomlop";
        System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                Object[] objects = {rs.getObject(1), rs.getObject(2), rs.getObject(3)
                        , rs.getObject(4), rs.getObject(5), rs.getObject(6)
                        , rs.getObject(7), rs.getObject(8), rs.getObject(9)
                        , rs.getObject(10), rs.getObject(11)};
                list.add(objects);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public boolean addVCNL(VienChucNhomLop VCNL) {
        boolean check = false;
        try {
            String sql = "INSERT INTO vienchunhomlop VALUES(" + VCNL.getManhomlop() + "," + VCNL.getMVC() + ")";
            int a = updatetoDatabasebySQL(sql);
            if (a == 1) {
                check = true;
                return check;
            }
        } catch (Exception aC) {
            System.err.println(aC.getMessage());
        } finally {
            closedb();
        }
        return check;
    }

    public boolean addNLPH(NhomLopPhongHoc NLPH) {
        boolean check = false;
        try {
            String sql = "INSERT INTO nhomlopphonghoc VALUES(" + NLPH.getMaNhomLop() + "," + NLPH.getMPH() + ")";
            int a = updatetoDatabasebySQL(sql);
            if (a == 1) {
                check = true;
                return check;
            }
        } catch (Exception aC) {
            System.err.println(aC.getMessage());
        } finally {
            closedb();
        }
        return check;
    }

    public ArrayList getListMVCHoTenGV() {
        ArrayList<Vienchuc> arrayList = new ArrayList<Vienchuc>();
        String sql = "select * from vienchuc";
        ResultSet rs = getResultsetbySQL(sql);
        try {
            while (rs.next()) {
                    Vienchuc vc = new Vienchuc(rs.getInt("MVC"), rs.getString("holot"), rs.getString("ten"));
                    arrayList.add(vc);
                }
            return arrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return arrayList;
        }
    }
    public ArrayList getListMVCHoTenGV() {
        ArrayList<Vienchuc> arrayList = new ArrayList<Vienchuc>();
        String sql = "select * from vienchuc";
        ResultSet rs = getResultsetbySQL(sql);
        try {
            while (rs.next()) {
                Vienchuc vc = new Vienchuc(rs.getInt("MVC"), rs.getString("holot"), rs.getString("ten"));
                arrayList.add(vc);
            }
            return arrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return arrayList;
        }
    }
    public static void main(String [] args){
        DatabaseKHGD db = new DatabaseKHGD();
        ArrayList s = db.getListMVCHoTenGV();
        System.out.println(s.get(0));
    }
}
