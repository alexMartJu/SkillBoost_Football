import Constant from '../../../Constant';
import entrenamientosService from '../../../services/client/entrenamientos.service';

export const entrenamientos = {
    namespaced: true,

    state: {
        entrenamientos: [],
        suscribedEntrenamientos: new Set()
    },

    actions: {
        [Constant.INITIALIZE_ENTRENAMIENTO]: async (store) => {
            try {
                const { data } = await entrenamientosService.GetEntrenamientos();

                store.commit(Constant.INITIALIZE_ENTRENAMIENTO, data);
            } catch (error) {
                console.error("Error al cargar las entrenamientos:", error);
            }
        },
        [Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO]: async (store, slug) => {
            try {
                const { data } = await entrenamientosService.GetOneEntrenamiento(slug);

                store.commit(Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO, data);
            } catch (error) {
                console.error("Error al cargar el entrenamiento:", error);
            }
        },
        //Acción para cargar los entrenamientos suscritos
        [Constant.FETCH_SUSCRIBED_ENTRENAMIENTOS]: async (store) => {
            try {
                const response = await entrenamientosService.GetSuscribedEntrenamientos();
                
                if (response.data && response.data.reservas && Array.isArray(response.data.reservas)) {
                    const slugs = response.data.reservas
                        .filter(reserva => reserva.entrenamiento && reserva.entrenamiento.slug)
                        .map(reserva => reserva.entrenamiento.slug);
                    
                    store.commit(Constant.SET_SUSCRIBED_ENTRENAMIENTOS, slugs);
                } else {
                    console.warn("Estructura de respuesta inesperada:", response.data);
                }
            } catch (error) {
                console.error("Error al cargar los entrenamientos suscritos:", error);
            }
        }

    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                console.log(payload);
                state.entrenamientos = payload;
            }
        },
        [Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                state.entrenamientos = payload;
            }
        },
        [Constant.SET_SUSCRIBED_ENTRENAMIENTOS](state, slugs) {
            state.suscribedEntrenamientos = new Set(slugs);
        }
    },

    getters: {
        GetEntrenamientos(state) {
            return state.entrenamientos;
        },
        GetOneEntrenamiento(state) {
            return state.entrenamientos;
        },
        isEntrenamientoSuscribed: (state) => (slug) => {
            return state.suscribedEntrenamientos && state.suscribedEntrenamientos.has(slug);
        },
        getAllSuscribedEntrenamientos(state) {
            return state.suscribedEntrenamientos;
        }
    }
};