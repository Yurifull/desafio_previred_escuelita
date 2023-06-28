CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    rut VARCHAR(25),
    razon_social VARCHAR(50),
    fecha VARCHAR(50)
);

CREATE TABLE trabajadores (
    id SERIAL PRIMARY KEY,
    id_empresa BIGINT REFERENCES empresas(id),
    rut VARCHAR(25),
    apellido_paterno VARCHAR(50),
    apellido_materno VARCHAR(50),
    direccion VARCHAR(70)
);