import secrets from "@/secrets";
import Api from "../Api";

const notSpringHeaders = () => ({
    headers: {
        'isSpringboot': false
    }
});

export default {
    GetDeportes() {
        return Api(secrets.URL_SPRING).get('deportes', notSpringHeaders());
    },
    CreateDeporte(deporte) {
        return Api(secrets.URL_SPRING).post('deportes', deporte, notSpringHeaders());
    },
    GetPistas() {
        return Api(secrets.URL_SPRING).get('pistas', notSpringHeaders());
    },
    CreatePista(pista) {
        return Api(secrets.URL_SPRING).post('pistas', pista, notSpringHeaders());
    },
    GetPistaBySlug(slug) {
        return Api(secrets.URL_SPRING).get(`pistas/${slug}`, notSpringHeaders());
    },
    GetDeporteBySlug(slug) {
        return Api(secrets.URL_SPRING).get(`deportes/${slug}`, notSpringHeaders());
    },
    UpdateDeporte(slug, deporte) {
        return Api(secrets.URL_SPRING).put(`deportes/${slug}`, deporte, notSpringHeaders());
    },
    UpdatePista(slug, pista) {
        return Api(secrets.URL_SPRING).put(`pistas/${slug}`, pista, notSpringHeaders());
    },
    DeleteDeporte(deporteslug) {
        return Api(secrets.URL_SPRING).delete(`deportes/${deporteslug}`, notSpringHeaders());
    },
    DeletePista(pistaslug) {
        return Api(secrets.URL_SPRING).delete(`pistas/${pistaslug}`, notSpringHeaders());
    }
};
