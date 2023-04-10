package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.dto.KanbanResponse;
import fr.deloitte.HRsolution.Backend.dto.ListeResponse;
import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CandidatRest {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(path = "/candidat")
    public List<Candidat> listCandidats(){
        return candidatRepository.findAll();
    }

    @GetMapping(path = "/candidat/{id}")
    public Optional<Candidat> getCandidat(@PathVariable(name = "id") Long id){
        return candidatRepository.findById(id);
    }

    @GetMapping(path = "/liste")
    public List<ListeResponse> liste(){
        return candidatRepository.getListe();
    }

    @GetMapping(path = "/liste/{id}")
    public ListeResponse ListeOne(@PathVariable(name = "id") Long id){
        return candidatRepository.getOneListe(id);
    }

    @GetMapping(path = "/kanban")
    public List<KanbanResponse> kanban(){
        return candidatRepository.getKanban();
    }
}
