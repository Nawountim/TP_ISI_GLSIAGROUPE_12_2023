package SocieteEgaGroup.SocieteEga.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "datenaissance")
    private LocalDate dateNaissance;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "courriel")
    private String courriel;

    @Column(name = "nationalite")
    private String nationalite;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getNationalite() {
        return nationalite;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public  Client() {

    }

    public Client(String nom, String prenom, LocalDate dateNaissance, String sexe, String adresse, String telephone, String courriel, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.telephone = telephone;
        this.courriel = courriel;
        this.nationalite = nationalite;
    }
}

