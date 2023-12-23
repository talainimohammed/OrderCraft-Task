package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnnect {

    private static DatabaseConnnect instance = null;
    private Connection conn;
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_ordercraft";
    private final String USER = "root";
    private final String PASS = "";

    private DatabaseConnnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static DatabaseConnnect getInstance() {
    	if (instance == null) {
            instance = new DatabaseConnnect();
        }
        return instance;
		
    }
}
