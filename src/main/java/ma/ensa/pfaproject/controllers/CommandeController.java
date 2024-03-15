package ma.ensa.pfaproject.controllers;

import jakarta.transaction.Transactional;
import ma.ensa.pfaproject.dtos.CommandResponse;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.dtos.RechercheClientDto;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/commande")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @Transactional
    @PostMapping("/create")
    public ResponseEntity<?> createCommande(@RequestBody CommandeDTO commandeDTO){
        Commande commande = commandeService.createCommande(commandeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commande);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCommande(@PathVariable Long commandeId,@RequestBody CommandeDTO commandeDTO){
        Commande commande  = commandeService.updateCommande(commandeId,commandeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(commande);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long id){
        commandeService.deleteCommande(id);
        return ResponseEntity.status(HttpStatus.OK).body("Commande supprimé avec succés");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommande(@PathVariable Long id){
        Commande commande = commandeService.getCommandeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(commande);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCommandes(){
        List<Commande> commandeList = commandeService.getAllCommandes();
        return ResponseEntity.status(HttpStatus.OK).body(commandeList);
    }

    @PostMapping("/recherche")
    public ResponseEntity<?> getCommandes(@RequestBody RechercheClientDto rechercheClientDto){
        List<CommandResponse> commandeList = commandeService.getAllCommandesByClientNom(rechercheClientDto.getNomClient());
        return ResponseEntity.status(HttpStatus.OK).body(commandeList);
    }
}
