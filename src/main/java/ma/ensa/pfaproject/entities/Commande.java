package ma.ensa.pfaproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;


    private Date dateCommande;
    private Date dateReglement;
    //private String moyenPayement;
    private double montantTotal;

    @Enumerated(EnumType.STRING)
    private StatusCommande statusCde;

    @JsonIgnoreProperties("commandes")
    @ManyToOne
    private Client client;

    @JsonIgnoreProperties("commandes")
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
}
