CREATE TABLE IF NOT EXISTS CUSTOMER
(
    customer_id character varying(10) NOT NULL,
    customer_fname character varying(255),
    customer_lname character varying(255),
    customer_division dvns,
    customer_phone character(10),
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);
