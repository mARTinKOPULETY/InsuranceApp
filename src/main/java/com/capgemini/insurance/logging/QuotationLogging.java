package com.capgemini.insurance.logging;

import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.service.*;

import java.util.logging.*;

public class QuotationLogging {
    private final Logger logger = Logger.getLogger(CustomerService.class.getName());

    private QuotationService quotationService;
    String middleName ="";

    public QuotationLogging(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    private void loggerMessage(Customer customer, Quotation quotation) {

      middleName = paddingOfTheMiddleNameSpace(customer);

        logger.info("Quotation created. Beginning of isurance: " + quotation.getBeginningOfInsurance() +
                ". Insured amount: " + quotation.getInsuredAmount() +
                ". Date of signing mortage: " + quotation.getDateOfSigningMortgage() +
                ". Customer: "+ customer.getFirstName() + " " + middleName + customer.getLastName() + ".");
    }
    private static String paddingOfTheMiddleNameSpace(Customer customer) {
        String middleName = "";

        if (customer.getMiddleName() != null)
            middleName = customer.getMiddleName() + " ";

        return middleName;
    }

    public void  getLoggerMessage(Customer customer, Quotation quotation){
        loggerMessage( customer, quotation);
    }
}
