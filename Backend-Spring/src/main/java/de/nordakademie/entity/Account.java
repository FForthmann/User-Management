package de.nordakademie.entity;


import javax.persistence.*;
import java.util.Date;

@Entity(name ="Account")
@Table(name = "account")
public class Account {


    private Long userId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String a;

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

    public Account() {
    }
}
