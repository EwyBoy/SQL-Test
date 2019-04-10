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
import java.util.Scanner;

public class Main extends Application {

    /*
     *  Usually sits next to David Kvasnes Olsen and Hallvard Moan Kristiansen,
     *  our code could have similarities from shearing knowledge.
     *
     *  Here is a link to MY GitHub in case someone is looking at my src and not referencing me in their sources:
     *  https://github.com/EwyBoy/SQL-Test
     *
     *  God påske!
     *
     */

    public static Database database = new Database("database");
    private static String resource;

    @Override
    public void start(Stage primaryStage) throws Exception {

        if (resource != null) {
            Parent root = FXMLLoader.load(getClass().getResource("../fx/fxml/" + resource));
            primaryStage.setTitle("SQL");
            primaryStage.setScene(new Scene(root, 720, 540));
            primaryStage.show();
        }

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
        Scanner scanner = new Scanner(System.in);
        runApplication(scanner);
        initDatabase(database,true);
        launch(args);
    }

    private static void runApplication(Scanner scanner) {
        System.out.println("Type '1' to launch the test data table");
        System.out.println("Type '2' to launch Task E window");
        System.out.println("Type '3' to launch Task G window");
        System.out.println("Info: You may have to delete the database for it work properly after each launch..");

        String option = scanner.nextLine().toLowerCase();

        if (option.contentEquals("1")) {

            resource = "sample.fxml";

        } else if (option.contentEquals("2")) {

            resource = "student.fxml";

        } else if (option.contentEquals("3")) {

            resource = "kull.fxml";

        } else {

            resource = "sample.fxml";

        }
    }

}
