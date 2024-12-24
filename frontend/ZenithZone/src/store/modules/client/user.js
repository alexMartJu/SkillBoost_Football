import Constant from '../../../Constant';
import UserService from '../../../services/client/user.service';
import router from '../../../router/router'

export const user = {
    namespaced: true,
    state: {
        user: {},
        profile: {},
        token: "",
        refreshToken: "",
        tokenAdmin: "",
        entrenadorToken: "",
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

        [Constant.LOGOUT]: async (store, payload) => {
            if (localStorage.getItem('token')) {
                try {
                    const response = await UserService.BlacklistToken(payload);
                    let data = { status: response.status };

                    store.commit(Constant.LOGOUT, data);
                } catch (error) {
                    store.commit(Constant.LOGOUT, { status: null, status_admin: null });
                }
            } else {
                try {
                    store.commit(Constant.LOGOUT, { status: 200 });
                } catch {
                    store.commit(Constant.LOGOUT, { status: null, status_admin: null });
                }
            }

        },//LOGOUT

        [Constant.ADD_USER]: async (store, payload) => {
            try {
                const response = await UserService.Register(payload);
                if (response.status === 201) {
                    store.commit(Constant.ADD_USER, true);
                }
            } catch (error) {
                // console.log(`error: `, error);
                throw error;
            }
        },//ADD_USER

        [Constant.INITIALIZE_PROFILE]: async (store, payload) => {
            try {
                const response = await UserService.Profile(payload);
                if (response.status === 200) {
                    store.commit(Constant.INITIALIZE_PROFILE, response.data);
                }
            } catch (error) {
                console.error(error);
            }
        },//INITIALIZE_PROFILE

        [Constant.INITIALIZE_USER]: async (store, payload) => {
            try {
                let response = null;

                if (payload?.token) {
                    Headers.Authorization = `Bearer ${payload.token}`;
                    response = await UserService.GetCurrentUser();
                } else if (payload?.tokenAdmin) {
                    Headers.Authorization = `Bearer ${payload.tokenAdmin}`;
                    response = await UserService.GetCurrentAdmin();
                } else if (payload?.entrenadorToken) {
                    Headers.Authorization = `Bearer ${payload.entrenadorToken}`;
                    response = await UserService.GetCurrentEntrenador();
                }

                if (response && response.status === 200) {
                    // console.log(response.data.usuario);
                    store.commit(Constant.INITIALIZE_USER, response.data.usuario);
                }
            } catch (error) {
                console.error(error);
            }
        },//INITIALIZE_USER
    },


    mutations: {
        [Constant.LOGIN]: (state, payload) => {
            if (payload) {
                // console.log(payload);
                state.user = payload;

                if (payload.tokenAdmin) {
                    // console.log(`tokenAdmin: `, payload.tokenAdmin);
                    state.isAdmin = true;
                    state.tokenAdmin = payload.tokenAdmin;
                    localStorage.setItem('isAdmin', true);
                    localStorage.setItem('tokenAdmin', payload.tokenAdmin);
                } else if (payload.entrenadorToken) {
                    // console.log(`entrenadorToken: `, payload.entrenadorToken);
                    state.isEntrenador = true;
                    state.entrenadorToken = payload.entrenadorToken;
                    localStorage.setItem('isEntrenador', true);
                    localStorage.setItem('entrenadorToken', payload.entrenadorToken);
                } else if (payload.token) {
                    // console.log(`token: `, payload.token);
                    state.isAuth = true;
                    state.token = payload.token;
                    state.refreshToken = payload.refreshToken;
                    localStorage.setItem('isAuth', true);
                    localStorage.setItem('token', payload.token);
                    localStorage.setItem('refreshToken', payload.refreshToken);
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
                state.profile = payload;
                state.isAuth = !!payload.token;
                state.isAdmin = !!payload.tokenAdmin;
                state.isEntrenador = !!payload.entrenadorToken;
            }
        },//INITIALIZE_PROFILE

        [Constant.INITIALIZE_USER]: (state, payload) => {
            if (payload) {
                state.user = payload;
                state.isAuth = !!payload.numeroSocio;
                state.isAdmin = !!payload.numeroAdmin;
                state.isEntrenador = !!payload.numeroEntrenador;
            }
        },//INITIALIZE_USER

        [Constant.LOGOUT]: (state, payload) => {
            state.user = {};
            state.isAuth = false;
            state.isAdmin = false;
            state.isEntrenador = false;
            state.token = "";
            state.tokenAdmin = "";
            state.entrenadorToken = "";
            localStorage.removeItem('isAuth');
            localStorage.removeItem('isAdmin');
            localStorage.removeItem('isEntrenador');
            localStorage.removeItem('token');
            localStorage.removeItem('refreshToken');
            localStorage.removeItem('tokenAdmin');
            localStorage.removeItem('entrenadorToken');

            router.push({ name: 'home' });

            if (payload.status === 200) {
                console.log(`Logout successfuly`);
            }
        },//LOGOUT
    },


    getters: {
        GetProfile: (state) => {
            return state.profile;
        },//GetProfile
        GetCurrentUser: (state) => {
            return state.user;
        },//GetCurrentUser
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