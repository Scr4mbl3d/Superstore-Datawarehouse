CREATE TABLE IF NOT EXISTS DEPARTMENT
(
    dept_id character varying(5) NOT NULL,
    dept_name character varying(255) NOT NULL,
    mgr_id integer NOT NULL,
    CONSTRAINT department_pkey PRIMARY KEY (dept_id)
);