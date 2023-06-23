package fr.deloitte.HRsolution.Backend.payloads;

import fr.deloitte.HRsolution.Backend.Entities.Integration;
import fr.deloitte.HRsolution.Backend.Entities.StatutOffre;
import lombok.Data;

@Data
public class OffrePayload {
    private StatutOffre statut;
    private Integration integration;
}
