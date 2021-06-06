package com.ren.task03.Hikari;

import com.ren.task03.jdbc.JDBCUtils;

import java.sql.*;

public class HikariTest {
    public static void main(String[] args) {

    }

    public static void select() {

        String sql = "select * from book";

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( sql );
             ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                System.out.println(rs.getString("book_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception s) {
            s.printStackTrace();
        }
    }
}
