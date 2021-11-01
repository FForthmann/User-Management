package de.nordakademie.model;


import javax.persistence.*;
import java.util.Date;

@Table(name = "account")
@Entity(name = "Account")
public class Account {

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

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer countStatus;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date year;

    public Account(Long invoiceNumber, Integer countStatus, Double amount, Date year) {
        this.invoiceNumber = invoiceNumber;
        this.countStatus = countStatus;
        this.amount = amount;
        this.year = year;
    }

    public Account() {

    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(Integer countStatus) {
        this.countStatus = countStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Account{" +
                "invoiceNumber=" + invoiceNumber +
                ", userId=" + userId +
                ", countStatus=" + countStatus +
                ", amount=" + amount +
                ", year=" + year +
                '}';
    }
}
