package ma.ensa.pfaproject.dtos;

import lombok.Builder;
import lombok.Data;
import ma.ensa.pfaproject.entities.StatusCommande;

import java.util.Date;

@Data
@Builder
public class CommandResponse {
    private Long idCommande;
    private Long idClient;
    private String nomClient;
    private double montantTotal;
    private StatusCommande status;
    private String dateReglement;
    private String dateCommande;

}
