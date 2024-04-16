import java.sql.*;

public class ConnectDatabase {
    public static void main(String[] args) throws ClassNotFoundException {
        final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/training";
        final String MYSQL_USERNAME = "root";
        final String MYSQL_PASSWORD = "root";

        final String INSERT_QUERY_STATIC = "SELECT * FROM training.javaclass;";
        final String INSERT_QUERY_DYNAMIC = "INSERT INTO `training`.`javaclass` (`id`, `name`, `age`) VALUES (?, ?, ?);";
        final String DELETE_QUERY_STATIC = "DELETE FROM javaclass where age = 20;";
        final String UPDATE_QUERY_DYNAMIC = "UPDATE `training`.`javaclass` SET `age` = '20' WHERE (`id` = ?);";
        final String INSERT_PYTHONCLASS_DYNAMIC = "INSERT INTO `training`.`pythonclass` (`name`, `age`) VALUES (?, ?);";

        // step-1: register driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // step-2: create connection
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);) {
            if (conn != null)
                System.out.println("Connected");

            /* step-3: create a statement from connection
             *  types: Statement: to run a static query
             *         PreparedStatement: to run a prepared(query with dynamic inputs) query
             *         Callable statement: To run stored procedures
             * */
            Statement stmt = conn.createStatement();

            // step-4: prepare query
            /* step-5: execute the prepared query / returns boolean (true - has ResultSet)
             * execute(): DML, DQL or any SQL queries / returns ResultSet
             * executeUpdate(): DDL(CREATE, ALTER), DML(INSERT, UPDATE, DELETE) / returns integer(no. of rows affected)
             * executeQuery(): DQL(SELECT) / returns ResultSet
             * */
            ResultSet resultSet = stmt.executeQuery(INSERT_QUERY_STATIC);

            // step-6: process the result
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id")); // parameter can be column-name(preferred) or column-index
                System.out.print(resultSet.getString("name"));
                System.out.print(resultSet.getString(3));
                System.out.println();
            }

            PreparedStatement prepStmtInsert = conn.prepareStatement(INSERT_QUERY_DYNAMIC);
            prepStmtInsert.setInt(1, 11);
            prepStmtInsert.setString(2, "Java");
            prepStmtInsert.setInt(3, 22);
            int insertedRows = prepStmtInsert.executeUpdate();
            if (insertedRows != 0) {
                System.out.println("Success, Rows Inserted:" + insertedRows);
            }

            PreparedStatement prepStmtUpdate = conn.prepareStatement(UPDATE_QUERY_DYNAMIC);
            prepStmtUpdate.setInt(1, 11);
            int updatedRows = prepStmtUpdate.executeUpdate();
            if (updatedRows != 0) {
                System.out.println("Success, Rows Updated:" + updatedRows);
            }


            PreparedStatement prepStmtDelete = conn.prepareStatement(DELETE_QUERY_STATIC);
            int deletedRows = prepStmtDelete.executeUpdate();
            if (deletedRows != 0) {
                System.out.println("Success, Rows Deleted:" + deletedRows);
            }

            PreparedStatement prepStmtInsertPythonClass = conn.prepareStatement(INSERT_PYTHONCLASS_DYNAMIC);
            prepStmtInsertPythonClass.setString(1, "Test");
            prepStmtInsertPythonClass.setInt(2, 22);
            boolean holdsResultSet = prepStmtInsertPythonClass.execute(getQueryFromPreparedStatement(prepStmtInsertPythonClass));
            if (holdsResultSet) {
                System.out.println("Success");
            }

            // step-7: close the connection if not created like try(Connection conn = ...)
            conn.close();
        } catch (SQLException e) {
            System.out.println("EX::" + e);
        }

    }

    private static String getQueryFromPreparedStatement(PreparedStatement preparedStatement) {
        String preparedStatementAsString = preparedStatement.toString();
        System.out.println(preparedStatementAsString);
        return preparedStatementAsString.substring(preparedStatementAsString.indexOf(": ") + 2);
    }
}