package fr.deloitte.HRsolution.Backend.Repositories;

import fr.deloitte.HRsolution.Backend.Entities.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {
}
