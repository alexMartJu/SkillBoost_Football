import secrets from "@/secrets";
import Api from "../Api";

export default {
    //Obtener todas las pistas
    GetAllPistas() {
        return Api(secrets.URL_PROXY).get('main/pistas');
    },
    
    //Obtener horarios ocupados de una pista en una fecha específica
    GetHorariosOcupados(pistaSlug, fecha) {
        return Api(secrets.URL_PROXY).get(`main/pistas/${pistaSlug}/horarios-ocupados?fecha=${fecha}`);
    },
    
    //Crear un nuevo entrenamiento
    CreateEntrenamiento(entrenamiento) {
        return Api(secrets.URL_PROXY).post('main/entrenador/entrenamientos/create', entrenamiento);
    },

    //Obtener todas las tecnificaciones
    GetAllTecnificaciones() {
        return Api(secrets.URL_PROXY).get('main/tecnificaciones');
    },
    
    //Obtener subtipos de una tecnificación específica
    GetSubtiposByTecnificacion(tecnificacionSlug) {
        return Api(secrets.URL_PROXY).get(`main/tecnificaciones/${tecnificacionSlug}/subtipos`);
    }

};
