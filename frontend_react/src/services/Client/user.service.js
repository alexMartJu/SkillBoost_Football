import secrets from "../../secrets";
import Api from "../api";

export default {

    Register(data) {
        return Api(secrets.URL_SPRING).post('register', data);
    },//Register

    Login(data) {
        return Api(secrets.URL_SPRING).post('login', data);
    },//Login

    Logout() {
        return Api(secrets.URL_SPRING).post('logout');
    },//Logout

    Profile() {
        return Api(secrets.URL_SPRING).get('profile');
    },//Profile
}//export