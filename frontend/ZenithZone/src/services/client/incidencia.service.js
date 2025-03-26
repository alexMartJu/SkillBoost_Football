import secrets from "@/secrets"
import Api from "../Api"

class IncidenciaService {
  //Crear una nueva incidencia
  createIncidencia(data) {
    return Api(secrets.URL_PROXY).post('main/incidencias', data);
  }
}

export default new IncidenciaService();