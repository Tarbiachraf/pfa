package ma.ensa.pfaproject.controllers;

import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.dtos.RechercheProduitDTO;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/Product")
@RestController
public class ProductController {
    @Autowired
    private ProduitService produitService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProduitDTO produitDTO){
        Produit produit1 = produitService.createProduit(produitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produit1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProduitDTO produitDTO){
        Produit produit1 = produitService.updateProduit(id,produitDTO);
        return ResponseEntity.status(HttpStatus.OK).body(produit1);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        produitService.deleteProduit(id);
        return ResponseEntity.status(HttpStatus.OK).body("produit supprimé avec succés");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Produit produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(){
        List<Produit> produitList = produitService.getAllProduit();
        return ResponseEntity.ok(produitList);
    }

    @GetMapping("/recherche")
    public ResponseEntity<?> getAllProductsByNomAndCategorie(@RequestBody RechercheProduitDTO rechercheProduitDTO){
        List<Produit> produitList = produitService.getAllProduitByNomContainingKeyAndCategorieId(rechercheProduitDTO.getProduitNom(),rechercheProduitDTO.getIdCategorie());
        return ResponseEntity.ok(produitList);
    }

}
