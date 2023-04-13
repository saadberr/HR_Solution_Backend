package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.Prequal;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.PrequalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    @PostMapping(path = "/ajouterprequal/{id}")
    public ResponseEntity<Prequal> createprequal(@PathVariable(name = "id") Long id, @RequestBody Prequal newprequal){
        Optional<Candidat> optionalCandidate = candidatRepository.findById(id);
        Candidat candidat = optionalCandidate.get();
        List<Prequal> prequalifs=candidat.getPrequals();
        prequalifs.add(newprequal);
        candidat.setPrequals(prequalifs);
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }
}
