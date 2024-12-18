import Api from "../Api";
import secrets from "../../secrets";

export default {
    // USER
    Register(data) {
        return Api(secrets.URL_SPRING).post('users', data);
    },

    Login(data) {
        return Api(secrets.URL_SPRING).post('users/login', data);
    },

    Logout() {
        return Api(secrets.URL_SPRING).post('logout');
    },

    UpdateUser() {
        return Api(secrets.URL_SPRING).put('user');
    },

    CurrentUserProfile() {
        return Api(secrets.URL_SPRING).get('user');
    },

    GetCurrentUser() {
        return Api(secrets.URL_SPRING).get('user');
    },

    Profile(numSocio) {
        console.log(`numSocio: ${numSocio}`);
        return Api(secrets.URL_SPRING).get(`profiles/${numSocio}`);
    },

    // ADMIN
    GetCurrentAdmin() {
        return Api(secrets.URL_SPRING, 'false').get('currentAdmin',);
    },

    // ENTRENADOR
    GetCurrentEntrenador() {
        return Api(secrets.URL_SPRING, 'false').get('currentEntrenador');
    },

    UpdateEntrenador() {
        return Api(secrets.URL_SPRING, 'false').update('currentEntrenador');
    },

}//export