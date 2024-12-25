import Api from "../Api";
import secrets from "../../secrets";

export default {
    Profile(numSocio) {
        return Api(secrets.URL_SPRING).get(`profiles/${numSocio}`);
    },
    Entrenamientos(numeroSocio) {
        return Api(secrets.URL_SPRING).post('profileEntrenamientos', numeroSocio);
    },
    Reservas(numeroSocio) {
        return Api(secrets.URL_SPRING).post('profileReservas', numeroSocio);
    },
    Graficas(numeroSocio) {
        return Api(secrets.URL_SPRING).post('profileGraficas', numeroSocio);
    }
}