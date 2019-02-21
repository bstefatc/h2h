package com.rbc.gam.head2head.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ComparisonHolding.
 */
@Entity
@Table(name = "comparison_holding")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ComparisonHolding implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "holdings")
    private String holdings;

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

    public ComparisonHolding name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHoldings() {
        return holdings;
    }

    public ComparisonHolding holdings(String holdings) {
        this.holdings = holdings;
        return this;
    }

    public void setHoldings(String holdings) {
        this.holdings = holdings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComparisonHolding comparisonHolding = (ComparisonHolding) o;
        if (comparisonHolding.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comparisonHolding.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComparisonHolding{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", holdings='" + getHoldings() + "'" +
            "}";
    }
}
