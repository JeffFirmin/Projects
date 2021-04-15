package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Publication;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.plaf.PanelUI;

@Stateless
public class DAOPublication implements DAOCrud {

    private static final String JPQL_SELECT_PAR_IDPUB = "SELECT p FROM Publication p WHERE p.publicationId=:publicationId";
    private static final String PARAM_IDPUB = "publicationId";

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext(unitName = "pu-sportify")
    private EntityManager em;

    @Override
    public boolean creer(Object object) throws DAOException {
        Publication publication = null;
        if (object instanceof Publication)
            publication = (Publication) object;

        try {

            em.persist(publication);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Object afficher(Object object) throws DAOException {
        Publication publication;
        Long publicationId = null;
        if (object instanceof Publication) {
            publication = (Publication) object;
        } else if (object instanceof Long) {
            publicationId = (Long) object;
        }
        Query requete = em.createQuery(JPQL_SELECT_PAR_IDPUB);
        requete.setParameter(PARAM_IDPUB, publicationId);
        try {
            publication = (Publication) requete.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return publication;
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
