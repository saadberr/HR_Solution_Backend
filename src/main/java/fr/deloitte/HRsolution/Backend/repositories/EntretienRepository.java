package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.entities.Entretien;
import fr.deloitte.HRsolution.Backend.entities.Prequal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {
}
