import com.m1if10.app.dao.AdminScoDao;
import com.m1if10.app.dao.AlternantDao;
import com.m1if10.app.dao.ProfDao;
import com.m1if10.app.modele.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ce programme permet de remplir la BDD avec des données de test.
 */
public class InsertTestData {
    public static void main (String[] args) throws ParseException {

        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        AlternantDao altDao = new AlternantDao(em);
        ProfDao profDao = new ProfDao(em);
        AdminScoDao adminScoDao = new AdminScoDao(em);

        Alternant alt = altDao.createAlternant("etu", "Martin", "Paul", "pm.jpg", "Google","A2","p1809392");
        System.out.println(alt.getPassword());
        Prof prof = profDao.createProf("prof", "Hadi", "Jacques", "jh.jpg");
        Prof prof2 = profDao.createProf("prof2", "Jacques", "Jean", "jj.png");
        AdminSco adminSco = adminScoDao.createAdminSco("sco", "Doe", "Jane", "jd.jpg");

        UE ue = new UE("M1IF10","PROJET SAVAPA");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateCours1 = sdf.parse("2019-11-06 10:30");
        Cours cours = new Cours(ue,"CM","Nautibus C1",dateCours1,"A",2);
        liaisonProfUe lp = new liaisonProfUe(prof,ue,true);
        liaisonProfUe lpfalse = new liaisonProfUe(prof2,ue,false);

        em.getTransaction().begin();
        em.persist(cours);
        em.persist(ue);
        em.persist(lp);
        em.persist(lpfalse);
        em.getTransaction().commit();
        em.close();

        System.exit(0);
    }
}
