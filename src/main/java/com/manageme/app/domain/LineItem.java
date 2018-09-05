package com.manageme.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LineItem.
 */
@Entity
@Table(name = "line_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "feedback")
    private String feedback;

    @OneToOne
    @JoinColumn(unique = true)
    private Asset assetOwed;

    @ManyToOne
    @JsonIgnore
    private SeparationApplication separationApplication;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public LineItem feedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Asset getAssetOwed() {
        return assetOwed;
    }

    public LineItem assetOwed(Asset asset) {
        this.assetOwed = asset;
        return this;
    }

    public void setAssetOwed(Asset asset) {
        this.assetOwed = asset;
    }

    public SeparationApplication getSeparationApplication() {
        return separationApplication;
    }

    public LineItem separationApplication(SeparationApplication separationApplication) {
        this.separationApplication = separationApplication;
        return this;
    }

    public void setSeparationApplication(SeparationApplication separationApplication) {
        this.separationApplication = separationApplication;
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
        LineItem lineItem = (LineItem) o;
        if (lineItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lineItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LineItem{" +
            "id=" + getId() +
            ", feedback='" + getFeedback() + "'" +
            "}";
    }
}
