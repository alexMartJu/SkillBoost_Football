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
    }


};
