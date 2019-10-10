package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ApPayableCreditNotesTrans.
 */
@Entity
@Table(name = "ap_payable_credit_notes_trans")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApPayableCreditNotesTrans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "type", length = 255)
    private String type;

    @Max(value = 11)
    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JsonIgnoreProperties("apPayableCreditNotesTrans")
    private ApPayable apPayable;

    @ManyToOne
    @JsonIgnoreProperties("apPayableCreditNotesTrans")
    private ApPayee apPayee;

    @ManyToOne
    @JsonIgnoreProperties("apPayableCreditNotesTrans")
    private ApPayable apPayable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public ApPayableCreditNotesTrans type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public ApPayableCreditNotesTrans amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ApPayable getApPayable() {
        return apPayable;
    }

    public ApPayableCreditNotesTrans apPayable(ApPayable apPayable) {
        this.apPayable = apPayable;
        return this;
    }

    public void setApPayable(ApPayable apPayable) {
        this.apPayable = apPayable;
    }

    public ApPayee getApPayee() {
        return apPayee;
    }

    public ApPayableCreditNotesTrans apPayee(ApPayee apPayee) {
        this.apPayee = apPayee;
        return this;
    }

    public void setApPayee(ApPayee apPayee) {
        this.apPayee = apPayee;
    }

    public ApPayable getApPayable() {
        return apPayable;
    }

    public ApPayableCreditNotesTrans apPayable(ApPayable apPayable) {
        this.apPayable = apPayable;
        return this;
    }

    public void setApPayable(ApPayable apPayable) {
        this.apPayable = apPayable;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApPayableCreditNotesTrans)) {
            return false;
        }
        return id != null && id.equals(((ApPayableCreditNotesTrans) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApPayableCreditNotesTrans{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
