package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.dto.KanbanResponse;
import fr.deloitte.HRsolution.Backend.dto.OffreListe;
import fr.deloitte.HRsolution.Backend.entities.*;
import fr.deloitte.HRsolution.Backend.payloads.OffrePayload;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OffreRest {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(path = "/offre")
    public List<OffreListe> offreListe(){
        return candidatRepository.getOffre();
    }

    @PutMapping("/modifierOffre/{id}")
    public ResponseEntity<Candidat> updatePourcentageAng(@PathVariable Long id, @RequestBody OffrePayload payload){
        // access the objects from the payload
        StatutOffre statut = payload.getStatut();
        Integration integration = payload.getIntegration();

        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Find the offer
        Offre offre = candidat.getOffre();
        // Update the statuts of the Candidat
        List<StatutOffre> exOffres = offre.getStatutOffres();
        exOffres.add(new StatutOffre(null, statut.getStatut(), statut.getRaison(), statut.getDateStatut()));
        offre.setStatutOffres(exOffres);
        candidat.setOffre(offre);
        // Set the integration
        if(integration == null){
            offre.setIntegration(null);
        }
        else{
            offre.setIntegration(new Integration(null, integration.getDateIntegration()));
            // Check if the candidate source is a cooptation to update its dateIntegration field
            if(candidat.getSource().equals("Cooptation")){
                Cooptation cop = candidat.getCooptation();
                cop.setDateIntegration(integration.getDateIntegration());
                cop.setStatutCooptation(0);
                candidat.setCooptation(cop);
            }
        }
        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        // Return the updated Candidat
        return ResponseEntity.ok().build();
    }
}
