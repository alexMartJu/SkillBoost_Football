import Api from "../Api";
import secrets from "../../secrets";

export default {
    Profile(numSocio) {
        return Api(secrets.URL_SPRING).get(`profiles/${numSocio}`);
    },
    UpdateProfile(data) {
        return Api(secrets.URL_SPRING).put(`user`, data);
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
    Graficas(año = 2024) {
        return Api(secrets.URL_SPRING).get(`profileGraficas?año=${año}`);
    },

    // Botones de cancelar
    CancelarReserva(data) {
        return Api(secrets.URL_SPRING).delete('profileReservas', data);
    },
    CancelarEntrenamiento(data) {
        return Api(secrets.URL_SPRING).delete('profileEntrenamiento', data);
    }
}