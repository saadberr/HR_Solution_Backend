package fr.deloitte.HRsolution.Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STAFF")
@Data @AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String grade;

    @OneToMany(mappedBy = "staff")
    private List<Candidat> candidats;

}
