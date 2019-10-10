package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A WoPaymentMethod.
 */
@Entity
@Table(name = "wo_payment_method")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WoPaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "woPaymentMethod")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApPayable> apPayables = new HashSet<>();

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

    public WoPaymentMethod name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApPayable> getApPayables() {
        return apPayables;
    }

    public WoPaymentMethod apPayables(Set<ApPayable> apPayables) {
        this.apPayables = apPayables;
        return this;
    }

    public WoPaymentMethod addApPayable(ApPayable apPayable) {
        this.apPayables.add(apPayable);
        apPayable.setWoPaymentMethod(this);
        return this;
    }

    public WoPaymentMethod removeApPayable(ApPayable apPayable) {
        this.apPayables.remove(apPayable);
        apPayable.setWoPaymentMethod(null);
        return this;
    }

    public void setApPayables(Set<ApPayable> apPayables) {
        this.apPayables = apPayables;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoPaymentMethod)) {
            return false;
        }
        return id != null && id.equals(((WoPaymentMethod) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoPaymentMethod{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
