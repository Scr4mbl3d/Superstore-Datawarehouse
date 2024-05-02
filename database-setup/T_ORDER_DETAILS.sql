CREATE TABLE IF NOT EXISTS public.order_details
(
    order_id integer NOT NULL DEFAULT nextval('order_details_order_id_seq'::regclass),
    product_id character varying(10),
    customer_id character varying(10),
    salesman_id integer,
    quantity integer,
    order_price numeric(10,2),
    order_date date,
    CONSTRAINT order_details_pkey PRIMARY KEY (order_id),
    CONSTRAINT order_details_customer_id_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id) 
    CONSTRAINT order_details_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES public.local_inventory (product_id) 
    CONSTRAINT order_details_salesman_id_fkey FOREIGN KEY (salesman_id)
        REFERENCES public.employee (emp_id) 
);