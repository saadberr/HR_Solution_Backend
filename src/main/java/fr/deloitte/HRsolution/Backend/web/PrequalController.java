package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.Prequal;
import fr.deloitte.HRsolution.Backend.entities.StatutCandidat;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.PrequalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PrequalController {
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private PrequalRepository prequalRepository;

    @GetMapping(path = "/prequalification/{id}")
    public ResponseEntity<List<Prequal>> getPrequalsByCandidateId(@PathVariable Long id) {
        Optional<Candidat> optionalCandidate = candidatRepository.findById(id);
        Candidat candidate = optionalCandidate.get();
        List<Prequal> prequals = candidate.getPrequals();
        return ResponseEntity.ok(new ArrayList<>(prequals));
    }
    @GetMapping(path = "/prequalifs")
    public List<Prequal> listPrequals(){
            return prequalRepository.findAll();
    }

    @PutMapping(path = "/ajouterprequal/{id}")
    public ResponseEntity<Prequal> createprequal(@PathVariable(name = "id") Long id, @RequestBody Prequal newprequal){
        // Get the candidate
        Optional<Candidat> optionalCandidate = candidatRepository.findById(id);
        Candidat candidat = optionalCandidate.get();

        // Get prequals list and add the new prequal to it
        List<Prequal> prequalifs = candidat.getPrequals();
        prequalifs.add(newprequal);
        candidat.setPrequals(prequalifs);

        // Get status list and add "préqualifié" to it
        List<StatutCandidat> status = candidat.getStatuts();
        status.add(new StatutCandidat(null, newprequal.getResultatPrequal(), new Date()));
        candidat.setStatuts(status);

        // Save changes
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }
}
