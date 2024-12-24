import pistaService from '../../../services/client/pistas.service';
import Constant from '../../../Constant';

export const pistas = {
    namespaced: true,

    state: {
        pistas: []
    },

    actions: {
        [Constant.INITIALIZE_PISTA]: async (store) => {
            try {
                const { data } = await pistaService.GetPistas();
                // console.log(data);

                store.commit(Constant.INITIALIZE_PISTA, data.pistas);
            } catch (error) {
                console.error("Error al cargar las pistas:", error);
            }
        },

        [Constant.INITIALIZE_ONE_STATE_PISTA]: async (store, slug) => {
            console.log(`slug: ${slug}`);
            try {
                const { data } = await pistaService.GetOnePista(slug);
                store.commit(Constant.INITIALIZE_ONE_STATE_PISTA, data);
            } catch (error) {
                console.error("Error al cargar la pista:", error);
            }
        },
    },

    mutations: {
        [Constant.INITIALIZE_PISTA](state, payload) {
            if (payload) {
                state.pistas = payload;
            }
        },
        [Constant.INITIALIZE_ONE_STATE_PISTA](state, payload) {
            if (payload) {
                state.pistas = payload;
            }
        },
    },

    getters: {
        GetPistas(state) {
            return state.pistas;
        },
        GetOnePista(state) {
            return state.pistas;
        }
    }
};