package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.postgresql.util.PSQLException;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Club;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

@Stateless
public class DAOInscrit implements DAOCrud {
    @PersistenceContext(unitName = "pu-sportify")
    private EntityManagerFactory factory;
    private static final long serialVersionUID = 1L;

    public DAOInscrit(EntityManagerFactory factory) {
        this.factory = factory;
    }

    /**
     * Ajouter un utilisateur à la table inscrit dans la BD.
     * 
     * @param objet une instance Inscrit
     * @return Un booléen qui est true si l'utilisateur est créé, false sinon
     */
    @Override
    public boolean creer(Object objet) throws DAOException {
        // créer une instance de EntityManager pour lancer une transaction
        EntityManager entitymanager = factory.createEntityManager();
        Inscrit inscrit = null;
        if (objet instanceof Inscrit) {
            inscrit = (Inscrit) objet;
        }
        try {
            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Modification de la table
            entitymanager.persist(inscrit);

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

    /**
     * Recherche un utilisateur par son Email (clé primaire), donc on utilise find à
     * la place de createQuery.
     * 
     * @param object Email sous forme d'une chaine de caractères
     * @return Une instance de la classe inscrit s'il existe, null sinon
     */
    @Override
    public Object afficher(Object object) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        String email = null;
        Inscrit inscrit = null;
        if (object instanceof String) {
            email = (String) object;
        }
        try {
            inscrit = (Inscrit) entitymanager.find(Inscrit.class, email);
        } catch (NoResultException e) {
            System.out.println("User not found");
        } catch (Exception e) {
            System.out.println("User not found");
        } finally {
            entitymanager.close();
        }
        return inscrit;
    }

    @Override
    public void update(Object object, Object id) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        String query = "UPDATE Inscrit " + "SET nominscrit=:nominscrit, emailInscrit=:emailinscrit,password=:password,"
                + "telinscrit=:telinscrit, naissanceinscrit=:naissanceinscrit, dateinscription =: dateinscription, club.emailclub =: emailclub " + " WHERE emailInscrit=:emailinscrit";
        Inscrit inscrit = null;
        String mailclub = null;
        if (object instanceof Inscrit) {
            inscrit = (Inscrit) object;
        }
        if (id instanceof String) {
            mailclub = (String) id;
        }
        try {

            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Modification de la table
             entitymanager.createQuery(query).setParameter("nominscrit", inscrit.getNomInscrit())
                    .setParameter("emailinscrit", inscrit.getEmailInscrit())
                    .setParameter("password", inscrit.getPassword())
                    .setParameter("telinscrit", inscrit.getTelInscrit())
                    .setParameter("naissanceinscrit", inscrit.getNaissanceInscrit())
                    .setParameter("dateinscription", inscrit.getDateInscription())
                    .setParameter("emailclub", mailclub).executeUpdate();

            // Mise à jours de la table
            entitymanager.getTransaction().commit();
//            System.out.println(updateContent);

        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            entitymanager.close();
        }


    }

    /**
     * Suppression d'un utilisateur de la table inscrit dans la BD.
     * 
     * @param object Un email sous forme de chaine de caractère
     * @return 0 si la suppression est un un succès, -1 sinon
     */
    @Override
    public int delete(Object object) throws DAOException {
        EntityManager entitymanager = factory.createEntityManager();
        String email = null;
        if (object instanceof String) {
            email = (String) object;
        }
        try {
            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Chercher un utilisateur par son email
            Inscrit inscrit = entitymanager.find(Inscrit.class, email);

            if (inscrit != null) {
                // suppression de l'inscrit et mise à jours de la table
                entitymanager.remove(inscrit);
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
     * Afficher la liste de tous les utilisateur.
     * 
     * @return Une liste contenant tous les utilisateurs dans la table Inscrit.
     */

    public List<Inscrit> afficherAll() {
        EntityManager entitymanager = factory.createEntityManager();
        String query = "SELECT i FROM Inscrit i";
        List<Inscrit> userList = null;
        try {
            userList = entitymanager.createQuery(query).getResultList();
            for (Inscrit user : userList) {
                System.out.println(user.getEmailInscrit());
            }
        } catch (DAOException e) {
            System.out.println("A error has occured");
        }
        return userList;
    }

    /**
     * Adherer à un club et mettre la table inscrit à jours.
     * 
     * @param club    une instance de club
     * @param inscrit email de l'utilisateur
     */
    public boolean adherer(Club club, Inscrit inscrit) {
        EntityManager entitymanager = factory.createEntityManager();

        try {
            // Lancer une transaction
            entitymanager.getTransaction().begin();

            // mettre à jours la colonne club
            inscrit.setclub(club);

            // Mise à jours de la table
            entitymanager.getTransaction().commit();
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
