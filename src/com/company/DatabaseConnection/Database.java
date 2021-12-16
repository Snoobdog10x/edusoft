package com.company.DatabaseConnection;

import com.company.Class.HocPhan;
import com.company.Class.lichsudangky;

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

    public List<lichsudangky> getListLSDK() {
        List<lichsudangky> list = new ArrayList<lichsudangky>();
        String SQL = "SELECT s.MSSV,s.ten,n.manhomlop,n.Nhom,n.thuchanh,h.MMH,h.tenmonhoc,l.ngaydangki " +
                "FROM lichsudangky l, sinhvien s, nhomlophoc n,hocphan h " +
                "WHERE l.MSSV=s.MSSV and l.Manhomlop=n.Manhomlop and l.MMH=h.MMH";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            while (rs.next()) {
                lichsudangky ls=new lichsudangky(rs.getInt(1),rs.getString(2),rs.getInt(3)
                        ,rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8));
                list.add(ls);
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
