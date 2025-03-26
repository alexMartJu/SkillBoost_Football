import secrets from "@/secrets";
import Api from "../Api";

export default {
    //
    GetTecnificaciones() {
        return Api(secrets.URL_PROXY).get('main/tecnificaciones');
    },
    GetTecnificacionBySlug(slug) {
        return Api(secrets.URL_PROXY).get(`main/tecnificaciones/${slug}`);
    },
    CreateTecnificacion(tecnificacion) {
        return Api(secrets.URL_PROXY).post('main/tecnificaciones', tecnificacion);
    },
    UpdateTecnificacion(slug, tecnificacion) {
        return Api(secrets.URL_PROXY).put(`main/tecnificaciones/${slug}`, tecnificacion);
    },
    DeleteTecnificacion(slug) {
        return Api(secrets.URL_PROXY).delete(`main/tecnificaciones/${slug}`);
    },
    //Subtipos de Tecnificación
    GetSubtiposTecnificacion() {
        return Api(secrets.URL_PROXY).get('main/subtipo-tecnificaciones');
    },
    GetSubtipoTecnificacionBySlug(slug) {
        return Api(secrets.URL_PROXY).get(`main/subtipo-tecnificaciones/${slug}`);
    },
    CreateSubtipoTecnificacion(tecnificacionSlug, subtipo) {
        return Api(secrets.URL_PROXY).post(`main/tecnificaciones/${tecnificacionSlug}/subtipos`, subtipo);
    },
    UpdateSubtipoTecnificacion(slug, subtipo) {
        return Api(secrets.URL_PROXY).put(`main/subtipo-tecnificaciones/${slug}`, subtipo);
    },
    DeleteSubtipoTecnificacion(slug) {
        return Api(secrets.URL_PROXY).delete(`main/subtipo-tecnificaciones/${slug}`);
    },
    GetSubtiposByTecnificacionSlug(tecnificacionSlug) {
        return Api(secrets.URL_PROXY).get(`main/tecnificaciones/${tecnificacionSlug}/subtipos`);
    },
    //Pistas
    GetPistas() {
        return Api(secrets.URL_PROXY).get('main/pistas');
    },
    GetPistaBySlug(slug) {
        return Api(secrets.URL_PROXY).get(`main/pistas/${slug}`);
    },
    CreatePista(pista) {
        return Api(secrets.URL_PROXY).post('main/pistas/create', pista);
    },
    UpdatePista(slug, pista) {
        return Api(secrets.URL_PROXY).put(`main/pistas/${slug}/update`, pista);
    },
    DeletePista(slug) {
        return Api(secrets.URL_PROXY).delete(`main/pistas/${slug}/delete`);
    },
    //Tipos usuarios
    GetTutores() {
        return Api(secrets.URL_PROXY).get('main/profiles/tutores');
    },
    GetJugadoresClub() {
        return Api(secrets.URL_PROXY).get('main/profiles/jugadores-club');
    },
    GetJugadores() {
        return Api(secrets.URL_PROXY).get('main/profiles/jugadores');
    },
    GetJugadoresSociales() {
        return Api(secrets.URL_PROXY).get('main/profiles/jugadores-sociales');
    },
    //Suscripciones
    GetSuscripciones() {
        return Api(secrets.URL_PROXY).get('main/suscripciones');
    },
    UpdateSuscripcionPrecio(slug, precioData) {
        return Api(secrets.URL_PROXY).patch(`main/suscripciones/${slug}/precio`, precioData);
    },
    //Organizaciones
    GetOrganizaciones() {
        return Api(secrets.URL_PROXY).get('main/organizaciones');
    },
    GetOrganizacionBySlug(slug) {
        return Api(secrets.URL_PROXY).get(`main/organizaciones/${slug}`);
    },
    CreateOrganizacion(organizacion) {
        return Api(secrets.URL_PROXY).post('main/organizaciones', organizacion);
    },
    //Registro de usuarios
    RegisterCoach(coachData) {
        return Api(secrets.URL_PROXY).post('main/entrenadores/registro', coachData);
    },
    RegisterClubPlayer(playerData) {
        return Api(secrets.URL_PROXY).post('main/jugadores-club/registro', playerData);
    },
    RegisterSocialHelpPlayer(playerData) {
        return Api(secrets.URL_PROXY).post('main/jugadores-sociales/registro', playerData);
    },
    //Entrenadores
    GetEntrenadores() {
        return Api(secrets.URL_PROXY).get('main/entrenadores');
    },
    
    DeleteEntrenador(numeroEntrenador) {
        return Api(secrets.URL_PROXY).delete(`main/entrenadores/${numeroEntrenador}`);
    },
    
    GetEntrenadorByNumero(numeroEntrenador) {
        return Api(secrets.URL_PROXY).get(`main/entrenadores/${numeroEntrenador}`);
    },
    //Entrenamientos pendientes
    GetPendingEntrenamientos() {
        return Api(secrets.URL_PROXY).get('main/admin/entrenamientos/pending');
    },
    
    ApproveEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).post(`main/admin/entrenamientos/${slug}/approve`);
    },
    
    DenyEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).post(`main/admin/entrenamientos/${slug}/deny`);
    },


};
