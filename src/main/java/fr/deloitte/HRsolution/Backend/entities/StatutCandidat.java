package fr.deloitte.HRsolution.Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STATUT_DE_CANDIDAT")
@Data @AllArgsConstructor
public class StatutCandidat {

    private String statut;
    @Temporal(TemporalType.DATE)
    private Date dateStatut;
}
