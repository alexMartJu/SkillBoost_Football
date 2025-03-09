CREATE TABLE profile_logros (
    profile_id bigint,
    logro_id int,
    fecha_logro timestamp DEFAULT CURRENT_TIMESTAMP,
    progreso_actual int,
    PRIMARY KEY (profile_id, logro_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (logro_id) REFERENCES logros(id)
);

CREATE TABLE pagos (
    id int PRIMARY KEY AUTO_INCREMENT,
    profile_id bigint,
    suscripcion_id bigint,
    monto decimal(10,2),
    fecha_pago timestamp DEFAULT CURRENT_TIMESTAMP,
    fecha_inicio date,
    fecha_fin date,
    estado varchar(50),
    metodo_pago varchar(50),
    referencia_pago varchar(255),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (suscripcion_id) REFERENCES suscripciones(id)
);

CREATE TABLE reservas (
    id int PRIMARY KEY AUTO_INCREMENT,
    profile_id bigint,
    entrenamiento_id bigint,
    fecha datetime,
    estado varchar(50),
    asistencia boolean DEFAULT false,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamientos(id)
);

CREATE TABLE graficas (
    id int PRIMARY KEY AUTO_INCREMENT,
    seccion varchar(255),
    nivel int,
    Mes int,
    año int,
    profile_id bigint,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
);

CREATE TABLE evaluaciones_rendimiento (
    id int PRIMARY KEY AUTO_INCREMENT,
    profile_id bigint,
    entrenamiento_id bigint,
    fecha_evaluacion date,
    puntuacion int,
    comentarios text,
    areas_mejora text,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamientos(id)
);

CREATE TABLE images (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    image_url varchar(255),
    imageable_type varchar(255),
    imageable_id bigint,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    deleted_at timestamp NULL
);

ALTER TABLE horarios_pista 
ADD CONSTRAINT fk_horarios_entrenamiento 
FOREIGN KEY (entrenamiento_id) REFERENCES entrenamientos(id);