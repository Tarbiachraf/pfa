package ma.ensa.pfaproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LigneCommande {
    @Id
    private Long id;

    @JsonIgnoreProperties("ligneCommandes")
    @ManyToOne
    private Commande commande;

    @JsonIgnoreProperties("ligneCommandes")
    @ManyToOne
    private Produit produit;

    private int quantite;
}
