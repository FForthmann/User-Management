package de.nordakademie.model;

import de.nordakademie.util.ExceptionMessages;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Name {
    @Column(nullable = false)
    @NotEmpty(message = ExceptionMessages.USER_FIRST_NAME_EMPTY)
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = ExceptionMessages.USER_LAST_NAME_EMPTY)
    @NotBlank(message = ExceptionMessages.USER_LAST_NAME_EMPTY)
    private String lastName;

    public Name(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public Name() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
