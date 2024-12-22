import Constant from '../../../Constant';
import entrenamientosService from '../../../services/client/entrenamientos.service';

export const entrenamientos = {
    namespaced: true,

    state: {
        entrenamientos: []
    },

    actions: {
        [Constant.INITIALIZE_ENTRENAMIENTO]: async (store) => {
            try {
                const { data } = await entrenamientosService.GetEntrenamientos();
                // console.log(data);

                store.commit(Constant.INITIALIZE_ENTRENAMIENTO, data);
            } catch (error) {
                console.error("Error al cargar las entrenamientos:", error);
            }
        },
        [Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO]: async (store, slug) => {
            try {
                const { data } = await entrenamientosService.GetOneEntrenamiento(slug);
                // console.log(data);

                store.commit(Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO, data);
            } catch (error) {
                console.error("Error al cargar el entrenamiento:", error);
            }
        }
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                console.log(payload);
                state.entrenamientos = payload;
            }
        },
        [Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                state.entrenamientos = payload;
            }
        }
    },

    getters: {
        GetEntrenamientos(state) {
            return state.entrenamientos;
        },
        GetOneEntrenamiento(state) {
            return state.entrenamientos;
        }
    }
};