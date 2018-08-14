package com.manageme.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A SeparationApplication.
 */
@Entity
@Table(name = "separation_application")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SeparationApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_of_leaving", nullable = false)
    private Instant dateOfLeaving;

    @Column(name = "date_of_submission")
    private Instant dateOfSubmission;

    @NotNull
    @Column(name = "reason_for_leaving", nullable = false)
    private String reasonForLeaving;

    @Column(name = "completed")
    private Boolean completed;

    @OneToOne
    @JoinColumn(unique = true)
    private Employee employee;

    @ManyToOne
    @JsonIgnoreProperties("")
    private LineItem lineItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateOfLeaving() {
        return dateOfLeaving;
    }

    public SeparationApplication dateOfLeaving(Instant dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
        return this;
    }

    public void setDateOfLeaving(Instant dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public Instant getDateOfSubmission() {
        return dateOfSubmission;
    }

    public SeparationApplication dateOfSubmission(Instant dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
        return this;
    }

    public void setDateOfSubmission(Instant dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    public SeparationApplication reasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
        return this;
    }

    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public SeparationApplication completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Employee getEmployee() {
        return employee;
    }

    public SeparationApplication employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LineItem getLineItem() {
        return lineItem;
    }

    public SeparationApplication lineItem(LineItem lineItem) {
        this.lineItem = lineItem;
        return this;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
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
        SeparationApplication separationApplication = (SeparationApplication) o;
        if (separationApplication.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), separationApplication.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SeparationApplication{" +
            "id=" + getId() +
            ", dateOfLeaving='" + getDateOfLeaving() + "'" +
            ", dateOfSubmission='" + getDateOfSubmission() + "'" +
            ", reasonForLeaving='" + getReasonForLeaving() + "'" +
            ", completed='" + isCompleted() + "'" +
            "}";
    }
}
