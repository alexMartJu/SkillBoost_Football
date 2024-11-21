import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetPistas() {
        return Api(secrets.URL_SPRING).get('pistas');
    }
}