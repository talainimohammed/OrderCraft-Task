package model;

public class Article {
	
	private int id_article;
	private String libelle;
	private String categorie;
	private double prix;
	private int stock;
	
	
	public Article(String libelle, String categorie,double prix, int stock) {
		super();
		this.libelle = libelle;
		this.categorie = categorie;
		this.prix=prix;
		this.stock = stock;
	}
	
	public Article(int id_article, String libelle, String categorie, double prix, int stock) {
		super();
		this.id_article = id_article;
		this.libelle = libelle;
		this.categorie = categorie;
		this.prix = prix;
		this.stock = stock;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getId_article() {
		return id_article;
	}
	public void setId_article(int id_article) {
		this.id_article = id_article;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Article [id_article=" + id_article + ", libelle=" + libelle + ", categorie=" + categorie + ", stock="
				+ stock + "]";
	}
	
	

}
