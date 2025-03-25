CREATE TABLE incidencias (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    usuario_id bigint NOT NULL,
    tipo varchar(50) NOT NULL,
    descripcion text NOT NULL,
    metodo_pago varchar(50),
    referencia_externa varchar(255),
    status varchar(20) NOT NULL DEFAULT 'pending',
    fecha_incidencia datetime NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);