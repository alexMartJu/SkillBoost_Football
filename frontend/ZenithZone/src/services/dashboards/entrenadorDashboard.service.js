import secrets from "@/secrets";
import Api from "../Api";

export default {
    // Obtener la lista de entrenamientos desde la API
    GetEntrenamientos() {
        return Api(secrets.URL_LARAVEL).get('entrenamientos');
    },

    // Crear un nuevo entrenamiento en la API
    CreateEntrenamiento(nuevoEntrenamiento) {
        return Api(secrets.URL_LARAVEL).post('entrenamientos', nuevoEntrenamiento);
    }
};
