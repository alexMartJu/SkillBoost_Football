import entrenadorDashboardService from '../../../services/dashboards/entrenadorDashboard.service';
import Constant from '../../../Constant';

export const entrenadorDashboard = {
    namespaced: true,

    state: {
        entrenamientos: [],
        gráficas: [],
        profile: {},
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

        [Constant.INITIALIZE_GRAFICAS](state, payload) {
            if (payload) {
              state.gráficas = payload;
            }
          },
      
          [Constant.UPDATE_ONE_GRAFICA](state, updatedGrafica) {
            const index = state.gráficas.findIndex(grafica => grafica.id === updatedGrafica.id);
            if (index !== -1) {
              state.gráficas.splice(index, 1, updatedGrafica);
            }
          },
          [Constant.INITIALIZE_PROFILE](state, payload) {
            state.profile = payload || {};  
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
        [Constant.INITIALIZE_GRAFICAS]: async (store, id) => {
            try {
              const { data } = await entrenadorDashboardService.GetGraficas(id);
              store.commit(Constant.INITIALIZE_GRAFICAS, data.data);
            } catch (error) {
              console.error("Error al cargar las gráficas del alumno:", error);
            }
          },
      
          // Acción para actualizar las gráficas de un alumno
          [Constant.UPDATE_ONE_GRAFICA]: async (store, { id, graficas }) => {
            try {
              const { data } = await entrenadorDashboardService.UpdateGraficas(id, graficas);
              store.commit(Constant.UPDATE_ONE_GRAFICA, data.data);  // Actualizar las gráficas en el estado
            } catch (error) {
              console.error("Error al actualizar las gráficas:", error);
            }
          },

          [Constant.INITIALIZE_PROFILE]: async (store, profileId) => {
            try {
                const { data } = await entrenadorDashboardService.GetProfile(profileId); 
                console.log("Data"+data.data);
                store.commit(Constant.INITIALIZE_PROFILE, data.data); 
            } catch (error) {
                console.error("Error al cargar el profile:", error);
            }
        },
    },

    getters: {
        GetEntrenamientos(state) {
            return state.entrenamientos;
        },
        GetProfile(state) {
            return state.profile;
        },
        GetGraficas(state) {
            return state.graficas;
          },
    }
};
