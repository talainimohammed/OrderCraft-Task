package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Article;
import model.ArticleInterface;
import model.Client;
import model.Connectiondb;

public class ArticleDAO implements ArticleInterface{
	Connectiondb conDB=new Connectiondb();
	Connection con=conDB.connectionBD();
    PreparedStatement statement = null;
    ResultSet st=null;
    Article ar=null;
    
	public boolean ajouterArticle(Article c) {
		 try {
                // Ajouter Client
                String insertQuery = "INSERT INTO article(libelle, categorie, prix, stock) VALUES (?, ?, ?, ?)";
                statement = con.prepareCall(insertQuery);
                statement.setString(1, c.getLibelle());
                statement.setString(2, c.getCategorie());
                statement.setDouble(3, c.getPrix());
                statement.setInt(4, c.getStock());
                statement.execute();
                return true;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public boolean modifierArticle(Article c) {
		 try {
               // Ajouter Client
               String insertQuery = "UPDATE article set libelle=?, categorie=?, prix=?, stock=? WHERE id_article=?";
               statement = con.prepareCall(insertQuery);
               statement.setString(1, c.getLibelle());
               statement.setString(2, c.getCategorie());
               statement.setDouble(3, c.getPrix());
               statement.setInt(4, c.getStock());
               statement.setInt(5, c.getId_article());
               statement.execute();
               return true;
	                
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	public Article afficherArticleAvecId(int id){
		 try {
			statement = con.prepareStatement("select * from article where id_article="+id);
			st=statement.executeQuery();
			
			while (st.next()) {
				ar=new Article(st.getInt(1),st.getString(2), st.getString(3), st.getDouble(4), st.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ar; 
	}
	public ArrayList<Article> afficherArticles(){
		ArrayList<Article> articlesList=new ArrayList<>();
		 try {
			statement = con.prepareStatement("select * from article");
			st=statement.executeQuery();
			
			while (st.next()) {
				ar=new Article(st.getInt(1),st.getString(2), st.getString(3), st.getDouble(4), st.getInt(5));
				articlesList.add(ar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articlesList; 
	}
	public boolean supprimeArticle(int id) {
		
        try {
        	String query = "DELETE FROM article WHERE id_article = ?";
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
