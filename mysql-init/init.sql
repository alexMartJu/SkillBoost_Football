-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS ZenithZone;

-- Usar la base de datos recién creada o existente
USE ZenithZone;

-- Crear la tabla si no existe
CREATE TABLE IF NOT EXISTS Deportes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT
);
