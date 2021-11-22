package de.nordakademie.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.nordakademie.util.ExceptionMessages;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
    @Column(nullable = false)
    @NotEmpty(message = ExceptionMessages.USER_STREET_EMPTY)
    private String street;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_HOUSENUMBER_EMPTY)
    private Integer houseNumber;

    @ManyToOne
    @JsonUnwrapped
    @NotNull(message = ExceptionMessages.USER_POSTCODE_EMPTY)
    private Postcode postalCode;

    public Address(String street, Integer houseNumber, Postcode postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public Address() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Postcode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Postcode postalCode) {
        this.postalCode = postalCode;
    }
}
