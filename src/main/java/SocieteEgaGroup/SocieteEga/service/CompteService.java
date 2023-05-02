package SocieteEgaGroup.SocieteEga.service;

import SocieteEgaGroup.SocieteEga.model.Compte;
import SocieteEgaGroup.SocieteEga.repo.CompteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CompteService {
    @Autowired
    private CompteRepo compteRepository;

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Compte getCompteByNumero(String numeroCompte) {
        return compteRepository.findByNumeroCompte(numeroCompte);
    }


    public Compte createCompte(Compte compte) {
        // Générer le numéro de compte aléatoirement et concaténer l'année courante
        Random random = new Random();
        String numeroCompte = "";
        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(36); // 36 caractères alphanumériques (10 chiffres + 26 lettres)
            if (rand < 10) {
                numeroCompte += rand;
            } else {
                numeroCompte += (char) (rand + 55); // ASCII code pour les lettres majuscules
            }
        }
        numeroCompte += LocalDate.now().getYear();
        LocalDate dateCreation = LocalDate.now();


        // Enregistrer le compte avec le numéro de compte généré
        compte.setNumeroCompte(numeroCompte);
        compte.setDateCreation(dateCreation);
        return compteRepository.save(compte);
    }

    public boolean deleteCompte(Long id) {
        compteRepository.deleteById(id);
        return false;
    }

    public Compte updateCompte(String numeroCompte, Compte compte) {
        Compte compteEnBase = getCompteByNumero(numeroCompte);
        if (compteEnBase != null) {
            compteEnBase.setSolde(compte.getSolde());
            // Mettre à jour les autres propriétés du compte si nécessaire
            //compteEnBase.setDateCreation(LocalDate.now()); // Mise à jour de la date de dernière modification
            compteRepository.save(compteEnBase); // Sauvegarde du compte modifié en base de données
        }
        return compteEnBase;
    }
    public Compte updateSoldeCompte(Compte compte) {
        compteRepository.save(compte);
        return compte;
    }



    public Compte getStatistiquesCompte(String numeroCompte) {
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte);
        Compte stats = new Compte();
        stats.setDernierDepot(compte.getDernierDepot());
        stats.setDernierRetrait(compte.getDernierRetrait());
        return stats;
    }


    public Compte getCompteByNumeroCompte(String numeroCompte) {
        return null;
    }

     public Compte Versement(String numeroCompte, Integer montant) {
        Compte compteToUpdate = compteRepository.findByNumeroCompte(numeroCompte);

        double nouveauSolde = compteToUpdate.getSolde() + montant;
        compteToUpdate.setSolde(nouveauSolde);
        compteRepository.save(compteToUpdate);

        return compteToUpdate;
    }

    public Compte Retrait(String numeroCompte, Integer montant) {
        Compte compteToUpdate = compteRepository.findByNumeroCompte(numeroCompte);

        double nouveauSolde = compteToUpdate.getSolde() - montant;
        compteToUpdate.setSolde(nouveauSolde);
        compteRepository.save(compteToUpdate);

        return compteToUpdate;
    }

    public Compte Virement(String numCompteSrc,String numCompteDest,double  montant) {
        Compte compteSrc = compteRepository.findByNumeroCompte(numCompteSrc);

        Compte compteDst = compteRepository.findByNumeroCompte(numCompteDest);


        double nouveauSoldeSrc = compteSrc.getSolde() - montant;
        double nouveauSoldeDst = compteDst.getSolde() + montant;

        compteSrc.setSolde(nouveauSoldeSrc);
        compteDst.setSolde(nouveauSoldeDst);
        compteRepository.save(compteSrc);
        compteRepository.save(compteDst);

        return compteSrc;
    }




    public void effectuerVirement(String numCompteSrc, String numCompteDest, double montant) {
        Compte compteSrc = compteRepository.findByNumeroCompte(numCompteSrc);
        Compte compteDest = compteRepository.findByNumeroCompte(numCompteDest);
        if(compteSrc == null || compteDest == null) {
            throw new IllegalArgumentException("Un ou plusieurs comptes n'existent pas");
        }
        if(compteSrc.getSolde() < montant) {
            throw new IllegalArgumentException("Solde insuffisant");
        }
        double nouveauSoldeSrc = compteSrc.getSolde() - montant;
        double nouveauSoldeDest = compteDest.getSolde() + montant;
        compteSrc.setSolde(nouveauSoldeSrc);
        compteDest.setSolde(nouveauSoldeDest);
        compteRepository.save(compteSrc);
        compteRepository.save(compteDest);
    }




}
