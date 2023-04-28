package TPISIGLSI.CrudApi.Controller;

import TPISIGLSI.CrudApi.Entity.Compte;
import TPISIGLSI.CrudApi.Service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{id}")
    public Compte getCompteById(@PathVariable("id") Long id) {
        return compteService.getCompteById(id);
    }

    @PostMapping
    public Compte createCompte(@RequestBody Compte compte) {
        return compteService.createCompte(compte);
    }

    @PutMapping("/{id}")
    public Compte updateCompte(@PathVariable("id") Long id, @RequestBody Compte compte) {
        return compteService.updateCompte(id, compte);
    }

    @DeleteMapping("/{id}")
    public void deleteCompte(@PathVariable("id") Long id) {
        compteService.deleteCompte(id);
    }

    // Endpoint pour faire un versement
    @PostMapping("/{numeroCompte}/versement")
    public void faireVersement(@PathVariable String numeroCompte, @RequestParam double montant) {
        compteService.faireVersement(numeroCompte, montant);
    }

    // Endpoint pour faire un retrait sur un compte
    @PostMapping("/{numeroCompte}/retrait")
    public void faireRetrait(@PathVariable String numeroCompte, @RequestParam double montant) {
        compteService.faireRetrait(numeroCompte, montant);
    }

}

