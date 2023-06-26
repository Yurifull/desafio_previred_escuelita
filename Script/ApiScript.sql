--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-06-26 02:06:24

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
-- TOC entry 2 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 3337 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 24662)
-- Name: empresas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresas (
    id bigint NOT NULL,
    fecha_de_insercion timestamp(6) without time zone,
    razon_social character varying(100),
    rut character varying(12)
);


ALTER TABLE public.empresas OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24661)
-- Name: empresas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empresas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresas_id_seq OWNER TO postgres;

--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 215
-- Name: empresas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empresas_id_seq OWNED BY public.empresas.id;


--
-- TOC entry 218 (class 1259 OID 24669)
-- Name: trabajadores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trabajadores (
    id bigint NOT NULL,
    apellido_materno character varying(255),
    apellido_paterno character varying(255),
    direccion_fisica character varying(255),
    nombre character varying(255),
    rut character varying(12),
    empresa_id bigint NOT NULL
);


ALTER TABLE public.trabajadores OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24668)
-- Name: trabajadores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.trabajadores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trabajadores_id_seq OWNER TO postgres;

--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 217
-- Name: trabajadores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.trabajadores_id_seq OWNED BY public.trabajadores.id;


--
-- TOC entry 3179 (class 2604 OID 24665)
-- Name: empresas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresas ALTER COLUMN id SET DEFAULT nextval('public.empresas_id_seq'::regclass);


--
-- TOC entry 3180 (class 2604 OID 24672)
-- Name: trabajadores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trabajadores ALTER COLUMN id SET DEFAULT nextval('public.trabajadores_id_seq'::regclass);


--
-- TOC entry 3329 (class 0 OID 24662)
-- Dependencies: 216
-- Data for Name: empresas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empresas (id, fecha_de_insercion, razon_social, rut) FROM stdin;
\.


--
-- TOC entry 3331 (class 0 OID 24669)
-- Dependencies: 218
-- Data for Name: trabajadores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trabajadores (id, apellido_materno, apellido_paterno, direccion_fisica, nombre, rut, empresa_id) FROM stdin;
\.


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 215
-- Name: empresas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empresas_id_seq', 1, false);


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 217
-- Name: trabajadores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trabajadores_id_seq', 1, false);


--
-- TOC entry 3182 (class 2606 OID 24667)
-- Name: empresas empresas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT empresas_pkey PRIMARY KEY (id);


--
-- TOC entry 3184 (class 2606 OID 24676)
-- Name: trabajadores trabajadores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trabajadores
    ADD CONSTRAINT trabajadores_pkey PRIMARY KEY (id);


--
-- TOC entry 3185 (class 2606 OID 24677)
-- Name: trabajadores fkjjjbdr4kuhrdiq5houakgb0pe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trabajadores
    ADD CONSTRAINT fkjjjbdr4kuhrdiq5houakgb0pe FOREIGN KEY (empresa_id) REFERENCES public.empresas(id);


-- Completed on 2023-06-26 02:06:24

--
-- PostgreSQL database dump complete
--

