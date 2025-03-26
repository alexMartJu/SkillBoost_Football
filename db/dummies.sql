-- Roles and Permissions
INSERT INTO `roles` (`name`, `slug`, `description`) VALUES
('admin', 'admin', 'Rol para el administrador del sistema'),
('entrenador', 'entrenador', 'Rol de entrenador'),
('jugador', 'juagdor', 'Rol jugador normal mayor edad'),
('tutor', 'tutor', 'Rol para tutores de menores de edad'),
('jugador_social', 'jugador-social', 'Rol para jugadores sociales'),
('jugador_club', 'juagdor-club', 'Rol para jugadores de club');

INSERT INTO `permissions` (`name`, `slug`, `description`) VALUES
('manage_users', 'manage-users', 'Can manage all users'),
('manage_trainings', 'manage-trainings', 'Can manage training sessions');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES
(1, 1), (1, 2), (2, 2);

-- Users and Profiles ($2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42 es password123)
INSERT INTO `usuarios` (`email`, `password`, `tipo_usuario`) VALUES
('admin@test.com', '$2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42', 'admin'),
('coach@test.com', '$2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42', 'entrenador'),
('player@test.com', '$2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42', 'tutor'),
('alex@gmail.com', '$2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42', 'jugador'),
('oscar@gmail.com', '$2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42', 'jugador_club'),
('adelia@gmail.com', '$2a$10$SMfqxEM/2FecKFTaEo4.x.qiW17blXKZ7il08ZUr3IIc3gc.ld/42', 'jugador_social');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1), (2, 2), (3, 3), (4,3), (5, 6), (6, 5);

INSERT INTO `profiles` (`id`, `nombre`, `apellidos`, `image`, `edad`, `es_menor`, `numeroEntrenador`, `especialidad`, `numerosocio`) VALUES
(1, 'Admin', 'System', 'https://static.productionready.io/images/smiley-cyrus.jpg', 30, false, NULL, NULL, 'ADM001'),
(2, 'Juan', 'Coach', 'https://static.productionready.io/images/smiley-cyrus.jpg', 35, false, 'COACH001', 'Técnica Individual', 'COA001'),
(3, 'Pedro', 'Juan', 'https://static.productionready.io/images/smiley-cyrus.jpg', 16, true, NULL, NULL, 'PLA001'),
(4, 'Alex', 'Martinez', 'https://static.productionready.io/images/smiley-cyrus.jpg', 19, false, NULL, NULL, '12345678'),
(5, 'Oscar', 'Martinez', 'https://static.productionready.io/images/smiley-cyrus.jpg', 17, true, NULL, NULL, '23456789'),
(6, 'Adelia', 'Campos', 'https://static.productionready.io/images/smiley-cyrus.jpg', 9, true, NULL, NULL, '34567890');

-- Tecnificaciones (ACTUALIZADO)
INSERT INTO `tecnificaciones` (`nombre`, `slug`, `descripcion`) VALUES
('Técnica Individual', 'tecnica-individual', 'Desarrollo de habilidades técnicas individuales fundamentales para el control y manejo del balón'),
('Táctica Posicional', 'tactica-posicional', 'Entrenamiento enfocado en el posicionamiento y movimientos tácticos dentro del campo'),
('Físico-Técnica', 'fisico-tecnica', 'Combinación de entrenamiento físico con elementos técnicos para mejorar el rendimiento global'),
('Específica por Posición', 'especifica-posicion', 'Entrenamiento especializado según la posición del jugador en el campo'),
('Mental-Táctica', 'mental-tactica', 'Desarrollo de aspectos mentales y tácticos para mejorar la toma de decisiones y lectura del juego');


-- Subtipos de Tecnificación (ACTUALIZADO con nuevos campos)
INSERT INTO `subtipo_tecnificacion` (
    `tecnificacion_id`, 
    `nombre`, 
    `slug`, 
    `descripcion`,
    `tipo_entrenamiento`,
    `objetivos`,
    `beneficios`,
    `frecuencia_sugerida`,
    `metodo_evaluacion`,
    `tecnologia_utilizada`
) VALUES
-- Técnica Individual
(1, 'Control y dominio del balón', 'control-dominio-balon', 
'Ejercicios para mejorar el control y dominio del balón en diferentes situaciones de juego',
'Práctico-repetitivo', 
'Mejorar la recepción, amortiguación y control del balón con todas las superficies de contacto',
'Mayor confianza con el balón, reducción de pérdidas y mejor capacidad para mantener la posesión bajo presión',
'3 veces por semana', 
'Circuito técnico cronometrado y análisis de video',
'Sensores de presión en balones inteligentes y análisis de video HD'),

(1, 'Regate y conducción', 'regate-conduccion', 
'Entrenamiento de técnicas de regate y conducción para superar adversarios',
'Técnico-táctico', 
'Desarrollar habilidad para superar rivales en situaciones de 1vs1 y conducir el balón a diferentes velocidades',
'Mayor capacidad de desborde, mejor protección del balón y creación de ventajas numéricas',
'2 veces por semana', 
'Test de conducción con obstáculos y situaciones de juego real',
'Conos inteligentes con sensores y cámaras de alta velocidad'),

(1, 'Pase y recepción', 'pase-recepcion', 
'Perfeccionamiento de la técnica de pase y recepción a diferentes distancias',
'Técnico-cooperativo', 
'Mejorar la precisión, potencia y timing de los pases, así­ como la calidad de recepción',
'Mayor fluidez en el juego colectivo, reducción de errores en la entrega y mejor conexión entre lí­neas',
'3-4 veces por semana', 
'Análisis estadí­stico de precisión y test de pases bajo presión',
'Radar de velocidad y sistemas de tracking de precisión'),

(1, 'Tiro a puerta', 'tiro-puerta', 
'Técnicas avanzadas de finalización y remate a porterí­a',
'Técnico-finalización', 
'Perfeccionar la técnica de golpeo, mejorar la precisión y potencia en los remates',
'Mayor efectividad en zona de finalización, variedad en los tipos de remate y mejor toma de decisiones',
'2 veces por semana', 
'Estadí­sticas de conversión y análisis biomecánico del golpeo',
'Porterí­a con sensores de impacto y análisis de trayectoria'),

(1, 'Técnica de cabeza', 'tecnica-cabeza', 
'Desarrollo de la técnica de juego aéreo tanto defensivo como ofensivo',
'Técnico-especí­fico', 
'Mejorar el timing, la potencia y la dirección en el juego de cabeza',
'Mayor dominio del juego aéreo, mejor defensa en balones altos y mayor peligro en acciones a balón parado',
'1-2 veces por semana', 
'Test de precisión y potencia en remates de cabeza',
'Balones especiales de peso variable y análisis de salto vertical'),

-- Táctica Posicional
(2, 'Posicionamiento defensivo', 'posicionamiento-defensivo', 
'Entrenamiento de posicionamiento y movimientos defensivos individuales y colectivos',
'Táctico-defensivo', 
'Mejorar la colocación, anticipación y coordinación defensiva del equipo',
'Mayor solidez defensiva, reducción de espacios entre lí­neas y mejor presión tras pérdida',
'2 veces por semana', 
'Análisis táctico de partidos y simulaciones de situaciones defensivas',
'Software de análisis táctico y pizarras electrónicas'),

(2, 'Movimientos ofensivos', 'movimientos-ofensivos', 
'Trabajo de desmarques, apoyos y movimientos para generar superioridades',
'Táctico-ofensivo', 
'Desarrollar patrones de movimiento ofensivo para crear y aprovechar espacios',
'Mayor fluidez ofensiva, creación de espacios y mejor aprovechamiento de oportunidades',
'2-3 veces por semana', 
'Análisis de movimientos sin balón y creación de ocasiones',
'GPS de seguimiento y software de análisis de patrones de movimiento'),

(2, 'Lectura de juego', 'lectura-juego', 
'Desarrollo de la capacidad de interpretación de situaciones de juego',
'Cognitivo-táctico', 
'Mejorar la anticipación, interpretación y toma de decisiones durante el juego',
'Mejor anticipación a las jugadas, reducción de tiempo de reacción y mayor eficacia táctica',
'2 veces por semana', 
'Test de reconocimiento de patrones y simulaciones tácticas',
'Realidad virtual y simuladores de situaciones de juego'),

(2, 'Transiciones ataque-defensa', 'transiciones-ataque-defensa', 
'Entrenamiento especí­fico de las fases de transición ofensiva y defensiva',
'Táctico-transicional', 
'Optimizar los momentos de cambio de posesión para sacar ventaja o minimizar riesgos',
'Mayor efectividad en contraataques, mejor reacción tras pérdida y aprovechamiento de desajustes',
'2 veces por semana', 
'Análisis de tiempos de reacción y efectividad en transiciones',
'Cronómetros de precisión y análisis de video en tiempo real'),

(2, 'Juego en equipo', 'juego-equipo', 
'Desarrollo de la coordinación y sincronización entre jugadores',
'Táctico-colectivo', 
'Mejorar la comunicación, sincronización y entendimiento entre los jugadores',
'Mayor cohesión táctica, mejor funcionamiento colectivo y adaptación a diferentes contextos',
'3 veces por semana', 
'Análisis de rendimiento colectivo y ejercicios de coordinación',
'Software de análisis de interacciones entre jugadores'),

-- Fí­sico-Técnica
(3, 'Velocidad con balón', 'velocidad-balon', 
'Entrenamiento de la velocidad de desplazamiento y ejecución con balón',
'Fí­sico-técnico', 
'Mejorar la velocidad de conducción, cambios de ritmo y ejecución técnica a alta velocidad',
'Mayor capacidad para ejecutar acciones técnicas a máxima velocidad sin pérdida de precisión',
'2 veces por semana', 
'Test de velocidad con balón y análisis de rendimiento técnico a alta intensidad',
'Células fotoeléctricas y sensores de movimiento'),

(3, 'Resistencia especí­fica', 'resistencia-especifica', 
'Desarrollo de la resistencia aplicada a acciones técnicas repetidas',
'Fí­sico-condicional', 
'Mantener la calidad técnica bajo condiciones de fatiga y mejorar la recuperación',
'Mayor rendimiento técnico en los minutos finales y mejor recuperación entre esfuerzos',
'2-3 veces por semana', 
'Test de resistencia especí­fica y monitoreo de frecuencia cardí­aca',
'Pulsómetros y sistemas de análisis de lactato'),

(3, 'Coordinación', 'coordinacion', 
'Ejercicios para mejorar la coordinación neuromuscular aplicada al fútbol',
'Neuromuscular', 
'Desarrollar la coordinación óculo-pédica, el equilibrio y la agilidad con balón',
'Mayor precisión técnica, mejor adaptación a situaciones cambiantes y reducción de errores',
'3 veces por semana', 
'Circuitos de coordinación y test de precisión técnica',
'Escaleras de coordinación electrónicas y plataformas de equilibrio'),

(3, 'Agilidad', 'agilidad', 
'Entrenamiento de cambios de dirección y reacción con balón',
'Fí­sico-reactivo', 
'Mejorar la capacidad de cambio de dirección, reacción y adaptación con balón',
'Mayor capacidad para superar rivales, mejor reacción ante estí­mulos y adaptación al entorno',
'2 veces por semana', 
'Test de agilidad reactiva y análisis de tiempos de reacción',
'Sistemas de luces reactivas y plataformas de presión'),

(3, 'Potencia de disparo', 'potencia-disparo', 
'Desarrollo de la fuerza aplicada al golpeo y remate',
'Fí­sico-técnico especí­fico', 
'Aumentar la potencia de golpeo manteniendo la precisión técnica',
'Mayor capacidad de finalización desde media y larga distancia y mejor ejecución de balones parados',
'1-2 veces por semana', 
'Medición de velocidad de disparo y análisis biomecánico',
'Radar de velocidad y análisis de video de alta velocidad'),

-- Especí­fica por Posición
(4, 'Porteros', 'porteros', 
'Entrenamiento especí­fico para guardametas',
'Técnico-especí­fico posicional', 
'Desarrollar habilidades especí­ficas de portero: blocajes, despejes, salidas y juego con los pies',
'Mayor seguridad bajo palos, mejor juego aéreo y capacidad para iniciar el juego desde atrás',
'3-4 veces por semana', 
'Análisis de intervenciones y test especí­ficos de portero',
'Máquinas de disparo programables y análisis de posicionamiento'),

(4, 'Defensas', 'defensas', 
'Entrenamiento especializado para jugadores de la lí­nea defensiva',
'Técnico-táctico posicional', 
'Mejorar el marcaje, la anticipación, el juego aéreo y la salida de balón',
'Mayor solidez defensiva, mejor lectura del juego y capacidad para iniciar ataques',
'3 veces por semana', 
'Análisis de duelos defensivos y test de capacidad de anticipación',
'Software de análisis de posicionamiento y simuladores tácticos'),

(4, 'Centrocampistas', 'centrocampistas', 
'Entrenamiento especí­fico para jugadores de medio campo',
'Técnico-táctico posicional', 
'Desarrollar la visión de juego, el pase, la recuperación y la llegada a segunda lí­nea',
'Mayor control del juego, mejor distribución del balón y equilibrio entre ataque y defensa',
'3 veces por semana', 
'Análisis de pases completados, recuperaciones y participación en el juego',
'Software de análisis de interacciones y mapas de calor'),

(4, 'Delanteros', 'delanteros', 
'Entrenamiento especializado para atacantes',
'Técnico-táctico ofensivo', 
'Perfeccionar la definición, el desmarque, el juego de espaldas y la presión alta',
'Mayor efectividad en zona de finalización, mejor movimiento sin balón y capacidad para generar espacios',
'3 veces por semana', 
'Estadí­sticas de conversión y análisis de movimientos ofensivos',
'Sensores de precisión en disparos y análisis de posicionamiento ofensivo'),

-- Mental-Táctica
(5, 'Toma de decisiones', 'toma-decisiones', 
'Desarrollo de la capacidad para elegir la mejor opción en cada situación',
'Cognitivo-práctico', 
'Mejorar la velocidad y calidad de las decisiones bajo presión',
'Mejor resolución de situaciones complejas, reducción de errores y mayor eficacia en el juego',
'2 veces por semana', 
'Simulaciones de juego con decisiones múltiples y análisis de elecciones',
'Realidad virtual y software de simulación de escenarios'),

(5, 'Visión de juego', 'vision-juego', 
'Entrenamiento para ampliar el campo visual y la percepción del juego',
'Perceptivo-cognitivo', 
'Desarrollar la capacidad de ver más allá del balón y anticipar movimientos',
'Mayor capacidad para detectar compañeros mejor posicionados y anticipar movimientos de rivales',
'2-3 veces por semana', 
'Test de reconocimiento de patrones y ejercicios de visión periférica',
'Gafas de restricción visual y software de seguimiento ocular'),

(5, 'Inteligencia espacial', 'inteligencia-espacial', 
'Desarrollo de la comprensión y utilización de los espacios',
'Cognitivo-espacial', 
'Mejorar la interpretación de espacios libres y ocupados durante el juego',
'Mejor aprovechamiento de espacios, creación de superioridades y optimización del posicionamiento',
'2 veces por semana', 
'Análisis de ocupación de espacios y creación de ventajas posicionales',
'Software de análisis espacial y mapas térmicos de ocupación'),

(5, 'Concentración', 'concentracion', 
'Entrenamiento para mantener el foco mental durante todo el partido',
'Psicológico-atencional', 
'Desarrollar la capacidad de mantener la atención y evitar distracciones',
'Mayor consistencia en el rendimiento, reducción de errores por desatención y mejor gestión de momentos crí­ticos',
'3 veces por semana', 
'Test de atención sostenida y ejercicios de concentración bajo presión',
'Neurofeedback y aplicaciones de entrenamiento mental'),

(5, 'Liderazgo', 'liderazgo', 
'Desarrollo de habilidades de comunicación y liderazgo en el campo',
'Psicológico-social', 
'Mejorar la capacidad de influir positivamente en compañeros y gestionar situaciones de presión',
'Mejor cohesión de equipo, comunicación más efectiva y mayor resiliencia ante la adversidad',
'1-2 veces por semana', 
'Evaluación de comportamientos de liderazgo y feedback de compañeros',
'Sistemas de comunicación en tiempo real y análisis de interacciones verbales');

-- Facilities
INSERT INTO `pistas` (`nombre`, `slug`, `descripcion`, `tipo`, `dimensiones`, `capacidad`) VALUES
('Campo Principal', 'campo-principal', 'Campo de fútbol 11 con césped natural, ideal para partidos oficiales y entrenamientos avanzados.', 'Césped Natural - Fútbol 11', '105m x 68m', 5000),
('Campo Auxiliar', 'campo-auxiliar', 'Campo de fútbol 11 con césped artificial, utilizado para entrenamientos y partidos amistosos.', 'Césped Artificial - Fútbol 11', '105m x 68m', 3000),
('Mini Estadio', 'mini-estadio', 'Campo de fútbol 7 con césped natural, perfecto para entrenamientos de categorías inferiores.', 'Césped Natural - Fútbol 7', '65m x 45m', 1500),
('Campo de Entrenamiento 1', 'campo-entrenamiento-1', 'Campo de fútbol 7 con césped artificial, utilizado para sesiones de entrenamiento y torneos.', 'Césped Artificial - Fútbol 7', '65m x 45m', 1000),
('Campo de Entrenamiento 2', 'campo-entrenamiento-2', 'Campo de fútbol 7 con césped artificial, equipado con iluminación para entrenamientos nocturnos.', 'Césped Artificial - Fútbol 7', '65m x 45m', 1000),
('Campo de Reserva', 'campo-reserva', 'Campo de fútbol 11 con césped natural, reservado para eventos especiales y partidos de exhibición.', 'Césped Natural - Fútbol 11', '105m x 68m', 4000);

-- Subscriptions and Payments
INSERT INTO `suscripciones` 
(`nombre`, `slug`, `precio`, `entrenamientos_totales`, `incluye_graficas`, `incluye_evaluacion`,
`ventaja1`, `ventaja2`, `ventaja3`, `ventaja4`, `created_at`, `updated_at`) 
VALUES 
('Formación Inicial', 'formacion-inicial', 49.99, 8, FALSE, FALSE, 
'8 entrenamientos mensuales', 'Acceso a logros y recompensas', NULL, NULL, NOW(), NOW()),
('Desarrollo Avanzado', 'desarrollo-avanzado', 79.99, 12, FALSE, TRUE, 
'12 entrenamientos mensuales', 'Acceso a logros y recompensas', 'Evaluaciones de rendimiento personalizadas', NULL, NOW(), NOW()),
('Élite Profesional', 'elite-profesional', 119.99, 16, TRUE, TRUE, 
'16 entrenamientos mensuales', 'Acceso a logros y recompensas', 'Evaluaciones de rendimiento personalizadas', 'Análisis gráfico de evolución técnica', NOW(), NOW());

-- Trainings (Sin pista_id y horario_id)
INSERT INTO `entrenamientos` (`nombre`, `slug`, `descripcion`, `nivel`, `edad_minima`, `edad_maxima`, `tecnificacion_id`, `subtipo_tecnificacion_id`, `entrenador_id`, `max_plazas`, `objetivos`, `equipamiento_necesario`, `duracion_minutos`) VALUES
('Control y Dominio de Balón', 'control-dominio-balon', 'Entrenamiento enfocado en mejorar el control y dominio del balón, incluyendo técnicas de dribbling y manejo bajo presión.', 'Principiante', 4, 13, 1, 1, 2, 20, 'Mejorar el control del balón y la capacidad de dribbling.', 'Balones, conos, marcadores', 90),
('Posicionamiento Defensivo', 'posicionamiento-defensivo', 'Entrenamiento para mejorar el posicionamiento defensivo, incluyendo tácticas de marcaje y anticipación.', 'Avanzado', 14, 20, 2, 6, 2, 15, 'Optimizar el posicionamiento defensivo y la capacidad de anticipación.', 'Conos, chalecos, balones', 75),
('Potencia de Tiro', 'potencia-tiro', 'Entrenamiento para aumentar la potencia y precisión del tiro, incluyendo ejercicios de fuerza y técnica de disparo.', 'Intermedio', 16, 22, 3, 15, 2, 18, 'Incrementar la potencia y precisión en el tiro.', 'Balones, porterías, pesas', 60),
('Centrocampistas', 'centrocampistas', 'Entrenamiento especializado para centrocampistas, enfocándose en la visión de juego, pases y control del medio campo.', 'Avanzado', 15, 21, 4, 18, 2, 20, 'Mejorar la visión de juego y la capacidad de control del medio campo.', 'Balones, conos, chalecos', 90),
('Visión de Juego', 'vision-juego', 'Entrenamiento para mejorar la visión de juego, incluyendo ejercicios de toma de decisiones y pases estratégicos.', 'Intermedio', 13, 19, 5, 21, 2, 20, 'Desarrollar la visión de juego y la capacidad de toma de decisiones.', 'Balones, conos, marcadores', 75),
('Técnica de Cabeza', 'tecnica-cabeza', 'Entrenamiento para perfeccionar la técnica de cabeceo, incluyendo ejercicios de salto y precisión.', 'Principiante', 12, 18, 1, 5, 2, 15, 'Mejorar la técnica de cabeceo y la precisión en el juego aéreo.', 'Balones, porterías, colchonetas', 60),
('Transiciones Ataque-Defensa', 'transiciones-ataque-defensa', 'Entrenamiento para mejorar las transiciones rápidas entre ataque y defensa, incluyendo tácticas de contraataque.', 'Avanzado', 14, 20, 2, 9, 2, 18, 'Optimizar las transiciones rápidas y la capacidad de contraataque.', 'Balones, conos, chalecos', 90);

-- Schedule and Assignments (Ahora con entrenamientos asignados)
INSERT INTO `horarios_pista` (`pista_id`, `fecha_inicio`, `fecha_fin`, `entrenamiento_id`, `created_at`) VALUES
(1, '2025-07-15 09:00:00', '2025-07-15 10:30:00', 1, NOW()),
(2, '2025-07-16 10:00:00', '2025-07-16 11:15:00', 2, NOW()),
(3, '2025-07-17 11:00:00', '2025-07-17 12:00:00', 3, NOW()),
(4, '2025-07-18 09:30:00', '2025-07-18 11:00:00', 4, NOW()),
(5, '2025-07-19 10:30:00', '2025-07-19 11:45:00', 5, NOW()),
(6, '2025-07-20 09:00:00', '2025-07-20 10:00:00', 6, NOW()),
(1, '2025-07-21 11:00:00', '2025-07-21 12:30:00', 7, NOW());

-- Achievements
INSERT INTO logros (nombre, slug, descripcion, requisito_entrenamientos, nivel_dificultad, recompensa, created_at, updated_at)
VALUES 
('Principiante Dedicado', 'principiante-dedicado', 'Has completado 10 entrenamientos. Â¡Estás en el camino correcto!', 10, 'Fácil', 'Camiseta oficial de SkillBoost Football', NOW(), NOW()),
('Jugador Comprometido', 'jugador-comprometido', 'Has completado 25 entrenamientos. Tu dedicación es admirable.', 25, 'Medio', 'Balón de fútbol profesional', NOW(), NOW()),
('Maestro del Entrenamiento', 'maestro-entrenamiento', 'Has completado 50 entrenamientos. Â¡Eres un verdadero profesional!', 50, 'Difí­cil', 'Botas de fútbol profesionales con logo de SkillBoost Football', NOW(), NOW());

-- Images
INSERT INTO `images` (`image_url`, `imageable_type`, `imageable_id`) VALUES
('campo1.jpg', 'Pista', 1),
('campo1_1.jpg', 'Pista', 1),
('campo2.jpg', 'Pista', 2),
('campo2_2.jpg', 'Pista', 2),
('campo3.jpg', 'Pista', 3),
('campo3_3.jpg', 'Pista', 3),
('campo4.jpg', 'Pista', 4),
('campo5.jpg', 'Pista', 5),
('campo6.jpg', 'Pista', 6),
('control_balon.jpg', 'SubtipoTecnificacion', 1),
('regate.jpg', 'SubtipoTecnificacion', 2),
('pase.jpg', 'SubtipoTecnificacion', 3),
('tiro.jpg', 'SubtipoTecnificacion', 4),
('cabeza.jpg', 'SubtipoTecnificacion', 5),
('posicionamiento_defensivo.jpg', 'SubtipoTecnificacion', 6),
('mov_ofensivos.jpg', 'SubtipoTecnificacion', 7),
('lectura_juego.jpg', 'SubtipoTecnificacion', 8),
('trans_ataque_defensa.jpg', 'SubtipoTecnificacion', 9),
('juego_equipo.jpg', 'SubtipoTecnificacion', 10),
('vel_balon.jpg', 'SubtipoTecnificacion', 11),
('resistencia.jpg', 'SubtipoTecnificacion', 12),
('coordinacion.jpg', 'SubtipoTecnificacion', 13),
('agilidad.jpg', 'SubtipoTecnificacion', 14),
('pot_tiro.jpg', 'SubtipoTecnificacion', 15),
('porteros.jpg', 'SubtipoTecnificacion', 16),
('defensa.jpg', 'SubtipoTecnificacion', 17),
('centrocampista.jpg', 'SubtipoTecnificacion', 18),
('delanteros.jpg', 'SubtipoTecnificacion', 19),
('toma_decisiones.jpg', 'SubtipoTecnificacion', 20),
('vision_juego.jpg', 'SubtipoTecnificacion', 21),
('int_espacial.jpg', 'SubtipoTecnificacion', 22),
('concentracion.jpg', 'SubtipoTecnificacion', 23),
('liderazgo.jpg', 'SubtipoTecnificacion', 24),
('tec_individual.jpg', 'Tecnificacion', 1),
('tac_posicional.jpg', 'Tecnificacion', 2),
('fisico_tecnica.jpg', 'Tecnificacion', 3),
('posicion.jpg', 'Tecnificacion', 4),
('mental_tactica.jpg', 'Tecnificacion', 5);


-- Blacklist Tokens
INSERT INTO `blacklist_token` (`usuario_id`, `refresh_token`) VALUES
(1, 'expired_token_1'),
(2, 'expired_token_2');

-- NUEVO: Profile Suscripciones (tabla nueva)
INSERT INTO `profile_suscripciones` 
(`profile_id`, `suscripcion_id`, `fecha_inicio`, `fecha_fin`, `status`, `metodo_pago`, `created_at`) 
VALUES
(3, 1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 'active', 'stripe', NOW());

-- CAMBIADO: Pagos (nueva estructura)
INSERT INTO `pagos` 
(`profile_suscripcion_id`, `monto`, `metodo_pago`, `status`, `referencia_externa`, `fecha`, `created_at`) 
VALUES
(1, 49.99, 'stripe', 'completed', 'payment_stripe_123456', NOW(), NOW());

-- ACTUALIZACIí“N: Vincular el último pago con la suscripción
UPDATE `profile_suscripciones` SET `ultimo_pago_id` = 1 WHERE `id` = 1;

-- Organizaciones sociales con las que colabora SkillBoost Football
INSERT INTO `organizaciones` (
    `nombre`, 
    `slug`, 
    `tipo`, 
    `vision`, 
    `logo`, 
    `descripcion`, 
    `mision`, 
    `impacto_social`, 
    `sitio_web`, 
    `contacto_email`, 
    `contacto_tlf`, 
    `direccion`, 
    `ciudad`, 
    `pais`, 
    `colaboracion_descripcion`, 
    `fecha_inicio_colaboracion`, 
    `created_at`, 
    `updated_at`
) VALUES 
(
    'Fundación ONAT', 
    'fundacion-onat', 
    'Fundación Deportiva', 
    'Un mundo donde todos los niños tengan acceso a oportunidades deportivas independientemente de su situación socioeconómica', 
    'https://onatfoundation.org/wp-content/uploads/2021/06/img_logo_OnatFundation_colorVerde.png', 
    'La Fundación ONAT trabaja para promover la inclusión social a través del deporte, especialmente el fútbol. Creada por deportistas profesionales, busca transformar la vida de niños en situación de vulnerabilidad ofreciéndoles un espacio seguro donde desarrollar valores y habilidades para la vida a través del deporte.',
    'Transformar la vida de niños y jóvenes en riesgo de exclusión social a través de programas deportivos que fomenten valores como el trabajo en equipo, la disciplina y el respeto', 
    'Más de 5,000 niños beneficiados en sus programas deportivos, reducción del 40% en abandono escolar entre los participantes y mejora significativa en habilidades sociales y autoestima de los jóvenes participantes', 
    'https://onatfoundation.org', 
    'contacto@onatfoundation.org', 
    '+34 911 234 567', 
    'Calle del Deporte 15', 
    'Madrid', 
    'España', 
    'SkillBoost Football proporciona entrenadores voluntarios y acceso gratuito a tecnificación avanzada para 50 niños seleccionados por la Fundación ONAT. Además, organizamos torneos conjuntos donde los participantes de ambas organizaciones comparten experiencias y aprendizajes, creando un entorno de inclusión real.', 
    '2023-06-15', 
    NOW(), 
    NOW()
),
(
    'Fundación Emalcsa', 
    'fundacion-emalcsa', 
    'Fundación Municipal', 
    'Una sociedad donde el deporte sea un vehí­culo de transformación social y desarrollo comunitario', 
    'https://fundacionemalcsa.org/wp-content/uploads/2017/12/cropped-logo-fundacion-emalcsa.png', 
    'La Fundación Emalcsa, a través de su programa Deporte Solidario, apoya proyectos que utilizan el deporte como herramienta de inclusión social. Trabajan especialmente con colectivos vulnerables, incluyendo niños en riesgo de exclusión, personas con discapacidad y comunidades desfavorecidas, promoviendo valores como la igualdad, la solidaridad y el respeto.',
    'Fomentar la inclusión social y la igualdad de oportunidades a través de programas deportivos accesibles para todos, con especial atención a colectivos vulnerables', 
    'Implementación de más de 30 proyectos deportivos solidarios, beneficiando a más de 3,000 personas en situación vulnerable anualmente y creación de redes comunitarias de apoyo a través del deporte', 
    'https://fundacionemalcsa.org', 
    'info@fundacionemalcsa.org', 
    '+34 981 154 080', 
    'Plaza de Marí­a Pita 1', 
    'A Coruña', 
    'España', 
    'Nuestra colaboración con Fundación Emalcsa incluye la creación de un programa especí­fico de tecnificación para niños de barrios desfavorecidos de A Coruña. SkillBoost Football aporta la metodologí­a y los recursos técnicos, mientras que Emalcsa facilita las instalaciones y la captación de participantes. El programa ha demostrado mejorar no solo las habilidades futbolí­sticas sino también el rendimiento académico y la integración social de los participantes.', 
    '2022-09-01', 
    NOW(), 
    NOW()
),
(
    'Fundación Laureus Sport for Good', 
    'fundacion-laureus', 
    'Fundación Internacional', 
    'Un mundo donde todos los jóvenes tengan acceso al poder transformador del deporte para superar la violencia, la discriminación y las desventajas', 
    'https://uploads.concordia.net/2020/09/16135557/Laureus.png', 
    'Laureus Sport for Good utiliza el poder del deporte para ayudar a los jóvenes a superar problemas como la violencia, la discriminación y las desventajas. Con el respaldo de los mejores atletas del mundo, la fundación apoya programas deportivos comunitarios que han demostrado tener un impacto positivo en la vida de los jóvenes, abordando problemas sociales urgentes.',
    'Utilizar el poder del deporte como herramienta para cambiar la vida de los jóvenes y ayudarles a superar limitaciones impuestas por desafí­os sociales', 
    'Más de 6 millones de jóvenes impactados en más de 40 paí­ses, reducción significativa de comportamientos de riesgo entre participantes y mejora en indicadores de salud mental y fí­sica', 
    'http://laureus.com/sport-for-good', 
    'contact@laureus.org', 
    '+44 20 7514 2762', 
    '30 Cecil Street', 
    'Londres', 
    'Reino Unido', 
    'SkillBoost Football es socio implementador oficial de Laureus en España, aplicando su metodologí­a "Sport for Development" en nuestros programas para jóvenes en riesgo de exclusión. Nuestros entrenadores reciben formación especí­fica de Laureus para maximizar el impacto social del fútbol, y juntos desarrollamos métricas de evaluación que demuestran cómo el deporte puede transformar vidas. Anualmente, organizamos el "Laureus SkillBoost Cup", un torneo internacional que reúne a jóvenes de diferentes programas Laureus.', 
    '2021-03-10', 
    NOW(), 
    NOW()
),
(
    'Fundación Real Madrid', 
    'fundacion-real-madrid', 
    'Fundación Deportiva', 
    'Un mundo donde el deporte sea accesible para todos como herramienta educativa y de integración social', 
    'https://www.pnguniverse.com/wp-content/uploads/2020/08/fundacion-real-madrid-1280x472.png', 
    'La Fundación Real Madrid utiliza los valores inherentes al deporte y, en particular, al fútbol, como herramienta educativa y de integración social. Desarrolla proyectos en más de 80 paí­ses, beneficiando a miles de niños y jóvenes en situación vulnerable, utilizando la metodologí­a "Por una Educación REAL: Valores y Deporte", que combina la práctica deportiva con la educación en valores.',
    'Fomentar los valores del deporte y su papel como herramienta educativa para contribuir al desarrollo integral de la personalidad de quienes lo practican', 
    'Más de 100,000 beneficiarios en todo el mundo, presencia en más de 80 paí­ses con escuelas sociodeportivas y reducción de la desigualdad a través del acceso al deporte de calidad', 
    'https://www.realmadrid.com/es-ES/fundacion', 
    'fundacion@realmadrid.org', 
    '+34 91 453 29 00', 
    'Av. de las Fuerzas Armadas 402', 
    'Madrid', 
    'España', 
    'Nuestra colaboración con la Fundación Real Madrid se centra en la implementación de su metodologí­a "Por una Educación REAL" en nuestros programas de tecnificación para niños en exclusión social. SkillBoost Football ha adaptado esta metodologí­a para enfocarse en aspectos técnicos avanzados, manteniendo el énfasis en valores. Además, facilitamos que entrenadores de la Fundación Real Madrid impartan masterclasses en nuestras instalaciones, y nuestros jugadores más destacados tienen la oportunidad de participar en campus organizados por la Fundación.', 
    '2022-01-20', 
    NOW(), 
    NOW()
),
(
    'UPS Foundation', 
    'ups-foundation', 
    'Fundación Corporativa', 
    'Comunidades más equitativas y resilientes a través del poder de la colaboración público-privada', 
    'https://www.ups.com/assets/resources/images/UPS_logo.svg', 
    'La Fundación UPS dirige los esfuerzos de ciudadaní­a global de UPS, centrándose en cuatro áreas: diversidad, equidad e inclusión; justicia económica y empoderamiento; desarrollo comunitario local; y respuesta a crisis humanitarias. A través de su colaboración con la Fundación Real Madrid, apoya programas deportivos para niños y jóvenes en riesgo de exclusión social.',
    'Liderar programas de impacto social que generen un cambio positivo y sostenible en las comunidades donde UPS opera', 
    'Más de $70 millones en donaciones anuales a organizaciones sin fines de lucro, 20 millones de horas de voluntariado desde 1982 y apoyo a más de 4,300 organizaciones en todo el mundo', 
    'https://about.ups.com/es/es/social-impact/the-ups-foundation.html', 
    'foundation@ups.com', 
    '+1 404 828 6000', 
    '55 Glenlake Parkway NE', 
    'Atlanta', 
    'Estados Unidos', 
    'SkillBoost Football colabora con UPS Foundation en la logí­stica de nuestro programa "Balones Sin Fronteras", que lleva equipamiento deportivo a comunidades desfavorecidas. UPS proporciona transporte gratuito para enviar material deportivo donado a escuelas y comunidades con recursos limitados. Además, empleados de UPS participan como voluntarios en nuestros eventos comunitarios, aportando su experiencia en organización y logí­stica. Esta colaboración nos permite ampliar significativamente nuestro alcance e impacto social.', 
    '2023-10-05', 
    NOW(), 
    NOW()
);