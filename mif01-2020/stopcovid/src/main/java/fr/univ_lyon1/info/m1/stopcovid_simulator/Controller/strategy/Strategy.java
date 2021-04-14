package fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy;

public interface Strategy {
    /**
     * The interface to select the strategy we want to use.
     * @param nbContacts number of meets for two users.
     * @return returns true if the strategy is satisfied.
     */
    boolean strategy(int nbContacts);

}
