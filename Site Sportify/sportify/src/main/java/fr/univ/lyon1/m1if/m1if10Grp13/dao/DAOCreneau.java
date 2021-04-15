package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Creneau;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.CreneauCompositeKey;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class DAOCreneau implements DAOCrud {

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext(unitName = "pu-sportify")
    private EntityManagerFactory factory;

    public DAOCreneau(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean creer(Object object) throws DAOException {
        EntityManager entitymanager = this.factory.createEntityManager();
        Creneau creneau = null;
        if (object instanceof Creneau)
            creneau = (Creneau) object;

        try {
            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Modification de la table
            entitymanager.persist(creneau);

            // Mise à jours de la table
            entitymanager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Femeture de l'objet ntityManager
            entitymanager.close();
        }
        return true;

    }

    @Override
    public Object afficher(Object object) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        CreneauCompositeKey creneauId = null;
        Creneau creneau;

        if (object instanceof CreneauCompositeKey) {
            creneauId = (CreneauCompositeKey) object;
        } else if (object instanceof Creneau) {
            creneau = (Creneau) object;
        }

        try {
            creneau = (Creneau) entitymanager.find(Creneau.class, creneauId);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return creneau;
    }

    @Override
    public void update(Object object, Object id) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public int delete(Object object) throws DAOException {

        EntityManager entitymanager = factory.createEntityManager();
        CreneauCompositeKey creneauId = null;
        if (object instanceof CreneauCompositeKey) {
            creneauId = (CreneauCompositeKey) object;
        }
        try {
            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Chercher un creneau par son id
            Creneau creneau = entitymanager.find(Creneau.class, creneauId);

            if (creneau != null) {
                // suppression du creneau et mise à jours de la table
                entitymanager.remove(creneau);
                entitymanager.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println("Canno't delete user");
            return -1;
        } finally {
            entitymanager.close();
        }
        return 0;
    }

    /**
     * Afficher la liste de tous les creneaux disponibles.
     * 
     * @return une liste de creneaux.
     */
    @SuppressWarnings("unchecked")
    public List<Creneau> afficherAll() {
        EntityManager entitymanager = this.factory.createEntityManager();
        List<Creneau> creneauList = null;
        String request = "SELECT c FROM Creneau c";
        try {
            creneauList = (List<Creneau>) entitymanager.createQuery(request).getResultList();
            for (Creneau creneau : creneauList) {
                System.out.println(creneau.getDateCreneau());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return creneauList;
    }

    /**
     * Recherche l'id d'un creneau par son heure et date (différente d'afficher, car
     * cella prend des chaine de caractèresen entrée)
     * 
     * @param heure chaine de caractère qui représente l'heure de debut
     * @param date  chaine de caractère representant la date
     * @return un ojet creneau si le creneau cherché est disponible
     */
    @SuppressWarnings("unchecked")
    public Creneau trouverCrenauId(String heure, String date) {
        EntityManager entitymanager = this.factory.createEntityManager();
        List<Creneau> creneau = null;
        try {
            Date heureCreneau = new SimpleDateFormat("hh:mm:ss").parse(heure);
            Date dateCreneau = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
            // trouver l'id d'un terrain par son heure et date
            creneau = entitymanager.createQuery(
                    "SELECT c FROM Creneau c WHERE c.datecreneau  =:datecreneau AND c.heurecreneau  =:heurecreneau")
                    .setParameter("datecreneau", dateCreneau).setParameter("heurecreneau", heureCreneau)
                    .getResultList();
            return creneau.get(0);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
  
       
    }

}
