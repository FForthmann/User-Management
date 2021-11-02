package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Postcode {

    @Id
    private Long postcode;
    @Column(nullable = false)
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
