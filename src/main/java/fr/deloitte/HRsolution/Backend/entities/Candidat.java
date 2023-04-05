package fr.deloitte.HRsolution.Backend.entities;

import fr.deloitte.HRsolution.Backend.enums.Genre;
import fr.deloitte.HRsolution.Backend.enums.Practice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CANDIDAT")
@Data @AllArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private Long telephone;
    private String email;
    private String pays;
    private String nationalite;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String ecole;
    private String diplome;
    private Long anneeDiplome;
    private String eActuelle;
    private String grade;

    @Enumerated(EnumType.STRING)
    private String experience;

    @Temporal(TemporalType.DATE)
    private Date dateSourcing;

    @Enumerated(EnumType.STRING)
    private String source;

    @Enumerated(EnumType.STRING)
    private Practice practice;

}
