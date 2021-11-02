package de.nordakademie.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;


@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    @Column(
            updatable = false,
            nullable = false)
    private Long userId;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private LocalDate entryDate;

    @Column(nullable = false)
    private LocalDate cancellationDate;

    @Column(nullable = false)
    private String memberType;

    @Column(nullable = false)
    private Integer accountDetails;

    @Column(nullable = false)
    private Long familyId;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User(Long userId,
                Name name, Address address, LocalDate birthday,
                LocalDate entryDate,
                LocalDate cancellationDate,
                String memberType,
                Integer accountDetails,
                Long familyId) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.entryDate = entryDate;
        this.cancellationDate = cancellationDate;
        this.memberType = memberType;
        this.accountDetails = accountDetails;
        this.familyId = familyId;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Integer getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(Integer accountDetails) {
        this.accountDetails = accountDetails;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name=" + name +
                ", address=" + address +
                ", birthday=" + birthday +
                ", entryDate=" + entryDate +
                ", cancellationDate=" + cancellationDate +
                ", memberType='" + memberType + '\'' +
                ", accountDetails=" + accountDetails +
                ", familyId=" + familyId +
                '}';
    }
}
