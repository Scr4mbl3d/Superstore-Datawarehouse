CREATE SEQUENCE IF NOT EXISTS order_details_order_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY order_details.order_id;