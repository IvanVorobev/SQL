package ru.netology.web.data;

import lombok.Value;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    public static Connection getConnection() throws SQLException {
        val connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.99.100:3306/app", "app", "pass");
        return connection;
    }

    public static void cleanAuthCodesTable() throws SQLException {
        String deleteAuthCodes = "DELETE FROM auth_codes; ";
        try (val conn = SQLUtils.getConnection();
             val deleteAuthCodesStmt = conn.createStatement();
        ) {
            deleteAuthCodesStmt.executeUpdate(deleteAuthCodes);
        }
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String getVerificationCode() throws SQLException {
        val authCode = "SELECT code FROM auth_codes order by created desc limit 1;";
        try (val conn = getConnection();
                val codeStmt = conn.prepareStatement(authCode)) {
            try (val rs = codeStmt.executeQuery(authCode)) {
                String code = "";
                while (rs.next()) {
                    code = rs.getString("code");
                }
                return code;
            }
        }
    }
}