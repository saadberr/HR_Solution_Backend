package fr.deloitte.HRsolution.Backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deloitte.HRsolution.Backend.entities.Candidat;
import fr.deloitte.HRsolution.Backend.entities.StatutCandidat;
import fr.deloitte.HRsolution.Backend.repositories.CandidatRepository;
import fr.deloitte.HRsolution.Backend.repositories.StatutCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class StatutCService {

    @Autowired
    private StatutCRepository statutCRepository;

    public void saveStatutCFromJsonFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<StatutCandidat> statuts = objectMapper.readValue(new File("src/main/resources/static/statutC.json"), new TypeReference<List<StatutCandidat>>(){});
        statutCRepository.saveAll(statuts);

    }
}
