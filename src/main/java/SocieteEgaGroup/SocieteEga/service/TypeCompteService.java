package SocieteEgaGroup.SocieteEga.service;

import SocieteEgaGroup.SocieteEga.model.TypeCompte;
import SocieteEgaGroup.SocieteEga.repo.TypeCompteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCompteService {

    @Autowired
    private TypeCompteRepo typeCompteRepository;

    public List<TypeCompte> getAllTypeComptes() {
        return typeCompteRepository.findAll();
    }

    public TypeCompte getTypeCompteById(Long id) {
        return typeCompteRepository.findById(id).orElse(null);
    }

    public TypeCompte saveTypeCompte(TypeCompte typeCompte) {
        return typeCompteRepository.save(typeCompte);
    }

    public void deleteTypeCompte(Long id) {
        typeCompteRepository.deleteById(id);
    }
}
