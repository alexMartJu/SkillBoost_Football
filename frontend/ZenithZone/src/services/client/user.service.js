import Api from "../Api";
import secrets from "../../secrets";

export default {

    Register(data) {
        console.log(data);
        return Api(secrets.URL_SPRING).post('users', data);
    },//Register

    Login(data) {
        console.log(data);
        return Api(secrets.URL_SPRING).post('users/login', data);
    },//Login

    Logout() {
        return Api(secrets.URL_SPRING).post('logout');
    },//Logout

    UpdateUser() {
        return Api(secrets.URL_SPRING).put('user');
    },//UpdateUser

    CurrentUserProfile() {
        return Api(secrets.URL_SPRING).get('user');
    },

    Profile(numSocio) {
        console.log(`numSocio: ${numSocio}`);
        return Api(secrets.URL_SPRING).get(`profiles/${numSocio}`);
    },//Profile

}//export