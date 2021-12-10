package com.company.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String URL="jdbc:mysql://localhost:3306/cnpm";
    private String User="root";
    private String pass="";
    private Connection conn;
    public Database(){
        try{
            conn= DriverManager.getConnection(URL,User,pass);
            System.out.println(conn.getCatalog());
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
