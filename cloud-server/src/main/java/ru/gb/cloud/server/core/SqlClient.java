package ru.gb.cloud.server.core;

import java.sql.*;

public class SqlClient {

    private static Connection connection;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/cloudstorage" +
                    "user=cloud-user&password=1234");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throw new RuntimeException(throwable);
        }
    }

    synchronized static String getNickname(String login, String password) {
        try {
            ResultSet rs = statement.executeQuery(
                    String.format("select fld_nickname from tbl_users where fld_login = '%s' and fld_password = '%s'",
                            login, password));
            if (rs.next()) {
                return rs.getString("fld_nickname");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
