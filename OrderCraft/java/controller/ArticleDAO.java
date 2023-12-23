package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DatabaseConnnect;
import model.Article;
import model.I_Article;
import model.Client;

public class ArticleDAO implements I_Article{
    Connection con = DatabaseConnnect.getInstance().getConnection();
    PreparedStatement statement = null;
    ResultSet st=null;
    Article artDao=null;
	SqlOperations sqloperation=new SqlOperations();

	@Override
	public Article ajouterArticle(Article c) {
		 // Ajouter Article
		String insertQuery = "INSERT INTO article(libelle, categorie, prix, stock) VALUES ('"+c.getLibelle()+"', '"+c.getCategorie()+"', '"+c.getPrix()+"', '"+c.getStock()+"')";     
		int idArticle =  sqloperation.ajouterSql(insertQuery,"ADD");
		return this.afficherArticleAvecId(idArticle);
	}
	
	@Override
	public Article modifierArticle(Article c) {
		 // Modifier Article
		   String insertQuery = "UPDATE article set libelle='"+c.getLibelle()+"', categorie='"+c.getCategorie()+"', prix='"+c.getPrix()+"', stock='"+c.getStock()+"' WHERE id_article="+c.getId_article();
		   int idArticle =  sqloperation.ajouterSql(insertQuery,null);
		   return this.afficherArticleAvecId(c.getId_article());
	}
	
	@Override
	public Article afficherArticleAvecId(int id){
		//Afficher Les Article Par id article
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
	
	@Override
	public ArrayList<Article> afficherArticles(){
		//Afficher tous Les Articles

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
	
	@Override
	public boolean supprimeArticle(int id) {
			//Supprimer Article
        	String query = "DELETE FROM article WHERE id_article = "+id;
    		int check= sqloperation.ajouterSql(query,null);
        	if(check>0) {
     		   return true;
     	   }
     		return false;
	}
}
