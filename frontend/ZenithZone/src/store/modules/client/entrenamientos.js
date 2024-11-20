import Constant from '../../../Constant';
import entrenamientosService from '../../../services/client/entrenamientos.service';

export const entrenamientos = {
    namespaced: true,

    state: {
        entrenamientos: []
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                state.entrenamientos = payload;
            }
        }
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
    },

    getters: {
        GetEntrenamientos(state) {
            return state.entrenamientos;
        },
    }
};