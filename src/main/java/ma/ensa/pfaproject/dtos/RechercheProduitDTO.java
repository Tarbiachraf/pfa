package ma.ensa.pfaproject.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RechercheProduitDTO {
    private Long idCategorie;
    private String produitNom;
}
