package de.nordakademie.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.nordakademie.util.ExceptionMessages;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "User.existsFamilyIdByUserId", query = "SELECT EXISTS (SELECT * FROM USER WHERE FAMILY_ID_USER_ID = :userId)")
})
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

    @Column(name = "user_Id",
            updatable = false,
            nullable = false)
    private Long userId;

    @Valid
    @Embedded
    private Name name;

    @Valid
    @Embedded
    private Address address;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_BIRTHDAY_EMPTY)
    private LocalDate birthday;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_ENTRY_DATE_EMPTY)
    private LocalDate entryDate;

    @Column()
    private LocalDate cancellationDate;

    @Column()
    private LocalDate leavingDate;

    @ManyToOne(optional = false)
    @NotNull(message = ExceptionMessages.USER_MEMBER_TYPE_EMPTY)
    @JsonUnwrapped
    private MemberType memberType;

    @ManyToOne()
    @JoinColumn(nullable = true)
    private MemberType memberTypeChange;

    @ManyToOne
    private User familyId;

    @Column(nullable = false)
    @NotBlank(message = "Das Feld 'Bankdaten' darf nicht leer sein.")
    private String bankAccountDetails;

    @Column()
    private Double actualAmount;

    public User(Long userId,
                Name name,
                Address address,
                LocalDate birthday,
                LocalDate entryDate,
                LocalDate cancellationDate,
                LocalDate leavingDate,
                MemberType memberType,
                MemberType memberTypeChange, User familyId, String bankAccountDetails, Double actualAmount) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.entryDate = entryDate;
        this.cancellationDate = cancellationDate;
        this.leavingDate = leavingDate;
        this.memberType = memberType;
        this.memberTypeChange = memberTypeChange;
        this.familyId = familyId;
        this.bankAccountDetails = bankAccountDetails;
        this.actualAmount = actualAmount;
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
                ", memberType=" + memberType +
                ", memberTypeChange=" + memberTypeChange +
                ", familyId=" + familyId +
                ", bankAccountDetails='" + bankAccountDetails + '\'' +
                ", actualAmount=" + actualAmount +
                '}';
    }

    public MemberType getMemberTypeChange() {
        return memberTypeChange;
    }

    public void setMemberTypeChange(MemberType memberTypeChange) {
        this.memberTypeChange = memberTypeChange;
    }

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }
}
