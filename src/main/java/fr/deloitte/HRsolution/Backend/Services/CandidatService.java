package fr.deloitte.HRsolution.Backend.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deloitte.HRsolution.Backend.Entities.Candidat;
import fr.deloitte.HRsolution.Backend.Repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candiatRepository;

    public void saveCandidatsFromJsonFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Candidat> candidats = objectMapper.readValue(new File("src/main/resources/static/data.json"), new TypeReference<List<Candidat>>(){});
        candiatRepository.saveAll(candidats);

    }
}
