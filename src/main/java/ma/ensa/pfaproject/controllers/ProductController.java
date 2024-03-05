package ma.ensa.pfaproject.controllers;

import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/Product")
@RestController
public class ProductController {
    @Autowired
    private ProduitService produitService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Produit produit){
        Produit produit1 = produitService.createProduit(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(produit1);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Produit produit){
        Produit produit1 = produitService.createProduit(produit);
        return ResponseEntity.status(HttpStatus.OK).body(produit1);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        produitService.deleteProduit(id);
        return ResponseEntity.status(HttpStatus.OK).body("produit supprimé avec succés");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Produit> produitOptional = produitService.getProduitById(id);
        if(produitOptional.isPresent()){
            Produit produit = produitOptional.get();
            ResponseEntity.ok(produit);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(){
        List<Produit> produitList = produitService.getAllProduit();
        return ResponseEntity.ok(produitList);
    }

}
