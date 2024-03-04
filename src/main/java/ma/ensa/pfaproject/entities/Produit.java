package ma.ensa.pfaproject.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String refProd;

    private String nomProd;

    private int prixUnitaireHT;

    private String details;

    @OneToMany(mappedBy = "produit")
    List<Ligne_Commande> ligneCommandes;
}
