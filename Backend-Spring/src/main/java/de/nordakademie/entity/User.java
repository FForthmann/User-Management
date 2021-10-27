package de.nordakademie.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String secondName;

    private String street;

    private Integer houseNumber;

    private Integer postalCode;

    private Date birthday;

    private Date entryDate;

    private Date cancellationDate;

    private String memberType;

    private Integer accountDetails;

    private Long familyId;

}
