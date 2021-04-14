package com.m1if10.app.dao;

import com.m1if10.app.modele.Prof;
import com.m1if10.app.modele.UE;
import com.m1if10.app.modele.liaisonProfUe;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Class to manage the UE model
 */
public class UeDao {
    /**
     * The entity manager to manage changes of UE
     */
    private EntityManager em;

    public UeDao(EntityManager em) {
        this.em = em;
    }

    /**
     * Get the specified UE model
     * @param nameUe: UE's name
     * @return specified UE
     */
    public UE getUe(String nameUe) {
        em.getTransaction().begin();
        Query tmp2 = em.createQuery(
                "select u from UE u where u.idUe = :nameUe")
                .setParameter("nameUe", nameUe);
        em.getTransaction().commit();

        if (tmp2.getResultList().size() == 0) {
            insertUE(nameUe,"");
        }

        return (UE) tmp2.getResultList().get(0);
    }

    public boolean existeUE(String nameUE){
        Query query = em.createQuery(
                "Select ue " +
                        "from UE ue " +
                        "where ue.idUe=:nameUE ")
                .setParameter("nameUE", nameUE);
        return query.getResultList().size()!=0;
    }

    public void insertUE(String nameUE, String intitule){
        em.getTransaction().begin();
        UE ue = new UE(nameUE,intitule);
        em.persist(ue);
        em.getTransaction().commit();
    }
    public void insertUeProf(String nameUE, String intitule, String emailprof){
        em.getTransaction().begin();
        Prof prof = em.find(Prof.class,emailprof);
        UE ue = new UE(nameUE,intitule);
        liaisonProfUe lpa = new liaisonProfUe(prof,ue, true);
        em.persist(ue);
        em.persist(lpa);
        em.getTransaction().commit();
    }
}
