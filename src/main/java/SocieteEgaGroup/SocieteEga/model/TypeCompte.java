package SocieteEgaGroup.SocieteEga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table(name = "type_compte")
public class TypeCompte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    // constructeurs

    public TypeCompte() {}

    public TypeCompte(String nom) {
        this.nom = nom;
    }

    // getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

