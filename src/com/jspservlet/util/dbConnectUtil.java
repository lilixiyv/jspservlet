package com.jspservlet.util;

import java.sql.*;

public class dbConnectUtil {
    // initial
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // fill className
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/dbsc_lab3?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String username = "dbsc_lab3";
            String password = "dbsc";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void disconnect(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}