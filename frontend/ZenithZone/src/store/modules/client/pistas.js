import pistaService from '../../../services/client/pistas.service';
import Constant from '../../../Constant';

export const pistas = {
    namespaced: true,

    state: {
        pistas: []
    },

    mutations: {
        [Constant.INITIALIZE_PISTA](state, payload) {
            if (payload) {
                state.pistas = payload;
            }
        }
    },

    actions: {
        [Constant.INITIALIZE_PISTA]: async (store) => {
            try {
                const { data } = await pistaService.GetPistas();
                // console.log(data);

                store.commit(Constant.INITIALIZE_PISTA, data);
            } catch (error) {
                console.error("Error al cargar las pistas:", error);
            }
        },
    },

    getters: {
        GetPistas(state) {
            return state.pistas;
        },
    }
};