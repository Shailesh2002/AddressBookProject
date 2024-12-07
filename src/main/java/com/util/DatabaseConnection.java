package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    private static Connection con;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book_application", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
