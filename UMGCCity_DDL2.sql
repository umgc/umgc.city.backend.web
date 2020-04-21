


DROP SCHEMA IF EXISTS public CASCADE;
DROP TABLE IF EXISTS city_user CASCADE;
DROP TABLE IF EXISTS city  CASCADE;
DROP TABLE IF EXISTS zone_land_use  CASCADE;
DROP TABLE IF EXISTS zone CASCADE;
DROP TABLE IF EXISTS development_standards CASCADE;
DROP TABLE IF EXISTS allowed_land_use CASCADE;
DROP TABLE IF EXISTS permit_type CASCADE;
DROP TABLE IF EXISTS application CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;




CREATE SCHEMA public

    AUTHORIZATION postgres;



CREATE EXTENSION IF NOT EXISTS "uuid-ossp";



COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


  create table public.city_user(
	  id uuid DEFAULT uuid_generate_v4 ()primary key,
	  email_address character varying(100) not null ,
      password character varying(100) not null,
      enabled boolean not null, /*add by Tarig*/
	   last_name character varying(50),
          first_name character varying(50)

      );

create unique index ix_city_user_id on city_user (email_address);/*add by Tarig*/

ALTER TABLE public.city_user

    OWNER to postgres;


/*able public.authorities add by Tarig*/
  create table public.authorities (
      email_address character varying(100) not null,
      authority character varying(50) not null,
      constraint fk_authorities_users foreign key(email_address) references city_user(email_address)
      );
      create unique index ix_auth_username on authorities (email_address,authority);

	ALTER TABLE public.city_user    OWNER to postgres;

CREATE TABLE public.city

(

    id uuid DEFAULT uuid_generate_v4 (),

    city_user_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES city_user(id) ON DELETE CASCADE ON UPDATE CASCADE,

    name character varying(50) NOT NULL,

    state character varying(50) NOT NULL,

    CONSTRAINT city_id PRIMARY KEY (id)

);



ALTER TABLE public.city

    OWNER to postgres;



CREATE TABLE public.zone_land_use

(

    id uuid DEFAULT uuid_generate_v4 (),

    city_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE,

    description character varying(100) NOT NULL,

    CONSTRAINT zone_land_use_id PRIMARY KEY (id)

);



ALTER TABLE public.zone_land_use

    OWNER to postgres;



CREATE TABLE public.zone

(

    id uuid DEFAULT uuid_generate_v4(),

    city_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE,

	zone_symbol character varying(5) NOT NULL,

	description character varying(100) NOT NULL,

    CONSTRAINT zone_id PRIMARY KEY (id)

);



ALTER TABLE public.zone

    OWNER to postgres;	



CREATE TABLE public.allowed_land_use

(

    id uuid DEFAULT uuid_generate_v4(),

    zone_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES zone(id),

    zone_land_use_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES zone_land_use(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT allowed_land_use_id PRIMARY KEY (id)

 );



ALTER TABLE public.allowed_land_use

    OWNER to postgres;

	

CREATE TABLE public.development_standards

(

    id uuid NOT NULL DEFAULT uuid_generate_v4(),

	zone_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES zone(id) ON DELETE CASCADE ON UPDATE CASCADE,

    general_standard_url character varying(100) NOT NULL,

    additional_standard_url character varying(100) NOT NULL,

    garden_standard_url character varying(100) NOT NULL,

    frontage_and_facades_standards_url character varying(100) NOT NULL,

    CONSTRAINT development_standards_id PRIMARY KEY (id)

);



ALTER TABLE public.development_standards

    OWNER to postgres;

	

CREATE TABLE public.permit_type

(

    id uuid NOT NULL DEFAULT uuid_generate_v4(),

	allowed_land_use_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES allowed_land_use(id) ON DELETE CASCADE ON UPDATE CASCADE,

    description character varying(100) NOT NULL,

    procedure_url character varying(100) NOT NULL,

    name character varying(100),

    CONSTRAINT permit_type_id PRIMARY KEY (id)

);



ALTER TABLE public.permit_type

    OWNER to postgres;

	

CREATE TABLE public.application

(

    id uuid NOT NULL DEFAULT uuid_generate_v4(),

	permit_type_id uuid NOT NULL DEFAULT uuid_generate_v4() REFERENCES permit_type(id) ON DELETE CASCADE ON UPDATE CASCADE,

    application_url character varying(100) NOT NULL,

    name character varying(50) NOT NULL,

    CONSTRAINT application_id PRIMARY KEY (id)

);



ALTER TABLE public.application

    OWNER to postgres;