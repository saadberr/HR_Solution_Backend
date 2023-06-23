package fr.deloitte.HRsolution.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
