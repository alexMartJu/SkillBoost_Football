import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetPistas() {
        return Api(secrets.URL_SPRING).get('pistas');
    },

    GetPistasByDeporte(slug) {
        // console.log(`deportes/${slug}/pistas`);
        return Api(secrets.URL_SPRING).get(`deportes/${slug}/pistas`);
    }
}