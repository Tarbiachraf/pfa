package ma.ensa.pfaproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieProduit {
    @Id
    private Long idCategorie;
    private String nomCategorie;

    @OneToMany(mappedBy = "categorieProduit")
    private List<Produit> produitList;
}
