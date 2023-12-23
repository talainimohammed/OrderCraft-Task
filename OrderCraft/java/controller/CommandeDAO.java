package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonParser;

import model.Client;
import model.Commande;
import model.Connectiondb;
import model.Etat;

public class CommandeDAO {

	Connection con=Connectiondb.connectionBD();
    PreparedStatement statement = null;
    ResultSet st=null;
	Commande cmd=null;
	
	public Commande ajouterCommande(Commande c,String listart) {
		 try {
                // Ajouter Commande
                String insertQuery = "INSERT INTO commande(id_client, etat,date_creation,date_modification) VALUES ('"+c.getId_client()+"', '"+c.getEtat()+"', '"+c.getDate_creation()+"', '"+c.getDate_modification()+"')";
                statement = con.prepareCall(insertQuery);
                statement.execute();
                
                statement = con.prepareStatement("select * from commande where id_commande=(SELECT LAST_INSERT_ID())");
    			st=statement.executeQuery();
    			
    			while (st.next()) {
    				cmd=new Commande(st.getInt(1),st.getInt(2), st.getString(3), st.getDate(4).toLocalDate(), st.getDate(5).toLocalDate());
    			}
    			
    			JSONArray array = new JSONArray(listart);  
    			for(int i=0; i < array.length(); i++)   
    			{  
    			JSONObject object = array.getJSONObject(i); 
    			String Query = "INSERT INTO commande_article(id_commande, id_article,qty) VALUES ('"+cmd.getId_commande()+"', '"+object.getInt("id_article")+"', '"+object.getInt("qty")+"')";
                statement = con.prepareCall(Query);
                statement.execute();
    			//System.out.println(object.getString("categorie"));  
    			//System.out.println(object.getString("libelle"));  
    			}      			
                return cmd;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	public Commande afficherCommandeAvecId(int id){
		 try {
			statement = con.prepareStatement("select * from commande where id_commande="+id);
			st=statement.executeQuery();
			
			while (st.next()) {
				cmd=new Commande(st.getInt(1),st.getInt(2), st.getString(3), st.getDate(4).toLocalDate(), st.getDate(5).toLocalDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cmd; 
	}
	public ArrayList<Commande> afficherCommandes(){
		ArrayList<Commande> commandesList=new ArrayList<>();
		 try {
			statement = con.prepareStatement("select * from commande");
			st=statement.executeQuery();
			
			while (st.next()) {
				cmd=new Commande(st.getInt(1),st.getInt(2), st.getString(3), st.getDate(4).toLocalDate(), st.getDate(5).toLocalDate());
				commandesList.add(cmd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commandesList; 
	}
	
	public ArrayList<String> afficherCommandesArticle(){
		ArrayList<String> commandesList=new ArrayList<>();
		 try {
			statement = con.prepareStatement("SELECT commande.id_commande,commande.id_client,commande.etat,commande.date_creation,commande.date_modification,commande_article.id_article,commande_article.qty FROM `commande`,commande_article WHERE commande.id_commande=commande_article.id_commande;");
			st=statement.executeQuery();
			String ob=null;
			while (st.next()) {
			//cmd=new Commande(st.getInt(1),st.getInt(2), st.getString(3), st.getDate(4).toLocalDate(), st.getDate(5).toLocalDate());
			ob=st.getInt(1)+","+st.getInt(2)+","+ st.getString(3)+","+ st.getDate(4).toLocalDate()+","+ st.getDate(5).toLocalDate();
			ob+=","+st.getInt(6)+","+st.getInt(7);
			commandesList.add(ob);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commandesList; 
	}
	
	public ArrayList<String> afficherCommandesArticleClient(int id){
		ArrayList<String> commandesList=new ArrayList<>();
		 try {
			statement = con.prepareStatement("SELECT commande.id_commande,commande.id_client,commande_article.id_article,commande_article.qty FROM `commande`,commande_article WHERE commande.id_commande=commande_article.id_commande;");
			st=statement.executeQuery();
			String ob=null;
			while (st.next()) {
			ob=st.getInt(1)+","+st.getInt(2)+","+ st.getString(3)+","+ st.getDate(4).toLocalDate();
			commandesList.add(ob);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commandesList; 
	}
	public boolean modifieretat(int id) {
		 try {
              // Modifier Etat
				 statement = con.prepareStatement("select etat from commande where id_commande="+id);
				 st=statement.executeQuery();
					
				st.next(); 
				String etat=st.getString(1);
				Etat enumEtat=null;
				Etat val=null;
				if(etat.equals(enumEtat.EnAttente)) {
					val=enumEtat.EnCours;
				}
	            String insertQuery = "UPDATE commande set etat=? WHERE id_commande=?";
		        statement = con.prepareCall(insertQuery);
		        statement.setInt(1, id);
		        statement.execute();
              return true;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	public boolean supprimeCommandes(int id) {
		
        try {
        	String query = "DELETE FROM commande_article WHERE id_commande = ?";
    		statement = con.prepareStatement(query);
            statement.setInt(1,id);
			statement.execute();
        	String query1 = "DELETE FROM commande WHERE id_commande = ?";
    		statement = con.prepareStatement(query1);
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
