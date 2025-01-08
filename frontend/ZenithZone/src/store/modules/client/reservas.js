import pistaService from '../../../services/client/pistas.service';
import Constant from '../../../Constant';
import reservasService from '@/services/client/reservas.service';

export const reservas = {
    namespaced: true,

    state: {
        reservas: []
    },

    actions: {
        [Constant.INITIALIZE_RESERVA]: async (store) => {
            try {
                const { data } = await reservasService.GetReservas();
                console.log(data);

                store.commit(Constant.INITIALIZE_RESERVA, data);
            } catch (error) {
                console.error("Error al cargar las pistas:", error);
            }
        },
    },

    mutations: {
        [Constant.INITIALIZE_RESERVA](state, payload) {
            if (payload) {
                state.reservas = payload;
            }
        },
    },

    getters: {
        GetPistas(state) {
            return state.reservas;
        },
    }
};