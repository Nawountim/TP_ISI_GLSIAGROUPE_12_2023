package SocieteEgaGroup.SocieteEga.service;

import SocieteEgaGroup.SocieteEga.model.Client;
import SocieteEgaGroup.SocieteEga.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;

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
        if (existingClient == null) {
            return null;
        }
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setDateNaissance(client.getDateNaissance());
        existingClient.setSexe(client.getSexe());
        existingClient.setAdresse(client.getAdresse());
        existingClient.setTelephone(client.getTelephone());
        existingClient.setCourriel(client.getCourriel());
        existingClient.setNationalite(client.getNationalite());
        return clientRepository.save(existingClient);
    }

    public boolean deleteClient(Long id) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient == null) {
            return false;
        }
        clientRepository.delete(existingClient);
        return true;
    }
}
