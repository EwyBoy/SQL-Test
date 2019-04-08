package newmain;

import database.Database;
import database.DatabaseManager;
import main.Main;

public class NewMain {

    public static void main(String[] args) {

        Database database = new Database("DeloppgaveD");
        DatabaseManager databaseManager = new DatabaseManager();
        Main.initDatabase(database, false);

        databaseManager.insertTable(database, "Skole", "navn", "'UiB'");
        databaseManager.insertTable(database, "Kull", "kode, skole", "'2019V', 'UiB'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'1', 'Kari', '2019V'");
        databaseManager.insertTable(database, "Student", "nr, navn, kull", "'2', 'Per', '2019V'");
        databaseManager.insertTable(database, "Karakter", "kurskode, karakter, year, student", "'INFO233','B','2019','1'");
        databaseManager.insertTable(database, "Karakter", "kurskode, karakter, year, student", "'INFO233','A','2019','2'");
        databaseManager.insertTable(database, "Kurs", "kode, navn, skole", "'INFO233', 'Avansert Programmering', 'UiB'");
    }

}
