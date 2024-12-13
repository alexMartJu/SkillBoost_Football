import secrets from "@/secrets";
import Api from "../Api";

const notSpringHeaders = () => ({
    headers: {
        'isSpringboot': false
    }
});

export default {
    // Obtener la lista de entrenamientos desde la API
    GetEntrenamientos() {
        return Api(secrets.URL_LARAVEL).get('entrenamientos', notSpringHeaders());
    },

    // Crear un nuevo entrenamiento en la API
    CreateEntrenamiento(nuevoEntrenamiento) {
        return Api(secrets.URL_LARAVEL).post('entrenamientos', nuevoEntrenamiento, notSpringHeaders());
    }
};
