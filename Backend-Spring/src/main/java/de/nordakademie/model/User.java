package de.nordakademie.model;

import java.util.Date;
import javax.persistence.*;

@Entity(name = "User")
public class User {

    @Id
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

    public User(Long userId,
                String firstName,
                String secondName,
                String street,
                Integer houseNumber,
                Integer postalCode,
                Date birthday,
                Date entryDate,
                Date cancellationDate,
                String memberType,
                Integer accountDetails,
                Long familyId) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.birthday = birthday;
        this.entryDate = entryDate;
        this.cancellationDate = cancellationDate;
        this.memberType = memberType;
        this.accountDetails = accountDetails;
        this.familyId = familyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
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
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", postalCode=" + postalCode +
                ", birthday=" + birthday +
                ", entryDate=" + entryDate +
                ", cancellationDate=" + cancellationDate +
                ", memberType='" + memberType + '\'' +
                ", accountDetails=" + accountDetails +
                ", familyId=" + familyId +
                '}';
    }
}
