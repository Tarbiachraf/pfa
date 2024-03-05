package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.ClientRepository;
import ma.ensa.pfaproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Client updateClient(Client updatedClient) {
        Client client = clientRepository.findById(updatedClient.getIdClient()).get();
        if(client != null){
            client.setNomClient(updatedClient.getNomClient());
            client.setPrenomClient(updatedClient.getPrenomClient());
            client.setPays(updatedClient.getPays());
            client.setTel(updatedClient.getTel());
            client.setEMail(updatedClient.getEMail());
            client.setVille(updatedClient.getVille());
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
        throw new RessourceNotFoundException(ResourceTypeConstant.CLIENT,id, ErrorMessages.ClientNotFoundMessage);

    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.CLIENT,id, ErrorMessages.ClientNotFoundMessage));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
