package fr.deloitte.HRsolution.Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PACKAGE")
@Data @AllArgsConstructor
public class PackageAvantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int primeAnnuelle;
    private int primeProjet;
    private String autrePrimes;
    private String cimr;
    private String assurance;
    private String congesAnnuelles;
    private String modeTravail;
    private String contrat;

    @OneToOne
    private Prequal prequal;


}
