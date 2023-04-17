package SocieteEgaGroup.SocieteEga.repo;

import SocieteEgaGroup.SocieteEga.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepo extends JpaRepository<Compte, Long> {
    Compte findByNumeroCompte(String numeroCompte);
}
