CREATE TABLE profiles (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    suscripcion_id bigint,
    numerosocio varchar(50) UNIQUE,
    nombre varchar(255),
    apellidos varchar(255),
    bio text,
    image varchar(255),
    edad int,
    posicion_preferida varchar(50),
    club_origen varchar(255) NULL,
    es_menor boolean DEFAULT false,
    numeroEntrenador varchar(50) UNIQUE NULL,
    especialidad varchar(255) NULL,
    experiencia_años int NULL,
    certificaciones text NULL,
    organizacion_origen varchar(255) NULL,
    entrenamientos_disponibles INT DEFAULT 0,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id),
    FOREIGN KEY (suscripcion_id) REFERENCES suscripciones(id)
);

CREATE TABLE subtipo_tecnificacion (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    tecnificacion_id bigint,
    nombre varchar(255),
    slug varchar(255),
    descripcion text,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (tecnificacion_id) REFERENCES tecnificaciones(id)
);
