import Constant from '../../../Constant';
import UserService from '../../../services/client/user.service';
import router from '../../../router/router';
import { jwtDecode } from 'jwt-decode';

export const user = {
    namespaced: true,
    state: {
        user: {},
        profile: {},
        accessToken: "",
        refreshToken: "",
        isAuth: false,
        isAdmin: false,
        isEntrenador: false,
        userType: ""
    },
    actions: {
        [Constant.LOGIN]: async (store, payload) => {
            try {
                const response = await UserService.Login(payload);
                if (response.status === 200) {
                    store.commit(Constant.LOGIN, response.data);
                }
            } catch (error) {
                throw error;
            }
        },//LOGIN

        [Constant.LOGOUT]: async (store, payload) => {
            if (localStorage.getItem('accessToken')) {
                try {
                    const response = await UserService.Logout(payload);
                    let data = { status: response.status };

                    store.commit(Constant.LOGOUT, data);
                } catch (error) {
                    store.commit(Constant.LOGOUT, { status: null });
                }
            } else {
                try {
                    store.commit(Constant.LOGOUT, { status: 200 });
                } catch {
                    store.commit(Constant.LOGOUT, { status: null });
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
                throw error;
            }
        },//ADD_USER

        [Constant.INITIALIZE_PROFILE]: async (store, payload) => {
            try {
                let response = null;
                
                //Primero verificamos si es un entrenador
                if (payload.numeroEntrenador) {
                    response = await UserService.GetProfileByNumeroEntrenador(payload.numeroEntrenador);
                } 
                //Si no es entrenador o no se pudo obtener el perfil, intentamos con numeroSocio
                else if (payload.numeroSocio) {
                    response = await UserService.GetProfileByNumeroSocio(payload.numeroSocio);
                }

                if (response && response.status === 200) {
                    store.commit(Constant.INITIALIZE_PROFILE, response.data);
                }
            } catch (error) {
                console.error(error);
            }
        },//INITIALIZE_PROFILE

        [Constant.INITIALIZE_USER]: async (store, payload) => {
            try {
                let response = null;
                
                if (payload?.accessToken) {
                    localStorage.setItem('accessToken', payload.accessToken);
                }
                
                response = await UserService.GetCurrentUser();

                if (response && response.status === 200) {
                    store.commit(Constant.INITIALIZE_USER, response.data);
                }
            } catch (error) {
                console.error(error);
            }
        },//INITIALIZE_USER

        [Constant.UPDATE_USER]: async (store, payload) => {
            try {
              let response;
              if (store.state.isEntrenador) {
                response = await UserService.UpdateCoachUser(payload);
              } else {
                response = await UserService.UpdateUser(payload);
              }
              
              if (response.status === 200) {
                store.commit(Constant.UPDATE_USER, response.data);
                return response.data;
              }
            } catch (error) {
              console.error("Error al actualizar el usuario:", error);
              throw error;
            }
          },
    },

    mutations: {
        [Constant.LOGIN]: (state, payload) => {
            if (payload) {
                state.user = payload.user;
                state.accessToken = payload.accessToken;
                state.refreshToken = payload.refreshToken;
                
                //Decodificar el token para obtener el tipo de usuario
                const decodedToken = jwtDecode(payload.accessToken);
                state.userType = decodedToken.userType || payload.user.tipoUsuario;
                
                //Establecer flags basados en el tipo de usuario (insensible a mayúsculas/minúsculas)
                state.isAdmin = state.userType?.toLowerCase() === 'admin';
                state.isEntrenador = state.userType?.toLowerCase() === 'entrenador';
                //isAuth solo para usuarios normales (no admin ni entrenador)
                state.isAuth = !state.isAdmin && !state.isEntrenador;
                
                // Guardar en localStorage
                localStorage.setItem('accessToken', payload.accessToken);
                localStorage.setItem('refreshToken', payload.refreshToken);
                localStorage.setItem('userType', state.userType);
                
                if (state.isAdmin) {
                    localStorage.setItem('isAdmin', true);
                    router.push({ name: 'DashboardAdmin' });
                } else if (state.isEntrenador) {
                    localStorage.setItem('isEntrenador', true);
                    router.push({ name: 'DashboardEntrenador' });
                } else {
                    localStorage.setItem('isAuth', true);
                    router.push({ name: 'home' });
                }
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
            }
        },//INITIALIZE_PROFILE

        [Constant.INITIALIZE_USER]: (state, payload) => {
            if (payload) {
                state.user = payload;
                state.userType = payload.tipoUsuario;
                state.isAdmin = state.userType?.toLowerCase() === 'admin';
                state.isEntrenador = state.userType?.toLowerCase() === 'entrenador';
                //isAuth solo para usuarios normales (no admin ni entrenador)
                state.isAuth = !state.isAdmin && !state.isEntrenador;
                
                localStorage.setItem('userType', state.userType);
                
                if (state.isAdmin) {
                    localStorage.setItem('isAdmin', true);
                } else if (state.isEntrenador) {
                    localStorage.setItem('isEntrenador', true);
                } else {
                    localStorage.setItem('isAuth', true);
                }
            }
        },//INITIALIZE_USER

        [Constant.LOGOUT]: (state, payload) => {
            state.user = {};
            state.profile = {};
            state.isAuth = false;
            state.isAdmin = false;
            state.isEntrenador = false;
            state.accessToken = "";
            state.refreshToken = "";
            state.userType = "";
            
            localStorage.removeItem('isAuth');
            localStorage.removeItem('isAdmin');
            localStorage.removeItem('isEntrenador');
            localStorage.removeItem('accessToken');
            localStorage.removeItem('refreshToken');
            localStorage.removeItem('userType');

            router.push({ name: 'home' });

            if (payload.status === 200) {
                console.log(`Logout successful`);
            }
        },//LOGOUT

        [Constant.UPDATE_USER]: (state, payload) => {
            if (payload) {
              // Actualizar el usuario en el estado
              state.user = {
                ...state.user,
                ...payload
              };
              
              // Si el payload incluye un perfil, actualizarlo también
              if (payload.profile) {
                state.profile = {
                  ...state.profile,
                  ...payload.profile
                };
              }
            }
          },
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
        GetUserType: (state) => {
            return state.userType;
        }//GetUserType
    }//getters
}//export
