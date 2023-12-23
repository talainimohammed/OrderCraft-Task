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

	public Commande(int id_client, String etat) {
		super();
		this.id_client = id_client;
		this.etat = etat;
		date.format(formatter);
		this.date_creation=date;
		this.date_modification=date;
		
	}

	public Commande(int id_commande, int id_client, String etat, LocalDate date1, LocalDate date3) {
		super();
		this.id_commande = id_commande;
		this.id_client = id_client;
		this.etat = etat;
		this.date_creation = date1;
		this.date_modification = date3;
	}

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public LocalDate getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(LocalDate date_creation) {
		this.date_creation = date_creation;
	}

	public LocalDate getDate_modification() {
		return date_modification;
	}

	public void setDate_modification(LocalDate date_modification) {
		this.date_modification = date_modification;
	}

	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", id_client=" + id_client + ", etat=" + etat
				+ ", date_creation=" + date_creation + ", date_modification=" + date_modification + "]";
	}
	
	
	
	
	
}
