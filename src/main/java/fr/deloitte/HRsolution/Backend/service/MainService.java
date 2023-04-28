package fr.deloitte.HRsolution.Backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deloitte.HRsolution.Backend.entities.*;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.StaffRepository;
import fr.deloitte.HRsolution.Backend.repositories.StatutCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private CandidatRepository candiatRepository;
    @Autowired
    private StatutCRepository statutCRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private EntityManager entityManager;
    public void saveSCFromJsonFile() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode candidats = mapper.readTree(new File("src/main/resources/static/data.json"));
        JsonNode statuts = mapper.readTree(new File("src/main/resources/static/statutC.json"));
        JsonNode statuts2 = mapper.readTree(new File("src/main/resources/static/statutC2.json"));
        JsonNode prequals = mapper.readTree(new File("src/main/resources/static/prequal.json"));
        JsonNode prequals2 = mapper.readTree(new File("src/main/resources/static/prequal2.json"));
        JsonNode staffs = mapper.readTree(new File("src/main/resources/static/staff.json"));
        JsonNode offres = mapper.readTree(new File("src/main/resources/static/statutO.json"));
        JsonNode offres2 = mapper.readTree(new File("src/main/resources/static/statutO2.json"));
        JsonNode entretiens = mapper.readTree(new File("src/main/resources/static/entretien.json"));
        JsonNode entretiens2 = mapper.readTree(new File("src/main/resources/static/entretien2.json"));

        for (int i = 0; i < staffs.size(); i++){
            JsonNode sf = staffs.get(i);
            Staff staff = new Staff(null, sf.get("nom").textValue(), sf.get("prenom").textValue(), sf.get("grade").textValue());
            staffRepository.save(staff);
        }

        for (int i = 0; i < candidats.size() && i < statuts.size(); i++) {
            /*if(i < prequals.size() && i < prequals2.size() && i < offres.size() && i < offres2.size() && i < entretiens.size() && i < entretiens2.size()){
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                JsonNode p2 = prequals2.get(i);
                JsonNode o = offres.get(i);
                JsonNode o2 = offres2.get(i);
                JsonNode e = entretiens.get(i);
                JsonNode e2 = entretiens2.get(i);
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


                List<StatutOffre> canOffres = new ArrayList<>();
                StatutOffre offre = new StatutOffre(null, o.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o.get("offreDateStatut").textValue()));
                StatutOffre offre2 = new StatutOffre(null, o2.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o2.get("offreDateStatut").textValue()));
                canOffres.add(offre);
                canOffres.add(offre2);
                Offre of = new Offre(null, canOffres);

                List<Entretien> canEntretiens = new ArrayList<>();
                Entretien interview = new Entretien( null,e.get("typeEntretien").textValue(), LocalDateTime.parse(e.get("dateheureEntretien").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e.get("etat").textValue(), e.get("commentaire").textValue(), Arrays.asList(e.get("recruteurs").textValue().split("\\s*,\\s*")));
                Entretien interview2 = new Entretien( null,e2.get("typeEntretien").textValue(), LocalDateTime.parse(e.get("dateheureEntretien").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e2.get("etat").textValue(), e2.get("commentaire").textValue(), Arrays.asList(e2.get("recruteurs").textValue().split("\\s*,\\s*")));
                canEntretiens.add(interview);
                canEntretiens.add(interview2);

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, canPrequals, null, of,canEntretiens);

                Long[] items = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
                int randomIndex = (int) (Math.random() * items.length);
                Long randomStaffId = items[randomIndex];
                Staff staff = entityManager.getReference(Staff.class, randomStaffId);
                candidat.setStaff(staff);

                candiatRepository.save(candidat);
            }
            else if(i < prequals.size() && i < prequals2.size() && i < offres.size() && i < offres2.size() && i < entretiens.size()){
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                JsonNode p2 = prequals2.get(i);
                JsonNode o = offres.get(i);
                JsonNode o2 = offres2.get(i);
                JsonNode e = entretiens.get(i);
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


                List<StatutOffre> canOffres = new ArrayList<>();
                StatutOffre offre = new StatutOffre(null, o.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o.get("offreDateStatut").textValue()));
                StatutOffre offre2 = new StatutOffre(null, o2.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o2.get("offreDateStatut").textValue()));
                canOffres.add(offre);
                canOffres.add(offre2);
                Offre of = new Offre(null, canOffres);

                List<Entretien> canEntretiens = new ArrayList<>();
                Entretien interview = new Entretien(null, e.get("typeEntretien").textValue(), LocalDateTime.parse(e.get("dateheureEntretien").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e.get("etat").textValue(), e.get("commentaire").textValue(), Arrays.asList(e.get("recruteurs").textValue().split("\\s*,\\s*")));
                canEntretiens.add(interview);

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, canPrequals, null, of,canEntretiens);

                Long[] items = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
                int randomIndex = (int) (Math.random() * items.length);
                Long randomStaffId = items[randomIndex];
                Staff staff = entityManager.getReference(Staff.class, randomStaffId);
                candidat.setStaff(staff);

                candiatRepository.save(candidat);
            }
            else*/ if(i < prequals.size() && i < prequals2.size() && i < offres.size() && i < offres2.size()){
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                JsonNode p2 = prequals2.get(i);
                JsonNode o = offres.get(i);
                JsonNode o2 = offres2.get(i);
                JsonNode e = entretiens.get(i);
                JsonNode e2 = entretiens2.get(i);

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


                List<StatutOffre> canOffres = new ArrayList<>();
                StatutOffre offre = new StatutOffre(null, o.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o.get("offreDateStatut").textValue()));
                StatutOffre offre2 = new StatutOffre(null, o2.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o2.get("offreDateStatut").textValue()));
                canOffres.add(offre);
                canOffres.add(offre2);
                Offre of = new Offre(null, canOffres);

                List<Entretien> canEntretiens = new ArrayList<>();
                Entretien interview = new Entretien( null,e.get("typeEntretien").textValue(), LocalDateTime.parse(e.get("dateheureEntretien").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e.get("etat").textValue(), e.get("commentaire").textValue(), Arrays.asList(e.get("recruteurs").textValue().split("\\s*,\\s*")));
/*
                Entretien interview2 = new Entretien( null,e2.get("typeEntretien").textValue(), LocalDateTime.parse(e.get("dateheureEntretien").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), e2.get("etat").textValue(), e2.get("commentaire").textValue(), Arrays.asList(e2.get("recruteurs").textValue().split("\\s*,\\s*")));
*/
                canEntretiens.add(interview);
/*
                canEntretiens.add(interview2);
*/

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, canPrequals, null, of,canEntretiens);

                Long[] items = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
                int randomIndex = (int) (Math.random() * items.length);
                Long randomStaffId = items[randomIndex];
                Staff staff = entityManager.getReference(Staff.class, randomStaffId);
                candidat.setStaff(staff);

                candiatRepository.save(candidat);
            }
            else if(i < prequals.size() && i < prequals2.size() && i < offres.size()) {
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                JsonNode p2 = prequals2.get(i);
                JsonNode o = offres.get(i);
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

                List<StatutOffre> canOffres = new ArrayList<>();
                StatutOffre offre = new StatutOffre(null, o.get("offreStatut").textValue(), null, new SimpleDateFormat("dd/MM/yyyy").parse(o.get("offreDateStatut").textValue()));
                canOffres.add(offre);
                Offre of = new Offre(null, canOffres);

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, canPrequals, null, of,null);

                Long[] items = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
                int randomIndex = (int) (Math.random() * items.length);
                Long randomStaffId = items[randomIndex];
                Staff staff = entityManager.getReference(Staff.class, randomStaffId);
                candidat.setStaff(staff);

                candiatRepository.save(candidat);
            } else if (i < prequals.size() && i < prequals2.size()) {
                JsonNode c = candidats.get(i);
                JsonNode s = statuts.get(i);
                JsonNode s2 = statuts2.get(i);
                JsonNode p = prequals.get(i);
                JsonNode p2 = prequals2.get(i);
                JsonNode o = offres.get(i);
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

                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, canPrequals, null, null,null);

                Long[] items = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
                int randomIndex = (int) (Math.random() * items.length);
                Long randomStaffId = items[randomIndex];
                Staff staff = entityManager.getReference(Staff.class, randomStaffId);
                candidat.setStaff(staff);

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


                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, canPrequals, null, null,null);

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


                Candidat candidat = new Candidat(null, c.get("nom").textValue(), c.get("prenom").textValue(), c.get("telephone").textValue(), c.get("email").textValue(), c.get("pays").textValue(), c.get("nationalite").textValue(), c.get("genre").textValue(), c.get("ecole").textValue(), c.get("diplome").textValue(), c.get("anneeDiplome").asInt(), c.get("entActuelle").textValue(), c.get("grade").textValue(), c.get("experience").textValue(), new SimpleDateFormat("dd/MM/yyyy").parse(c.get("dateSourcing").textValue()), c.get("source").textValue(), c.get("practice").textValue(), c.get("specialite").textValue(), 0, null, canStatuts, null, null, null, null,null);

                candiatRepository.save(candidat);
            }


        }
    }
}
