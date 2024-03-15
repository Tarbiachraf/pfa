package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.dtos.CommandResponse;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Commande;

import java.util.List;

public interface CommandeService {
    public Commande createCommande(CommandeDTO newCommandeDTO);
    public Commande updateCommande(Long commandeId, CommandeDTO updatedCommandeDto);

    public void deleteCommande(Long id);
    public Commande getCommandeById(Long id);
    public List<CommandResponse> getAllCommandes();

    public List<CommandResponse> getAllCommandesByClientNom(String clientNom);

    public List<CommandResponse> getAllCommandesByClientId(Long clientId);
}
