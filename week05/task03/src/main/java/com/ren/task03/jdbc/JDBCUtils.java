package com.ren.task03.jdbc;

import java.sql.*;

public class JDBCUtils {
    private static final String jdbcDriver = "";
    private static final  String jdbcUrl = "";
    private static final String jdbcUser = "";
    private static final String jdbcPassword = "";

    private static void loadDriver() throws ClassNotFoundException{
        Class.forName(jdbcDriver);
    }

    public static Connection getConnection() throws Exception{
        loadDriver();
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        release(stmt, conn);
    }

    public static void release(Statement stmt, Connection conn) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
