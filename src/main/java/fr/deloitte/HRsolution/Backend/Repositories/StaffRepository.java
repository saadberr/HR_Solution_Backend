package fr.deloitte.HRsolution.Backend.Repositories;

import fr.deloitte.HRsolution.Backend.Entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT DISTINCT s.id, s.nom, s.prenom ,s.grade FROM Staff s")
    List<Object[]> findDistinctIdAndNomAndPrenomAndGrade();
    Staff findByNomAndPrenom(String nom, String prenom);

    Staff findByNom(String nom);

    Staff findByPrenom(String prenom);
}
