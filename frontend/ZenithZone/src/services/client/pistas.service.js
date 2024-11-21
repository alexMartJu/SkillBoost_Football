import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetPistas() {
        return Api(secrets.URL_LARAVEL).get('pistas');
    }
}