package de.nordakademie.util;

public final class ExceptionMessages {
    public static final String USER_CREATION_FAILED = "The creation of the user failed because the data entered is invalid or incomplete.";

    public static final String USER_DELETE_ILLEGAL_ARGUMENT = "The user's deletion failed because the data entered is invalid or incomplete.";

    public static final String USER_NOT_FOUND_WHEN_DELETE = "The user cannot be deleted because he does not exist in the database.";

    public static final String USER_READ_FAILED = "The user cannot be retrieved because he does not exist in the database.";

    public static final String USER_UPDATE_ILLEGAL_ARGUMENT = "Updating the user failed because the data entered is invalid or incomplete.";

    public static final String USER_NOT_FOUND_WHEN_UPDATE = "The user cannot be updated because he does not exist in the database.";

    public static final String POSTCODE_CREATION_FAILED = "The creation of the postcode failed because the data entered is invalid or incomplete.";

    public static final String POSTCODE_DELETE_ILLEGAL_ARGUMENT =
            "The deletion of the postcode failed because the data entered is invalid or incomplete.";

    public static final String POSTCODE_NOT_FOUND_WHEN_DELETE = "The postcode cannot be deleted because he does not exist in the database.";

    public static final String POSTCODE_DELETE_REFERENCE_VIOLATED = "The postcode cannot be deleted because it is used in another database table.";

    public static final String POSTCODE_READ_FAILED = "The postcode cannot be retrieved because he does not exist in the database.";

    public static final String POSTCODE_UPDATE_ILLEGAL_ARGUMENT = "Updating the postcode failed because the data entered is invalid or incomplete.";

    public static final String POSTCODE_NOT_FOUND_WHEN_UPDATE = "The postcode cannot be updated because he does not exist in the database.";

    private ExceptionMessages() {
        // Constructor to hide the public one
    }
}
