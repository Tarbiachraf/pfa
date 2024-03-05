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
    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody Client client){
        Client client1  = clientService.createClient(client);
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
    public ResponseEntity<?> getAllProducts(){
        List<Client> clientList = clientService.getAllClients();
        return ResponseEntity.ok(clientList);
    }
}
