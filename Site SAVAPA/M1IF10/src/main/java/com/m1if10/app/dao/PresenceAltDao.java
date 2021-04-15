package com.m1if10.app.dao;

import com.m1if10.app.classes.RandomString;
import com.m1if10.app.modele.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to manage the PresenceAlt model
 */
public class PresenceAltDao {
    /**
     * The entity manager to manage changes of PresenceAlt
     */
    private EntityManager em;

    public PresenceAltDao(EntityManager em) {
        this.em = em;
    }

    public void begin() {
        em.getTransaction().begin();
    }

    public void end() {
        em.getTransaction().commit();
        em.close();
    }

    public void createPresence(Alternant alt, Cours cours, EtatPresence etatPresence) {
        PresenceAlt pa = new PresenceAlt(alt, cours, etatPresence);
        em.persist(pa);
        alt.setCleQr(new RandomString(32).nextString());
    }

    public void updateAbsence(EtatPresence etat, String altstring, String coursstring) {
        Alternant alternant = em.find(Alternant.class, altstring);
        Cours cours = em.find(Cours.class, Integer.parseInt(coursstring));
        PresenceAlt presenceAlt = em.find(PresenceAlt.class, new PresenceAltPk(alternant, cours));
        presenceAlt.setPresent(etat.toString());
    }

    public Map<Alternant, List<Cours>> getAbsences(EtatPresence etat) {
        Query query = em.createQuery(
                "Select pa.idPa.alternant, " +
                        "pa.idPa.cours " +
                        "from PresenceAlt pa " +
                        "where pa.present=:etat")
                .setParameter("etat", etat.toString());

        List<Object[]> queryResultList = query.getResultList();
        Map<Alternant, List<Cours>> resultMap = new HashMap<>();
        for (Object[] result : queryResultList) {
            Alternant alt = (Alternant) result[0];
            Cours cours = (Cours) result[1];

            List<Cours> listCours = resultMap.get(alt);
            if (listCours == null) {
                listCours = new ArrayList<>();
                listCours.add(cours);
                resultMap.put(alt, listCours);
            } else {
                listCours.add(cours);
            }
        }
        return resultMap;
    }

    public Map<Cours, EtatPresence> getPresencesAlt(String email) {
        Query query = em.createQuery(
                "Select pa.present, " +
                        "pa.idPa.cours " +
                        "from PresenceAlt pa " +
                        "where pa.idPa.alternant.email=:email order by pa.idPa.cours.content.date asc ")
                .setParameter("email", email);

        List<Object[]> queryResultList = query.getResultList();
        Map<Cours, EtatPresence> resultMap = new HashMap<>();
        for (Object[] result : queryResultList) {
            Cours cours = (Cours) result[1];
            EtatPresence present = EtatPresence.ABINJ;
            for(EtatPresence etat : EtatPresence.class.getEnumConstants()){
                if((result[0]).equals(etat.toString())){
                    present = etat;
                }
            }
            resultMap.put(cours, present);

        }
        return resultMap;
    }


}
