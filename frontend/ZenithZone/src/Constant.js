export default {
    // Deporte
    INITIALIZE_DEPORTE: "InitializeDeporte",
    INITIALIZE_ONE_STATE_DEPORTE: "InitializeOneStateDeporte",
    DELETE_ONE_DEPORTE: "DeteleOneDeporte",
    UPDATE_ONE_DEPORTE: "UpdateOneDeporte",
    CREATE_ONE_DEPORTE: "CreateOneDeporte",
    FETCH_DEPORTE_BY_SLUG: "FetchDeporteBySlug",
    SET_CURRENT_DEPORTE: "SetCurrentDeporte",

    // Entrenamiento
    INITIALIZE_ENTRENAMIENTO: "InitializeEntrenamiento",
    INITIALIZE_ONE_STATE_ENTRENAMIENTO: "InitializeOneStateEntrenamiento",
    UPDATE_STATUS_ENTRENAMIENTO: "cambiarEstadoEntrenamiento",
    DELETE_ONE_ENTRENAMIENTO: "DeteleOneEntrenamiento",
    UPDATE_ONE_ENTRENAMIENTO: "UpdateOneEntrenamiento",
    CREATE_ONE_ENTRENAMIENTO: "CreateOneEntrenamiento",
    SET_CURRENT_ENTRENAMIENTO: "SetCurrentEntrenamiento",
    FETCH_ENTRENAMIENTOS_OCUPADOS: "FetchEntrenamientosOcupados",
    FETCH_SUSCRIBED_ENTRENAMIENTOS: "FetchSuscribedEntrenamientos",
    SET_SUSCRIBED_ENTRENAMIENTOS: "SetSuscribedEntrenamientos",

    // Pistas
    INITIALIZE_PISTA: "InitializePista",
    INITIALIZE_ONE_STATE_PISTA: "InitializeOneStatePista",
    DELETE_ONE_PISTA: "DeteleOnePista",
    UPDATE_ONE_PISTA: "UpdateOnePista",
    CREATE_ONE_PISTA: "CreateOnePista",
    FETCH_PISTA_BY_SLUG: "FetchPistaBySlug",
    SET_CURRENT_PISTA: "SetCurrentPista",

        // User
        INITIALIZE_PROFILE: "InitializeProfile",
        INITIALIZE_USER: "InitializeUser",
        INITIALIZE_ONE_USER: "InitializeOneUser",
        ADD_USER: "AddUser",
        DELETE_USER: "DeleteUser",
        UPDATE_USER: "UpdateUser",
        LOGIN: "Login",
        LOGIN_ADMIN: "Login_admin",
        LOGOUT: "Logout",
        //entrenadores
        INITIALIZE_ENTRENADOR: "initializeEntrenador",
        DELETE_ONE_ENTRENADOR: "deleteOneEntrenador",
        REGISTER_ENTRENADOR:"registerOneEntrenador",
        ADD_NEW_ENTRENADOR: "addOneEntrenador",

        //Salas
        INITIALIZE_SALAS: "InitializeSalas",
        CREATE_ONE_SALA: "CreateOneSala",
        DELETE_ONE_SALA: "DeleteOneSala",
        UPDATE_ONE_SALA: "UpdateOneSala",
        FETCH_SALA_BY_ID: "FetchSalaById",
        SET_CURRENT_SALA: "SetCurrentSala",

        //graficas
        INITIALIZE_GRAFICAS:"InitializeGraficas",
        UPDATE_ONE_GRAFICA: "UpdateOneGrafica",

    // Profile
    INITIALIZE_ENTRENAMIENTO_PROFILE: "initizalizeEntrenamientoProfile",
    INITIALIZE_RESERVA_PROFILE: "initizalizeReservaProfile",
    INITIALIZE_GRAFICA_PROFILE: "initizalizeGraficaProfile",

    //pistas privadas
  INITIALIZE_PISTA_PRIVADA: "InitializePistaPrivada",
  INITIALIZE_ONE_STATE_PISTA_PRIVADA: "InitializeOneStatePistaPrivada",
  DELETE_ONE_PISTA_PRIVADA: "DeleteOnePistaPrivada",
  UPDATE_ONE_PISTA_PRIVADA: "UpdateOnePistaPrivada",
  CREATE_ONE_PISTA_PRIVADA: "CreateOnePistaPrivada",
  FETCH_PISTA_PRIVADA_BY_SLUG: "FetchPistaPrivadaBySlug",
  SET_CURRENT_PISTA_PRIVADA: "SetCurrentPistaPrivada",

  INITIALIZE_RESERVA: "InitializeReservas",
  DELETE_ONE_RESERVA: "DeleteOneReserva",


  INITIALIZE_HORARIO: "InitializeHorario", 

  //Tecnificaciones
  INITIALIZE_TECNIFICACION: "InitializeTecnificacion",
  INITIALIZE_ONE_STATE_TECNIFICACION: "InitializeOneStateTecnificacion",
  DELETE_ONE_TECNIFICACION: "DeleteOneTecnificacion",
  UPDATE_ONE_TECNIFICACION: "UpdateOneTecnificacion",
  CREATE_ONE_TECNIFICACION: "CreateOneTecnificacion",
  FETCH_TECNIFICACION_BY_SLUG: "FetchTecnificacionBySlug",
  SET_CURRENT_TECNIFICACION: "SetCurrentTecnificacion",

  //Subtipos de Tecnificación
  INITIALIZE_SUBTIPO_TECNIFICACION: "InitializeSubtipoTecnificacion",
  INITIALIZE_ONE_STATE_SUBTIPO_TECNIFICACION: "InitializeOneStateSubtipoTecnificacion",
  DELETE_ONE_SUBTIPO_TECNIFICACION: "DeleteOneSubtipoTecnificacion",
  UPDATE_ONE_SUBTIPO_TECNIFICACION: "UpdateOneSubtipoTecnificacion",
  CREATE_ONE_SUBTIPO_TECNIFICACION: "CreateOneSubtipoTecnificacion",
  FETCH_SUBTIPO_TECNIFICACION_BY_SLUG: "FetchSubtipoTecnificacionBySlug",
  SET_CURRENT_SUBTIPO_TECNIFICACION: "SetCurrentSubtipoTecnificacion",

  //Organizaciones
  INITIALIZE_ORGANIZACION: "InitializeOrganizacion",
  CREATE_ONE_ORGANIZACION: "CreateOneOrganizacion",
  FETCH_ORGANIZACION_BY_SLUG: "FetchOrganizacionBySlug",
  SET_CURRENT_ORGANIZACION: "SetCurrentOrganizacion",

  //Suscripciones
  INITIALIZE_SUSCRIPCION: "InitializeSuscripcion",
  INITIALIZE_ONE_STATE_SUSCRIPCION: "InitializeOneStateSuscripcion",
  UPDATE_ONE_SUSCRIPCION: "UpdateOneSuscripcion",
  UPDATE_ONE_SUSCRIPCION_PRECIO: "UpdateOneSuscripcionPrecio",
  FETCH_SUSCRIPCION_BY_SLUG: "FetchSuscripcionBySlug",
  SET_CURRENT_SUSCRIPCION: "SetCurrentSuscripcion",

  //Notificaciones
  INITIALIZE_NOTIFICATIONS: "InitializeNotifications",
  INITIALIZE_UNREAD_BELL_NOTIFICATIONS: "InitializeUnreadBellNotifications",
  MARK_NOTIFICATION_AS_READ: "MarkNotificationAsRead",
  SET_CURRENT_NOTIFICATIONS: "SetCurrentNotifications",

  INITIALIZE_RESERVAS_ACTIVAS: 'INITIALIZE_RESERVAS_ACTIVAS',
  INITIALIZE_RESERVAS_EXPIRADAS: 'INITIALIZE_RESERVAS_EXPIRADAS',
  CANCELAR_ENTRENAMIENTO: 'CANCELAR_ENTRENAMIENTO',

  INITIALIZE_TECNIFICACION_ADMIN: "InitializeTecnificacionAdmin",
  INITIALIZE_ONE_STATE_TECNIFICACION_ADMIN: "InitializeOneStateTecnificacionAdmin",
  DELETE_ONE_TECNIFICACION_ADMIN: "DeleteOneTecnificacionAdmin",
  UPDATE_ONE_TECNIFICACION_ADMIN: "UpdateOneTecnificacionAdmin",
  CREATE_ONE_TECNIFICACION_ADMIN: "CreateOneTecnificacionAdmin",
  FETCH_TECNIFICACION_BY_SLUG_ADMIN: "FetchTecnificacionBySlugAdmin",
  SET_CURRENT_TECNIFICACION_ADMIN: "SetCurrentTecnificacionAdmin",

  //Subtipos de Tecnificación Admin
  INITIALIZE_SUBTIPO_TECNIFICACION_ADMIN: "InitializeSubtipoTecnificacionAdmin",
  DELETE_ONE_SUBTIPO_TECNIFICACION_ADMIN: "DeleteOneSubtipoTecnificacionAdmin",
  UPDATE_ONE_SUBTIPO_TECNIFICACION_ADMIN: "UpdateOneSubtipoTecnificacionAdmin",
  CREATE_ONE_SUBTIPO_TECNIFICACION_ADMIN: "CreateOneSubtipoTecnificacionAdmin",
  FETCH_SUBTIPO_TECNIFICACION_BY_SLUG_ADMIN: "FetchSubtipoTecnificacionBySlugAdmin",
  SET_CURRENT_SUBTIPO_TECNIFICACION_ADMIN: "SetCurrentSubtipoTecnificacionAdmin",

  //Pistas Admin
  INITIALIZE_PISTA_ADMIN: "InitializePistaAdmin",
  INITIALIZE_ONE_STATE_PISTA_ADMIN: "InitializeOneStatePistaAdmin",
  DELETE_ONE_PISTA_ADMIN: "DeleteOnePistaAdmin",
  UPDATE_ONE_PISTA_ADMIN: "UpdateOnePistaAdmin",
  CREATE_ONE_PISTA_ADMIN: "CreateOnePistaAdmin",
  FETCH_PISTA_BY_SLUG_ADMIN: "FetchPistaBySlugAdmin",
  SET_CURRENT_PISTA_ADMIN: "SetCurrentPistaAdmin",

  // Constantes para tipos de usuarios
  FETCH_TUTORES: "FETCH_TUTORES",
  SET_TUTORES: "SET_TUTORES",
  FETCH_JUGADORES_CLUB: "FETCH_JUGADORES_CLUB",
  SET_JUGADORES_CLUB: "SET_JUGADORES_CLUB",
  FETCH_JUGADORES: "FETCH_JUGADORES",
  SET_JUGADORES: "SET_JUGADORES",
  FETCH_JUGADORES_SOCIALES: "FETCH_JUGADORES_SOCIALES",
  SET_JUGADORES_SOCIALES: "SET_JUGADORES_SOCIALES",
  SET_LOADING_USERS: "SET_LOADING_USERS",

  //Suscripciones Admin
  INITIALIZE_SUSCRIPCIONES_ADMIN: "InitializeSuscripcionesAdmin",
  UPDATE_SUSCRIPCION_PRECIO_ADMIN: "UpdateSuscripcionPrecioAdmin",
  SET_SUSCRIPCIONES_ADMIN: "SetSuscripcionesAdmin",
  SET_CURRENT_SUSCRIPCION_ADMIN: "SetCurrentSuscripcionAdmin",

  //Organizaciones Admin
  INITIALIZE_ORGANIZACIONES_ADMIN: "InitializeOrganizacionesAdmin",
  CREATE_ONE_ORGANIZACION_ADMIN: "CreateOneOrganizacionAdmin",
  FETCH_ORGANIZACION_BY_SLUG_ADMIN: "FetchOrganizacionBySlugAdmin",
  SET_CURRENT_ORGANIZACION_ADMIN: "SetCurrentOrganizacionAdmin",

  REGISTER_COACH: "REGISTER_COACH",
  REGISTER_CLUB_PLAYER: "REGISTER_CLUB_PLAYER",
  REGISTER_SOCIAL_HELP_PLAYER: "REGISTER_SOCIAL_HELP_PLAYER",

  //Entrenadores (Admin Dashboard)
  ADMIN_INITIALIZE_ENTRENADORES: "AdminInitializeEntrenadores",
  ADMIN_SET_ENTRENADORES: "AdminSetEntrenadores",
  ADMIN_SET_CURRENT_ENTRENADOR: "AdminSetCurrentEntrenador",
  ADMIN_DELETE_ENTRENADOR: "AdminDeleteEntrenador",
}