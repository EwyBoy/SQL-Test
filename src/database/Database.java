package database;

import java.io.File;

public class Database {

    //http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

    private File database;
    private String sql;
    private String url;

    public Database(String name) {
        this.database = new File(name + ".db");
        this.sql = name + ".sql";
        this.url = "jdbc:sqlite:" + database.getPath();
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
