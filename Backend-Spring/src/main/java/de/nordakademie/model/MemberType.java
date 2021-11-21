package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import de.nordakademie.util.ExceptionMessages;
@Entity
@Table(name = "Member_Type")
public class MemberType {
    @Id
    @NotNull(message = ExceptionMessages.USER_MEMBER_TYPE_EMPTY)
    @NotEmpty(message = ExceptionMessages.USER_MEMBER_TYPE_EMPTY)
    private String description;

    @Column
    @NotNull(message = ExceptionMessages.MEMBER_TYPE_AMOUNT_EMPTY)
    private Double amount;

    public MemberType(String description, Double amount) {
        this.description = description;
        this.amount = amount;
    }

    public MemberType() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MemberType{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
