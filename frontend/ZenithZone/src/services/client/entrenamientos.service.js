import secrets from "@/secrets"
import Api from "../Api"

export default {
    FormatFilters(params) {
        let params_ = [];
        Object.entries(params).forEach(([key, value]) => {
            // Include only meaningful values
            if (key === 'nombre' || key === 'dia') {
                // Include only if not an empty string
                if (value !== "") {
                    params_.push(`${key}=${value}`);
                }
            } else if (key.endsWith('Min') || key.endsWith('Max')) {
                // Include Min/Max values only if greater than 0
                if (value > 0) {
                    params_.push(`${key}=${value}`);
                }
            } else if (key === 'deporteId') {
                // Include deporteId only if greater than 0
                if (value > 0) {
                    params_.push(`${key}=${value}`);
                }
            } else {
                // Include other parameters (e.g., page, limit) as is
                params_.push(`${key}=${value}`);
            }
        });
        return params_.join('&');
    },

    GetEntrenamientos(params) {
        console.log(`entrenamientos?${this.FormatFilters(params)}`);
        return Api(secrets.URL_SPRING).get(`entrenamientos?${this.FormatFilters(params)}`);
        // return Api(secrets.URL_SPRING).get(`entrenamientos`);
    },

    GetEntrenamientosPaginate(params) {
        return Api(secrets.URL_SPRING).get(`entrenamientosPaginate?${this.FormatFilters(params)}`);
    }
}