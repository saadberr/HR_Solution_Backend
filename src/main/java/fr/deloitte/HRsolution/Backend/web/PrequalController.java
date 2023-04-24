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
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/modifierPrequal/{id}&{pid}")
    public  ResponseEntity<Candidat> updateprequals(@PathVariable Long id, @PathVariable Long pid, @RequestBody Prequal newprequal){
        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();

        // Update the prequal of the Candidat
        List<Prequal> existingPrequal = candidat.getPrequals();
        System.out.println(existingPrequal);
        existingPrequal.forEach(prequal -> {
            System.out.println(pid);
            System.out.println(prequal.getId());
            if(prequal.getId().equals(pid)){
                System.out.println(newprequal);
                System.out.println(prequal);
                prequal.setCommentaire(newprequal.getCommentaire());
                prequal.setNiveauFR(newprequal.getNiveauFR());
                prequal.setNiveauEN(newprequal.getNiveauEN());
                prequal.setTypeContratSaPs(newprequal.getTypeContratSaPs());
                prequal.setSanet(newprequal.getSanet());
                prequal.setPsnet(newprequal.getPsnet());
                prequal.setPreavis(newprequal.getPreavis());
                prequal.setPrimePerformance(newprequal.getPrimePerformance());
                prequal.setTreizieme(newprequal.getTreizieme());
                prequal.setPrimeProjet(newprequal.getPrimeProjet());
                prequal.setAutrePrimes(newprequal.getAutrePrimes());
                prequal.setModeTravail(newprequal.getModeTravail());
                prequal.setAssurance(newprequal.getAssurance());
                prequal.setCimr(newprequal.getCimr());
                prequal.setCongesAnnuelles(newprequal.getCongesAnnuelles());
                prequal.setModeSouhaite(newprequal.getModeSouhaite());
                prequal.setContrat(newprequal.getContrat());
                prequal.setAvisRH(newprequal.getAvisRH());
                prequal.setResultatPrequal(newprequal.getResultatPrequal());
                prequal.setReasons(newprequal.getReasons());
            }
        });

        candidat.setPrequals(existingPrequal);
        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        // Return the updated Candidat
        return ResponseEntity.ok().build();
    }
}
