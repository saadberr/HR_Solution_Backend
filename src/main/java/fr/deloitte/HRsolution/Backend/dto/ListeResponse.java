package fr.deloitte.HRsolution.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ListeResponse {

    public Long id;
    public String prenom;
    public String nom;
    public String email;
    public String telephone;
    public String pays;
    public String practice;
    public String specialite;
    public String grade;
    public String experience;
    public String source;
    public Date dateSourcing;
    public String statut;
    public Date dateStatut;
}
