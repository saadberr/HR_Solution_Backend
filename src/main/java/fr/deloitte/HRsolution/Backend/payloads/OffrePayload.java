package fr.deloitte.HRsolution.Backend.payloads;

import fr.deloitte.HRsolution.Backend.entities.Integration;
import fr.deloitte.HRsolution.Backend.entities.StatutOffre;
import lombok.Data;

@Data
public class OffrePayload {
    private StatutOffre statut;
    private Integration integration;
}
