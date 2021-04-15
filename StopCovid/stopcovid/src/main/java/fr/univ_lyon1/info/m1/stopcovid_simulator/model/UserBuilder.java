package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import java.util.HashMap;
import java.util.Map;

public class UserBuilder {
    private Map<String, User> users = new HashMap<>();;

    /**
     * add a user to this.user map.
     * @param user the user we want to create, as String.
     * @return this builder, with the new user added.
     */
    public UserBuilder add(final String user) {
        users.put(user, new User(user));
        return this;
    }

    /**
     * Create the map.
     * @return returns the map of users.
     */
    public Map<String, User> build() {
        return users;
    }
}
