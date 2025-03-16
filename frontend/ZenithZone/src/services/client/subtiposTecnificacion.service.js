import secrets from "@/secrets"
import Api from "../Api"

export default {

    GetSubtiposTecnificacion() {
        return Api(secrets.URL_PROXY).get('main/subtipo-tecnificaciones');
    },

    GetOneSubtipoTecnificacion(slug) {
        return Api(secrets.URL_PROXY).get(`main/subtipo-tecnificaciones/${slug}`);
    },

    GetSubtiposByTecnificacion(slug) {
        return Api(secrets.URL_PROXY).get(`main/tecnificaciones/${slug}/subtipos`);
    },

    GetSubtiposTecnificacionInfinite(offset, limit) {
        return Api(secrets.URL_PROXY).get(`main/subtipo-tecnificaciones-scroll?offset=${offset}&limit=${limit}`);
    }

}