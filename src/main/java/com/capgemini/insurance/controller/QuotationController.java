package com.capgemini.insurance.controller;

import com.capgemini.insurance.dto.*;
import com.capgemini.insurance.entity.*;
import com.capgemini.insurance.exceptions.*;
import com.capgemini.insurance.service.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("api/v2/quotation")
public class QuotationController {
    private final QuotationService quotationService;
    private final ModelMapper modelMapper;

    public QuotationController(QuotationService quotationService, ModelMapper modelMapper) {
        this.quotationService = quotationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<QuotationDTO> createQuotation(@RequestBody QuotationDTO quotationDTO)  {

        Quotation createdQuotation = quotationService.createQuotation(quotationDTO);
        QuotationDTO createdQuotationDTO = modelMapper.map(createdQuotation, QuotationDTO.class);

        return new ResponseEntity<>(createdQuotationDTO, HttpStatus.CREATED);
    }

}
