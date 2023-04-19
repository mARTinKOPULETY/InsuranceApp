package com.capgemini.insurance.service;


import com.capgemini.insurance.dto.CustomerDTO;
import com.capgemini.insurance.entity.Customer;
import com.capgemini.insurance.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    public CustomerService customerService;
    @Mock
    public CustomerRepository customerRepository;


    @Test
    @DisplayName("Should modify customer")
    void modifyCustomerTest(){
        //Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Jan");
        customer.setLastName("Novak");
        customer.setMiddleName("Josef");
        customer.setEmail("johnjnovak@gmail.com");
        customer.setPhoneNumber("666999666");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Karl");
        customerDTO.setLastName("Ufon");
        customerDTO.setEmail("KarlVonUfon@programnautajovanisvedku.cz");
        customerDTO.setMiddleName("Von");
        customerDTO.setPhoneNumber("999666999");
        customerDTO.setBirthDate(LocalDate.of(1991, 1, 1));

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        //Act
        Customer modifyCustomer = customerService.modifyCustomer(customer.getCustomerId(), customerDTO);
        //Assert
        assertNotNull(modifyCustomer);
        assertEquals(modifyCustomer.getFirstName(),"Karl");
        assertEquals(modifyCustomer.getMiddleName(),"Von");
        assertEquals(modifyCustomer.getLastName(),"Ufon");
        assertEquals(modifyCustomer.getEmail(),"KarlVonUfon@programnautajovanisvedku.cz");
        assertEquals(modifyCustomer.getPhoneNumber(),"999666999");
        assertEquals(modifyCustomer.getBirthDate().toString(),"1991-01-01");
    }

    @Test
    @DisplayName("Should update all customers parameters as a null")
    void modifyCustomerTestWithNulls(){

        //Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Jan");
        customer.setLastName("Novak");
        customer.setMiddleName("Josef");
        customer.setEmail("johnjnovak@gmail.com");
        customer.setPhoneNumber("666999666");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));

        CustomerDTO customerDTO = new CustomerDTO();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        //Act
        Customer modifyCustomer = customerService.modifyCustomer(customer.getCustomerId(), customerDTO);
        //Assert
        assertNotNull(modifyCustomer);
        assertNull(modifyCustomer.getFirstName());
        assertNull(modifyCustomer.getMiddleName());
        assertNull(modifyCustomer.getLastName());
        assertNull(modifyCustomer.getEmail());
        assertNull(modifyCustomer.getPhoneNumber());
        assertNull(modifyCustomer.getBirthDate());
    }

    @Test
    @DisplayName("Should update all customers parameters as null by using removeAll boolean parameter")
    void testModifyCustomerWithRemoveAll() {
        //Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Jan");
        customer.setLastName("Novak");
        customer.setMiddleName("Josef");
        customer.setEmail("johnjnovak@gmail.com");
        customer.setPhoneNumber("666999666");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setRemoveAll(true);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        //Act

        Customer modifyCustomer = customerService.modifyCustomer(customer.getCustomerId(), customerDTO);
        //Assert
        assertNotNull(modifyCustomer);
        assertNull(modifyCustomer.getFirstName());
        assertNull(modifyCustomer.getMiddleName());
        assertNull(modifyCustomer.getLastName());
        assertNull(modifyCustomer.getEmail());
        assertNull(modifyCustomer.getPhoneNumber());
        assertNull(modifyCustomer.getBirthDate());
    }
    @Test
    @DisplayName("Should throw ResponseStatusException")
    void modifyCustomerWrongId(){
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        //Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> {
            customerService.modifyCustomer(1L,customerDTO);
        });
        assertEquals("404 NOT_FOUND \"Customer not found for id: 1\"", exception.getMessage());
    }
}
