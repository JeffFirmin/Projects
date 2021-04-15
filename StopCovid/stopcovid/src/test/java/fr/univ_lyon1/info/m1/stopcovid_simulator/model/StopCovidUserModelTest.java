package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StopCovidUserModelTest {

    public User user;

    @BeforeAll
    static void initAll() {
        System.out.println("starting StopCovidUserModelTest");
    }

    @BeforeEach
    void init() {
        user = new User("user0");
    }

    @Test
    void dummyTestInt() {
        // Given
        Integer i = 0;

        // When
        i++;

        // Then
        assertThat(i, is(1));
    }

    @Test
    void dummyTestIntFailure() {
        // Given
        Integer i = 0;

        // When
        i = i + 42;

        // Then
        assertThat(i, is(42));
    }

    @Test
    public void TestGettersUser() {
        assertEquals (user.toString(), "user0");
        assertEquals (user.getName(),"user0");
        assertEquals(user.getStatus().toString(),"Sain");
        assertTrue(user.getContacts().isEmpty());
    }

    @Test
    public void TestSettersUser() {

        assertTrue(user.getContacts().isEmpty());
        user.addContact("user1",50);
        assertFalse(user.getContacts().isEmpty());
        assertEquals(user.getContacts().get("user1"),50);
        assertNull(user.getContacts().get("user2"));
        user.increaseRencontre("user2");
        assertEquals(user.getContacts().get("user2"),1);
        user.increaseRencontre("user2");
        assertEquals(user.getContacts().get("user2"),2);
        assertNull(user.getContacts().get("user3"));
        assertEquals(user.getStatus().toString(), "Sain");
        user.setStatus(StopCovidUserStatus.INFECTED);
        assertNotEquals(user.getStatus().toString(),"Sain");
        assertEquals(user.getStatus().toString(),"Infecté");
        user.setStatus(StopCovidUserStatus.RISKY);
        assertEquals(user.getStatus().toString(),"A risque");
        user.removeContact("user1");
        assertNull(user.getContacts().get("user1"));
    }

    @Test
    public void SortContactsTest() {
        user.addContact("user1",10);
        user.addContact("user2",1);
        user.addContact("user4",100);
        user.addContact("user3",40);

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(user.getContacts().entrySet());

        assertNotEquals(list.get(0).getValue(),100);
        assertNotEquals(list.get(1).getValue(),40);
        assertNotEquals(list.get(2).getValue(),10);
        assertNotEquals(list.get(3).getValue(), 1);

        user.sortContacts();
        List<Map.Entry<String, Integer>> list2 =
                new LinkedList<Map.Entry<String, Integer>>(user.getContacts().entrySet());

        assertEquals(list2.get(0).getValue(),100);
        assertEquals(list2.get(1).getValue(),40);
        assertEquals(list2.get(2).getValue(),10);
        assertEquals(list2.get(3).getValue(), 1);

    }


    
}