package database;

import java.io.File;

public class Database {

    //http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

    private File database;
    private String sql;
    private String url;
    private String databaseName;

    public Database(String name) {
        this.database = new File(name + ".db");
        this.sql = name + ".sql";
        this.url = "jdbc:sqlite:" + database.getPath();
        this.databaseName = name;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public File getDatabase() {
        return database;
    }

    public String getSql() {
        return sql;
    }

    public String getUrl() {
        return url;
    }

}
