package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Commande {
	
	private int id_commande;
	private int id_client;
	private String etat;
	private LocalDate created_at;
	private LocalDate updated_at;
	LocalDate date = LocalDate.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Commande(CommandeBuilder builder) {
		this.id_commande= builder.id_commande;
		this.id_client = builder.id_client;
		this.etat = builder.etat;
		this.created_at=builder.created_at;
		this.updated_at=builder.updated_at;
	}

	public int getId_commande() {
		return id_commande;
	}

	public int getId_client() {
		return id_client;
	}

	public String getEtat() {
		return etat;
	}

	public LocalDate getcreated_at() {
		return created_at;
	}

	public LocalDate getupdated_at() {
		return updated_at;
	}

	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", id_client=" + id_client + ", etat=" + etat
				+ ", date_creation=" + created_at + ", date_modification=" + updated_at + "]";
	}
	
	public static class CommandeBuilder{
		private int id_commande;
		private int id_client;
		private String etat;
		private LocalDate created_at;
		private LocalDate updated_at;
		
		public CommandeBuilder setId_commande(int id_commande) {
			this.id_commande = id_commande;
			return this;
		}
		public CommandeBuilder setId_client(int id_client) {
			this.id_client = id_client;
			return this;
		}
		public CommandeBuilder setEtat(String etat) {
			this.etat = etat;
			return this;

		}
		public CommandeBuilder setcreated_at(LocalDate created_at) {
			this.created_at = created_at;
			return this;

		}
		public CommandeBuilder setupdated_at(LocalDate updated_at) {
			this.updated_at = updated_at;
			return this;
		}
		
		public Commande build() {
			return new Commande(this);
		}
	}
	
	
	
	
}
