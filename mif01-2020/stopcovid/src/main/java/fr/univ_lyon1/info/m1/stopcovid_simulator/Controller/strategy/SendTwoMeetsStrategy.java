package fr.univ_lyon1.info.m1.stopcovid_simulator.Controller.strategy;

public class SendTwoMeetsStrategy implements Strategy {

    /**
     * Check if two users met twice.
     * @param nbContacts number of times these two users met.
     * @return returns true if the number oh meets >= 2.
     */
    public boolean strategy(final int nbContacts) {
        return nbContacts >= 2;
    }

    @Override
    public String toString() {
        return "Informer les personnes rencontrées deux fois";
    }

}
