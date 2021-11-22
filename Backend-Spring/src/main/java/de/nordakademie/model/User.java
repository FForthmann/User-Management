package de.nordakademie.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.nordakademie.util.ExceptionMessages;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * The type User.
 */
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "User.existsFamilyIdByUserId", query = "SELECT EXISTS (SELECT * FROM USER WHERE FAMILY_ID_USER_ID = :userId)")
})
@Entity(name = "User")
@Table(name = "user")
public class User {
    /**
     * The User id.
     */
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

    /**
     * The Name.
     */
    @Valid
    @Embedded
    private Name name;

    /**
     * The Address.
     */
    @Valid
    @Embedded
    private Address address;

    /**
     * The Birthday.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_BIRTHDAY_EMPTY)
    private LocalDate birthday;

    /**
     * The Entry date.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.USER_ENTRY_DATE_EMPTY)
    private LocalDate entryDate;

    /**
     * The Cancellation date.
     */
    @Column()
    private LocalDate cancellationDate;

    /**
     * The Leaving date.
     */
    @Column()
    private LocalDate leavingDate;

    /**
     * The Member type.
     */
    @ManyToOne(optional = false)
    @NotNull(message = ExceptionMessages.USER_MEMBER_TYPE_EMPTY)
    @JsonUnwrapped
    private MemberType memberType;

    /**
     * The Member type change.
     */
    @ManyToOne()
    @JoinColumn(nullable = true)
    private MemberType memberTypeChange;

    /**
     * The Family id.
     */
    @ManyToOne
    private User familyId;

    /**
     * The Bank account details.
     */
    @Column(nullable = false)
    @NotBlank(message = "Das Feld 'Bankdaten' darf nicht leer sein.")
    private String bankAccountDetails;

    /**
     * The Actual amount.
     */
    @Column()
    private Double actualAmount;

    /**
     * Instantiates a new User.
     *
     * @param userId             the user id
     * @param name               the name
     * @param address            the address
     * @param birthday           the birthday
     * @param entryDate          the entry date
     * @param cancellationDate   the cancellation date
     * @param leavingDate        the leaving date
     * @param memberType         the member type
     * @param memberTypeChange   the member type change
     * @param familyId           the family id
     * @param bankAccountDetails the bank account details
     * @param actualAmount       the actual amount
     */
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

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets leaving date.
     *
     * @return the leaving date
     */
    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    /**
     * Sets leaving date.
     *
     * @param leavingDate the leaving date
     */
    public void setLeavingDate(final LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets entry date.
     *
     * @return the entry date
     */
    public LocalDate getEntryDate() {
        return entryDate;
    }

    /**
     * Sets entry date.
     *
     * @param entryDate the entry date
     */
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * Gets cancellation date.
     *
     * @return the cancellation date
     */
    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    /**
     * Sets cancellation date.
     *
     * @param cancellationDate the cancellation date
     */
    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    /**
     * Gets member type.
     *
     * @return the member type
     */
    public MemberType getMemberType() {
        return memberType;
    }

    /**
     * Sets member type.
     *
     * @param memberType the member type
     */
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    /**
     * Gets family id.
     *
     * @return the family id
     */
    public User getFamilyId() {
        return familyId;
    }

    /**
     * Sets family id.
     *
     * @param familyId the family id
     */
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

    /**
     * Gets member type change.
     *
     * @return the member type change
     */
    public MemberType getMemberTypeChange() {
        return memberTypeChange;
    }

    /**
     * Sets member type change.
     *
     * @param memberTypeChange the member type change
     */
    public void setMemberTypeChange(MemberType memberTypeChange) {
        this.memberTypeChange = memberTypeChange;
    }

    /**
     * Gets bank account details.
     *
     * @return the bank account details
     */
    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    /**
     * Sets bank account details.
     *
     * @param bankAccountDetails the bank account details
     */
    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    /**
     * Gets actual amount.
     *
     * @return the actual amount
     */
    public Double getActualAmount() {
        return actualAmount;
    }

    /**
     * Sets actual amount.
     *
     * @param actualAmount the actual amount
     */
    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }
}
