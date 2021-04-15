package fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy;

public class SendAllContactsStrategy implements Strategy {

    /**
     * Check if two users met at least once.
     * @param nbContacts number of meets.
     * @return returns true if number of meets > 0.
     */
    public boolean strategy(final int nbContacts) {
        return nbContacts > 0;
    }

    @Override
    public String toString() {
        return "Informer les personnes rencontrées une fois";
    }
}
