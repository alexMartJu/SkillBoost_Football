CREATE TABLE profile_suscripciones (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    profile_id bigint NOT NULL,
    suscripcion_id bigint NOT NULL,
    fecha_inicio date,
    fecha_fin date,
    status varchar(50),
    metodo_pago varchar(50),
    ultimo_pago_id bigint NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (suscripcion_id) REFERENCES suscripciones(id)
);

CREATE TABLE pagos (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    profile_suscripcion_id bigint NOT NULL,
    monto decimal(10,2),
    metodo_pago varchar(50),
    status varchar(50),
    referencia_externa varchar(255) UNIQUE,
    fecha datetime,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (profile_suscripcion_id) REFERENCES profile_suscripciones(id)
);

ALTER TABLE profile_suscripciones 
ADD CONSTRAINT fk_profile_suscripciones_ultimo_pago
FOREIGN KEY (ultimo_pago_id) REFERENCES pagos(id);

CREATE TABLE profile_logros (
    profile_id bigint,
    logro_id int,
    fecha_logro timestamp DEFAULT CURRENT_TIMESTAMP,
    progreso_actual int,
    PRIMARY KEY (profile_id, logro_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (logro_id) REFERENCES logros(id)
);

CREATE TABLE reservas (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    profile_id bigint,
    entrenamiento_id bigint,
    fecha datetime,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamientos(id)
);

CREATE TABLE graficas (
    id bigint PRIMARY KEY AUTO_INCREMENT,
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