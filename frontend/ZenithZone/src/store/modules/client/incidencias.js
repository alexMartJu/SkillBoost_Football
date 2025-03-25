import Constant from '../../../Constant';
import incidenciaService from '../../../services/client/incidencia.service';

export const incidencias = {
    namespaced: true,

    state: {
        incidencias: [],
        loading: false,
        error: null
    },

    actions: {
        [Constant.CREATE_INCIDENCIA]: async (store, incidenciaData) => {
            try {
                store.commit(Constant.SET_LOADING_INCIDENCIAS, true);
                const { data } = await incidenciaService.createIncidencia(incidenciaData);
                
                store.commit(Constant.ADD_INCIDENCIA, data);
                return data;
            } catch (error) {
                console.error("Error al crear la incidencia:", error);
                store.commit(Constant.SET_ERROR_INCIDENCIAS, error.message || 'Error al crear la incidencia');
                throw error;
            } finally {
                store.commit(Constant.SET_LOADING_INCIDENCIAS, false);
            }
        }
    },

    mutations: {
        [Constant.SET_LOADING_INCIDENCIAS](state, isLoading) {
            state.loading = isLoading;
        },
        [Constant.SET_ERROR_INCIDENCIAS](state, error) {
            state.error = error;
        },
        [Constant.ADD_INCIDENCIA](state, incidencia) {
            state.incidencias.unshift(incidencia); //Añadir al principio del array
        }
    },

    getters: {
        GetIncidencias(state) {
            return state.incidencias;
        },
        IsLoading(state) {
            return state.loading;
        },
        GetError(state) {
            return state.error;
        }
    }
};
