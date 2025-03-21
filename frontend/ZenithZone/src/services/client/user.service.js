import Api from "../Api";
import secrets from "../../secrets";

export default {
    // USER
    Register(data) {
        return Api(secrets.URL_PROXY).post('main/usuarios', data);
    },

    Login(data) {
        return Api(secrets.URL_PROXY).post('main/login', data);
    },

    UpdateUser(data) {
        return Api(secrets.URL_PROXY).put('main/user', data);
    },
    
    UpdateCoachUser(data) {
        return Api(secrets.URL_PROXY).put('main/user/coach', data);
    },

    GetCurrentUser() {
        return Api(secrets.URL_PROXY).get('main/user');
    },

    Refresh(refreshToken) {
        return Api(secrets.URL_PROXY).post(`main/refresh`, refreshToken);
    },

    Logout(refreshToken) {
        return Api(secrets.URL_PROXY).post(`main/logout`, refreshToken);
    },

    GetProfileByNumeroSocio(numeroSocio) {
        return Api(secrets.URL_PROXY).get(`main/profiles/${numeroSocio}`);
    },
    
    GetProfileByNumeroEntrenador(numeroEntrenador) {
        return Api(secrets.URL_PROXY).get(`main/profiles/entrenadores/${numeroEntrenador}`);
    }
}
