import secrets from "@/secrets"
import Api from "../Api"

export default {
    GetDeportes() {
        return Api(secrets.URL_LARAVEL).get('deportes');
    },
    CreateDeporte(deporte) {
        return Api(secrets.URL_LARAVEL).post('deportes', deporte);
      },
    GetPistas(){
        return Api(secrets.URL_LARAVEL).get('pistas');
    },
    CreatePista(pista) {
        return Api(secrets.URL_LARAVEL).post('pistas', pista);
      },
}