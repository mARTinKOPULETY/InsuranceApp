package com.capgemini.insurance.dto;

import com.capgemini.insurance.entity.*;

import java.math.*;
import java.time.*;

public class QuotationDTO {
    private Long quotationId;
    private LocalDate beginningOfInsurance;
    private BigDecimal insuredAmount;
    private LocalDate dateOfSigningMortgage;
    private CustomerDTO customer;

    public QuotationDTO() {
    }

    public QuotationDTO(Long quotationId, LocalDate beginningOfInsurance, BigDecimal insuredAmount, LocalDate dateOfSigningMortgage, CustomerDTO customer) {
        this.quotationId = quotationId;
        this.beginningOfInsurance = beginningOfInsurance;
        this.insuredAmount = insuredAmount;
        this.dateOfSigningMortgage = dateOfSigningMortgage;
        this.customer = customer;
    }

    public Long getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }

    public LocalDate getBeginningOfInsurance() {
        return beginningOfInsurance;
    }

    public void setBeginningOfInsurance(LocalDate beginningOfInsurance) {
        this.beginningOfInsurance = beginningOfInsurance;
    }

    public BigDecimal getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(BigDecimal insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public LocalDate getDateOfSigningMortgage() {
        return dateOfSigningMortgage;
    }

    public void setDateOfSigningMortgage(LocalDate dateOfSigningMortgage) {
        this.dateOfSigningMortgage = dateOfSigningMortgage;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
