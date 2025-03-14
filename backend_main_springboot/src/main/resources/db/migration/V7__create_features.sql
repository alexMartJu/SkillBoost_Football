CREATE TABLE notificaciones (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  id_usuario bigint NOT NULL,
  mensaje text NOT NULL,
  tipo_notificacion varchar(50) NOT NULL,
  isRead boolean DEFAULT false,
  createdAt timestamp DEFAULT CURRENT_TIMESTAMP,
  updatedAt timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);