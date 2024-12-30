import entrenadorDashboardService from '../../../services/dashboards/entrenadorDashboard.service';
import Constant from '../../../Constant';

export const entrenadorDashboard = {
    namespaced: true,

    state: {
        entrenamientos: [],
        graficas: [],
        profile: {},
        entrenador: null,
        pistasPrivadas: [], 
        horarios: [],
        horariosOcupados: []
    },

    mutations: {
        [Constant.INITIALIZE_ENTRENAMIENTO](state, payload) {
            if (payload) {
                state.entrenamientos = payload;
            }
        },
        [Constant.INITIALIZE_ENTRENADOR](state, payload) {
            state.entrenador = payload; 
          },
        [Constant.CREATE_ONE_ENTRENAMIENTO](state, nuevoEntrenamiento) {
            state.entrenamientos.push(nuevoEntrenamiento);  
        },
        [Constant.DELETE_ONE_ENTRENAMIENTO](state, entrenamientoId) {
            state.entrenamientos = state.entrenamientos.filter(entrenamiento => entrenamiento.id !== entrenamientoId);
        },
        [Constant.FETCH_ENTRENAMIENTOS_OCUPADOS](state, horariosOcupados) {
            state.horariosOcupados = horariosOcupados; 
        },

        [Constant.INITIALIZE_GRAFICAS](state, payload) {
            if (payload) {
              state.graficas = payload;
            }
          },
      
          [Constant.UPDATE_ONE_GRAFICA](state, updatedGraficas) {
            const index = state.graficas.findIndex(graficas => graficas.id === updatedGraficas.id);
            if (index !== -1) {
              state.graficas.splice(index, 1, updatedGraficas);
            }
          },
          [Constant.INITIALIZE_PROFILE](state, payload) {
            state.profile = payload || {};  
        },
        [Constant.INITIALIZE_PISTA_PRIVADA](state, payload) {
            if (payload) {
                state.pistasPrivadas = payload;
            }
        },
        [Constant.INITIALIZE_HORARIO](state, payload) {
            if (payload) {
                state.horarios = payload;
            }
        },
    },

    actions: {
        async [Constant.INITIALIZE_ENTRENADOR]({ commit }) {
            try {
              const response = await entrenadorDashboardService.GetEntrenador();
              console.log(response+ "entrenador");
              commit(Constant.INITIALIZE_ENTRENADOR, response.data);
            } catch (error) {
              console.error("Error al cargar el entrenador:", error);
            }
          },
        [Constant.INITIALIZE_ENTRENAMIENTO]: async (store) => {
            try {
                const { data } = await entrenadorDashboardService.GetEntrenamientos();
                store.commit(Constant.INITIALIZE_ENTRENAMIENTO, data.data);
            } catch (error) {
                console.error("Error al cargar los entrenamientos:", error);
            }
        },

        [Constant.CREATE_ONE_ENTRENAMIENTO]: async (store, nuevoEntrenamiento) => {
            try {
                console.log("nuevoEntrenamiento"+JSON.stringify(nuevoEntrenamiento));
                const { data } = await entrenadorDashboardService.CreateEntrenamiento(nuevoEntrenamiento);
                store.commit(Constant.CREATE_ONE_ENTRENAMIENTO, data.data); 
            } catch (error) {
                console.error("Error al crear el entrenamiento:", error);
            }
        },  
        async [Constant.DELETE_ONE_ENTRENAMIENTO]({ store}, entrenamientoId) {
            try {
                const { data } = await entrenadorDashboardService.DeleteEntrenamiento(entrenamientoId);
                store.commit(Constant.INITIALIZE_ENTRENAMIENTO, data.data);
            } catch (error) {
                console.error("Error al eliminar el entrenamiento:", error);
                throw error;
            }
        },
        async [Constant.INITIALIZE_GRAFICAS]({ store }, profileId) {
            try {
              const response = await entrenadorDashboardService.GetGraficas(profileId);
              console.log('Respuesta de la API:', response.data);
             store.commit(Constant.INITIALIZE_GRAFICAS, response.data);
            } catch (error) {
              console.error('Error al obtener gráficas:', error);
            }
          },
        [Constant.UPDATE_ONE_GRAFICA]: async (store, { id, graficas }) => {
            try {
                console.log("id:", id, "grafica:", graficas);  
        
                if (!graficas || !graficas.seccion || graficas.nivel === undefined) {
                    console.error("Error: 'graficas' está mal formada o vacía:", graficas);
                    return;
                }
        
                const { data } = await entrenadorDashboardService.UpdateGraficas(id, graficas);
                console.log("data"+data)
                store.commit(Constant.UPDATE_ONE_GRAFICA, data); 
            } catch (error) {
                console.error("Error al actualizar la gráfica:", error);
            }
        },

          [Constant.INITIALIZE_PROFILE]: async (store, profileId) => {
            try {
                const { data } = await entrenadorDashboardService.GetProfile(profileId); 
                store.commit(Constant.INITIALIZE_PROFILE, data.data); 
            } catch (error) {
                console.error("Error al cargar el profile:", error);
            }
        },
        async [Constant.INITIALIZE_PISTA_PRIVADA]({ commit }) {
            try {
                const { data } = await entrenadorDashboardService.GetPistasPrivadas(); 
                commit(Constant.INITIALIZE_PISTA_PRIVADA, data.data);
            } catch (error) {
                console.error("Error al obtener pistas privadas:", error);
            }
        },

        async [Constant.FETCH_HORARIOS_PISTA_PRIVADA]({ commit }, pistaId) {
            try {
              const { data } = await entrenadorDashboardService.GetHorarios(pistaId);
              return data.data;
            } catch (error) {
              console.error("Error al obtener horarios de pista privada:", error);
              throw error;
            }
          },
          async [Constant.FETCH_ENTRENAMIENTOS_OCUPADOS]({ commit }, pistaId) {
            try {
                const { data } = await entrenadorDashboardService.GetEntrenamientosOcupados(pistaId);
                console.log("Data de horarios ocupados:", data);
                commit(Constant.FETCH_ENTRENAMIENTOS_OCUPADOS, data);
            } catch (error) {
                console.error("Error al obtener entrenamientos ocupados:", error);
                throw error;
            }
        }
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
        GetEntrenador(state){
            return state.entrenador;
        },
        GetHorariosOcupados(state) {
            return state.horariosOcupados;
        }
    }
};
