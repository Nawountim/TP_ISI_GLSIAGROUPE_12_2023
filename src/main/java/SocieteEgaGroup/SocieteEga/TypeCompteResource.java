package SocieteEgaGroup.SocieteEga;

import SocieteEgaGroup.SocieteEga.model.TypeCompte;
import SocieteEgaGroup.SocieteEga.service.TypeCompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/typecomptes")
public class TypeCompteResource {

    @Autowired
    private TypeCompteService typeCompteService;

    @GetMapping("")
    public List<TypeCompte> getAllTypeComptes() {
        return typeCompteService.getAllTypeComptes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCompte> getTypeCompteById(@PathVariable Long id) {
        TypeCompte typeCompte = typeCompteService.getTypeCompteById(id);
        if (typeCompte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(typeCompte);
    }

    @PostMapping("")
    public ResponseEntity<TypeCompte> createTypeCompte(@RequestBody TypeCompte typeCompte) {
        TypeCompte savedTypeCompte = typeCompteService.saveTypeCompte(typeCompte);
        return ResponseEntity.created(URI.create("/api/typecomptes/" + savedTypeCompte.getId())).body(savedTypeCompte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeCompte(@PathVariable Long id) {
        TypeCompte typeCompte = typeCompteService.getTypeCompteById(id);
        if (typeCompte == null) {
            return ResponseEntity.notFound().build();
        }
        typeCompteService.deleteTypeCompte(id);
        return ResponseEntity.noContent().build();
    }
}

