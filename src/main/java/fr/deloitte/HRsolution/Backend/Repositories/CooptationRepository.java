package fr.deloitte.HRsolution.Backend.Repositories;

import fr.deloitte.HRsolution.Backend.Entities.Cooptation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CooptationRepository extends JpaRepository<Cooptation, Long> {

    Cooptation findByNomCoopteurAndPracticeCoopteur(String nomCoopteur, String practiceCoopteur);
}
