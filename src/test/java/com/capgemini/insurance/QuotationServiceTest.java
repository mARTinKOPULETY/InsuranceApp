package com.capgemini.insurance;


import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.Customer;
import com.capgemini.insurance.entity.Quotation;
import com.capgemini.insurance.repository.CustomerRepository;
import com.capgemini.insurance.repository.QuotationRepository;
import com.capgemini.insurance.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import com.capgemini.insurance.dto.CustomerDTO;
import com.capgemini.insurance.dto.QuotationDTO;
import com.capgemini.insurance.entity.Customer;
import com.capgemini.insurance.entity.Quotation;
import com.capgemini.insurance.repository.CustomerRepository;
import com.capgemini.insurance.repository.QuotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuotationServiceTest {

    @Mock
    private QuotationRepository quotationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private QuotationService quotationService;

    private Customer existingCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        existingCustomer = new Customer();
        existingCustomer.setCustomerId(1L);
        existingCustomer.setFirstName("Jan");
        existingCustomer.setLastName("Novak");


        when(customerRepository.findById(existingCustomer.getCustomerId())).thenReturn(Optional.of(existingCustomer));
    }




    @Test
    void testCreateQuotationWithId() {
        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setQuotationId(1L);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> quotationService.createQuotation(quotationDTO));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Quotation Id is not allowed here", exception.getReason());
    }
}
