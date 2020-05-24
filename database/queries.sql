--
-- PostgreSQL database cluster dump
--

-- Started on 2020-05-24 19:25:38

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md59529e68db84f4e548ae2128c4f675658';
CREATE ROLE tharinda;
ALTER ROLE tharinda WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION NOBYPASSRLS PASSWORD 'md59bb3c8f3a007d79b290682ae7fb7633c';






\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-05-24 19:25:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2020-05-24 19:25:39

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-05-24 19:25:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3100 (class 1262 OID 16399)
-- Name: hangouts; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE hangouts WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


\connect hangouts

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 16400)
-- Name: hngout; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA hngout;


--
-- TOC entry 219 (class 1259 OID 49330)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 213 (class 1259 OID 41019)
-- Name: contract_availability; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.contract_availability (
    contract_id bigint NOT NULL,
    avail_unit_id integer NOT NULL,
    count smallint,
    week_def_id smallint NOT NULL,
    season_id smallint NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 41009)
-- Name: contract_season; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.contract_season (
    season_id smallint NOT NULL,
    contract_id bigint NOT NULL,
    name character varying(100),
    from_date date,
    to_date date
);


--
-- TOC entry 197 (class 1259 OID 16453)
-- Name: contracts; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.contracts (
    contract_id bigint NOT NULL,
    version smallint NOT NULL,
    prop_id integer NOT NULL,
    timeslot smallint,
    version_txt character varying(50),
    name character varying(200),
    from_date date,
    to_date date
);


--
-- TOC entry 196 (class 1259 OID 16451)
-- Name: contract_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.contract_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3101 (class 0 OID 0)
-- Dependencies: 196
-- Name: contract_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: -
--

ALTER SEQUENCE hngout.contract_seq OWNED BY hngout.contracts.contract_id;


--
-- TOC entry 205 (class 1259 OID 24658)
-- Name: facilities_facility_id_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.facilities_facility_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 222 (class 1259 OID 49361)
-- Name: location_city; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.location_city (
    country_id smallint NOT NULL,
    state_id smallint NOT NULL,
    city_id smallint NOT NULL,
    name character varying(50),
    id bigint NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 49353)
-- Name: location_country; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.location_country (
    country_id smallint NOT NULL,
    name character varying(50)
);


--
-- TOC entry 223 (class 1259 OID 49378)
-- Name: location_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3102 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: -
--

ALTER SEQUENCE hngout.location_seq OWNED BY hngout.location_city.id;


--
-- TOC entry 221 (class 1259 OID 49356)
-- Name: location_state; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.location_state (
    country_id smallint NOT NULL,
    state_id smallint NOT NULL,
    name character varying(50)
);


--
-- TOC entry 224 (class 1259 OID 49387)
-- Name: location_suburb; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.location_suburb (
    suberb_id smallint NOT NULL,
    city_id bigint NOT NULL,
    name character varying(50)
);


--
-- TOC entry 228 (class 1259 OID 49514)
-- Name: menu; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.menu (
    menu_id bigint NOT NULL,
    name character varying(100),
    description character varying(2000)
);


--
-- TOC entry 230 (class 1259 OID 49537)
-- Name: menu_category; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.menu_category (
    menu_id bigint NOT NULL,
    cat_id smallint NOT NULL,
    name character varying(100)
);


--
-- TOC entry 233 (class 1259 OID 49582)
-- Name: menu_choices; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.menu_choices (
    menu_id bigint NOT NULL,
    cat_id smallint NOT NULL,
    prop_ch_id integer NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 16466)
-- Name: organization_org_id_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.organization_org_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 199 (class 1259 OID 16468)
-- Name: organization; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.organization (
    org_id bigint DEFAULT nextval('hngout.organization_org_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100)
);


--
-- TOC entry 215 (class 1259 OID 49258)
-- Name: payment_options; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.payment_options (
    option_id smallint NOT NULL,
    name character varying(20)
);


--
-- TOC entry 210 (class 1259 OID 40973)
-- Name: prop_availability_unit; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_availability_unit (
    prop_id integer NOT NULL,
    unit_id integer NOT NULL,
    name character varying(100),
    description text,
    capacity smallint,
    sys_unit_id integer
);


--
-- TOC entry 232 (class 1259 OID 49555)
-- Name: prop_choices; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_choices (
    prop_ch_id integer NOT NULL,
    prop_id bigint,
    choice_id bigint,
    name character varying(100),
    description character varying(2000)
);


--
-- TOC entry 203 (class 1259 OID 24582)
-- Name: prop_facilities; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_facilities (
    prop_id bigint NOT NULL,
    facility_id integer NOT NULL,
    name character varying(100),
    description text
);


--
-- TOC entry 229 (class 1259 OID 49522)
-- Name: prop_menu; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_menu (
    prop_id bigint NOT NULL,
    menu_id bigint NOT NULL
);


--
-- TOC entry 225 (class 1259 OID 49481)
-- Name: prop_operation_hours; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_operation_hours (
    prop_id bigint NOT NULL,
    op_id smallint NOT NULL,
    time_start time without time zone,
    time_end time without time zone,
    name character varying(100)
);


--
-- TOC entry 216 (class 1259 OID 49275)
-- Name: prop_payment_options; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_payment_options (
    prop_id bigint NOT NULL,
    option_id smallint NOT NULL
);


--
-- TOC entry 227 (class 1259 OID 49499)
-- Name: prop_reservations; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_reservations (
    prop_id bigint NOT NULL,
    res_id bigint NOT NULL,
    booked_contract_id integer
);


--
-- TOC entry 218 (class 1259 OID 49308)
-- Name: prop_speciality; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_speciality (
    prop_id bigint NOT NULL,
    speciality_id smallint NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 24700)
-- Name: prop_tags; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.prop_tags (
    prop_id bigint NOT NULL,
    tag_id integer NOT NULL,
    name character varying(100),
    description text
);


--
-- TOC entry 201 (class 1259 OID 16477)
-- Name: property; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.property (
    prop_id bigint NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(200) NOT NULL,
    description text,
    geo_location point,
    current_cont_id integer,
    start_time time without time zone,
    end_time time without time zone,
    org_id bigint,
    created_user character varying(50),
    created_date timestamp with time zone,
    modified_user character varying(50),
    modified_date timestamp with time zone,
    contact_id bigint,
    based_location_id bigint
);


--
-- TOC entry 200 (class 1259 OID 16475)
-- Name: property_prop_id_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.property_prop_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3103 (class 0 OID 0)
-- Dependencies: 200
-- Name: property_prop_id_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: -
--

ALTER SEQUENCE hngout.property_prop_id_seq OWNED BY hngout.property.prop_id;


--
-- TOC entry 217 (class 1259 OID 49290)
-- Name: property_speciality; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.property_speciality (
    speciality_id smallint NOT NULL,
    name character varying(20),
    description character varying(1000)
);


--
-- TOC entry 3104 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE property_speciality; Type: COMMENT; Schema: hngout; Owner: -
--

COMMENT ON TABLE hngout.property_speciality IS 'Hangout = Cuisines, Salon = Bridal,Heir etc';


--
-- TOC entry 226 (class 1259 OID 49491)
-- Name: reservations; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.reservations (
    reservation_id bigint NOT NULL,
    date date,
    "time" time without time zone,
    time_slots smallint,
    status character varying(10),
    host_user bigint,
    head_count smallint,
    special_req text
);


--
-- TOC entry 208 (class 1259 OID 40963)
-- Name: sys_avail_unit_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.sys_avail_unit_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 209 (class 1259 OID 40965)
-- Name: sys_availability_unit; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.sys_availability_unit (
    unit_id integer DEFAULT nextval('hngout.sys_avail_unit_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100),
    min_capacity smallint,
    max_capacity smallint
);


--
-- TOC entry 231 (class 1259 OID 49547)
-- Name: sys_choices; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.sys_choices (
    choice_id bigint NOT NULL,
    name character varying(100),
    description text
);


--
-- TOC entry 214 (class 1259 OID 49252)
-- Name: sys_contacts; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.sys_contacts (
    contact_id bigint DEFAULT nextval('hngout.contact_id_seq'::regclass) NOT NULL,
    type character varying(5) NOT NULL,
    name character varying(100),
    email character varying(254),
    phone_primary character varying(20),
    phone_secondary character varying(20),
    address_line1 character varying(100),
    address_line2 character varying(100),
    address_line3 character varying(100),
    location_id integer,
    zip_code character varying(10),
    web character varying(100)
);


--
-- TOC entry 202 (class 1259 OID 16506)
-- Name: sys_facilities; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.sys_facilities (
    facility_id integer DEFAULT nextval('hngout.facilities_facility_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    description text
);


--
-- TOC entry 206 (class 1259 OID 24660)
-- Name: tags_tag_id_seq; Type: SEQUENCE; Schema: hngout; Owner: -
--

CREATE SEQUENCE hngout.tags_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 204 (class 1259 OID 24653)
-- Name: sys_tags; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.sys_tags (
    tag_id integer DEFAULT nextval('hngout.tags_tag_id_seq'::regclass) NOT NULL,
    code character varying(10),
    description text,
    name character varying(100)
);


--
-- TOC entry 211 (class 1259 OID 40991)
-- Name: week_definition; Type: TABLE; Schema: hngout; Owner: -
--

CREATE TABLE hngout.week_definition (
    week_def_id smallint NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100),
    week character varying(7),
    s boolean[],
    sssss boolean[]
);


--
-- TOC entry 2828 (class 2604 OID 24590)
-- Name: contracts contract_id; Type: DEFAULT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contracts ALTER COLUMN contract_id SET DEFAULT nextval('hngout.contract_seq'::regclass);


--
-- TOC entry 2835 (class 2604 OID 49380)
-- Name: location_city id; Type: DEFAULT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_city ALTER COLUMN id SET DEFAULT nextval('hngout.location_seq'::regclass);


--
-- TOC entry 2830 (class 2604 OID 24637)
-- Name: property prop_id; Type: DEFAULT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property ALTER COLUMN prop_id SET DEFAULT nextval('hngout.property_prop_id_seq'::regclass);


--
-- TOC entry 3074 (class 0 OID 41019)
-- Dependencies: 213
-- Data for Name: contract_availability; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.contract_availability (contract_id, avail_unit_id, count, week_def_id, season_id) FROM stdin;
71	2	10	1	1
71	4	1	1	1
71	1	10	1	1
72	2	10	2	1
72	4	1	1	1
72	1	10	3	1
73	2	10	1	1
73	4	1	1	1
73	1	10	1	1
1	2	10	1	1
1	4	1	1	1
1	1	10	1	1
74	2	10	1	1
74	4	1	1	1
74	1	10	1	1
75	2	10	1	1
75	4	1	1	1
75	1	10	1	1
76	1	12	1	3
76	4	1	1	3
76	2	12	1	3
76	4	1	1	1
76	2	10	1	1
76	1	10	1	1
76	4	2	3	2
76	4	1	2	2
76	2	20	3	2
76	2	10	2	2
76	1	20	3	2
76	1	10	2	2
86	4	1	1	1
86	2	10	1	1
93	1	12	1	3
88	1	12	1	3
88	4	1	1	3
88	2	12	1	3
88	4	1	1	1
88	2	10	1	1
88	1	10	1	1
88	1	20	3	2
88	1	10	2	2
88	4	2	3	2
88	4	1	2	2
88	2	20	3	2
88	2	10	2	2
89	1	12	1	3
89	4	1	1	3
89	2	12	1	3
89	4	1	1	1
89	2	10	1	1
89	1	10	1	1
81	1	12	1	3
81	4	1	1	3
81	2	12	1	3
81	4	1	1	1
81	2	10	1	1
81	1	10	1	1
81	1	20	3	2
81	1	10	2	2
81	4	2	3	2
81	4	1	2	2
81	2	20	3	2
81	2	10	2	2
80	1	10	1	1
80	4	1	1	1
80	2	10	1	1
80	1	10	2	2
80	1	20	3	2
80	4	2	3	2
80	2	20	3	2
80	4	1	2	2
80	2	10	2	2
80	4	1	1	3
80	2	12	1	3
80	1	12	1	3
79	1	10	1	1
79	4	1	1	1
79	2	10	1	1
79	1	10	2	2
79	1	20	3	2
79	4	2	3	2
79	2	20	3	2
79	4	1	2	2
79	2	10	2	2
79	4	1	1	3
79	2	12	1	3
79	1	12	1	3
89	1	20	3	2
89	1	10	2	2
89	4	2	3	2
89	4	1	2	2
89	2	20	3	2
89	2	10	2	2
89	2	122	2	1
91	1	12	1	3
91	4	1	1	3
91	2	12	1	3
91	4	1	1	1
91	2	10	1	1
91	1	10	1	1
91	1	20	3	2
91	1	10	2	2
91	4	2	3	2
91	4	1	2	2
91	2	20	3	2
91	2	10	2	2
92	1	12	1	3
92	4	1	1	3
92	2	12	1	3
92	4	1	1	1
92	2	10	1	1
92	1	10	1	1
92	1	20	3	2
92	1	10	2	2
92	4	2	3	2
92	4	1	2	2
92	2	20	3	2
92	2	10	2	2
93	4	1	1	3
93	2	12	1	3
93	4	1	1	1
93	2	10	1	1
93	1	10	1	1
93	1	20	3	2
93	1	10	2	2
93	4	2	3	2
93	4	1	2	2
93	2	20	3	2
93	2	10	2	2
94	1	12	1	3
94	4	1	1	3
94	2	12	1	3
94	4	1	1	1
94	2	10	1	1
94	1	10	1	1
94	1	20	3	2
94	1	10	2	2
94	4	2	3	2
94	4	1	2	2
94	2	20	3	2
94	2	10	2	2
95	1	12	1	3
95	4	1	1	3
95	2	12	1	3
95	4	1	1	1
95	2	10	1	1
95	1	10	1	1
95	1	20	3	2
95	1	10	2	2
95	4	2	3	2
95	4	1	2	2
95	2	20	3	2
95	2	10	2	2
96	1	12	1	3
96	4	1	1	3
96	2	12	1	3
96	4	1	1	1
96	2	10	1	1
96	1	10	1	1
96	1	20	3	2
96	1	10	2	2
96	4	2	3	2
96	4	1	2	2
96	2	20	3	2
96	2	10	2	2
97	1	12	1	3
97	4	1	1	3
97	2	12	1	3
97	4	1	1	1
97	2	10	1	1
97	1	10	1	1
97	1	20	3	2
97	1	10	2	2
97	4	2	3	2
97	4	1	2	2
97	2	20	3	2
97	2	10	2	2
98	1	12	1	3
98	4	1	1	3
98	2	12	1	3
98	4	1	1	1
98	2	10	1	1
98	1	10	1	1
98	1	20	3	2
98	1	10	2	2
98	4	2	3	2
98	4	1	2	2
98	2	20	3	2
98	2	10	2	2
99	1	12	1	3
99	4	1	1	3
99	2	12	1	3
99	4	1	1	1
99	2	10	1	1
99	1	10	1	1
99	1	20	3	2
99	1	10	2	2
99	4	2	3	2
99	4	1	2	2
99	2	20	3	2
99	2	10	2	2
100	1	12	1	3
100	4	1	1	3
100	2	12	1	3
100	4	1	1	1
100	2	10	1	1
100	1	10	1	1
100	1	20	3	2
100	1	10	2	2
100	4	2	3	2
100	4	1	2	2
100	2	20	3	2
100	2	10	2	2
101	1	12	1	3
101	4	1	1	3
101	2	12	1	3
101	4	1	1	1
101	2	10	1	1
101	1	10	1	1
101	1	20	3	2
101	1	10	2	2
101	4	2	3	2
101	4	1	2	2
101	2	20	3	2
101	2	10	2	2
102	1	12	1	3
102	4	1	1	3
102	2	12	1	3
102	4	1	1	1
102	2	10	1	1
102	1	10	1	1
102	1	20	3	2
102	1	10	2	2
102	4	2	3	2
102	4	1	2	2
102	2	20	3	2
102	2	10	2	2
103	1	12	1	3
103	4	1	1	3
103	2	12	1	3
103	4	1	1	1
103	2	10	1	1
103	1	10	1	1
103	1	20	3	2
103	1	10	2	2
103	4	2	3	2
103	4	1	2	2
103	2	20	3	2
103	2	10	2	2
104	1	12	1	3
104	4	1	1	3
104	2	12	1	3
104	4	1	1	1
104	2	10	1	1
104	1	10	1	1
104	1	20	3	2
104	1	10	2	2
104	4	2	3	2
104	4	1	2	2
104	2	20	3	2
104	2	10	2	2
105	1	12	1	3
105	4	1	1	3
105	2	12	1	3
105	4	1	1	1
105	2	10	1	1
105	1	10	1	1
105	1	20	3	2
105	1	10	2	2
105	4	2	3	2
105	4	1	2	2
105	2	20	3	2
105	2	10	2	2
106	1	12	1	3
106	4	1	1	3
106	2	12	1	3
106	4	1	1	1
106	2	10	1	1
106	1	10	1	1
106	1	20	3	2
106	1	10	2	2
106	4	2	3	2
106	4	1	2	2
106	2	20	3	2
106	2	10	2	2
107	1	12	1	3
107	4	1	1	3
107	2	12	1	3
107	4	1	1	1
107	2	10	1	1
107	1	10	1	1
107	1	20	3	2
107	1	10	2	2
107	4	2	3	2
107	4	1	2	2
107	2	20	3	2
107	2	10	2	2
108	1	12	1	3
108	4	1	1	3
108	2	12	1	3
108	4	1	1	1
108	2	10	1	1
108	1	10	1	1
108	1	20	3	2
108	1	10	2	2
108	4	2	3	2
108	4	1	2	2
108	2	20	3	2
108	2	10	2	2
109	1	12	1	3
109	4	1	1	3
109	2	12	1	3
109	4	1	1	1
109	2	10	1	1
109	1	10	1	1
109	1	20	3	2
109	1	10	2	2
109	4	2	3	2
109	4	1	2	2
109	2	20	3	2
109	2	10	2	2
110	1	12	1	3
110	4	1	1	3
110	2	12	1	3
110	4	1	1	1
110	2	10	1	1
110	1	10	1	1
110	1	20	3	2
110	1	10	2	2
110	4	2	3	2
110	4	1	2	2
110	2	20	3	2
110	2	10	2	2
\.


--
-- TOC entry 3073 (class 0 OID 41009)
-- Dependencies: 212
-- Data for Name: contract_season; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.contract_season (season_id, contract_id, name, from_date, to_date) FROM stdin;
1	70	Jan to March	2020-01-01	2020-03-31
1	71	Jan to March	2020-01-01	2020-03-31
1	72	Jan to March	2020-01-01	2020-03-31
1	73	Jan to March	2020-01-01	2020-03-31
1	1	Jan to March	2020-01-01	2020-03-31
1	74	Jan to March	2020-01-01	2020-03-31
1	75	Jan to March	2020-01-01	2020-03-31
3	76	May to Dec	2020-06-01	2020-12-31
1	76	Jan to March	2020-01-01	2020-03-31
2	76	April to May	2020-04-01	2020-05-31
2	94	April to May	2020-04-01	2020-05-31
3	95	May to Dec	2020-06-01	2020-12-31
1	95	Jan to March	2020-01-01	2020-03-31
1	78	Jan to March	2020-01-01	2020-03-31
2	78	April to May	2020-04-01	2020-05-31
2	95	April to May	2020-04-01	2020-05-31
1	77	Jan to March	2020-01-01	2020-03-31
2	77	April to May	2020-04-01	2020-05-31
3	77	May to Decaaa	2020-06-01	2020-12-31
3	78	May to Decaaa	2020-06-01	2020-12-31
3	79	May to Dec	2020-06-01	2020-12-31
1	79	Jan to March	2020-01-01	2020-03-31
2	79	April to May	2020-04-01	2020-05-31
3	80	May to Dec	2020-06-01	2020-12-31
1	80	Jan to March	2020-01-01	2020-03-31
2	80	April to May	2020-04-01	2020-05-31
3	81	May to Dec	2020-06-01	2020-12-31
1	81	Jan to March	2020-01-01	2020-03-31
2	81	April to May	2020-04-01	2020-05-31
3	82	May to Dec	2020-06-01	2020-12-31
1	82	Jan to March	2020-01-01	2020-03-31
2	82	April to May	2020-04-01	2020-05-31
3	83	May to Dec	2020-06-01	2020-12-31
1	83	Jan to March	2020-01-01	2020-03-31
2	83	April to May	2020-04-01	2020-05-31
3	84	May to Dec	2020-06-01	2020-12-31
1	84	Jan to March	2020-01-01	2020-03-31
2	84	April to May	2020-04-01	2020-05-31
1	85	Jan to March	2020-01-01	2020-03-31
1	86	Jan to March	2020-01-01	2020-03-31
3	96	May to Dec	2020-06-01	2020-12-31
3	88	May to Dec	2020-06-01	2020-12-31
1	88	Jan to March	2020-01-01	2020-03-31
2	88	April to May	2020-04-01	2020-05-31
3	89	May to Dec	2020-06-01	2020-12-31
2	89	April to May	2020-04-01	2020-05-31
1	89	Jan to Marchaaa	2021-01-01	2021-03-31
3	91	May to Dec	2020-06-01	2020-12-31
1	91	Jan to March	2020-01-01	2020-03-31
2	91	April to May	2020-04-01	2020-05-31
3	92	May to Dec	2020-06-01	2020-12-31
1	92	Jan to March	2020-01-01	2020-03-31
2	92	April to May	2020-04-01	2020-05-31
3	93	May to Dec	2020-06-01	2020-12-31
1	93	Jan to March	2020-01-01	2020-03-31
2	93	April to May	2020-04-01	2020-05-31
3	94	May to Dec	2020-06-01	2020-12-31
1	94	Jan to March	2020-01-01	2020-03-31
1	96	Jan to March	2020-01-01	2020-03-31
2	96	April to May	2020-04-01	2020-05-31
3	97	May to Dec	2020-06-01	2020-12-31
1	97	Jan to March	2020-01-01	2020-03-31
2	97	April to May	2020-04-01	2020-05-31
3	98	May to Dec	2020-06-01	2020-12-31
1	98	Jan to March	2020-01-01	2020-03-31
2	98	April to May	2020-04-01	2020-05-31
3	99	May to Dec	2020-06-01	2020-12-31
1	99	Jan to March	2020-01-01	2020-03-31
2	99	April to May	2020-04-01	2020-05-31
3	100	May to Dec	2020-06-01	2020-12-31
1	100	Jan to March	2020-01-01	2020-03-31
2	100	April to May	2020-04-01	2020-05-31
3	101	May to Dec	2020-06-01	2020-12-31
1	101	Jan to March	2020-01-01	2020-03-31
2	101	April to May	2020-04-01	2020-05-31
3	102	May to Dec	2020-06-01	2020-12-31
1	102	Jan to March	2020-01-01	2020-03-31
2	102	April to May	2020-04-01	2020-05-31
3	103	May to Dec	2020-06-01	2020-12-31
1	103	Jan to March	2020-01-01	2020-03-31
2	103	April to May	2020-04-01	2020-05-31
3	104	May to Dec	2020-06-01	2020-12-31
1	104	Jan to March	2020-01-01	2020-03-31
2	104	April to May	2020-04-01	2020-05-31
3	105	May to Dec	2020-06-01	2020-12-31
1	105	Jan to March	2020-01-01	2020-03-31
2	105	April to May	2020-04-01	2020-05-31
3	106	May to Dec	2020-06-01	2020-12-31
1	106	Jan to March	2020-01-01	2020-03-31
2	106	April to May	2020-04-01	2020-05-31
3	107	May to Dec	2020-06-01	2020-12-31
1	107	Jan to March	2020-01-01	2020-03-31
2	107	April to May	2020-04-01	2020-05-31
3	108	May to Dec	2020-06-01	2020-12-31
1	108	Jan to March	2020-01-01	2020-03-31
2	108	April to May	2020-04-01	2020-05-31
3	109	May to Dec	2020-06-01	2020-12-31
1	109	Jan to March	2020-01-01	2020-03-31
2	109	April to May	2020-04-01	2020-05-31
3	110	May to Dec	2020-06-01	2020-12-31
1	110	Jan to March	2020-01-01	2020-03-31
2	110	April to May	2020-04-01	2020-05-31
\.


--
-- TOC entry 3058 (class 0 OID 16453)
-- Dependencies: 197
-- Data for Name: contracts; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.contracts (contract_id, version, prop_id, timeslot, version_txt, name, from_date, to_date) FROM stdin;
12	1	1	30	V1.0	\N	\N	\N
13	1	1	30	V1.0	\N	\N	\N
26	1	1	60	dsds	\N	\N	\N
29	1	1	30	V1.0	\N	\N	\N
30	1	1	30	V1.0	\N	\N	\N
31	1	1	30	V1.0	\N	\N	\N
52	1	1	30	V1111.0	\N	\N	\N
70	1	1	30	V1111.0	\N	\N	\N
71	1	1	60	V1111.0	\N	\N	\N
72	1	1	60	V1111.0	\N	\N	\N
73	1	1	30	V1111.0	\N	\N	\N
1	1	1	60	V1.1	\N	\N	\N
74	1	1	30	V1.0	\N	\N	\N
75	1	1	30	V1.0	2020 Full year	2020-01-01	2020-12-31
76	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
78	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
77	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
81	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
80	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31
79	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31
82	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31
83	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31
84	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31
85	1	1	60	V2.0	2020 Full yearqq	2020-01-01	2020-12-31
86	1	1	60	V2.0	2020 Full yearqq	2020-01-01	2020-12-31
88	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
89	1	1	60	V2.0	2020 Full yearqqqqqqqq	2020-01-01	2020-12-31
91	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
92	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
93	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
94	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
95	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
96	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
97	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
98	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
99	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
100	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
101	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
102	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
103	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
104	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
105	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
106	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
107	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
108	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
109	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
110	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31
\.


--
-- TOC entry 3083 (class 0 OID 49361)
-- Dependencies: 222
-- Data for Name: location_city; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.location_city (country_id, state_id, city_id, name, id) FROM stdin;
1	1	1	Colombo 01	1
1	1	2	Colombo 02	2
1	1	3	Nugegoda	3
\.


--
-- TOC entry 3081 (class 0 OID 49353)
-- Dependencies: 220
-- Data for Name: location_country; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.location_country (country_id, name) FROM stdin;
1	Sri Lanka
\.


--
-- TOC entry 3082 (class 0 OID 49356)
-- Dependencies: 221
-- Data for Name: location_state; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.location_state (country_id, state_id, name) FROM stdin;
1	1	Colombo
1	2	Kandy
1	3	Galle
\.


--
-- TOC entry 3085 (class 0 OID 49387)
-- Dependencies: 224
-- Data for Name: location_suburb; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.location_suburb (suberb_id, city_id, name) FROM stdin;
\.


--
-- TOC entry 3089 (class 0 OID 49514)
-- Dependencies: 228
-- Data for Name: menu; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.menu (menu_id, name, description) FROM stdin;
\.


--
-- TOC entry 3091 (class 0 OID 49537)
-- Dependencies: 230
-- Data for Name: menu_category; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.menu_category (menu_id, cat_id, name) FROM stdin;
\.


--
-- TOC entry 3094 (class 0 OID 49582)
-- Dependencies: 233
-- Data for Name: menu_choices; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.menu_choices (menu_id, cat_id, prop_ch_id) FROM stdin;
\.


--
-- TOC entry 3060 (class 0 OID 16468)
-- Dependencies: 199
-- Data for Name: organization; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.organization (org_id, code, name) FROM stdin;
2	orgde22232	Jini
1	mcn	Machan Restaurant
3	amaya	Amaya Restaurant
0	mcnxxxx	Machan Restaurantxxxxxxx
9	mcn3	Machan xxxxxyyyy
10	mcn_kott	Machan Kottawazz
\.


--
-- TOC entry 3076 (class 0 OID 49258)
-- Dependencies: 215
-- Data for Name: payment_options; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.payment_options (option_id, name) FROM stdin;
1	Cash Payment
2	MasterCard
3	Visa
4	FriMi
5	AMEX
\.


--
-- TOC entry 3071 (class 0 OID 40973)
-- Dependencies: 210
-- Data for Name: prop_availability_unit; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_availability_unit (prop_id, unit_id, name, description, capacity, sys_unit_id) FROM stdin;
1	1	Regular Seat	Regular Seat	1	1
1	2	\N	\N	1	2
1	3	Meeting Room	Business Meeting Room	10	4
1	4	Party Room	Party Room	12	4
\.


--
-- TOC entry 3093 (class 0 OID 49555)
-- Dependencies: 232
-- Data for Name: prop_choices; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_choices (prop_ch_id, prop_id, choice_id, name, description) FROM stdin;
\.


--
-- TOC entry 3064 (class 0 OID 24582)
-- Dependencies: 203
-- Data for Name: prop_facilities; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_facilities (prop_id, facility_id, name, description) FROM stdin;
1	1	Vehicle Parking	Vehicle Parking available with own risk
1	2	\N	\N
1	3	\N	\N
327	1	Vehicle Parking	Vehicle Parking available with own risk
331	1	Vehicle Parking	Vehicle Parking available with own risk
348	1	Vehicle Parking	Vehicle Parking available with own risk
349	1	Vehicle Parking	Vehicle Parking available with own risk
351	1	Vehicle Parking	Vehicle Parking available with own risk
352	1	Vehicle Parking	Vehicle Parking available with own risk
353	1	Vehicle Parking	Vehicle Parking available with own risk
354	1	Vehicle Parking	Vehicle Parking available with own risk
355	1	Vehicle Parking	Vehicle Parking available with own risk
356	1	Vehicle Parking	Vehicle Parking available with own risk
357	1	Vehicle Parking	Vehicle Parking available with own risk
359	1	Vehicle Parking	Vehicle Parking available with own risk
360	1	Vehicle Parking	Vehicle Parking available with own risk
361	1	Vehicle Parking	Vehicle Parking available with own risk
362	1	Vehicle Parking	Vehicle Parking available with own risk
363	1	Vehicle Parking	Vehicle Parking available with own risk
364	1	Vehicle Parking	Vehicle Parking available with own risk
366	1	Vehicle Parking	Vehicle Parking available with own risk
367	1	Vehicle Parking	Vehicle Parking available with own risk
368	1	Vehicle Parking	Vehicle Parking available with own risk
370	3	Vehicle Parking	Vehicle Parking available with own risk
371	3	Vehicle Parking	Vehicle Parking available with own risk
372	3	Vehicle Parking	Vehicle Parking available with own risk
373	3	Vehicle Parking	Vehicle Parking available with own risk
374	3	Vehicle Parking	Vehicle Parking available with own risk
375	3	Vehicle Parking	Vehicle Parking available with own risk
376	5	Vehicle Parkingxxxxxxxxxxxxxxxx	Vehicle Parking available with own riskxxxxxxxxx
378	7	\N	\N
382	3	Vehicle Parking	Vehicle Parking available with own risk
382	8	\N	\N
\.


--
-- TOC entry 3090 (class 0 OID 49522)
-- Dependencies: 229
-- Data for Name: prop_menu; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_menu (prop_id, menu_id) FROM stdin;
\.


--
-- TOC entry 3086 (class 0 OID 49481)
-- Dependencies: 225
-- Data for Name: prop_operation_hours; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_operation_hours (prop_id, op_id, time_start, time_end, name) FROM stdin;
\.


--
-- TOC entry 3077 (class 0 OID 49275)
-- Dependencies: 216
-- Data for Name: prop_payment_options; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_payment_options (prop_id, option_id) FROM stdin;
1	1
1	2
1	3
1	4
1	5
\.


--
-- TOC entry 3088 (class 0 OID 49499)
-- Dependencies: 227
-- Data for Name: prop_reservations; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_reservations (prop_id, res_id, booked_contract_id) FROM stdin;
\.


--
-- TOC entry 3079 (class 0 OID 49308)
-- Dependencies: 218
-- Data for Name: prop_speciality; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_speciality (prop_id, speciality_id) FROM stdin;
1	1
1	2
1	3
1	4
\.


--
-- TOC entry 3068 (class 0 OID 24700)
-- Dependencies: 207
-- Data for Name: prop_tags; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.prop_tags (prop_id, tag_id, name, description) FROM stdin;
1	2	\N	\N
1	1	Beach Front	Beach Front
382	1	Beach Front	Beach Front
\.


--
-- TOC entry 3062 (class 0 OID 16477)
-- Dependencies: 201
-- Data for Name: property; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.property (prop_id, code, name, description, geo_location, current_cont_id, start_time, end_time, org_id, created_user, created_date, modified_user, modified_date, contact_id, based_location_id) FROM stdin;
3	mcn_kottaw	Machan Kottawa	dsdsssssssssssssssss	\N	\N	\N	\N	1	\N	\N	\N	\N	\N	\N
6	mcn_kotaat	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
1	mcn_pannip	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	(6.84663999999999984,79.9467590000000001)	\N	08:00:00	20:00:00	1	\N	\N	\N	\N	1	1
5	mcn_kottx	Machan KottawazXXXz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
7	mcnx_k	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
4	amaya_hill	Amaya Hills Kandy	dsdsdsdsds	\N	\N	\N	\N	3	\N	\N	\N	\N	\N	\N
238	mcnxx	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
240	mcnxpx	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
243	mcnxpllx	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
245	qwe	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
246	qwe1	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
247	qwe2	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
251	mcnxxzz	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
2	mcn_marada	Machan Maradana	\N	\N	\N	\N	\N	1	\N	\N	\N	\N	\N	\N
270	mcn_1	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	\N
272	mcn_2	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	1
274	mcn_3	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	2
275	mcn_4	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	2
276	mcn_5	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	1	2
280	mcn_6	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	3	2
281	mcn_7	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	4	2
282	mcn_8	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	5	2
284	mcn_10	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	18	1
283	mcn_9	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	6	2
299	mcn_11	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	18	1
303	mcn1_11	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	89	08:00:00	20:00:00	\N	\N	\N	\N	\N	17	1
348	x_3	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	72	08:00:00	20:00:00	\N	\N	\N	\N	\N	52	1
307	mcn1_12	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	88	08:00:00	20:00:00	\N	\N	\N	\N	\N	25	1
327	x_1	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	70	08:00:00	20:00:00	\N	\N	\N	\N	\N	41	1
331	x_2	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	71	08:00:00	20:00:00	\N	\N	\N	\N	\N	43	1
349	x_4	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	73	08:00:00	20:00:00	\N	\N	\N	\N	\N	53	1
351	x_5	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	74	08:00:00	20:00:00	\N	\N	\N	\N	\N	55	1
352	x_6	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	75	08:00:00	20:00:00	\N	\N	\N	\N	\N	56	1
353	x_7	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	76	08:00:00	20:00:00	\N	\N	\N	\N	\N	57	1
354	x_8	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	77	08:00:00	20:00:00	\N	\N	\N	\N	\N	58	1
355	x_9	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	78	08:00:00	20:00:00	\N	\N	\N	\N	\N	59	1
356	x1_1	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	79	08:00:00	20:00:00	\N	\N	\N	\N	\N	60	1
357	x1_2	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	80	08:00:00	20:00:00	\N	\N	\N	\N	\N	61	1
359	x1_3	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	81	08:00:00	20:00:00	\N	\N	\N	\N	\N	63	1
360	x360	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	82	08:00:00	20:00:00	\N	\N	\N	\N	\N	64	1
361	x361	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	83	08:00:00	20:00:00	\N	\N	\N	\N	\N	65	1
362	x362	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	84	08:00:00	20:00:00	\N	\N	\N	\N	\N	66	1
363	x363	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	85	08:00:00	20:00:00	\N	\N	\N	\N	\N	67	1
364	x364	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	86	08:00:00	20:00:00	\N	\N	\N	\N	\N	68	3
366	x366	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	91	08:00:00	20:00:00	\N	\N	\N	\N	\N	70	3
367	x367	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	92	08:00:00	20:00:00	\N	\N	\N	\N	\N	71	3
368	x368	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	93	08:00:00	20:00:00	\N	\N	\N	\N	\N	72	3
370	x370	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	94	08:00:00	20:00:00	\N	\N	\N	\N	\N	74	3
371	x371	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	95	08:00:00	20:00:00	\N	\N	\N	\N	\N	75	3
372	x372	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	96	08:00:00	20:00:00	\N	\N	\N	\N	\N	76	3
373	x373	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	97	08:00:00	20:00:00	\N	\N	\N	\N	\N	77	3
374	x374	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	98	08:00:00	20:00:00	\N	\N	\N	\N	\N	78	3
375	x375	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	99	08:00:00	20:00:00	\N	\N	\N	\N	\N	79	3
376	x376	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	100	08:00:00	20:00:00	\N	\N	\N	\N	\N	89	3
382	x382	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	102	08:00:00	20:00:00	\N	\N	\N	\N	\N	103	3
378	x378	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	101	08:00:00	20:00:00	\N	\N	\N	\N	\N	94	3
\.


--
-- TOC entry 3078 (class 0 OID 49290)
-- Dependencies: 217
-- Data for Name: property_speciality; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.property_speciality (speciality_id, name, description) FROM stdin;
1	Asian	\N
3	Thai	\N
4	Chinese	\N
2	Italian	Italian cuisine is a Mediterranean cuisine consisting of the ingredients, recipes and cooking techniques
\.


--
-- TOC entry 3087 (class 0 OID 49491)
-- Dependencies: 226
-- Data for Name: reservations; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.reservations (reservation_id, date, "time", time_slots, status, host_user, head_count, special_req) FROM stdin;
\.


--
-- TOC entry 3070 (class 0 OID 40965)
-- Dependencies: 209
-- Data for Name: sys_availability_unit; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.sys_availability_unit (unit_id, code, name, min_capacity, max_capacity) FROM stdin;
1	reg	Regular Seat	1	1
2	rooftop	Roof top Seat	1	1
3	beachfront	Beach front seat	1	1
4	room	Regular Room	7	15
5	booth	Personal Booth	2	6
6	hall	Hall	16	100
\.


--
-- TOC entry 3092 (class 0 OID 49547)
-- Dependencies: 231
-- Data for Name: sys_choices; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.sys_choices (choice_id, name, description) FROM stdin;
\.


--
-- TOC entry 3075 (class 0 OID 49252)
-- Dependencies: 214
-- Data for Name: sys_contacts; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.sys_contacts (contact_id, type, name, email, phone_primary, phone_secondary, address_line1, address_line2, address_line3, location_id, zip_code, web) FROM stdin;
1	prop	Machang	machan@info.com	+94789745654	+01124544554	this road	that road	city	-500	12345	machang.com
2	prop	ds	\N	\N	\N	\N	\N	\N	\N	\N	\N
3	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
4	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
5	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
6	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
7	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
8	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
9	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
10	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
11	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
12	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
13	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
14	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
15	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
16	prop	Machangaaaaa	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
61	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
18	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
20	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
17	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
22	prop	CCCCCCCCCCCCCCCCCCCCCCCCCCCC	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
23	prop	CCCCCCCCCCCCCCCCCCCCCCCCCCCC	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
24	prop	CCCCCCCCCCCCCCCCCCCCCCCCCCCCxxx	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
25	prop	CCCCCCCCCCCCCCCCCCCCCCCCCCCCxxx	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
41	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
43	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
52	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
53	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
55	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
56	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
57	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
58	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
59	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
60	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
63	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
64	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
65	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
66	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
67	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
68	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
70	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
71	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
72	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
74	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
75	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
76	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
77	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
78	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
79	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
80	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
81	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
82	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
83	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
84	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
85	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
86	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
87	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
88	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
89	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
92	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
93	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
94	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
98	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
99	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
100	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
101	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
102	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
103	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
\.


--
-- TOC entry 3063 (class 0 OID 16506)
-- Dependencies: 202
-- Data for Name: sys_facilities; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.sys_facilities (facility_id, code, name, description) FROM stdin;
3	smoking	Smoking	Smoking
1	parking2	Vehicle Parking2	Vehicle Parking2
0	parking	Vehicle Parking	Vehicle Parking
4	parking3	Vehicle Parking	Vehicle Parking
5	parking4xx	Vehicle Parking	Vehicle Parking
6	parking8	Vehicle Parking	Vehicle Parking
7	parking9	Vehicle Parking	Vehicle Parking
8	parking19	Vehicle Parking	Vehicle Parking
9	parking11	Vehicle Parking	Vehicle Parking
2	booze	Booze	Liquor Availableaaaaaa
13	parking14	Vehicle Parking	Vehicle Parking
16	parking1	Vehicle Parking	Vehicle Parking
20	parking1q	Vehicle Parking	Vehicle Parking
23	parki11ng1	Vehicle Parking	Vehicle Parking
\.


--
-- TOC entry 3065 (class 0 OID 24653)
-- Dependencies: 204
-- Data for Name: sys_tags; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.sys_tags (tag_id, code, description, name) FROM stdin;
1	seaview	Sea View 	Sea View
3	rooftop	Rooftop	Rooftop
2	music	Music Lobvr	Music
14	music1	Music Lobvr	Music
15	music2	Music Lobvr	Music
16	music3	Music Lobvr	Music
\.


--
-- TOC entry 3072 (class 0 OID 40991)
-- Dependencies: 211
-- Data for Name: week_definition; Type: TABLE DATA; Schema: hngout; Owner: -
--

COPY hngout.week_definition (week_def_id, code, name, week, s, sssss) FROM stdin;
4	fridayclos	Close Only Friday	1111011	{t,f}	\N
1	default	Full Week	1111111	{t,f}	\N
2	wd	Week Days	1111100	{t,f}	\N
3	we	Week End	0000011	{t,f}	\N
5	xxx	ssssss	1111111	{t,f}	\N
\.


--
-- TOC entry 3105 (class 0 OID 0)
-- Dependencies: 219
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.contact_id_seq', 105, true);


--
-- TOC entry 3106 (class 0 OID 0)
-- Dependencies: 196
-- Name: contract_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.contract_seq', 110, true);


--
-- TOC entry 3107 (class 0 OID 0)
-- Dependencies: 205
-- Name: facilities_facility_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.facilities_facility_id_seq', 23, true);


--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.location_seq', 3, true);


--
-- TOC entry 3109 (class 0 OID 0)
-- Dependencies: 198
-- Name: organization_org_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.organization_org_id_seq', 472, true);


--
-- TOC entry 3110 (class 0 OID 0)
-- Dependencies: 200
-- Name: property_prop_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.property_prop_id_seq', 384, true);


--
-- TOC entry 3111 (class 0 OID 0)
-- Dependencies: 208
-- Name: sys_avail_unit_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.sys_avail_unit_seq', 7, true);


--
-- TOC entry 3112 (class 0 OID 0)
-- Dependencies: 206
-- Name: tags_tag_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: -
--

SELECT pg_catalog.setval('hngout.tags_tag_id_seq', 16, true);


--
-- TOC entry 2871 (class 2606 OID 49269)
-- Name: sys_contacts contacts_pk; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_contacts
    ADD CONSTRAINT contacts_pk PRIMARY KEY (contact_id);


--
-- TOC entry 2847 (class 2606 OID 16513)
-- Name: sys_facilities facilities_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_facilities
    ADD CONSTRAINT facilities_pkey PRIMARY KEY (facility_id);


--
-- TOC entry 2883 (class 2606 OID 49360)
-- Name: location_state location_state_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_state
    ADD CONSTRAINT location_state_pkey PRIMARY KEY (state_id, country_id);


--
-- TOC entry 2887 (class 2606 OID 49391)
-- Name: location_suburb location_suburb_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_suburb
    ADD CONSTRAINT location_suburb_pkey PRIMARY KEY (suberb_id, city_id);


--
-- TOC entry 2899 (class 2606 OID 49541)
-- Name: menu_category menu_category_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.menu_category
    ADD CONSTRAINT menu_category_pkey PRIMARY KEY (menu_id, cat_id);


--
-- TOC entry 2905 (class 2606 OID 49586)
-- Name: menu_choices menu_choices_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.menu_choices
    ADD CONSTRAINT menu_choices_pkey PRIMARY KEY (menu_id, cat_id, prop_ch_id);


--
-- TOC entry 2895 (class 2606 OID 49521)
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (menu_id);


--
-- TOC entry 2839 (class 2606 OID 24626)
-- Name: organization org_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.organization
    ADD CONSTRAINT org_pkey PRIMARY KEY (org_id);


--
-- TOC entry 2873 (class 2606 OID 49265)
-- Name: payment_options payment_type_pk; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.payment_options
    ADD CONSTRAINT payment_type_pk PRIMARY KEY (option_id);


--
-- TOC entry 2869 (class 2606 OID 49432)
-- Name: contract_availability pk_avail; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contract_availability
    ADD CONSTRAINT pk_avail PRIMARY KEY (contract_id, avail_unit_id, week_def_id, season_id);


--
-- TOC entry 2885 (class 2606 OID 49385)
-- Name: location_city pk_city; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_city
    ADD CONSTRAINT pk_city PRIMARY KEY (id);


--
-- TOC entry 2837 (class 2606 OID 49416)
-- Name: contracts pk_contract; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contracts
    ADD CONSTRAINT pk_contract PRIMARY KEY (contract_id);


--
-- TOC entry 2881 (class 2606 OID 49372)
-- Name: location_country pk_country; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_country
    ADD CONSTRAINT pk_country PRIMARY KEY (country_id);


--
-- TOC entry 2867 (class 2606 OID 49430)
-- Name: contract_season pk_season; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contract_season
    ADD CONSTRAINT pk_season PRIMARY KEY (contract_id, season_id);


--
-- TOC entry 2877 (class 2606 OID 49304)
-- Name: property_speciality pk_specialty; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property_speciality
    ADD CONSTRAINT pk_specialty PRIMARY KEY (speciality_id);


--
-- TOC entry 2879 (class 2606 OID 49312)
-- Name: prop_speciality pk_specialty_prop; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_speciality
    ADD CONSTRAINT pk_specialty_prop PRIMARY KEY (prop_id, speciality_id);


--
-- TOC entry 2863 (class 2606 OID 40980)
-- Name: prop_availability_unit prop_avail_type_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_pkey PRIMARY KEY (prop_id, unit_id);


--
-- TOC entry 2903 (class 2606 OID 49574)
-- Name: prop_choices prop_choices_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_choices
    ADD CONSTRAINT prop_choices_pkey PRIMARY KEY (prop_ch_id);


--
-- TOC entry 2851 (class 2606 OID 49454)
-- Name: prop_facilities prop_facilities_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facilities_pkey PRIMARY KEY (prop_id, facility_id);


--
-- TOC entry 2897 (class 2606 OID 49526)
-- Name: prop_menu prop_menu_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_menu
    ADD CONSTRAINT prop_menu_pkey PRIMARY KEY (prop_id, menu_id);


--
-- TOC entry 2889 (class 2606 OID 49485)
-- Name: prop_operation_hours prop_operation_hours_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_operation_hours
    ADD CONSTRAINT prop_operation_hours_pkey PRIMARY KEY (prop_id, op_id);


--
-- TOC entry 2875 (class 2606 OID 49279)
-- Name: prop_payment_options prop_payment_pk; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_payment_options
    ADD CONSTRAINT prop_payment_pk PRIMARY KEY (prop_id, option_id);


--
-- TOC entry 2893 (class 2606 OID 49503)
-- Name: prop_reservations prop_reservations_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_reservations
    ADD CONSTRAINT prop_reservations_pkey PRIMARY KEY (prop_id, res_id);


--
-- TOC entry 2857 (class 2606 OID 49468)
-- Name: prop_tags prop_tags_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_tags
    ADD CONSTRAINT prop_tags_pkey PRIMARY KEY (prop_id, tag_id);


--
-- TOC entry 2843 (class 2606 OID 24639)
-- Name: property property_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT property_pkey PRIMARY KEY (prop_id);


--
-- TOC entry 2891 (class 2606 OID 49498)
-- Name: reservations reservations_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (reservation_id);


--
-- TOC entry 2859 (class 2606 OID 49185)
-- Name: sys_availability_unit sys_avail_type_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_availability_unit
    ADD CONSTRAINT sys_avail_type_pkey PRIMARY KEY (unit_id);


--
-- TOC entry 2901 (class 2606 OID 49554)
-- Name: sys_choices sys_choices_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_choices
    ADD CONSTRAINT sys_choices_pkey PRIMARY KEY (choice_id);


--
-- TOC entry 2853 (class 2606 OID 24657)
-- Name: sys_tags tags_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tag_id);


--
-- TOC entry 2861 (class 2606 OID 40972)
-- Name: sys_availability_unit uk_avail_unit_code; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_availability_unit
    ADD CONSTRAINT uk_avail_unit_code UNIQUE (code);


--
-- TOC entry 2849 (class 2606 OID 16519)
-- Name: sys_facilities uk_code_facility; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_facilities
    ADD CONSTRAINT uk_code_facility UNIQUE (code);


--
-- TOC entry 2841 (class 2606 OID 16517)
-- Name: organization uk_code_org; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.organization
    ADD CONSTRAINT uk_code_org UNIQUE (code);


--
-- TOC entry 2845 (class 2606 OID 16521)
-- Name: property uk_code_prop; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT uk_code_prop UNIQUE (code);


--
-- TOC entry 2855 (class 2606 OID 49407)
-- Name: sys_tags uk_tag_code; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.sys_tags
    ADD CONSTRAINT uk_tag_code UNIQUE (code);


--
-- TOC entry 2865 (class 2606 OID 49197)
-- Name: week_definition week_definition_pkey; Type: CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.week_definition
    ADD CONSTRAINT week_definition_pkey PRIMARY KEY (week_def_id);


--
-- TOC entry 2917 (class 2606 OID 49220)
-- Name: contract_availability avail_week_def_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contract_availability
    ADD CONSTRAINT avail_week_def_fk FOREIGN KEY (week_def_id) REFERENCES hngout.week_definition(week_def_id);


--
-- TOC entry 2932 (class 2606 OID 49563)
-- Name: prop_choices fk_choice_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_choices
    ADD CONSTRAINT fk_choice_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2909 (class 2606 OID 49333)
-- Name: property fk_contact; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_contact FOREIGN KEY (contact_id) REFERENCES hngout.sys_contacts(contact_id);


--
-- TOC entry 2906 (class 2606 OID 49417)
-- Name: property fk_contract; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_contract FOREIGN KEY (current_cont_id) REFERENCES hngout.contracts(contract_id);


--
-- TOC entry 2923 (class 2606 OID 49373)
-- Name: location_state fk_country; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_state
    ADD CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES hngout.location_country(country_id);


--
-- TOC entry 2908 (class 2606 OID 49397)
-- Name: property fk_location; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_location FOREIGN KEY (based_location_id) REFERENCES hngout.location_city(id);


--
-- TOC entry 2931 (class 2606 OID 49542)
-- Name: menu_category fk_menu_cat; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.menu_category
    ADD CONSTRAINT fk_menu_cat FOREIGN KEY (menu_id) REFERENCES hngout.menu(menu_id);


--
-- TOC entry 2934 (class 2606 OID 49587)
-- Name: menu_choices fk_menu_cat_choices; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.menu_choices
    ADD CONSTRAINT fk_menu_cat_choices FOREIGN KEY (menu_id, cat_id) REFERENCES hngout.menu_category(menu_id, cat_id);


--
-- TOC entry 2935 (class 2606 OID 49592)
-- Name: menu_choices fk_menu_choice_prop_ch; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.menu_choices
    ADD CONSTRAINT fk_menu_choice_prop_ch FOREIGN KEY (prop_ch_id) REFERENCES hngout.prop_choices(prop_ch_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2929 (class 2606 OID 49527)
-- Name: prop_menu fk_menu_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_menu
    ADD CONSTRAINT fk_menu_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2907 (class 2606 OID 32768)
-- Name: property fk_org; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_org FOREIGN KEY (org_id) REFERENCES hngout.organization(org_id);


--
-- TOC entry 2919 (class 2606 OID 49280)
-- Name: prop_payment_options fk_pay_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_payment_options
    ADD CONSTRAINT fk_pay_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2930 (class 2606 OID 49532)
-- Name: prop_menu fk_prop_menu; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_menu
    ADD CONSTRAINT fk_prop_menu FOREIGN KEY (menu_id) REFERENCES hngout.menu(menu_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2926 (class 2606 OID 49486)
-- Name: prop_operation_hours fk_prop_op; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_operation_hours
    ADD CONSTRAINT fk_prop_op FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2920 (class 2606 OID 49285)
-- Name: prop_payment_options fk_prop_pay; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_payment_options
    ADD CONSTRAINT fk_prop_pay FOREIGN KEY (option_id) REFERENCES hngout.payment_options(option_id);


--
-- TOC entry 2927 (class 2606 OID 49504)
-- Name: prop_reservations fk_prop_res; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_reservations
    ADD CONSTRAINT fk_prop_res FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2922 (class 2606 OID 49313)
-- Name: prop_speciality fk_prop_specialty; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_speciality
    ADD CONSTRAINT fk_prop_specialty FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2928 (class 2606 OID 49509)
-- Name: prop_reservations fk_res_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_reservations
    ADD CONSTRAINT fk_res_prop FOREIGN KEY (res_id) REFERENCES hngout.reservations(reservation_id);


--
-- TOC entry 2918 (class 2606 OID 49443)
-- Name: contract_availability fk_season_avail; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contract_availability
    ADD CONSTRAINT fk_season_avail FOREIGN KEY (contract_id, season_id) REFERENCES hngout.contract_season(contract_id, season_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2916 (class 2606 OID 49448)
-- Name: contract_season fk_season_contract; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.contract_season
    ADD CONSTRAINT fk_season_contract FOREIGN KEY (contract_id) REFERENCES hngout.contracts(contract_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2921 (class 2606 OID 49318)
-- Name: prop_speciality fk_specialty_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_speciality
    ADD CONSTRAINT fk_specialty_prop FOREIGN KEY (speciality_id) REFERENCES hngout.property_speciality(speciality_id);


--
-- TOC entry 2924 (class 2606 OID 49366)
-- Name: location_city fk_state_city; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_city
    ADD CONSTRAINT fk_state_city FOREIGN KEY (country_id, state_id) REFERENCES hngout.location_state(country_id, state_id);


--
-- TOC entry 2925 (class 2606 OID 49392)
-- Name: location_suburb fk_suburb; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.location_suburb
    ADD CONSTRAINT fk_suburb FOREIGN KEY (city_id) REFERENCES hngout.location_city(id);


--
-- TOC entry 2933 (class 2606 OID 49568)
-- Name: prop_choices fk_sys_choice; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_choices
    ADD CONSTRAINT fk_sys_choice FOREIGN KEY (choice_id) REFERENCES hngout.sys_choices(choice_id);


--
-- TOC entry 2913 (class 2606 OID 49469)
-- Name: prop_tags pprop_tags_prop_fki; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_tags
    ADD CONSTRAINT pprop_tags_prop_fki FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2915 (class 2606 OID 40986)
-- Name: prop_availability_unit prop_avail_type_prop_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_prop_fk FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2914 (class 2606 OID 49186)
-- Name: prop_availability_unit prop_avail_type_st_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_st_fk FOREIGN KEY (unit_id) REFERENCES hngout.sys_availability_unit(unit_id);


--
-- TOC entry 2910 (class 2606 OID 24682)
-- Name: prop_facilities prop_facility_facility_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facility_facility_fk FOREIGN KEY (facility_id) REFERENCES hngout.sys_facilities(facility_id);


--
-- TOC entry 2911 (class 2606 OID 49455)
-- Name: prop_facilities prop_facility_prop_fki; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facility_prop_fki FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2912 (class 2606 OID 24708)
-- Name: prop_tags prop_tags_tag_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: -
--

ALTER TABLE ONLY hngout.prop_tags
    ADD CONSTRAINT prop_tags_tag_fk FOREIGN KEY (tag_id) REFERENCES hngout.sys_tags(tag_id);


-- Completed on 2020-05-24 19:25:40

--
-- PostgreSQL database dump complete
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-05-24 19:25:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2812 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16393)
-- Name: quque; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.quque (
    qq date[],
    id integer
);


--
-- TOC entry 2806 (class 0 OID 16393)
-- Dependencies: 197
-- Data for Name: quque; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.quque (qq, id) FROM stdin;
\N	100
\N	100
\N	100
\N	100
\N	100
\N	100
\N	100
\.


-- Completed on 2020-05-24 19:25:40

--
-- PostgreSQL database dump complete
--

-- Completed on 2020-05-24 19:25:40

--
-- PostgreSQL database cluster dump complete
--

