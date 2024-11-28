import secrets from "@/secrets"
import Api from "../Api"

export default {
    FormatFilters(params) {
        let params_ = [];
        Object.entries(params).forEach(item => {
            if (item[0] === 'categories' && item[1].length > 0) {
                const categories = item[1].map(item => `categories=${item}`).join('&');
                params_.push(categories)
            } else if (item[1] != null) {
                params_.push(`${item[0]}=${item[1]}`);
            }
        });
        return params_.join('&')
    },

    GetEntrenamientos(params) {
        return Api(secrets.URL_SPRING).get(`entrenamientos?${this.FormatFilters(params)}`);
    },

    GetEntrenamientosPaginate(params) {
        return Api(secrets.URL_SPRING).get(`entrenamientosPaginate?${this.FormatFilters(params)}`);
    }
}