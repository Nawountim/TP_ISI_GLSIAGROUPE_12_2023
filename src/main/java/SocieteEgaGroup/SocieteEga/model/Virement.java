package SocieteEgaGroup.SocieteEga.model;

public class Virement {
    private String compteSource;
    private String compteDestination;
    private double montant;

    public Virement(String compteSource, String compteDestination, double montant) {
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
        this.montant = montant;
    }

    public String getCompteSource() {
        return compteSource;
    }
    public String getCompteDest() {
        return compteDestination;
    }

    public void setCompteSource(String compteSource) {
        this.compteSource = compteSource;
    }

    public String getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(String compteDestination) {
        this.compteDestination = compteDestination;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}