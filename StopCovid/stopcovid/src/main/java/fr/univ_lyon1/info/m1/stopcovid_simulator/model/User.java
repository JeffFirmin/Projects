package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class User {
    private final String name;
    private StopCovidUserStatus status = StopCovidUserStatus.NO_RISK;
    private HashMap<String, Integer> contacts = new HashMap<String, Integer>();

    /**
     * Parameter constructor of a user.
     * @param name name of the user.
     */
    public User(final String name) {
        this.name = name;
    }

    /**
     * add a new meeting in the contact list of current user.
     * @param name
     */
    public void increaseRencontre(final String name) {
        if (contacts.containsKey(name)) {
            contacts.put(name, contacts.get(name) + 1);
        } else {
            contacts.put(name, 1);
        }
    }


    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(final StopCovidUserStatus newStatus) {
        this.status = newStatus;
    }

    public HashMap<String, Integer> getContacts() {
        return contacts;
    }

    /**
     * Add or change contacts in current user's Hashmap.
     * @param key key of the Hashmap,
     *            a string that represents the name of the person
     *            that the current user met.
     * @param value value of the Hashmap,
     *              an integer for the number of times.
     */
    public void addContact(final String key, final Integer value) {
        contacts.put(key, value);
    }

    /**
     * remove a contact.
     * @param key the contact we want to remove.
     */
    public void removeContact(final String key) {
        contacts.remove(key);
    }

    public StopCovidUserStatus getStatus() {
        return status;
    }

    /**
     * Sorts user's contact, by number of meets, + to -.
     */
    public void sortContacts() {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(contacts.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(final Map.Entry<String, Integer> o1,
                               final Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        contacts = temp;
    }
}
