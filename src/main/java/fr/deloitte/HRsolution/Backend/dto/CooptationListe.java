package fr.deloitte.HRsolution.Backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CooptationListe {

    public Long id;
    public String prenom;
    public String nom;
    public String email;
    public String telephone;
    public String grade;
    public String practice;

    private String nomCoopteur;
    private String practiceCoopteur;
    private int montant;
    private Date dateIntegration;
    private Date datePremierVers;
    private Date dateDeuxiemeVers;
    private int statutCooptation;

}
