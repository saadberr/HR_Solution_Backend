package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.Cooptation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CooptationRepository extends JpaRepository<Cooptation, Long> {

    Cooptation findByNomCoopteurAndPracticeCoopteur(String nomCoopteur, String practiceCoopteur);
}
