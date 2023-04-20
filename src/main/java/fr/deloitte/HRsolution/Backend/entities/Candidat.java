package fr.deloitte.HRsolution.Backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CANDIDAT")
@Data @AllArgsConstructor @NoArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String pays;
    private String nationalite;
    private String genre;

    private String ecole;
    private String diplome;
    private int anneeDiplome;
    private String entActuelle;
    private String grade;
    private String experience;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateSourcing;
    private String source;
    private String practice;
    private String specialite;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "staff_id",referencedColumnName = "id" )
    private Staff staff;

    @OneToMany(targetEntity = StatutCandidat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidat_id", referencedColumnName = "id")
    private List<StatutCandidat> statuts;

    @OneToMany(targetEntity = Document.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidat_id", referencedColumnName = "id")
    private List<Document> documents;

    @OneToMany(targetEntity = Prequal.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidat_id", referencedColumnName = "id")
    private List<Prequal> prequals;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offre_id", referencedColumnName = "id")
    private Offre offre;


}
