package de.nordakademie.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Embeddable
public class Address {

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer houseNumber;
    @ManyToOne
    @JsonUnwrapped
    private Postcode postalCode;
    @Column(nullable = false)
    private String city;

    public Address(String street, Integer houseNumber, Postcode postalCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
