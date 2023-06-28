-- Create the database
CREATE DATABASE previred_desafio;

-- Switch to the newly created database
\c previred_desafio;

-- Create the role
CREATE ROLE previred LOGIN PASSWORD 'previred';

-- Grant all permissions to the role
GRANT ALL PRIVILEGES ON DATABASE previred_desafio TO previred;

-- Create the "companies" table
CREATE TABLE companies (
                           id bigserial NOT NULL,
                           company_name varchar(255) NOT NULL,
                           "date" date NOT NULL,
                           rut varchar(255) NOT NULL,
                           CONSTRAINT companies_pkey PRIMARY KEY (id)
);

-- Create the "workers" table with a foreign key to "companies"
CREATE TABLE workers (
                         id bigserial NOT NULL,
                         address varchar(255) NOT NULL,
                         first_name varchar(255) NOT NULL,
                         last_name varchar(255) NOT NULL,
                         rut varchar(255) NOT NULL,
                         second_last_name varchar(255) NOT NULL,
                         company_id int8 NULL,
                         CONSTRAINT workers_pkey PRIMARY KEY (id),
                         CONSTRAINT fk777a3390p2trv3h9yeyaegduo FOREIGN KEY (company_id) REFERENCES companies(id)
);

-- Test data
INSERT INTO companies (company_name,"date",rut)
VALUES ('Apple','1998-03-23','458763621');

INSERT INTO companies (company_name,"date",rut)
VALUES ('Samsung','2003-07-13','508724834');

INSERT INTO workers (address,first_name,last_name,rut,second_last_name,company_id)
VALUES ('Centenerario 198, Independencia','Camila','Perez','192384632','Santana',1);

INSERT INTO workers (address,first_name,last_name,rut,second_last_name,company_id)
VALUES ('Arturo Prat 12, Rancagua','Fabian','Hormazabal','167289342','Pinto',1);

INSERT INTO workers (address,first_name,last_name,rut,second_last_name,company_id)
VALUES ('Vivaceta 353, Chillán','Sofia','Canales','150938341','Sagredo',2);



