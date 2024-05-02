CREATE TABLE IF NOT EXISTS	EMPLOYEE
(
    emp_id char(5) NOT NULL,
    emp_fname character varying(255),
    emp_lname character varying(255),
    branch_id character varying(5),
    dept_id character varying(5),
    month_salary integer,
    CONSTRAINT employee_pkey PRIMARY KEY (emp_id),
    CONSTRAINT employee_branch_id_fkey FOREIGN KEY (branch_id)
        REFERENCES public.branch (branch_id),
    CONSTRAINT fk_dept FOREIGN KEY (dept_id)
)
