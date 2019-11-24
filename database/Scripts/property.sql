-- SEQUENCE: hngout.property_prop_id_seq

-- DROP SEQUENCE hngout.property_prop_id_seq;

CREATE SEQUENCE hngout.property_prop_id_seq
    INCREMENT 1
    START 237
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE hngout.property_prop_id_seq
    OWNER TO tharinda;


-- Table: hngout.property

-- DROP TABLE hngout.property;

CREATE TABLE hngout.property
(
    prop_id bigint NOT NULL DEFAULT nextval('hngout.property_prop_id_seq'::regclass),
    code character varying(10) COLLATE pg_catalog."default" NOT NULL,
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    location_name character varying(100) COLLATE pg_catalog."default",
    geo_location point,
    current_cont_id integer,
    current_cont_version smallint,
    start_time time without time zone,
    end_time time without time zone,
    org_id bigint,
    CONSTRAINT property_pkey PRIMARY KEY (prop_id),
    CONSTRAINT uk_code_prop UNIQUE (code)
,
    CONSTRAINT fk_contract FOREIGN KEY (current_cont_version, current_cont_id)
        REFERENCES hngout.contracts (version, contract_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_org FOREIGN KEY (org_id)
        REFERENCES hngout.organization (org_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hngout.property
    OWNER to tharinda;