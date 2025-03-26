import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetSuscripciones() {
        return Api(secrets.URL_PROXY).get(`main/suscripciones`);
    },

    UpdatePrecio(slug, precio) {
        return Api(secrets.URL_PROXY).patch(`main/suscripciones/${slug}/precio`, { precio });
    }
}