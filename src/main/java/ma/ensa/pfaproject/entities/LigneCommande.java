package ma.ensa.pfaproject.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande {
    @Id
    private Long id;

    @ManyToOne
    private Commande commande;

    @ManyToOne
    private Produit produit;

    private int quantite;



}
