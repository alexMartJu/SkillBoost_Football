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
    DeleteEntrenador(numeroentrenador){
        return Api(secrets.URL_SPRING,isSpringboot).delete(`entrenador/${numeroentrenador}`);
    },
    GetEntrenadores() {
        return Api(secrets.URL_SPRING,isSpringboot).get("entrenador");
      },
    GetEntrenamientos() {
        return Api(secrets.URL_SPRING,isSpringboot).get("entrenamientos");
    },
    UpdateStatus(entrenamientoSlug,status){ 
        return Api(secrets.URL_SPRING,isSpringboot).put(`entrenamientos/status/${entrenamientoSlug}`,status);
    },
    GetSalas(){
        return Api(secrets.URL_SPRING,isSpringboot).get('salas');
    },
    CreateSalas(sala) {
        console.log("sala"+JSON.stringify(sala));
        return Api(secrets.URL_SPRING,isSpringboot).post('salas', sala);
    },
    DeleteSalas(sala){
        return Api(secrets.URL_SPRING,isSpringboot).delete(`salas/${sala}`);
    },
    GetPistasPrivadas() {
        return Api(secrets.URL_SPRING, isSpringboot).get('pistasprivadas');
    },
    CreatePistaPrivada(pistaPrivada) {
        return Api(secrets.URL_SPRING, isSpringboot).post('pistasprivadas', pistaPrivada);
    },
    GetPistaPrivadaBySlug(slug) {
        return Api(secrets.URL_SPRING, isSpringboot).get(`pistasprivadas/${slug}`);
    },
    DeletePistaPrivada(pistaslug) {
        return Api(secrets.URL_SPRING, isSpringboot).delete(`pistasprivadas/${pistaslug}`);
    },
    GetReservas() {
        return Api(secrets.URL_SPRING, isSpringboot).get('reservas');
    },
    DeleteReserva(reservaId) {
        return Api(secrets.URL_SPRING, isSpringboot).delete(`reservas/${reservaId}`);
    },

};
