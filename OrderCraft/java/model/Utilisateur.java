package model;

public class Utilisateur {
	
	private int id_utilisateur;
	private String nom;
	private String email;
	private String password;
	
	public Utilisateur(String nom, String email, String password) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
	}

	
	public int getId_utilisateur() {
		return id_utilisateur;
	}


	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Utilisateur [id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", email=" + email + ", password="
				+ password + "]";
	}
	
	
	
}
