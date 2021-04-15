package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Admin;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Evenement;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DAOEvenement implements DAOCrud {

    private static final String JPQL_SELECT_PAR_IDEVENM = "SELECT e FROM Evenement e WHERE e.evenementId=:evenementId";
    private static final String PARAM_IDEVENM = "evenementId";

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext(unitName = "pu-sportify")
    private EntityManager em;

    @Override
    public boolean creer(Object object) throws DAOException {
        Evenement evenement = null;
        if (object instanceof Evenement)
            evenement = (Evenement) object;

        try {

            em.persist(evenement);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Object afficher(Object object) throws DAOException {
        Evenement evenement;
        Long evenementId = null;
        if (object instanceof Evenement) {
            evenement = (Evenement) object;
        } else if (object instanceof Long) {
            evenementId = (Long) object;
        }
        Query requete = em.createQuery(JPQL_SELECT_PAR_IDEVENM);
        requete.setParameter(PARAM_IDEVENM, evenementId);
        try {
            evenement = (Evenement) requete.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return evenement;
    }

    @Override
    public void update(Object object, Object id) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public int delete(Object object) throws DAOException {
        // TODO Auto-generated method stub
        return 0;
    }

}
