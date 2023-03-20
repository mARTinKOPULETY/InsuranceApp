package com.capgemini.insurance;


import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.repository.*;
import com.capgemini.insurance.service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.modelmapper.*;
import org.springframework.web.server.*;

import java.math.*;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class QuotationServiceTest {

    @Mock
    QuotationRepository quotationRepository;
    @Mock
    CustomerRepository customerRepository;
    ModelMapper modelMapper = new ModelMapper();

    QuotationService quotationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quotationService = new QuotationService(quotationRepository, customerRepository, modelMapper);
    }

    @Test
    void createQuotationShouldReturnQuotation() {
        // Given
        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setBeginningOfInsurance(LocalDate.now());
        quotationDTO.setDateOfSigningMortgage(LocalDate.now());
        quotationDTO.setInsuredAmount(BigDecimal.valueOf(100000));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1L);
        quotationDTO.setCustomer(customerDTO);

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Jan");
        customer.setLastName("Novak");
        customer.setMiddleName("Josef");
        customer.setEmail("johnjnovak@gmail.com");
        customer.setBirthDate(LocalDate.of(1980, 1, 1));
        customer.setPhoneNumber("666999666");
        System.out.println(quotationDTO.getQuotationId());
        System.out.println(quotationDTO.getBeginningOfInsurance());
        System.out.println(quotationDTO.getCustomer().getCustomerId());
        when(customerRepository.findById(customerDTO.getCustomerId())).thenReturn(Optional.of(customer));
        when(quotationRepository.save(any(Quotation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // When
        Quotation quotation = quotationService.createQuotation(quotationDTO);

        // Then
        assertNotNull(quotation);
        assertEquals(quotationDTO.getBeginningOfInsurance(), quotation.getBeginningOfInsurance());
        assertEquals(quotationDTO.getDateOfSigningMortgage(), quotation.getDateOfSigningMortgage());
        assertEquals(quotationDTO.getInsuredAmount(), quotation.getInsuredAmount());

    }

    @Test
    void createQuotationShouldThrowExceptionWhenQuotationIdIsInserted() {
        // Given
        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setQuotationId(1L);

        // When & Then
        assertThrows(ResponseStatusException.class, () -> quotationService.createQuotation(quotationDTO));
    }

    @Test
    void createQuotationShouldThrowExceptionWhenCustomerDoesNotExist() {
        // Given
        QuotationDTO quotationDTO = new QuotationDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1L);
        quotationDTO.setCustomer(customerDTO);

        when(customerRepository.findById(customerDTO.getCustomerId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResponseStatusException.class, () -> quotationService.createQuotation(quotationDTO));
    }
}

