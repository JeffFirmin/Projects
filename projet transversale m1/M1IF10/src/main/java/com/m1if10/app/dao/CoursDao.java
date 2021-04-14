package com.m1if10.app.dao;

import com.m1if10.app.modele.Cours;
import com.m1if10.app.modele.UE;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Class to manage the Cours model
 */
public class CoursDao {
    /**
     * The entity manager to manage changes of Cours
     */
    private EntityManager em;

    public CoursDao(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates the Cours model
     * @param nameUe: Cours' name
     * @param nature: Cours' nature
     * @param salle: Cours' room
     * @param date: Cours' date
     * @param groupe: Cours' group
     * @param duree: Cours' duration
     * @return Cours model
     */
    public Cours createCours(UE nameUe,String nature, String salle, Date date, String groupe, double duree) {
        em.getTransaction().begin();
        Cours cours = new Cours(nameUe, nature,  salle, date, groupe, duree);
        em.persist(cours);
        em.getTransaction().commit();
        em.getTransaction().begin();
        Query tmp = em.createQuery(
                "select c from Cours c where c.nameUe.idUe = :idue and c.content.nature = :nature order by c.idCour DESC ")
                .setParameter("idue",nameUe.getIdUe())
                .setParameter("nature",nature);
        em.getTransaction().commit();
        List i = tmp.getResultList();
        return (Cours) i.get(0);
    }

    /**
     * Get the list of Cours for an Alternant
     * @param mail: Alternant's mail
     * @return list of Cours
     */
    public List<Cours> getCoursAlt (String mail) {
        List<Cours> tmp = new ArrayList<Cours>();
        em.getTransaction().begin();
        Query tmp2 = em.createQuery(
                "select pa.idPa.cours " +
                        "from PresenceAlt pa " +
                        "where pa.idPa.alternant.email = :mail order by pa.idPa.cours.content.date ").setParameter("mail",mail);
        em.getTransaction().commit();

        tmp = tmp2.getResultList();

        em.close();
        return tmp;
    }
}
