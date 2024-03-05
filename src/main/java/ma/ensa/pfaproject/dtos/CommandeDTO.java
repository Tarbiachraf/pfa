package ma.ensa.pfaproject.dtos;

import lombok.Builder;
import lombok.Data;
import ma.ensa.pfaproject.entities.LigneCommande;

import java.util.List;

@Builder
@Data
public class CommandeDTO {
    private Long idClient;
    private double montantTotal;
    private List<LigneCommande> ligneCommande;
}
