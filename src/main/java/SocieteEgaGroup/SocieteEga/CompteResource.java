package SocieteEgaGroup.SocieteEga;

import SocieteEgaGroup.SocieteEga.model.Compte;
import SocieteEgaGroup.SocieteEga.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Compte> getCompteByNumeroCompte(@PathVariable String numeroCompte) {
        Compte compte = compteService.getCompteByNumeroCompte(numeroCompte);
        if (compte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(compte);
    }

    @PostMapping("")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteService.saveCompte(compte);
        return ResponseEntity.created(URI.create("/api/comptes/" + savedCompte.getNumeroCompte())).body(savedCompte);
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
