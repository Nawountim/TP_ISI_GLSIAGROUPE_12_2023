package SocieteEgaGroup.SocieteEga;

import SocieteEgaGroup.SocieteEga.model.Compte;
import SocieteEgaGroup.SocieteEga.model.Retrait;
import SocieteEgaGroup.SocieteEga.model.Versement;
import SocieteEgaGroup.SocieteEga.model.Virement;
import SocieteEgaGroup.SocieteEga.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comptes")
public class CompteResource {

    @Autowired
    private CompteService compteService;

    @GetMapping("")
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{numeroCompte}")
    public ResponseEntity<Compte> getCompteByNumero(@PathVariable String numeroCompte) {
        Compte compte = compteService.getCompteByNumero(numeroCompte);
        if (compte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(compte);
    }

    @GetMapping("/statistiques/moyenneSolde")
    public String getMoyenneSolde() {
        List<Compte> comptes = compteService.getAllComptes();
        double totalSolde = 0;
        for (Compte compte : comptes) {
            totalSolde += compte.getSolde();
        }
        return "Moyenne des soldes: " + totalSolde / comptes.size() + "Francs";
    }

    @GetMapping("/statistiques/nombreComptes")
    public String getNombreComptes() {
        List<Compte> comptes = compteService.getAllComptes();
        return "Il y a actuellement " + comptes.size() + " comptes dans le système.";
    }

    @GetMapping("/statistiques/compteMinSolde")
    public Map<String, String> getCompteWithMinSolde() {
        List<Compte> comptes = compteService.getAllComptes();
        Compte compteMinSolde = null;
        double soldeMin = Double.MAX_VALUE;
        for (Compte compte : comptes) {
            double solde = compte.getSolde();
            if (solde < soldeMin) {
                soldeMin = solde;
                compteMinSolde = compte;
            }
        }
        Map<String, String> result = new HashMap<>();
        result.put("numero", compteMinSolde.getNumeroCompte());
        result.put("proprietaire", compteMinSolde.getClientName());
        result.put("solde", String.valueOf(compteMinSolde.getSolde()));
        return result;
    }


    @GetMapping("/statistiques/compteMaxSolde")
    public Map<String, String> getCompteWithMaxSolde() {
        List<Compte> comptes = compteService.getAllComptes();
        Compte compteMaxSolde = null;
        double soldeMax = -1;
        for (Compte compte : comptes) {
            double solde = compte.getSolde();
            if (solde > soldeMax) {
                soldeMax = solde;
                compteMaxSolde = compte;
            }
        }
        Map<String, String> result = new HashMap<>();
        result.put("numero", compteMaxSolde.getNumeroCompte());
        result.put("proprietaire", compteMaxSolde.getClientName());
        result.put("solde", Double.toString(soldeMax));
        return result;
    }




    @PostMapping("")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteService.createCompte(compte);
        return ResponseEntity.created(URI.create("/api/comptes/" + savedCompte.getNumeroCompte())).body(savedCompte);
    }

    @PostMapping("/versement")
    public ResponseEntity<String> effectuerVersement(@RequestBody Versement versement) {
        String numeroCompte = versement.getNumeroCompte();
        double montant = versement.getMontant();
        if (montant <= 0) {
            return ResponseEntity.badRequest().body(" Montant invalide !");
        }
        Compte compte = compteService.getCompteByNumero(numeroCompte);
        if (compte != null) {
            double nouveauSolde = compte.getSolde() + montant;
            System.out.println("Montant du versement : " + montant); // Trace de débogage
            compte.setSolde(nouveauSolde);
            compte.setDernierDepot(LocalDate.now());
            compteService.updateSoldeCompte(compte);
            return ResponseEntity.ok("Le versement a été effectué avec succès !");
        } else {
            return ResponseEntity.badRequest().body("Le compte n'existe pas !");
        }
    }

    @PostMapping("/retrait")
    public ResponseEntity<String> effectuerRetrait(@RequestBody Versement versement) {
        String numeroCompte = versement.getNumeroCompte();
        double montant = versement.getMontant();
        Compte compte = compteService.getCompteByNumero(numeroCompte);
        if (compte != null) {
            double soldeActuel = compte.getSolde();
            if (soldeActuel >= montant) {
                double nouveauSolde = soldeActuel - montant;
                System.out.println("Montant du retrait : " + montant); // Trace de débogage
                compte.setSolde(nouveauSolde);
                compte.setDernierRetrait(LocalDate.now());
                compteService.updateSoldeCompte(compte);
                return ResponseEntity.ok("Le retrait a été effectué avec succès !");
            } else {
                return ResponseEntity.badRequest().body("Le solde du compte est insuffisant pour effectuer le retrait !");
            }
        } else {
            return ResponseEntity.badRequest().body("Le compte n'existe pas !");
        }
    }


    @PostMapping("/virement")
    public ResponseEntity<String> effectuerVirement(@RequestBody Virement virement) {
        String compteSource = virement.getCompteSource();
        String compteDest = virement.getCompteDest();
        double montant = virement.getMontant();
        Compte compteSourceObj = compteService.getCompteByNumero(compteSource);
        Compte compteDestObj = compteService.getCompteByNumero(compteDest);
        if (compteSourceObj != null && compteDestObj != null) {
            double soldeActuel = compteSourceObj.getSolde();
            if (montant <= 0) {
                return ResponseEntity.badRequest().body("Le montant du virement doit être positif !");
            } else if (soldeActuel >= montant) {
                double nouveauSoldeSource = soldeActuel - montant;
                compteSourceObj.setSolde(nouveauSoldeSource);
                double nouveauSoldeDest = compteDestObj.getSolde() + montant;
                compteDestObj.setSolde(nouveauSoldeDest);
                compteService.updateSoldeCompte(compteSourceObj);
                compteService.updateSoldeCompte(compteDestObj);
                String message = String.format("Virement de %.2f effectué avec succès à %s, propriétaire du numéro de compte %s !", montant, compteDestObj.getClientName(), compteDestObj.getNumeroCompte());
                return ResponseEntity.ok(message);
            } else {
                return ResponseEntity.badRequest().body("Le solde du compte source est insuffisant !");
            }
        } else {
            return ResponseEntity.badRequest().body("L'un des comptes n'existe pas !");
        }
    }







    @PutMapping("/{numeroCompte}")
    public ResponseEntity<Compte> updateCompte(@PathVariable String numeroCompte, @RequestBody Compte compte) {
        Compte updatedCompte = compteService.updateCompte(numeroCompte, compte);
        if (updatedCompte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedCompte);
    }

    @DeleteMapping("/{numeroCompte}")
    public ResponseEntity<Void> deleteCompte(@PathVariable String numeroCompte) {
        boolean isDeleted = compteService.deleteCompte(Long.valueOf(numeroCompte));
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
