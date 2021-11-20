package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Member_Type")
public class MemberType {
    @Id
    private String description;

    @Column
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
