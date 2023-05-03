package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.dto.CooptationListe;
import fr.deloitte.HRsolution.Backend.entities.*;
import fr.deloitte.HRsolution.Backend.payloads.CooptationStatut;
import fr.deloitte.HRsolution.Backend.payloads.OffrePayload;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CooptationRest {

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
    public ResponseEntity<Candidat> updatePourcentageAng(@PathVariable Long id, @RequestBody CooptationStatut request){

        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Find the cooptation
        Cooptation cop = candidat.getCooptation();
        cop.setStatutCooptation(request.getIntValue());
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
        Cooptation cop = candidat.getCooptation();

        cop.setNomCoopteur(cooptation.getNomCoopteur());
        cop.setPracticeCoopteur(cooptation.getPracticeCoopteur());
        cop.setMontant(cooptation.getMontant());
        cop.setDatePremierVers(cooptation.getDatePremierVers());
        cop.setDateDeuxiemeVers(cooptation.getDateDeuxiemeVers());

        candidat.setCooptation(cop);
        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }

}
