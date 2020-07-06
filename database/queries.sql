--
-- PostgreSQL database cluster dump
--

-- Started on 2020-07-06 23:12:32

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

-- Started on 2020-07-06 23:12:32

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

-- Completed on 2020-07-06 23:12:32

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-07-06 23:12:32

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
-- TOC entry 3147 (class 1262 OID 16399)
-- Name: hangouts; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE hangouts WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE hangouts OWNER TO postgres;

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
-- Name: hngout; Type: SCHEMA; Schema: -; Owner: tharinda
--

CREATE SCHEMA hngout;


ALTER SCHEMA hngout OWNER TO tharinda;

--
-- TOC entry 219 (class 1259 OID 49330)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE hngout.contact_id_seq OWNER TO tharinda;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 213 (class 1259 OID 41019)
-- Name: contract_availability; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.contract_availability (
    contract_id bigint NOT NULL,
    avail_unit_id integer NOT NULL,
    count smallint,
    week_def_id smallint NOT NULL,
    season_id smallint NOT NULL
);


ALTER TABLE hngout.contract_availability OWNER TO tharinda;

--
-- TOC entry 212 (class 1259 OID 41009)
-- Name: contract_season; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.contract_season (
    season_id smallint NOT NULL,
    contract_id bigint NOT NULL,
    name character varying(100),
    from_date date,
    to_date date
);


ALTER TABLE hngout.contract_season OWNER TO tharinda;

--
-- TOC entry 197 (class 1259 OID 16453)
-- Name: contracts; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.contracts (
    contract_id bigint NOT NULL,
    version smallint NOT NULL,
    prop_id bigint NOT NULL,
    timeslot smallint,
    version_txt character varying(50),
    name character varying(200),
    from_date date,
    to_date date,
    bookable_horizon smallint
);


ALTER TABLE hngout.contracts OWNER TO tharinda;

--
-- TOC entry 196 (class 1259 OID 16451)
-- Name: contract_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.contract_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hngout.contract_seq OWNER TO tharinda;

--
-- TOC entry 3148 (class 0 OID 0)
-- Dependencies: 196
-- Name: contract_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: tharinda
--

ALTER SEQUENCE hngout.contract_seq OWNED BY hngout.contracts.contract_id;


--
-- TOC entry 205 (class 1259 OID 24658)
-- Name: facilities_facility_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.facilities_facility_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE hngout.facilities_facility_id_seq OWNER TO tharinda;

--
-- TOC entry 222 (class 1259 OID 49361)
-- Name: location_city; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.location_city (
    country_id smallint NOT NULL,
    state_id smallint NOT NULL,
    city_id smallint NOT NULL,
    name character varying(50),
    id bigint NOT NULL
);


ALTER TABLE hngout.location_city OWNER TO tharinda;

--
-- TOC entry 220 (class 1259 OID 49353)
-- Name: location_country; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.location_country (
    country_id smallint NOT NULL,
    name character varying(50)
);


ALTER TABLE hngout.location_country OWNER TO tharinda;

--
-- TOC entry 223 (class 1259 OID 49378)
-- Name: location_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hngout.location_seq OWNER TO tharinda;

--
-- TOC entry 3149 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: tharinda
--

ALTER SEQUENCE hngout.location_seq OWNED BY hngout.location_city.id;


--
-- TOC entry 221 (class 1259 OID 49356)
-- Name: location_state; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.location_state (
    country_id smallint NOT NULL,
    state_id smallint NOT NULL,
    name character varying(50)
);


ALTER TABLE hngout.location_state OWNER TO tharinda;

--
-- TOC entry 224 (class 1259 OID 49387)
-- Name: location_suburb; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.location_suburb (
    suberb_id smallint NOT NULL,
    city_id bigint NOT NULL,
    name character varying(50)
);


ALTER TABLE hngout.location_suburb OWNER TO tharinda;

--
-- TOC entry 228 (class 1259 OID 49514)
-- Name: menu; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.menu (
    menu_id bigint NOT NULL,
    name character varying(100),
    description character varying(2000)
);


ALTER TABLE hngout.menu OWNER TO tharinda;

--
-- TOC entry 230 (class 1259 OID 49537)
-- Name: menu_category; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.menu_category (
    menu_id bigint NOT NULL,
    cat_id smallint NOT NULL,
    name character varying(100)
);


ALTER TABLE hngout.menu_category OWNER TO tharinda;

--
-- TOC entry 233 (class 1259 OID 49582)
-- Name: menu_choices; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.menu_choices (
    menu_id bigint NOT NULL,
    cat_id smallint NOT NULL,
    prop_ch_id integer NOT NULL
);


ALTER TABLE hngout.menu_choices OWNER TO tharinda;

--
-- TOC entry 236 (class 1259 OID 57654)
-- Name: menu_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.menu_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hngout.menu_seq OWNER TO tharinda;

--
-- TOC entry 198 (class 1259 OID 16466)
-- Name: organization_org_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.organization_org_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE hngout.organization_org_id_seq OWNER TO tharinda;

--
-- TOC entry 199 (class 1259 OID 16468)
-- Name: organization; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.organization (
    org_id bigint DEFAULT nextval('hngout.organization_org_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100)
);


ALTER TABLE hngout.organization OWNER TO tharinda;

--
-- TOC entry 215 (class 1259 OID 49258)
-- Name: payment_options; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.payment_options (
    option_id smallint NOT NULL,
    name character varying(20)
);


ALTER TABLE hngout.payment_options OWNER TO tharinda;

--
-- TOC entry 239 (class 1259 OID 74029)
-- Name: promotion_statistics; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.promotion_statistics (
    prop_id bigint NOT NULL,
    promo_id smallint NOT NULL,
    views integer,
    clicks integer,
    authentic_views integer,
    authentic_clicks integer,
    converge integer
);


ALTER TABLE hngout.promotion_statistics OWNER TO tharinda;

--
-- TOC entry 234 (class 1259 OID 49597)
-- Name: promotion_tiers; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.promotion_tiers (
    tier_id smallint NOT NULL,
    name character varying(100),
    description character varying(2000),
    landing_page smallint,
    result_page smallint,
    search_boost smallint
);


ALTER TABLE hngout.promotion_tiers OWNER TO tharinda;

--
-- TOC entry 237 (class 1259 OID 57674)
-- Name: promotion_types; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.promotion_types (
    type_id smallint NOT NULL,
    name character varying(100),
    conditions character varying(2000)
);


ALTER TABLE hngout.promotion_types OWNER TO tharinda;

--
-- TOC entry 235 (class 1259 OID 49605)
-- Name: promotions; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.promotions (
    prop_id bigint NOT NULL,
    promo_id smallint NOT NULL,
    tier_id smallint,
    promo_type smallint,
    start_date date,
    end_date date,
    name character varying(200),
    description character varying(2000),
    conditions character varying(2000),
    live boolean,
    maxed_out_landing boolean,
    maxed_out_search boolean
);


ALTER TABLE hngout.promotions OWNER TO tharinda;

--
-- TOC entry 210 (class 1259 OID 40973)
-- Name: prop_availability_unit; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_availability_unit (
    prop_id bigint NOT NULL,
    unit_id integer NOT NULL,
    name character varying(100),
    description text,
    capacity smallint,
    sys_unit_id integer
);


ALTER TABLE hngout.prop_availability_unit OWNER TO tharinda;

--
-- TOC entry 232 (class 1259 OID 49555)
-- Name: prop_choices; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_choices (
    prop_ch_id integer NOT NULL,
    prop_id bigint,
    choice_id bigint,
    name character varying(100),
    description character varying(2000)
);


ALTER TABLE hngout.prop_choices OWNER TO tharinda;

--
-- TOC entry 238 (class 1259 OID 57711)
-- Name: prop_exploded_avail_data; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_exploded_avail_data (
    prop_id bigint NOT NULL,
    avail_unit_id integer NOT NULL,
    date date NOT NULL,
    time_slot time without time zone NOT NULL,
    contract_avail_count smallint,
    open smallint,
    close smallint,
    bookable smallint,
    booked smallint,
    reservation_id bigint,
    hold smallint,
    contract_id bigint
);


ALTER TABLE hngout.prop_exploded_avail_data OWNER TO tharinda;

--
-- TOC entry 203 (class 1259 OID 24582)
-- Name: prop_facilities; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_facilities (
    prop_id bigint NOT NULL,
    facility_id integer NOT NULL,
    name character varying(100),
    description text
);


ALTER TABLE hngout.prop_facilities OWNER TO tharinda;

--
-- TOC entry 229 (class 1259 OID 49522)
-- Name: prop_menu; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_menu (
    prop_id bigint NOT NULL,
    menu_id bigint NOT NULL
);


ALTER TABLE hngout.prop_menu OWNER TO tharinda;

--
-- TOC entry 225 (class 1259 OID 49481)
-- Name: prop_operation_hours; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_operation_hours (
    prop_id bigint NOT NULL,
    op_id smallint NOT NULL,
    time_start time without time zone,
    time_end time without time zone,
    name character varying(100)
);


ALTER TABLE hngout.prop_operation_hours OWNER TO tharinda;

--
-- TOC entry 216 (class 1259 OID 49275)
-- Name: prop_payment_options; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_payment_options (
    prop_id bigint NOT NULL,
    option_id smallint NOT NULL
);


ALTER TABLE hngout.prop_payment_options OWNER TO tharinda;

--
-- TOC entry 227 (class 1259 OID 49499)
-- Name: prop_reservations; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_reservations (
    prop_id bigint NOT NULL,
    res_id bigint NOT NULL,
    booked_contract_id integer
);


ALTER TABLE hngout.prop_reservations OWNER TO tharinda;

--
-- TOC entry 218 (class 1259 OID 49308)
-- Name: prop_speciality; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_speciality (
    prop_id bigint NOT NULL,
    speciality_id smallint NOT NULL
);


ALTER TABLE hngout.prop_speciality OWNER TO tharinda;

--
-- TOC entry 207 (class 1259 OID 24700)
-- Name: prop_tags; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.prop_tags (
    prop_id bigint NOT NULL,
    tag_id integer NOT NULL,
    name character varying(100),
    description text
);


ALTER TABLE hngout.prop_tags OWNER TO tharinda;

--
-- TOC entry 201 (class 1259 OID 16477)
-- Name: property; Type: TABLE; Schema: hngout; Owner: tharinda
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
    based_location_id bigint,
    timeslot smallint
);


ALTER TABLE hngout.property OWNER TO tharinda;

--
-- TOC entry 200 (class 1259 OID 16475)
-- Name: property_prop_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.property_prop_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hngout.property_prop_id_seq OWNER TO tharinda;

--
-- TOC entry 3150 (class 0 OID 0)
-- Dependencies: 200
-- Name: property_prop_id_seq; Type: SEQUENCE OWNED BY; Schema: hngout; Owner: tharinda
--

ALTER SEQUENCE hngout.property_prop_id_seq OWNED BY hngout.property.prop_id;


--
-- TOC entry 217 (class 1259 OID 49290)
-- Name: property_speciality; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.property_speciality (
    speciality_id smallint NOT NULL,
    name character varying(20),
    description character varying(1000)
);


ALTER TABLE hngout.property_speciality OWNER TO tharinda;

--
-- TOC entry 3151 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE property_speciality; Type: COMMENT; Schema: hngout; Owner: tharinda
--

COMMENT ON TABLE hngout.property_speciality IS 'Hangout = Cuisines, Salon = Bridal,Heir etc';


--
-- TOC entry 226 (class 1259 OID 49491)
-- Name: reservations; Type: TABLE; Schema: hngout; Owner: tharinda
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


ALTER TABLE hngout.reservations OWNER TO tharinda;

--
-- TOC entry 208 (class 1259 OID 40963)
-- Name: sys_avail_unit_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.sys_avail_unit_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE hngout.sys_avail_unit_seq OWNER TO tharinda;

--
-- TOC entry 209 (class 1259 OID 40965)
-- Name: sys_availability_unit; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.sys_availability_unit (
    unit_id integer DEFAULT nextval('hngout.sys_avail_unit_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100),
    min_capacity smallint,
    max_capacity smallint
);


ALTER TABLE hngout.sys_availability_unit OWNER TO tharinda;

--
-- TOC entry 231 (class 1259 OID 49547)
-- Name: sys_choices; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.sys_choices (
    choice_id bigint NOT NULL,
    name character varying(100),
    description text
);


ALTER TABLE hngout.sys_choices OWNER TO tharinda;

--
-- TOC entry 214 (class 1259 OID 49252)
-- Name: sys_contacts; Type: TABLE; Schema: hngout; Owner: tharinda
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


ALTER TABLE hngout.sys_contacts OWNER TO tharinda;

--
-- TOC entry 202 (class 1259 OID 16506)
-- Name: sys_facilities; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.sys_facilities (
    facility_id integer DEFAULT nextval('hngout.facilities_facility_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    description text
);


ALTER TABLE hngout.sys_facilities OWNER TO tharinda;

--
-- TOC entry 206 (class 1259 OID 24660)
-- Name: tags_tag_id_seq; Type: SEQUENCE; Schema: hngout; Owner: tharinda
--

CREATE SEQUENCE hngout.tags_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE hngout.tags_tag_id_seq OWNER TO tharinda;

--
-- TOC entry 204 (class 1259 OID 24653)
-- Name: sys_tags; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.sys_tags (
    tag_id integer DEFAULT nextval('hngout.tags_tag_id_seq'::regclass) NOT NULL,
    code character varying(10),
    description text,
    name character varying(100)
);


ALTER TABLE hngout.sys_tags OWNER TO tharinda;

--
-- TOC entry 211 (class 1259 OID 40991)
-- Name: week_definition; Type: TABLE; Schema: hngout; Owner: tharinda
--

CREATE TABLE hngout.week_definition (
    week_def_id smallint NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(100),
    week character varying(7)
);


ALTER TABLE hngout.week_definition OWNER TO tharinda;

--
-- TOC entry 2853 (class 2604 OID 24590)
-- Name: contracts contract_id; Type: DEFAULT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contracts ALTER COLUMN contract_id SET DEFAULT nextval('hngout.contract_seq'::regclass);


--
-- TOC entry 2860 (class 2604 OID 49380)
-- Name: location_city id; Type: DEFAULT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_city ALTER COLUMN id SET DEFAULT nextval('hngout.location_seq'::regclass);


--
-- TOC entry 2855 (class 2604 OID 24637)
-- Name: property prop_id; Type: DEFAULT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property ALTER COLUMN prop_id SET DEFAULT nextval('hngout.property_prop_id_seq'::regclass);


--
-- TOC entry 3115 (class 0 OID 41019)
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
111	1	12	1	3
111	4	1	1	3
111	2	12	1	3
111	4	1	1	1
111	2	10	1	1
111	1	10	1	1
111	1	20	3	2
111	1	10	2	2
111	4	2	3	2
111	4	1	2	2
111	2	20	3	2
111	2	10	2	2
112	1	12	1	3
112	4	1	1	3
112	2	12	1	3
112	4	1	1	1
112	2	10	1	1
112	1	10	1	1
112	1	20	3	2
112	1	10	2	2
112	4	2	3	2
112	4	1	2	2
112	2	20	3	2
112	2	10	2	2
113	1	12	1	3
113	4	1	1	3
113	2	12	1	3
113	4	1	1	1
113	2	10	1	1
113	1	10	1	1
113	1	20	3	2
113	1	10	2	2
113	4	2	3	2
113	4	1	2	2
113	2	20	3	2
113	2	10	2	2
114	1	12	1	3
114	4	1	1	3
114	2	12	1	3
114	4	1	1	1
114	2	10	1	1
114	1	10	1	1
114	1	20	3	2
114	1	10	2	2
114	4	2	3	2
114	4	1	2	2
114	2	20	3	2
114	2	10	2	2
115	1	12	1	3
115	4	1	1	3
115	2	12	1	3
115	4	1	1	1
115	2	10	1	1
115	1	10	1	1
115	1	20	3	2
115	1	10	2	2
115	4	2	3	2
115	4	1	2	2
115	2	20	3	2
115	2	10	2	2
116	1	12	1	3
116	4	1	1	3
116	2	12	1	3
116	4	1	1	1
116	2	10	1	1
116	1	10	1	1
116	1	20	3	2
116	1	10	2	2
116	4	2	3	2
116	4	1	2	2
116	2	20	3	2
116	2	10	2	2
117	1	12	1	3
117	4	1	1	3
117	2	12	1	3
117	4	1	1	1
117	2	10	1	1
117	1	10	1	1
117	1	20	3	2
117	1	10	2	2
117	4	2	3	2
117	4	1	2	2
117	2	20	3	2
117	2	10	2	2
118	1	12	1	3
118	4	1	1	3
118	2	12	1	3
118	4	1	1	1
118	2	10	1	1
118	1	10	1	1
118	1	20	3	2
118	1	10	2	2
118	4	2	3	2
118	4	1	2	2
118	2	20	3	2
118	2	10	2	2
119	1	12	1	3
119	4	1	1	3
119	2	12	1	3
119	4	1	1	1
119	2	10	1	1
119	1	10	1	1
119	1	20	3	2
119	1	10	2	2
119	4	2	3	2
119	4	1	2	2
119	2	20	3	2
119	2	10	2	2
120	1	12	1	3
120	4	1	1	3
120	2	12	1	3
120	4	1	1	1
120	2	10	1	1
120	1	10	1	1
120	1	20	3	2
120	1	10	2	2
120	4	2	3	2
120	4	1	2	2
120	2	20	3	2
120	2	10	2	2
121	1	12	1	3
121	4	1	1	3
121	2	12	1	3
121	4	1	1	1
121	2	10	1	1
121	1	10	1	1
121	1	20	3	2
121	1	10	2	2
121	4	2	3	2
121	4	1	2	2
121	2	20	3	2
121	2	10	2	2
122	1	12	1	3
122	4	1	1	3
122	2	12	1	3
122	4	1	1	1
122	2	10	1	1
122	1	10	1	1
122	1	20	3	2
122	1	10	2	2
122	4	2	3	2
122	4	1	2	2
122	2	20	3	2
122	2	10	2	2
123	1	12	1	3
123	4	1	1	3
123	2	12	1	3
123	4	1	1	1
123	2	10	1	1
123	1	10	1	1
123	1	20	3	2
123	1	10	2	2
123	4	2	3	2
123	4	1	2	2
123	2	20	3	2
123	2	10	2	2
124	1	12	1	3
124	4	1	1	3
124	2	12	1	3
124	4	1	1	1
124	2	10	1	1
124	1	10	1	1
124	1	20	3	2
124	1	10	2	2
124	4	2	3	2
124	4	1	2	2
124	2	20	3	2
124	2	10	2	2
125	1	12	1	3
125	4	1	1	3
125	2	12	1	3
125	4	1	1	1
125	2	10	1	1
125	1	10	1	1
125	1	20	3	2
125	1	10	2	2
125	4	2	3	2
125	4	1	2	2
125	2	20	3	2
125	2	10	2	2
126	1	12	1	3
126	4	1	1	3
126	2	12	1	3
126	4	1	1	1
126	2	10	1	1
126	1	10	1	1
126	1	20	3	2
126	1	10	2	2
126	4	2	3	2
126	4	1	2	2
126	2	20	3	2
126	2	10	2	2
127	4	2	3	1
127	4	1	2	1
127	2	20	3	1
127	2	10	2	1
127	1	20	3	1
127	1	10	2	1
127	4	12	1	2
127	2	12	1	2
127	1	12	1	2
128	4	2	3	1
128	4	1	2	1
128	2	20	3	1
128	2	10	2	1
128	1	20	3	1
128	1	10	2	1
128	4	12	1	2
128	2	12	1	2
128	1	12	1	2
129	4	2	3	1
129	4	1	2	1
129	2	20	3	1
129	2	10	2	1
129	1	20	3	1
129	1	10	2	1
129	4	12	1	2
129	2	12	1	2
129	1	12	1	2
130	4	2	3	1
130	4	1	2	1
130	2	20	3	1
130	2	10	2	1
130	1	20	3	1
130	1	10	2	1
130	4	12	1	2
130	2	12	1	2
130	1	12	1	2
131	4	2	3	1
131	4	1	2	1
131	2	20	3	1
131	2	10	2	1
131	1	20	3	1
131	1	10	2	1
131	4	12	1	2
131	2	12	1	2
131	1	12	1	2
132	4	2	3	1
132	4	1	2	1
132	2	20	3	1
132	2	10	2	1
132	1	20	3	1
132	1	10	2	1
132	4	12	1	2
132	2	12	1	2
132	1	12	1	2
133	4	2	3	1
133	4	1	2	1
133	2	20	3	1
133	2	10	2	1
133	1	20	3	1
133	1	10	2	1
133	4	12	1	2
133	2	12	1	2
133	1	12	1	2
134	4	2	3	1
134	4	1	2	1
134	2	20	3	1
134	2	10	2	1
134	1	20	3	1
134	1	10	2	1
134	4	12	1	2
134	2	12	1	2
134	1	12	1	2
135	4	2	3	1
135	4	1	2	1
135	2	20	3	1
135	2	10	2	1
135	1	20	3	1
135	1	10	2	1
135	4	12	1	2
135	2	12	1	2
135	1	12	1	2
136	4	2	3	1
136	4	1	2	1
136	2	20	3	1
136	2	10	2	1
136	1	20	3	1
136	1	10	2	1
136	4	12	1	2
136	2	12	1	2
136	1	12	1	2
137	4	2	3	1
137	4	1	2	1
137	2	20	3	1
137	2	10	2	1
137	1	20	3	1
137	1	10	2	1
137	4	12	1	2
137	2	12	1	2
137	1	12	1	2
138	4	2	3	1
138	4	1	2	1
138	2	20	3	1
138	2	10	2	1
138	1	20	3	1
138	1	10	2	1
138	4	12	1	2
138	2	12	1	2
138	1	12	1	2
139	4	2	3	1
139	4	1	2	1
139	2	20	3	1
139	2	10	2	1
139	1	20	3	1
139	1	10	2	1
139	4	12	1	2
139	2	12	1	2
139	1	12	1	2
140	4	2	3	1
140	4	1	2	1
140	2	20	3	1
140	2	10	2	1
140	1	20	3	1
140	1	10	2	1
140	4	12	1	2
140	2	12	1	2
140	1	12	1	2
141	4	2	3	1
141	4	1	2	1
141	2	20	3	1
141	2	10	2	1
141	1	20	3	1
141	1	10	2	1
141	4	12	1	2
141	2	12	1	2
141	1	12	1	2
142	4	2	3	1
142	4	1	2	1
142	2	20	3	1
142	2	10	2	1
142	1	20	3	1
142	1	10	2	1
142	4	12	1	2
142	2	12	1	2
142	1	12	1	2
143	4	2	3	1
143	4	1	2	1
143	2	20	3	1
143	2	10	2	1
143	1	20	3	1
143	1	10	2	1
143	4	12	1	2
143	2	12	1	2
143	1	12	1	2
144	4	2	3	1
144	4	1	2	1
144	2	20	3	1
144	2	10	2	1
144	1	20	3	1
144	1	10	2	1
144	4	12	1	2
144	2	12	1	2
144	1	12	1	2
145	4	2	3	1
145	4	1	2	1
145	2	20	3	1
145	2	10	2	1
145	1	20	3	1
145	1	10	2	1
145	4	12	1	2
145	2	12	1	2
145	1	12	1	2
146	4	2	3	1
146	4	1	2	1
146	2	20	3	1
146	2	10	2	1
146	1	20	3	1
146	1	10	2	1
146	4	12	1	2
146	2	12	1	2
146	1	12	1	2
147	4	2	3	1
147	4	1	2	1
147	2	20	3	1
147	2	10	2	1
147	1	20	3	1
147	1	10	2	1
147	4	12	1	2
147	2	12	1	2
147	1	12	1	2
149	4	2	3	1
149	4	1	2	1
149	2	20	3	1
149	2	10	2	1
149	1	20	3	1
149	1	10	2	1
149	4	12	1	2
149	2	12	1	2
149	1	12	1	2
150	4	2	3	1
150	4	1	2	1
150	2	20	3	1
150	2	10	2	1
150	1	20	3	1
150	1	10	2	1
150	4	12	1	2
150	2	12	1	2
150	1	12	1	2
151	4	2	3	1
151	4	1	2	1
151	2	20	3	1
151	2	10	2	1
151	1	20	3	1
151	1	10	2	1
151	4	12	1	2
151	2	12	1	2
151	1	12	1	2
152	4	2	3	1
152	4	1	2	1
152	2	20	3	1
152	2	10	2	1
152	1	20	3	1
152	1	10	2	1
152	4	12	1	2
152	2	12	1	2
152	1	12	1	2
153	4	2	3	1
153	4	1	2	1
153	2	20	3	1
153	2	10	2	1
153	1	20	3	1
153	1	10	2	1
153	4	12	1	2
153	2	12	1	2
153	1	12	1	2
154	4	2	3	1
154	4	1	2	1
154	2	20	3	1
154	2	10	2	1
154	1	20	3	1
154	1	10	2	1
154	4	12	1	2
154	2	12	1	2
154	1	12	1	2
155	4	2	3	1
155	4	1	2	1
155	2	20	3	1
155	2	10	2	1
155	1	20	3	1
155	1	10	2	1
155	4	12	1	2
155	2	12	1	2
155	1	12	1	2
156	4	2	3	1
156	4	1	2	1
156	2	20	3	1
156	2	10	2	1
156	1	20	3	1
156	1	10	2	1
156	4	12	1	2
156	2	12	1	2
156	1	12	1	2
157	4	2	3	1
157	4	1	2	1
157	2	20	3	1
157	2	10	2	1
157	1	20	3	1
157	1	10	2	1
157	4	12	1	2
157	2	12	1	2
157	1	12	1	2
158	4	2	3	1
158	4	1	2	1
158	2	20	3	1
158	2	10	2	1
158	1	20	3	1
158	1	10	2	1
158	4	12	1	2
158	2	12	1	2
158	1	12	1	2
159	4	2	3	1
159	4	1	2	1
159	2	20	3	1
159	2	10	2	1
159	1	20	3	1
159	1	10	2	1
159	4	12	1	2
159	2	12	1	2
159	1	12	1	2
160	4	2	3	1
160	4	1	2	1
160	2	20	3	1
160	2	10	2	1
160	1	20	3	1
160	1	10	2	1
160	4	12	1	2
160	2	12	1	2
160	1	12	1	2
161	4	2	3	1
161	4	1	2	1
161	2	20	3	1
161	2	10	2	1
161	1	20	3	1
161	1	10	2	1
161	4	12	1	2
161	2	12	1	2
161	1	12	1	2
162	4	2	3	1
162	4	1	2	1
162	2	20	3	1
162	2	10	2	1
162	1	20	3	1
162	1	10	2	1
162	4	12	1	2
162	2	12	1	2
162	1	12	1	2
163	4	2	3	1
163	4	1	2	1
163	2	20	3	1
163	2	10	2	1
163	1	20	3	1
163	1	10	2	1
163	4	12	1	2
163	2	12	1	2
163	1	12	1	2
164	4	2	3	1
164	4	1	2	1
164	2	20	3	1
164	2	10	2	1
164	1	20	3	1
164	1	10	2	1
164	4	12	1	2
164	2	12	1	2
164	1	12	1	2
165	4	2	3	1
165	4	1	2	1
165	2	20	3	1
165	2	10	2	1
165	1	20	3	1
165	1	10	2	1
165	4	12	1	2
165	2	12	1	2
165	1	12	1	2
166	4	2	3	1
166	4	1	2	1
166	2	20	3	1
166	2	10	2	1
166	1	20	3	1
166	1	10	2	1
166	4	12	1	2
166	2	12	1	2
166	1	12	1	2
167	4	2	3	1
167	4	1	2	1
167	2	20	3	1
167	2	10	2	1
167	1	20	3	1
167	1	10	2	1
167	4	12	1	2
167	2	12	1	2
167	1	12	1	2
168	4	2	3	1
168	4	1	2	1
168	2	20	3	1
168	2	10	2	1
168	1	20	3	1
168	1	10	2	1
168	4	12	1	2
168	2	12	1	2
168	1	12	1	2
169	4	2	3	1
169	4	1	2	1
169	2	20	3	1
169	2	10	2	1
169	1	20	3	1
169	1	10	2	1
169	4	12	1	2
169	2	12	1	2
169	1	12	1	2
170	4	2	3	1
170	4	1	2	1
170	2	20	3	1
170	2	10	2	1
170	1	20	3	1
170	1	10	2	1
170	4	12	1	2
170	2	12	1	2
170	1	12	1	2
\.


--
-- TOC entry 3114 (class 0 OID 41009)
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
3	111	May to Dec	2020-06-01	2020-12-31
1	111	Jan to March	2020-01-01	2020-03-31
2	111	April to May	2020-04-01	2020-05-31
3	112	May to Dec	2020-06-01	2020-12-31
1	112	Jan to March	2020-01-01	2020-03-31
2	112	April to May	2020-04-01	2020-05-31
3	113	May to Dec	2020-06-01	2020-12-31
1	113	Jan to March	2020-01-01	2020-03-31
2	113	April to May	2020-04-01	2020-05-31
3	114	May to Dec	2020-06-01	2020-12-31
1	114	Jan to March	2020-01-01	2020-03-31
2	114	April to May	2020-04-01	2020-05-31
3	115	May to Dec	2020-06-01	2020-12-31
1	115	Jan to March	2020-01-01	2020-03-31
2	115	April to May	2020-04-01	2020-05-31
3	116	May to Dec	2020-06-01	2020-12-31
1	116	Jan to March	2020-01-01	2020-03-31
2	116	April to May	2020-04-01	2020-05-31
3	117	May to Dec	2020-06-01	2020-12-31
1	117	Jan to March	2020-01-01	2020-03-31
2	117	April to May	2020-04-01	2020-05-31
3	118	May to Dec	2020-06-01	2020-12-31
1	118	Jan to March	2020-01-01	2020-03-31
2	118	April to May	2020-04-01	2020-05-31
3	119	May to Dec	2020-06-01	2020-12-31
1	119	Jan to March	2020-01-01	2020-03-31
2	119	April to May	2020-04-01	2020-05-31
3	120	May to Dec	2020-06-01	2020-12-31
1	120	Jan to March	2020-01-01	2020-03-31
2	120	April to May	2020-04-01	2020-05-31
3	121	May to Dec	2020-06-01	2020-12-31
1	121	Jan to March	2020-01-01	2020-03-31
2	121	April to May	2020-04-01	2020-05-31
3	122	May to Dec	2020-06-01	2020-12-31
1	122	Jan to March	2020-01-01	2020-03-31
2	122	April to May	2020-04-01	2020-05-31
3	123	May to Dec	2020-06-01	2020-12-31
1	123	Jan to March	2020-01-01	2020-03-31
2	123	April to May	2020-04-01	2020-05-31
3	124	May to Dec	2020-06-01	2020-12-31
1	124	Jan to March	2020-01-01	2020-03-31
2	124	April to May	2020-04-01	2020-05-31
3	125	May to Dec	2020-06-01	2020-12-31
1	125	Jan to March	2020-01-01	2020-03-31
2	125	April to May	2020-04-01	2020-05-31
3	126	May to Dec	2020-06-01	2020-12-31
1	126	Jan to March	2020-01-01	2020-03-31
2	126	April to May	2020-04-01	2020-05-31
1	127	June-July	2020-06-01	2020-07-31
2	127	Aug	2020-08-01	2020-07-31
1	128	June-July	2020-06-01	2020-07-31
2	128	Aug	2020-08-01	2020-07-31
1	129	June-July	2020-06-01	2020-07-31
2	129	Aug	2020-08-01	2020-07-31
1	130	June-July	2020-06-01	2020-07-31
2	130	Aug	2020-08-01	2020-07-31
1	131	June-July	2020-06-01	2020-07-31
2	131	Aug	2020-08-01	2020-07-31
1	132	June-July	2020-06-01	2020-07-31
2	132	Aug	2020-08-01	2020-07-31
1	133	June-July	2020-06-01	2020-07-31
2	133	Aug	2020-08-01	2020-07-31
1	134	June-July	2020-06-01	2020-07-31
2	134	Aug	2020-08-01	2020-07-31
1	135	June-July	2020-06-01	2020-07-31
2	135	Aug	2020-08-01	2020-07-31
1	136	June-July	2020-06-01	2020-07-31
2	136	Aug	2020-08-01	2020-07-31
1	137	June-July	2020-06-01	2020-07-31
2	137	Aug	2020-08-01	2020-07-31
1	138	June-July	2020-06-01	2020-07-31
2	138	Aug	2020-08-01	2020-07-31
1	139	June-July	2020-06-01	2020-07-31
2	139	Aug	2020-08-01	2020-07-31
1	140	June-July	2020-06-01	2020-07-31
2	140	Aug	2020-08-01	2020-07-31
1	141	June-July	2020-06-01	2020-07-31
2	141	Aug	2020-08-01	2020-07-31
1	142	June-July	2020-06-01	2020-07-31
2	142	Aug	2020-08-01	2020-07-31
1	143	June-July	2020-06-01	2020-07-31
2	143	Aug	2020-08-01	2020-07-31
1	144	June-July	2020-06-01	2020-07-31
2	144	Aug	2020-08-01	2020-07-31
1	145	June-July	2020-06-01	2020-07-31
2	145	Aug	2020-08-01	2020-07-31
1	146	June-July	2020-06-01	2020-07-31
2	146	Aug	2020-08-01	2020-07-31
1	147	June-July	2020-06-01	2020-07-31
2	147	Aug	2020-08-01	2020-07-31
1	149	June-July	2020-06-01	2020-07-31
2	149	Aug	2020-08-01	2020-07-31
1	150	June-July	2020-06-01	2020-07-31
2	150	Aug	2020-08-01	2020-07-31
1	151	June-July	2020-06-01	2020-07-31
2	151	Aug	2020-08-01	2020-07-31
1	152	June-July	2020-06-01	2020-07-31
2	152	Aug	2020-08-01	2020-07-31
1	153	June-July	2020-06-01	2020-07-31
2	153	Aug	2020-08-01	2020-07-31
1	154	June-July	2020-06-01	2020-07-31
2	154	Aug	2020-08-01	2020-07-31
1	155	June-July	2020-06-01	2020-07-31
2	155	Aug	2020-08-01	2020-07-31
1	156	June-July	2020-06-01	2020-07-31
2	156	Aug	2020-08-01	2020-07-31
1	157	June-July	2020-06-01	2020-07-31
2	157	Aug	2020-08-01	2020-07-31
1	158	June-July	2020-06-01	2020-07-31
2	158	Aug	2020-08-01	2020-07-31
1	159	June-July	2020-06-01	2020-07-31
2	159	Aug	2020-08-01	2020-07-31
1	160	June-July	2020-06-01	2020-07-31
2	160	Aug	2020-08-01	2020-07-31
1	161	June-July	2020-06-01	2020-07-31
2	161	Aug	2020-08-01	2020-07-31
1	162	June-July	2020-06-01	2020-07-31
2	162	Aug	2020-08-01	2020-07-31
1	163	June-July	2020-06-01	2020-07-31
2	163	Aug	2020-08-01	2020-07-31
1	164	June-July	2020-06-01	2020-07-31
2	164	Aug	2020-08-01	2020-07-31
1	165	June-July	2020-06-01	2020-07-31
2	165	Aug	2020-08-01	2020-07-31
1	166	June-July	2020-06-01	2020-07-31
2	166	Aug	2020-08-01	2020-07-31
1	167	June-July	2020-06-01	2020-07-31
2	167	Aug	2020-08-01	2020-07-31
1	168	June-July	2020-06-01	2020-07-31
2	168	Aug	2020-08-01	2020-07-31
1	169	June-July	2020-06-01	2020-07-31
2	169	Aug	2020-08-01	2020-07-31
1	170	June-July	2020-06-01	2020-07-31
2	170	Aug	2020-08-01	2020-07-31
\.


--
-- TOC entry 3099 (class 0 OID 16453)
-- Dependencies: 197
-- Data for Name: contracts; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.contracts (contract_id, version, prop_id, timeslot, version_txt, name, from_date, to_date, bookable_horizon) FROM stdin;
123	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
12	1	1	30	V1.0	\N	\N	\N	\N
13	1	1	30	V1.0	\N	\N	\N	\N
26	1	1	60	dsds	\N	\N	\N	\N
29	1	1	30	V1.0	\N	\N	\N	\N
30	1	1	30	V1.0	\N	\N	\N	\N
31	1	1	30	V1.0	\N	\N	\N	\N
52	1	1	30	V1111.0	\N	\N	\N	\N
70	1	1	30	V1111.0	\N	\N	\N	\N
71	1	1	60	V1111.0	\N	\N	\N	\N
72	1	1	60	V1111.0	\N	\N	\N	\N
73	1	1	30	V1111.0	\N	\N	\N	\N
74	1	1	30	V1.0	\N	\N	\N	\N
75	1	1	30	V1.0	2020 Full year	2020-01-01	2020-12-31	\N
76	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
124	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
125	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
78	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
77	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
126	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	10
81	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
80	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31	\N
79	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31	\N
82	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31	\N
83	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31	\N
84	1	1	60	V2.0	2020 Full yeasdaddadadsadasdr	2020-01-01	2020-12-31	\N
85	1	1	60	V2.0	2020 Full yearqq	2020-01-01	2020-12-31	\N
86	1	1	60	V2.0	2020 Full yearqq	2020-01-01	2020-12-31	\N
1	1	1	30	V1.1	\N	\N	\N	10
88	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
89	1	1	60	V2.0	2020 Full yearqqqqqqqq	2020-01-01	2020-12-31	\N
91	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
92	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
93	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
94	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
95	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
96	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
97	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
98	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
99	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
100	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
101	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
102	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
103	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
104	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
105	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
106	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
107	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
108	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
109	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
110	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
111	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
112	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
113	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
114	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
115	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
116	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
117	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
118	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
119	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
120	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
121	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
122	1	1	60	V2.0	2020 Full year	2020-01-01	2020-12-31	\N
127	22	1	60	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
128	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
129	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
131	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
132	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
133	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
134	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
135	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
136	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
137	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
138	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
130	1	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
139	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
140	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
141	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
142	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
143	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
144	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
145	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
146	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
147	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
149	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
150	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
151	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
152	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
153	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
154	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
155	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
156	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
157	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
158	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
159	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
160	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
161	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
162	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
163	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
164	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
165	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
166	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
167	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
168	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
169	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
170	22	1	\N	V22.0	Avail Test Contract	2020-06-01	2020-08-31	10
\.


--
-- TOC entry 3124 (class 0 OID 49361)
-- Dependencies: 222
-- Data for Name: location_city; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_city (country_id, state_id, city_id, name, id) FROM stdin;
1	1	1	Colombo 01	1
1	1	2	Colombo 02	2
1	1	3	Nugegoda	3
\.


--
-- TOC entry 3122 (class 0 OID 49353)
-- Dependencies: 220
-- Data for Name: location_country; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_country (country_id, name) FROM stdin;
1	Sri Lanka
\.


--
-- TOC entry 3123 (class 0 OID 49356)
-- Dependencies: 221
-- Data for Name: location_state; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_state (country_id, state_id, name) FROM stdin;
1	1	Colombo
1	2	Kandy
1	3	Galle
\.


--
-- TOC entry 3126 (class 0 OID 49387)
-- Dependencies: 224
-- Data for Name: location_suburb; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.location_suburb (suberb_id, city_id, name) FROM stdin;
\.


--
-- TOC entry 3130 (class 0 OID 49514)
-- Dependencies: 228
-- Data for Name: menu; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.menu (menu_id, name, description) FROM stdin;
\.


--
-- TOC entry 3132 (class 0 OID 49537)
-- Dependencies: 230
-- Data for Name: menu_category; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.menu_category (menu_id, cat_id, name) FROM stdin;
\.


--
-- TOC entry 3135 (class 0 OID 49582)
-- Dependencies: 233
-- Data for Name: menu_choices; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.menu_choices (menu_id, cat_id, prop_ch_id) FROM stdin;
\.


--
-- TOC entry 3101 (class 0 OID 16468)
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
-- TOC entry 3117 (class 0 OID 49258)
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
-- TOC entry 3141 (class 0 OID 74029)
-- Dependencies: 239
-- Data for Name: promotion_statistics; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.promotion_statistics (prop_id, promo_id, views, clicks, authentic_views, authentic_clicks, converge) FROM stdin;
1	1	10	5	8	4	1
\.


--
-- TOC entry 3136 (class 0 OID 49597)
-- Dependencies: 234
-- Data for Name: promotion_tiers; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.promotion_tiers (tier_id, name, description, landing_page, result_page, search_boost) FROM stdin;
1	Free Tier	Free Tier where properties can ...	2	2	1
\.


--
-- TOC entry 3139 (class 0 OID 57674)
-- Dependencies: 237
-- Data for Name: promotion_types; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.promotion_types (type_id, name, conditions) FROM stdin;
1	Basic Discount	Just a Discount
2	Last Minute Deal	Last  Minute Deal 
\.


--
-- TOC entry 3137 (class 0 OID 49605)
-- Dependencies: 235
-- Data for Name: promotions; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.promotions (prop_id, promo_id, tier_id, promo_type, start_date, end_date, name, description, conditions, live, maxed_out_landing, maxed_out_search) FROM stdin;
1	1	1	1	2020-01-01	2020-02-01	Jan Promo	\N	\N	t	\N	\N
1	2	1	1	2020-01-01	2020-01-01	Jan Promo not live	\N	\N	f	\N	\N
1	3	1	2	2020-01-01	2020-01-01	Jan promo type 2	\N	\N	t	\N	\N
1	5	1	2	2020-02-11	2020-03-11	Feb Promo 2 updatedd	\N	\N	t	\N	\N
\.


--
-- TOC entry 3112 (class 0 OID 40973)
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
-- TOC entry 3134 (class 0 OID 49555)
-- Dependencies: 232
-- Data for Name: prop_choices; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_choices (prop_ch_id, prop_id, choice_id, name, description) FROM stdin;
\.


--
-- TOC entry 3140 (class 0 OID 57711)
-- Dependencies: 238
-- Data for Name: prop_exploded_avail_data; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_exploded_avail_data (prop_id, avail_unit_id, date, time_slot, contract_avail_count, open, close, bookable, booked, reservation_id, hold, contract_id) FROM stdin;
1	1	2020-06-26	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-26	23:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-26	20:00:00	10	0	0	10	0	\N	0	170
1	4	2020-06-26	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-26	23:00:00	1	0	0	1	0	\N	0	170
1	1	2020-06-27	08:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	09:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	10:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	14:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	15:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	20:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	21:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	22:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-27	23:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	08:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	09:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	10:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	14:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	15:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	20:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	21:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	22:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-27	23:00:00	20	0	0	20	0	\N	0	170
1	4	2020-06-27	08:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	09:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	10:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	14:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	15:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	20:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	21:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	22:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-27	23:00:00	2	0	0	2	0	\N	0	170
1	1	2020-06-28	08:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	09:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	10:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	14:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	15:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	20:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	21:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	22:00:00	20	0	0	20	0	\N	0	170
1	1	2020-06-28	23:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	08:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	09:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	10:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	14:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	15:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	20:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	21:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	22:00:00	20	0	0	20	0	\N	0	170
1	2	2020-06-28	23:00:00	20	0	0	20	0	\N	0	170
1	4	2020-06-28	08:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	09:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	10:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	14:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	15:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	20:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	21:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	22:00:00	2	0	0	2	0	\N	0	170
1	4	2020-06-28	23:00:00	2	0	0	2	0	\N	0	170
1	1	2020-06-29	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	20:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-29	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-29	23:00:00	10	0	0	10	0	\N	0	170
1	4	2020-06-29	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-29	23:00:00	1	0	0	1	0	\N	0	170
1	1	2020-06-30	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	20:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-06-30	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-06-30	23:00:00	10	0	0	10	0	\N	0	170
1	4	2020-06-30	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-06-30	23:00:00	1	0	0	1	0	\N	0	170
1	1	2020-07-01	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	20:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-01	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-01	23:00:00	10	0	0	10	0	\N	0	170
1	4	2020-07-01	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-01	23:00:00	1	0	0	1	0	\N	0	170
1	1	2020-07-02	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	20:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-02	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-02	23:00:00	10	0	0	10	0	\N	0	170
1	4	2020-07-02	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-02	23:00:00	1	0	0	1	0	\N	0	170
1	1	2020-07-03	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	20:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-03	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-03	23:00:00	10	0	0	10	0	\N	0	170
1	4	2020-07-03	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-03	23:00:00	1	0	0	1	0	\N	0	170
1	1	2020-07-04	08:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	09:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	10:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	14:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	15:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	20:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	21:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	22:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-04	23:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	08:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	09:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	10:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	14:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	15:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	20:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	21:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	22:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-04	23:00:00	20	0	0	20	0	\N	0	170
1	4	2020-07-04	08:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	09:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	10:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	14:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	15:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	20:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	21:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	22:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-04	23:00:00	2	0	0	2	0	\N	0	170
1	1	2020-07-05	08:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	09:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	10:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	14:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	15:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	20:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	21:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	22:00:00	20	0	0	20	0	\N	0	170
1	1	2020-07-05	23:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	08:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	09:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	10:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	14:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	15:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	20:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	21:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	22:00:00	20	0	0	20	0	\N	0	170
1	2	2020-07-05	23:00:00	20	0	0	20	0	\N	0	170
1	4	2020-07-05	08:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	09:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	10:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	14:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	15:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	20:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	21:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	22:00:00	2	0	0	2	0	\N	0	170
1	4	2020-07-05	23:00:00	2	0	0	2	0	\N	0	170
1	1	2020-07-06	08:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	09:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	10:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	14:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	15:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	20:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	21:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	22:00:00	10	0	0	10	0	\N	0	170
1	1	2020-07-06	23:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	08:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	09:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	10:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	14:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	15:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	20:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	21:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	22:00:00	10	0	0	10	0	\N	0	170
1	2	2020-07-06	23:00:00	10	0	0	10	0	\N	0	170
1	4	2020-07-06	08:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	09:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	10:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	14:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	15:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	20:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	21:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	22:00:00	1	0	0	1	0	\N	0	170
1	4	2020-07-06	23:00:00	1	0	0	1	0	\N	0	170
\.


--
-- TOC entry 3105 (class 0 OID 24582)
-- Dependencies: 203
-- Data for Name: prop_facilities; Type: TABLE DATA; Schema: hngout; Owner: tharinda
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
385	3	Vehicle Parking	Vehicle Parking available with own risk
385	8	\N	\N
\.


--
-- TOC entry 3131 (class 0 OID 49522)
-- Dependencies: 229
-- Data for Name: prop_menu; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_menu (prop_id, menu_id) FROM stdin;
\.


--
-- TOC entry 3127 (class 0 OID 49481)
-- Dependencies: 225
-- Data for Name: prop_operation_hours; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_operation_hours (prop_id, op_id, time_start, time_end, name) FROM stdin;
1	1	08:00:00	11:00:00	Breakfast
1	2	14:00:00	16:00:00	Evening
1	3	20:00:00	24:00:00	Dinner
\.


--
-- TOC entry 3118 (class 0 OID 49275)
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
-- TOC entry 3129 (class 0 OID 49499)
-- Dependencies: 227
-- Data for Name: prop_reservations; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_reservations (prop_id, res_id, booked_contract_id) FROM stdin;
\.


--
-- TOC entry 3120 (class 0 OID 49308)
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
-- TOC entry 3109 (class 0 OID 24700)
-- Dependencies: 207
-- Data for Name: prop_tags; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.prop_tags (prop_id, tag_id, name, description) FROM stdin;
1	2	\N	\N
1	1	Beach Front	Beach Front
382	1	Beach Front	Beach Front
385	1	Beach Front	Beach Front
385	2	\N	\N
\.


--
-- TOC entry 3103 (class 0 OID 16477)
-- Dependencies: 201
-- Data for Name: property; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.property (prop_id, code, name, description, geo_location, current_cont_id, start_time, end_time, org_id, created_user, created_date, modified_user, modified_date, contact_id, based_location_id, timeslot) FROM stdin;
3	mcn_kottaw	Machan Kottawa	dsdsssssssssssssssss	\N	\N	\N	\N	1	\N	\N	\N	\N	\N	\N	\N
6	mcn_kotaat	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
5	mcn_kottx	Machan KottawazXXXz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
1	mcn_pannip	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	(6.84663999999999984,79.9467590000000001)	1	08:00:00	20:00:00	1	\N	\N	\N	\N	1	1	60
7	mcnx_k	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
4	amaya_hill	Amaya Hills Kandy	dsdsdsdsds	\N	\N	\N	\N	3	\N	\N	\N	\N	\N	\N	\N
238	mcnxx	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
240	mcnxpx	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
243	mcnxpllx	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
245	qwe	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
246	qwe1	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
247	qwe2	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
251	mcnxxzz	Machan Kottawazz	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
2	mcn_marada	Machan Maradana	\N	\N	\N	\N	\N	1	\N	\N	\N	\N	\N	\N	\N
270	mcn_1	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	\N	\N
272	mcn_2	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	1	\N
274	mcn_3	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	2	\N
275	mcn_4	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	\N	2	\N
276	mcn_5	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	1	2	\N
280	mcn_6	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	3	2	\N
281	mcn_7	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	4	2	\N
282	mcn_8	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	5	2	\N
284	mcn_10	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	18	1	\N
283	mcn_9	Machan Pannipitiya	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	6	2	\N
299	mcn_11	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	\N	08:00:00	20:00:00	\N	\N	\N	\N	\N	18	1	\N
303	mcn1_11	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	89	08:00:00	20:00:00	\N	\N	\N	\N	\N	17	1	\N
348	x_3	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	72	08:00:00	20:00:00	\N	\N	\N	\N	\N	52	1	\N
307	mcn1_12	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	88	08:00:00	20:00:00	\N	\N	\N	\N	\N	25	1	\N
327	x_1	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	70	08:00:00	20:00:00	\N	\N	\N	\N	\N	41	1	\N
331	x_2	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	71	08:00:00	20:00:00	\N	\N	\N	\N	\N	43	1	\N
349	x_4	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	73	08:00:00	20:00:00	\N	\N	\N	\N	\N	53	1	\N
351	x_5	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	74	08:00:00	20:00:00	\N	\N	\N	\N	\N	55	1	\N
352	x_6	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	75	08:00:00	20:00:00	\N	\N	\N	\N	\N	56	1	\N
353	x_7	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	76	08:00:00	20:00:00	\N	\N	\N	\N	\N	57	1	\N
354	x_8	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	77	08:00:00	20:00:00	\N	\N	\N	\N	\N	58	1	\N
355	x_9	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	78	08:00:00	20:00:00	\N	\N	\N	\N	\N	59	1	\N
356	x1_1	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	79	08:00:00	20:00:00	\N	\N	\N	\N	\N	60	1	\N
357	x1_2	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	80	08:00:00	20:00:00	\N	\N	\N	\N	\N	61	1	\N
359	x1_3	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	81	08:00:00	20:00:00	\N	\N	\N	\N	\N	63	1	\N
360	x360	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	82	08:00:00	20:00:00	\N	\N	\N	\N	\N	64	1	\N
361	x361	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	83	08:00:00	20:00:00	\N	\N	\N	\N	\N	65	1	\N
362	x362	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	84	08:00:00	20:00:00	\N	\N	\N	\N	\N	66	1	\N
363	x363	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	85	08:00:00	20:00:00	\N	\N	\N	\N	\N	67	1	\N
364	x364	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	86	08:00:00	20:00:00	\N	\N	\N	\N	\N	68	3	\N
366	x366	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	91	08:00:00	20:00:00	\N	\N	\N	\N	\N	70	3	\N
367	x367	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	92	08:00:00	20:00:00	\N	\N	\N	\N	\N	71	3	\N
368	x368	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	93	08:00:00	20:00:00	\N	\N	\N	\N	\N	72	3	\N
370	x370	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	94	08:00:00	20:00:00	\N	\N	\N	\N	\N	74	3	\N
371	x371	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	95	08:00:00	20:00:00	\N	\N	\N	\N	\N	75	3	\N
372	x372	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	96	08:00:00	20:00:00	\N	\N	\N	\N	\N	76	3	\N
373	x373	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	97	08:00:00	20:00:00	\N	\N	\N	\N	\N	77	3	\N
374	x374	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	98	08:00:00	20:00:00	\N	\N	\N	\N	\N	78	3	\N
375	x375	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	99	08:00:00	20:00:00	\N	\N	\N	\N	\N	79	3	\N
382	x382	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	102	08:00:00	20:00:00	\N	\N	\N	\N	\N	106	3	\N
385	x385	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	104	08:00:00	20:00:00	\N	\N	\N	\N	\N	107	3	\N
376	x376	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	100	08:00:00	20:00:00	\N	\N	\N	\N	\N	89	3	\N
378	x378	Machan Updated	Machang is an ideal place to cool off with your friends after a busy day or meeting them during lunch while enjoying your favourite cricket matches or Music videos on the big screen; even shooting a game of pool with them.	\N	101	08:00:00	20:00:00	\N	\N	\N	\N	\N	94	3	\N
\.


--
-- TOC entry 3119 (class 0 OID 49290)
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
-- TOC entry 3128 (class 0 OID 49491)
-- Dependencies: 226
-- Data for Name: reservations; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.reservations (reservation_id, date, "time", time_slots, status, host_user, head_count, special_req) FROM stdin;
\.


--
-- TOC entry 3111 (class 0 OID 40965)
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
-- TOC entry 3133 (class 0 OID 49547)
-- Dependencies: 231
-- Data for Name: sys_choices; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.sys_choices (choice_id, name, description) FROM stdin;
\.


--
-- TOC entry 3116 (class 0 OID 49252)
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
106	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
107	prop	zzzzzzzzzxaz	ASASSmachan@info.com	+94789745654	+01124544554	this road	that road	city	\N	12345	machang.com
\.


--
-- TOC entry 3104 (class 0 OID 16506)
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
23	parki11ng1	Vehicle Parking	Vehicle Parking
\.


--
-- TOC entry 3106 (class 0 OID 24653)
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
-- TOC entry 3113 (class 0 OID 40991)
-- Dependencies: 211
-- Data for Name: week_definition; Type: TABLE DATA; Schema: hngout; Owner: tharinda
--

COPY hngout.week_definition (week_def_id, code, name, week) FROM stdin;
4	fridayclos	Close Only Friday	1111011
1	default	Full Week	1111111
2	wd	Week Days	1111100
3	we	Week End	0000011
5	xxx	ssssss	1111111
\.


--
-- TOC entry 3152 (class 0 OID 0)
-- Dependencies: 219
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.contact_id_seq', 107, true);


--
-- TOC entry 3153 (class 0 OID 0)
-- Dependencies: 196
-- Name: contract_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.contract_seq', 170, true);


--
-- TOC entry 3154 (class 0 OID 0)
-- Dependencies: 205
-- Name: facilities_facility_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.facilities_facility_id_seq', 23, true);


--
-- TOC entry 3155 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.location_seq', 3, true);


--
-- TOC entry 3156 (class 0 OID 0)
-- Dependencies: 236
-- Name: menu_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.menu_seq', 3, false);


--
-- TOC entry 3157 (class 0 OID 0)
-- Dependencies: 198
-- Name: organization_org_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.organization_org_id_seq', 472, true);


--
-- TOC entry 3158 (class 0 OID 0)
-- Dependencies: 200
-- Name: property_prop_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.property_prop_id_seq', 385, true);


--
-- TOC entry 3159 (class 0 OID 0)
-- Dependencies: 208
-- Name: sys_avail_unit_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.sys_avail_unit_seq', 7, true);


--
-- TOC entry 3160 (class 0 OID 0)
-- Dependencies: 206
-- Name: tags_tag_id_seq; Type: SEQUENCE SET; Schema: hngout; Owner: tharinda
--

SELECT pg_catalog.setval('hngout.tags_tag_id_seq', 16, true);


--
-- TOC entry 2896 (class 2606 OID 49269)
-- Name: sys_contacts contacts_pk; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_contacts
    ADD CONSTRAINT contacts_pk PRIMARY KEY (contact_id);


--
-- TOC entry 2872 (class 2606 OID 16513)
-- Name: sys_facilities facilities_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_facilities
    ADD CONSTRAINT facilities_pkey PRIMARY KEY (facility_id);


--
-- TOC entry 2908 (class 2606 OID 49360)
-- Name: location_state location_state_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_state
    ADD CONSTRAINT location_state_pkey PRIMARY KEY (state_id, country_id);


--
-- TOC entry 2912 (class 2606 OID 49391)
-- Name: location_suburb location_suburb_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_suburb
    ADD CONSTRAINT location_suburb_pkey PRIMARY KEY (suberb_id, city_id);


--
-- TOC entry 2924 (class 2606 OID 49541)
-- Name: menu_category menu_category_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.menu_category
    ADD CONSTRAINT menu_category_pkey PRIMARY KEY (menu_id, cat_id);


--
-- TOC entry 2930 (class 2606 OID 49586)
-- Name: menu_choices menu_choices_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.menu_choices
    ADD CONSTRAINT menu_choices_pkey PRIMARY KEY (menu_id, cat_id, prop_ch_id);


--
-- TOC entry 2920 (class 2606 OID 49521)
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (menu_id);


--
-- TOC entry 2864 (class 2606 OID 24626)
-- Name: organization org_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.organization
    ADD CONSTRAINT org_pkey PRIMARY KEY (org_id);


--
-- TOC entry 2898 (class 2606 OID 49265)
-- Name: payment_options payment_type_pk; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.payment_options
    ADD CONSTRAINT payment_type_pk PRIMARY KEY (option_id);


--
-- TOC entry 2894 (class 2606 OID 49432)
-- Name: contract_availability pk_avail; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contract_availability
    ADD CONSTRAINT pk_avail PRIMARY KEY (contract_id, avail_unit_id, week_def_id, season_id);


--
-- TOC entry 2910 (class 2606 OID 49385)
-- Name: location_city pk_city; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_city
    ADD CONSTRAINT pk_city PRIMARY KEY (id);


--
-- TOC entry 2862 (class 2606 OID 49416)
-- Name: contracts pk_contract; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contracts
    ADD CONSTRAINT pk_contract PRIMARY KEY (contract_id);


--
-- TOC entry 2906 (class 2606 OID 49372)
-- Name: location_country pk_country; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_country
    ADD CONSTRAINT pk_country PRIMARY KEY (country_id);


--
-- TOC entry 2892 (class 2606 OID 49430)
-- Name: contract_season pk_season; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contract_season
    ADD CONSTRAINT pk_season PRIMARY KEY (contract_id, season_id);


--
-- TOC entry 2902 (class 2606 OID 49304)
-- Name: property_speciality pk_specialty; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property_speciality
    ADD CONSTRAINT pk_specialty PRIMARY KEY (speciality_id);


--
-- TOC entry 2904 (class 2606 OID 49312)
-- Name: prop_speciality pk_specialty_prop; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_speciality
    ADD CONSTRAINT pk_specialty_prop PRIMARY KEY (prop_id, speciality_id);


--
-- TOC entry 2940 (class 2606 OID 74033)
-- Name: promotion_statistics promotion_statistics_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotion_statistics
    ADD CONSTRAINT promotion_statistics_pkey PRIMARY KEY (prop_id, promo_id);


--
-- TOC entry 2932 (class 2606 OID 49604)
-- Name: promotion_tiers promotion_tiers_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotion_tiers
    ADD CONSTRAINT promotion_tiers_pkey PRIMARY KEY (tier_id);


--
-- TOC entry 2936 (class 2606 OID 57681)
-- Name: promotion_types promotion_types_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotion_types
    ADD CONSTRAINT promotion_types_pkey PRIMARY KEY (type_id);


--
-- TOC entry 2934 (class 2606 OID 57659)
-- Name: promotions promotions_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotions
    ADD CONSTRAINT promotions_pkey PRIMARY KEY (prop_id, promo_id);


--
-- TOC entry 2888 (class 2606 OID 57698)
-- Name: prop_availability_unit prop_avail_type_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_pkey PRIMARY KEY (prop_id, unit_id);


--
-- TOC entry 2928 (class 2606 OID 49574)
-- Name: prop_choices prop_choices_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_choices
    ADD CONSTRAINT prop_choices_pkey PRIMARY KEY (prop_ch_id);


--
-- TOC entry 2876 (class 2606 OID 49454)
-- Name: prop_facilities prop_facilities_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facilities_pkey PRIMARY KEY (prop_id, facility_id);


--
-- TOC entry 2922 (class 2606 OID 49526)
-- Name: prop_menu prop_menu_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_menu
    ADD CONSTRAINT prop_menu_pkey PRIMARY KEY (prop_id, menu_id);


--
-- TOC entry 2914 (class 2606 OID 49485)
-- Name: prop_operation_hours prop_operation_hours_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_operation_hours
    ADD CONSTRAINT prop_operation_hours_pkey PRIMARY KEY (prop_id, op_id);


--
-- TOC entry 2900 (class 2606 OID 49279)
-- Name: prop_payment_options prop_payment_pk; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_payment_options
    ADD CONSTRAINT prop_payment_pk PRIMARY KEY (prop_id, option_id);


--
-- TOC entry 2918 (class 2606 OID 49503)
-- Name: prop_reservations prop_reservations_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_reservations
    ADD CONSTRAINT prop_reservations_pkey PRIMARY KEY (prop_id, res_id);


--
-- TOC entry 2882 (class 2606 OID 49468)
-- Name: prop_tags prop_tags_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_tags
    ADD CONSTRAINT prop_tags_pkey PRIMARY KEY (prop_id, tag_id);


--
-- TOC entry 2938 (class 2606 OID 57715)
-- Name: prop_exploded_avail_data prop_widen_data_grid_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_exploded_avail_data
    ADD CONSTRAINT prop_widen_data_grid_pkey PRIMARY KEY (prop_id, avail_unit_id, date, time_slot);


--
-- TOC entry 2868 (class 2606 OID 24639)
-- Name: property property_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT property_pkey PRIMARY KEY (prop_id);


--
-- TOC entry 2916 (class 2606 OID 49498)
-- Name: reservations reservations_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (reservation_id);


--
-- TOC entry 2884 (class 2606 OID 49185)
-- Name: sys_availability_unit sys_avail_type_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_availability_unit
    ADD CONSTRAINT sys_avail_type_pkey PRIMARY KEY (unit_id);


--
-- TOC entry 2926 (class 2606 OID 49554)
-- Name: sys_choices sys_choices_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_choices
    ADD CONSTRAINT sys_choices_pkey PRIMARY KEY (choice_id);


--
-- TOC entry 2878 (class 2606 OID 24657)
-- Name: sys_tags tags_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tag_id);


--
-- TOC entry 2886 (class 2606 OID 40972)
-- Name: sys_availability_unit uk_avail_unit_code; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_availability_unit
    ADD CONSTRAINT uk_avail_unit_code UNIQUE (code);


--
-- TOC entry 2874 (class 2606 OID 16519)
-- Name: sys_facilities uk_code_facility; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_facilities
    ADD CONSTRAINT uk_code_facility UNIQUE (code);


--
-- TOC entry 2866 (class 2606 OID 16517)
-- Name: organization uk_code_org; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.organization
    ADD CONSTRAINT uk_code_org UNIQUE (code);


--
-- TOC entry 2870 (class 2606 OID 16521)
-- Name: property uk_code_prop; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT uk_code_prop UNIQUE (code);


--
-- TOC entry 2880 (class 2606 OID 49407)
-- Name: sys_tags uk_tag_code; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.sys_tags
    ADD CONSTRAINT uk_tag_code UNIQUE (code);


--
-- TOC entry 2890 (class 2606 OID 49197)
-- Name: week_definition week_definition_pkey; Type: CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.week_definition
    ADD CONSTRAINT week_definition_pkey PRIMARY KEY (week_def_id);


--
-- TOC entry 2952 (class 2606 OID 49220)
-- Name: contract_availability avail_week_def_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contract_availability
    ADD CONSTRAINT avail_week_def_fk FOREIGN KEY (week_def_id) REFERENCES hngout.week_definition(week_def_id);


--
-- TOC entry 2967 (class 2606 OID 49563)
-- Name: prop_choices fk_choice_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_choices
    ADD CONSTRAINT fk_choice_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2944 (class 2606 OID 49333)
-- Name: property fk_contact; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_contact FOREIGN KEY (contact_id) REFERENCES hngout.sys_contacts(contact_id);


--
-- TOC entry 2941 (class 2606 OID 49417)
-- Name: property fk_contract; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_contract FOREIGN KEY (current_cont_id) REFERENCES hngout.contracts(contract_id);


--
-- TOC entry 2958 (class 2606 OID 49373)
-- Name: location_state fk_country; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_state
    ADD CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES hngout.location_country(country_id);


--
-- TOC entry 2943 (class 2606 OID 49397)
-- Name: property fk_location; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_location FOREIGN KEY (based_location_id) REFERENCES hngout.location_city(id);


--
-- TOC entry 2966 (class 2606 OID 49542)
-- Name: menu_category fk_menu_cat; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.menu_category
    ADD CONSTRAINT fk_menu_cat FOREIGN KEY (menu_id) REFERENCES hngout.menu(menu_id);


--
-- TOC entry 2969 (class 2606 OID 49587)
-- Name: menu_choices fk_menu_cat_choices; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.menu_choices
    ADD CONSTRAINT fk_menu_cat_choices FOREIGN KEY (menu_id, cat_id) REFERENCES hngout.menu_category(menu_id, cat_id);


--
-- TOC entry 2970 (class 2606 OID 49592)
-- Name: menu_choices fk_menu_choice_prop_ch; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.menu_choices
    ADD CONSTRAINT fk_menu_choice_prop_ch FOREIGN KEY (prop_ch_id) REFERENCES hngout.prop_choices(prop_ch_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2964 (class 2606 OID 49527)
-- Name: prop_menu fk_menu_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_menu
    ADD CONSTRAINT fk_menu_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2942 (class 2606 OID 32768)
-- Name: property fk_org; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.property
    ADD CONSTRAINT fk_org FOREIGN KEY (org_id) REFERENCES hngout.organization(org_id);


--
-- TOC entry 2954 (class 2606 OID 49280)
-- Name: prop_payment_options fk_pay_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_payment_options
    ADD CONSTRAINT fk_pay_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2972 (class 2606 OID 49613)
-- Name: promotions fk_promo_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotions
    ADD CONSTRAINT fk_promo_prop FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2976 (class 2606 OID 74034)
-- Name: promotion_statistics fk_promo_stat; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotion_statistics
    ADD CONSTRAINT fk_promo_stat FOREIGN KEY (prop_id, promo_id) REFERENCES hngout.promotions(prop_id, promo_id);


--
-- TOC entry 2973 (class 2606 OID 49618)
-- Name: promotions fk_promo_tier; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotions
    ADD CONSTRAINT fk_promo_tier FOREIGN KEY (tier_id) REFERENCES hngout.promotion_tiers(tier_id);


--
-- TOC entry 2971 (class 2606 OID 57692)
-- Name: promotions fk_promo_type; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.promotions
    ADD CONSTRAINT fk_promo_type FOREIGN KEY (promo_type) REFERENCES hngout.promotion_types(type_id);


--
-- TOC entry 2965 (class 2606 OID 49532)
-- Name: prop_menu fk_prop_menu; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_menu
    ADD CONSTRAINT fk_prop_menu FOREIGN KEY (menu_id) REFERENCES hngout.menu(menu_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2961 (class 2606 OID 49486)
-- Name: prop_operation_hours fk_prop_op; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_operation_hours
    ADD CONSTRAINT fk_prop_op FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2955 (class 2606 OID 49285)
-- Name: prop_payment_options fk_prop_pay; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_payment_options
    ADD CONSTRAINT fk_prop_pay FOREIGN KEY (option_id) REFERENCES hngout.payment_options(option_id);


--
-- TOC entry 2962 (class 2606 OID 49504)
-- Name: prop_reservations fk_prop_res; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_reservations
    ADD CONSTRAINT fk_prop_res FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2957 (class 2606 OID 49313)
-- Name: prop_speciality fk_prop_specialty; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_speciality
    ADD CONSTRAINT fk_prop_specialty FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2963 (class 2606 OID 49509)
-- Name: prop_reservations fk_res_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_reservations
    ADD CONSTRAINT fk_res_prop FOREIGN KEY (res_id) REFERENCES hngout.reservations(reservation_id);


--
-- TOC entry 2953 (class 2606 OID 49443)
-- Name: contract_availability fk_season_avail; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contract_availability
    ADD CONSTRAINT fk_season_avail FOREIGN KEY (contract_id, season_id) REFERENCES hngout.contract_season(contract_id, season_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2951 (class 2606 OID 49448)
-- Name: contract_season fk_season_contract; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.contract_season
    ADD CONSTRAINT fk_season_contract FOREIGN KEY (contract_id) REFERENCES hngout.contracts(contract_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2956 (class 2606 OID 49318)
-- Name: prop_speciality fk_specialty_prop; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_speciality
    ADD CONSTRAINT fk_specialty_prop FOREIGN KEY (speciality_id) REFERENCES hngout.property_speciality(speciality_id);


--
-- TOC entry 2959 (class 2606 OID 49366)
-- Name: location_city fk_state_city; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_city
    ADD CONSTRAINT fk_state_city FOREIGN KEY (country_id, state_id) REFERENCES hngout.location_state(country_id, state_id);


--
-- TOC entry 2960 (class 2606 OID 49392)
-- Name: location_suburb fk_suburb; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.location_suburb
    ADD CONSTRAINT fk_suburb FOREIGN KEY (city_id) REFERENCES hngout.location_city(id);


--
-- TOC entry 2968 (class 2606 OID 49568)
-- Name: prop_choices fk_sys_choice; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_choices
    ADD CONSTRAINT fk_sys_choice FOREIGN KEY (choice_id) REFERENCES hngout.sys_choices(choice_id);


--
-- TOC entry 2974 (class 2606 OID 57716)
-- Name: prop_exploded_avail_data fk_wide_data_avail_unit; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_exploded_avail_data
    ADD CONSTRAINT fk_wide_data_avail_unit FOREIGN KEY (prop_id, avail_unit_id) REFERENCES hngout.prop_availability_unit(prop_id, unit_id);


--
-- TOC entry 2975 (class 2606 OID 57721)
-- Name: prop_exploded_avail_data fk_wide_data_reservation; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_exploded_avail_data
    ADD CONSTRAINT fk_wide_data_reservation FOREIGN KEY (reservation_id) REFERENCES hngout.reservations(reservation_id);


--
-- TOC entry 2948 (class 2606 OID 49469)
-- Name: prop_tags pprop_tags_prop_fki; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_tags
    ADD CONSTRAINT pprop_tags_prop_fki FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2950 (class 2606 OID 57699)
-- Name: prop_availability_unit prop_avail_type_prop_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_prop_fk FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2949 (class 2606 OID 49186)
-- Name: prop_availability_unit prop_avail_type_st_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_availability_unit
    ADD CONSTRAINT prop_avail_type_st_fk FOREIGN KEY (unit_id) REFERENCES hngout.sys_availability_unit(unit_id);


--
-- TOC entry 2945 (class 2606 OID 24682)
-- Name: prop_facilities prop_facility_facility_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facility_facility_fk FOREIGN KEY (facility_id) REFERENCES hngout.sys_facilities(facility_id);


--
-- TOC entry 2946 (class 2606 OID 49455)
-- Name: prop_facilities prop_facility_prop_fki; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_facilities
    ADD CONSTRAINT prop_facility_prop_fki FOREIGN KEY (prop_id) REFERENCES hngout.property(prop_id);


--
-- TOC entry 2947 (class 2606 OID 24708)
-- Name: prop_tags prop_tags_tag_fk; Type: FK CONSTRAINT; Schema: hngout; Owner: tharinda
--

ALTER TABLE ONLY hngout.prop_tags
    ADD CONSTRAINT prop_tags_tag_fk FOREIGN KEY (tag_id) REFERENCES hngout.sys_tags(tag_id);


-- Completed on 2020-07-06 23:12:33

--
-- PostgreSQL database dump complete
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-07-06 23:12:33

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
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


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

CREATE TABLE public.quque (
    qq date[],
    id integer
);


ALTER TABLE public.quque OWNER TO postgres;

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


-- Completed on 2020-07-06 23:12:33

--
-- PostgreSQL database dump complete
--

-- Completed on 2020-07-06 23:12:33

--
-- PostgreSQL database cluster dump complete
--

