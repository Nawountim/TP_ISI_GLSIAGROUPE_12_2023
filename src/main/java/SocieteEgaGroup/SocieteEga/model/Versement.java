package SocieteEgaGroup.SocieteEga.model;

public class Versement {
    private String numeroCompte;
    private double montant;

    public Versement(String numeroCompte, double montant) {
        this.numeroCompte = numeroCompte;
        this.montant = montant;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
