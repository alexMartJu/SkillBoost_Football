import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetDeportes() {
        return Api(secrets.URL_LARAVEL).get('Deportes');
    }
}