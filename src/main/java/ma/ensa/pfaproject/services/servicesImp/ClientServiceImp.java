package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.ClientRepository;
import ma.ensa.pfaproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client createClient(Client newClient) {
        return clientRepository.save(newClient);
    }

    @Override
    public Client updateClient(Long id, Client updatedClient) {
        Client client = clientRepository.findById(id).get();
        if(client != null){
            client.setNomClient(updatedClient.getNomClient());
            client.setPrenomClient(updatedClient.getPrenomClient());
            client.setPays(updatedClient.getPays());
            client.setTel(updatedClient.getTel());
            client.setEmail(updatedClient.getEmail());
            client.setVille(updatedClient.getVille());
            client.setSociete(updatedClient.getSociete());
            client.setCodePostal(updatedClient.getCodePostal());
            return clientRepository.save(client);
        }
        throw new RessourceNotFoundException(ResourceTypeConstant.CLIENT,updatedClient.getIdClient(), ErrorMessages.ClientNotFoundMessage);


    }

    @Override
    public void deleteClient(Long id) {
        if(clientRepository.existsById(id)){
            clientRepository.deleteById(id);
        }
        else {
            throw new RessourceNotFoundException(ResourceTypeConstant.CLIENT,id, ErrorMessages.ClientNotFoundMessage);
        }

    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.CLIENT,id, ErrorMessages.ClientNotFoundMessage));
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        if(clientList == null){
            return Collections.emptyList();
        }
        return clientList;
    }

    @Override
    public List<Client> getAllClientsByNom(String key) {
        List<Client> clientList = clientRepository.getAllClientsByNomContainingKey(key);
        if(clientList == null){
            return Collections.emptyList();
        }
        return clientList;
    }
}
