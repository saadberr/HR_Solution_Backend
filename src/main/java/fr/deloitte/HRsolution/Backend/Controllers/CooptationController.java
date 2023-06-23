package fr.deloitte.HRsolution.Backend.Controllers;

import fr.deloitte.HRsolution.Backend.dto.CooptationListe;
import fr.deloitte.HRsolution.Backend.Entities.*;
import fr.deloitte.HRsolution.Backend.payloads.CooptationStatut;
import fr.deloitte.HRsolution.Backend.Repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CooptationController {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(path = "/cooptation")
    public List<CooptationListe> cooptationListe(){
        return candidatRepository.getCooptation();
    }

    @GetMapping(path = "/cooptation/{id}")
    public CooptationListe oneCooptationListe(@PathVariable(name = "id") Long id){
        return candidatRepository.getOneCooptation(id);
    }

    @PutMapping("/modifierCooptation/{id}")
    public ResponseEntity<Candidat> updateStatutCooptation(@PathVariable Long id, @RequestBody CooptationStatut request){

        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Find the cooptation
        List<Cooptation> cop = candidat.getCooptation();
        cop.get(0).setStatutCooptation(request.getIntValue());
        candidat.setCooptation(cop);

        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modifierFullCooptation/{id}")
    public ResponseEntity<Candidat> updateCooptation(@PathVariable Long id, @RequestBody Cooptation cooptation){

        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Find the cooptation
        List<Cooptation> cop = candidat.getCooptation();
        if(!cop.isEmpty()){
            cop.get(0).setNomCoopteur(cooptation.getNomCoopteur());
            cop.get(0).setPracticeCoopteur(cooptation.getPracticeCoopteur());
            cop.get(0).setMontant(cooptation.getMontant());
            cop.get(0).setDatePremierVers(cooptation.getDatePremierVers());
            cop.get(0).setDateDeuxiemeVers(cooptation.getDateDeuxiemeVers());

            candidat.setCooptation(cop);
        }

        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }

}
