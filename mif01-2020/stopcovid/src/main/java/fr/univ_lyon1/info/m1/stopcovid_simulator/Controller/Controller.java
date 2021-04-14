package fr.univ_lyon1.info.m1.stopcovid_simulator.Controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy.SendAllContactsStrategy;
import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy.SendTwoMeetsStrategy;
import fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy.Strategy;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.StopCovidUserStatus;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.User;
import javafx.scene.control.Alert;

import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Controller {
    private Map<String, User> users;
    private Vector<String> riskyUsers;
    private Vector<Strategy> strategies;
    private final int selectTopContacts = 10;



    private void initStrategies() {
        strategies = new Vector<>();
        strategies.add(new SendAllContactsStrategy());
        strategies.add(new SendTwoMeetsStrategy());
    }
    /**
     * Constructor of the controller of the whole application, by parameter.
     * @param name name of the user we want to create
     */
    public Controller(final String name) {
        this.users = new HashMap<>();
        this.users.put(name, new User(name));
        this.riskyUsers = new Vector<>();
        initStrategies();
    }

    /**
     *  Constructor of the controller of the whole application, by default.
     */
    public Controller() {
        this.users = new HashMap<>();
        this.riskyUsers = new Vector<>();
        initStrategies();
    }

    /**
     * Constructor of the controller, by parameter.
     * @param users Map of the users for the controller.
     */
    public Controller(final Map<String, User> users) {
        this.users = users;
        this.riskyUsers = new Vector<>();
        initStrategies();
    }

    /**
     * Increase the number of persons seen in the contact list.
     * @param userName name of the first user.
     * @param contactName name of the second user.
     */
    public void increaseRencontre(final String userName, final String contactName) {
        users.get(userName).increaseRencontre(contactName);
    }

    /**
     * Get the userName's name.
     * @param userName name of the user.
     * @return userName's name.
     */
    public String getName(final String userName) {
        return users.get(userName).getName();
    }

    /**
     * Modify the status of the current user.
     * @param userName  name of the user.
     * @param newStatus new status you want to set.
     */
    public void setStatus(final String userName, final StopCovidUserStatus newStatus) {
        users.get(userName).setStatus(newStatus);
    }

    /**
     * List of contacts, for the current user.
     * @param userName name of the user.
     * @return Hashmap of contacts met by the current user.
     */
    public HashMap<String, Integer> getContacts(final String userName) {
        return users.get(userName).getContacts();
    }

    /**
     * get the users of the whole application.
     * @return returns all the users.
     */
    public Map<String, User> getUsers() {
        return users;
    }

    public Vector<String> getRiskyUsers() {
        return riskyUsers;
    }

    public Vector<Strategy> getStrategies() {
        return strategies;
    }
    /**
     * Add or change contacts in userName's contacts Hashmap.
     * @param userName user's contacts.
     * @param key key of the Hashmap,
     *            a string that represents the name of the person
     *            that the current user met.
     * @param value value of the Hashmap,
     *              an integer for the number of times.
     */
    public void addContact(final String userName, final String key, final Integer value) {
        users.get(userName).addContact(key, value);
    }

    /**
     * remove key from userName's contacts.
     * @param userName where to remove the contact.
     * @param key which contact to move.
     */
    public void removeContact(final String userName, final String key) {
        users.get(userName).removeContact(key);
    }

    /**
     * Get the username's status.
     * @param username name of the user.
     * @return returns a status.
     */
    public StopCovidUserStatus getStatus(final String username) {
        return users.get(username).getStatus();
    }

    /**
     * get the status from the datas in the model.
     * @param status status related to a data in the model.
     * @return a StropCovidUserStatus from the model.
     */
    public StopCovidUserStatus getStatusData(final String status) {
        if (status.equals("NO_RISK")) {
            return StopCovidUserStatus.NO_RISK;
        } else if (status.equals("INFECTED")) {
            return StopCovidUserStatus.INFECTED;
        } else {
            return StopCovidUserStatus.RISKY;
        }
    }


    /**
     * get one user among the set of users.
     * @param userName name of the user.
     * @return user's informations.
     */
    public User getUser(final String userName) {
        return users.get(userName);
    }

    /**
     * Action of the meet button.
     * @param a the left user in the combo.
     * @param b the right person in the combo.
     */
    public boolean meetBtnAction(final String a, final String b) {
        if ((a == null || b == null) || (a.equals(b))) {
            meetError();
            return false;
        }
        increaseRencontre(a, b);
        increaseRencontre(b, a);
        return true;
    }

    /**
     * messages when two selected users can't meet (same users, user null, etc... ).
     */
    public void meetError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please select two users that will meet");
        alert.showAndWait();
    }

    /**
     * Action of the "declare infected" button.
     * @param userName the name of the user who is declared infected.
     * @param strat current strategy in the combo.
     */
    public void declareBtnAction(final String userName, final Strategy strat) {
        setStatus(userName, StopCovidUserStatus.INFECTED);

        Set<String> keys = getContacts(userName).keySet(); //get a set of keys of contacts
        List<String> keysList = new ArrayList<>(keys);
        // transform into an array list to get the indexes

        getContacts(userName).forEach((k, v) -> {
            if (strat.strategy(v) && keysList.indexOf(k) < selectTopContacts
                    && !users.get(k).getStatus().equals(StopCovidUserStatus.INFECTED)) {
                if (!riskyUsers.contains(k)) {
                    riskyUsers.add(k);
                }
                setStatus(k, StopCovidUserStatus.RISKY);
            }
        });
    }


    /**
     * Add a user into the controller.
     * @param userName user's name.
     * @param user user's object.
     */
    public void addUser(final String userName, final User user) {
        users.put(userName, user);
    }

    /**
     * Get the number of users in the whole application.
     * @return returns the number of users.
     */
    public int nbUsers() {
        return users.size();
    }


    /**
     * get a random user from the map.
     * @return returns a user as a String.
     */
    public String getRandomUser() {
        final int plage = 5000;
        double random = (Math.random() * plage) % (nbUsers());

        Set<String> keys = users.keySet();
        //get a set of keys of contacts

        List<String> keysList = new ArrayList<>(keys);
        // transform into an array list to get the indexes

        return keysList.get((int) random);
    }
}
