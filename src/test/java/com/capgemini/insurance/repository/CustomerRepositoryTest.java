package com.capgemini.insurance.repository;


import com.capgemini.insurance.entity.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Shuld save the customer into the database")
    void save(){
        //Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Jan");
        customer.setLastName("Novak");
        customer.setMiddleName("Josef");
        customer.setEmail("johnjnovak@gmail.com");
        customer.setPhoneNumber("666999666");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));
        //Act
        Customer updatedCustomer = customerRepository.save(customer);
        //Assert
        assertNotNull(updatedCustomer);
        assertThat(updatedCustomer.getFirstName()).isEqualTo("Jan");
    }

    @Test
    @DisplayName("Should find the customer by its id.")
    void findById(){
        //Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Jan");
        customer.setLastName("Novak");
        customer.setMiddleName("Josef");
        customer.setEmail("johnjnovak@gmail.com");
        customer.setPhoneNumber("666999666");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));
        //Act
        Customer customerById = customerRepository.findById(customer.getCustomerId()).get();
        //Assert
        assertNotNull(customerById);
        assertThat(customerById.getFirstName()).isEqualTo("Jan");
    }
}
