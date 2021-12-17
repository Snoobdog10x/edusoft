package com.company;

import com.company.DatabaseConnection.Database;
import com.company.UIUX.Giaodienchung;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        new Giaodienchung();
        Database db=new Database();
    }
}
