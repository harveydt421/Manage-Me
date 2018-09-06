package com.manageme.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "(\\+\\d{1})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s-.]?\\d{4}")
    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "employee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Asset> assets = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Notification> notifications = new HashSet<>();

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private SeparationApplication separationApplication;

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public Employee user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public Employee assets(Set<Asset> assets) {
        this.assets = assets;
        return this;
    }

    public Employee addAsset(Asset asset) {
        this.assets.add(asset);
        asset.setEmployee(this);
        return this;
    }

    public Employee removeAsset(Asset asset) {
        this.assets.remove(asset);
        asset.setEmployee(null);
        return this;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Employee notifications(Set<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public Employee addNotification(Notification notification) {
        this.notifications.add(notification);
        notification.setEmployee(this);
        return this;
    }

    public Employee removeNotification(Notification notification) {
        this.notifications.remove(notification);
        notification.setEmployee(null);
        return this;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public SeparationApplication getSeparationApplication() {
        return separationApplication;
    }

    public Employee separationApplication(SeparationApplication separationApplication) {
        this.separationApplication = separationApplication;
        return this;
    }

    public void setSeparationApplication(SeparationApplication separationApplication) {
        this.separationApplication = separationApplication;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            "}";
    }
}
