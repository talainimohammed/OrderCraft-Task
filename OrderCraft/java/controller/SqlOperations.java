package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnnect;
import model.Client;

public class SqlOperations {

	 Connection con = DatabaseConnnect.getInstance().getConnection();
     PreparedStatement statement = null;
     ResultSet st=null;
     
	public int ajouterSql(String query,String operation) {
        try {
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        	if(operation!=null) {
        		int line = statement.executeUpdate();
                if (line > 0) {
                    try (ResultSet lastid = statement.getGeneratedKeys()) {
                        if (lastid.next()) {
                            return lastid.getInt(1);
                        } else {
                   
                            throw new RuntimeException("Recuperation Id a echoue");
                        }
                    }
                } else {
                    throw new RuntimeException("Echec d' Insertion");
                }
        	}else {
  			  return statement.executeUpdate();
        	}
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	public ResultSet getSql(String query) {
		try {
			statement = con.prepareStatement(query);
			st=statement.executeQuery();
			return st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
}
