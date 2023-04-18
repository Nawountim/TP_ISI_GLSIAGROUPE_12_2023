package SocieteEgaGroup.SocieteEga.model;

public class Retrait {
    private String numeroCompte;
    private double montant;

    public Retrait(String numeroCompte, double montant) {
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
