package model;

import java.util.ArrayList;

public interface ArticleInterface {
	
	boolean ajouterArticle(Article c);
	boolean modifierArticle(Article c);
	Article afficherArticleAvecId(int id);
	ArrayList<Article> afficherArticles();
	boolean supprimeArticle(int id);

}
