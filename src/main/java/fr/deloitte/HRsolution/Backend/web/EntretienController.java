package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.Entretien;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EntretienController {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private EntretienRepository entretienRepository;



    @PutMapping(path = "/planifierentretien/{id}")
    public ResponseEntity<Entretien> createentretien(@PathVariable(name = "id") Long id, @RequestBody Entretien newentretien){
        // Get the candidate
        Optional<Candidat> optionalCandidate = candidatRepository.findById(id);
        Candidat candidat = optionalCandidate.get();

        // Get entretiens list and add the new entretien to it
        List<Entretien> entretiens = candidat.getEntretiens();
        entretiens.add(newentretien);
        candidat.setEntretiens(entretiens);

        // Set interviewers list for the new entretien
        List<String> interviewers = newentretien.getRecruteurs();
        newentretien.setRecruteurs(interviewers);

        // Save changes
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }

}
