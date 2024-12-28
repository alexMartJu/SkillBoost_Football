import Constant from '@/Constant';
import profileService from '@/services/client/profile.service';
// import entrenamientosService from '../../../services/client/entrenamientos.service';

export const profile = {
    namespaced: true,

    state: {
        entrenamientos: [],
        reservas: [],
        graficas: []
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
        [Constant.INITIALIZE_RESERVA_PROFILE]: async (store, numeroSocio) => {
            try {
                const { data } = await profileService.Reservas(numeroSocio);
                // console.log(data);

                store.commit(Constant.INITIALIZE_RESERVA_PROFILE, data.reservas);
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
            // console.log(state.graficas);
            return state.graficas;
        }
    }
};