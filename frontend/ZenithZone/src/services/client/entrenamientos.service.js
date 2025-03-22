import secrets from "@/secrets"
import Api from "../Api"

export default {
    FormatFilters(params) {
        let params_ = [];
        Object.entries(params).forEach(([key, value]) => {
            if (key === 'nombre' || key === 'nivel' || key === 'tecnificacionNombre') {
                if (value !== "" && value !== null) {
                    params_.push(`${key}=${value}`);
                }
            } else if (key === 'edadMinima' || key === 'edadMaxima' || key === 'maxPlazasMin' || key === 'maxPlazasMax') {
                if (value !== null && value > 0) {
                    params_.push(`${key}=${value}`);
                }
            } else if (key === 'fechaInicio' || key === 'fechaFin') {
                if (value !== null && value !== "") {
                    //Si ya tiene formato datetime-local (contiene 'T'), usarlo directamente
                    //Si no, añadir la parte de tiempo
                    const formattedDate = value.includes('T') 
                        ? value 
                        : `${value}T00:00:00`;
                    params_.push(`${key}=${encodeURIComponent(formattedDate)}`);
                }
            } else {
                params_.push(`${key}=${value}`);
            }
        });
        return params_.join('&');
    },

    GetEntrenamientos(params) {
        return Api(secrets.URL_PROXY).get(`main/entrenamientos?${this.FormatFilters(params)}`);
    },

    GetEntrenamientosData() {
        return Api(secrets.URL_PROXY).get(`main/entrenamientos/filter-data`);
    },

    GetEntrenamientosTotalFiltered(params) {
        return Api(secrets.URL_PROXY).get(`main/entrenamientos/count?${this.FormatFilters(params)}`);
    },

    GetOneEntrenamiento(slug) {
        return Api(secrets.URL_PROXY).get(`main/entrenamientos/${slug}`);
    },

    GetSuscribedEntrenamientos() {
        return Api(secrets.URL_PROXY).get(`main/reservas`);
    }
}