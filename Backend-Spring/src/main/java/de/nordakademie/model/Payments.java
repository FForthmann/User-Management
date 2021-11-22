package de.nordakademie.model;

import de.nordakademie.util.ExceptionMessages;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "Payments.existsUserInPayments", query = "SELECT EXISTS (SELECT * FROM PAYMENTS WHERE USER_ID_USER_ID = :userId)"),
        @NamedNativeQuery(name = "Payments.existsUserInPaymentsForThisYear", query = "SELECT EXISTS (SELECT * FROM PAYMENTS WHERE USER_ID_USER_ID = :userId AND YEAR = :year)"),
        @NamedNativeQuery(name = "Payments.findPaymentsByUserId", query = "SELECT INVOICE_NUMBER FROM PAYMENTS WHERE USER_ID_USER_ID = :userId AND YEAR = :year"),
})
@Table(name = "payments")
@Entity(name = "Payments")
public class Payments {
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

    @ManyToOne
    @JoinColumn(nullable = true)
    private User userId;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_COUNT_STATUS_EMPTY)
    private Boolean countStatus;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_AMOUNT_EMPTY)
    private Double amount;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_YEAR_EMPTY)
    private Integer year;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.PAYMENT_IBAN_EMPTY)
    @NotBlank(message = ExceptionMessages.PAYMENT_IBAN_EMPTY)
    private String bankAccountDetails;

    public Payments(User userId, Long invoiceNumber, Boolean countStatus, Double amount, Integer year, String bankAccountDetails) {
        this.invoiceNumber = invoiceNumber;
        this.userId = userId;
        this.countStatus = countStatus;
        this.amount = amount;
        this.year = year;
        this.bankAccountDetails = bankAccountDetails;
    }

    public Payments() {

    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Boolean getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(Boolean countStatus) {
        this.countStatus = countStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getYear() {
        return year;
    }

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

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }
}
