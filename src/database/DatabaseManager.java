package database;

import java.sql.*;

// Used this as a reference when creating this class
// http://www.sqlitetutorial.net/sqlite-java/

public class DatabaseManager implements IDatabaseManager {

    private Connection connect(Database db) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db.getUrl());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void createTable(Database db) {

        String sql
            = "CREATE TABLE IF NOT EXISTS warehouses (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	name text NOT NULL,\n"
            + "	capacity real\n"
            + ");"
        ;

        try (
            Connection connection = DriverManager.getConnection(db.getUrl());
            Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTable(Database db, String name, double capacity) {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

        try(
            Connection connection = this.connect(db);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, capacity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTable(Database db, int id) {
        String sql = "DELETE FROM warehouses WHERE id = ?";

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
        String sql = "UPDATE warehouses SET name = ? , " + "capacity = ? " + "WHERE id = ?";

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
        String sql = "SELECT id, name, capacity FROM warehouses";

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
