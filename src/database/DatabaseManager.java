package database;

import java.sql.*;

// Used this as a reference when creating this class
// http://www.sqlitetutorial.net/sqlite-java/

public class DatabaseManager {

    public static Connection connect(Database db) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db.getUrl());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void createTable(Database db, String data) {
        try (
            Connection connection = DriverManager.getConnection(db.getUrl());
            Statement statement = connection.createStatement()
        ) {
            statement.execute(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTable(Database database, String tableName, String data, String values) {
        try(
            Connection connection = connect(database);
            Statement statement = connection.createStatement()
        ) {
            statement.execute("INSERT INTO " + tableName + "(" + data + ") VALUES (" + values + ");");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTable(Database db, int id) {
        String sql = "DELETE FROM " + db.getDatabaseName() + " WHERE id = ?";

        try (
            Connection connection = this.connect(db);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTable(Database db, int id, String name, double capacity) {
        String sql = "UPDATE " + db.getDatabaseName() + " SET name = ? , " + "capacity = ? " + "WHERE id = ?";

        try(
            Connection connection = this.connect(db);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, capacity);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void selectAll(Database db) {
        String sql = "SELECT id, name, capacity FROM " + db.getDatabaseName();

        try (
             Connection connection = this.connect(db);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                System.out.println(
                   resultSet.getInt("id") +  "\t" +
                   resultSet.getString("name") + "\t" +
                   resultSet.getDouble("capacity")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
