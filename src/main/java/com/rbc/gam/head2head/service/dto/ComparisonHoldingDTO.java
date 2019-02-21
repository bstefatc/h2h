package com.rbc.gam.head2head.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ComparisonHolding entity.
 */
public class ComparisonHoldingDTO implements Serializable {

    private Long id;

    private String name;

    private String holdings;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHoldings() {
        return holdings;
    }

    public void setHoldings(String holdings) {
        this.holdings = holdings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComparisonHoldingDTO comparisonHoldingDTO = (ComparisonHoldingDTO) o;
        if (comparisonHoldingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comparisonHoldingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComparisonHoldingDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", holdings='" + getHoldings() + "'" +
            "}";
    }
}
