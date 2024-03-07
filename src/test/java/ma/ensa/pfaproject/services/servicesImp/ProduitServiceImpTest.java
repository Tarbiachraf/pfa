package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.mapper.ProduitMapper;
import ma.ensa.pfaproject.repositories.ProductRepository;
import ma.ensa.pfaproject.services.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProduitServiceImpTest {
        @InjectMocks
        private ProduitServiceImp produitService;

        @Mock
        private ProduitMapper produitMapper;

        @Mock
        private ProductRepository produitRepository;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testCreateProduit() {
            // Création d'un ProduitDTO de test
            ProduitDTO newProduitDto = new ProduitDTO();
            newProduitDto.setRefProd("12345");
            newProduitDto.setNomProd("Produit de test");
            newProduitDto.setPrixUnitaireHT(100);
            newProduitDto.setDetails("Détails du produit");

            // Configuration du comportement du mapper
            Produit produit = Produit.builder()
                    .refProd("12345")
                    .nomProd("Produit de test")
                    .prixUnitaireHT(100)
                    .details("Détails du produit")
                    .build();
            Produit produit2 = Produit.builder()
                    .refProd("12345")
                    .nomProd("Produit de test")
                    .prixUnitaireHT(10)
                    .details("Détails du produit")
                    .build();

            when(produitMapper.toProduit(newProduitDto)).thenReturn(produit);

            // Appel de la méthode à tester
            produitService.createProduit(newProduitDto);

            // Vérification que la méthode save du repository a été appelée avec le produit en paramètre
            verify(produitRepository).save(produit);
        }
}
