package com.capgemini.insurance.dto;

import java.time.*;

public class CustomerDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private boolean isRemoveAll;

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String firstName, String lastName, String middleName, String email, String phoneNumber, LocalDate birthDate, boolean isRemoveAll) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.isRemoveAll = isRemoveAll;
    }

    public boolean isRemoveAll() {
        return isRemoveAll;
    }

    public void setRemoveAll(boolean removeAll) {
        isRemoveAll = removeAll;
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
}
