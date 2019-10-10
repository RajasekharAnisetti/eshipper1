package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ApPayeeType.
 */
@Entity
@Table(name = "ap_payee_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApPayeeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "apPayeeType")
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

    public ApPayeeType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApPayable> getApPayables() {
        return apPayables;
    }

    public ApPayeeType apPayables(Set<ApPayable> apPayables) {
        this.apPayables = apPayables;
        return this;
    }

    public ApPayeeType addApPayable(ApPayable apPayable) {
        this.apPayables.add(apPayable);
        apPayable.setApPayeeType(this);
        return this;
    }

    public ApPayeeType removeApPayable(ApPayable apPayable) {
        this.apPayables.remove(apPayable);
        apPayable.setApPayeeType(null);
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
        if (!(o instanceof ApPayeeType)) {
            return false;
        }
        return id != null && id.equals(((ApPayeeType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApPayeeType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
