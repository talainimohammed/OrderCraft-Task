package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.taglibs.standard.tag.common.core.CatchTag;

import config.DatabaseConnnect;
import model.Client;
import model.I_Client;
import model.Commande;

public class ClientDAO implements I_Client{
	
    Connection con = DatabaseConnnect.getInstance().getConnection();
    PreparedStatement statement = null;
    ResultSet st=null;
	Client cl=null;
	SqlOperations sqloperation=new SqlOperations();

	@Override
	public Client ajouterClient(Client c) {
		 // Ajouter Client
		String insertQuery = "INSERT INTO client(nom, prenom, tel, adresse) VALUES ('"+c.getNom()+"', '"+c.getPrenom()+"', '"+c.getTel()+"', '"+c.getAdresse()+"')";
		int idClient =  sqloperation.ajouterSql(insertQuery,"ADD");
		return this.afficherClientsAvecId(idClient);
	}
	
	@Override
	public Client modifierClient(Client c) {
		 // Modifier Client
		   String insertQuery = "UPDATE client set nom='"+c.getNom()+"', prenom='"+c.getPrenom()+"', tel='"+c.getTel()+"', adresse='"+c.getAdresse()+"' WHERE id_client="+c.getId_client()+"";
		   int check= sqloperation.ajouterSql(insertQuery,null);
		   if(check>0) {
			   return this.afficherClientsAvecId(c.getId_client());
		   }
		return null;
	}
	
	@Override
	public Client afficherClientsAvecId(int id){
		 try {
			String qry="select * from client where id_client="+id;
			st=sqloperation.getSql(qry);
			
			while (st.next()) {
				cl=new Client.ClientBuilder().setId_client(st.getInt(1)).setNom(st.getString(2)).setPrenom(st.getString(3)).setTel(st.getString(4)).setAdresse(st.getString(5)).build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cl; 
	}
	
	@Override
	public ArrayList<Client> afficherClients(){
		ArrayList<Client> clientsList=new ArrayList<>();
		 try {
			String qry="select * from client";
			st=sqloperation.getSql(qry);
			while (st.next()) {
				cl=new Client.ClientBuilder().setId_client(st.getInt(1)).setNom(st.getString(2)).setPrenom(st.getString(3)).setTel(st.getString(4)).setAdresse(st.getString(5)).build();
				clientsList.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientsList; 
	}
	
	@Override
	public boolean supprimeClient(int id) {
		Commande cmd=null;
		CommandeDAO cmddao=new CommandeDAO();
		String qry="select id_commande from commande where id_client="+id;
		st=sqloperation.getSql(qry);
		try {
			while (st.next()) {
				cmd=new Commande.CommandeBuilder().setId_commande(st.getInt(1)).build();
				cmddao.supprimeCommandes(cmd.getId_commande());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String query = "DELETE FROM client WHERE id_client = "+id;
		int check= sqloperation.ajouterSql(query,null);
	   if(check>0) {
		   return true;
	   }
		return false;
	}
}
