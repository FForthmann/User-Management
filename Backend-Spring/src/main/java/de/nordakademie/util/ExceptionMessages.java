package de.nordakademie.util;

/**
 * Detailed API messages for Exceptions.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
public final class ExceptionMessages {
    /**
     * The constant USER_CREATION_FAILED.
     */
    public static final String USER_CREATION_FAILED =
            "Die Erstellung des Benutzers ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant USER_DELETE_ILLEGAL_ARGUMENT.
     */
    public static final String USER_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung des Benutzers ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant USER_NOT_FOUND_WHEN_DELETE.
     */
    public static final String USER_NOT_FOUND_WHEN_DELETE = "Der Benutzer kann nicht gelöscht werden, da er in der Datenbank nicht existiert.";

    /**
     * The constant USER_READ_FAILED.
     */
    public static final String USER_READ_FAILED = "Der Benutzer kann nicht abgerufen werden, da er in der Datenbank nicht existiert.";

    /**
     * The constant USER_UPDATE_ILLEGAL_ARGUMENT.
     */
    public static final String USER_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung des Benutzers ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant USER_NOT_FOUND_WHEN_UPDATE.
     */
    public static final String USER_NOT_FOUND_WHEN_UPDATE = "Der Benutzer kann nicht aktualisiert werden, da er in der Datenbank nicht existiert.";

    /**
     * The constant POSTCODE_CREATION_FAILED.
     */
    public static final String POSTCODE_CREATION_FAILED =
            "Die Erstellung der Postleitzahl ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant POSTCODE_DELETE_ILLEGAL_ARGUMENT.
     */
    public static final String POSTCODE_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung der Postleitzahl ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant POSTCODE_NOT_FOUND_WHEN_DELETE.
     */
    public static final String POSTCODE_NOT_FOUND_WHEN_DELETE =
            "Die Postleitzahl kann nicht gelöscht werden, da sie in der Datenbank nicht vorhanden ist.";

    /**
     * The constant POSTCODE_DELETE_REFERENCE_VIOLATED.
     */
    public static final String POSTCODE_DELETE_REFERENCE_VIOLATED =
            "Die Postleitzahl kann nicht gelöscht werden, da sie in einer anderen Datenbanktabelle verwendet wird.";

    /**
     * The constant POSTCODE_READ_FAILED.
     */
    public static final String POSTCODE_READ_FAILED = "Die Postleitzahl kann nicht abgerufen werden, da sie nicht in der Datenbank vorhanden ist.";

    /**
     * The constant POSTCODE_UPDATE_ILLEGAL_ARGUMENT.
     */
    public static final String POSTCODE_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung der Postleitzahl ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant POSTCODE_NOT_FOUND_WHEN_UPDATE.
     */
    public static final String POSTCODE_NOT_FOUND_WHEN_UPDATE =
            "Die Postleitzahl kann nicht aktualisiert werden, da sie nicht in der Datenbank vorhanden ist.";

    /**
     * The constant MEMBERTYPE_CREATION_FAILED.
     */
    public static final String MEMBERTYPE_CREATION_FAILED =
            "Die Erstellung der Mitgliedsart ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant MEMBERTYPE_DELETE_ILLEGAL_ARGUMENT.
     */
    public static final String MEMBERTYPE_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung der Mitgliedsart ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant MEMBERTYPE_NOT_FOUND_WHEN_DELETE.
     */
    public static final String MEMBERTYPE_NOT_FOUND_WHEN_DELETE =
            "Die Mitgliedsart kann nicht gelöscht werden, da sie in der Datenbank nicht existiert.";

    /**
     * The constant MEMBERTYPE_DELETE_REFERENCE_VIOLATED.
     */
    public static final String MEMBERTYPE_DELETE_REFERENCE_VIOLATED =
            "Die Mitgliedsart kann nicht gelöscht werden, da er in einer anderen Datenbanktabelle verwendet wird.";

    /**
     * The constant MEMBERTYPE_READ_FAILED.
     */
    public static final String MEMBERTYPE_READ_FAILED = "Die Mitgliedsart kann nicht abgerufen werden, da er in der Datenbank nicht existiert.";

    /**
     * The constant MEMBERTYPE_UPDATE_ILLEGAL_ARGUMENT.
     */
    public static final String MEMBERTYPE_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung der Mitgliedsart ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant MEMBERTYPE_NOT_FOUND_WHEN_UPDATE.
     */
    public static final String MEMBERTYPE_NOT_FOUND_WHEN_UPDATE =
            "Die Mitgliedsart kann nicht aktualisiert werden, da sie in der Datenbank nicht vorhanden ist.";

    /**
     * The constant PAYMENT_CREATION_FAILED.
     */
    public static final String PAYMENT_CREATION_FAILED =
            "Die Erstellung der Zahlung ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant PAYMENT_DELETE_ILLEGAL_ARGUMENT.
     */
    public static final String PAYMENT_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung der Zahlungen ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant PAYMENT_NOT_FOUND_WHEN_DELETE.
     */
    public static final String PAYMENT_NOT_FOUND_WHEN_DELETE = "Die Zahlung kann nicht gelöscht werden, da sie in der Datenbank nicht vorhanden ist.";

    /**
     * The constant PAYMENT_READ_FAILED.
     */
    public static final String PAYMENT_READ_FAILED = "Die Zahlung kann nicht abgerufen werden, da sie nicht in der Datenbank vorhanden ist.";

    /**
     * The constant PAYMENT_UPDATE_ILLEGAL_ARGUMENT.
     */
    public static final String PAYMENT_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung der Zahlung ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    /**
     * The constant PAYMENT_NOT_FOUND_WHEN_UPDATE.
     */
    public static final String PAYMENT_NOT_FOUND_WHEN_UPDATE =
            "Die Zahlung kann nicht aktualisiert werden, da sie nicht in der Datenbank vorhanden ist.";

    /**
     * The constant USER_STREET_NOT_TEXT.
     */
    public static final String USER_STREET_NOT_TEXT = "Das Feld 'Straße' darf nur aus einer Zeichenkette bestehen.";

    /**
     * The constant USER_FIRST_NAME_NOT_TEXT.
     */
    public static final String USER_FIRST_NAME_NOT_TEXT = "Das Feld 'Vorname' darf nur aus einer Zeichenkette bestehen.";

    /**
     * The constant USER_LAST_NAME_NOT_TEXT.
     */
    public static final String USER_LAST_NAME_NOT_TEXT = "Das Feld 'Nachname' darf nur aus einer Zeichenkette bestehen.";

    /**
     * The constant USER_BIRTHDAY_BEFORE_ENTRY_DATE_OR_IN_FUTURE.
     */
    public static final String USER_BIRTHDAY_BEFORE_ENTRY_DATE_OR_IN_FUTURE =
            "Das Geburtsdatum liegt entweder nicht vor dem Eintrittsdatum oder liegt in der Zukunft.";

    /**
     * The constant USER_ENTRY_DATE_BEFORE_CANCELLATION_DATE.
     */
    public static final String USER_ENTRY_DATE_BEFORE_CANCELLATION_DATE = "Das Eintrittsdatum liegt nicht vor dem Kündigungsdatum.";

    /**
     * The constant USER_BIRTHDAY_EMPTY.
     */
    public static final String USER_BIRTHDAY_EMPTY = "Das Feld 'Geburtstag' darf nicht leer sein.";

    /**
     * The constant USER_ENTRY_DATE_EMPTY.
     */
    public static final String USER_ENTRY_DATE_EMPTY = "Das Feld 'Eintrittsdatum' darf nicht leer sein.";

    /**
     * The constant USER_MEMBER_TYPE_EMPTY.
     */
    public static final String USER_MEMBER_TYPE_EMPTY = "Das Feld 'Mitgliedsart' darf nicht leer sein.";

    /**
     * The constant USER_FIRST_NAME_EMPTY.
     */
    public static final String USER_FIRST_NAME_EMPTY = "Das Feld 'Vorname' darf nicht leer sein.";

    /**
     * The constant USER_LAST_NAME_EMPTY.
     */
    public static final String USER_LAST_NAME_EMPTY = "Das Feld 'Nachname' darf nicht leer sein.";

    /**
     * The constant USER_STREET_EMPTY.
     */
    public static final String USER_STREET_EMPTY = "Das Feld 'Straße' darf nicht leer sein.";

    /**
     * The constant USER_HOUSENUMBER_EMPTY.
     */
    public static final String USER_HOUSENUMBER_EMPTY = "Das Feld 'Hausnummer' darf nicht leer sein.";

    /**
     * The constant USER_POSTCODE_EMPTY.
     */
    public static final String USER_POSTCODE_EMPTY = "Das Feld 'Postleitzahl' darf nicht leer sein.";

    /**
     * The constant USER_POSTCODE_NOT_FIVE_DIGITS.
     */
    public static final String USER_POSTCODE_NOT_FIVE_DIGITS = "Das Feld 'Postleitzahl' darf nur aus fünf Zahlen bestehen.";

    /**
     * The constant USER_LOCATION_ONLY_TEXT.
     */
    public static final String USER_LOCATION_ONLY_TEXT = "Das Feld 'Ort' darf nur aus einer Zeichenkette bestehen.";

    /**
     * The constant USER_LOCATION_EMPTY.
     */
    public static final String USER_LOCATION_EMPTY = "Das Feld 'Ort' darf nicht leer sein.";

    /**
     * The constant MEMBER_TYPE_AMOUNT_EMPTY.
     */
    public static final String MEMBER_TYPE_AMOUNT_EMPTY = "Das Feld 'Betrag' darf nicht leer sein.";

    /**
     * The constant USER_NAME_EMPTY.
     */
    public static final String USER_NAME_EMPTY = "Das Objekt 'Name' besteht aus den Feldern 'Vorname' & 'Nachname' und darf nicht leer sein.";

    /**
     * The constant USER_ADDRESS_EMPTY.
     */
    public static final String USER_ADDRESS_EMPTY =
            "Das Objekt 'Adresse' besteht aus den Feldern 'Straße', 'Hausnummer', & 'Postleitzahl' und darf nicht leer sein.";

    /**
     * The constant PAYMENT_COUNT_STATUS_EMPTY.
     */
    public static final String PAYMENT_COUNT_STATUS_EMPTY = "Das Feld 'Zahlstatus' darf nicht leer sein.";

    /**
     * The constant PAYMENT_AMOUNT_EMPTY.
     */
    public static final String PAYMENT_AMOUNT_EMPTY = "Das Feld 'Betrag' darf nicht leer sein.";

    /**
     * The constant PAYMENT_YEAR_EMPTY.
     */
    public static final String PAYMENT_YEAR_EMPTY = "Das Feld 'Jahr' darf nicht leer sein.";

    /**
     * The constant PAYMENT_IBAN_EMPTY.
     */
    public static final String PAYMENT_IBAN_EMPTY = "Das Feld 'IBAN' darf nicht leer sein.";

    /**
     * The constant POSTCODE_IS_EMPTY.
     */
    public static final String POSTCODE_IS_EMPTY = "Das Feld 'Postleitzahl' darf nicht leer sein.";

    /**
     * The constant IBAN_NOT_VALID.
     */
    public static final String IBAN_NOT_VALID = "Das Feld 'IBAN' darf nur aus 22 Zeichen bestehen: zwei Buchstaben am Anfang und 20 Zahlen.";

    /**
     * The constant CYCLIC_FAMILY_ID_REFERENCE.
     */
    public static final String CYCLIC_FAMILY_ID_REFERENCE = "Der Benutzer kann nicht auf sich selber als Familienmitglied verweisen.";

    /**
     * The constant YOUTH_ACCOUNT_MEMBER_IS_ADULT.
     */
    public static final String YOUTH_ACCOUNT_MEMBER_IS_ADULT = "Der Benutzer ist bereits erwachsen und kann kein Jugendkonto einrichten.";

    /**
     * The constant YOUTH_VALID_MEMBER_TYPES.
     */
    public static final String YOUTH_VALID_MEMBER_TYPES =
            "Der Benutzer ist jünger als 18. Deshalb kann nur ein Fördermitglied und Jugendkonto erstellt werden.";

    /**
     * The constant CHANGE_SAME_MEMBERTYPE.
     */
    public static final String CHANGE_SAME_MEMBERTYPE = "Die Mitgliedsart kann nicht auf die gleiche Mitgliedsart gewechselt werden.";

    /**
     * The constant FAMILY_MEMBER_DOES_NOT_EXIST.
     */
    public static final String FAMILY_MEMBER_DOES_NOT_EXIST = "Der Familienangehörige existiert nicht.";

    /**
     * Constructor to hide the public one.
     */
    private ExceptionMessages() {
        // Empty constructur
    }
}
