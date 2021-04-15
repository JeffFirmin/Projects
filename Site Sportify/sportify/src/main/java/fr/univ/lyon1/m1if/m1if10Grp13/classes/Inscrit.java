package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.*;

import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "inscrit")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Inscrit {
    @Id
    @Column(name = "emailinscrit")
    private String emailInscrit;

    @ManyToOne
    @JoinColumn(name = "emailclub")
    private Club club;

    @Column(name = "nomInscrit")
    private String nominscrit;

    @Column(name = "telInscrit")
    private String telinscrit;

    @Column(name = "password")
    @Convert(converter = AESEncryption.class)
    private String password;

    @Column(name = "naissanceInscrit")
    private Date naissanceinscrit;

    @Column(name = "dateInscription")
    private Date dateinscription;

    public Inscrit(String emailInscrit, Club club, String nomInscrit, String telInscrit, String password,
            Date naissanceInscrit, Date dateInscription) {
        this.emailInscrit = emailInscrit;
        this.club = club;
        this.nominscrit = nomInscrit;
        this.telinscrit = telInscrit;
        this.password = password;
        this.naissanceinscrit = naissanceInscrit;
        this.dateinscription = dateInscription;
    }

    public Inscrit() {

    }

    public String getEmailInscrit() {
        return emailInscrit;
    }

    public String getNomInscrit() {
        return nominscrit;
    }

    public String getTelInscrit() {
        return telinscrit;
    }

    public String getPassword() {
        return password;
    }

    public Date getNaissanceInscrit() {
        return naissanceinscrit;
    }

    public Date getDateInscription() {
        return dateinscription;
    }
    
    public String getClubOfUserName() {
        try {
            return club.getNomClub();
        } catch (Exception e) {
            return null;
        }
    }

    public Club getClub() {
        return club;
    }

    public void setEmailInscrit(String emailInscrit) {
        this.emailInscrit = emailInscrit;
    }

    public void setclub(Club club) {
        this.club = club;
    }

    public void setNomInscrit(String nomInscrit) {
        this.nominscrit = nomInscrit;
    }

    public void setTelInscrit(String telInscrit) {
        this.telinscrit = telInscrit;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNaissanceInscrit(Date naissanceInscrit) {
        this.naissanceinscrit = naissanceInscrit;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateinscription = dateInscription;
    }

}
