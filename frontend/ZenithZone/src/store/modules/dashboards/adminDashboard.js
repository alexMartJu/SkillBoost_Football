import Constant from '../../../Constant';
import adminDashboardService from '../../../services/dashboards/adminDashboard.service';

export const adminDashboard = {
    namespaced: true,

    state: {
        deportes: [],
        pistas: []
    },

    mutations: {
        [Constant.INITIALIZE_DEPORTE](state, payload) {
            if (payload) {
                state.deportes = payload;
            }
        },
        [Constant.INITIALIZE_PISTA](state, payload) {
            if (payload) {
                state.pistas = payload;
            }
        },
        [Constant.CREATE_ONE_DEPORTE](state, newDeporte) {
            state.deportes.push(newDeporte); 
          },
          // Crear una pista
          [Constant.CREATE_ONE_PISTA](state, newPista) {
            state.pistas.push(newPista); 
          },
          [Constant.SET_CURRENT_PISTA](state, pista) {
            state.currentPista = pista; 
        },
    },

    actions: {
        async [Constant.INITIALIZE_DEPORTE](store) {
            try {
                const { data } = await adminDashboardService.GetDeportes();
                store.commit(Constant.INITIALIZE_DEPORTE, data.data);
            } catch (error) {
                console.error("Error al cargar los deportes:", error);
            }
        },
        
        async [Constant.INITIALIZE_PISTA](store) {
            try {
                const { data } = await adminDashboardService.GetPistas();
                store.commit(Constant.INITIALIZE_PISTA, data.data);
            } catch (error) {
                console.error("Error al cargar las pistas:", error);
            }
        },
    
    async [Constant.CREATE_ONE_DEPORTE]({ commit }, newDeporte) {
        try {
          const { data } = await adminDashboardService.CreateDeporte(newDeporte);
          commit(Constant.CREATE_ONE_DEPORTE, data.data); // Agregar el deporte creado
        } catch (error) {
          console.error("Error al crear el deporte:", error);
        }
      },
  
      // Crear una pista
      async [Constant.CREATE_ONE_PISTA]({ commit }, newPista) {
        try {
          const { data } = await adminDashboardService.CreatePista(newPista);
          commit(Constant.CREATE_ONE_PISTA, data.data); 
        } catch (error) {
          console.error("Error al crear la pista:", error);
        }
      },
        async [Constant.FETCH_PISTA_BY_SLUG]({ commit }, slug) {
            try {
                console.log("fetched");
                const { data } = await adminDashboardService.GetPistaBySlug(slug); 
                console.log("data"+JSON.stringify(data.data));
                commit(Constant.SET_CURRENT_PISTA, data.data); 
                return data.data;
            } catch (error) {
                console.error("Error al cargar la pista:", error);
            }
        },
        async [Constant.UPDATE_ONE_PISTA]({ commit }, slug) {
           console.log("hola");
        },
    },

    getters: {
        GetDeportes(state) {
            return state.deportes;
        },
        GetPistas(state) {
            return state.pistas;
        },
        GetCurrentPista(state) {
            return state.currentPista; 
          },
    }
};