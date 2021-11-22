package de.nordakademie.model;

import de.nordakademie.util.ExceptionMessages;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The type Payments.
 */
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "Payments.existsUserInPayments", query = "SELECT EXISTS (SELECT * FROM PAYMENTS WHERE USER_ID_USER_ID = :userId)"),
        @NamedNativeQuery(name = "Payments.existsUserInPaymentsForThisYear", query = "SELECT EXISTS (SELECT * FROM PAYMENTS WHERE USER_ID_USER_ID = :userId AND YEAR = :year)"),
        @NamedNativeQuery(name = "Payments.findPaymentsByUserId", query = "SELECT INVOICE_NUMBER FROM PAYMENTS WHERE USER_ID_USER_ID = :userId AND YEAR = :year"),
})
@Table(name = "payments")
@Entity(name = "Payments")
public class Payments {
    /**
     * The Invoice number.
     */
    @Id
    @SequenceGenerator(
            name = "invoiceNumber_sequence",
            sequenceName = "invoiceNumber_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoiceNumber_sequence"
    )
    @Column(
            updatable = false,
            nullable = false)
    private Long invoiceNumber;

    /**
     * The User id.
     */
    @ManyToOne
    @JoinColumn(nullable = true)
    private User userId;

    /**
     * The Count status.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_COUNT_STATUS_EMPTY)
    private Boolean countStatus;

    /**
     * The Amount.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_AMOUNT_EMPTY)
    private Double amount;

    /**
     * The Year.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_YEAR_EMPTY)
    private Integer year;

    /**
     * The Bank account details.
     */
    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_IBAN_EMPTY)
    @NotBlank(message = ExceptionMessages.PAYMENT_IBAN_EMPTY)
    private String bankAccountDetails;

    /**
     * Instantiates a new Payments.
     *
     * @param userId             the user id
     * @param invoiceNumber      the invoice number
     * @param countStatus        the count status
     * @param amount             the amount
     * @param year               the year
     * @param bankAccountDetails the bank account details
     */
    public Payments(User userId, Long invoiceNumber, Boolean countStatus, Double amount, Integer year, String bankAccountDetails) {
        this.invoiceNumber = invoiceNumber;
        this.userId = userId;
        this.countStatus = countStatus;
        this.amount = amount;
        this.year = year;
        this.bankAccountDetails = bankAccountDetails;
    }

    /**
     * Instantiates a new Payments.
     */
    public Payments() {

    }

    /**
     * Gets invoice number.
     *
     * @return the invoice number
     */
    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets invoice number.
     *
     * @param invoiceNumber the invoice number
     */
    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public User getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(User userId) {
        this.userId = userId;
    }

    /**
     * Gets count status.
     *
     * @return the count status
     */
    public Boolean getCountStatus() {
        return countStatus;
    }

    /**
     * Sets count status.
     *
     * @param countStatus the count status
     */
    public void setCountStatus(Boolean countStatus) {
        this.countStatus = countStatus;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "invoiceNumber=" + invoiceNumber +
                ", userId=" + userId +
                ", countStatus=" + countStatus +
                ", amount=" + amount +
                ", year=" + year +
                ", bankAccountDetails=" + bankAccountDetails +
                '}';
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
}
