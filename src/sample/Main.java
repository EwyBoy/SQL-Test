package sample;

import database.Database;
import database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SQL");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private static void initDatabase() {
        try {
            Database database = new Database("database");
            Connection connection = DriverManager.getConnection(database.getUrl());
            System.out.println("Connection established (Read with british accent)");
            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.createTable(database);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        initDatabase();
        launch(args);
    }


}
