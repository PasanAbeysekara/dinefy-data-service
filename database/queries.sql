--
-- PostgreSQL database cluster dump
--

-- Started on 2020-05-20 21:42:02

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

create ROLE postgres;
alter role postgres with SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md59529e68db84f4e548ae2128c4f675658';
create ROLE tharinda;
alter role tharinda with SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION NOBYPASSRLS PASSWORD 'md59bb3c8f3a007d79b290682ae7fb7633c';






\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-05-20 21:42:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
select pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2020-05-20 21:42:03

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-05-20 21:42:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
select pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3023 (class 1262 OID 16399)
-- Name: hangouts; Type: DATABASE; Schema: -; Owner: postgres
--

create DATABASE hangouts with TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


alter database hangouts OWNER TO postgres;

\connect hangouts

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
select pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 16400)
-- Name: hngout; Type: SCHEMA; Schema: -; Owner: tharinda
--

create SCHEMA hngout;


alter SCHEMA hngout OWNER TO tharinda;

--
-- TOC entry 219 (class 1259 OID 49330)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.contact_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


alter table hngout.contact_id_seq OWNER TO tharinda;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 213 (class 1259 OID 41019)
-- Name: contract_availability; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.contract_availability (
    contract_id bigint NOT NULL,
    avail_unit_id integer NOT NULL,
    count smallint,
    week_def_id smallint NOT NULL,
    season_id smallint NOT NULL
);


alter table hngout.contract_availability OWNER TO tharinda;

--
-- TOC entry 212 (class 1259 OID 41009)
-- Name: contract_season; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.contract_season (
    season_id smallint NOT NULL,
    contract_id bigint NOT NULL,
    name character varying(100),
    from_date date,
    to_date date
);


alter table hngout.contract_season OWNER TO tharinda;

--
-- TOC entry 197 (class 1259 OID 16453)
-- Name: contracts; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.contracts (
    contract_id bigint NOT NULL,
    version smallint NOT NULL,
    prop_id integer NOT NULL,
    timeslot smallint,
    version_txt character varying(50),
    name character varying(200),
    from_date date,
    to_date date
);


alter table hngout.contracts OWNER TO tharinda;

--
-- TOC entry 196 (class 1259 OID 16451)
-- Name: contract_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.contract_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table hngout.contract_seq OWNER TO tharinda;

--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 196
-- Name: contract_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: tharinda
--

alter sequence hngout.contract_seq OWNED BY hngout.contracts.contract_id;


--
-- TOC entry 205 (class 1259 OID 24658)
-- Name: facilities_facility_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.facilities_facility_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


alter table hngout.facilities_facility_id_seq OWNER TO tharinda;

--
-- TOC entry 222 (class 1259 OID 49361)
-- Name: location_city; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.location_city (
    country_id smallint NOT NULL,
    state_id smallint NOT NULL,
    city_id smallint NOT NULL,
    name character varying(50),
    id bigint NOT NULL
);


alter table hngout.location_city OWNER TO tharinda;

--
-- TOC entry 220 (class 1259 OID 49353)
-- Name: location_country; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.location_country (
    country_id smallint NOT NULL,
    name character varying(50)
);


alter table hngout.location_country OWNER TO tharinda;

--
-- TOC entry 223 (class 1259 OID 49378)
-- Name: location_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.location_seq
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table hngout.location_seq OWNER TO tharinda;

--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: tharinda
--

alter sequence hngout.location_seq OWNED BY hngout.location_city.id;


--
-- TOC entry 221 (class 1259 OID 49356)
-- Name: location_state; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.location_state (
    country_id smallint NOT NULL,
    state_id smallint NOT NULL,
    name character varying(50)
);


alter table hngout.location_state OWNER TO tharinda;

--
-- TOC entry 224 (class 1259 OID 49387)
-- Name: location_suburb; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.location_suburb (
    suberb_id smallint NOT NULL,
    city_id bigint NOT NULL,
    name character varying(50)
);


alter table hngout.location_suburb OWNER TO tharinda;

--
-- TOC entry 198 (class 1259 OID 16466)
-- Name: organization_org_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.organization_org_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


alter table hngout.organization_org_id_seq OWNER TO tharinda;

--
-- TOC entry 199 (class 1259 OID 16468)
-- Name: organization; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.organization (
    org_id bigint DEFAULT nextval('hngout.organization_org_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100)
);


alter table hngout.organization OWNER TO tharinda;

--
-- TOC entry 215 (class 1259 OID 49258)
-- Name: payment_options; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.payment_options (
    option_id smallint NOT NULL,
    name character varying(20)
);


alter table hngout.payment_options OWNER TO tharinda;

--
-- TOC entry 210 (class 1259 OID 40973)
-- Name: prop_availability_unit; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.prop_availability_unit (
    prop_id integer NOT NULL,
    unit_id integer NOT NULL,
    name character varying(100),
    description text,
    capacity smallint,
    sys_unit_id integer
);


alter table hngout.prop_availability_unit OWNER TO tharinda;

--
-- TOC entry 203 (class 1259 OID 24582)
-- Name: prop_facilities; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.prop_facilities (
    prop_id integer NOT NULL,
    facility_id integer NOT NULL,
    name character varying(100),
    description text
);


alter table hngout.prop_facilities OWNER TO tharinda;

--
-- TOC entry 216 (class 1259 OID 49275)
-- Name: prop_payment_options; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.prop_payment_options (
    prop_id bigint NOT NULL,
    option_id smallint NOT NULL
);


alter table hngout.prop_payment_options OWNER TO tharinda;

--
-- TOC entry 218 (class 1259 OID 49308)
-- Name: prop_speciality; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.prop_speciality (
    prop_id bigint NOT NULL,
    speciality_id smallint NOT NULL
);


alter table hngout.prop_speciality OWNER TO tharinda;

--
-- TOC entry 207 (class 1259 OID 24700)
-- Name: prop_tags; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.prop_tags (
    prop_id integer NOT NULL,
    tag_id integer NOT NULL,
    name character varying(100),
    description text
);


alter table hngout.prop_tags OWNER TO tharinda;

--
-- TOC entry 201 (class 1259 OID 16477)
-- Name: property; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.property (
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


alter table hngout.property OWNER TO tharinda;

--
-- TOC entry 200 (class 1259 OID 16475)
-- Name: property_prop_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.property_prop_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table hngout.property_prop_id_seq OWNER TO tharinda;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 200
-- Name: property_prop_id_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: tharinda
--

alter sequence hngout.property_prop_id_seq OWNED BY hngout.property.prop_id;


--
-- TOC entry 217 (class 1259 OID 49290)
-- Name: property_speciality; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.property_speciality (
    speciality_id smallint NOT NULL,
    name character varying(20),
    description character varying(1000)
);


alter table hngout.property_speciality OWNER TO tharinda;

--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE property_speciality; Type: COMMENT; Schema: hngout; Owner: tharinda
--

COMMENT ON TABLE hngout.property_speciality IS 'Hangout = Cuisines, Salon = Bridal,Heir etc';


--
-- TOC entry 208 (class 1259 OID 40963)
-- Name: sys_avail_unit_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.sys_avail_unit_seq
    start with 1
    increment by 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


alter table hngout.sys_avail_unit_seq OWNER TO tharinda;

--
-- TOC entry 209 (class 1259 OID 40965)
-- Name: sys_availability_unit; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.sys_availability_unit (
    unit_id integer DEFAULT nextval('hngout.sys_avail_unit_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100),
    min_capacity smallint,
    max_capacity smallint
);


alter table hngout.sys_availability_unit OWNER TO tharinda;

--
-- TOC entry 214 (class 1259 OID 49252)
-- Name: sys_contacts; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.sys_contacts (
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


alter table hngout.sys_contacts OWNER TO tharinda;

--
-- TOC entry 202 (class 1259 OID 16506)
-- Name: sys_facilities; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.sys_facilities (
    facility_id integer DEFAULT nextval('hngout.facilities_facility_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    description text
);


alter table hngout.sys_facilities OWNER TO tharinda;

--
-- TOC entry 206 (class 1259 OID 24660)
-- Name: tags_tag_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

create sequence hngout.tags_tag_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


alter table hngout.tags_tag_id_seq OWNER TO tharinda;

--
-- TOC entry 204 (class 1259 OID 24653)
-- Name: sys_tags; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.sys_tags (
    tag_id integer DEFAULT nextval('hngout.tags_tag_id_seq'::regclass) NOT NULL,
    code character varying(10),
    description text,
    name character varying(100)
);


alter table hngout.sys_tags OWNER TO tharinda;

--
-- TOC entry 211 (class 1259 OID 40991)
-- Name: week_definition; Type: TABLE; Schema: hngout; Owner: tharinda
--

create TABLE hngout.week_definition (
    week_def_id smallint NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100),
    week character varying(7),
    s boolean[],
    sssss boolean[]
);


alter table hngout.week_definition OWNER TO tharinda;

--
-- TOC entry 2788 (class 2604 OID 24590)
-- Name: contracts contract_id; Type: DEFAULT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contracts alter COLUMN contract_id SET DEFAULT nextval('hngout.contract_seq'::regclass);


--
-- TOC entry 2795 (class 2604 OID 49380)
-- Name: location_city id; Type: DEFAULT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_city alter COLUMN id SET DEFAULT nextval('hngout.location_seq'::regclass);


--
-- TOC entry 2790 (class 2604 OID 24637)
-- Name: property prop_id; Type: DEFAULT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property alter COLUMN prop_id SET DEFAULT nextval('hngout.property_prop_id_seq'::regclass);


--
-- TOC entry 3006 (class 0 OID 41019)
-- Dependencies: 213
-- Data for Name: contract_availability; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
\.


--
-- TOC entry 3005 (class 0 OID 41009)
-- Dependencies: 212
-- Data for Name: contract_season; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
1	78	Jan to March	2020-01-01	2020-03-31
2	78	April to May	2020-04-01	2020-05-31
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
\.


--
-- TOC entry 2990 (class 0 OID 16453)
-- Dependencies: 197
-- Data for Name: contracts; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
\.


--
-- TOC entry 3015 (class 0 OID 49361)
-- Dependencies: 222
-- Data for Name: location_city; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_city (country_id, state_id, city_id, name, id) FROM stdin;
1	1	1	Colombo 01	1
1	1	2	Colombo 02	2
1	1	3	Nugegoda	3
\.


--
-- TOC entry 3013 (class 0 OID 49353)
-- Dependencies: 220
-- Data for Name: location_country; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_country (country_id, name) FROM stdin;
1	Sri Lanka
\.


--
-- TOC entry 3014 (class 0 OID 49356)
-- Dependencies: 221
-- Data for Name: location_state; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_state (country_id, state_id, name) FROM stdin;
1	1	Colombo
1	2	Kandy
1	3	Galle
\.


--
-- TOC entry 3017 (class 0 OID 49387)
-- Dependencies: 224
-- Data for Name: location_suburb; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_suburb (suberb_id, city_id, name) FROM stdin;
\.


--
-- TOC entry 2992 (class 0 OID 16468)
-- Dependencies: 199
-- Data for Name: organization; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
-- TOC entry 3008 (class 0 OID 49258)
-- Dependencies: 215
-- Data for Name: payment_options; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.payment_options (option_id, name) FROM stdin;
1	Cash Payment
2	MasterCard
3	Visa
4	FriMi
5	AMEX
\.


--
-- TOC entry 3003 (class 0 OID 40973)
-- Dependencies: 210
-- Data for Name: prop_availability_unit; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_availability_unit (prop_id, unit_id, name, description, capacity, sys_unit_id) FROM stdin;
1	1	Regular Seat	Regular Seat	1	1
1	2	\N	\N	1	2
1	3	Meeting Room	Business Meeting Room	10	4
1	4	Party Room	Party Room	12	4
\.


--
-- TOC entry 2996 (class 0 OID 24582)
-- Dependencies: 203
-- Data for Name: prop_facilities; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_facilities (prop_id, facility_id, name, description) FROM stdin;
1	1	Vehicle Parking	Vehicle Parking available with own risk
1	2	\N	\N
1	3	\N	\N
\.


--
-- TOC entry 3009 (class 0 OID 49275)
-- Dependencies: 216
-- Data for Name: prop_payment_options; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_payment_options (prop_id, option_id) FROM stdin;
1	1
1	2
1	3
1	4
1	5
\.


--
-- TOC entry 3011 (class 0 OID 49308)
-- Dependencies: 218
-- Data for Name: prop_speciality; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_speciality (prop_id, speciality_id) FROM stdin;
1	1
1	2
1	3
1	4
\.


--
-- TOC entry 3000 (class 0 OID 24700)
-- Dependencies: 207
-- Data for Name: prop_tags; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_tags (prop_id, tag_id, name, description) FROM stdin;
1	2	\N	\N
1	1	Beach Front	Beach Front
\.


--
-- TOC entry 2994 (class 0 OID 16477)
-- Dependencies: 201
-- Data for Name: property; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
\.


--
-- TOC entry 3010 (class 0 OID 49290)
-- Dependencies: 217
-- Data for Name: property_speciality; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.property_speciality (speciality_id, name, description) FROM stdin;
1	Asian	\N
3	Thai	\N
4	Chinese	\N
2	Italian	Italian cuisine is a Mediterranean cuisine consisting of the ingredients, recipes and cooking techniques
\.


--
-- TOC entry 3002 (class 0 OID 40965)
-- Dependencies: 209
-- Data for Name: sys_availability_unit; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
-- TOC entry 3007 (class 0 OID 49252)
-- Dependencies: 214
-- Data for Name: sys_contacts; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
17	prop	sasssss	machan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
18	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
20	prop	zzzzzzzzzz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
\.


--
-- TOC entry 2995 (class 0 OID 16506)
-- Dependencies: 202
-- Data for Name: sys_facilities; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
\.


--
-- TOC entry 2997 (class 0 OID 24653)
-- Dependencies: 204
-- Data for Name: sys_tags; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
-- TOC entry 3004 (class 0 OID 40991)
-- Dependencies: 211
-- Data for Name: week_definition; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.week_definition (week_def_id, code, name, week, s, sssss) FROM stdin;
4	fridayclos	Close Only Friday	1111011	{t,f}	\N
1	default	Full Week	1111111	{t,f}	\N
2	wd	Week Days	1111100	{t,f}	\N
3	we	Week End	0000011	{t,f}	\N
5	xxx	ssssss	1111111	{t,f}	\N
\.


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 219
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.contact_id_seq', 20, true);


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 196
-- Name: contract_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.contract_seq', 86, true);


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 205
-- Name: facilities_facility_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.facilities_facility_id_seq', 21, true);


--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.location_seq', 3, true);


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 198
-- Name: organization_org_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.organization_org_id_seq', 472, true);


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 200
-- Name: property_prop_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.property_prop_id_seq', 302, true);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 208
-- Name: sys_avail_unit_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.sys_avail_unit_seq', 7, true);


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 206
-- Name: tags_tag_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

select pg_catalog.setval('hngout.tags_tag_id_seq', 16, true);


--
-- TOC entry 2831 (class 2606 OID 49269)
-- Name: sys_contacts contacts_pk; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_contacts
    ADD CONSTRAINT contacts_pk PRIMARY KEY (contact_id);


--
-- TOC entry 2807 (class 2606 OID 16513)
-- Name: sys_facilities facilities_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_facilities
    ADD CONSTRAINT facilities_pkey PRIMARY KEY (facility_id);


--
-- TOC entry 2843 (class 2606 OID 49360)
-- Name: location_state location_state_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_state
    ADD CONSTRAINT location_state_pkey PRIMARY KEY (state_id, country_id);


--
-- TOC entry 2847 (class 2606 OID 49391)
-- Name: location_suburb location_suburb_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_suburb
    ADD CONSTRAINT location_suburb_pkey PRIMARY KEY (suberb_id, city_id);


--
-- TOC entry 2799 (class 2606 OID 24626)
-- Name: organization org_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.organization
    ADD CONSTRAINT org_pkey PRIMARY KEY (org_id);


--
-- TOC entry 2833 (class 2606 OID 49265)
-- Name: payment_options payment_type_pk; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.payment_options
    ADD CONSTRAINT payment_type_pk PRIMARY KEY (option_id);


--
-- TOC entry 2829 (class 2606 OID 49432)
-- Name: contract_availability pk_avail; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contract_availability
    ADD CONSTRAINT pk_avail PRIMARY KEY (contract_id, avail_unit_id, week_def_id, season_id);


--
-- TOC entry 2845 (class 2606 OID 49385)
-- Name: location_city pk_city; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_city
    ADD CONSTRAINT pk_city PRIMARY KEY (id);


--
-- TOC entry 2797 (class 2606 OID 49416)
-- Name: contracts pk_contract; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contracts
    ADD CONSTRAINT pk_contract PRIMARY KEY (contract_id);


--
-- TOC entry 2841 (class 2606 OID 49372)
-- Name: location_country pk_country; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_country
    ADD CONSTRAINT pk_country PRIMARY KEY (country_id);


--
-- TOC entry 2827 (class 2606 OID 49430)
-- Name: contract_season pk_season; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contract_season
    ADD CONSTRAINT pk_season PRIMARY KEY (contract_id, season_id);


--
-- TOC entry 2837 (class 2606 OID 49304)
-- Name: property_speciality pk_specialty; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property_speciality
    ADD CONSTRAINT pk_specialty PRIMARY KEY (speciality_id);


--
-- TOC entry 2839 (class 2606 OID 49312)
-- Name: prop_speciality pk_specialty_prop; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_speciality
    ADD CONSTRAINT pk_specialty_prop PRIMARY KEY (prop_id, speciality_id);


--
-- TOC entry 2823 (class 2606 OID 40980)
-- Name: prop_availability_unit prop_avail_type_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_pkey PRIMARY KEY (prop_id, unit_id);


--
-- TOC entry 2811 (class 2606 OID 24589)
-- Name: prop_facilities prop_facilities_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facilities_pkey PRIMARY KEY (prop_id, facility_id);


--
-- TOC entry 2835 (class 2606 OID 49279)
-- Name: prop_payment_options prop_payment_pk; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_payment_options
    ADD CONSTRAINT prop_payment_pk PRIMARY KEY (prop_id, option_id);


--
-- TOC entry 2817 (class 2606 OID 24707)
-- Name: prop_tags prop_tags_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_tags
    ADD CONSTRAINT prop_tags_pkey PRIMARY KEY (prop_id, tag_id);


--
-- TOC entry 2803 (class 2606 OID 24639)
-- Name: property property_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property
    ADD CONSTRAINT property_pkey PRIMARY KEY (prop_id);


--
-- TOC entry 2819 (class 2606 OID 49185)
-- Name: sys_availability_unit sys_avail_type_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_availability_unit
    ADD CONSTRAINT sys_avail_type_pkey PRIMARY KEY (unit_id);


--
-- TOC entry 2813 (class 2606 OID 24657)
-- Name: sys_tags tags_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tag_id);


--
-- TOC entry 2821 (class 2606 OID 40972)
-- Name: sys_availability_unit uk_avail_unit_code; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_availability_unit
    ADD CONSTRAINT uk_avail_unit_code UNIQUE (code);


--
-- TOC entry 2809 (class 2606 OID 16519)
-- Name: sys_facilities uk_code_facility; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_facilities
    ADD CONSTRAINT uk_code_facility UNIQUE (code);


--
-- TOC entry 2801 (class 2606 OID 16517)
-- Name: organization uk_code_org; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.organization
    ADD CONSTRAINT uk_code_org UNIQUE (code);


--
-- TOC entry 2805 (class 2606 OID 16521)
-- Name: property uk_code_prop; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property
    ADD CONSTRAINT uk_code_prop UNIQUE (code);


--
-- TOC entry 2815 (class 2606 OID 49407)
-- Name: sys_tags uk_tag_code; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.sys_tags
    ADD CONSTRAINT uk_tag_code UNIQUE (code);


--
-- TOC entry 2825 (class 2606 OID 49197)
-- Name: week_definition week_definition_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.week_definition
    ADD CONSTRAINT week_definition_pkey PRIMARY KEY (week_def_id);


--
-- TOC entry 2859 (class 2606 OID 49220)
-- Name: contract_availability avail_week_def_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contract_availability
    ADD CONSTRAINT avail_week_def_fk FOREIGN KEY (week_def_id) REFERENCES hngout.week_definition(week_def_id);


--
-- TOC entry 2851 (class 2606 OID 49333)
-- Name: property fk_contact; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property
    ADD CONSTRAINT fk_contact FOREIGN KEY (contact_id) REFERENCES hngout.sys_contacts(contact_id);


--
-- TOC entry 2848 (class 2606 OID 49417)
-- Name: property fk_contract; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property
    ADD CONSTRAINT fk_contract FOREIGN KEY (current_cont_id) REFERENCES hngout.contracts(contract_id);


--
-- TOC entry 2865 (class 2606 OID 49373)
-- Name: location_state fk_country; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_state
    ADD CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES hngout.location_country(country_id);


--
-- TOC entry 2850 (class 2606 OID 49397)
-- Name: property fk_location; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property
    ADD CONSTRAINT fk_location FOREIGN KEY (based_location_id) REFERENCES hngout.location_city(id);


--
-- TOC entry 2849 (class 2606 OID 32768)
-- Name: property fk_org; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.property
    ADD CONSTRAINT fk_org FOREIGN KEY (org_id) REFERENCES hngout.organization(org_id);


--
-- TOC entry 2861 (class 2606 OID 49280)
-- Name: prop_payment_options fk_pay_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_payment_options
    ADD CONSTRAINT fk_pay_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2862 (class 2606 OID 49285)
-- Name: prop_payment_options fk_prop_pay; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_payment_options
    ADD CONSTRAINT fk_prop_pay FOREIGN KEY (option_id) REFERENCES hngout.payment_options(option_id);


--
-- TOC entry 2864 (class 2606 OID 49313)
-- Name: prop_speciality fk_prop_specialty; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_speciality
    ADD CONSTRAINT fk_prop_specialty FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2860 (class 2606 OID 49443)
-- Name: contract_availability fk_season_avail; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contract_availability
    ADD CONSTRAINT fk_season_avail FOREIGN KEY (contract_id, season_id) REFERENCES hngout.contract_season(contract_id, season_id) ON update CASCADE ON delete CASCADE;


--
-- TOC entry 2858 (class 2606 OID 49448)
-- Name: contract_season fk_season_contract; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.contract_season
    ADD CONSTRAINT fk_season_contract FOREIGN KEY (contract_id) REFERENCES hngout.contracts(contract_id) ON update CASCADE ON delete CASCADE;


--
-- TOC entry 2863 (class 2606 OID 49318)
-- Name: prop_speciality fk_specialty_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_speciality
    ADD CONSTRAINT fk_specialty_prop FOREIGN KEY (speciality_id) REFERENCES hngout.property_speciality(speciality_id);


--
-- TOC entry 2866 (class 2606 OID 49366)
-- Name: location_city fk_state_city; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_city
    ADD CONSTRAINT fk_state_city FOREIGN KEY (country_id, state_id) REFERENCES hngout.location_state(country_id, state_id);


--
-- TOC entry 2867 (class 2606 OID 49392)
-- Name: location_suburb fk_suburb; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.location_suburb
    ADD CONSTRAINT fk_suburb FOREIGN KEY (city_id) REFERENCES hngout.location_city(id);


--
-- TOC entry 2855 (class 2606 OID 24713)
-- Name: prop_tags pprop_tags_prop_fki; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_tags
    ADD CONSTRAINT pprop_tags_prop_fki FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2857 (class 2606 OID 40986)
-- Name: prop_availability_unit prop_avail_type_prop_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_prop_fk FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2856 (class 2606 OID 49186)
-- Name: prop_availability_unit prop_avail_type_st_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_st_fk FOREIGN KEY (unit_id) REFERENCES hngout.sys_availability_unit(unit_id);


--
-- TOC entry 2853 (class 2606 OID 24682)
-- Name: prop_facilities prop_facility_facility_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facility_facility_fk FOREIGN KEY (facility_id) REFERENCES hngout.sys_facilities(facility_id);


--
-- TOC entry 2852 (class 2606 OID 24677)
-- Name: prop_facilities prop_facility_prop_fki; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facility_prop_fki FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2854 (class 2606 OID 24708)
-- Name: prop_tags prop_tags_tag_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

alter table ONLY hngout.prop_tags
    ADD CONSTRAINT prop_tags_tag_fk FOREIGN KEY (tag_id) REFERENCES hngout.sys_tags(tag_id);


-- Completed on 2020-05-20 21:42:03

--
-- PostgreSQL database dump complete
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-05-20 21:42:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
select pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

create EXTENSION IF NOT EXISTS adminpack with SCHEMA pg_catalog;


--
-- TOC entry 2812 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16393)
-- Name: quque; Type: TABLE; Schema: public; Owner: postgres
--

create TABLE public.quque (
    qq date[],
    id integer
);


alter table public.quque OWNER TO postgres;

--
-- TOC entry 2806 (class 0 OID 16393)
-- Dependencies: 197
-- Data for Name: quque; Type: TABLE DATA; Schema: public; Owner: postgres
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


-- Completed on 2020-05-20 21:42:04

--
-- PostgreSQL database dump complete
--

-- Completed on 2020-05-20 21:42:04

--
-- PostgreSQL database cluster dump complete
--

