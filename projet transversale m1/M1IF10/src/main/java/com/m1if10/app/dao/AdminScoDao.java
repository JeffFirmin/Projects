package com.m1if10.app.dao;

import com.m1if10.app.modele.AdminSco;

import javax.persistence.EntityManager;

/**
 * Class to manage the AdminSco model
 */
public class AdminScoDao {
    /**
     * The entity manager to manage changes of AdminSco
     */
    private EntityManager em;

    public AdminScoDao(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates the AdminSco model
     * @param password: AdminSco's password
     * @param nom: AdminSco's Surname
     * @param prenom: AdminSco's First name
     * @param urlPhoto: AdminSco's photo's URL
     * @return AdminSco model
     */
    public AdminSco createAdminSco(String password, String nom, String prenom, String urlPhoto) {
        em.getTransaction().begin();
        String email = nom +"."+prenom+"@univ-lyon1.fr";
        AdminSco adminSco = new AdminSco(email, password, nom, prenom, urlPhoto);
        em.persist(adminSco);
        em.getTransaction().commit();
        return adminSco;
    }
}
