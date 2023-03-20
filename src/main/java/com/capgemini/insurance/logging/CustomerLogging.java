package com.capgemini.insurance.logging;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.service.*;

import java.util.*;
import java.util.logging.*;

public class CustomerLogging {
    private final Logger logger = Logger.getLogger(CustomerService.class.getName());
    private CustomerService customerService ;

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

    public CustomerLogging(CustomerService customerService) {
        this.customerService = customerService;
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

    private void loggerUpdateMessage(Long customerId, CustomerDTO customerDTO) {

        String loggerMessage =("\n\n\tCustomer with id "+ customerId + " has been updated. " +
                messageFirstName(customerService.getOriginalFirstName(), customerService.getUpdatedFirstName()) +
                messageMiddleName(customerService.getOriginalMiddleName(),customerService.getUpdatedMiddleName()) +
                messageLastName(customerService.getOriginalLastName(), customerService.getUpdatedLastName() ) +
                messageEmail(customerService.getOriginalEmail(), customerService.getUpdatedEmail()) +
                messagePhoneNumber(customerService.getOriginalPhoneNumber(), customerService.getUpdatedPhoneNumber()) +
                messageBirthDate(customerService.getOriginalBirthDate(), customerService.getUpdatedBirthDate()) + "\n");

        String ifNoChanges = "";
        if(loggerMessage.length() == 41 ){
            ifNoChanges =  "\t\tAttributes have been updated with the same values, no changes to see.";
        }

        logger.info(loggerMessage + ifNoChanges);
    }

    public void getLoggerUpdateMessage(Long customerId, CustomerDTO customerDTO){

        loggerUpdateMessage(customerId, customerDTO);
    }
}
