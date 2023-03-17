package com.capgemini.insurance;


import com.capgemini.insurance.dto.CustomerDTO;
import com.capgemini.insurance.entity.Customer;
import com.capgemini.insurance.repository.CustomerRepository;
import com.capgemini.insurance.service.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.insurance.dto.CustomerDTO;
import com.capgemini.insurance.entity.Customer;
import com.capgemini.insurance.repository.CustomerRepository;

@SpringBootTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private Customer existingCustomer;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);

        existingCustomer = new Customer();
        existingCustomer.setCustomerId(1L);
        existingCustomer.setFirstName("Jan");
        existingCustomer.setLastName("Novak");
        existingCustomer.setMiddleName("Josef");
        existingCustomer.setEmail("johnjnovak@gmail.com");
        existingCustomer.setPhoneNumber("666999666");
        existingCustomer.setBirthDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testModifyCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Karl");
        customerDTO.setLastName("Ufon");
        customerDTO.setEmail("KarlVonUfon@programnautajovanisvedku.cz");
        customerDTO.setMiddleName("Von");
        customerDTO.setPhoneNumber("999666999");
        customerDTO.setBirthDate(LocalDate.of(1991, 1, 1));


        Optional<Customer> optionalCustomer = Optional.of(existingCustomer);

        when(customerRepository.findById(existingCustomer.getCustomerId())).thenReturn(optionalCustomer);
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Customer modifiedCustomer = customerService.modifyCustomer(existingCustomer.getCustomerId(), customerDTO);

        assertEquals("Karl", modifiedCustomer.getFirstName());
        assertEquals("Ufon", modifiedCustomer.getLastName());
        assertEquals("Von", modifiedCustomer.getMiddleName());
        assertEquals("KarlVonUfon@programnautajovanisvedku.cz", modifiedCustomer.getEmail());
        assertEquals("999666999", modifiedCustomer.getPhoneNumber());
        assertEquals(existingCustomer.getBirthDate(), modifiedCustomer.getBirthDate());
    }

    @Test
    void testModifyCustomerWithNullValues() {
        CustomerDTO customerDTO = new CustomerDTO();

        Optional<Customer> optionalCustomer = Optional.of(existingCustomer);

        when(customerRepository.findById(existingCustomer.getCustomerId())).thenReturn(optionalCustomer);
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Customer modifiedCustomer = customerService.modifyCustomer(existingCustomer.getCustomerId(), customerDTO);

        assertNull(modifiedCustomer.getFirstName());
        assertNull(modifiedCustomer.getLastName());
        assertNull(modifiedCustomer.getMiddleName());
        assertNull(modifiedCustomer.getEmail());
        assertNull(modifiedCustomer.getPhoneNumber());
        assertEquals(existingCustomer.getBirthDate(), modifiedCustomer.getBirthDate());
    }

    @Test
    void testModifyCustomerWithRemoveAll() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setRemoveAll(true);

        Optional<Customer> optionalCustomer = Optional.of(existingCustomer);

        when(customerRepository.findById(existingCustomer.getCustomerId())).thenReturn(optionalCustomer);
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Customer modifiedCustomer = customerService.modifyCustomer(existingCustomer.getCustomerId(), customerDTO);

        assertNull(modifiedCustomer.getFirstName());
        assertNull(modifiedCustomer.getLastName());
        assertNull(modifiedCustomer.getMiddleName());
        assertNull(modifiedCustomer.getEmail());
        assertNull(modifiedCustomer.getPhoneNumber());
        assertNull(modifiedCustomer.getBirthDate());
    }
}