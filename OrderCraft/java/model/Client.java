package model;

public class Client {

	private int id_client;
	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	
	
	
	private Client(ClientBuilder clientBuilder) {
		this.id_client=clientBuilder.id_client;
		this.nom=clientBuilder.nom;
		this.prenom=clientBuilder.prenom;
		this.tel=clientBuilder.tel;
		this.adresse=clientBuilder.adresse;
	}
	public int getId_client() {
		return id_client;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTel() {
		return tel;
	}

	public String getAdresse() {
		return adresse;
	}

	
	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse="
				+ adresse + "]";
	}
	
	public  static class ClientBuilder{
		private int id_client;
		private String nom;
		private String prenom;
		private String tel;
		private String adresse;
		
		public ClientBuilder setId_client(int id_client) {
			this.id_client = id_client;
			return this;
		}
		public ClientBuilder setNom(String nom) {
			this.nom = nom;
			return this;
		}
		public ClientBuilder setPrenom(String prenom) {
			this.prenom = prenom;
			return this;
		}
		public ClientBuilder setTel(String tel) {
			this.tel = tel;
			return this;
		}
		public ClientBuilder setAdresse(String adresse) {
			this.adresse = adresse;
			return this;
		}
		public Client build() {
			return new Client(this);
		}
		
		
	}
	
}
