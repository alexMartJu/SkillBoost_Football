import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetTecnificaciones() {
        return Api(secrets.URL_PROXY).get('main/tecnificaciones');
    }
}