package model;

import java.util.ArrayList;

public interface CommandeInterface {
	
	public Commande ajouterCommande(Commande c,String listart);
	public Commande afficherCommandeAvecId(int id);
	public ArrayList<Commande> afficherCommandes();
	public ArrayList<String> afficherInfosCommande(int id);
	public boolean modifieretat(int id) ;
	public boolean supprimeCommandes(int id);


}
