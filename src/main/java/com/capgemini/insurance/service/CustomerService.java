package com.capgemini.insurance.service;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.repository.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.logging.*;

@Service
public class CustomerService {

    private final Logger logger = Logger.getLogger(CustomerService.class.getName());
    private final CustomerRepository customerRepository;

    private String originalFirstName = null;
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




    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //This method takes in a Customer ID and a CustomerDTO object,
    // and updates the corresponding Customer object in the repository with the new data provided in the DTO.
    // If the DTO contains null values, the corresponding fields in the Customer object are set to null.
    //If the DTO contains a value for the field removeAll, all fields in the Customer object are set to null.
    //Updating or removing methods are separated into its own method for readability.
    public Customer modifyCustomer(Long customerId, CustomerDTO customerDTO) throws IllegalArgumentException {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {

            Customer existingCustomer = optionalCustomer.get();
            boolean removeAll = customerDTO.isRemoveAll();

            updateFirstName(customerDTO, existingCustomer, removeAll);
            updateLastName(customerDTO, existingCustomer, removeAll);
            updateMiddleName(customerDTO, existingCustomer, removeAll);
            updateEmail(customerDTO, existingCustomer, removeAll);
            updatePhoneNumber(customerDTO, existingCustomer, removeAll);
            updateBirthDate(customerDTO, existingCustomer, removeAll);


            getLoggerMessage(customerId);

            return customerRepository.save(existingCustomer);
        } else {
            throw new IllegalArgumentException("Customer not found for this id: " + customerId);
        }
    }

    private void updateFirstName(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalFirstName = existingCustomer.getFirstName();
        if (customerDTO.getFirstName() != null) {
           updatedFirstName = customerDTO.getFirstName();

            existingCustomer.setFirstName(customerDTO.getFirstName());
        } else if (removeAll || customerDTO.getFirstName() == null  ) {
            updatedFirstName  = null;
            existingCustomer.setFirstName(null);
        }
    }

    private void updateLastName(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalLastName = existingCustomer.getLastName();
        if (customerDTO.getLastName() != null) {
           updatedLastName = customerDTO.getLastName();
            existingCustomer.setLastName(customerDTO.getLastName());
        } else if (removeAll || customerDTO.getLastName() == null) {
            existingCustomer.setLastName(null);
            updatedLastName = null;
        }
    }

    private void updateMiddleName(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalMiddleName = existingCustomer.getMiddleName();
        if (customerDTO.getMiddleName() != null) {
            updatedMiddleName = customerDTO.getMiddleName();
            existingCustomer.setMiddleName(customerDTO.getMiddleName());
        } else if (removeAll || customerDTO.getMiddleName() == null ) {
           updatedMiddleName= null;
            existingCustomer.setMiddleName(null);
        }
    }

    private void updateEmail(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
       originalEmail = existingCustomer.getEmail();
        if (customerDTO.getEmail() != null) {
            updatedEmail = customerDTO.getEmail();
            existingCustomer.setEmail(customerDTO.getEmail());
        } else if (removeAll || customerDTO.getEmail() == null  ) {
            updatedEmail= null;
            existingCustomer.setEmail(null);
        }
    }

    private void updatePhoneNumber(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalPhoneNumber= existingCustomer.getPhoneNumber();
        if (customerDTO.getPhoneNumber() != null) {
            updatedPhoneNumber= customerDTO.getPhoneNumber();
            existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        } else if (removeAll || customerDTO.getPhoneNumber() == null) {
            updatedPhoneNumber = null;
            existingCustomer.setPhoneNumber(null);
        }
    }

    private void updateBirthDate(CustomerDTO customerDTO, Customer existingCustomer, boolean removeAll) {
        originalBirthDate = (existingCustomer.getBirthDate() == null) ? null : existingCustomer.getBirthDate().toString();
//        originalBirthDate = existingCustomer.getBirthDate().toString();
        if (customerDTO.getBirthDate() != null) {
           updatedBirthDate = customerDTO.getBirthDate().toString();
            existingCustomer.setBirthDate(customerDTO.getBirthDate());
        } else if (removeAll || customerDTO.getBirthDate() == null) {
            updatedBirthDate = null;
            existingCustomer.setBirthDate(null);
        }
    }

    private String messageFirstName(String originalFirstName, String changedString) {

        if(!Objects.equals(originalFirstName, changedString)) {
            message = "\n\t\tFirst name changed from '" + originalFirstName + "' to '" + changedString + "'. ";
        } else {
            message = "";
        }
        return message;
    }

    private String messageLastName(String originalLastName, String changedString) {

//        if(originalLastName != changedString || !originalLastName.equals(changedString)){
        if(!Objects.equals(originalLastName, changedString)){
            message = "\n\t\tLast name changed from '" + originalLastName + "' to '" + changedString + "'. ";
        } else {
            message = "";
        }
        return message;
    }

    private String messageMiddleName(String originalMiddleName, String changedString) {
        if(!Objects.equals(originalMiddleName, changedString)) {
            message = "\n\t\tMiddle name changed from '" + originalMiddleName + "' to '" + changedString + "'. ";
        } else {
            message = "";
        }
        return message;
    }

    private String messageEmail(String originalEmail, String changedString) {
        if(!Objects.equals(originalEmail, changedString)) {
            message = "\n\t\tEmail changed from '" + originalEmail + "' to '" + changedString + "'. ";
        } else {
            message = "";
        }
        return message;
    }
    private String messagePhoneNumber(String originalPhoneNumber, String changedString) {
        if(!Objects.equals(originalPhoneNumber, changedString)) {
            message = "\n\t\tPhone number changed from '" + originalPhoneNumber + "' to '" + changedString + "'. ";
        } else {
            message = "";
        }
        return message;
    }

    private String messageBirthDate(String originalBirthDate, String changedString) {

        if(!Objects.equals(originalBirthDate, changedString)) {
            message = "\n\t\tBirth date changed from '" + originalBirthDate + "' to '" + changedString + "'. ";
        } else {
            message = "";
        }
        return message;
    }

    public void getLoggerMessage(Long customerId) {

            String loggerMessage =("\n\n\tCustomer with id "+ customerId + " has been updated. " +
                    messageFirstName(originalFirstName,updatedFirstName) +
                    messageMiddleName(originalMiddleName,updatedMiddleName) +
                    messageLastName(originalLastName,updatedLastName) +
                    messageEmail(originalEmail,updatedEmail) +
                    messagePhoneNumber(originalPhoneNumber,updatedPhoneNumber) +
                    messageBirthDate(originalBirthDate,updatedBirthDate) + "\n");

        String ifNoChanges = "";
        if(loggerMessage.length() == 41 ){
            ifNoChanges =  "\t\tAttributes have been updated with the same values, no changes to see.";
        }

        logger.info(loggerMessage + ifNoChanges);
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
