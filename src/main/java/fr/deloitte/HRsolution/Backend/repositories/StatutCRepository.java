package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.entities.StatutCandidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutCRepository extends JpaRepository<StatutCandidat, Long> {

}
