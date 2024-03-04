package ma.ensa.pfaproject.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class LigneCommandeId implements Serializable {
    @ManyToOne
    private Commande commande;

    @ManyToOne
    private Produit produit;
}
