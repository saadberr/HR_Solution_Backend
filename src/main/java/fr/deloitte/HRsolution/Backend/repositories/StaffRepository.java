package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
