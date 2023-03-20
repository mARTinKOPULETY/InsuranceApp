package com.capgemini.insurance.logging;

import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.service.*;

import java.util.logging.*;

public class SubscriptionLogging {

    private final Logger logger = Logger.getLogger(CustomerService.class.getName());

    private SubscriptionService subscriptionService;

    String middleName ="";
    public SubscriptionLogging(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    private void LoggerMessage(Long id, Customer customer, Subscription subscription, Quotation quotation) {
      middleName = paddingOfTheMiddleNameSpace(customer);

        logger.info("\n\n\tSubscription with id: " + id + " was requested: " +
                "\n\t\tCustomer: " + customer.getFirstName() +
                " " + middleName + customer.getLastName() +
                ". Customers email: " + customer.getEmail() +
                ". Customers phone number: " + customer.getPhoneNumber() +
                ".\n\t\tQuotation id: " + quotation.getQuotationId() +
                ". Quotation start date: " + subscription.getStartDate() +
                ". Quotation valid until: " + subscription.getValidUntil() +
                ".\n\t\tBeginning of insurance: " + quotation.getBeginningOfInsurance() +
                ". Insured amount: " + quotation.getInsuredAmount() +
                ". Date of signing the mortgage: " + quotation.getDateOfSigningMortgage()+
                ".\n");
    }


    private void loggerCreateMessage(Customer customer, Subscription subscription) {
        middleName = paddingOfTheMiddleNameSpace(customer);
        logger.info("Subscription created. Start date: " + subscription.getStartDate() +
                ". Valid until: "+ subscription.getValidUntil() +
                ". For quotation id: " + subscription.getQuotation().getQuotationId()+
                ". Customer: " + customer.getFirstName() + " " + middleName + customer.getLastName() + ".");
    }

    private static String paddingOfTheMiddleNameSpace(Customer customer) {
        String middleName = "";

        if (customer.getMiddleName() != null)
            middleName = customer.getMiddleName() + " ";

        return middleName;
    }
    public void getLoggerMessage(Long id, Customer customer, Subscription subscription, Quotation quotation){
        LoggerMessage( id,customer, subscription, quotation);
    }

    public void getLoggerCreateMessage(Customer customer, Subscription subscription){
        loggerCreateMessage(customer, subscription);
    }




}
