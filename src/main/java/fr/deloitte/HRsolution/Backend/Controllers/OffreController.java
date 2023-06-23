package fr.deloitte.HRsolution.Backend.Controllers;

import fr.deloitte.HRsolution.Backend.dto.OffreListe;
import fr.deloitte.HRsolution.Backend.Entities.*;
import fr.deloitte.HRsolution.Backend.payloads.OffrePayload;
import fr.deloitte.HRsolution.Backend.Repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OffreController {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(path = "/offre")
    public List<OffreListe> offreListe(){
        return candidatRepository.getOffre();
    }

    @PutMapping("/modifierOffre/{id}")
    public ResponseEntity<Candidat> updateOffre(@PathVariable Long id, @RequestBody OffrePayload payload){
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
                List<Cooptation> cop = candidat.getCooptation();
                if(!cop.isEmpty()){
                    // we will update information related to the first coopteur added, he's the one who's going benifit from the cooptation reward
                    cop.get(0).setDateIntegration(integration.getDateIntegration());
//                    cop.get(0).setStatutCooptation(0);
                    candidat.setCooptation(cop);
                }

            }
        }

        List<StatutCandidat> status = candidat.getStatuts();
        status.add(new StatutCandidat(null, statut.getStatut(), new Date()));
        candidat.setStatuts(status);

        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        // Return the updated Candidat
        return ResponseEntity.ok().build();
    }
}
