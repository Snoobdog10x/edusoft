package com.company.DatabaseConnection;

import com.company.Class.lichsudangky;

import java.sql.*;

public class Database {
    private String URL="jdbc:mysql://localhost:3306/cnpm";
    private String User="root";
    private String pass="";
    private Connection conn;
    public Database(){
        connectdb();
    }
    private void connectdb(){
        try{
            conn= DriverManager.getConnection(URL,User,pass);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private ResultSet getResultsetbySQL(String SQL){
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
    public lichsudangky getList(){
        String SQL="SELECT * FROM lichsudangky l, sinhvien s, nhomlophoc n,hocphan h  WHERE l.MSSV=s.MSSV and l.Manhomlop=n.Manhomlop and l.MMH=h.MMH";
        ResultSet rs=getResultsetbySQL(SQL);
        return null;
    }
    public void closedb(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
