package ma.ensa.pfaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String refProd;

    private String nomProd;

    private int prixUnitaireHT;

    private String details;

    @OneToMany(mappedBy = "produit")
    List<LigneCommande> ligneCommandes;

    @ManyToOne
    private CategorieProduit categorieProduit;
}
