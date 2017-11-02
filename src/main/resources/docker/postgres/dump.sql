--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.9
-- Dumped by pg_dump version 9.5.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'ru_RU.UTF-8' LC_CTYPE = 'ru_RU.UTF-8';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: uni; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA uni;


ALTER SCHEMA uni OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = uni, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cathedras; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE cathedras (
    name integer,
    id integer NOT NULL
);


ALTER TABLE cathedras OWNER TO postgres;

--
-- Name: faculties; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE faculties (
    name integer,
    id integer NOT NULL
);


ALTER TABLE faculties OWNER TO postgres;

--
-- Name: groups; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE groups (
    name integer,
    id integer NOT NULL
);


ALTER TABLE groups OWNER TO postgres;

--
-- Name: marks; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE marks (
    rating integer NOT NULL,
    id integer NOT NULL,
    studentid integer,
    subjectid integer
);


ALTER TABLE marks OWNER TO postgres;

--
-- Name: students; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE students (
    name character varying(20),
    surname character varying(20),
    sex character varying(20),
    id integer NOT NULL
);


ALTER TABLE students OWNER TO postgres;

--
-- Name: subjects; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE subjects (
    name character varying(20),
    id integer NOT NULL
);


ALTER TABLE subjects OWNER TO postgres;

--
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: uni; Owner: postgres
--

CREATE SEQUENCE subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE subjects_id_seq OWNER TO postgres;

--
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: uni; Owner: postgres
--

ALTER SEQUENCE subjects_id_seq OWNED BY subjects.id;


--
-- Name: universities; Type: TABLE; Schema: uni; Owner: postgres
--

CREATE TABLE universities (
    name character varying(20),
    id integer NOT NULL
);


ALTER TABLE universities OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY subjects ALTER COLUMN id SET DEFAULT nextval('subjects_id_seq'::regclass);


--
-- Data for Name: cathedras; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY cathedras (name, id) FROM stdin;
\.


--
-- Data for Name: faculties; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY faculties (name, id) FROM stdin;
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY groups (name, id) FROM stdin;
\.


--
-- Data for Name: marks; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY marks (rating, id, studentid, subjectid) FROM stdin;
\.


--
-- Data for Name: students; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY students (name, surname, sex, id) FROM stdin;
\.


--
-- Data for Name: subjects; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY subjects (name, id) FROM stdin;
\.


--
-- Name: subjects_id_seq; Type: SEQUENCE SET; Schema: uni; Owner: postgres
--

SELECT pg_catalog.setval('subjects_id_seq', 1, false);


--
-- Data for Name: universities; Type: TABLE DATA; Schema: uni; Owner: postgres
--

COPY universities (name, id) FROM stdin;
МУПОЧ	1
fsafsa	2
helloFromJava	3
\.


--
-- Name: cathedras_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY cathedras
    ADD CONSTRAINT cathedras_id_pk PRIMARY KEY (id);


--
-- Name: faculties_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY faculties
    ADD CONSTRAINT faculties_id_pk PRIMARY KEY (id);


--
-- Name: groups_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_id_pk PRIMARY KEY (id);


--
-- Name: marks_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY marks
    ADD CONSTRAINT marks_id_pk PRIMARY KEY (id);


--
-- Name: students_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_id_pk PRIMARY KEY (id);


--
-- Name: subjects_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_id_pk PRIMARY KEY (id);


--
-- Name: universities_id_pk; Type: CONSTRAINT; Schema: uni; Owner: postgres
--

ALTER TABLE ONLY universities
    ADD CONSTRAINT universities_id_pk PRIMARY KEY (id);


--
-- Name: cathedras_name_uindex; Type: INDEX; Schema: uni; Owner: postgres
--

CREATE UNIQUE INDEX cathedras_name_uindex ON cathedras USING btree (name);


--
-- Name: faculties_name_uindex; Type: INDEX; Schema: uni; Owner: postgres
--

CREATE UNIQUE INDEX faculties_name_uindex ON faculties USING btree (name);


--
-- Name: groups_name_uindex; Type: INDEX; Schema: uni; Owner: postgres
--

CREATE UNIQUE INDEX groups_name_uindex ON groups USING btree (name);


--
-- Name: subjects_name_uindex; Type: INDEX; Schema: uni; Owner: postgres
--

CREATE UNIQUE INDEX subjects_name_uindex ON subjects USING btree (name);


--
-- Name: universities_name_uindex; Type: INDEX; Schema: uni; Owner: postgres
--

CREATE UNIQUE INDEX universities_name_uindex ON universities USING btree (name);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

