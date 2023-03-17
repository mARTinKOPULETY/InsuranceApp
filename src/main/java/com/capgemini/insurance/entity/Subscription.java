package com.capgemini.insurance.entity;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(nullable= true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true, name = "subscription_id")
    private Long subscriptionId;

    @OneToOne
    @JoinColumn(name = "quotation_id", unique = true)
    private Quotation quotation;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;


    public Subscription() {
    }

    public Subscription(Long subscriptionId, Quotation quotation, LocalDate startDate, LocalDate validUntil) {
        this.subscriptionId = subscriptionId;
        this.quotation = quotation;
        this.startDate = startDate;
        this.validUntil = validUntil;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
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
