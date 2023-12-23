package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import config.DatabaseConnnect;
import model.Client;
import model.Commande;
import model.CommandeInterface;
import model.Etat;

public class CommandeDAO implements CommandeInterface{

    PreparedStatement statement = null;
    ResultSet st=null;
	Commande cmd=null;
	SqlOperations sqloperation=new SqlOperations();
    Connection con = DatabaseConnnect.getInstance().getConnection();
    LocalDate date = LocalDate.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
	public Commande ajouterCommande(Commande c,String listart) {
		 try {
	            date.format(formatter);
                // Ajouter Commande
                String insertQuery = "INSERT INTO commande(id_client, etat,date_creation,date_modification) VALUES ('"+c.getId_client()+"', '"+c.getEtat()+"', '"+date+"', '"+date+"')";
                int idCommande = sqloperation.ajouterSql(insertQuery,"ADD");
                cmd=new Commande.CommandeBuilder().setId_commande(idCommande).build();
                        
    			JSONArray array = new JSONArray(listart);  
    			for(int i=0; i < array.length(); i++)   
    			{  
    			JSONObject object = array.getJSONObject(i); 
    			String Query = "INSERT INTO commande_article(id_commande, id_article,qty) VALUES ('"+cmd.getId_commande()+"', '"+object.getInt("id_article")+"', '"+object.getInt("qty")+"')";
                statement = con.prepareCall(Query);
                statement.execute(); 
    			}      			
                return cmd;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	public Commande afficherCommandeAvecId(int id){
		 try {
				String qry="select * from commande where id_commande="+id;
				st=sqloperation.getSql(qry);
				while (st.next()) {
					cmd=new Commande.CommandeBuilder().setId_commande(st.getInt(1)).setId_client(st.getInt(2)).setEtat(st.getString(3)).setDate_creation(st.getDate(4).toLocalDate()).setDate_modification(st.getDate(5).toLocalDate()).build();
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
			 	String qry="select * from commande";
				st=sqloperation.getSql(qry);
			
			while (st.next()) {
				cmd=new Commande.CommandeBuilder().setId_commande(st.getInt(1)).setId_client(st.getInt(2)).setEtat(st.getString(3)).setDate_creation(st.getDate(4).toLocalDate()).setDate_modification(st.getDate(5).toLocalDate()).build();
				commandesList.add(cmd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commandesList; 
	}
	

	
	public ArrayList<String> afficherInfosCommande(int id){
		ArrayList<String> commandesList=new ArrayList<>();
		 try {
			 String qry="SELECT commande.id_commande,commande.id_client,commande_article.id_article,commande_article.qty FROM `commande`,commande_article WHERE commande.id_commande=commande_article.id_commande and commande.id_commande="+id;
			st=sqloperation.getSql(qry);
			String ob=null;
			while (st.next()) {
			ob=st.getInt(1)+","+st.getInt(2)+","+ st.getInt(3)+","+ st.getInt(4);
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
			 	String qry="select etat from commande where id_commande="+id;
				st=sqloperation.getSql(qry);					
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
		//Delete from Table Commande Article
        String query = "DELETE FROM commande_article WHERE id_commande = "+id;
		int check= sqloperation.ajouterSql(query,null);
   	   if(check>0) {
   		   //Delete from Table Commande
		   String query1 = "DELETE FROM commande WHERE id_commande = "+id;
		   int check1= sqloperation.ajouterSql(query1,null);
		   if(check1>0) {
			   return true;
		   }
   	   }
		return false;
	}
}
