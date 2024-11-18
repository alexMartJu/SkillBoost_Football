import claseService from '../../../services/client/clases.service';
import Constant from '../../../Constant';

export const clases = {
    namespaced: true,

    state: {
        clases: []
    },

    mutations: {
        [Constant.INITIALIZE_CLASE](state, payload) {
            if (payload) {
                state.clases = payload;
            }
        }
    },

    actions: {
        [Constant.INITIALIZE_CLASE]: async (store) => {
            try {
                const { data } = await claseService.GetClases();
                // console.log(data);

                store.commit(Constant.INITIALIZE_CLASE, data);
            } catch (error) {
                console.error("Error al cargar las clases:", error);
            }
        },
    },

    getters: {
        GetClases(state) {
            return state.clases;
        },
    }
};