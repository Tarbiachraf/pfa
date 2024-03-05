package ma.ensa.pfaproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;

    @Column(unique = true)
    private String refProd;

    private String nomProd;

    private int prixUnitaireHT;

    private String details;

    @JsonIgnoreProperties("produit")
    @OneToMany(mappedBy = "produit")
    List<LigneCommande> ligneCommandes;

    @JsonIgnoreProperties("produitList")
    @ManyToOne
    private CategorieProduit categorieProduit;
}
