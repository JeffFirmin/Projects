package fr.univ.lyon1.m1if.m1if10Grp13.daoException;

public class DAOConfigException extends RuntimeException {

    public DAOConfigException() {
        super();
    }

    public DAOConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DAOConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOConfigException(String message) {
        super(message);
    }

    public DAOConfigException(Throwable cause) {
        super(cause);
    }

}
