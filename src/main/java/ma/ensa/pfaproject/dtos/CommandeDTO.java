package ma.ensa.pfaproject.dtos;

import lombok.Builder;
import lombok.Data;
import ma.ensa.pfaproject.entities.LigneCommande;
import ma.ensa.pfaproject.entities.StatusCommande;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class CommandeDTO {
    private Long idClient;
    private double montantTotal;
    private StatusCommande status;
    private Date dateReglement;
    private Date dateCommande;
    private List<LigneCommandeDto> ligneCommandes;
}
