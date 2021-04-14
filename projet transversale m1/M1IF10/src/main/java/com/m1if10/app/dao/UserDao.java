package com.m1if10.app.dao;

import com.m1if10.app.classes.CryptageMdp;
import com.m1if10.app.modele.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Class to manage the User model
 */
public class UserDao {
    /**
     * The entity manager to manage changes of User
     */
    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    /**
     * Gets the specified User with User's mail
     * @param email: User's mail
     * @return specified User
     */
    public User getUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return em.find(User.class, email);
    }

    /**
     * Checks if User has changed his password
     * @param mail: Users'mail
     * @param pwd: User's password
     * @return true if User changed the password successfully
     */
    public boolean changePassword (String mail, String pwd) {

        String encrypt = CryptageMdp.encrypt(pwd);

        em.getTransaction().begin();
        User user = em.find(User.class, mail);
        user.setPassword(encrypt);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Query query2 = em.createQuery("select u.password from User u where u.email = :mail")
                .setParameter("mail",mail);
        em.getTransaction().commit();

        List tmp = query2.getResultList();
        if(((String)tmp.get(0)).equals(encrypt)) {
            return true;
        }
        else return false;
    }

}
