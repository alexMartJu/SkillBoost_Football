import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetPistas() {
        return Api(secrets.URL_PROXY).get('main/pistas');
    },

    GetOnePista(slug) {
        return Api(secrets.URL_PROXY).get(`main/pistas/${slug}`);
    }
}