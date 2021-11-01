package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
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
