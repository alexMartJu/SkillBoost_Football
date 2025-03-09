CREATE TABLE entrenamientos (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    slug varchar(255) UNIQUE,
    descripcion text,
    nivel varchar(50),
    edad_minima int,
    edad_maxima int,
    tecnificacion_id bigint,
    subtipo_tecnificacion_id bigint,
    entrenador_id bigint,
    max_plazas int,
    objetivos text,
    equipamiento_necesario text,
    duracion_minutos int,
    status varchar(50) DEFAULT 'active',
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    deleted_at timestamp NULL,
    FOREIGN KEY (tecnificacion_id) REFERENCES tecnificaciones(id),
    FOREIGN KEY (subtipo_tecnificacion_id) REFERENCES subtipo_tecnificacion(id),
    FOREIGN KEY (entrenador_id) REFERENCES profiles(id)
);

CREATE TABLE logros (
    id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    descripcion text,
    requisito_entrenamientos int,
    imagen varchar(255),
    nivel_dificultad varchar(50),
    puntos int,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL
);
