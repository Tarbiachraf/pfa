package ma.ensa.pfaproject.controllers;

import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody Client client){
        Client client1 = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client1);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id,@RequestBody Client client){
        Client client1  = clientService.updateClient(id,client);
        return ResponseEntity.status(HttpStatus.OK).body(client1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("client supprimé avec succés");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id){
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClients(){
        List<Client> clientList = clientService.getAllClients();
        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/recherche")
    public ResponseEntity<?> getAllClientsByNomContainingKey(@RequestBody String key){
        List<Client> clientList = clientService.getAllClientsByNom(key);
        return ResponseEntity.ok(clientList);
    }
}
