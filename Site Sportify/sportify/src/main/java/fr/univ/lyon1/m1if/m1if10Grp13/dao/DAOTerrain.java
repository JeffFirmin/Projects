package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Admin;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Publication;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Terrain;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;
import org.hibernate.dialect.function.TemplateRenderer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DAOTerrain implements DAOCrud {

    private static final String JPQL_SELECT_PAR_IDTERRAIN = "SELECT t FROM Terrain t WHERE t.terrainId=:terrrainId";
    private static final String PARAM_IDTERRAIN = "terrainId";

    private EntityManagerFactory factory;

    public DAOTerrain(EntityManagerFactory factory) {
        this.factory = factory;
    }


    /**
     * Creer et ajouter un terrain à la BD.
     * @param object Une instance d'un terrain
     * @return booléen qui indique si la création est un succés
     */
    @Override
    public boolean creer(Object object) throws DAOException {
        EntityManager entitymanager = this.factory.createEntityManager();
        Terrain terrain = null;
        if (object instanceof Terrain)
            terrain    = (Terrain) object;


        try {

            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Modification de la table
            entitymanager.persist(terrain);

            // Mise à jours de la table
            entitymanager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // Femeture de l'objet ntityManager
            entitymanager.close();
        }

    }

    /**
     * Rechercher un terrain par son ID.
     * 
     * @param object un Long qui représente l'ID du terrain
     * @return un objet de type Terrain
     */
    @Override
    public Object afficher(Object object) throws DAOException {
        // Creation d'un EntityManager
        EntityManager entitymanager = this.factory.createEntityManager();
        Terrain terrain = null;
        Long terrainId = null;

        // verifier si l'objet passé est de type Long
        if (object instanceof Integer) {
            terrainId = (Long) object;
        }

        try {
            terrain = (Terrain) entitymanager.find(Terrain.class, terrainId);
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terrain;
    }

    @Override
    public void update(Object object, Object id) throws DAOException {
        // TODO Auto-generated method stub

    }

    /**
     * Supprimer un terrain par son id
     * 
     * @param object Id du terrain return -1 si la suppression echoue, 0 sinon
     */
    @Override
    public int delete(Object object) throws DAOException {
        EntityManager entitymanager = this.factory.createEntityManager();
        Long terrainId = (long) -1;
        if (object instanceof Long) {
            terrainId = (Long) object;
        }
        try {
            // Lancement d'une transaction
            entitymanager.getTransaction().begin();

            // Chercher un utilisateur par son email
            Terrain terrain = entitymanager.find(Terrain.class, terrainId);

            if (terrain != null) {
                // suppression de l'inscrit et mise à jours de la table
                entitymanager.remove(terrain);
                entitymanager.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println("Canno't delete terrain");
            return -1;
        } finally {
            entitymanager.close();
        }
        return 0;
    }

}
