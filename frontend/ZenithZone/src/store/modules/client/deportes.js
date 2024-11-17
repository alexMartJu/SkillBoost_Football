import deportesService from '../../../services/client/deportes.service';
import Constant from '../../../Constant';

export const deportes = {
    namespaced: true,

    state: {
        deportes: []
    },

    mutations: {
        [Constant.INITIALIZE_DEPORTE](state, payload) {
            if (payload) {
                state.deportes = payload;
            }
        }
    },

    actions: {
        [Constant.INITIALIZE_DEPORTE]: async (store) => {
            try {
                const { data } = await deportesService.GetDeportes();
                // console.log(data);

                store.commit(Constant.INITIALIZE_DEPORTE, data);
            } catch (error) {
                console.error("Error al cargar los deportes:", error);
            }
        },
    },

    getters: {
        GetDeportes(state) {
            return state.deportes;
        },
    }
};