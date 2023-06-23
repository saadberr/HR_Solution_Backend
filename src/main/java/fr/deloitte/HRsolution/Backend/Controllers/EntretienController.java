package fr.deloitte.HRsolution.Backend.Controllers;

import fr.deloitte.HRsolution.Backend.Entities.Candidat;
import fr.deloitte.HRsolution.Backend.Entities.Entretien;
import fr.deloitte.HRsolution.Backend.Entities.StatutCandidat;
import fr.deloitte.HRsolution.Backend.Repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.Repositories.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PutMapping(path = "/planifierentretienparemail")
    public ResponseEntity<Entretien> createentretienparemail(@RequestBody Entretien newentretien,@RequestParam String candidatEmail){
        // Get the candidate
        Optional<Candidat> optionalCandidate = candidatRepository.findByEmail(candidatEmail);
        Candidat candidat = optionalCandidate.get();

        // Get entretiens list and add the new entretien to it
        List<Entretien> entretiens = candidat.getEntretiens();
        entretiens.add(newentretien);
        candidat.setEntretiens(entretiens);

        // Set interviewers list for the new entretien
        List<String> interviewers = newentretien.getRecruteurs();
        newentretien.setRecruteurs(interviewers);

        if(newentretien.getTypeEntretien()!=null){
            // Get status list and add the interview status to it
            List<StatutCandidat> status = candidat.getStatuts();
            String statutToUpdate= "Entretien planifié"+" "+ newentretien.getTypeEntretien();
            status.add(new StatutCandidat(null, statutToUpdate, new Date()));
            candidat.setStatuts(status);
        }

        // Save changes
        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modifierentretien/{id}&{pid}")
    public ResponseEntity<Candidat> updateStatutEntetien(@PathVariable Long id,@PathVariable Long pid, @RequestParam String etatEntretien, @RequestParam String commentaireEntretien ){
        Optional<Candidat> candidatToUpdate = candidatRepository.findById(id);
        Candidat candidat= candidatToUpdate.get();
        List<Entretien> existingEntretiens=candidat.getEntretiens();
        existingEntretiens.forEach(entretien -> {
            if (entretien.getId().equals(pid)){
                entretien.setEtat(etatEntretien);
                entretien.setCommentaire(commentaireEntretien);
            }
        });
        candidat.setEntretiens(existingEntretiens);

        // Get status list and add the interview status to it
        List<StatutCandidat> status = candidat.getStatuts();
        status.add(new StatutCandidat(null, etatEntretien, new Date()));
        candidat.setStatuts(status);

        candidatRepository.save(candidat);
        return ResponseEntity.ok().build();

    }
}
