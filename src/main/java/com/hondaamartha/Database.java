package com.hondaamartha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/hundak";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Keknya belum nyala itu xampp", e);
        }
    }


    public static void executeQuery(Connection conn, String query) {
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            boolean isResultSet = pstmt.execute();

            // select
            if (isResultSet) {
                ResultSet rs = pstmt.getResultSet();
                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } else {
                // insert update delete
                int updateCount = pstmt.getUpdateCount();
                System.out.println("Query executed successfully. Rows affected: " + updateCount);
            }
        } catch (SQLException e) {
            System.out.println("Execution Error: " + e.getMessage());
        }
    }
}
