import secrets from "@/secrets";
import Api from "../Api";

export default {
    // Obtener la lista de clases desde la API
    GetClases() {
        return Api(secrets.URL_LARAVEL).get('clases');
    },

    // Crear una nueva clase en la API
    CreateClase(nuevaClase) {
        return Api(secrets.URL_LARAVEL).post('clases', nuevaClase);
    }
};
