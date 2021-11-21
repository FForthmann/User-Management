package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import de.nordakademie.util.ExceptionMessages;
@Entity
public class Postcode {
    @Id
    @NotNull(message = "Die Postleitzahl darf nicht leer sein.")
    private Long postcode;

    @Column(nullable = false)
    @NotNull(message = "Das Feld 'Location' darf nicht leer sein.")
    @NotBlank(message = "Das Feld 'Location' darf nicht leer sein.")
    private String location;

    public Postcode(Long postcode, String location) {
        this.postcode = postcode;
        this.location = location;
    }

    public Postcode() {
    }

    public Long getPostcode() {
        return postcode;
    }

    public void setPostcode(Long postcode) {
        this.postcode = postcode;
    }

    public String getLocation() {
        return location;
    }

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
