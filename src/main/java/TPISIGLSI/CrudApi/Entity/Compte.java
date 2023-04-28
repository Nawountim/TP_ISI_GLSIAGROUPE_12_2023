package TPISIGLSI.CrudApi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

 enum TypeCompte {
    COURANT,
    EPARGNE
}
@Entity
public class Compte {

    private String numeroCompte;
    private TypeCompte typeCompte;
    private String dateCreation;
    private double solde;
    private Client proprietaire;

    @ManyToOne(fetch = FetchType.LAZY.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    public Compte(TypeCompte typeCompte, String dateCreation, Client proprietaire) {
        this.numeroCompte = genererNumeroCompte();
        this.typeCompte = typeCompte;
        this.dateCreation = dateCreation;
        this.solde = 0.0;
        this.proprietaire = proprietaire;
    }

    private String genererNumeroCompte() {
        return UUID.randomUUID().toString().substring(0, 5) + this.dateCreation;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    // Méthode pour faire un versement
    public void faireVersement(String numeroCompte, double montant) {
        if (this.numeroCompte.equals(numeroCompte)) {
            if (montant > 0) {
                this.solde += montant;
                System.out.println("Versement de " + montant + " effectué avec succès sur le compte " + numeroCompte);
            } else {
                System.out.println("Le montant doit être supérieur à 0.");
            }
        } else {
            System.out.println("Le numéro de compte ne correspond pas.");
        }
    }

}
