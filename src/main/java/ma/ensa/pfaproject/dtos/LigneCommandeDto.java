package ma.ensa.pfaproject.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeDto {
    private Long idProduit;

    private int quantite;
}
