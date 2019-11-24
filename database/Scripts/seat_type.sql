-- SEQUENCE: hngout.sys_seat_type_seq

-- DROP SEQUENCE hngout.sys_seat_type_seq;

CREATE SEQUENCE hngout.sys_seat_type_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE hngout.sys_seat_type_seq
    OWNER TO tharinda;


-- Table: hngout.sys_seat_type

-- DROP TABLE hngout.sys_seat_type;

CREATE TABLE hngout.sys_seat_type
(
    seat_type_id bigint NOT NULL DEFAULT nextval('hngout.sys_seat_type_seq'::regclass),
    code character varying(10),
	name character varying(100),
    CONSTRAINT sys_seat_type_pkey PRIMARY KEY (seat_type_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hngout.sys_seat_type
    OWNER to tharinda;


-- Table: hngout.prop_facilities

-- DROP TABLE hngout.prop_facilities;

CREATE TABLE hngout.prop_seat_type
(
    prop_id integer NOT NULL,
    seat_type_id integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT prop_seat_type_pkey PRIMARY KEY (prop_id, seat_type_id),
    CONSTRAINT prop_seat_type_st_fk FOREIGN KEY (seat_type_id)
        REFERENCES hngout.sys_seat_type (seat_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT prop_seat_type_prop_fk FOREIGN KEY (prop_id)
        REFERENCES hngout.property (prop_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hngout.prop_seat_type
    OWNER to tharinda;