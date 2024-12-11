import Constant from '../../../Constant';
import UserService from '../../../services/client/user.service';
import router from '../../../router/router'

export const user = {
    namespaced: true,
    state: {
        user: {},
        token: "",
        tokenAdmin: "",
        tokenEntrenador: "",
        isAuth: false,
        isAdmin: false,
        isEntrenador: false,
    },
    actions: {
        [Constant.LOGIN]: async (store, payload) => {
            try {
                const response = await UserService.Login(payload);
                if (response.status === 200) {
                    store.commit(Constant.LOGIN, response.data.usuario);
                }
            } catch (error) {
                throw error;
            }
        },//LOGIN

        [Constant.LOGOUT]: async (store) => {
            try {
                const response = await UserService.Logout();
                let data = { status: response.status };

                store.commit(Constant.LOGOUT, data);
            } catch (error) {
                console.log(`error: `, error);
                store.commit(Constant.LOGOUT, { status: null, status_admin: null });
            }
        },//LOGOUT

        [Constant.ADD_USER]: async (store, payload) => {
            try {
                const response = await UserService.Register(payload);
                if (response.status === 201) {
                    store.commit(Constant.ADD_USER, true);
                }
            } catch (error) {
                console.log(`error: `, error);
                throw error;
            }
        },//ADD_USER

        [Constant.INITIALIZE_PROFILE]: async (store) => {
            try {
                const response = await UserService.Profile();
                if (response.status === 200) {
                    store.commit(Constant.INITIALIZE_PROFILE, response.data);
                }
            } catch (error) {
                console.error(error);
            }
        },//INITIALIZE_PROFILE
    },


    mutations: {
        [Constant.LOGIN]: (state, payload) => {
            if (payload) {
                console.log(payload);
                state.user = payload;

                if (payload.tokenAdmin) {
                    console.log(`tokenAdmin: `, payload.tokenAdmin);
                    state.isAdmin = true;
                    state.tokenAdmin = payload.tokenAdmin;
                } else if (payload.tokenEntrenador) {
                    console.log(`tokenEntrenador: `, payload.tokenEntrenador);
                    state.isEntrenador = true;
                    state.tokenEntrenador = payload.tokenEntrenador;
                } else if (payload.token) {
                    console.log(`token: `, payload.token);
                    state.isAuth = true;
                    state.token = payload.token;
                }

                router.push({ name: 'home' });
            }
        },//LOGIN

        [Constant.ADD_USER]: (state, payload) => {
            if (payload) {
                router.push({ name: 'login' });
            }
        },//ADD_USER

        [Constant.INITIALIZE_PROFILE]: (state, payload) => {
            if (payload) {
                state.user = payload;
                state.isAuth = !!payload.token;
                state.isAdmin = !!payload.tokenAdmin;
                state.isEntrenador = !!payload.tokenEntrenador;
            }
        },//INITIALIZE_PROFILE

        [Constant.LOGOUT]: (state, payload) => {
            state.user = {};
            state.isAuth = false;
            state.isAdmin = false;
            state.isEntrenador = false;
            state.token = "";
            state.tokenAdmin = "";
            state.tokenEntrenador = "";

            router.push({ name: 'home' });

            if (payload.status === 200) {
                console.log(`Logout successfuly`);
            }
        },//LOGOUT
    },


    getters: {
        GetProfile: (state) => {
            return state.user;
        },//GetProfile
        GetIsAuth: (state) => {
            return state.isAuth;
        },//GetIsAuth
        GetIsAdmin: (state) => {
            return state.isAdmin;
        },//GetIsAdmin
        GetIsEntrenador: (state) => {
            return state.isEntrenador;
        },//GetIsEntrenador

    }//getters
}//export