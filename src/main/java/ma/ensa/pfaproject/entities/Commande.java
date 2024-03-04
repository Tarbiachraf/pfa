package ma.ensa.pfaproject.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    private Date dateCommande;
    private Date dateReglement;
    //private String moyenPayement;
    private double montantTotal;

    private String statusCde;

    @OneToMany(mappedBy = "commande")
    private List<Ligne_Commande> ligneCommandes;
}
