package fr.deloitte.HRsolution.Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STATUT_OFFRE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatutOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private String raison;
    //@Temporal(TemporalType.TIMESTAMP)
    @Temporal(TemporalType.DATE)
    private Date dateStatut;
}
