package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;

import java.util.List;

public interface CommandeService {
    public Commande createCommande(CommandeDTO newCommandeDTO);
    public Commande updateCommande(CommandeDTO updatedCommandeDto);

    public void deleteCommande(Long id);
    public Commande getCommandeById(Long id);
    public List<Commande> getAllCommandes();

    public List<Commande> getAllCommandesByClientNom(String clientNom);

    public List<Commande> getAllCommandesByClientId(Long clientId);
}
