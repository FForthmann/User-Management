package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import de.nordakademie.util.ExceptionMessages;
/**
 * The datamodel for type Postcode.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@Entity
public class Postcode {
    /**
     * The Postcode.
     */
    @Id
    @NotNull(message = ExceptionMessages.POSTCODE_IS_EMPTY)
    private Long postcode;

    /**
     * The Location.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_LOCATION_EMPTY)
    @NotBlank(message = ExceptionMessages.USER_LOCATION_EMPTY)
    private String location;

    /**
     * Constructor instantiates a new Postcode with parameters for hibernate.
     * @param postcode the postcode
     * @param location the location
     */
    public Postcode(Long postcode, String location) {
        this.postcode = postcode;
        this.location = location;
    }

    /**
     * Regular constructor instantiates a new Postcode.
     */
    public Postcode() {
    }

    /**
     * Gets postcode.
     *
     * @return the postcode
     */
    public Long getPostcode() {
        return postcode;
    }

    /**
     * Sets postcode.
     *
     * @param postcode the postcode
     */
    public void setPostcode(Long postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Postcode{" +
                "postcode=" + postcode +
                ", location='" + location + '\'' +
                '}';
    }
}
