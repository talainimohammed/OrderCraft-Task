package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DatabaseConnnect;
import model.Article;
import model.ArticleInterface;
import model.Client;

public class ArticleDAO implements ArticleInterface{
    Connection con = DatabaseConnnect.getInstance().getConnection();
    PreparedStatement statement = null;
    ResultSet st=null;
    Article artDao=null;
	SqlOperations sqloperation=new SqlOperations();

	public Article ajouterArticle(Article c) {
		 // Ajouter Article
		String insertQuery = "INSERT INTO article(libelle, categorie, prix, stock) VALUES ('"+c.getLibelle()+"', '"+c.getCategorie()+"', '"+c.getPrix()+"', '"+c.getStock()+"')";     
		int idArticle =  sqloperation.ajouterSql(insertQuery,"ADD");
		return this.afficherArticleAvecId(idArticle);
	}
	
	public Article modifierArticle(Article c) {
		 // Modifier Article
		   String insertQuery = "UPDATE article set libelle='"+c.getLibelle()+"', categorie='"+c.getCategorie()+"', prix='"+c.getPrix()+"', stock='"+c.getStock()+"' WHERE id_article="+c.getId_article();
		   int idArticle =  sqloperation.ajouterSql(insertQuery,null);
		   return this.afficherArticleAvecId(c.getId_article());
	}
	public Article afficherArticleAvecId(int id){
		 try {
			String qry="select * from article where id_article="+id;
			st=sqloperation.getSql(qry);
			
			while (st.next()) {
				artDao=new Article.ArticleBuilder().setId_article(st.getInt(1)).setLibelle(st.getString(2)).setCategorie(st.getString(3)).setPrix(st.getDouble(4)).setStock(st.getInt(5)).build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artDao; 
	}
	public ArrayList<Article> afficherArticles(){
		ArrayList<Article> articlesList=new ArrayList<>();
		 try {
			 String qry="select * from article";
			 st=sqloperation.getSql(qry);
			
			while (st.next()) {
				artDao=new Article.ArticleBuilder().setId_article(st.getInt(1)).setLibelle(st.getString(2)).setCategorie(st.getString(3)).setPrix(st.getDouble(4)).setStock(st.getInt(5)).build();
				articlesList.add(artDao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articlesList; 
	}
	public boolean supprimeArticle(int id) {
        	String query = "DELETE FROM article WHERE id_article = "+id;
    		int check= sqloperation.ajouterSql(query,null);
        	if(check>0) {
     		   return true;
     	   }
     		return false;
	}
}
