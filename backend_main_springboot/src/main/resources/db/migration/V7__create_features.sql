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

CREATE TABLE organizaciones (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  nombre varchar(191) NOT NULL UNIQUE,
  slug varchar(191) UNIQUE NOT NULL,
  tipo varchar(100),
  vision varchar(500),
  logo varchar(255),
  descripcion text,
  mision varchar(500),
  impacto_social varchar(500),
  sitio_web varchar(255),
  contacto_email varchar(255),
  contacto_tlf varchar(50),
  direccion varchar(255),
  ciudad varchar(100),
  pais varchar(100),
  colaboracion_descripcion text,
  fecha_inicio_colaboracion date,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp,
  deleted_at timestamp NULL
);