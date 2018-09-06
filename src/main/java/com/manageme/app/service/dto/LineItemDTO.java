package com.manageme.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LineItem entity.
 */
public class LineItemDTO implements Serializable {

    private Long id;

    private String feedback;

    private Boolean cleared;

    private Long assetOwedId;

    private String assetOwedName;

    private Long separationApplicationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Boolean isCleared() {
        return cleared;
    }

    public void setCleared(Boolean cleared) {
        this.cleared = cleared;
    }

    public Long getAssetOwedId() {
        return assetOwedId;
    }

    public void setAssetOwedId(Long assetId) {
        this.assetOwedId = assetId;
    }

    public String getAssetOwedName() {
        return assetOwedName;
    }

    public void setAssetOwedName(String assetName) {
        this.assetOwedName = assetName;
    }

    public Long getSeparationApplicationId() {
        return separationApplicationId;
    }

    public void setSeparationApplicationId(Long separationApplicationId) {
        this.separationApplicationId = separationApplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LineItemDTO lineItemDTO = (LineItemDTO) o;
        if (lineItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lineItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LineItemDTO{" +
            "id=" + getId() +
            ", feedback='" + getFeedback() + "'" +
            ", cleared='" + isCleared() + "'" +
            ", assetOwed=" + getAssetOwedId() +
            ", assetOwed='" + getAssetOwedName() + "'" +
            ", separationApplication=" + getSeparationApplicationId() +
            "}";
    }
}
