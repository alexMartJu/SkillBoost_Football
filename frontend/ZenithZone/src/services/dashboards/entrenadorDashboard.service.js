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
    },
    // Obtener entrenamientos pendientes del entrenador
    GetPendingEntrenamientos() {
        return Api(secrets.URL_PROXY).get('main/entrenador/entrenamientos/pending');
    },
    
    // Obtener entrenamientos aprobados del entrenador
    GetApprovedEntrenamientos() {
        return Api(secrets.URL_PROXY).get('main/entrenador/entrenamientos/approved');
    },
    
    // Obtener entrenamientos denegados del entrenador
    GetDeniedEntrenamientos() {
        return Api(secrets.URL_PROXY).get('main/entrenador/entrenamientos/denied');
    }

};
