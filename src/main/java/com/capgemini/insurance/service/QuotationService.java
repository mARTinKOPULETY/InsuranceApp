package com.capgemini.insurance.service;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.repository.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.logging.*;

@Service
public class QuotationService {

    private final Logger logger = Logger.getLogger(QuotationService.class.getName());
    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public QuotationService(QuotationRepository quotationRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.quotationRepository = quotationRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Quotation createQuotation(QuotationDTO quotationDTO) {
        checkIfNoIdIsInserted(quotationDTO);

        Customer customer = checkIfCustomerExists(quotationDTO);

        Quotation quotation = modelMapper.map(quotationDTO, Quotation.class);

        String middleName = paddingOfTheMiddleNameSpace(customer);

        logger.info("Quotation created. Beginning of isurance: " + quotation.getBeginningOfInsurance() +
                ". Insured amount: " + quotation.getInsuredAmount() +
                ". Date of signing mortage: " + quotation.getDateOfSigningMortgage() +
                ". Customer: "+ customer.getFirstName() + " " +middleName+ customer.getLastName() + ".");

          return    quotationRepository.save(quotation);
    }

    private static void checkIfNoIdIsInserted(QuotationDTO quotationDTO) {
        if (quotationDTO.getQuotationId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quotation Id is not allowed here");
        }
    }

    private static String paddingOfTheMiddleNameSpace(Customer customer) {
        String middleName = "";
        
        if (customer.getMiddleName() != null)
            middleName = customer.getMiddleName() + " ";
        
        return middleName;
    }

    private Customer checkIfCustomerExists(QuotationDTO quotationDTO) {
        Long customerId = quotationDTO.getCustomer().getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer does not exist");
        }
        return customer;
    }


}
