package com.manageme.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "separationApplication", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LineItem> lineItems = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "separation_application_functional_representative",
               joinColumns = @JoinColumn(name = "separation_applications_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "functional_representatives_id", referencedColumnName = "id"))
    private Set<Employee> functionalRepresentatives = new HashSet<>();

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

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public SeparationApplication lineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    public SeparationApplication addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
        lineItem.setSeparationApplication(this);
        return this;
    }

    public SeparationApplication removeLineItem(LineItem lineItem) {
        this.lineItems.remove(lineItem);
        lineItem.setSeparationApplication(null);
        return this;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Set<Employee> getFunctionalRepresentatives() {
        return functionalRepresentatives;
    }

    public SeparationApplication functionalRepresentatives(Set<Employee> employees) {
        this.functionalRepresentatives = employees;
        return this;
    }

    public SeparationApplication addFunctionalRepresentative(Employee employee) {
        this.functionalRepresentatives.add(employee);
        return this;
    }

    public SeparationApplication removeFunctionalRepresentative(Employee employee) {
        this.functionalRepresentatives.remove(employee);
        return this;
    }

    public void setFunctionalRepresentatives(Set<Employee> employees) {
        this.functionalRepresentatives = employees;
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
