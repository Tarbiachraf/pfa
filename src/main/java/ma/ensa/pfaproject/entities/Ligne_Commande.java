package ma.ensa.pfaproject.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Ligne_Commande {
    @EmbeddedId
    private Ligne_CommandeId id
    private int quantite;



}
