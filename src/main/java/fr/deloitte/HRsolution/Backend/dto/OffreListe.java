package fr.deloitte.HRsolution.Backend.dto;

import fr.deloitte.HRsolution.Backend.entities.Integration;
import fr.deloitte.HRsolution.Backend.entities.Offre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreListe {
    public Long id;
    public String prenom;
    public String nom;
    public String email;
    public String telephone;
    public String pays;
    public String practice;
    public String specialite;
    public String grade;
    public Date dateSourcing;

    public String statut;
    public String raison;
    public Date dateStatut;

    public Offre offre;
}
