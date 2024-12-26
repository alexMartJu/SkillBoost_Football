import Api from "../Api";
import secrets from "../../secrets";

export default {
    GetEntrenadores() {
        return Api(secrets.URL_SPRING, 'false').get(`entrenador`);
    },
    Profile(numeroEntrenador) {
        return Api(secrets.URL_SPRING, 'false').get(`entrenador/${numeroEntrenador}`);
    },
    GetCurrentEntrenador() {
        return Api(secrets.URL_SPRING, 'false').get('currentEntrenador');
    },
    UpdateEntrenador() {
        return Api(secrets.URL_SPRING, 'false').update('currentEntrenador');
    },

}