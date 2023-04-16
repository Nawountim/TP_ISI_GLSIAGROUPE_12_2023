package SocieteEgaGroup.SocieteEga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_compte")
    private String numeroCompte;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "type_compte_id")
    private TypeCompte typeCompte;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "solde")
    private double solde;

    // Getters and setters
}

