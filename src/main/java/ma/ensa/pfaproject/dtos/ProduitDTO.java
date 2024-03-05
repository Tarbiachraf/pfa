package ma.ensa.pfaproject.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProduitDTO {

    private String refProd;

    private String nomProd;

    private int prixUnitaireHT;

    private String details;

    private String category;
}
