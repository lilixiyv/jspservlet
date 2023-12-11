package com.jspservlet.util;

import java.sql.*;

public class dbConnectUtil {
    // initial
    static{
        try {
            Class.forName("fill className"); // fill className
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect(){
        Connection conn = null;
        try {
            String url = ""; // need to fill url
            String username = "root";
            String password = "123456";
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