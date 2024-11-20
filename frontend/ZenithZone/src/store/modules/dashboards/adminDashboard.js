import Constant from '../../../Constant';
import adminDashboardService from '../../../services/dashboards/adminDashboard.service';

export const adminDashboard = {
    namespaced: true,

    state: {
        deportes: [],
        pistas: []
    },

    mutations: {
        [Constant.INITIALIZE_DEPORTE](state, payload) {
            if (payload) {
                state.deportes = payload;
            }
        },
        [Constant.INITIALIZE_PISTA](state, payload) {
            if (payload) {
                state.pistas = payload;
            }
        }
    },

    actions: {
        async [Constant.INITIALIZE_DEPORTE](store) {
            try {
                const { data } = await adminDashboardService.GetDeportes();
                store.commit(Constant.INITIALIZE_DEPORTE, data.data);
            } catch (error) {
                console.error("Error al cargar los deportes:", error);
            }
        },
        
        async [Constant.INITIALIZE_PISTA](store) {
            try {
                const { data } = await adminDashboardService.GetPistas();
                store.commit(Constant.INITIALIZE_PISTA, data.data);
            } catch (error) {
                console.error("Error al cargar las pistas:", error);
            }
        },
    },

    getters: {
        GetDeportes(state) {
            return state.deportes;
        },
        GetPistas(state) {
            return state.pistas;
        },
    }
};