import secrets from "@/secrets";
import Api from "../Api";

export default {
    GetDeportes() {
        return Api(secrets.URL_SPRING).get('deportes');
    },
    CreateDeporte(deporte) {
        return Api(secrets.URL_SPRING).post('deportes', deporte);
    },
    GetPistas() {
        return Api(secrets.URL_SPRING).get('pistas');
    },
    CreatePista(pista) {
        return Api(secrets.URL_SPRING).post('pistas', pista);
    },
    GetPistaBySlug(slug) {
        return Api(secrets.URL_SPRING).get(`pistas/${slug}`);
    },
    GetDeporteBySlug(slug) {
        return Api(secrets.URL_SPRING).get(`deportes/${slug}`);
    },
    UpdateDeporte(slug, deporte) {
        return Api(secrets.URL_SPRING).put(`deportes/${slug}`, deporte);
    },
    UpdatePista(slug, pista) {
        return Api(secrets.URL_SPRING).put(`pistas/${slug}`, pista);
    },
    DeleteDeporte(deporteslug) {
        return Api(secrets.URL_SPRING).delete(`deportes/${deporteslug}`);
    },
    DeletePista(pistaslug) {
        return Api(secrets.URL_SPRING).delete(`pistas/${pistaslug}`);
    },
    RegisterEntrenador(){
        return Api(secrets.URL_SPRING).post(`entrenador/register`);
    },
    DeleteEntrenador(numeroEntrenador){
        return Api(secrets.URL_SPRING).delete(`entrenador/${numeroEntrenador}`);
    },
    GetEntrenadores() {
        return Api(secrets.URL_SPRING).get("entrenador");
      },
};
