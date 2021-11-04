package de.nordakademie.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

    @Column()
    private LocalDate entryDate;

    @Column()
    private LocalDate cancellationDate;

    @Column()
    private LocalDate leavingDate;

    @Column(nullable = false)
    private String memberType;

    @Column(nullable = false)
    private Integer accountDetails;

    @Column()
    private Long familyId;

    public User(Long userId,
                Name name, Address address, LocalDate birthday,
                LocalDate entryDate,
                LocalDate cancellationDate,
                LocalDate leavingDate,
                String memberType,
                Integer accountDetails,
                Long familyId) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.entryDate = entryDate;
        this.cancellationDate = cancellationDate;
        this.leavingDate = leavingDate;
        this.memberType = memberType;
        this.accountDetails = accountDetails;
        this.familyId = familyId;
    }

    public User() {
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(final LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

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
                ", leavingDate=" + leavingDate +
                ", memberType='" + memberType + '\'' +
                ", accountDetails=" + accountDetails +
                ", familyId=" + familyId +
                '}';
    }
}
