import entrenadorDashboardService from '../../../services/dashboards/entrenadorDashboard.service';
import Constant from '../../../Constant';

export const entrenadorDashboard = {
    namespaced: true,

    state: {
        entrenamientos: [],
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                state.entrenamientos = payload;
            }
        },

        [Constant.CREATE_ONE_ENTRENAMIENTO](state, nuevoEntrenamiento) {
            state.entrenamientos.push(nuevoEntrenamiento);  // Añadir el nuevo entrenamiento a la lista
        },
    },

    actions: {
        // Acción para inicializar los entrenamientos
        [Constant.INITIALIZE_ENTRENAMIENTO]: async (store) => {
            try {
                const { data } = await entrenadorDashboardService.GetEntrenamientos();
                store.commit(Constant.INITIALIZE_ENTRENAMIENTO, data.data);
            } catch (error) {
                console.error("Error al cargar los entrenamientos:", error);
            }
        },

        // Acción para crear un nuevo entrenamiento
        [Constant.CREATE_ONE_ENTRENAMIENTO]: async (store, nuevoEntrenamiento) => {
            try {
                const { data } = await entrenadorDashboardService.CreateEntrenamiento(nuevoEntrenamiento);
                store.commit(Constant.CREATE_ONE_ENTRENAMIENTO, data.data);  // Añadir el entrenamiento creado a la lista
            } catch (error) {
                console.error("Error al crear el entrenamiento:", error);
            }
        },
    },

    getters: {
        GetEntrenamientos(state) {
            return state.entrenamientos;
        },
    }
};
