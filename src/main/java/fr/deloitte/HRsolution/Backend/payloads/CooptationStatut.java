package fr.deloitte.HRsolution.Backend.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CooptationStatut {
    @JsonProperty("intValue")
    private int intValue;
}
