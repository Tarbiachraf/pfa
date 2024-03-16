package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.CommandResponse;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.dtos.LigneCommandeDto;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.LigneCommande;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.mapper.CommandMapper;
import ma.ensa.pfaproject.mapper.CommandResponseMapper;
import ma.ensa.pfaproject.mapper.LigneCommandMapper;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.repositories.LigneCommandeRepository;
import ma.ensa.pfaproject.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImp implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private LigneCommandService ligneCommandService;

    @Autowired
    private CommandMapper commandMapper;

    @Autowired
    private LigneCommandMapper ligneCommandMapper;

    @Autowired
    private CommandResponseMapper commandResponseMapper;
    @Override
    public Commande createCommande(CommandeDTO newCommandeDto) {
        Commande commande = commandMapper.toCommande(newCommandeDto);
        Commande savedCommande = commandeRepository.save(commande);
        List<LigneCommande> ligneCommandes = commande.getLigneCommandes();
        for(LigneCommande l:ligneCommandes){
            l.setCommande(savedCommande);
            ligneCommandeRepository.save(l);
        }
        return savedCommande;
    }

    @Override
    public Commande updateCommande(Long commandeId,CommandeDTO updatedCommandeDto) {
//        Commande updatedcommande = commandMapper.toCommande(updatedCommandeDto);
//        Commande commande = commandeRepository.findById(commandeId).orElseThrow(()-> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,commandeId, ErrorMessages.CommandeNotFoundMessage));
//
////        commande.setMontantTotal(updatedcommande.getMontantTotal());
//        commande.setStatusCde(updatedcommande.getStatusCde());
//
//        commande.setDateReglement(updatedcommande.getDateReglement());
//
//        commande.getLigneCommandes().clear();
//        commande.setLigneCommandes(updatedcommande.getLigneCommandes());
//        return commandeRepository.save(commande);
        Commande existingCommande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE, commandeId, ErrorMessages.CommandeNotFoundMessage));

        // Mise à jour des attributs de la commande
        existingCommande.setMontantTotal(updatedCommandeDto.getMontantTotal());
        existingCommande.setStatusCde(updatedCommandeDto.getStatus());
        existingCommande.setDateReglement(updatedCommandeDto.getDateReglement());
        existingCommande.setDateCommande(updatedCommandeDto.getDateCommande());

        List<LigneCommande> updatedLigneCommandes = new ArrayList<>();
        for (LigneCommandeDto ligneCommandeDto : updatedCommandeDto.getLigneCommandes()) {
            LigneCommande ligneCommande = ligneCommandMapper.toLigneCommande(ligneCommandeDto);
            ligneCommande.setCommande(existingCommande);
            updatedLigneCommandes.add(ligneCommande);
        }
        existingCommande.setLigneCommandes(updatedLigneCommandes);

        deleteOrphanedLigneCommandes(existingCommande);
        return commandeRepository.save(existingCommande);
    }
    private void deleteOrphanedLigneCommandes(Commande commande) {
        List<LigneCommande> existingLigneCommandes = ligneCommandeRepository.findByCommande(commande);

        for (LigneCommande existingLigneCommande : existingLigneCommandes) {
            if (!commande.getLigneCommandes().contains(existingLigneCommande)) {
                ligneCommandeRepository.delete(existingLigneCommande);
            }
        }
    }

    @Override
    public void deleteCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE, id, ErrorMessages.CommandeNotFoundMessage));

        ligneCommandeRepository.deleteAllByCommande(commande);

        commandeRepository.delete(commande);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,id, ErrorMessages.CommandeNotFoundMessage));
    }

    @Override
    public List<CommandResponse> getAllCommandes() {
        List<Commande> commandeList = commandeRepository.findAll();
        if(commandeList == null){
            return Collections.emptyList();
        }
        return commandeList.stream().map(commande -> commandResponseMapper.toCommandeResponse(commande)).collect(Collectors.toList());
    }

    @Override
    public List<CommandResponse> getAllCommandesByClientNom(String clientNom) {
        List<Commande> commandeList = commandeRepository.getAllCommandesByClientContainingKey(clientNom);
        if(commandeList == null){
            return Collections.emptyList();
        }
        return commandeList.stream().map(commande -> commandResponseMapper.toCommandeResponse(commande)).collect(Collectors.toList());
    }

    @Override
    public List<CommandResponse> getAllCommandesByClientId(Long clientId) {
        List<Commande> commandeList = commandeRepository.findAll();
        if(commandeList == null){
            return Collections.emptyList();
        }
        return commandeList.stream().map(commande -> commandResponseMapper.toCommandeResponse(commande)).collect(Collectors.toList());
    }
 }
