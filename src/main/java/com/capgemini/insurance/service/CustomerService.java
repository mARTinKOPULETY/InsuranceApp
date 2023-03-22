package com.capgemini.insurance.service;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.logging.*;
import com.capgemini.insurance.repository.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private String originalFirstName = "";
    private String updatedFirstName = "";
    private String originalLastName = "";
    private String updatedLastName = "";
    private String originalMiddleName = "";
    private String updatedMiddleName = "";
    private String originalEmail = "";
    private String updatedEmail = "";
    private String originalPhoneNumber = "";
    private String updatedPhoneNumber = "";
    private String originalBirthDate = "";
    private String updatedBirthDate = "";
    private String message= "";
    private CustomerLogging customerLogging = new CustomerLogging(this);


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //This method takes in a Customer ID and a CustomerDTO object,
    // and updates the corresponding Customer object in the repository with the new data provided in the DTO.
    // If the DTO contains null values, the corresponding fields in the Customer object are set to null.
    //If the DTO contains a value for the field removeAll, all fields in the Customer object are set to null.
    //Updating or removing methods are separated into its own method for readability.
    public Customer modifyCustomer(Long customerId, CustomerDTO customerDTO) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);


        if (optionalCustomer.isPresent()) {

            Customer existingCustomer = optionalCustomer.get();

            boolean isRemoveAll = customerDTO.isRemoveAll();

            updateFirstName(customerDTO, existingCustomer, isRemoveAll);
            updateLastName(customerDTO, existingCustomer, isRemoveAll);
            updateMiddleName(customerDTO, existingCustomer, isRemoveAll);
            updateEmail(customerDTO, existingCustomer, isRemoveAll);
            updatePhoneNumber(customerDTO, existingCustomer, isRemoveAll);
            updateBirthDate(customerDTO, existingCustomer, isRemoveAll);

            customerLogging.getLoggerUpdateMessage(customerId, customerDTO);

            return customerRepository.save(existingCustomer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found for id: " + customerId);
        }
    }

    private void updateFirstName(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalFirstName = existingCustomer.getFirstName();
        if (customerDTO.getFirstName() != null) {
           updatedFirstName = customerDTO.getFirstName();
            existingCustomer.setFirstName(customerDTO.getFirstName());
        }
        if (removeAll || customerDTO.getFirstName() == null  ) {
            updatedFirstName  = null;
            existingCustomer.setFirstName(null);
        }
    }

    private void updateLastName(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalLastName = existingCustomer.getLastName();
        if (customerDTO.getLastName() != null) {
           updatedLastName = customerDTO.getLastName();
            existingCustomer.setLastName(customerDTO.getLastName());
        }
        if (removeAll || customerDTO.getLastName() == null) {
            existingCustomer.setLastName(null);
            updatedLastName = null;
        }
    }

    private void updateMiddleName(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalMiddleName = existingCustomer.getMiddleName();
        if (customerDTO.getMiddleName() != null) {
            updatedMiddleName = customerDTO.getMiddleName();
            existingCustomer.setMiddleName(customerDTO.getMiddleName());
        }
        if (removeAll || customerDTO.getMiddleName() == null ) {
           updatedMiddleName= null;
            existingCustomer.setMiddleName(null);
        }
    }

    private void updateEmail(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
       originalEmail = existingCustomer.getEmail();
        if (customerDTO.getEmail() != null) {
            updatedEmail = customerDTO.getEmail();
            existingCustomer.setEmail(customerDTO.getEmail());
        }
        if (removeAll || customerDTO.getEmail() == null  ) {
            updatedEmail= null;
            existingCustomer.setEmail(null);
        }
    }

    private void updatePhoneNumber(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalPhoneNumber= existingCustomer.getPhoneNumber();
        if (customerDTO.getPhoneNumber() != null) {
            updatedPhoneNumber= customerDTO.getPhoneNumber();
            existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        }
        if (removeAll || customerDTO.getPhoneNumber() == null) {
            updatedPhoneNumber = null;
            existingCustomer.setPhoneNumber(null);
        }
    }


    private void updateBirthDate(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalBirthDate = (existingCustomer.getBirthDate() == null) ? null : existingCustomer.getBirthDate().toString();
        if (customerDTO.getBirthDate() != null) {
           updatedBirthDate = customerDTO.getBirthDate().toString();
            existingCustomer.setBirthDate(customerDTO.getBirthDate());
        }
        if (removeAll || customerDTO.getBirthDate() == null) {
            updatedBirthDate = null;
            existingCustomer.setBirthDate(null);
        }
    }


    public String getOriginalFirstName() {
        return originalFirstName;
    }

    public void setOriginalFirstName(String originalFirstName) {
        this.originalFirstName = originalFirstName;
    }

    public String getUpdatedFirstName() {
        return updatedFirstName;
    }

    public void setUpdatedFirstName(String updatedFirstName) {
        this.updatedFirstName = updatedFirstName;
    }

    public String getOriginalLastName() {
        return originalLastName;
    }

    public void setOriginalLastName(String originalLastName) {
        this.originalLastName = originalLastName;
    }

    public String getUpdatedLastName() {
        return updatedLastName;
    }

    public void setUpdatedLastName(String updatedLastName) {
        this.updatedLastName = updatedLastName;
    }

    public String getOriginalMiddleName() {
        return originalMiddleName;
    }

    public void setOriginalMiddleName(String originalMiddleName) {
        this.originalMiddleName = originalMiddleName;
    }

    public String getUpdatedMiddleName() {
        return updatedMiddleName;
    }

    public void setUpdatedMiddleName(String updatedMiddleName) {
        this.updatedMiddleName = updatedMiddleName;
    }

    public String getOriginalEmail() {
        return originalEmail;
    }

    public void setOriginalEmail(String originalEmail) {
        this.originalEmail = originalEmail;
    }

    public String getUpdatedEmail() {
        return updatedEmail;
    }

    public void setUpdatedEmail(String updatedEmail) {
        this.updatedEmail = updatedEmail;
    }

    public String getOriginalPhoneNumber() {
        return originalPhoneNumber;
    }

    public void setOriginalPhoneNumber(String originalPhoneNumber) {
        this.originalPhoneNumber = originalPhoneNumber;
    }

    public String getUpdatedPhoneNumber() {
        return updatedPhoneNumber;
    }

    public void setUpdatedPhoneNumber(String updatedPhoneNumber) {
        this.updatedPhoneNumber = updatedPhoneNumber;
    }

    public String getOriginalBirthDate() {
        return originalBirthDate;
    }

    public void setOriginalBirthDate(String originalBirthDate) {
        this.originalBirthDate = originalBirthDate;
    }

    public String getUpdatedBirthDate() {
        return updatedBirthDate;
    }

    public void setUpdatedBirthDate(String updatedBirthDate) {
        this.updatedBirthDate = updatedBirthDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
