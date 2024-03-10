package ma.ensa.pfaproject.entities;

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

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nomClient;

    private String prenomClient;

    private String CodePostal;

    private String ville;

    private String pays;

    private String tel;

    private String eMail;

    private String societe;

    @JsonIgnoreProperties("client")
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;

}
