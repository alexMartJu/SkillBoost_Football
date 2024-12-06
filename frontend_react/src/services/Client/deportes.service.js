import secrets from "../../secrets";
import Api from "../api";

export default {
    GetDeportes() {
        return Api(secrets.URL_SPRING).get('deportes');
    }
}