package ma.ensa.pfaproject.mapper;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommandMapper {
    private final ClientRepository clientRepository;

    @Autowired
    public CommandMapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public CommandeDTO toCommandeDTO(Commande commande) {
        return CommandeDTO.builder()
                .idClient(commande.getClient().getIdClient())
                .ligneCommande(commande.getLigneCommandes())
                .montantTotal(commande.getMontantTotal())
                .build();
    }

    public Commande toCommande(CommandeDTO commandeDto) {
        Client client = clientRepository.findById(commandeDto.getIdClient())
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.CLIENT,commandeDto.getIdClient(), ErrorMessages.ClientNotFoundMessage));

        return Commande.builder()
                .client(client)
                .ligneCommandes(commandeDto.getLigneCommande())
                .montantTotal(commandeDto.getMontantTotal())
                .build();
    }
}

