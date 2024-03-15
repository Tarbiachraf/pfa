package ma.ensa.pfaproject.dtos;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitResponse {
    private Long idProduit;

    @Column(unique = true)
    private String refProd;

    private String nomProd;

    private int prixUnitaireHT;

    private String details;

    private String categoryName;
}
