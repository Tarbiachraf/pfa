package ma.ensa.pfaproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {

    private String refProd;

    private String nomProd;

    private int prixUnitaireHT;

    private String details;

    private Long category;
}
