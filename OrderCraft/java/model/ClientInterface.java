package model;

import java.util.ArrayList;

public interface ClientInterface {
	
	boolean ajouterClient(Client c);
	boolean modifierClient(Client c);
	Client afficherClientsAvecId(int id);
	ArrayList<Client> afficherClients();
	boolean supprimeClient(int id);

}
