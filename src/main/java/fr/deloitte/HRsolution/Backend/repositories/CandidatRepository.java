package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.dto.KanbanResponse;
import fr.deloitte.HRsolution.Backend.dto.ListeResponse;
import fr.deloitte.HRsolution.Backend.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {


    //@Query("SELECT new fr.deloitte.HRsolution.Backend.dto.ListeResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, latestPrequal.sanet, latestPrequal.psnet, latestPrequal.preavis) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.dateStatut = (SELECT MAX(s.dateStatut) FROM c.statuts s)")
    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.ListeResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, latestPrequal.sanet, latestPrequal.psnet, latestPrequal.preavis) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.id = (SELECT MAX(s.id) FROM c.statuts s)")
    public List<ListeResponse> getListe();

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.ListeResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, latestPrequal.sanet, latestPrequal.psnet, latestPrequal.preavis) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.id = (SELECT MAX(s.id) FROM c.statuts s) and c.id=:id")
    public ListeResponse getOneListe(@Param("id") Long id);

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.KanbanResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, latestPrequal.resultatPrequal, latestPrequal.datePrequal) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.id = (SELECT MAX(s.id) FROM c.statuts s)")
    public List<KanbanResponse> getKanban();

}
