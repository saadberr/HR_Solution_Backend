package fr.deloitte.HRsolution.Backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STATUT_DE_CANDIDAT")
@Data @AllArgsConstructor @NoArgsConstructor
public class StatutCandidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    //@Temporal(TemporalType.TIMESTAMP)
    @Temporal(TemporalType.DATE)
    private Date dateStatut;

    /*@ManyToOne
    private Candidat candidat;

     */
}
