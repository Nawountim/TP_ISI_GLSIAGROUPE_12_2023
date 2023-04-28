package TPISIGLSI.CrudApi.Service;

import TPISIGLSI.CrudApi.Entity.Compte;
import TPISIGLSI.CrudApi.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Compte getCompteById(Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Compte updateCompte(Long id, Compte compte) {
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null) {
            //existingCompte.setNumeroCompte(compte.getNumeroCompte());
            existingCompte.setTypeCompte(compte.getTypeCompte());
            existingCompte.setDateCreation(compte.getDateCreation());
            existingCompte.setSolde(compte.getSolde());
            existingCompte.setProprietaire(compte.getProprietaire());
            return compteRepository.save(existingCompte);
        }
        return null;
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

    // Méthode pour faire un versement
    public void faireVersement(String numeroCompte, double montant) {
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte);
        if (compte != null) {
            compte.faireVersement(numeroCompte, montant);
            compteRepository.save(compte);
        } else {
            System.out.println("Le numéro de compte n'existe pas.");
        }
    }

    // Méthode pour faire un retrait sur un compte
    public void faireRetrait(String numeroCompte, double montant) {
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte);
        if (compte != null) {
            double soldeActuel = compte.getSolde();
            if (soldeActuel >= montant) {
                double nouveauSolde = soldeActuel - montant;
                compte.setSolde(nouveauSolde);
                compteRepository.save(compte);
            } else {
                throw new RuntimeException("Solde insuffisant pour effectuer le retrait");
            }
        } else {
            throw new RuntimeException("Le compte avec le numéro de compte fourni n'existe pas");
        }
    }

    // Méthode pour effectuer un virement d'un compte à un autre
    public void effectuerVirement(String numeroCompteEmetteur, String numeroCompteBeneficiaire, double montant) {
        // Rechercher les comptes émetteur et bénéficiaire par leur numéro de compte
        Compte compteEmetteur = compteRepository.findByNumeroCompte(numeroCompteEmetteur);
        Compte compteBeneficiaire = compteRepository.findByNumeroCompte(numeroCompteBeneficiaire);

        // Vérifier si les comptes existent
        if (compteEmetteur == null) {
            throw new IllegalArgumentException("Le compte émetteur n'existe pas.");
        }
        if (compteBeneficiaire == null) {
            throw new IllegalArgumentException("Le compte bénéficiaire n'existe pas.");
        }

        // Vérifier si le solde du compte émetteur est suffisant pour effectuer le virement
        if (compteEmetteur.getSolde() < montant) {
            throw new IllegalArgumentException("Le solde du compte émetteur est insuffisant.");
        }

        // Mettre à jour les soldes des comptes émetteur et bénéficiaire
        compteEmetteur.setSolde(compteEmetteur.getSolde() - montant);
        compteBeneficiaire.setSolde(compteBeneficiaire.getSolde() + montant);

        // Enregistrer les modifications dans la base de données
        compteRepository.save(compteEmetteur);
        compteRepository.save(compteBeneficiaire);
    }

}
