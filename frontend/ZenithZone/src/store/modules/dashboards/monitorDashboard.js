import monitorDashboardService from '../../../services/dashboards/monitorDashboard.service';
import Constant from '../../../Constant';

export const monitorDashboard = {
    namespaced: true,

    state: {
        clases: [],
    },

    mutations: {
        [Constant.INITIALIZE_CLASE](state, payload) {
            if (payload) {
                state.clases = payload;
            }
        },

        [Constant.CREATE_ONE_CLASE](state, nuevaClase) {
            state.clases.push(nuevaClase);  // Añadir la nueva clase a la lista
        },
    },

    actions: {
        // Acción para inicializar las clases
        [Constant.INITIALIZE_CLASE]: async (store) => {
            try {
                const { data } = await monitorDashboardService.GetClases();
                store.commit(Constant.INITIALIZE_CLASE, data.data);
            } catch (error) {
                console.error("Error al cargar las clases:", error);
            }
        },

        // Acción para crear una nueva clase
        [Constant.CREATE_ONE_CLASE]: async (store, nuevaClase) => {
            try {
                const { data } = await monitorDashboardService.CreateClase(nuevaClase);
                store.commit(Constant.CREATE_ONE_CLASE, data.data);  // Añadir la clase creada a la lista
            } catch (error) {
                console.error("Error al crear la clase:", error);
            }
        },
    },

    getters: {
        GetClases(state) {
            return state.clases;
        },
    }
};
