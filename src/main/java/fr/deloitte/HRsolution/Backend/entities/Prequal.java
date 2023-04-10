package fr.deloitte.HRsolution.Backend.entities;

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

    @Temporal(TemporalType.DATE)
    private Date datePrequal;
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    private String niveauFR;
    private String niveauEN;

    private int SAnet;
    private int PSnet;

    private String preavis;
    private String avisRH;
    private String resultatPrequal;

    public Prequal(Date datePrequal, String commentaire, String niveauFR, String niveauEN, String resultatPrequal){
        this.datePrequal = datePrequal;
        this.commentaire = commentaire;
        this.niveauFR = niveauFR;
        this.niveauEN = niveauEN;
        this.resultatPrequal = resultatPrequal;
    }

    //@OneToOne(mappedBy = "prequal")
    //private PackageAvantage avantage;

    /*
    @ManyToOne
    private Candidat candidat;

     */




}
