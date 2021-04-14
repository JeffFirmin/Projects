package com.m1if10.app.dao;

import com.m1if10.app.modele.Prof;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to manage the Prof model
 */
public class ProfDao {
    /**
     * The entity manager to manage changes of AdminSco
     */
    private EntityManager em;

    public ProfDao(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates the Prof model
     * @param password: Prof's model
     * @param nom: Prof's surname
     * @param prenom: Prof's first name
     * @param urlPhoto: Prof's photo's url
     * @return Prof model
     */
    public Prof createProf(String password, String nom, String prenom, String urlPhoto) {
        em.getTransaction().begin();
        String email = prenom +"."+nom+"@univ-lyon1.fr";
        Prof p = new Prof(email, password, nom, prenom, urlPhoto);
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    /**
     * Checks if a Prof already exists in the DB
     * @param nom: Prof's surname
     * @param prenom: Prof's first name
     * @return true if specified Prof already exists
     */
    public boolean existeProf(String nom, String prenom){
        Query query = em.createQuery(
                "Select p " +
                        "from Prof p " +
                        "where p.nom=:nom "+
                        "and p.prenom=:prenom")
                .setParameter("nom", nom)
                .setParameter("prenom", prenom);
        return query.getResultList().size()!=0;
    }

    public Map<String,String> getAllProfs(){
        Query query = em.createQuery(
                "Select p.email, p.nom, p.prenom " +
                        "from Prof p ");
        List<Object[]> queryResultList = query.getResultList();
        Map<String, String> resultMap = new HashMap<>();
        for (Object[] result : queryResultList) {
            String emailprof = (String) result[0];
            String nomprof = (String) result[1];
            String prenomprof = (String) result[2];
            resultMap.put(emailprof, nomprof+ " " + prenomprof);
        }
        return resultMap;
    }
}
