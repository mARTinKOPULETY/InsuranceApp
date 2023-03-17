package com.capgemini.insurance.repository;


import com.capgemini.insurance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {

}
