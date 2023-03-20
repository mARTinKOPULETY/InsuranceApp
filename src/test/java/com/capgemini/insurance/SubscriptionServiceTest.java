package com.capgemini.insurance;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.repository.*;
import com.capgemini.insurance.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.modelmapper.*;

import java.math.*;
import java.time.*;
import java.util.*;

import org.springframework.web.server.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class SubscriptionServiceTest {

    @Mock
     SubscriptionRepository subscriptionRepository;

    @Mock
    QuotationRepository quotationRepository;

    @Mock
    CustomerRepository customerRepository;

    SubscriptionService subscriptionService;



    ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriptionService = new SubscriptionService(subscriptionRepository, quotationRepository, customerRepository, modelMapper);
    }

    @Test
    void createSubscriptionShouldReturnSubscription(){
        // Given
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setStartDate(LocalDate.now());
        subscriptionDTO.setValidUntil(LocalDate.now().plusMonths(1));

        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setQuotationId(1L);
        subscriptionDTO.setQuotation(quotationDTO);

        Quotation quotation = new Quotation();
        quotation.setQuotationId(1L);
        quotation.setBeginningOfInsurance(LocalDate.now());
        quotation.setInsuredAmount(BigDecimal.valueOf(1000));
        quotation.setDateOfSigningMortgage(LocalDate.now());

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

        when(quotationRepository.findById(quotationDTO.getQuotationId())).thenReturn(Optional.of(quotation));
        when(customerRepository.findById(customerDTO.getCustomerId())).thenReturn(Optional.of(customer));
        when(subscriptionRepository.save(any(Subscription.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Subscription subscription = subscriptionService.createSubscription(subscriptionDTO);

         assertNotNull(subscription);
         assertEquals( subscriptionDTO.getStartDate(), subscription.getStartDate());
         assertEquals( subscriptionDTO.getValidUntil(), subscription.getValidUntil());



    }


    @Test
    void testGetSubscriptionById() {
        Long id = 1L;

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setMiddleName(null);
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setPhoneNumber("123456789");

        Quotation quotation = new Quotation();
        quotation.setQuotationId(1L);
        quotation.setCustomer(customer);
        quotation.setBeginningOfInsurance(LocalDate.of(2022, 1, 1));
        quotation.setInsuredAmount(BigDecimal.valueOf(1000));
        quotation.setDateOfSigningMortgage(LocalDate.of(2022, 2, 1));

        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(1L);
        subscription.setStartDate(LocalDate.of(2022, 3, 1));
        subscription.setValidUntil(LocalDate.of(2022, 6, 1));
        subscription.setQuotation(quotation);


        when(subscriptionRepository.findById(id)).thenReturn(java.util.Optional.of(subscription));
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(java.util.Optional.of(customer));
        when(quotationRepository.findById(quotation.getQuotationId())).thenReturn(java.util.Optional.of(quotation));


        Subscription result = subscriptionService.getSubscriptionById(id);


        verify(subscriptionRepository, times(2)).findById(id);
        verify(customerRepository, times(1)).findById(customer.getCustomerId());
        verify(quotationRepository, times(1)).findById(quotation.getQuotationId());


        Assertions.assertEquals(subscription, result);
    }

    @Test
    void createSubscriptionShouldThrowExceptionWhenSubscriptionIdIsInserted(){
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId(1L);

        assertThrows(ResponseStatusException.class, () -> subscriptionService.createSubscription(subscriptionDTO));
    }

    @Test
    void createSubscriptionShouldThrowExceptionWhenQuotationDoesNotExist(){

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setQuotationId(1L);
        subscriptionDTO.setQuotation(quotationDTO);

        when(quotationRepository.findById(quotationDTO.getQuotationId())).thenReturn(Optional.empty());

       Exception exception  = assertThrows(ResponseStatusException.class, () -> subscriptionService.createSubscription(subscriptionDTO));
        assertEquals("400 BAD_REQUEST \"Quotation does not exist\"", exception.getMessage());
    }

    @Test
    void createSubscriptionShouldThrowExceptionWhenCustomerDoesNotExist(){

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setQuotationId(1L);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(2L);
        quotationDTO.setCustomer(customerDTO);
        subscriptionDTO.setQuotation(quotationDTO);

        when(quotationRepository.findById(quotationDTO.getQuotationId())).thenReturn(Optional.of(new Quotation()));
        when(customerRepository.findById(customerDTO.getCustomerId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> subscriptionService.createSubscription(subscriptionDTO));
        assertEquals("400 BAD_REQUEST \"Customer does not exist\"", exception.getMessage());
    }

}
