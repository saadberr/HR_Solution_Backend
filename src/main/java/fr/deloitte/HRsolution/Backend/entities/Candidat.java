package fr.deloitte.HRsolution.Backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CANDIDAT")
@Data @AllArgsConstructor @NoArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String pays;
    private String nationalite;
    private String genre;

    private String ecole;
    private String diplome;
    private int anneeDiplome;
    private String entActuelle;
    private String grade;
    private String experience;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateSourcing;
    private String source;
    private String practice;
    private String specialite;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "staff_id",referencedColumnName = "id" )
    private Staff staff;

    @OneToMany(targetEntity = StatutCandidat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidat_id", referencedColumnName = "id")
    private List<StatutCandidat> statuts;

    @OneToMany(mappedBy = "candidat")
    private List<Document> documents;

    @OneToMany(targetEntity = Prequal.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidat_id", referencedColumnName = "id")
    private List<Prequal> prequals;

    public Candidat(String nom, String prenom, String telephone, String email, String pays, String nationalite,
                    String genre, String ecole, String diplome, int anneeDiplome, String entActuelle,
                    String grade, String experience, Date dateSourcing, String source, String practice, String specialite, Staff staff) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.pays = pays;
        this.nationalite = nationalite;
        this.genre = genre;
        this.ecole = ecole;
        this.diplome = diplome;
        this.anneeDiplome = anneeDiplome;
        this.entActuelle = entActuelle;
        this.grade = grade;
        this.experience = experience;
        this.dateSourcing = dateSourcing;
        this.source = source;
        this.practice = practice;
        this.specialite = specialite;
        this.staff=staff;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getAnneeDiplome() {
        return anneeDiplome;
    }

    public void setAnneeDiplome(int anneeDiplome) {
        this.anneeDiplome = anneeDiplome;
    }

    public String getEntActuelle() {
        return entActuelle;
    }

    public void setEntActuelle(String entActuelle) {
        this.entActuelle = entActuelle;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Date getDateSourcing() {
        return dateSourcing;
    }

    public void setDateSourcing(Date dateSourcing) {
        this.dateSourcing = dateSourcing;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidat candidat = (Candidat) o;
        return anneeDiplome == candidat.anneeDiplome && Objects.equals(id, candidat.id) && Objects.equals(nom, candidat.nom) && Objects.equals(prenom, candidat.prenom) && Objects.equals(telephone, candidat.telephone) && Objects.equals(email, candidat.email) && Objects.equals(pays, candidat.pays) && Objects.equals(nationalite, candidat.nationalite) && Objects.equals(genre, candidat.genre) && Objects.equals(ecole, candidat.ecole) && Objects.equals(diplome, candidat.diplome) && Objects.equals(entActuelle, candidat.entActuelle) && Objects.equals(grade, candidat.grade) && Objects.equals(experience, candidat.experience) && Objects.equals(dateSourcing, candidat.dateSourcing) && Objects.equals(source, candidat.source) && Objects.equals(practice, candidat.practice) && Objects.equals(specialite, candidat.specialite) && Objects.equals(staff, candidat.staff) && Objects.equals(statuts, candidat.statuts) && Objects.equals(documents, candidat.documents) && Objects.equals(prequals, candidat.prequals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, telephone, email, pays, nationalite, genre, ecole, diplome, anneeDiplome, entActuelle, grade, experience, dateSourcing, source, practice, specialite, staff, statuts, documents, prequals);
    }

    @Override
    public String toString() {
        return "Candidat{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", pays='" + pays + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", genre='" + genre + '\'' +
                ", ecole='" + ecole + '\'' +
                ", diplome='" + diplome + '\'' +
                ", anneeDiplome=" + anneeDiplome +
                ", entActuelle='" + entActuelle + '\'' +
                ", grade='" + grade + '\'' +
                ", experience='" + experience + '\'' +
                ", dateSourcing=" + dateSourcing +
                ", source='" + source + '\'' +
                ", practice='" + practice + '\'' +
                ", specialite='" + specialite + '\'' +
                '}';
    }
}
