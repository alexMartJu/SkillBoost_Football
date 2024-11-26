DELETE FROM `horarios`;
DELETE FROM `deportes`;
DELETE FROM `pistas`;
DELETE FROM `entrenamientos`;
DELETE FROM `images`;
DELETE FROM `usuarios`;
DELETE FROM `entrenadores`;
DELETE FROM `admins`;
DELETE FROM `salas`;
DELETE FROM `reservas`;
DELETE FROM `graficas`;
DELETE FROM `favorites`;
DELETE FROM `follows`;
DELETE FROM `deporte_pista`;
DELETE FROM `horario_pista_reserva`;
DELETE FROM `entrenamiento_usuario`;

INSERT INTO `horarios` (`id`, `hora`) VALUES
(1, 'manaña'),
(2, 'mediodia'),
(3, 'tarde'),
(4, 'noche');

INSERT INTO `deportes` (`id`, `nombre`, `slug`, `created_at`, `updated_at`) VALUES
(1, 'Futbol', 'Futbol--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(2, 'Baloncesto', 'Baloncesto--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(3, 'Tenis', 'Tenis--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(4, 'Volleyball', 'Volleyball--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(5, 'Escalada', 'Escalada--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(6, 'Ciclismo', 'Ciclismo--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(7, 'Natación', 'Natación--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(8, 'Padel', 'Padel--123', '2024-11-17 14:33:24', '2024-11-17 14:33:24');

INSERT INTO `pistas` (`id`, `nombre`, `slug`, `created_at`, `updated_at`) VALUES
(1, 'Pista Atletismo', 'pista-atletismo', '2024-11-17 15:42:34', '2024-11-17 15:42:34'),
(2, 'Piscina olímpica', 'piscina-olimpica', '2024-11-17 15:42:57', '2024-11-17 15:42:57'),
(3, 'Rocódromo', 'rocodromo', '2024-11-17 15:42:57', '2024-11-17 15:42:57'),
(4, 'Pista de Padel', 'pista-padel', '2024-11-17 15:44:29', '2024-11-17 15:44:29'),
(5, 'Pista Ciclismo interior', 'pista-ciclismo-interior', '2024-11-17 15:44:29', '2024-11-17 15:44:29');

INSERT INTO `entrenamientos` (`id`, `nombre`, `slug`, `descripcion`, `dia`, `duracion`, `max_plazas`, `precio`, `deporte_id`, `horario_id`,`entrenador_id`, `created_at`, `updated_at`) VALUES
(1, 'Clase Básica A', 'clase-basica-a', 'Clase de iniciación al deporte A', 'Domingo', 60, 20, 15, 1, 1, 2,'2024-11-17 15:47:05', '2024-11-17 15:47:05'),
(2, 'Clase Avanzada B', 'clase-avanzada-b', 'Clase avanzada de deporte B', 'Martes', 90, 15, 30, 2, 2, 1,'2024-11-17 15:48:19', '2024-11-17 15:48:19'),
(3, 'Clase Intermedia C', 'clase-intermedia-c', 'Clase intermedia de deporte C', 'Jueves', 75, 25, 20, 3, 3, 3,'2024-11-17 15:48:19', '2024-11-17 15:48:19'),
(4, 'Clase Intensiva D', 'clase-intensiva-d', 'Clase intensiva de deporte D', 'Domingo', 120, 10, 40, 4, 4, 2,'2024-11-17 15:52:06', '2024-11-17 15:52:06'),
(5, 'Clase Adaptada E', 'clase-adaptada-e', 'Clase adaptada para personas con necesidades especiales', 'Miercoles', 60, 15, 25, 4, 2, 3,'2024-11-17 15:52:06', '2024-11-17 15:52:06');

INSERT INTO `images` (`image_url`, `imageable_type`, `imageable_id`) VALUES
('futbol.jpg', 'App\\Models\\Deporte', 1),
('baloncesto.jpg', 'App\\Models\\Deporte', 2),
('tenis.jpg', 'App\\Models\\Deporte', 3),
('volleyball.jpg', 'App\\Models\\Deporte', 4),
('escalada.webp', 'App\\Models\\Deporte', 5),
('ciclismo.jpg', 'App\\Models\\Deporte', 6),
('natacion.webp', 'App\\Models\\Deporte', 7),
('padel.png', 'App\\Models\\Deporte', 8),
('pista_atletismo.jpg', 'App\\Models\\Pista', 1),
('piscina.jpg', 'App\\Models\\Pista', 2),
('rocodromo.jpg', 'App\\Models\\Pista', 3),
('pista_padel.jpg', 'App\\Models\\Pista', 4),
('pista_ciclismo.jpg', 'App\\Models\\Pista', 5);

INSERT INTO `usuarios` (`id`, `nombre`, `apellidos`, `DNI`, `email`, `password`, `edad`) VALUES
(1, 'Juan', 'Pérez', '12345678A', 'juan.perez@example.com', 'password123', 30),
(2, 'Ana', 'García', '23456789B', 'ana.garcia@example.com', 'securepass', 25),
(3, 'Carlos', 'López', '34567890C', 'carlos.lopez@example.com', 'mypassword', 28);

INSERT INTO `entrenadores` (`id`, `nombre`, `apellidos`, `DNI`, `email`, `password`, `deporte_id`, `edad`) VALUES
(1, 'Laura', 'Martínez', '45678901D', 'laura.martinez@example.com', 'trainerpass1', 1, 35),
(2, 'Luis', 'Fernández', '56789012E', 'luis.fernandez@example.com', 'trainerpass2', 2, 40),
(3, 'María', 'Gómez', '67890123F', 'maria.gomez@example.com', 'trainerpass3', 3, 32);

INSERT INTO `admins` (`id`, `nombre`, `email`, `password`) VALUES
(1, 'Admin1', 'admin1@example.com', 'adminpass1');

INSERT INTO `salas` (`id`, `nombre`, `tamaño`, `ubicacion`, `entrenador_id`) VALUES
(1, 'Sala de Cardio', 50, 'Edificio A', 1),
(2, 'Sala de Pesas', 70, 'Edificio B', 2),
(3, 'Sala de Yoga', 30, 'Edificio C', 3);

INSERT INTO `reservas` (`id`, `info`, `usuario_id`, `status`) VALUES
(1, 'Reserva de pista de atletismo', 1, 'confirmada'),
(2, 'Reserva de piscina', 2, 'pendiente'),
(3, 'Reserva de rocódromo', 3, 'cancelada');

INSERT INTO `graficas` (`id`, `seccion`, `nivel`, `usuario_id`) VALUES
(1, 'Motivación', 70, 1),
(2, 'Agilidad', 60, 1),
(3, 'Velocidad', 50, 1),
(4, 'Aguante', 90, 1),
(5, 'Fuerza', 80, 1);

INSERT INTO `favorites` (`usuario_id`, `entrenamiento_id`) VALUES
(1, 2),
(2, 3),
(3, 4);

INSERT INTO `follows` (`follower_id`, `followed_id`) VALUES
(1, 2),
(2, 3),
(3, 1);

INSERT INTO `deporte_pista` (`deporte_id`, `pista_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2024-11-17 15:53:53', '2024-11-17 15:53:53'),
(1, 2, '2024-11-17 15:53:53', '2024-11-17 15:53:53'),
(2, 3, '2024-11-17 15:54:42', '2024-11-17 15:54:42'),
(2, 4, '2024-11-17 15:54:42', '2024-11-17 15:54:42'),
(3, 5, '2024-11-17 15:55:14', '2024-11-17 15:55:14'),
(4, 1, '2024-11-17 15:55:14', '2024-11-17 15:55:14'),
(5, 2, '2024-11-17 15:55:55', '2024-11-17 15:55:55');

INSERT INTO `horario_pista_reserva` (`horario_id`, `pista_id`, `reserva_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

INSERT INTO `entrenamiento_usuario` (`entrenamiento_id`, `usuario_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2024-11-17 15:53:53', '2024-11-17 15:53:53'),
(1, 2, '2024-11-17 15:53:53', '2024-11-17 15:53:53'),
(2, 3, '2024-11-17 15:54:42', '2024-11-17 15:54:42'),
(2, 2, '2024-11-17 15:54:42', '2024-11-17 15:54:42'),
(3, 1, '2024-11-17 15:55:14', '2024-11-17 15:55:14'),
(4, 2, '2024-11-17 15:55:14', '2024-11-17 15:55:14'),
(5, 2, '2024-11-17 15:55:55', '2024-11-17 15:55:55');