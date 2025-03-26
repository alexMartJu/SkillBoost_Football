import Api from "../Api";
import secrets from "../../secrets";

export default {
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
    SuscripcionActiva() {
        return Api(secrets.URL_PROXY).get('main/mi-suscripcion');
    },

    // Botones de cancelar
    CancelarEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).delete(`main/entrenamientos/${slug}/desinscribirse`);
    },
    UnirseEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).post(`main/entrenamientos/${slug}/inscribirse`);
    }
}