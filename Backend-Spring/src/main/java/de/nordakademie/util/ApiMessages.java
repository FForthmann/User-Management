package de.nordakademie.util;

/**
 * General API messages.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
public final class ApiMessages {
    /**
     * The constant MSG_NULL.
     */
    public static final String MSG_NULL = "Mindestens ein erforderlicher Wert ist null.";

    /**
     * The constant MEMBERTYPE_NOT_IN_DB.
     */
    public static final String MEMBERTYPE_NOT_IN_DB = "Die angegebene Mitgliedsart existiert nicht.";

    /**
     * The constant USER_NOT_IN_DB.
     */
    public static final String USER_NOT_IN_DB = "Der angegebene Benutzer existiert nicht.";

    /**
     * The constant ENTITY_NOT_EXISTS.
     */
    public static final String ENTITY_NOT_EXISTS = "Die Entität existiert nicht.";

    /**
     * Constructor to hide the public one.
     */
    private ApiMessages() {
        // Empty constructor
    }

}
