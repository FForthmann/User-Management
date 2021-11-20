package de.nordakademie.util;

public final class ExceptionMessages {
    public static final String USER_CREATION_FAILED =
            "Die Erstellung des Benutzers ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String USER_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung des Benutzers ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String USER_NOT_FOUND_WHEN_DELETE = "Der Benutzer kann nicht gelöscht werden, da er in der Datenbank nicht existiert.";

    public static final String USER_READ_FAILED = "Der Benutzer kann nicht abgerufen werden, da er in der Datenbank nicht existiert.";

    public static final String USER_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung des Benutzers ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String USER_NOT_FOUND_WHEN_UPDATE = "Der Benutzer kann nicht aktualisiert werden, da er in der Datenbank nicht existiert.";

    public static final String POSTCODE_CREATION_FAILED =
            "Die Erstellung der Postleitzahl ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String POSTCODE_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung der Postleitzahl ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String POSTCODE_NOT_FOUND_WHEN_DELETE =
            "Die Postleitzahl kann nicht gelöscht werden, da sie in der Datenbank nicht vorhanden ist.";

    public static final String POSTCODE_DELETE_REFERENCE_VIOLATED =
            "Die Postleitzahl kann nicht gelöscht werden, da sie in einer anderen Datenbanktabelle verwendet wird.";

    public static final String POSTCODE_READ_FAILED = "Die Postleitzahl kann nicht abgerufen werden, da sie nicht in der Datenbank vorhanden ist.";

    public static final String POSTCODE_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung der Postleitzahl ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String POSTCODE_NOT_FOUND_WHEN_UPDATE =
            "Die Postleitzahl kann nicht aktualisiert werden, da sie nicht in der Datenbank vorhanden ist.";

    public static final String MEMBERTYPE_CREATION_FAILED =
            "Die Erstellung der Mitgliedsart ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String MEMBERTYPE_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung der Mitgliedsart ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String MEMBERTYPE_NOT_FOUND_WHEN_DELETE =
            "Die Mitgliedsart kann nicht gelöscht werden, da sie in der Datenbank nicht existiert.";

    public static final String MEMBERTYPE_DELETE_REFERENCE_VIOLATED =
            "Die Mitgliedsart kann nicht gelöscht werden, da er in einer anderen Datenbanktabelle verwendet wird.";

    public static final String MEMBERTYPE_READ_FAILED = "Die Mitgliedsart kann nicht abgerufen werden, da er in der Datenbank nicht existiert.";

    public static final String MEMBERTYPE_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung der Mitgliedsart ist fehlgeschlagen, da die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String MEMBERTYPE_NOT_FOUND_WHEN_UPDATE =
            "Die Mitgliedsart kann nicht aktualisiert werden, da sie in der Datenbank nicht vorhanden ist.";

    public static final String PAYMENT_CREATION_FAILED =
            "Die Erstellung der Zahlung ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String PAYMENT_DELETE_ILLEGAL_ARGUMENT =
            "Die Löschung der Zahlungen ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String PAYMENT_NOT_FOUND_WHEN_DELETE = "Die Zahlung kann nicht gelöscht werden, da sie in der Datenbank nicht vorhanden ist.";

    public static final String PAYMENT_READ_FAILED = "Die Zahlung kann nicht abgerufen werden, da sie nicht in der Datenbank vorhanden ist.";

    public static final String PAYMENT_UPDATE_ILLEGAL_ARGUMENT =
            "Die Aktualisierung der Zahlung ist fehlgeschlagen, weil die eingegebenen Daten ungültig oder unvollständig sind.";

    public static final String PAYMENT_NOT_FOUND_WHEN_UPDATE =
            "Die Zahlung kann nicht aktualisiert werden, da sie nicht in der Datenbank vorhanden ist.";

    private ExceptionMessages() {
        // Constructor hiding the public one
    }
}
