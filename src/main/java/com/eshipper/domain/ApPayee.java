package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ApPayee.
 */
@Entity
@Table(name = "ap_payee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApPayee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "apPayee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApPayable> apPayables = new HashSet<>();

    @OneToMany(mappedBy = "apPayee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ApPayee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApPayable> getApPayables() {
        return apPayables;
    }

    public ApPayee apPayables(Set<ApPayable> apPayables) {
        this.apPayables = apPayables;
        return this;
    }

    public ApPayee addApPayable(ApPayable apPayable) {
        this.apPayables.add(apPayable);
        apPayable.setApPayee(this);
        return this;
    }

    public ApPayee removeApPayable(ApPayable apPayable) {
        this.apPayables.remove(apPayable);
        apPayable.setApPayee(null);
        return this;
    }

    public void setApPayables(Set<ApPayable> apPayables) {
        this.apPayables = apPayables;
    }

    public Set<ApPayableCreditNotesTrans> getApPayableCreditNotesTrans() {
        return apPayableCreditNotesTrans;
    }

    public ApPayee apPayableCreditNotesTrans(Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans = apPayableCreditNotesTrans;
        return this;
    }

    public ApPayee addApPayableCreditNotesTrans(ApPayableCreditNotesTrans apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans.add(apPayableCreditNotesTrans);
        apPayableCreditNotesTrans.setApPayee(this);
        return this;
    }

    public ApPayee removeApPayableCreditNotesTrans(ApPayableCreditNotesTrans apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans.remove(apPayableCreditNotesTrans);
        apPayableCreditNotesTrans.setApPayee(null);
        return this;
    }

    public void setApPayableCreditNotesTrans(Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans = apPayableCreditNotesTrans;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApPayee)) {
            return false;
        }
        return id != null && id.equals(((ApPayee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApPayee{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
