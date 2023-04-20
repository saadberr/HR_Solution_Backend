package fr.deloitte.HRsolution.Backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OFFRE")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = StatutOffre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "offre_id", referencedColumnName = "id")
    private List<StatutOffre> statutOffres;
}
