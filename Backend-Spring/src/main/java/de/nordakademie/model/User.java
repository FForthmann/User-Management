package de.nordakademie.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.LocalDate;
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

    @Column()
    private LocalDate cancellationDate;

    @Column()
    private LocalDate leavingDate;

    @ManyToOne(optional = false)
    @JsonUnwrapped
    private MemberType memberType;

    @ManyToOne
    private User familyId;

    public User(Long userId,
                Name name, Address address, LocalDate birthday,
                LocalDate entryDate,
                LocalDate cancellationDate,
                LocalDate leavingDate,
                MemberType memberType,
                User familyId) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.entryDate = entryDate;
        this.cancellationDate = cancellationDate;
        this.leavingDate = leavingDate;
        this.memberType = memberType;
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

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public User getFamilyId() {
        return familyId;
    }

    public void setFamilyId(User familyId) {
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
                ", familyId=" + familyId +
                '}';
    }
}
