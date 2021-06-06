package com.ren.task03.jdbc;

import java.sql.*;

public class JDBCTest {

    public static void jdbcSelect() {
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "select * from book";
            set = statement.executeQuery(sql);

            while (set.next()) {
                System.out.println(set.getString("book_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception s) {
            s.printStackTrace();
        } finally {
            JDBCUtils.release(set, statement, connection);
        }

    }

    public static void jdbcInsert() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into book values(?, ?)";
            stmt = conn.prepareStatement(sql);
            String bookName = "crazy java";
            String author = "M";
            stmt.setString(1, bookName);
            stmt.setString(2, author);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(stmt, conn);
        }
    }

    public static void jdbcUpdate() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update book set ahthor=? where book_name=?";
            stmt = conn.prepareStatement(sql);
            String bookName = "crazy java";
            String author = "W";
            stmt.setString(1, bookName);
            stmt.setString(2, author);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(stmt, conn);
        }
    }

    public static void jdbcDelete() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from book where book_name=?";
            stmt = conn.prepareStatement(sql);
            String bookName  = "crazy java";
            stmt.setString(1, bookName);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(stmt, conn);
        }
    }

    public static void main(String[] args) {
        jdbcInsert();
        jdbcSelect();
        jdbcUpdate();
        jdbcDelete();
    }
}
