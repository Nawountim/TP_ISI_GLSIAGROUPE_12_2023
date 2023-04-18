package SocieteEgaGroup.SocieteEga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;
import java.util.Random;

@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @Column(name = "numero_compte")
    private String numeroCompte;

    @Column(name = "solde")
    private double solde;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_dernier_depot")
    private LocalDate dernierDepot;

    @Column(name = "date_dernier_retrait")
    private LocalDate dernierRetrait;

    @ManyToOne
    @JoinColumn(name = "id_type_compte")
    private TypeCompte typeCompte;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    // Constructeur par défaut
    public Compte() {
    }

    // Constructeur avec paramètres
    public Compte(String numeroCompte, double solde, LocalDate dateCreation, TypeCompte typeCompte, Client client) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.typeCompte = typeCompte;
        this.client = client;
    }

    // Getters et setters
    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public LocalDate getDernierDepot() {
        return dernierDepot;
    }

    public LocalDate getDernierRetrait() {
        return dernierRetrait;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDernierDepot(LocalDate dernierDepot) {
        this.dernierDepot = dernierDepot;
    }

    public void setDernierRetrait(LocalDate dernierRetrait) {
        this.dernierRetrait = dernierRetrait;
    }


    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // Method to generate random account number
    private String generateNumeroCompte() {
        StringBuilder sb = new StringBuilder();
        String alphaNumericChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            sb.append(alphaNumericChars.charAt(random.nextInt(alphaNumericChars.length())));
        }

        sb.append(this.dateCreation.getYear());

        return sb.toString();
    }
    public String getClientName() {
        return this.client.getNom();
    }

}


