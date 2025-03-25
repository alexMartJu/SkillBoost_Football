import Api from "../Api";
import secrets from "../../secrets";

export default {
    Profile(numSocio) {
        return Api(secrets.URL_SPRING).get(`profiles/${numSocio}`);
    },
    UpdateProfile(data) {
        return Api(secrets.URL_SPRING).put(`user`, data);
    },
    Entrenamientos() {
        return Api(secrets.URL_SPRING).get('profileEntrenamientos');
    },
    Reservas() {
        return Api(secrets.URL_SPRING).get('profilePistas');
    },
    Graficas(año = 2025) {
        return Api(secrets.URL_PROXY).get(`main/profileGraficas?año=${año}`);
    },

    ReservasActivas() {
        return Api(secrets.URL_PROXY).get('main/reservas/activas');
    },
    ReservasExpiradas() {
        return Api(secrets.URL_PROXY).get('main/reservas/expiradas');
    },
    Logros() {
        return Api(secrets.URL_PROXY).get('main/logros');
    },

    // Botones de cancelar
    CancelarReserva(data) {
        return Api(secrets.URL_SPRING).delete(`reservaPista?slugPista=${data.slugPista}&hora=${data.hora}&fecha=${data.fecha}`);
    },
    CancelarEntrenamiento(slug) {
        return Api(secrets.URL_SPRING).delete(`entrenamientos/${slug}/inscripcion`);
    },
    UnirseEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).post(`main/entrenamientos/${slug}/inscribirse`);
    }
}