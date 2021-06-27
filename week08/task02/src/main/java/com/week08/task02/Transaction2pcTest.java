package com.week08.task02;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction2pcTest {

    public static void main(String[] args) throws SQLException, IOException {
        DataSource dataSource = getShadingJdbcDataSource();

        cleanDataSource(dataSource);

        TransactionTypeHolder.set(TransactionType.XA);

        Connection conn = dataSource.getConnection();
        String sql = "insert into t_mall_order (id, order_no, user_id) values(?, ?, ?)";

        System.out.println("insert data first time");

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (int i = 0; i < 21; i++) {
                statement.setInt(1, i);
                statement.setInt(2, i - 2);
                statement.setInt(3, i - 3);
                statement.executeUpdate();
            }

            conn.commit();
        }

        System.out.println("insert successful");

        System.out.println("insert data second time");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            for (int i = 0; i < 33; i++) {
                statement.setInt(1, i);
                statement.setInt(2, i - 3);
                statement.setInt(3, i - 5);
                statement.executeUpdate();
            }

            conn.commit();
        } catch (SQLException s) {
            System.out.println("second insert fail");
            conn.rollback();
        } finally {
            conn.close();
        }

        System.out.println("second insert finished");
    }

    private static DataSource getShadingJdbcDataSource() throws SQLException, IOException {
        String fileName = "/Users/mawanqiu/code/geektraining/week08/task02/src/main/resources/shading-table.yml";
        File file = new File(fileName);
        return YamlShardingSphereDataSourceFactory.createDataSource(file);
    }

    private static void cleanDataSource(DataSource dataSource) throws SQLException {
        System.out.println("clean all data");
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement()) {

            statement.execute("delete from t_mall_order");
            conn.commit();

        } catch (SQLException s) {
            s.printStackTrace();
        }

        System.out.println("clean data finished");
    }


}
