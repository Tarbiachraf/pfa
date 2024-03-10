package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Produit;

import java.util.List;

public interface ClientService {
    public Client createClient(Client newClient);
    public Client updateClient(Client UpdatedClient);

    public void deleteClient(Long id);
    public Client getClientById(Long id);
    public List<Client> getAllClients();

    public List<Client> getAllClientsByNom(String key);
}
