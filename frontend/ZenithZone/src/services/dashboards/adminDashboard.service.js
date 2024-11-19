import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetDeportes() {
        return Api(secrets.URL_LARAVEL).get('deportes');
    },
    GetPistas(){
        return Api(secrets.URL_LARAVEL).get('pistas');
    }
}