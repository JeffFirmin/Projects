package fr.univ.lyon1.m1if.m1if10Grp13;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class JPATEST {

    @Test
    public void setupEMTest() {
        EntityManager em = Persistence.createEntityManagerFactory("pu-sportify").createEntityManager();
        em.close();

    }
}
