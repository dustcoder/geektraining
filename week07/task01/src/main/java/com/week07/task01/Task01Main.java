package com.week07.task01;

import java.sql.*;
import java.util.Random;

public class Task01Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // first method insert one record each time
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            insertSingleRecord();
        }
        System.out.println("insert one record each time cost : " + (System.currentTimeMillis() - start1));

        truncateTable();

        // second method using prepare statement batch operation and commit 1000 records each time
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            insertBatchedRecords();
        }
        System.out.println("batch insertation cost " + (System.currentTimeMillis() - start1));
    }

    private static void truncateTable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackathon?characterEncoding=UTF-8", "root", "123");
             Statement statement = conn.createStatement()) {

            String sql = "delete from truncate table t_mall_order";

            statement.execute(sql);
        }
    }

    private static void insertSingleRecord() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackathon?characterEncoding=UTF-8", "root", "123");
             Statement statement = connection.createStatement();) {

            final StringBuilder sql = new StringBuilder("insert into t_mall_order (order_no,user_id,shipping_id,payment) values(");
            sql.append(String.join(",", getRandomInt(), getRandomInt(), getRandomInt(), getRandomDouble()));
            sql.append(")");
            statement.execute(sql.toString());
        }
    }

    private static void insertBatchedRecords() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackathon?characterEncoding=UTF-8", "root", "123");
             PreparedStatement statement = connection.prepareStatement("insert into t_mall_order (order_no,user_id,shipping_id,payment) values(?, ?, ?, ?)")) {

            connection.setAutoCommit(false);

            for (int i = 0; i < 1000; i++) {
                statement.setString(1, getRandomInt());
                statement.setString(2, getRandomInt());
                statement.setString(3, getRandomInt());
                statement.setString(4, getRandomDouble());
                statement.addBatch();
            }

            statement.execute();
            connection.commit();
        }
    }

    private static String  getRandomInt() {
        final Random random = new Random();
        return String.valueOf(random.nextInt());
    }

    private static String getRandomDouble() {
        final Random random = new Random();
        return String.valueOf(random.nextDouble());
    }


}
