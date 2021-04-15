package fr.univ.lyon1.m1if.m1if10Grp13.dao;

import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

public interface DAOCrud {
    /**
     * Ajouter une instance d'une classe donnée à la table de BD correspondante.
     * 
     * @param object une instance de l'objet qu'on veut ajouter à la BD
     * @return true si l'objet à bien était créé, false sinon
     * @throws DAOException
     */
    boolean creer(Object object) throws DAOException;

    /**
     * Chercher un element dans la base de données.
     * 
     * @param object Une instance de l'objet à chercher ou son ID (selon la classe)
     * @return une instance de l'objet demandé, sinon null
     * @throws DAOException
     */
    Object afficher(Object object) throws DAOException;

    /**
     * Mise à jours d'une table dans la BD.
     * 
     * @param object instance de la classe qu'on veut mettre à jours.
     * @throws DAOException
     */
    void update(Object object, Object id) throws DAOException;

    /**
     * Supprimer un tuple de la BD.
     * 
     * @param object instance de la classe ou son ID
     * @param id     l'id du tuple à modifier
     * @return 0 en cas de réussite, -1 sinon
     * @throws DAOException
     */
    int delete(Object object) throws DAOException;

}
