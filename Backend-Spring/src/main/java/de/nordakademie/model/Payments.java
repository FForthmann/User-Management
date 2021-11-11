package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "Payments.existsUserInPayments", query = "SELECT EXISTS (SELECT * FROM PAYMENTS WHERE USER_ID_USER_ID = :userId)"),
        @NamedNativeQuery(name = "Payments.deleteAllPaymentsByUserId",
                          query = "DELETE FROM PAYMENTS WHERE INVOICE_NUMBER IN (SELECT INVOICE_NUMBER FROM PAYMENTS WHERE USER_ID_USER_ID = " +
                                  ":userId)")
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

    @ManyToOne(optional = false)
    private User userId;

    @Column(nullable = false)
    private Boolean countStatus;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer bankAccountDetails;

    public Payments(User userId, Long invoiceNumber, Boolean countStatus, Double amount, Integer year, Integer bankAccountDetails) {
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

    public Integer getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(Integer bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }
}
