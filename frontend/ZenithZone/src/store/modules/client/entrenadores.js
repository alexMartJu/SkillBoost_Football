import Constant from '@/Constant';
import entrenadorService from '@/services/client/entrenador.service';

export const entrenadores = {
    namespaced: true,

    state: {
        entrenadores: [],
    },

    actions: {
        [Constant.INITIALIZE_ENTRENADOR]: async (store) => {
            try {
                const { data } = await entrenadorService.GetEntrenadores();
                console.log(data.data);

                store.commit(Constant.INITIALIZE_ENTRENADOR, data.data);
            } catch (error) {
                console.error("Error al cargar las entrenamientos:", error);
            }
        },
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENADOR](state, payload) {
            if (payload) {
                state.entrenadores = payload;
            }
        },
    },

    getters: {
        GetEntrenadores(state) {
            return state.entrenadores;
        },
    }
};