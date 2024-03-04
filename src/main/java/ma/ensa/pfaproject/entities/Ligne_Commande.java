package ma.ensa.pfaproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Ligne_Commande {

    private int quantite;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Commande commande;

}
