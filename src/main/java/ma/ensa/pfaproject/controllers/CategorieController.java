package ma.ensa.pfaproject.controllers;

import ma.ensa.pfaproject.entities.CategorieProduit;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.services.CategoryService;
import ma.ensa.pfaproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategorieController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestParam String nomCategorie){
        CategorieProduit categorieProduit = categoryService.createCategory(nomCategorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorieProduit);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody CategorieProduit categorieProduit){
        CategorieProduit categorieProduit1  = categoryService.updateCategory(id,categorieProduit);
        return ResponseEntity.status(HttpStatus.OK).body(categorieProduit1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("la catégorie supprimé avec succés");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        CategorieProduit categorieProduit = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categorieProduit);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategoris(){
        List<CategorieProduit> categorieProduits = categoryService.getAllCategory();
        return ResponseEntity.ok(categorieProduits);
    }
    @GetMapping("/recherche")
    public ResponseEntity<?> getAllCategoris(String nomCategorie){
        List<CategorieProduit> categorieProduitList = categoryService.getAllCategoryByNomContainingKey(nomCategorie);
        return ResponseEntity.ok(categorieProduitList);
    }
}
