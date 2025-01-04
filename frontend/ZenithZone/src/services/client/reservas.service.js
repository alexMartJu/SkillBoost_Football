import Api from "../Api";
import secrets from "../../secrets";

export default {
    CreateReserva(data) {
        return Api(secrets.URL_SPRING).post(`reservaPista`, data);
    },

}