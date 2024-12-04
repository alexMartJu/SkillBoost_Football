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
    GetPistaBySlug(slug) {
        return Api(secrets.URL_LARAVEL).get(`pistas/${slug}`);
    },
    GetDeporteBySlug(slug) {
        return Api(secrets.URL_LARAVEL).get(`deportes/${slug}`);
    },
    UpdateDeporte(slug,deporte) {
        console.log("deporte"+deporte);
        return Api(secrets.URL_LARAVEL).put(`deportes/${slug}`, deporte);
    },
    UpdatePista(slug,pista) {
        console.log("pista"+pista);
        return Api(secrets.URL_LARAVEL).put(`pistas/${slug}`, pista);
    },
    DeleteDeporte(deporteslug) {
        return Api(secrets.URL_LARAVEL).delete(`deportes/${deporteslug}`);
      },
      DeletePista(pistaslug) {
        return Api(secrets.URL_LARAVEL).delete(`pistas/${pistaslug}`);
      },

}