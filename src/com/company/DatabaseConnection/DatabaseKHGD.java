package com.company.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.company.Class.*;

public class DatabaseKHGD extends Database {


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
            String sql = "INSERT INTO vienchucnhomlop VALUES(" + VCNL.getManhomlop() + "," + VCNL.getMVC() + ")";
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

    public ArrayList<Vienchuc> getListMVCHoTenGV() {
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

    public ArrayList<PhongHoc> getListPH() {
        ArrayList<PhongHoc> arrayList = new ArrayList<PhongHoc>();
        String sql = "select * from phonghoc";
        ResultSet rs = getResultsetbySQL(sql);
        try {
            while (rs.next()) {
                PhongHoc vc = new PhongHoc(rs.getInt("MPH"));
                arrayList.add(vc);
            }
            return arrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return arrayList;
        }
    }

    public boolean AddNL(NhomLopHoc nlh) {
        boolean check = false;
        String sql = "insert into nhomlophoc(Nhom,thuchanh,MMH,SLdangki,SLtkb)" +
                " values(" + nlh.getNhom() + "," + nlh.getThuchanh() + "," + nlh.getMMH()
                + "," + nlh.getSLdangki() + "," + nlh.getSLtkb() + ")";
        try {
            int rs = updatetoDatabasebySQL(sql);
            if (rs == 1) {
                check = true;
                return check;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    public boolean AddNLPH(NhomLopPhongHoc nlph) {
        boolean check = false;
        String sql = "insert into nhomlopphonghoc(manhomlop,MPH)" +
                " values(" + nlph.getMaNhomLop() + "," + nlph.getMPH() + ")";
        try {
            int rs = updatetoDatabasebySQL(sql);
            if (rs == 1) {
                check = true;
                return check;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    public boolean AddVCNL(VienChucNhomLop vcnl) {
        boolean check = false;
        String sql = "insert into vienchucnhomlop( Manhomlop,MVC)" +
                " values(" + vcnl.getManhomlop() + "," + vcnl.getMVC() + ")";
        try {
            int rs = updatetoDatabasebySQL(sql);
            if (rs == 1) {
                check = true;
                return check;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    public ArrayList<HocPhan> getMH() {
        ArrayList<HocPhan> list = new ArrayList<>();
        String SQL = "SELECT * FROM hocphan";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                HocPhan hocphan = new HocPhan(rs.getInt("MMH"), rs.getString("tenmonhoc"));
                list.add(hocphan);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public int getlastNLH() {
        ArrayList<NhomLopHoc> list = new ArrayList<>();
        String SQL = "SELECT * FROM nhomlophoc ORDER BY manhomlop DESC LIMIT 1";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        int manhomlop = 0;
        try {
            while(rs.next()){
                manhomlop = rs.getInt("manhomlop");
            }
            return manhomlop;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return manhomlop;
        }
    }

}
