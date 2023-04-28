package TPISIGLSI.CrudApi.Service;

import TPISIGLSI.CrudApi.Entity.Client;
import TPISIGLSI.CrudApi.Repository.ClientRepository;
import TPISIGLSI.CrudApi.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setNom(client.getNom());
            existingClient.setPrenom(client.getPrenom());
            existingClient.setDateNaissance(client.getDateNaissance());
            existingClient.setSexe(client.getSexe());
            existingClient.setAdresse(client.getAdresse());
            existingClient.setNumeroTelephone(client.getNumeroTelephone());
            existingClient.setCourriel(client.getCourriel());
            existingClient.setNationalite(client.getNationalite());
            return clientRepository.save(existingClient);
        }
        return null;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
