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
(1, 1), (2, 2), (3, 3);

INSERT INTO `profiles` (`id`, `nombre`, `apellidos`, `image`, `edad`, `es_menor`, `numeroEntrenador`, `especialidad`, `numerosocio`) VALUES
(1, 'Admin', 'System', 'https://static.productionready.io/images/smiley-cyrus.jpg', 30, false, NULL, NULL, 'ADM001'),
(2, 'Juan', 'Coach', 'https://static.productionready.io/images/smiley-cyrus.jpg', 35, false, 'COACH001', 'TÃ©cnica Individual', 'COA001'),
(3, 'Pedro', 'Juan', 'https://static.productionready.io/images/smiley-cyrus.jpg', 16, true, NULL, NULL, 'PLA001'),
(1, 'Alex', 'Martinez', 'https://static.productionready.io/images/smiley-cyrus.jpg', 19, false, NULL, NULL, '12345678'),
(2, 'Oscar', 'Martinez', 'https://static.productionready.io/images/smiley-cyrus.jpg', 17, true, NULL, NULL, '23456789'),
(3, 'Adelia', 'Campos', 'https://static.productionready.io/images/smiley-cyrus.jpg', 9, true, NULL, NULL, '34567890');

-- Tecnificaciones (ACTUALIZADO)
INSERT INTO `tecnificaciones` (`nombre`, `slug`, `descripcion`) VALUES
('TÃ©cnica Individual', 'tecnica-individual', 'Desarrollo de habilidades tÃ©cnicas individuales fundamentales para el control y manejo del balÃ³n'),
('TÃ¡ctica Posicional', 'tactica-posicional', 'Entrenamiento enfocado en el posicionamiento y movimientos tÃ¡cticos dentro del campo'),
('FÃ­sico-TÃ©cnica', 'fisico-tecnica', 'CombinaciÃ³n de entrenamiento fÃ­sico con elementos tÃ©cnicos para mejorar el rendimiento global'),
('EspecÃ­fica por PosiciÃ³n', 'especifica-posicion', 'Entrenamiento especializado segÃºn la posiciÃ³n del jugador en el campo'),
('Mental-TÃ¡ctica', 'mental-tactica', 'Desarrollo de aspectos mentales y tÃ¡cticos para mejorar la toma de decisiones y lectura del juego');


-- Subtipos de TecnificaciÃ³n (ACTUALIZADO con nuevos campos)
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
-- TÃ©cnica Individual
(1, 'Control y dominio del balÃ³n', 'control-dominio-balon', 
'Ejercicios para mejorar el control y dominio del balÃ³n en diferentes situaciones de juego',
'PrÃ¡ctico-repetitivo', 
'Mejorar la recepciÃ³n, amortiguaciÃ³n y control del balÃ³n con todas las superficies de contacto',
'Mayor confianza con el balÃ³n, reducciÃ³n de pÃ©rdidas y mejor capacidad para mantener la posesiÃ³n bajo presiÃ³n',
'3 veces por semana', 
'Circuito tÃ©cnico cronometrado y anÃ¡lisis de video',
'Sensores de presiÃ³n en balones inteligentes y anÃ¡lisis de video HD'),

(1, 'Regate y conducciÃ³n', 'regate-conduccion', 
'Entrenamiento de tÃ©cnicas de regate y conducciÃ³n para superar adversarios',
'TÃ©cnico-tÃ¡ctico', 
'Desarrollar habilidad para superar rivales en situaciones de 1vs1 y conducir el balÃ³n a diferentes velocidades',
'Mayor capacidad de desborde, mejor protecciÃ³n del balÃ³n y creaciÃ³n de ventajas numÃ©ricas',
'2 veces por semana', 
'Test de conducciÃ³n con obstÃ¡culos y situaciones de juego real',
'Conos inteligentes con sensores y cÃ¡maras de alta velocidad'),

(1, 'Pase y recepciÃ³n', 'pase-recepcion', 
'Perfeccionamiento de la tÃ©cnica de pase y recepciÃ³n a diferentes distancias',
'TÃ©cnico-cooperativo', 
'Mejorar la precisiÃ³n, potencia y timing de los pases, asÃ­ como la calidad de recepciÃ³n',
'Mayor fluidez en el juego colectivo, reducciÃ³n de errores en la entrega y mejor conexiÃ³n entre lÃ­neas',
'3-4 veces por semana', 
'AnÃ¡lisis estadÃ­stico de precisiÃ³n y test de pases bajo presiÃ³n',
'Radar de velocidad y sistemas de tracking de precisiÃ³n'),

(1, 'Tiro a puerta', 'tiro-puerta', 
'TÃ©cnicas avanzadas de finalizaciÃ³n y remate a porterÃ­a',
'TÃ©cnico-finalizaciÃ³n', 
'Perfeccionar la tÃ©cnica de golpeo, mejorar la precisiÃ³n y potencia en los remates',
'Mayor efectividad en zona de finalizaciÃ³n, variedad en los tipos de remate y mejor toma de decisiones',
'2 veces por semana', 
'EstadÃ­sticas de conversiÃ³n y anÃ¡lisis biomecÃ¡nico del golpeo',
'PorterÃ­a con sensores de impacto y anÃ¡lisis de trayectoria'),

(1, 'TÃ©cnica de cabeza', 'tecnica-cabeza', 
'Desarrollo de la tÃ©cnica de juego aÃ©reo tanto defensivo como ofensivo',
'TÃ©cnico-especÃ­fico', 
'Mejorar el timing, la potencia y la direcciÃ³n en el juego de cabeza',
'Mayor dominio del juego aÃ©reo, mejor defensa en balones altos y mayor peligro en acciones a balÃ³n parado',
'1-2 veces por semana', 
'Test de precisiÃ³n y potencia en remates de cabeza',
'Balones especiales de peso variable y anÃ¡lisis de salto vertical'),

-- TÃ¡ctica Posicional
(2, 'Posicionamiento defensivo', 'posicionamiento-defensivo', 
'Entrenamiento de posicionamiento y movimientos defensivos individuales y colectivos',
'TÃ¡ctico-defensivo', 
'Mejorar la colocaciÃ³n, anticipaciÃ³n y coordinaciÃ³n defensiva del equipo',
'Mayor solidez defensiva, reducciÃ³n de espacios entre lÃ­neas y mejor presiÃ³n tras pÃ©rdida',
'2 veces por semana', 
'AnÃ¡lisis tÃ¡ctico de partidos y simulaciones de situaciones defensivas',
'Software de anÃ¡lisis tÃ¡ctico y pizarras electrÃ³nicas'),

(2, 'Movimientos ofensivos', 'movimientos-ofensivos', 
'Trabajo de desmarques, apoyos y movimientos para generar superioridades',
'TÃ¡ctico-ofensivo', 
'Desarrollar patrones de movimiento ofensivo para crear y aprovechar espacios',
'Mayor fluidez ofensiva, creaciÃ³n de espacios y mejor aprovechamiento de oportunidades',
'2-3 veces por semana', 
'AnÃ¡lisis de movimientos sin balÃ³n y creaciÃ³n de ocasiones',
'GPS de seguimiento y software de anÃ¡lisis de patrones de movimiento'),

(2, 'Lectura de juego', 'lectura-juego', 
'Desarrollo de la capacidad de interpretaciÃ³n de situaciones de juego',
'Cognitivo-tÃ¡ctico', 
'Mejorar la anticipaciÃ³n, interpretaciÃ³n y toma de decisiones durante el juego',
'Mejor anticipaciÃ³n a las jugadas, reducciÃ³n de tiempo de reacciÃ³n y mayor eficacia tÃ¡ctica',
'2 veces por semana', 
'Test de reconocimiento de patrones y simulaciones tÃ¡cticas',
'Realidad virtual y simuladores de situaciones de juego'),

(2, 'Transiciones ataque-defensa', 'transiciones-ataque-defensa', 
'Entrenamiento especÃ­fico de las fases de transiciÃ³n ofensiva y defensiva',
'TÃ¡ctico-transicional', 
'Optimizar los momentos de cambio de posesiÃ³n para sacar ventaja o minimizar riesgos',
'Mayor efectividad en contraataques, mejor reacciÃ³n tras pÃ©rdida y aprovechamiento de desajustes',
'2 veces por semana', 
'AnÃ¡lisis de tiempos de reacciÃ³n y efectividad en transiciones',
'CronÃ³metros de precisiÃ³n y anÃ¡lisis de video en tiempo real'),

(2, 'Juego en equipo', 'juego-equipo', 
'Desarrollo de la coordinaciÃ³n y sincronizaciÃ³n entre jugadores',
'TÃ¡ctico-colectivo', 
'Mejorar la comunicaciÃ³n, sincronizaciÃ³n y entendimiento entre los jugadores',
'Mayor cohesiÃ³n tÃ¡ctica, mejor funcionamiento colectivo y adaptaciÃ³n a diferentes contextos',
'3 veces por semana', 
'AnÃ¡lisis de rendimiento colectivo y ejercicios de coordinaciÃ³n',
'Software de anÃ¡lisis de interacciones entre jugadores'),

-- FÃ­sico-TÃ©cnica
(3, 'Velocidad con balÃ³n', 'velocidad-balon', 
'Entrenamiento de la velocidad de desplazamiento y ejecuciÃ³n con balÃ³n',
'FÃ­sico-tÃ©cnico', 
'Mejorar la velocidad de conducciÃ³n, cambios de ritmo y ejecuciÃ³n tÃ©cnica a alta velocidad',
'Mayor capacidad para ejecutar acciones tÃ©cnicas a mÃ¡xima velocidad sin pÃ©rdida de precisiÃ³n',
'2 veces por semana', 
'Test de velocidad con balÃ³n y anÃ¡lisis de rendimiento tÃ©cnico a alta intensidad',
'CÃ©lulas fotoelÃ©ctricas y sensores de movimiento'),

(3, 'Resistencia especÃ­fica', 'resistencia-especifica', 
'Desarrollo de la resistencia aplicada a acciones tÃ©cnicas repetidas',
'FÃ­sico-condicional', 
'Mantener la calidad tÃ©cnica bajo condiciones de fatiga y mejorar la recuperaciÃ³n',
'Mayor rendimiento tÃ©cnico en los minutos finales y mejor recuperaciÃ³n entre esfuerzos',
'2-3 veces por semana', 
'Test de resistencia especÃ­fica y monitoreo de frecuencia cardÃ­aca',
'PulsÃ³metros y sistemas de anÃ¡lisis de lactato'),

(3, 'CoordinaciÃ³n', 'coordinacion', 
'Ejercicios para mejorar la coordinaciÃ³n neuromuscular aplicada al fÃºtbol',
'Neuromuscular', 
'Desarrollar la coordinaciÃ³n Ã³culo-pÃ©dica, el equilibrio y la agilidad con balÃ³n',
'Mayor precisiÃ³n tÃ©cnica, mejor adaptaciÃ³n a situaciones cambiantes y reducciÃ³n de errores',
'3 veces por semana', 
'Circuitos de coordinaciÃ³n y test de precisiÃ³n tÃ©cnica',
'Escaleras de coordinaciÃ³n electrÃ³nicas y plataformas de equilibrio'),

(3, 'Agilidad', 'agilidad', 
'Entrenamiento de cambios de direcciÃ³n y reacciÃ³n con balÃ³n',
'FÃ­sico-reactivo', 
'Mejorar la capacidad de cambio de direcciÃ³n, reacciÃ³n y adaptaciÃ³n con balÃ³n',
'Mayor capacidad para superar rivales, mejor reacciÃ³n ante estÃ­mulos y adaptaciÃ³n al entorno',
'2 veces por semana', 
'Test de agilidad reactiva y anÃ¡lisis de tiempos de reacciÃ³n',
'Sistemas de luces reactivas y plataformas de presiÃ³n'),

(3, 'Potencia de disparo', 'potencia-disparo', 
'Desarrollo de la fuerza aplicada al golpeo y remate',
'FÃ­sico-tÃ©cnico especÃ­fico', 
'Aumentar la potencia de golpeo manteniendo la precisiÃ³n tÃ©cnica',
'Mayor capacidad de finalizaciÃ³n desde media y larga distancia y mejor ejecuciÃ³n de balones parados',
'1-2 veces por semana', 
'MediciÃ³n de velocidad de disparo y anÃ¡lisis biomecÃ¡nico',
'Radar de velocidad y anÃ¡lisis de video de alta velocidad'),

-- EspecÃ­fica por PosiciÃ³n
(4, 'Porteros', 'porteros', 
'Entrenamiento especÃ­fico para guardametas',
'TÃ©cnico-especÃ­fico posicional', 
'Desarrollar habilidades especÃ­ficas de portero: blocajes, despejes, salidas y juego con los pies',
'Mayor seguridad bajo palos, mejor juego aÃ©reo y capacidad para iniciar el juego desde atrÃ¡s',
'3-4 veces por semana', 
'AnÃ¡lisis de intervenciones y test especÃ­ficos de portero',
'MÃ¡quinas de disparo programables y anÃ¡lisis de posicionamiento'),

(4, 'Defensas', 'defensas', 
'Entrenamiento especializado para jugadores de la lÃ­nea defensiva',
'TÃ©cnico-tÃ¡ctico posicional', 
'Mejorar el marcaje, la anticipaciÃ³n, el juego aÃ©reo y la salida de balÃ³n',
'Mayor solidez defensiva, mejor lectura del juego y capacidad para iniciar ataques',
'3 veces por semana', 
'AnÃ¡lisis de duelos defensivos y test de capacidad de anticipaciÃ³n',
'Software de anÃ¡lisis de posicionamiento y simuladores tÃ¡cticos'),

(4, 'Centrocampistas', 'centrocampistas', 
'Entrenamiento especÃ­fico para jugadores de medio campo',
'TÃ©cnico-tÃ¡ctico posicional', 
'Desarrollar la visiÃ³n de juego, el pase, la recuperaciÃ³n y la llegada a segunda lÃ­nea',
'Mayor control del juego, mejor distribuciÃ³n del balÃ³n y equilibrio entre ataque y defensa',
'3 veces por semana', 
'AnÃ¡lisis de pases completados, recuperaciones y participaciÃ³n en el juego',
'Software de anÃ¡lisis de interacciones y mapas de calor'),

(4, 'Delanteros', 'delanteros', 
'Entrenamiento especializado para atacantes',
'TÃ©cnico-tÃ¡ctico ofensivo', 
'Perfeccionar la definiciÃ³n, el desmarque, el juego de espaldas y la presiÃ³n alta',
'Mayor efectividad en zona de finalizaciÃ³n, mejor movimiento sin balÃ³n y capacidad para generar espacios',
'3 veces por semana', 
'EstadÃ­sticas de conversiÃ³n y anÃ¡lisis de movimientos ofensivos',
'Sensores de precisiÃ³n en disparos y anÃ¡lisis de posicionamiento ofensivo'),

-- Mental-TÃ¡ctica
(5, 'Toma de decisiones', 'toma-decisiones', 
'Desarrollo de la capacidad para elegir la mejor opciÃ³n en cada situaciÃ³n',
'Cognitivo-prÃ¡ctico', 
'Mejorar la velocidad y calidad de las decisiones bajo presiÃ³n',
'Mejor resoluciÃ³n de situaciones complejas, reducciÃ³n de errores y mayor eficacia en el juego',
'2 veces por semana', 
'Simulaciones de juego con decisiones mÃºltiples y anÃ¡lisis de elecciones',
'Realidad virtual y software de simulaciÃ³n de escenarios'),

(5, 'VisiÃ³n de juego', 'vision-juego', 
'Entrenamiento para ampliar el campo visual y la percepciÃ³n del juego',
'Perceptivo-cognitivo', 
'Desarrollar la capacidad de ver mÃ¡s allÃ¡ del balÃ³n y anticipar movimientos',
'Mayor capacidad para detectar compaÃ±eros mejor posicionados y anticipar movimientos de rivales',
'2-3 veces por semana', 
'Test de reconocimiento de patrones y ejercicios de visiÃ³n perifÃ©rica',
'Gafas de restricciÃ³n visual y software de seguimiento ocular'),

(5, 'Inteligencia espacial', 'inteligencia-espacial', 
'Desarrollo de la comprensiÃ³n y utilizaciÃ³n de los espacios',
'Cognitivo-espacial', 
'Mejorar la interpretaciÃ³n de espacios libres y ocupados durante el juego',
'Mejor aprovechamiento de espacios, creaciÃ³n de superioridades y optimizaciÃ³n del posicionamiento',
'2 veces por semana', 
'AnÃ¡lisis de ocupaciÃ³n de espacios y creaciÃ³n de ventajas posicionales',
'Software de anÃ¡lisis espacial y mapas tÃ©rmicos de ocupaciÃ³n'),

(5, 'ConcentraciÃ³n', 'concentracion', 
'Entrenamiento para mantener el foco mental durante todo el partido',
'PsicolÃ³gico-atencional', 
'Desarrollar la capacidad de mantener la atenciÃ³n y evitar distracciones',
'Mayor consistencia en el rendimiento, reducciÃ³n de errores por desatenciÃ³n y mejor gestiÃ³n de momentos crÃ­ticos',
'3 veces por semana', 
'Test de atenciÃ³n sostenida y ejercicios de concentraciÃ³n bajo presiÃ³n',
'Neurofeedback y aplicaciones de entrenamiento mental'),

(5, 'Liderazgo', 'liderazgo', 
'Desarrollo de habilidades de comunicaciÃ³n y liderazgo en el campo',
'PsicolÃ³gico-social', 
'Mejorar la capacidad de influir positivamente en compaÃ±eros y gestionar situaciones de presiÃ³n',
'Mejor cohesiÃ³n de equipo, comunicaciÃ³n mÃ¡s efectiva y mayor resiliencia ante la adversidad',
'1-2 veces por semana', 
'EvaluaciÃ³n de comportamientos de liderazgo y feedback de compaÃ±eros',
'Sistemas de comunicaciÃ³n en tiempo real y anÃ¡lisis de interacciones verbales');

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
('FormaciÃ³n Inicial', 'formacion-inicial', 49.99, 8, FALSE, FALSE, 
'8 entrenamientos mensuales', 'Acceso a logros y recompensas', NULL, NULL, NOW(), NOW()),
('Desarrollo Avanzado', 'desarrollo-avanzado', 79.99, 12, FALSE, TRUE, 
'12 entrenamientos mensuales', 'Acceso a logros y recompensas', 'Evaluaciones de rendimiento personalizadas', NULL, NOW(), NOW()),
('Ã‰lite Profesional', 'elite-profesional', 119.99, 16, TRUE, TRUE, 
'16 entrenamientos mensuales', 'Acceso a logros y recompensas', 'Evaluaciones de rendimiento personalizadas', 'AnÃ¡lisis grÃ¡fico de evoluciÃ³n tÃ©cnica', NOW(), NOW());

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


-- Performance Tracking
INSERT INTO `graficas` (`seccion`, `nivel`, `Mes`, `aÃ±o`, `profile_id`) VALUES
('MotivaciÃ³n', 70, 1, 2024, 3),
('Agilidad', 60, 1, 2024, 3),
('Velocidad', 50, 1, 2024, 3),
('Aguante', 90, 1, 2024, 3),
('Fuerza', 80, 1, 2024, 3),
('MotivaciÃ³n', 80, 2, 2024, 3),
('Agilidad', 70, 2, 2024, 3),
('Velocidad', 60, 2, 2024, 3),
('Aguante', 95, 2, 2024, 3),
('Fuerza', 85, 2, 2024, 3),
('MotivaciÃ³n', 50, 1, 2025, 3),
('Agilidad', 60, 1, 2025, 3),
('Velocidad', 50, 1, 2025, 3),
('Aguante', 70, 1, 2025, 3),
('Fuerza', 60, 1, 2025, 3),
('MotivaciÃ³n', 70, 2, 2025, 3),
('Agilidad', 70, 2, 2025, 3),
('Velocidad', 55, 2, 2025, 3),
('Aguante', 90, 2, 2025, 3),
('Fuerza', 80, 2, 2025, 3);

-- Achievements
INSERT INTO logros (nombre, slug, descripcion, requisito_entrenamientos, nivel_dificultad, recompensa, created_at, updated_at)
VALUES 
('Principiante Dedicado', 'principiante-dedicado', 'Has completado 10 entrenamientos. Â¡EstÃ¡s en el camino correcto!', 10, 'FÃ¡cil', 'Camiseta oficial de SkillBoost Football', NOW(), NOW()),
('Jugador Comprometido', 'jugador-comprometido', 'Has completado 25 entrenamientos. Tu dedicaciÃ³n es admirable.', 25, 'Medio', 'BalÃ³n de fÃºtbol profesional', NOW(), NOW()),
('Maestro del Entrenamiento', 'maestro-entrenamiento', 'Has completado 50 entrenamientos. Â¡Eres un verdadero profesional!', 50, 'DifÃ­cil', 'Botas de fÃºtbol profesionales con logo de SkillBoost Football', NOW(), NOW());

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

-- ACTUALIZACIÃ“N: Vincular el Ãºltimo pago con la suscripciÃ³n
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
    'FundaciÃ³n ONAT', 
    'fundacion-onat', 
    'FundaciÃ³n Deportiva', 
    'Un mundo donde todos los niÃ±os tengan acceso a oportunidades deportivas independientemente de su situaciÃ³n socioeconÃ³mica', 
    'https://onatfoundation.org/wp-content/uploads/2021/06/img_logo_OnatFundation_colorVerde.png', 
    'La FundaciÃ³n ONAT trabaja para promover la inclusiÃ³n social a travÃ©s del deporte, especialmente el fÃºtbol. Creada por deportistas profesionales, busca transformar la vida de niÃ±os en situaciÃ³n de vulnerabilidad ofreciÃ©ndoles un espacio seguro donde desarrollar valores y habilidades para la vida a travÃ©s del deporte.',
    'Transformar la vida de niÃ±os y jÃ³venes en riesgo de exclusiÃ³n social a travÃ©s de programas deportivos que fomenten valores como el trabajo en equipo, la disciplina y el respeto', 
    'MÃ¡s de 5,000 niÃ±os beneficiados en sus programas deportivos, reducciÃ³n del 40% en abandono escolar entre los participantes y mejora significativa en habilidades sociales y autoestima de los jÃ³venes participantes', 
    'https://onatfoundation.org', 
    'contacto@onatfoundation.org', 
    '+34 911 234 567', 
    'Calle del Deporte 15', 
    'Madrid', 
    'EspaÃ±a', 
    'SkillBoost Football proporciona entrenadores voluntarios y acceso gratuito a tecnificaciÃ³n avanzada para 50 niÃ±os seleccionados por la FundaciÃ³n ONAT. AdemÃ¡s, organizamos torneos conjuntos donde los participantes de ambas organizaciones comparten experiencias y aprendizajes, creando un entorno de inclusiÃ³n real.', 
    '2023-06-15', 
    NOW(), 
    NOW()
),
(
    'FundaciÃ³n Emalcsa', 
    'fundacion-emalcsa', 
    'FundaciÃ³n Municipal', 
    'Una sociedad donde el deporte sea un vehÃ­culo de transformaciÃ³n social y desarrollo comunitario', 
    'https://fundacionemalcsa.org/wp-content/uploads/2017/12/cropped-logo-fundacion-emalcsa.png', 
    'La FundaciÃ³n Emalcsa, a travÃ©s de su programa Deporte Solidario, apoya proyectos que utilizan el deporte como herramienta de inclusiÃ³n social. Trabajan especialmente con colectivos vulnerables, incluyendo niÃ±os en riesgo de exclusiÃ³n, personas con discapacidad y comunidades desfavorecidas, promoviendo valores como la igualdad, la solidaridad y el respeto.',
    'Fomentar la inclusiÃ³n social y la igualdad de oportunidades a travÃ©s de programas deportivos accesibles para todos, con especial atenciÃ³n a colectivos vulnerables', 
    'ImplementaciÃ³n de mÃ¡s de 30 proyectos deportivos solidarios, beneficiando a mÃ¡s de 3,000 personas en situaciÃ³n vulnerable anualmente y creaciÃ³n de redes comunitarias de apoyo a travÃ©s del deporte', 
    'https://fundacionemalcsa.org', 
    'info@fundacionemalcsa.org', 
    '+34 981 154 080', 
    'Plaza de MarÃ­a Pita 1', 
    'A CoruÃ±a', 
    'EspaÃ±a', 
    'Nuestra colaboraciÃ³n con FundaciÃ³n Emalcsa incluye la creaciÃ³n de un programa especÃ­fico de tecnificaciÃ³n para niÃ±os de barrios desfavorecidos de A CoruÃ±a. SkillBoost Football aporta la metodologÃ­a y los recursos tÃ©cnicos, mientras que Emalcsa facilita las instalaciones y la captaciÃ³n de participantes. El programa ha demostrado mejorar no solo las habilidades futbolÃ­sticas sino tambiÃ©n el rendimiento acadÃ©mico y la integraciÃ³n social de los participantes.', 
    '2022-09-01', 
    NOW(), 
    NOW()
),
(
    'FundaciÃ³n Laureus Sport for Good', 
    'fundacion-laureus', 
    'FundaciÃ³n Internacional', 
    'Un mundo donde todos los jÃ³venes tengan acceso al poder transformador del deporte para superar la violencia, la discriminaciÃ³n y las desventajas', 
    'https://uploads.concordia.net/2020/09/16135557/Laureus.png', 
    'Laureus Sport for Good utiliza el poder del deporte para ayudar a los jÃ³venes a superar problemas como la violencia, la discriminaciÃ³n y las desventajas. Con el respaldo de los mejores atletas del mundo, la fundaciÃ³n apoya programas deportivos comunitarios que han demostrado tener un impacto positivo en la vida de los jÃ³venes, abordando problemas sociales urgentes.',
    'Utilizar el poder del deporte como herramienta para cambiar la vida de los jÃ³venes y ayudarles a superar limitaciones impuestas por desafÃ­os sociales', 
    'MÃ¡s de 6 millones de jÃ³venes impactados en mÃ¡s de 40 paÃ­ses, reducciÃ³n significativa de comportamientos de riesgo entre participantes y mejora en indicadores de salud mental y fÃ­sica', 
    'http://laureus.com/sport-for-good', 
    'contact@laureus.org', 
    '+44 20 7514 2762', 
    '30 Cecil Street', 
    'Londres', 
    'Reino Unido', 
    'SkillBoost Football es socio implementador oficial de Laureus en EspaÃ±a, aplicando su metodologÃ­a "Sport for Development" en nuestros programas para jÃ³venes en riesgo de exclusiÃ³n. Nuestros entrenadores reciben formaciÃ³n especÃ­fica de Laureus para maximizar el impacto social del fÃºtbol, y juntos desarrollamos mÃ©tricas de evaluaciÃ³n que demuestran cÃ³mo el deporte puede transformar vidas. Anualmente, organizamos el "Laureus SkillBoost Cup", un torneo internacional que reÃºne a jÃ³venes de diferentes programas Laureus.', 
    '2021-03-10', 
    NOW(), 
    NOW()
),
(
    'FundaciÃ³n Real Madrid', 
    'fundacion-real-madrid', 
    'FundaciÃ³n Deportiva', 
    'Un mundo donde el deporte sea accesible para todos como herramienta educativa y de integraciÃ³n social', 
    'https://www.pnguniverse.com/wp-content/uploads/2020/08/fundacion-real-madrid-1280x472.png', 
    'La FundaciÃ³n Real Madrid utiliza los valores inherentes al deporte y, en particular, al fÃºtbol, como herramienta educativa y de integraciÃ³n social. Desarrolla proyectos en mÃ¡s de 80 paÃ­ses, beneficiando a miles de niÃ±os y jÃ³venes en situaciÃ³n vulnerable, utilizando la metodologÃ­a "Por una EducaciÃ³n REAL: Valores y Deporte", que combina la prÃ¡ctica deportiva con la educaciÃ³n en valores.',
    'Fomentar los valores del deporte y su papel como herramienta educativa para contribuir al desarrollo integral de la personalidad de quienes lo practican', 
    'MÃ¡s de 100,000 beneficiarios en todo el mundo, presencia en mÃ¡s de 80 paÃ­ses con escuelas sociodeportivas y reducciÃ³n de la desigualdad a travÃ©s del acceso al deporte de calidad', 
    'https://www.realmadrid.com/es-ES/fundacion', 
    'fundacion@realmadrid.org', 
    '+34 91 453 29 00', 
    'Av. de las Fuerzas Armadas 402', 
    'Madrid', 
    'EspaÃ±a', 
    'Nuestra colaboraciÃ³n con la FundaciÃ³n Real Madrid se centra en la implementaciÃ³n de su metodologÃ­a "Por una EducaciÃ³n REAL" en nuestros programas de tecnificaciÃ³n para niÃ±os en exclusiÃ³n social. SkillBoost Football ha adaptado esta metodologÃ­a para enfocarse en aspectos tÃ©cnicos avanzados, manteniendo el Ã©nfasis en valores. AdemÃ¡s, facilitamos que entrenadores de la FundaciÃ³n Real Madrid impartan masterclasses en nuestras instalaciones, y nuestros jugadores mÃ¡s destacados tienen la oportunidad de participar en campus organizados por la FundaciÃ³n.', 
    '2022-01-20', 
    NOW(), 
    NOW()
),
(
    'UPS Foundation', 
    'ups-foundation', 
    'FundaciÃ³n Corporativa', 
    'Comunidades mÃ¡s equitativas y resilientes a travÃ©s del poder de la colaboraciÃ³n pÃºblico-privada', 
    'https://www.ups.com/assets/resources/images/UPS_logo.svg', 
    'La FundaciÃ³n UPS dirige los esfuerzos de ciudadanÃ­a global de UPS, centrÃ¡ndose en cuatro Ã¡reas: diversidad, equidad e inclusiÃ³n; justicia econÃ³mica y empoderamiento; desarrollo comunitario local; y respuesta a crisis humanitarias. A travÃ©s de su colaboraciÃ³n con la FundaciÃ³n Real Madrid, apoya programas deportivos para niÃ±os y jÃ³venes en riesgo de exclusiÃ³n social.',
    'Liderar programas de impacto social que generen un cambio positivo y sostenible en las comunidades donde UPS opera', 
    'MÃ¡s de $70 millones en donaciones anuales a organizaciones sin fines de lucro, 20 millones de horas de voluntariado desde 1982 y apoyo a mÃ¡s de 4,300 organizaciones en todo el mundo', 
    'https://about.ups.com/es/es/social-impact/the-ups-foundation.html', 
    'foundation@ups.com', 
    '+1 404 828 6000', 
    '55 Glenlake Parkway NE', 
    'Atlanta', 
    'Estados Unidos', 
    'SkillBoost Football colabora con UPS Foundation en la logÃ­stica de nuestro programa "Balones Sin Fronteras", que lleva equipamiento deportivo a comunidades desfavorecidas. UPS proporciona transporte gratuito para enviar material deportivo donado a escuelas y comunidades con recursos limitados. AdemÃ¡s, empleados de UPS participan como voluntarios en nuestros eventos comunitarios, aportando su experiencia en organizaciÃ³n y logÃ­stica. Esta colaboraciÃ³n nos permite ampliar significativamente nuestro alcance e impacto social.', 
    '2023-10-05', 
    NOW(), 
    NOW()
);