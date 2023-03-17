package com.capgemini.insurance.dto;

import com.capgemini.insurance.entity.*;

import java.time.LocalDate;

public class SubscriptionDTO {
    private Long subscriptionId;
    private QuotationDTO quotation;
    private LocalDate startDate;
    private LocalDate validUntil;
    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long subscriptionId, QuotationDTO quotation, LocalDate startDate, LocalDate validUntil) {
        this.subscriptionId = subscriptionId;
        this.quotation = quotation;
        this.startDate = startDate;
        this.validUntil = validUntil;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public QuotationDTO getQuotation() {
        return quotation;
    }

    public void setQuotation(QuotationDTO quotation) {
        this.quotation = quotation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }
}
