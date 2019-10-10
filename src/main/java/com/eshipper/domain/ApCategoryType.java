package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ApCategoryType.
 */
@Entity
@Table(name = "ap_category_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApCategoryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "apCategoryType")
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

    public ApCategoryType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApPayable> getApPayables() {
        return apPayables;
    }

    public ApCategoryType apPayables(Set<ApPayable> apPayables) {
        this.apPayables = apPayables;
        return this;
    }

    public ApCategoryType addApPayable(ApPayable apPayable) {
        this.apPayables.add(apPayable);
        apPayable.setApCategoryType(this);
        return this;
    }

    public ApCategoryType removeApPayable(ApPayable apPayable) {
        this.apPayables.remove(apPayable);
        apPayable.setApCategoryType(null);
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
        if (!(o instanceof ApCategoryType)) {
            return false;
        }
        return id != null && id.equals(((ApCategoryType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApCategoryType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
