CREATE TABLE IF NOT EXISTS LOCAL_INVENTORY
(
    product_id character varying(10) NOT NULL,
    branch_id character varying(5),
    product_name character varying(255),
    shipment_id character varying(10),
    shipment_arrival_date date,
    selling_price numeric(10,2) NOT NULL,
    stock_left integer NOT NULL,
    exp_date date,
    CONSTRAINT local_inventory_pkey PRIMARY KEY (product_id),
    CONSTRAINT local_inventory_branch_id_fkey FOREIGN KEY (branch_id)
        REFERENCES public.branch (branch_id)
);