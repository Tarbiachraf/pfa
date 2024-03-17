package ma.ensa.pfaproject.mapper;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.CommandResponse;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@Component
public class CommandResponseMapper {
    private final ClientRepository clientRepository;

    @Autowired
    public CommandResponseMapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    public CommandResponse toCommandeResponse(Commande commande) {
        return CommandResponse.builder()
                .idCommande(commande.getIdCommande())
                .idClient(commande.getClient().getIdClient())
                .nomClient(commande.getClient().getNomClient())
                .montantTotal(commande.getMontantTotal())
                .dateReglement(SimpleDateFormat.getDateInstance().format(commande.getDateReglement()))
                .status(commande.getStatusCde())
                .dateCommande(SimpleDateFormat.getDateInstance().format(commande.getDateCommande()))
                .build();
    }

    public Commande toCommande(CommandeDTO commandeDto) {
        Client client = clientRepository.findById(commandeDto.getIdClient())
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.CLIENT,commandeDto.getIdClient(), ErrorMessages.ClientNotFoundMessage));

        return Commande.builder()
                .client(client)
                .montantTotal(commandeDto.getMontantTotal())
                .dateReglement(commandeDto.getDateReglement())
                .statusCde(commandeDto.getStatus())
                .dateCommande(commandeDto.getDateCommande())
                .build();
    }
}
