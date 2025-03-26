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
    entrenamientos_totales int,
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

CREATE TABLE horarios_pista (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    pista_id bigint NOT NULL,
    fecha_inicio datetime NOT NULL,
    fecha_fin datetime NOT NULL,
    entrenamiento_id bigint NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    UNIQUE KEY uk_entrenamiento_id (entrenamiento_id),
    FOREIGN KEY (pista_id) REFERENCES pistas(id)
);
