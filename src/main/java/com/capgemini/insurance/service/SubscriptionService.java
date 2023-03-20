package com.capgemini.insurance.service;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.logging.*;
import com.capgemini.insurance.repository.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.logging.*;

@Service
public class SubscriptionService {
    private final Logger logger = Logger.getLogger(SubscriptionService.class.getName());
    private final SubscriptionRepository subscriptionRepository;
    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private SubscriptionLogging subscriptionLogging = new SubscriptionLogging(this);

    public SubscriptionService(SubscriptionRepository subscriptionRepository, QuotationRepository quotationRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.quotationRepository = quotationRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Subscription createSubscription(SubscriptionDTO subscriptionDTO) {

        checkIfNoIdIsInserted(subscriptionDTO);
        checkIfSubscriptionExists(subscriptionDTO);
        Customer customer = checkIfCustomerExists(subscriptionDTO);

        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);
        subscriptionLogging.getLoggerCreateMessage(customer, subscription);

        return subscriptionRepository.save(subscription);
    }

    private static String paddingOfTheMiddleNameSpace(Customer customer) {
        String middleName = "";

        if (customer.getMiddleName() != null)
            middleName = customer.getMiddleName() + " ";

        return middleName;
    }

    private Customer checkIfCustomerExists(SubscriptionDTO subscriptionDTO) {
        Long customerId = subscriptionDTO.getQuotation().getCustomer().getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer does not exist");
        }
        return customer;
    }

    private void checkIfSubscriptionExists(SubscriptionDTO subscriptionDTO) {
        Long quotationId = subscriptionDTO.getQuotation().getQuotationId();
        Quotation quotation = quotationRepository.findById(quotationId).orElse(null);
        if (quotation == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quotation does not exist");
        }
    }

    private static void checkIfNoIdIsInserted(SubscriptionDTO subscriptionDTO) {
        if (subscriptionDTO.getSubscriptionId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription Id is not allowed here");
        }
    }

    public Subscription getSubscriptionById(Long id) {
            try {
                Long customerId = subscriptionRepository.findById(id).get().getQuotation().getCustomer().getCustomerId();

                Customer customer = customerRepository.findById(customerId).orElse(null);
                Subscription subscription = subscriptionRepository.findById(id).orElse(null);
                Quotation quotation = quotationRepository.findById(id).orElse(null);

                subscriptionLogging.getLoggerMessage(id, customer, subscription, quotation);

                return subscription;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription with id " + id + " does not exist");
            }
    }




}
