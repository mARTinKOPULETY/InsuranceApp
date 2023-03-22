package com.capgemini.insurance.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(nullable= true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true, name = "customer_id")
    private Long customerId;
    @Column(name="first_name", length=50)
    private String firstName;
    @Column(name="last_name", length=50)
    private String lastName;
    @Column(name="middle_name", length=50)
    private String middleName;
    @Email
    @Column(name="email", length=50,  unique=true)
    private String email;
    @Column (name="phone_number", length=25, unique=true)
    private String phoneNumber;
    @Column (name="birth_date")
    @Past(message = "Birth date should be in the past")
    private LocalDate birthDate;
    @Transient
    private boolean removeAll;
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private Set<Quotation> quotations;

    public Customer() {
    }

    public Customer(Long customerId, String firstName, String lastName, String middleName, String email, String phoneNumber, LocalDate birthDate, Set<Quotation> quotations) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.removeAll = false;
        this.quotations = quotations;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(Set<Quotation> quotations) {
        this.quotations = quotations;
    }

    public boolean isRemoveAll() {
        return removeAll;
    }

    public void setRemoveAll(boolean removeAll) {
        this.removeAll = removeAll;
    }
}
