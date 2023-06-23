package fr.deloitte.HRsolution.Backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ENTRETIEN")
@Data @AllArgsConstructor @NoArgsConstructor
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeEntretien;
    private LocalDateTime dateheureEntretien;
    private String etat;
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    @ElementCollection
    private List<String> recruteurs;


}
