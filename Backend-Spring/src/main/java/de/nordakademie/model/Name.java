package de.nordakademie.model;

import de.nordakademie.util.ExceptionMessages;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * The datamodel for type Name.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@Embeddable
public class Name {
    /**
     * The First name.
     */
    @Column(nullable = false)
    @NotEmpty(message = ExceptionMessages.USER_FIRST_NAME_EMPTY)
    private String firstName;

    /**
     * The Last name.
     */
    @Column(nullable = false)
    @NotEmpty(message = ExceptionMessages.USER_LAST_NAME_EMPTY)
    @NotBlank(message = ExceptionMessages.USER_LAST_NAME_EMPTY)
    private String lastName;

    /**
     * Instantiates a new Name.
     *
     * @param firstName  the first name
     * @param secondName the second name
     */
    public Name(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
    }

    /**
     * Instantiates a new Name.
     */
    public Name() {
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
