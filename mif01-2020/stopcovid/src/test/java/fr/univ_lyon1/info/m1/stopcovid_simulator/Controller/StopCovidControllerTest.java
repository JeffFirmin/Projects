package fr.univ_lyon1.info.m1.stopcovid_simulator.Controller;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.StopCovidUserStatus;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.User;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.UserBuilder;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.StopCovidUserView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class StopCovidControllerTest {

    private Controller controller;
    private Controller emptyController;

    @BeforeAll
    static void initAll() {

        System.out.println("starting controller tests");
    }

    @BeforeEach
    void init() {

        controller = new Controller("user0");
        emptyController = new Controller();

    }

    @Test
    void ControllerGettersTests() {

        assertNull(emptyController.getUser("user0"));
        assertEquals(controller.getUser("user0").toString(),"user0");
        assertEquals(controller.getUser("user0").getName(),"user0");
        assertEquals(controller.getName("user0"),"user0");
        assertEquals(controller.getStatus("user0").toString(),"Sain");
        assertTrue(controller.getContacts("user0").isEmpty());
        assertEquals(controller.getStrategies().get(0).toString(),"Informer les personnes rencontrées une fois");
        assertEquals(controller.getStrategies().get(1).toString(),"Informer les personnes rencontrées deux fois");

    }

    @Test
    void ControllerSettersTests() {
        assertTrue(controller.getContacts("user0").isEmpty());
        controller.addContact("user0","user1",50);
        assertFalse(controller.getContacts("user0").isEmpty());
        assertEquals(controller.getContacts("user0").get("user1"),50);
        assertNull(controller.getContacts("user0").get("user2"));
        controller.increaseRencontre("user0","user2");
        assertEquals(controller.getContacts("user0").get("user2"),1);
        controller.increaseRencontre("user0","user2");
        assertEquals(controller.getContacts("user0").get("user2"),2);
        assertNull(controller.getContacts("user0").get("user3"));
        assertEquals(controller.getStatus("user0").toString(), "Sain");
        controller.setStatus("user0",StopCovidUserStatus.INFECTED);
        assertNotEquals(controller.getStatus("user0").toString(),"Sain");
        assertEquals(controller.getStatus("user0").toString(),"Infecté");
        controller.setStatus("user0",StopCovidUserStatus.RISKY);
        assertEquals(controller.getStatus("user0").toString(),"A risque");
        controller.removeContact("user0","user1");
        assertNull(controller.getUser("user0").getContacts().get("user1"));
        assertEquals(controller.getStatusData("INFECTED"),StopCovidUserStatus.INFECTED);
        assertEquals(controller.getStatusData("RISKY"),StopCovidUserStatus.RISKY);
        assertEquals(controller.getStatusData("NO_RISK"),StopCovidUserStatus.NO_RISK);
    }


    @Test
    void MeetBtnActionTests() {
        StopCovidUserView a = new StopCovidUserView("user0",controller,1);
        controller.addUser("user2",new User("user2"));
        StopCovidUserView b = new StopCovidUserView("user2",controller,1);
        controller.meetBtnAction(a.getUserName(), b.getUserName());
        assertEquals(controller.getUser(a.getUserName()).getContacts().get("user2"), 1);
        controller.meetBtnAction(a.getUserName(), b.getUserName());
        assertEquals(controller.getUser(a.getUserName()).getContacts().get("user2"), 2);
        assertNull(controller.getUser(a.getUserName()).getContacts().get("user1"));
        controller.addUser("user1",new User("user1"));
        assertEquals(controller.nbUsers(),3);

    }

    @Test
    void userBuilderTests() {
        assertFalse(controller.getUsers().containsKey("user1"));
        assertFalse(controller.getUsers().containsKey("user2"));
        assertFalse(controller.getUsers().containsKey("user3"));
        assertFalse(controller.getUsers().containsKey("user4"));

        Map<String, User> users = new UserBuilder()
                .add("user0")
                .add("user1")
                .add("user2")
                .add("user3")
                .add("user4")
                .build();
        controller = new Controller(users);

        assertTrue(controller.getUsers().containsKey("user1"));
        assertTrue(controller.getUsers().containsKey("user2"));
        assertTrue(controller.getUsers().containsKey("user3"));
        assertTrue(controller.getUsers().containsKey("user4"));
    }

    @Test
    void declareBtnActionFirstStrategyTests() {
        Map<String, User> users = new UserBuilder()
                .add("user0")
                .add("user1")
                .add("user2")
                .add("user3")
                .add("user4")
                .build();
        controller = new Controller(users);
        controller.addContact("user0","user1",10);
        controller.addContact("user0","user2",1);
        controller.addContact("user0","user3",2);
        controller.addContact("user0","user4",0);
        controller.declareBtnAction("user0",controller.getStrategies().get(0));
        assertTrue(controller.getRiskyUsers().contains("user1"));
        assertTrue(controller.getRiskyUsers().contains("user3"));
        assertTrue(controller.getRiskyUsers().contains("user2"));
        assertFalse(controller.getRiskyUsers().contains("user4"));
    }

    @Test
    void declareBtnActionSecondStrategyTests() {
        Map<String, User> users = new UserBuilder()
                .add("user0")
                .add("user1")
                .add("user2")
                .add("user3")
                .add("user4")
                .build();
        controller = new Controller(users);
        controller.addContact("user0","user1",10);
        controller.addContact("user0","user2",1);
        controller.addContact("user0","user3",2);
        controller.addContact("user0","user4",0);
        controller.declareBtnAction("user0",controller.getStrategies().get(1));
        assertTrue(controller.getRiskyUsers().contains("user1"));
        assertTrue(controller.getRiskyUsers().contains("user3"));
        assertFalse(controller.getRiskyUsers().contains("user2"));
        assertFalse(controller.getRiskyUsers().contains("user4"));
    }



}
