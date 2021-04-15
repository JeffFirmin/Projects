package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Club;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class DAOClub implements DAOCrud {
    @PersistenceContext(unitName = "pu-sportify")
    private EntityManagerFactory factory;

    public DAOClub(EntityManagerFactory factory) {
        this.factory = factory;
    }

    /**
     * {@inheritDoc} Ajouter un club à la table club dans la BD.
     */
    @Override
    public boolean creer(Object objet) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        Club club = null;
        if (objet instanceof Club) {
            club = (Club) objet;
        }
        try {
            entitymanager.persist(club);
            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Modification de la table
            entitymanager.persist(club);

            // Mise à jours de la table
            entitymanager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Femeture de l'objet ntityManager
            entitymanager.close();
        }

    }

    /**
     * {@inheritDoc} chercher un club par son Email.
     */
    @Override
    public Object afficher(Object object) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        String emailClub = null;
        Club club = null;
        if (object instanceof String) {
            emailClub = (String) object;
        }
        try {
            club = entitymanager.find(Club.class, emailClub);
        } catch (NoResultException e) {
            System.out.println("User not found");
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            entitymanager.close();
        }
        return club;
    }

    /**
     * {@inheritDoc} Mise à jours d'un club.
     */
    @Override
    public void update(Object object, Object id) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        String query = "UPDATE Club " + "SET nomclub=:nomclub, emailclub=:emailclub,passwordclub=:passwordclub,"
                + "nomresponsable=:nomresponsable, nbmaxadherent=:nbmax " + " WHERE emailclub=:email";
        Club club = null;
        String idClub = null;
        if (object instanceof Club) {
            club = (Club) object;
        }
        if (id instanceof String) {
            idClub = (String) id;
        }
        try {

            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Modification de la table
            int updateContent = entitymanager.createQuery(query).setParameter("nomclub", club.getNomClub())
                    .setParameter("emailclub", club.getEmailClub()).setParameter("passwordclub", club.getPasswordClub())
                    .setParameter("nomresponsable", club.getNomResponsable())
                    .setParameter("nbmax", club.getNbMaxAdherent()).setParameter("email", idClub).executeUpdate();

            // Mise à jours de la table
            entitymanager.getTransaction().commit();
            System.out.println(updateContent);

        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            entitymanager.close();
        }

    }

    /**
     * Suppression d'un club de la table club dans la BD.
     * 
     * @param object email sous forme de chaine de caractère
     * @return 0 si la suppression est un un succès, -1 sinon
     */
    @Override
    public int delete(Object object) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        String emailClub = null;
        if (object instanceof String) {
            emailClub = (String) object;
        }
        try {
            // Démarer une transaction
            entitymanager.getTransaction().begin();

            // Recherche le club par son email
            Club club = (Club) entitymanager.find(Club.class, emailClub);

            // Mergin the entity
            entitymanager.merge(club);

            // Supprimer le club
            entitymanager.remove(club);

            // mettre à jours la table club
            entitymanager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Canno't delete user");
            e.printStackTrace();
        } finally {
            // fermeture de l'EM
            entitymanager.close();
        }
        return 0;
    }

    /**
     * Afficher la liste de tous les clubs.
     * 
     * @return list contenant tous les clubs
     */
    @SuppressWarnings("unchecked")
    public List<Club> afficherAll() {
        EntityManager entitymanager = this.factory.createEntityManager();
        List<Club> listClub = null;
        String query = "SELECT c FROM Club c";

        try {
            listClub = entitymanager.createQuery(query).getResultList();
            return listClub;
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return listClub;
    }

    /**
     * Afficher la liste de tous les adhérents d'un club donné.
     * 
     * @param email du club sous forme d'une chaine de caractères
     * @return Liste contenant tous les adhérents d'un club
     */

    public List<Inscrit> listerAdherents(String email) {
        EntityManager entitymanager = factory.createEntityManager();
        String query = "SELECT i" + " FROM Inscrit i JOIN i.club c WHERE c.emailclub  =:email";
        List<Inscrit> adherents = null;
        try {
            adherents = entitymanager.createQuery(query).setParameter("email", email).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return adherents;
    }
}
