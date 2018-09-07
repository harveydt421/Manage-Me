package com.manageme.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SeparationApplication entity.
 */
public class SeparationApplicationDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant dateOfLeaving;

    private Instant dateOfSubmission;

    @NotNull
    private String reasonForLeaving;

    private Boolean completed;

    private Long employeeId;

    private String employeeName;

    private Set<EmployeeDTO> functionalRepresentatives = new HashSet<>();

    private Set<LineItemDTO> lineItems = new HashSet<>();

    public Set<LineItemDTO> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItemDTO> lineItems) {
        this.lineItems = lineItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Instant dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public Instant getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(Instant dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Set<EmployeeDTO> getFunctionalRepresentatives() {
        return functionalRepresentatives;
    }

    public void setFunctionalRepresentatives(Set<EmployeeDTO> employees) {
        this.functionalRepresentatives = employees;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeparationApplicationDTO separationApplicationDTO = (SeparationApplicationDTO) o;
        if (separationApplicationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), separationApplicationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SeparationApplicationDTO{" +
            "id=" + getId() +
            ", dateOfLeaving='" + getDateOfLeaving() + "'" +
            ", dateOfSubmission='" + getDateOfSubmission() + "'" +
            ", reasonForLeaving='" + getReasonForLeaving() + "'" +
            ", completed='" + isCompleted() + "'" +
            ", employee=" + getEmployeeId() +
            "}";
    }
}
