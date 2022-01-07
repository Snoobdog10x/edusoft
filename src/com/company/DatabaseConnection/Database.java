package com.company.DatabaseConnection;

import com.company.Class.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {
    private String URL = "jdbc:mysql://snooby.ddns.net:3306/cnpm";
    private String User = "root";
    private String pass = "thanhanh";
    private Connection conn;

    public Database() {
        connectdb();
    }

    public void connectdb() {
        try {
            conn = DriverManager.getConnection(URL, User, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getResultsetbySQL(String SQL) {
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int updatetoDatabasebySQL(String SQL) {
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            int rowcount = stmt.executeUpdate(SQL);
            return rowcount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public List<lichsudangky> getListLSDK() {
        List<lichsudangky> list = new ArrayList<lichsudangky>();
        String getLSDK = "SELECT * " +
                "FROM `lichsudangky` ls, `sinhvien` sv, `nhomlophoc` nlh, `hocphan` hp " +
                "WHERE ls.MSSV=sv.MSSV AND ls.Manhomlop=nlh.Manhomlop AND ls.MMH=hp.MMH";
        ResultSet lsdk = getResultsetbySQL(getLSDK);
        try {
            while (lsdk.next()) {
                SinhVien sv = new SinhVien(lsdk.getInt(5), lsdk.getString(6), lsdk.getString(7)
                        , lsdk.getString(8), lsdk.getDate(9), lsdk.getString(10)
                        , lsdk.getString(11), lsdk.getString(12), lsdk.getString(13));
                NhomLopHoc nhomLopHoc = new NhomLopHoc(lsdk.getInt(14), lsdk.getInt(15), lsdk.getInt(16)
                        , lsdk.getInt(17), lsdk.getInt(18), lsdk.getInt(19));
                HocPhan hocPhan=new HocPhan(lsdk.getInt(20),lsdk.getString(21),lsdk.getString(22)
                        ,lsdk.getInt(23),lsdk.getInt(24),lsdk.getInt(25));
                lichsudangky ls = new lichsudangky(sv,nhomLopHoc,hocPhan,lsdk.getDate(4));
                list.add(ls);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public List<SinhVien> getListQLSV() {
        List<SinhVien> list = new ArrayList<SinhVien>();
        String SQL = "SELECT * " +
                "FROM  sinhvien ";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            while (rs.next()) {
                SinhVien sv = new SinhVien(rs.getInt("MSSV"), rs.getString("malop"), rs.getString("holot"), rs.getString("ten"), rs.getDate("ngaysinh"),
                        rs.getString("sdt"), rs.getString("manganh"), rs.getString("noisinh"), rs.getString("email"));
                list.add(sv);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public boolean addsv(SinhVien sv) {
        boolean check = false;
        List<SinhVien> list = new ArrayList<SinhVien>();
        String SQL = "Insert into sinhvien(malop,holot,ten,ngaysinh,sdt,manganh,noisinh,email) " +
                "values('" + sv.getMalop() + "','" + sv.getHolot() + "'," + sv.getTen() + "," + sv.getNgaysinh() +
                "" + sv.getSdt() + "," + sv.getManganh() + "," + sv.getNoisinh() + "," + sv.getEmail() + " ) ";
        //System.out.println(SQL);
        int row = updatetoDatabasebySQL(SQL);
        if (row == 1) {
            check = true;
        }
        return check;
    }

    public void closedb() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Object[]> getkhgd() {
        List<Object[]> list = new ArrayList<>();
        String SQL = "SELECT n.*,a.ten,nlph.MPH FROM (SELECT nl.*,hp.tenmonhoc,hp.sotinchi,hp.sotiet FROM nhomlophoc nl,hocphan hp WHERE nl.MMH=hp.MMH) n, nhomlopphonghoc nlph ,(SELECT v.ten, vn.Manhomlop FROM vienchuc v,vienchucnhomlop vn WHERE v.MVC=vn.MVC) a WHERE n.Manhomlop=a.Manhomlop && n.Manhomlop = nlph.Manhomlop && nlph.Manhomlop=a.Manhomlop";
        //System.out.println(SQL);
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
    public int DeleteLSDK(int MSSV, int MNL, int MMH) throws SQLException {
        String sql = "Delete FROM lichsudangky WHERE MSSV=? AND Manhomlop=? AND MMH=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, MSSV);
        statement.setInt(2, MNL);
        statement.setInt(3, MMH);
        return statement.executeUpdate();
    }
    public int DKMH(int MSSV, int MNL, int MMH) throws SQLException {
        String sql = "Insert into lichsudangky (MSSV,Manhomlop,MMH,ngaydangki) " +
                "Values(?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, MSSV);
        statement.setInt(2, MNL);
        statement.setInt(3, MMH);
        statement.setDate(4, new Date(Calendar.getInstance().getTime().getTime()));
        return statement.executeUpdate();
    }
}
