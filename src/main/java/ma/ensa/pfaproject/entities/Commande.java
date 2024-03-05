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


    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date dateCommande;
    private Date dateReglement;
    //private String moyenPayement;
    private double montantTotal;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatusCommande statusCde = StatusCommande.NON_TRAITE;

    @JsonIgnoreProperties("commandes")
    @ManyToOne
    private Client client;

    @JsonIgnoreProperties("commande")
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
}
