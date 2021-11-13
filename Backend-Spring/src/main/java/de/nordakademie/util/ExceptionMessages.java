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

    public static final String POSTCODE_NOT_FOUND_WHEN_DELETE = "The postcode cannot be deleted because it does not exist in the database.";

    public static final String POSTCODE_DELETE_REFERENCE_VIOLATED = "The postcode cannot be deleted because it is used in another database table.";

    public static final String POSTCODE_READ_FAILED = "The postcode cannot be retrieved because it does not exist in the database.";

    public static final String POSTCODE_UPDATE_ILLEGAL_ARGUMENT = "Updating the postcode failed because the data entered is invalid or incomplete.";

    public static final String POSTCODE_NOT_FOUND_WHEN_UPDATE = "The postcode cannot be updated because it does not exist in the database.";

    public static final String MEMBERTYPE_CREATION_FAILED =
            "The creation of the membertype failed because the data entered is invalid or incomplete.";

    public static final String MEMBERTYPE_DELETE_ILLEGAL_ARGUMENT =
            "The deletion of the membertype failed because the data entered is invalid or incomplete.";

    public static final String MEMBERTYPE_NOT_FOUND_WHEN_DELETE = "The membertype cannot be deleted because it does not exist in the database.";

    public static final String MEMBERTYPE_DELETE_REFERENCE_VIOLATED =
            "The membertype cannot be deleted because it is used in another database table.";

    public static final String MEMBERTYPE_READ_FAILED = "The membertype cannot be retrieved because it does not exist in the database.";

    public static final String MEMBERTYPE_UPDATE_ILLEGAL_ARGUMENT =
            "Updating the membertype failed because the data entered is invalid or incomplete.";

    public static final String MEMBERTYPE_NOT_FOUND_WHEN_UPDATE = "The membertype cannot be updated because it does not exist in the database.";

    public static final String PAYMENT_CREATION_FAILED = "The creation of the payment failed because the data entered is invalid or incomplete.";

    public static final String PAYMENT_DELETE_ILLEGAL_ARGUMENT = "The payments's deletion failed because the data entered is invalid or incomplete.";

    public static final String PAYMENT_NOT_FOUND_WHEN_DELETE = "The payment cannot be deleted because it does not exist in the database.";

    public static final String PAYMENT_READ_FAILED = "The payment cannot be retrieved because it does not exist in the database.";

    public static final String PAYMENT_UPDATE_ILLEGAL_ARGUMENT = "Updating the payment failed because the data entered is invalid or incomplete.";

    public static final String PAYMENT_NOT_FOUND_WHEN_UPDATE = "The payment cannot be updated because it does not exist in the database.";

    private ExceptionMessages() {
        // Constructor to hide the public one
    }
}
