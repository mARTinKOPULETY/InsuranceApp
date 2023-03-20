package com.capgemini.insurance.service;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.logging.*;
import com.capgemini.insurance.repository.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private QuotationLogging quotationLogging = new QuotationLogging(this);


    public QuotationService(QuotationRepository quotationRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.quotationRepository = quotationRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Quotation createQuotation(QuotationDTO quotationDTO) {
        checkIfNoIdIsInserted(quotationDTO);

        Customer customer = checkIfCustomerExists(quotationDTO);

        Quotation quotation = modelMapper.map(quotationDTO, Quotation.class);

        quotationLogging.getLoggerMessage(customer, quotation);

        return    quotationRepository.save(quotation);
    }

    private static void checkIfNoIdIsInserted(QuotationDTO quotationDTO) {
        if (quotationDTO.getQuotationId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quotation Id is not allowed here");
        }
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
