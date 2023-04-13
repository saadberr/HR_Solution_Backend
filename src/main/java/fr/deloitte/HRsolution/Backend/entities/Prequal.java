package fr.deloitte.HRsolution.Backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PREQUAL")
@Data @AllArgsConstructor @NoArgsConstructor
public class Prequal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date datePrequal;
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    private String niveauFR;
    private String niveauEN;
    private int sanet;
    private int psnet;
    private String preavis;
    private String avisRH;
    private String resultatPrequal;

    // Package/Avantages
    private int primeAnnuelle;
    private int primeProjet;
    private String autrePrimes;
    private String cimr;
    private String assurance;
    private String congesAnnuelles;
    private String modeTravail;
    private String modeSouhaite;
    private String contrat;

    public Prequal(Date datePrequal, String commentaire, String niveauFR, String niveauEN, String resultatPrequal){
        this.datePrequal = datePrequal;
        this.commentaire = commentaire;
        this.niveauFR = niveauFR;
        this.niveauEN = niveauEN;
        this.resultatPrequal = resultatPrequal;
    }


}
