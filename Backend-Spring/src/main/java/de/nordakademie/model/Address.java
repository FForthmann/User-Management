package de.nordakademie.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.nordakademie.util.ExceptionMessages;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * The datamodel for type Address.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@Embeddable
public class Address {

    /**
     * The Street.
     */
    @Column(nullable = false)
    @NotEmpty(message = ExceptionMessages.USER_STREET_EMPTY)
    private String street;

    /**
     * The House number.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_HOUSENUMBER_EMPTY)
    private Integer houseNumber;

    /**
     * The Postal code.
     */
    @ManyToOne
    @JsonUnwrapped
    @NotNull(message = ExceptionMessages.USER_POSTCODE_EMPTY)
    private Postcode postalCode;

    /**
     * Instantiates a new Address.
     *
     * @param street      the street
     * @param houseNumber the house number
     * @param postalCode  the postal code
     */
    public Address(String street, Integer houseNumber, Postcode postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    /**
     * Instantiates a new Address.
     */
    public Address() {

    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets house number.
     *
     * @return the house number
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets house number.
     *
     * @param houseNumber the house number
     */
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public Postcode getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(Postcode postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", postalCode=" + postalCode +
                '}';
    }
}
