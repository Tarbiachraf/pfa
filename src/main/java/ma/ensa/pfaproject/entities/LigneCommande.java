package ma.ensa.pfaproject.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande {
    @EmbeddedId
    private LigneCommandeId id;
    private int quantite;



}
