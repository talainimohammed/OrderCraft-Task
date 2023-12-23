package model;

import java.util.ArrayList;

public interface I_Article {
	
	public Article ajouterArticle(Article c);
	public Article modifierArticle(Article c);
	public Article afficherArticleAvecId(int id);
	public ArrayList<Article> afficherArticles();
	public boolean supprimeArticle(int id);

}
