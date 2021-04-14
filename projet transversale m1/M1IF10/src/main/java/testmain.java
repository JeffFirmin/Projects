import com.m1if10.app.classes.CryptageMdp;
import com.m1if10.app.modele.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testmain {
    public static void main (String[] args) throws ParseException {

        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();



        Prof prof = new Prof("prof@prof.fr",CryptageMdp.encrypt("profpdw"),"profnom","profprenom","prof.png");
        Prof prof2 = new Prof("prof2@prof2.fr",CryptageMdp.encrypt("prof2pdw"),"prof2nom","prof2prenom","prof2.png");

        Alternant alt1 = new Alternant("alt@alt.fr",CryptageMdp.encrypt("altpdw"),"altnom","altprenom","alt.png", "entreprisealt",Groupe.A1,"p1809392");
        Alternant alt2 = new Alternant("alt2@alt.fr",CryptageMdp.encrypt("alt2pdw"),"altnom2","altprenom2","alt2.png", "entreprisealt2",Groupe.A2,"p1809392");

        AdminSco adminSco = new AdminSco("admin@admin.fr",CryptageMdp.encrypt("adminpdw"),"adminnom","adminprenom","admin.png");
        AdminSco adminSco2 = new AdminSco("admin2@admin.fr",CryptageMdp.encrypt("admin2pdw"),"adminnom2","adminprenom2","admin2.png");

        UE M1IF02 = new UE("M1IF02","PROG AVANCEE");
        UE M1IF10 = new UE("M1IF10","Projet Transversale du S1");
        UE M1IF03 = new UE("M1IF03","Application Web");
        UE TA = new UE("TA","Travail en autonomie");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateCours1 = sdf.parse("2019-09-11 7:45");
        Date dateCours2 = sdf.parse("2019-11-06 10:30");
        Date dateCours3 = sdf.parse("2019-11-08 16:15");
        Date dateCours4 = sdf.parse("2019-11-25 7:45");
        Date dateCours5 = sdf.parse("2019-11-25 16:15");
        Date dateCours6 = sdf.parse("2019-11-26 9:15");
        Date dateCours7 = sdf.parse("2019-11-26 14:00");
        Date dateCours8 = sdf.parse("2019-11-27 15:45");
        Date dateCours9 = sdf.parse("2019-11-28 15:45");


        Cours cours1 = new Cours(M1IF02,"CM","Themis 10",dateCours1,"A1",3);
        Cours cours2 = new Cours(M1IF10,"CM","Nautibus C1",dateCours2,"A1",2);
        Cours cours3 = new Cours(M1IF10,"TD","Nautibus TD12",dateCours3,"A1",1.5);
        Cours cours4 = new Cours(M1IF03,"CM","Amphi Deperet",dateCours4,"",4.0);
        Cours cours5 = new Cours(M1IF03,"TD","Nautibus TD12",dateCours5,"A2",1.5);
        Cours cours6 = new Cours(M1IF10,"TD","Nautibus C3",dateCours6,"A2",2.0);
        Cours cours7 = new Cours(M1IF03,"CM","Amphi Deperet",dateCours7,"",1.5);
        Cours cours8 = new Cours(M1IF10,"TP","Nautibus TP2",dateCours8,"A1",2.0);
        Cours cours9 = new Cours(M1IF03,"CM","Amphi Deperet",dateCours9,"",4.0);



        PresenceAlt pa = new PresenceAlt(alt1,cours1, EtatPresence.ABINJ);
        PresenceAlt pa2 = new PresenceAlt(alt1,cours2, EtatPresence.PRST);
        PresenceAlt pa3 = new PresenceAlt(alt2,cours1, EtatPresence.ABINJ);
        PresenceAlt pa4 = new PresenceAlt(alt2,cours2, EtatPresence.ABJ);
        PresenceAlt pa5 = new PresenceAlt(alt1,cours4, EtatPresence.NULL);
        PresenceAlt pa6 = new PresenceAlt(alt1,cours5, EtatPresence.NULL);
        PresenceAlt pa7 = new PresenceAlt(alt1,cours6, EtatPresence.NULL);
        PresenceAlt pa8 = new PresenceAlt(alt1,cours7, EtatPresence.NULL);
        PresenceAlt pa9 = new PresenceAlt(alt1,cours8, EtatPresence.NULL);
        PresenceAlt pa10 = new PresenceAlt(alt1,cours9, EtatPresence.NULL);


        liaisonProfUe lp1 = new liaisonProfUe(prof,M1IF02,true);
        liaisonProfUe lp2 = new liaisonProfUe(prof,M1IF03,true);
        liaisonProfUe lp3 = new liaisonProfUe(prof,TA,true);
        liaisonProfUe lpfalse = new liaisonProfUe(prof2,M1IF10,false);

        InscriptionUe altInscriptionUe1 = new InscriptionUe(alt1,M1IF02);
        InscriptionUe altInscriptionUe2 = new InscriptionUe(alt1,M1IF03);
        InscriptionUe altInscriptionUe3 = new InscriptionUe(alt1,M1IF10);
        InscriptionUe altInscriptionUe4 = new InscriptionUe(alt1,TA);


        // ajout dans la BD
        em.getTransaction().begin();

        em.persist(prof);
        em.persist(prof2);

        em.persist(alt1);
        em.persist(alt2);

        em.persist(adminSco);
        em.persist(adminSco2);

        em.persist(M1IF02);
        em.persist(M1IF10);
        em.persist(M1IF03);
        em.persist(TA);


        em.persist(cours1);
        em.persist(cours2);
        em.persist(cours3);
        em.persist(cours4);
        em.persist(cours5);
        em.persist(cours6);
        em.persist(cours7);
        em.persist(cours8);
        em.persist(cours9);


        em.persist(pa);
        em.persist(pa2);
        em.persist(pa3);
        em.persist(pa4);
        em.persist(pa5);
        em.persist(pa6);
        em.persist(pa7);
        em.persist(pa8);
        em.persist(pa9);
        em.persist(pa10);

        em.persist(lp1);
        em.persist(lp2);
        em.persist(lp3);
        em.persist(lpfalse);

        em.persist(altInscriptionUe1);
        em.persist(altInscriptionUe2);
        em.persist(altInscriptionUe3);
        em.persist(altInscriptionUe4);


        //em.getTransaction().commit();
        em.close();

        System.exit(0);
    }
}