package fr.deloitte.HRsolution.Backend.web;

import fr.deloitte.HRsolution.Backend.dto.KanbanResponse;
import fr.deloitte.HRsolution.Backend.dto.ListeResponse;
import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.Document;
import fr.deloitte.HRsolution.Backend.entities.Staff;
import fr.deloitte.HRsolution.Backend.entities.StatutCandidat;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.StaffRepository;
import fr.deloitte.HRsolution.Backend.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CandidatRest {

    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CandidatService candidatService;

    @GetMapping(path = "/candidat")
    public List<Candidat> listCandidats(){
        return candidatRepository.findAll();
    }
    @GetMapping(path = "/staff")
    public List<Object[]> listStaffs(){
        if (staffRepository != null) {
            return staffRepository.findDistinctIdAndNomAndPrenomAndGrade();
        } else {
            // Handle the case where staffRepository is null
            return Collections.emptyList(); // Return an empty list for example
        }
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/ajouter")
    public ResponseEntity<Candidat> create(@RequestBody Candidat newCandidat){
        Staff staff = staffRepository.findByNomAndPrenom(newCandidat.getStaff().getNom(), newCandidat.getStaff().getPrenom());
        if (staff == null) {
            staff = staffRepository.save(newCandidat.getStaff());
        }

        newCandidat.setStaff(staff); // set the staff object in the candidat object
        
        Candidat candidat = candidatRepository.save(newCandidat); // save the Candidat object to the database
        //return ResponseEntity.ok(candidat); // return the saved Candidat object
        return ResponseEntity.ok().build();

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/ajouterStaff")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff) {
        Staff newStaff = staffRepository.save(staff);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modifierstatut/{id}")
    public ResponseEntity<Candidat> updateCandidatStatuts(@PathVariable Long id, @RequestBody StatutCandidat statut) {
        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Update the statuts of the Candidat
        List<StatutCandidat> exStatuts = candidat.getStatuts();
        exStatuts.add(statut);
        candidat.setStatuts(exStatuts);
        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        // Return the updated Candidat
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/joindre/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Candidat> addDocument(@PathVariable Long id, @RequestBody Document doc) {
        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Update the statuts of the Candidat
        List<Document> documents = candidat.getDocuments();
        documents.add(doc);
        candidat.setDocuments(documents);
        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        // Return the updated Candidat
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/upload/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Candidat> uploadDocument(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("data") MultipartFile data) {
        // Create a new Document object and set its properties
        Document document = new Document();
        document.setName(name);
        try {
            document.setData(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }

        // Find the Candidat with the given id
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        Candidat candidat = optionalCandidat.get();
        // Update the statuts of the Candidat
        List<Document> documents = candidat.getDocuments();
        documents.add(document);
        candidat.setDocuments(documents);
        // Save the updated Candidat to the database
        candidatRepository.save(candidat);
        // Return the updated Candidat
        return ResponseEntity.ok().build();

    }


}
