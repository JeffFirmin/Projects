package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Admin;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DAOAdmin implements DAOCrud {

    private static final String JPQL_SELECT_PAR_NOMADMIN = "SELECT a FROM Admin a WHERE a.nomAdmin=:nomAdmin";
    private static final String PARAM_NOMADMIN = "nomAdmin";

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext(unitName = "pu-sportify")
    private EntityManager em;

    @Override
    public boolean creer(Object object) throws DAOException {
        Admin admin = null;
        if (object instanceof Admin)
            admin = (Admin) object;

        try {
            em.persist(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object afficher(Object object) throws DAOException {
        Admin admin;
        String nomAdmin = null;
        if (object instanceof Admin) {
            admin = (Admin) object;
        } else if (object instanceof String) {
            nomAdmin = (String) object;
        }
        Query requete = em.createQuery(JPQL_SELECT_PAR_NOMADMIN);
        requete.setParameter(PARAM_NOMADMIN, nomAdmin);
        try {
            admin = (Admin) requete.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return admin;
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
