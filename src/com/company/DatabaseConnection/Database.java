package com.company.DatabaseConnection;

import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;
import com.company.Class.vienchuc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String URL="jdbc:mysql://snooby.ddns.net:3306/cnpm";
    private String User="root";
    private String pass="thanhanh";
    private Connection conn;

    public Database(){
        connectdb();
    }
    public void connectdb(){
        try{
            conn= DriverManager.getConnection(URL,User,pass);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ResultSet getResultsetbySQL(String SQL){
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
    public int updatetoDatabasebySQL(String SQL){
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
    public List<vienchuc> getListVC() {
        List<vienchuc> listVC = new ArrayList<vienchuc>();
        String SQL = "SELECT * " +
                "FROM vienchuc " ;
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                Object vc = rs.getObject(i++);
                System.out.println(vc);
                System.out.println("vienchucla");
                listVC.add((vienchuc) vc);
            }
            return listVC;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return listVC;
        }
    }
    public List<lichsudangky> getListLSDK() {
        List<lichsudangky> list = new ArrayList<lichsudangky>();
        String SQL = "SELECT s.MSSV,s.ten,n.manhomlop,n.Nhom,n.thuchanh,h.MMH,h.tenmonhoc,l.ngaydangki " +
                "FROM lichsudangky l, sinhvien s, nhomlophoc n,hocphan h " +
                "WHERE l.MSSV=s.MSSV and l.Manhomlop=n.Manhomlop and l.MMH=h.MMH";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                lichsudangky ls=new lichsudangky(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8));
                list.add((lichsudangky) ls);
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
                SinhVien sv = new SinhVien(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                list.add(sv);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }
    public void closedb() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
