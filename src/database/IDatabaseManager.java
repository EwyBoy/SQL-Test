package database;

public interface IDatabaseManager {

    public void createTable(Database db);

    public void insertTable(Database db, String name, double capacity);

    public void deleteTable(Database db, int id);

    public void updateTable(Database db, int id, String name, double capacity);

    public void selectAll(Database db);

}
