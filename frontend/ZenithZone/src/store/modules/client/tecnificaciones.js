import tecnificacionesService from '../../../services/client/tecnificaciones.service';
import Constant from '../../../Constant';

export const tecnificaciones = {
    namespaced: true,

    state: {
        tecnificaciones: []
    },

    mutations: {
        [Constant.INITIALIZE_TECNIFICACION](state, payload) {
            if (payload) {
                state.tecnificaciones = payload;
            }
        }
    },

    actions: {
        [Constant.INITIALIZE_TECNIFICACION]: async (store) => {
            try {
                const { data } = await tecnificacionesService.GetTecnificaciones();
                store.commit(Constant.INITIALIZE_TECNIFICACION, data.tecnificaciones);
            } catch (error) {
                console.error("Error al cargar las tecnificaciones:", error);
            }
        },
    },

    getters: {
        GetTecnificaciones(state) {
            return state.tecnificaciones;
        },
    }
};