package main;

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


            databaseManager.createTable(database,
                    "CREATE TABLE IF NOT EXISTS Skole (\n"
                            + "navn text PRIMARY KEY\n"
                            + ");"
            );

            databaseManager.createTable(database,
                    "CREATE TABLE IF NOT EXISTS Kull (\n"
                            + "kode text PRIMARY KEY,\n"
                            + "skole text NOT NULL,\n"
                            + "FOREIGN KEY(skole) REFERENCES Skole(navn)\n"
                            + ");"
            );

            databaseManager.createTable(database,
                    "CREATE TABLE IF NOT EXISTS Student (\n"
                            + "nr text PRIMARY KEY,\n"
                            + "navn text NOT NULL,\n"
                            + "kull text NOT NULL,\n"
                            + "FOREIGN KEY (kull) REFERENCES Kull(kode)\n"
                            + ");"
            );

            databaseManager.createTable(database,
                    "CREATE TABLE IF NOT EXISTS Karakter (\n"
                            + "id integer PRIMARY KEY,\n"
                            + "karakter text NOT NULL,\n"
                            + "ar integer NOT NULL,\n"
                            + "student text NOT NULL,\n"
                            + "FOREIGN KEY (student) REFERENCES Student(nr)\n"
                            + ");"
            );

            databaseManager.createTable(database,
                    "CREATE TABLE IF NOT EXISTS Kurs (\n"
                            + "kode text PRIMARY KEY,\n"
                            + "navn text NOT NULL,\n"
                            + "skole text NOT NULL,\n"
                            + "FOREIGN KEY (skole) REFERENCES Skole(navn),\n"
                            + "karakter integer NOT NULL,\n"
                            + "FOREIGN KEY (karakter) REFERENCES Karakter(id)\n"
                            + ");"
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        initDatabase();
        launch(args);
    }

}
