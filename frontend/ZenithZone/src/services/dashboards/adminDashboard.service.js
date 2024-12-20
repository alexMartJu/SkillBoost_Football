import secrets from "@/secrets";
import Api from "../Api";
const isSpringboot = "false";
export default {
    GetDeportes() {
        return Api(secrets.URL_SPRING,isSpringboot).get('deportes');
    },
    CreateDeporte(deporte) {
        return Api(secrets.URL_SPRING,isSpringboot).post('deportes', deporte);
    },
    GetPistas() {
        return Api(secrets.URL_SPRING,isSpringboot).get('pistas');
    },
    CreatePista(pista) {
        return Api(secrets.URL_SPRING,isSpringboot).post('pistas', pista);
    },
    GetPistaBySlug(slug) {
        return Api(secrets.URL_SPRING,isSpringboot).get(`pistas/${slug}`);
    },
    GetDeporteBySlug(slug) {
        return Api(secrets.URL_SPRING,isSpringboot).get(`deportes/${slug}`);
    },
    UpdateDeporte(slug, deporte) {
        return Api(secrets.URL_SPRING,isSpringboot).put(`deportes/${slug}`, deporte);
    },
    UpdatePista(slug, pista) {
        return Api(secrets.URL_SPRING,isSpringboot).put(`pistas/${slug}`, pista);
    },
    DeleteDeporte(deporteslug) {
        return Api(secrets.URL_SPRING,isSpringboot).delete(`deportes/${deporteslug}`);
    },
    DeletePista(pistaslug) {
        return Api(secrets.URL_SPRING,isSpringboot).delete(`pistas/${pistaslug}`);
    },
    RegisterEntrenador(entrenadorData){
        return Api(secrets.URL_SPRING,isSpringboot).post('entrenador/register', entrenadorData);
    },
    DeleteEntrenador(numeroEntrenador){
        return Api(secrets.URL_SPRING,isSpringboot).delete(`entrenador/${numeroEntrenador}`);
    },
    GetEntrenadores() {
        return Api(secrets.URL_SPRING,isSpringboot).get("entrenador");
      },
    GetEntrenamientos() {
        return Api(secrets.URL_SPRING,isSpringboot).get("entrenamientos");
    },
    UpdateStatus(entrenamientoSlug,status){
        return Api(secrets.URL_SPRING,isSpringboot).put(`entrenamientos/status/${entrenamientoSlug}`,status);
    }

};
