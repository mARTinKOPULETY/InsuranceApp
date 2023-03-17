CREATE TABLE customer (
  customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  middle_name VARCHAR(50),
  email VARCHAR(50) UNIQUE,
  phone_number VARCHAR(50) UNIQUE,
  birth_date DATE
);

CREATE TABLE quotation (
  quotation_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  beginning_of_insurance DATE NOT NULL,
  insured_amount DECIMAL(19, 2) NOT NULL,
  date_of_signing_mortgage DATE NOT NULL,
  customer_id BIGINT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE subscription (
  subscription_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quotation_id BIGINT NOT NULL UNIQUE,
  start_date DATE NOT NULL,
  valid_until DATE NOT NULL,
  FOREIGN KEY (quotation_id) REFERENCES quotation (quotation_id)
);
