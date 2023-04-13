package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.entities.Prequal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrequalRepository extends JpaRepository<Prequal, Long> {


}
