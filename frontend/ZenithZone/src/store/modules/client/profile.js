import Constant from '@/Constant';
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
                const { data } = await entrenamientosService.GetEntrenamientos();
                // console.log(data);

                store.commit(Constant.INITIALIZE_ENTRENAMIENTO_PROFILE, data);
            } catch (error) {
                console.error("Error al cargar las entrenamientos:", error);
            }
        },
        [Constant.INITIALIZE_RESERVA_PROFILE]: async (store, slug) => {
            try {
                const { data } = await entrenamientosService.GetOneEntrenamiento(slug);
                // console.log(data);

                store.commit(Constant.INITIALIZE_RESERVA_PROFILE, data);
            } catch (error) {
                console.error("Error al cargar las reservas:", error);
            }
        },
        [Constant.INITIALIZE_GRAFICA_PROFILE]: async (store, slug) => {
            try {
                const { data } = await entrenamientosService.GetOneEntrenamiento(slug);
                // console.log(data);

                store.commit(Constant.INITIALIZE_GRAFICA_PROFILE, data);
            } catch (error) {
                console.error("Error al cargar las graficas:", error);
            }
        },
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                console.log(payload);
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
            return state.entrenamientos;
        },
        GetGraficasProfile(state) {
            return state.graficas;
        }
    }
};