package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.dto.ListeResponse;
import fr.deloitte.HRsolution.Backend.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT DISTINCT s.id, s.nom, s.prenom ,s.grade FROM Staff s")
    List<Object[]> findDistinctIdAndNomAndPrenomAndGrade();
    Staff findByNomAndPrenom(String nom, String prenom);
}
