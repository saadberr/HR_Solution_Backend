package fr.deloitte.HRsolution.Backend.Repositories;

import fr.deloitte.HRsolution.Backend.Entities.StatutCandidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutCRepository extends JpaRepository<StatutCandidat, Long> {

}
