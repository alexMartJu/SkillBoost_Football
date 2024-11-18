import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetClases() {
        return Api(secrets.URL_SPRING).get('clases');
    }
}