CREATE TABLE roles (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) UNIQUE,
    slug varchar(255) UNIQUE,
    description text,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    deleted_at timestamp NULL
);

CREATE TABLE permissions (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) UNIQUE,
    slug varchar(255) UNIQUE,
    description text,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    deleted_at timestamp NULL
);

CREATE TABLE usuarios (
    id int PRIMARY KEY AUTO_INCREMENT,
    email varchar(255) UNIQUE,
    password varchar(255),
    tipo_usuario varchar(50),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    status varchar(50) DEFAULT 'active',
    deleted_at timestamp NULL
);
