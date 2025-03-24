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
    },
    GetInscripcionesByEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).get(`main/entrenador/entrenamientos/${slug}/inscripciones`);
    },
    
    GetEvaluacionAlumno(numeroSocio, slug) {
        return Api(secrets.URL_PROXY).get(`main/entrenador/profiles/${numeroSocio}/entrenamientos/${slug}/evaluaciones`);
    },
    
    CreateEvaluacionAlumno(numeroSocio, slug, evaluacion) {
        return Api(secrets.URL_PROXY).post(`main/entrenador/profiles/${numeroSocio}/entrenamientos/${slug}/evaluaciones`, evaluacion);
    },
    
    UpdateEvaluacionAlumno(numeroSocio, slug, evaluacion) {
        return Api(secrets.URL_PROXY).put(`main/entrenador/profiles/${numeroSocio}/entrenamientos/${slug}/evaluaciones`, evaluacion);
    },
    
    GetGraficasAlumno(numeroSocio, año) {
        return Api(secrets.URL_PROXY).get(`main/profiles/${numeroSocio}/graficas?año=${año}`);
    },
    
    UpdateGraficasAlumno(numeroSocio, graficas) {
        // Si recibimos un objeto con propiedad 'secciones', usamos eso directamente
        const secciones = graficas.secciones || 
                         // De lo contrario, convertimos el objeto a un array de secciones
                         Object.entries(graficas).map(([seccion, nivel]) => ({ seccion, nivel }));
        
        return Api(secrets.URL_PROXY).put(`main/entrenador/profiles/${numeroSocio}/graficas`, {
            secciones: secciones
        });
    }

};
