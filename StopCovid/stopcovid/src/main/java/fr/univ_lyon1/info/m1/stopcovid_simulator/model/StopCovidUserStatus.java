package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

public enum StopCovidUserStatus {
    RISKY {
        @Override
        public String toString() {
            return "A risque";
        }
    },
    INFECTED {
        @Override
        public String toString() {
            return "Infecté";
        }
    },

    NO_RISK {
        @Override
        public String toString() {
            return "Sain";
        }
    }
}
