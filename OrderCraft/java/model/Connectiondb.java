package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectiondb {
	
	 public static Connection connectionBD(){
		Connection con = null;
		String url= "jdbc:mysql://localhost:3306/db_ordercraft";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException  e) {
			throw new RuntimeException(e);
		}
		return con;
	}
}
