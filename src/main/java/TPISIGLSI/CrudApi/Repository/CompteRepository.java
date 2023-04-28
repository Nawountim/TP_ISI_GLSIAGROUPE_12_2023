package TPISIGLSI.CrudApi.Repository;

import TPISIGLSI.CrudApi.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    // Méthode pour rechercher un compte par son numéro de compte
    Compte findByNumeroCompte(String numeroCompte);


}
