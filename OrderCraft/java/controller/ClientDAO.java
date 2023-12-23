package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.taglibs.standard.tag.common.core.CatchTag;

import model.Client;
import model.ClientInterface;
import model.Connectiondb;
import model.DatabaseConnnect;

public class ClientDAO implements ClientInterface{
	/*Connection con=null;
	DatabaseConnnect databaseConnnect = DatabaseConnnect.getInstance();
    con = databaseConnnect.getConnection();*/

	Connectiondb conDB=new Connectiondb();
	Connection con=conDB.connectionBD();
    PreparedStatement statement = null;
    ResultSet st=null;
	Client cl=null;
	public boolean ajouterClient(Client c) {
		 try {
                // Ajouter Client
                String insertQuery = "INSERT INTO client(nom, prenom, tel, adresse) VALUES (?, ?, ?, ?)";
                statement = con.prepareCall(insertQuery);
                statement.setString(1, c.getNom());
                statement.setString(2, c.getPrenom());
                statement.setString(3, c.getTel());
                statement.setString(4, c.getAdresse());
                statement.execute();
                return true;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public boolean modifierClient(Client c) {
		 try {
               // Ajouter Client
               String insertQuery = "UPDATE client set nom=?, prenom=?, tel=?, adresse=? WHERE id_client=?";
               statement = con.prepareCall(insertQuery);
               statement.setString(1, c.getNom());
               statement.setString(2, c.getPrenom());
               statement.setString(3, c.getTel());
               statement.setString(4, c.getAdresse());
               statement.setInt(5, c.getId_client());
               statement.execute();
               return true;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	public Client afficherClientsAvecId(int id){
		 try {
			statement = con.prepareStatement("select * from client where id_client="+id);
			st=statement.executeQuery();
			
			while (st.next()) {
				cl=new Client(st.getInt(1),st.getString(2), st.getString(3), st.getString(4), st.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cl; 
	}
	public ArrayList<Client> afficherClients(){
		ArrayList<Client> clientsList=new ArrayList<>();
		 try {
			statement = con.prepareStatement("select * from client");
			st=statement.executeQuery();
			
			while (st.next()) {
				cl=new Client(st.getInt(1),st.getString(2), st.getString(3), st.getString(4), st.getString(5));
				clientsList.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientsList; 
	}
	public boolean supprimeClient(int id) {
		
        try {
        	String query = "DELETE FROM client WHERE id_client = ?";
    		statement = con.prepareStatement(query);
            statement.setInt(1,id);
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
