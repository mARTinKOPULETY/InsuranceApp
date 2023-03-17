package com.capgemini.insurance.controller;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.exceptions.*;
import com.capgemini.insurance.service.SubscriptionService;
import org.modelmapper.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
@RequestMapping("api/v2/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    private final ModelMapper modelMapper;

    public SubscriptionController(SubscriptionService subscriptionService, ModelMapper modelMapper) {
        this.subscriptionService = subscriptionService;

        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO)  {



        Subscription createdSubscription = subscriptionService.createSubscription(subscriptionDTO);
        SubscriptionDTO createdSubscriptionDTO = modelMapper.map(createdSubscription, SubscriptionDTO.class);

        return new ResponseEntity<>(createdSubscriptionDTO, HttpStatus.CREATED);
    }

        @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable Long id) {

        Subscription subscription = subscriptionService.getSubscriptionById(id);
        SubscriptionDTO subscriptionDTO = modelMapper.map(subscription, SubscriptionDTO.class);

        if (subscription != null) {
            return new ResponseEntity<>(subscriptionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
