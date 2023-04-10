package fr.deloitte.HRsolution.Backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KanbanResponse {
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

    public String resultatPrequal;
    public Date datePrequal;
}
