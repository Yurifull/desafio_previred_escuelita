CREATE TABLE empresa (
                           id bigserial NOT NULL,
                           nombre varchar(255) NOT NULL,
                           razon_social varchar(255),
                           fecha_registro varchar(255),
                           rut varchar(255) NOT NULL,
                           CONSTRAINT empresa_pkey PRIMARY KEY (id)
);


CREATE TABLE empleado (
                         id bigserial NOT NULL,
                         direccion varchar(255) NOT NULL,
                         nombre varchar(255) NOT NULL,
                         apellidoP varchar(255) NOT NULL,
                         apellidoM varchar(255) NOT NULL,
						 rut varchar(255) NOT NULL,
                         empresa_id int8 NULL,
                         CONSTRAINT empleado_pkey PRIMARY KEY (id),
                         CONSTRAINT fk_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);





