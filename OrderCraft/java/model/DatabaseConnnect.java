package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnnect {

    private static DatabaseConnnect instance = null;
    private Connection conn = null;

    private DatabaseConnnect() {}

    private void init() throws SQLException {
        final String DB_URL = "jdbc:mysql://localhost:3306/db_ordercraft";
        final String USER = "root";
        final String PASS = "";
        
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public Connection getConnection() {
        return conn;
    }

    public static DatabaseConnnect getInstance() {
        try {
			
			    instance = new DatabaseConnnect();
			    instance.init();
			    return instance;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
		
    }
}
