package model;

import java.util.ArrayList;

public interface ClientInterface {
	
	public Client ajouterClient(Client c);
	public Client modifierClient(Client c);
	public Client afficherClientsAvecId(int id);
	public ArrayList<Client> afficherClients();
	public boolean supprimeClient(int id);

}
