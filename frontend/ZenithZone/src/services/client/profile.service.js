import Api from "../Api";
import secrets from "../../secrets";

export default {
    Profile(numSocio) {
        return Api(secrets.URL_SPRING).get(`profiles/${numSocio}`);
    },
    // Entrenamientos(numeroSocio) {
    //     return Api(secrets.URL_SPRING).post('profileEntrenamientos', numeroSocio);
    // },
    Entrenamientos() {
        return Api(secrets.URL_SPRING).get('entrenamientos');
    },
    Reservas(numeroSocio) {
        return Api(secrets.URL_SPRING).get('profileReservas', numeroSocio);
    },
    Graficas(numeroSocio) {
        return Api(secrets.URL_SPRING).get('profileGraficas', numeroSocio);
    }
}