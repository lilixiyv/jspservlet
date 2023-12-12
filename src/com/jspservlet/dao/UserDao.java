package com.jspservlet.dao;

import com.jspservlet.util.dbConnectUtil;

import com.jspservlet.entity.Sha256;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean login(String loginId, String loginPassword) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("select pw, identity " +
                    "from customer where customer_id = ?");
            ps.setString(1, loginId);
            rs = ps.executeQuery();
            Sha256 passwordSha256 = new Sha256();
            return passwordSha256.sha256(loginPassword).equals(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}