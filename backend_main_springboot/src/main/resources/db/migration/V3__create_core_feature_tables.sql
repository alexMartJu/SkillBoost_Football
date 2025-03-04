CREATE TABLE tecnificaciones (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    slug varchar(255) UNIQUE,
    descripcion text,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    deleted_at timestamp NULL
);

CREATE TABLE suscripciones (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(100) NOT NULL,
    slug varchar(255) UNIQUE NOT NULL,
    precio decimal(10,2) NOT NULL,
    entrenamientos_semanales int,
    incluye_graficas boolean DEFAULT false,
    incluye_evaluacion boolean DEFAULT false,
    ventaja1 varchar(255),
    ventaja2 varchar(255),
    ventaja3 varchar(255),
    ventaja4 varchar(255),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    deleted_at timestamp NULL
);

CREATE TABLE pistas (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    slug varchar(255) UNIQUE,
    descripcion text,
    tipo varchar(50),
    dimensiones varchar(50),
    capacidad int,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    deleted_at timestamp NULL
);

CREATE TABLE horarios (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    fecha_inicio datetime,
    fecha_fin datetime,
    disponible boolean DEFAULT true,
    deleted_at timestamp NULL
);
