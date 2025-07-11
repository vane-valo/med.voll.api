CREATE TABLE medicos(

    id bigint NOT NULL auto_increment,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(12) NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    barrio VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(12) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    estado VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,

    PRIMARY KEY(id)

);
