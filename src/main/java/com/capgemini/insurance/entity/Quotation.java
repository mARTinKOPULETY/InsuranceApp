package com.capgemini.insurance.entity;

import org.springframework.format.annotation.*;



import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "quotation")

public class Quotation {
    @Id
    @Column(nullable= true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "customer_id")
    private Long quotationId;
    @Column (name="beginning_of_insurance", nullable = false )

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beginningOfInsurance;
    @Column (name="insured_amount", nullable = false)
    private BigDecimal insuredAmount;
    @Column (name="date_of_signing_mortgage", nullable = false)
    private LocalDate dateOfSigningMortgage;
    @ManyToOne
    @JoinColumn (name="customer_id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "quotation" , cascade = CascadeType.ALL)
    private Subscription subscription;

    public Quotation() {
    }

    public Quotation(Long quotationId, LocalDate beginningOfInsurance, BigDecimal insuredAmount, LocalDate dateOfSigningMortgage, Customer customer) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
