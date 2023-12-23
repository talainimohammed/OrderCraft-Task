package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Commande {
	
	private int id_commande;
	private int id_client;
	private String etat;
	private LocalDate date_creation;
	private LocalDate date_modification;
	LocalDate date = LocalDate.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Commande(CommandeBuilder builder) {
		this.id_commande= builder.id_commande;
		this.id_client = builder.id_client;
		this.etat = builder.etat;
		this.date_creation=builder.date_creation;
		this.date_modification=builder.date_modification;
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

	public LocalDate getDate_creation() {
		return date_creation;
	}

	public LocalDate getDate_modification() {
		return date_modification;
	}

	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", id_client=" + id_client + ", etat=" + etat
				+ ", date_creation=" + date_creation + ", date_modification=" + date_modification + "]";
	}
	
	public static class CommandeBuilder{
		private int id_commande;
		private int id_client;
		private String etat;
		private LocalDate date_creation;
		private LocalDate date_modification;
		
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
		public CommandeBuilder setDate_creation(LocalDate date_creation) {
			this.date_creation = date_creation;
			return this;

		}
		public CommandeBuilder setDate_modification(LocalDate date_modification) {
			this.date_modification = date_modification;
			return this;
		}
		
		public Commande build() {
			return new Commande(this);
		}
	}
	
	
	
	
}
