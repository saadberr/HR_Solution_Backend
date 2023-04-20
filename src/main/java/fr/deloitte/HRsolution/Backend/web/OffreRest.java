package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.dto.KanbanResponse;
import fr.deloitte.HRsolution.Backend.dto.OffreListe;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OffreRest {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(path = "/offre")
    public List<OffreListe> offreListe(){
        return candidatRepository.getOffre();
    }
}
