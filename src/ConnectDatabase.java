import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class ConnectDatabase {
    public static void main(String[] args) throws ClassNotFoundException {
        final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/training";
        final String MYSQL_USERNAME = "root";
        final String MYSQL_PASSWORD = "root";

        final String INSERT_QUERY_STATIC = "INSERT INTO `training`.`javaclass` (`id`, `name`, `age`) VALUES ('4', 'Arun', '20');";
        final String INSERT_QUERY_DYNAMIC = "INSERT INTO `training`.`javaclass` (`id`, `name`, `age`) VALUES (?, ?, ?);";

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);) {
            if (conn != null)
                System.out.println("Connected");
            Statement stmt = conn.createStatement();
             stmt.executeUpdate(INSERT_QUERY_STATIC);

            PreparedStatement prepStmt = conn.prepareStatement(INSERT_QUERY_DYNAMIC);
            prepStmt.setInt(1,5);
            prepStmt.setString(2, "Test");
            prepStmt.setInt(3, 22);
            prepStmt.executeUpdate();
//            stmt.executeQuery("");
//            stmt.execute("");
        } catch (SQLException e) {
            System.out.println("EX::" + e);
        }

    }
}
