import subtiposTecnificacionService from '../../../services/client/subtiposTecnificacion.service';
import Constant from '../../../Constant';

export const subtiposTecnificacion = {
    namespaced: true,

    state: {
        subtiposTecnificacion: []
    },

    actions: {
        [Constant.INITIALIZE_SUBTIPO_TECNIFICACION]: async (store) => {
            try {
                const { data } = await subtiposTecnificacionService.GetSubtiposTecnificacion();
                store.commit(Constant.INITIALIZE_SUBTIPO_TECNIFICACION, data.subtipos);
            } catch (error) {
                console.error("Error al cargar los subtipos de tecnificación:", error);
            }
        },

        [Constant.INITIALIZE_ONE_STATE_SUBTIPO_TECNIFICACION]: async (store, slug) => {
            try {
                const { data } = await subtiposTecnificacionService.GetOneSubtipoTecnificacion(slug);
                store.commit(Constant.INITIALIZE_ONE_STATE_SUBTIPO_TECNIFICACION, data);
            } catch (error) {
                console.error("Error al cargar el subtipo de tecnificación:", error);
            }
        },
    },

    mutations: {
        [Constant.INITIALIZE_SUBTIPO_TECNIFICACION](state, payload) {
            if (payload) {
                state.subtiposTecnificacion = payload;
            }
        },
        [Constant.INITIALIZE_ONE_STATE_SUBTIPO_TECNIFICACION](state, payload) {
            if (payload) {
                state.subtiposTecnificacion = payload;
            }
        },
    },

    getters: {
        GetSubtiposTecnificacion(state) {
            return state.subtiposTecnificacion;
        },
        GetOneSubtipoTecnificacion(state) {
            return state.subtiposTecnificacion;
        }
    }
};