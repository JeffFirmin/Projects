package com.m1if10.app.dao;

import com.m1if10.app.modele.Alternant;
import com.m1if10.app.modele.Groupe;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Class to manage the Alternant model
 */
public class AlternantDao {
    /**
     * The entity manager to manage changes of AdminSco
     */
    private EntityManager em;

    public AlternantDao(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates the Alternant model
     * @param password: Alternant's password
     * @param nom: Alternant's Surname
     * @param prenom: Alternant's First name
     * @param photo: Alternant's photo's URL
     * @param entreprise: company where the alternant is working
     * @param groupe: Alternant's group
     * @param numEtu: Alternant's student number
     * @return Alternant model
     */
    public Alternant createAlternant(String password, String nom, String prenom, String photo, String entreprise, String groupe, String numEtu) {
        em.getTransaction().begin();
        String email = prenom +"."+nom+"@etu.univ-lyon1.fr";
        Groupe finalGroupe = Groupe.A1;
        for(Groupe  grp : Groupe.class.getEnumConstants()) {
            if (grp.toString().equals(groupe)) {
                finalGroupe = grp;
            }
        }
        Alternant alt = new Alternant(email, password, nom, prenom, photo, entreprise, finalGroupe, numEtu);
        em.persist(alt);
        em.getTransaction().commit();
        return alt;
    }


    /**
     * Find Alternant from a QRCode key
     * @param cleQr: QRCode key assigned to the Alternant
     * @return Alternant of the specified QRCode key
     */
    public Alternant findByCleQr(String cleQr) {
        TypedQuery<Alternant> q = em.createNamedQuery("Alternant.findByCleQr", Alternant.class);
        try {
            q.setParameter("cleQr", cleQr);
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks if an Alternant already exists in the DB
     * @param nom: Surname of the Alternant
     * @param prenom: First name of the Alternant
     * @return true if specified Alternant already exists in the DB
     */
    public boolean existeAlternant(String nom, String prenom){
        Query query = em.createQuery(
                "Select a " +
                        "from Alternant a " +
                        "where a.nom=:nom "+
                        "and a.prenom=:prenom")
                .setParameter("nom", nom)
                .setParameter("prenom", prenom);
        return query.getResultList().size()!=0;
    }
}
