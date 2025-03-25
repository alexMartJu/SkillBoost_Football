import Constant from '@/Constant';
import profileService from '@/services/client/profile.service';

export const profile = {
    namespaced: true,

    state: {
        entrenamientos: [],
        reservas: [],
        graficas: [],
        reservasActivas: [],
        reservasExpiradas: [],
        logros: []
    },

    actions: {
        [Constant.INITIALIZE_ENTRENAMIENTO_PROFILE]: async (store) => {
            try {
                const { data } = await profileService.Entrenamientos();

                store.commit(Constant.INITIALIZE_ENTRENAMIENTO_PROFILE, data.entrenamientos);
            } catch (error) {
                console.error("Error al cargar las entrenamientos:", error);
            }
        },
        [Constant.INITIALIZE_RESERVA_PROFILE]: async (store) => {
            try {
                const { data } = await profileService.Reservas();
                console.log(data);

                store.commit(Constant.INITIALIZE_RESERVA_PROFILE, data);
            } catch (error) {
                console.error("Error al cargar las reservas:", error);
            }
        },
        [Constant.INITIALIZE_GRAFICA_PROFILE]: async (store, año) => {
            try {
                const { data } = await profileService.Graficas(año);

                store.commit(Constant.INITIALIZE_GRAFICA_PROFILE, data.graficas);
            } catch (error) {
                console.error("Error al cargar las graficas:", error);
            }
        },
        [Constant.INITIALIZE_RESERVAS_ACTIVAS]: async (store) => {
            try {
                const { data } = await profileService.ReservasActivas();
                store.commit(Constant.INITIALIZE_RESERVAS_ACTIVAS, data.reservas);
            } catch (error) {
                console.error("Error al cargar las reservas activas:", error);
            }
        },
        [Constant.INITIALIZE_RESERVAS_EXPIRADAS]: async (store) => {
            try {
                const { data } = await profileService.ReservasExpiradas();
                store.commit(Constant.INITIALIZE_RESERVAS_EXPIRADAS, data.reservas);
            } catch (error) {
                console.error("Error al cargar las reservas expiradas:", error);
            }
        },
        [Constant.CANCELAR_ENTRENAMIENTO]: async (store, slug) => {
            try {
                await profileService.CancelarEntrenamiento(slug);
                store.commit(Constant.CANCELAR_ENTRENAMIENTO, slug);
                return true;
            } catch (error) {
                console.error("Error al cancelar el entrenamiento:", error);
                throw error;
            }
        },
        [Constant.INITIALIZE_LOGROS]: async (store) => {
            try {
                const { data } = await profileService.Logros();
                store.commit(Constant.INITIALIZE_LOGROS, data.logros);
            } catch (error) {
                console.error("Error al cargar los logros:", error);
            }
        }
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO_PROFILE](state, payload) {
            if (payload) {
                state.entrenamientos = payload;
            }
        },
        [Constant.INITIALIZE_RESERVA_PROFILE](state, payload) {
            if (payload) {
                state.reservas = payload;
            }
        },
        [Constant.INITIALIZE_GRAFICA_PROFILE](state, payload) {
            if (payload) {
                state.graficas = payload;
            }
        },
        [Constant.INITIALIZE_RESERVAS_ACTIVAS](state, payload) {
            if (payload) {
                state.reservasActivas = payload;
            }
        },
        [Constant.INITIALIZE_RESERVAS_EXPIRADAS](state, payload) {
            if (payload) {
                state.reservasExpiradas = payload;
            }
        },
        [Constant.CANCELAR_ENTRENAMIENTO](state, slug) {
            //Eliminar el entrenamiento de las reservas activas
            state.reservasActivas = state.reservasActivas.filter(reserva => reserva.slug !== slug);
        },
        [Constant.INITIALIZE_LOGROS](state, payload) {
            if (payload) {
                state.logros = payload;
            }
        }
    },

    getters: {
        GetEntrenamientosProfile(state) {
            return state.entrenamientos;
        },
        GetReservasProfile(state) {
            return state.reservas;
        },
        GetGraficasProfile(state) {
            return state.graficas;
        },
        GetReservasActivas(state) {
            return state.reservasActivas;
        },
        GetReservasExpiradas(state) {
            return state.reservasExpiradas;
        },
        GetLogros(state) {
            return state.logros;
        }
    }
};
