package fr.deloitte.HRsolution.Backend.repositories;

import fr.deloitte.HRsolution.Backend.dto.CooptationListe;
import fr.deloitte.HRsolution.Backend.dto.KanbanResponse;
import fr.deloitte.HRsolution.Backend.dto.ListeResponse;
import fr.deloitte.HRsolution.Backend.dto.OffreListe;
import fr.deloitte.HRsolution.Backend.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {




    //@Query("SELECT new fr.deloitte.HRsolution.Backend.dto.ListeResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, latestPrequal.sanet, latestPrequal.psnet, latestPrequal.preavis) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.dateStatut = (SELECT MAX(s.dateStatut) FROM c.statuts s)")
    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.ListeResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, c.pourcentageAng, latestPrequal.sanet, latestPrequal.psnet, latestPrequal.preavis) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.id = (SELECT MAX(s.id) FROM c.statuts s) ORDER BY c.id DESC")
    public List<ListeResponse> getListe();

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.ListeResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, c.pourcentageAng, latestPrequal.sanet, latestPrequal.psnet, latestPrequal.preavis) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.id = (SELECT MAX(s.id) FROM c.statuts s) and c.id=:id")
    public ListeResponse getOneListe(@Param("id") Long id);

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.KanbanResponse(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.experience, c.source, c.dateSourcing, latestStatus.statut, latestStatus.dateStatut, latestPrequal.resultatPrequal, latestPrequal.datePrequal) FROM Candidat c LEFT JOIN c.prequals latestPrequal ON latestPrequal.datePrequal = (SELECT MAX(p.datePrequal) FROM c.prequals p) JOIN c.statuts latestStatus WHERE latestStatus.id = (SELECT MAX(s.id) FROM c.statuts s) ORDER BY c.id DESC")
    public List<KanbanResponse> getKanban();

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.OffreListe(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.dateSourcing, o.statut, o.raison, o.dateStatut, c.offre) FROM Candidat c JOIN c.offre.statutOffres o WHERE o.id = (SELECT MAX(o2.id) FROM c.offre.statutOffres o2) ORDER BY c.id DESC")
    //@Query("SELECT new fr.deloitte.HRsolution.Backend.dto.OffreListe(c.id, c.prenom, c.nom, c.email, c.telephone, c.pays, c.practice, c.specialite, c.grade, c.offre) FROM Candidat c WHERE c.offre IS NOT NULL")
    public List<OffreListe> getOffre();

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.CooptationListe(c.id, c.prenom, c.nom, c.email, c.telephone, c.grade, c.practice, cop.nomCoopteur, cop.practiceCoopteur, cop.montant, cop.dateIntegration, cop.datePremierVers, cop.dateDeuxiemeVers, cop.statutCooptation) FROM Candidat c JOIN c.cooptation cop JOIN c.offre.statutOffres o WHERE o.id = (SELECT MAX(o2.id) FROM c.offre.statutOffres o2) AND o.statut LIKE 'Intégration%' ORDER BY c.id DESC")
    public List<CooptationListe> getCooptation();

    @Query("SELECT new fr.deloitte.HRsolution.Backend.dto.CooptationListe(c.id, c.prenom, c.nom, c.email, c.telephone, c.grade, c.practice, cop.nomCoopteur, cop.practiceCoopteur, cop.montant, cop.dateIntegration, cop.datePremierVers, cop.dateDeuxiemeVers, cop.statutCooptation) FROM Candidat c JOIN c.cooptation cop JOIN c.offre.statutOffres o WHERE o.id = (SELECT MAX(o2.id) FROM c.offre.statutOffres o2) AND o.statut LIKE 'Intégration%' AND c.id=:id")
    public CooptationListe getOneCooptation(@Param("id") Long id);

    Optional<Candidat> findByEmail(String candidatEmail);
}
