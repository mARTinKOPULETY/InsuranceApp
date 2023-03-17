package com.capgemini.insurance;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class InsuranceApp {

    private final Logger logger = LoggerFactory.getLogger(InsuranceApp.class);
    public static void main(String[] args) {

        SpringApplication.run(InsuranceApp.class);
    }
}