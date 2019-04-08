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

    public static Database database = new Database("database");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fx/fxml/kull.fxml"));
        primaryStage.setTitle("SQL");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void initDatabase(Database database, boolean populate) {
        try {
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
                            + "kurskode text NOT NULL REFERENCES Kurs(kode),\n"
                            + "karakter text NOT NULL,\n"
                            + "year integer NOT NULL,\n"
                            + "student text NOT NULL REFERENCES Student(nr)\n"
                            + ");"
            );

            databaseManager.createTable(database,
                    "CREATE TABLE IF NOT EXISTS Kurs (\n"
                            + "kode text PRIMARY KEY,\n"
                            + "navn text NOT NULL,\n"
                            + "skole text NOT NULL REFERENCES Skole(navn)\n"
                            + ");"
            );

            if (populate) populateDatabase(database, databaseManager);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void populateDatabase(Database database, DatabaseManager databaseManager) {

        databaseManager.insertTable(database, "Skole", "navn", "'UiB'");
        databaseManager.insertTable(database, "Skole", "navn", "'UiO'");
        databaseManager.insertTable(database, "Skole", "navn", "'UiS'");

        databaseManager.insertTable(database, "Kull", "kode, skole", "'2019H', 'UiB'");
        databaseManager.insertTable(database, "Kull", "kode, skole", "'2019V', 'UiO'");
        databaseManager.insertTable(database, "Kull", "kode, skole", "'2020H', 'UiS'");

        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'0', 'Arne Treholt', '2019H'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'1', 'Katja Kai', '2019V'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'4', 'Maja Kai', '2019V'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'5', 'Oto Kai', '2019V'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'6', 'Greger Kai', '2019V'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'2', 'Bente Bent', '2020H'");

        databaseManager.insertTable(database, "Karakter", "kurskode, karakter, year, student", "'INFO233', 'A', 2019, 0");
        databaseManager.insertTable(database, "Karakter", "kurskode, karakter, year, student", "'INFO262', 'B', 2019, 1");
        databaseManager.insertTable(database, "Karakter", "kurskode, karakter, year, student", "'INFO284', 'C', 2019, 2");

        databaseManager.insertTable(database, "Kurs","kode, navn, skole","'INFO233', 'Advanced Programming', 'UiB'");
        databaseManager.insertTable(database, "Kurs","kode, navn, skole","'INFO262', 'Interaction Design', 'UiO'");
        databaseManager.insertTable(database, "Kurs","kode, navn, skole","'INFO284', 'Machine Learning', 'UiS'");
    }

    public static void main(String[] args) {

        initDatabase(database,true);
        launch(args);
    }

}
