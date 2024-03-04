package ma.ensa.pfaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    private Date dateCommande;
    private Date dateReglement;
    //private String moyenPayement;
    private double montantTotal;

    private String statusCde;

    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
}
