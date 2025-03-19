import secrets from "@/secrets"
import Api from "../Api"

export default {
    //Obtener todas las organizaciones
    GetOrganizaciones() {
        return Api(secrets.URL_PROXY).get(`main/organizaciones`);
    },

    //Obtener una organización por slug
    GetOneOrganizacion(slug) {
        return Api(secrets.URL_PROXY).get(`main/organizaciones/${slug}`);
    },

    //Método para crear una organización (solo Admin)
    CreateOrganizacion(organizacion) {
        return Api(secrets.URL_PROXY).post(`main/organizaciones`, organizacion);
    }
}