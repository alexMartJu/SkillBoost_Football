import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetEntrenamientos() {
        return Api(secrets.URL_SPRING).get('entrenamientos');
    }
}