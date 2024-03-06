package ma.ensa.pfaproject.controllers;

import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @PostMapping("/create")
    public ResponseEntity<?> createCommande(@RequestBody CommandeDTO commandeDTO){
        Commande commande = commandeService.createCommande(commandeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commande);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCommande(@RequestBody CommandeDTO commandeDTO){
        Commande commande  = commandeService.updateCommande(commandeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(commande);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long id){
        commandeService.deleteCommande(id);
        return ResponseEntity.status(HttpStatus.OK).body("Commande supprimé avec succés");
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCommande(@PathVariable Long id){
        Commande commande = commandeService.getCommandeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(commande);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCommandes(){
        List<Commande> commandeList = commandeService.getAllCommandes();
        return ResponseEntity.status(HttpStatus.OK).body(commandeList);
    }
}
