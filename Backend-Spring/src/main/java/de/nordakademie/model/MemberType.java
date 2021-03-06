package de.nordakademie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import de.nordakademie.util.ExceptionMessages;
/**
 * The datamodel for type Membertype.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@Entity
@Table(name = "Member_Type")
public class MemberType {
    /**
     * The Description.
     */
    @Id
    @NotNull(message = ExceptionMessages.USER_MEMBER_TYPE_EMPTY)
    @NotEmpty(message = ExceptionMessages.USER_MEMBER_TYPE_EMPTY)
    private String description;

    /**
     * The Amount.
     */
    @Column
    @NotNull(message = ExceptionMessages.MEMBER_TYPE_AMOUNT_EMPTY)
    private Double amount;

    /**
     * Constructor instantiates a new Member type with parameters for hibernate.
     *
     * @param description the description
     * @param amount      the amount
     */
    public MemberType(String description, Double amount) {
        this.description = description;
        this.amount = amount;
    }

    /**
     * Regular constructor instantiates a new Member type.
     */
    public MemberType() {

    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "MemberType{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
