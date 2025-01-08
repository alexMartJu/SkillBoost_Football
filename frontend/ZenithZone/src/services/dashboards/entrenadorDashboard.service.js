import secrets from "@/secrets";
import Api from "../Api";
const isSpringboot = "false";
export default {
    // Obtener la lista de entrenamientos desde la API
    GetEntrenamientos() {
        return Api(secrets.URL_SPRING,isSpringboot).get('entrenamientos');
    },

    // Crear un nuevo entrenamiento en la API
    CreateEntrenamiento(nuevoEntrenamiento) {
        return Api(secrets.URL_SPRING,isSpringboot).post('entrenamientos', nuevoEntrenamiento);
    },
    GetGraficas(id) {
        return Api(secrets.URL_SPRING, isSpringboot).get(`graficas/profile/${id}`);
      },
    
    UpdateGraficas(id, graficas) {
        return Api(secrets.URL_SPRING, isSpringboot).post(`graficas/profile/${id}`, graficas);
    },
    GetProfile(id) {
        return Api(secrets.URL_SPRING, isSpringboot).get(`profile/${id}`);
    },
    DeleteEntrenamiento(id){
        return Api(secrets.URL_SPRING,isSpringboot).delete(`entrenamientos/${id}`);
    },
    GetEntrenador(){
        return Api(secrets.URL_SPRING,isSpringboot).get(`currentEntrenador`);
    },
    GetPistasPrivadas(){
        return Api(secrets.URL_SPRING,isSpringboot).get(`pistasprivadas`);
    },
    GetEntrenamientosOcupados(pistaId){
        return Api(secrets.URL_SPRING,isSpringboot).get(`horariosocupados/${pistaId}`);
    },
    GetHorarios(pistaId){
        return Api(secrets.URL_SPRING,isSpringboot).get(`horarios`);
    }

};
