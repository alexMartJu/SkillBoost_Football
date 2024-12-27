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

                store.commit(Constant.INITIALIZE_ENTRENADOR, data.entrenadores);
            } catch (error) {
                console.error("Error al cargar las entrenamientos:", error);
            }
        },
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENADOR](state, payload) {
            if (payload) {
                console.log(payload);
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