package com.capgemini.insurance.controller;


import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.service.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("api/v2/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomerDTO> modifyOrDeleteCustomer(@PathVariable("id") Long customerId,
            @Valid @RequestBody CustomerDTO customerDTO) throws IllegalArgumentException {

        Customer updatedCustomer = customerService.modifyCustomer(customerId, customerDTO);
        CustomerDTO updatedCustomerDTO = modelMapper.map(updatedCustomer, CustomerDTO.class);

        return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);

    }

}
