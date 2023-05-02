package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.dto.CooptationListe;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CooptationRest {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(path = "/cooptation")
    public List<CooptationListe> cooptationListe(){
        return candidatRepository.getCooptation();
    }

}
