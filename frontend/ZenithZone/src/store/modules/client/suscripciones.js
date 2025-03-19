import Constant from '../../../Constant';
import suscripcionesService from '../../../services/client/suscripciones.service';

export const suscripciones = {
    namespaced: true,

    state: {
        suscripciones: []
    },

    actions: {
        [Constant.INITIALIZE_SUSCRIPCION]: async (store) => {
            try {
                const response = await suscripcionesService.GetSuscripciones();
                // Extract the suscripciones array from the response
                const suscripcionesData = response.data.suscripciones;
                store.commit(Constant.INITIALIZE_SUSCRIPCION, suscripcionesData);
            } catch (error) {
                console.error("Error al cargar las suscripciones:", error);
            }
        },
        [Constant.UPDATE_ONE_SUSCRIPCION_PRECIO]: async (store, { slug, precio }) => {
            try {
                const { data } = await suscripcionesService.UpdatePrecio(slug, precio);
                store.commit(Constant.UPDATE_ONE_SUSCRIPCION, data);
                return data;
            } catch (error) {
                console.error("Error al actualizar el precio de la suscripción:", error);
                throw error;
            }
        }
    },

    mutations: {
        [Constant.INITIALIZE_SUSCRIPCION](state, payload) {
            if (payload) {
                state.suscripciones = payload;
            }
        },
        [Constant.UPDATE_ONE_SUSCRIPCION](state, payload) {
            if (payload) {
                const index = state.suscripciones.findIndex(s => s.slug === payload.slug);
                if (index !== -1) {
                    state.suscripciones[index] = payload;
                }
            }
        }
    },

    getters: {
        GetSuscripciones(state) {
            return state.suscripciones;
        }
    }
};
