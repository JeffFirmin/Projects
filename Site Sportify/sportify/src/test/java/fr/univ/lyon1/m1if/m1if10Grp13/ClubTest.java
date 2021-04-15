package fr.univ.lyon1.m1if.m1if10Grp13;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Club;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOClub;
import org.junit.jupiter.api.Test;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ClubTest {

    @Test
    public void creerTest(){
        EntityManager em = Persistence.createEntityManagerFactory("pu-sportify").createEntityManager();
        DAOClub daoClub = new DAOClub(em.getEntityManagerFactory());
        Club club = new Club("toto fc","titi@gmail.com","123546","titi",30);
        Boolean clubCreer = daoClub.creer(club);
        assertThat(clubCreer,is(true));
    }

    @Test
    public void afficherEmailTest(){
        EntityManager em = Persistence.createEntityManagerFactory("pu-sportify").createEntityManager();
        DAOClub daoClub = new DAOClub(em.getEntityManagerFactory());
        Club club = new Club("toto fc","titi@gmail.com","123546","titi",30);
        String clubEmailClub = club.getEmailClub();
        String clubEmailAfficher = (String) daoClub.afficher(club);
        assertThat(clubEmailClub,equalTo("titi@gmail.com"));
        assertThat(clubEmailAfficher,equalTo(null));

    }
    @Test
    public void deleteTest(){
        EntityManager em = Persistence.createEntityManagerFactory("pu-sportify").createEntityManager();
        DAOClub daoClub = new DAOClub(em.getEntityManagerFactory());
        Club club = new Club("toto fc","titi@gmail.com","123546","titi",30);
        int valeur = (int) daoClub.delete(club);
        assertThat(valeur,is(-1));

    }
}
