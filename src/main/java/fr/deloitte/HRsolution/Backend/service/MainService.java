package fr.deloitte.HRsolution.Backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.Prequal;
import fr.deloitte.HRsolution.Backend.entities.StatutCandidat;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.StatutCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private CandidatRepository candiatRepository;
    @Autowired
    private StatutCRepository statutCRepository;
    public void saveSCFromJsonFile() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode candidats = mapper.readTree(new File("src/main/resources/static/data.json"));
        JsonNode statuts = mapper.readTree(new File("src/main/resources/static/statutC.json"));
        JsonNode statuts2 = mapper.readTree(new File("src/main/resources/static/statutC2.json"));
        JsonNode prequals = mapper.readTree(new File("src/main/resources/static/prequal.json"));
        JsonNode prequals2 = mapper.readTree(new File("src/main/resources/static/prequal2.json"));

        for (int i = 0; i < candidats.size() && i < statuts.size(); i++) {
            if(i < prequals.size() && i < prequals2.size()) {
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                JsonNode p2 = prequals2.get(i);
                // Do something with the current elements
                List<StatutCandidat> canStatuts = new ArrayList<>();
                StatutCandidat statut = new StatutCandidat(null, s.get("statut").textValue(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s.get("dateStatut").textValue()));
                StatutCandidat statut2 = new StatutCandidat(null, s2.get("statut").textValue(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s2.get("dateStatut").textValue()));
                canStatuts.add(statut);
                canStatuts.add(statut2);

                List<Prequal> canPrequals = new ArrayList<>();
                Prequal prequal = new Prequal(new SimpleDateFormat("dd/MM/yyyy").parse(p.get("datePrequal").textValue()), p.get("commentaire").textValue(), p.get("niveauFR").textValue(), p.get("niveauEN").textValue(), p.get("resultatPrequal").textValue());
                Prequal prequal2 = new Prequal(new SimpleDateFormat("dd/MM/yyyy").parse(p2.get("datePrequal").textValue()), p2.get("commentaire").textValue(), p2.get("niveauFR").textValue(), p2.get("niveauEN").textValue(), p2.get("resultatPrequal").textValue());
                canPrequals.add(prequal);
                canPrequals.add(prequal2);

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), null, canStatuts, null, canPrequals);

                candiatRepository.save(candidat);
            } else if (i < prequals.size()) {
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                // Do something with the current elements
                List<StatutCandidat> canStatuts = new ArrayList<>();
                StatutCandidat statut = new StatutCandidat(null, s.get("statut").textValue(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s.get("dateStatut").textValue()));
                StatutCandidat statut2 = new StatutCandidat(null, s2.get("statut").textValue(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s2.get("dateStatut").textValue()));
                canStatuts.add(statut);
                canStatuts.add(statut2);

                List<Prequal> canPrequals = new ArrayList<>();
                Prequal prequal = new Prequal(new SimpleDateFormat("dd/MM/yyyy").parse(p.get("datePrequal").textValue()), p.get("commentaire").textValue(), p.get("niveauFR").textValue(), p.get("niveauEN").textValue(), p.get("resultatPrequal").textValue());
                canPrequals.add(prequal);

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), null, canStatuts, null, canPrequals);

                candiatRepository.save(candidat);
            }
            else {
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                // Do something with the current elements
                List<StatutCandidat> canStatuts = new ArrayList<>();
                StatutCandidat statut = new StatutCandidat(null, s.get("statut").textValue(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s.get("dateStatut").textValue()));
                StatutCandidat statut2 = new StatutCandidat(null, s2.get("statut").textValue(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s2.get("dateStatut").textValue()));
                canStatuts.add(statut);
                canStatuts.add(statut2);

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), null, canStatuts, null, null);

                candiatRepository.save(candidat);
            }


        }
    }
}
