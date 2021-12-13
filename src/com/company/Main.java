package com.company;

import com.company.DatabaseConnection.Database;
import com.company.UIUX.Giaodienchung;

public class Main {

    public static void main(String[] args) {
        System.out.println("hihi");
       Database db=new Database();
       db.getList();
       db.closedb();
        //new Giaodienchung();
    }
}
