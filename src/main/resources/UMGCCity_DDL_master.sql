-- noinspection SqlResolveForFile

DROP DATABASE "UMGCity";

CREATE DATABASE "UMGCity"
    WITH 
    OWNER = postgres
    TABLESPACE = pg_default
    CONNECTION LIMIT = 500;

DROP SCHEMA IF EXISTS public CASCADE;

DROP TABLE IF EXISTS city_user CASCADE;
DROP TABLE IF EXISTS city  CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;
DROP TABLE IF EXISTS zone_land_use  CASCADE;
DROP TABLE IF EXISTS zone CASCADE;
DROP TABLE IF EXISTS development_standards CASCADE;
DROP TABLE IF EXISTS allowed_land_use CASCADE;

CREATE SCHEMA public
    AUTHORIZATION postgres;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';

CREATE TABLE public.city_user
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    email_address character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT city_user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.city_user
    OWNER to postgres;
	
CREATE TABLE public.city
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    city_user_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    state character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT city_id PRIMARY KEY (id),
    CONSTRAINT city_city_user_id_fkey FOREIGN KEY (city_user_id)
        REFERENCES public.city_user (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.city
    OWNER to postgres;

CREATE TABLE public.authorities
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    city_user_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES city_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    authority character varying(50) COLLATE pg_catalog."default" NOT NULL
);

TABLESPACE pg_default;

ALTER TABLE public.authorities
    OWNER to postgres;

CREATE TABLE public.zone_land_use
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    city_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    description character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT zone_land_use_id PRIMARY KEY (id),
    CONSTRAINT zone_land_use_city_id_fkey FOREIGN KEY (city_id)
        REFERENCES public.city (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.zone_land_use
    OWNER to postgres;

CREATE TABLE public.zone
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    city_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    zone_symbol character varying(25) COLLATE pg_catalog."default" NOT NULL,
    description character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT zone_id PRIMARY KEY (id),
    CONSTRAINT zone_city_id_fkey FOREIGN KEY (city_id)
        REFERENCES public.city (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.zone
    OWNER to postgres;	

CREATE TABLE public.allowed_land_use
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    zone_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    zone_land_use_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    permit_name character varying(250) COLLATE pg_catalog."default" NOT NULL,
    permit_description character varying(500) COLLATE pg_catalog."default" NOT NULL,
    procedure_url character varying(250) COLLATE pg_catalog."default" NOT NULL,
    application_url character varying(250) COLLATE pg_catalog."default",
    CONSTRAINT allowed_land_use_id PRIMARY KEY (id),
    CONSTRAINT allowed_land_use_zone_id_fkey FOREIGN KEY (zone_id)
        REFERENCES public.zone (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT allowed_land_use_zone_land_use_id_fkey FOREIGN KEY (zone_land_use_id)
        REFERENCES public.zone_land_use (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.allowed_land_use
    OWNER to postgres;
	
CREATE TABLE public.development_standards
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    zone_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    general_standard_url character varying(200) COLLATE pg_catalog."default" NOT NULL,
    additional_standard_url character varying(200) COLLATE pg_catalog."default",
    garden_standard_url character varying(200) COLLATE pg_catalog."default",
    frontage_and_facades_standards_url character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT development_standards_id PRIMARY KEY (id),
    CONSTRAINT development_standards_zone_id_fkey FOREIGN KEY (zone_id)
        REFERENCES public.zone (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.development_standards
	OWNER to postgres;
