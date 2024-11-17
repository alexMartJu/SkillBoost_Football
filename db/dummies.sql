INSERT INTO `deportes` (`deportes_id`, `nombre`, `slug`, `created_at`, `updated_at`) VALUES
(1, 'voluptas voluptatem', 'voluptas-voluptatem', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(2, 'error dolores', 'error-dolores', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(3, 'et sed', 'et-sed', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(4, 'velit consequatur', 'velit-consequatur', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(5, 'laborum maxime', 'laborum-maxime', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(6, 'facere aut', 'facere-aut', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(7, 'et consequuntur', 'et-consequuntur', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(8, 'debitis consequatur', 'debitis-consequatur', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(9, 'temporibus aperiam', 'temporibus-aperiam', '2024-11-17 14:33:24', '2024-11-17 14:33:24'),
(10, 'enim nulla', 'enim-nulla', '2024-11-17 14:33:24', '2024-11-17 14:33:24');

INSERT INTO `pistas` (`pistas_id`, `nombre`, `slug`, `created_at`, `updated_at`) VALUES
(1, 'Pista Central', 'pista-central', '2024-11-17 15:42:34', '2024-11-17 15:42:34'),
(2, 'Pista Norte', 'pista-norte', '2024-11-17 15:42:57', '2024-11-17 15:42:57'),
(3, 'Pista Sur', 'pista-sur', '2024-11-17 15:42:57', '2024-11-17 15:42:57'),
(4, 'Pista Este', 'pista-este', '2024-11-17 15:44:29', '2024-11-17 15:44:29'),
(5, 'Pista Oeste', 'pista-oeste', '2024-11-17 15:42:57', '2024-11-17 15:42:57');

INSERT INTO `deportes_pistas` (`deportes_id`, `pistas_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2024-11-17 15:53:53', '2024-11-17 15:53:53'),
(1, 2, '2024-11-17 15:53:53', '2024-11-17 15:53:53'),
(2, 3, '2024-11-17 15:54:42', '2024-11-17 15:54:42'),
(2, 4, '2024-11-17 15:54:42', '2024-11-17 15:54:42'),
(3, 5, '2024-11-17 15:55:14', '2024-11-17 15:55:14'),
(4, 1, '2024-11-17 15:55:14', '2024-11-17 15:55:14'),
(5, 2, '2024-11-17 15:55:55', '2024-11-17 15:55:55');

INSERT INTO `clases` (`deportes_id`, `id`, `nombre`, `slug`, `descripcion`, `duracion`, `max_plazas`, `precio`, `created_at`, `updated_at`) VALUES
(1, 1, 'Clase Básica A', 'clase-basica-a', 'Clase de iniciación al deporte A', 60, 20, 15, '2024-11-17 15:47:05', '2024-11-17 15:47:05'),
(2, 2, 'Clase Avanzada B', 'clase-avanzada-b', 'Clase avanzada de deporte B', 90, 15, 30, '2024-11-17 15:48:19', '2024-11-17 15:48:19'),
(3, 3, 'Clase Intermedia C', 'clase-intermedia-c', 'Clase intermedia de deporte C', 75, 25, 20, '2024-11-17 15:48:19', '2024-11-17 15:48:19'),
(4, 4, 'Clase Intensiva D', 'clase-intensiva-d', 'Clase intensiva de deporte D', 120, 10, 40, '2024-11-17 15:52:06', '2024-11-17 15:52:06'),
(5, 5, 'Clase Adaptada E', 'clase-adaptada-e', 'Clase adaptada para personas con necesidades especiales', 60, 15, 25, '2024-11-17 15:52:06', '2024-11-17 15:52:06');