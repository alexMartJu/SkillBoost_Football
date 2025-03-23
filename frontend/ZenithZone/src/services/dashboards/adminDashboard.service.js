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
    }

};
