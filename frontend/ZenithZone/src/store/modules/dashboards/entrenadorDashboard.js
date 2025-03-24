import entrenadorDashboardService from '../../../services/dashboards/entrenadorDashboard.service';
import Constant from '../../../Constant';

export const entrenadorDashboard = {
    namespaced: true,

    state: {
        pistas: [],
        horariosOcupados: [],
        selectedPista: null,
        tecnificaciones: [],
        subtiposTecnificacion: [],
        loading: false,
        error: null,
        pendingEntrenamientos: [],
        approvedEntrenamientos: [],
        deniedEntrenamientos: [],
    },

    mutations: {
        [Constant.SET_PISTAS](state, pistas) {
            state.pistas = pistas;
        },
        [Constant.SET_SELECTED_PISTA](state, pista) {
            state.selectedPista = pista;
        },
        [Constant.SET_HORARIOS_OCUPADOS](state, horarios) {
            state.horariosOcupados = horarios;
        },
        [Constant.SET_LOADING](state, status) {
            state.loading = status;
        },
        [Constant.SET_ERROR](state, error) {
            state.error = error;
        },
        [Constant.SET_TECNIFICACIONES](state, tecnificaciones) {
            state.tecnificaciones = tecnificaciones;
        },
        [Constant.SET_SUBTIPOS_TECNIFICACION](state, subtipos) {
            state.subtiposTecnificacion = subtipos;
        },
        [Constant.SET_PENDING_ENTRENAMIENTOS](state, entrenamientos) {
            state.pendingEntrenamientos = entrenamientos;
        },
        [Constant.SET_APPROVED_ENTRENAMIENTOS](state, entrenamientos) {
            state.approvedEntrenamientos = entrenamientos;
        },
        [Constant.SET_DENIED_ENTRENAMIENTOS](state, entrenamientos) {
            state.deniedEntrenamientos = entrenamientos;
        },
    },

    actions: {
        //Cargar todas las pistas
        async [Constant.FETCH_PISTAS]({ commit }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetAllPistas();
                commit(Constant.SET_PISTAS, response.data.pistas);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar pistas:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar las pistas');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        //Seleccionar una pista
        [Constant.SET_SELECTED_PISTA]({ commit }, pista) {
            commit(Constant.SET_SELECTED_PISTA, pista);
        },
        //Cargar horarios ocupados de una pista
        async [Constant.FETCH_HORARIOS_OCUPADOS]({ commit }, { pistaSlug, fecha }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetHorariosOcupados(pistaSlug, fecha);
                commit(Constant.SET_HORARIOS_OCUPADOS, response.data.horariosOcupados);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar horarios ocupados:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar los horarios ocupados');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        //Crear un nuevo entrenamiento
        async [Constant.CREATE_ONE_ENTRENAMIENTO]({ commit }, entrenamiento) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.CreateEntrenamiento(entrenamiento);
                commit(Constant.SET_ERROR, null);
                return response.data;
            } catch (error) {
                console.error('Error al crear entrenamiento:', error);
                if (error.response && error.response.status === 500) {
                    commit(Constant.SET_ERROR, 'El horario seleccionado se solapa con otro entrenamiento en esta pista');
                } else {
                    commit(Constant.SET_ERROR, 'No se pudo crear el entrenamiento');
                }
                throw error;
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.FETCH_TECNIFICACIONES]({ commit }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetAllTecnificaciones();
                commit(Constant.SET_TECNIFICACIONES, response.data.tecnificaciones);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar tecnificaciones:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar las tecnificaciones');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.FETCH_SUBTIPOS_TECNIFICACION]({ commit }, tecnificacionSlug) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetSubtiposByTecnificacion(tecnificacionSlug);
                commit(Constant.SET_SUBTIPOS_TECNIFICACION, response.data.subtipos);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar subtipos de tecnificación:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar los subtipos de tecnificación');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        // Cargar entrenamientos pendientes
        async fetchPendingEntrenamientos({ commit }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetPendingEntrenamientos();
                commit(Constant.SET_PENDING_ENTRENAMIENTOS, response.data.entrenamientos);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar entrenamientos pendientes:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar los entrenamientos pendientes');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        
        // Cargar entrenamientos aprobados
        async fetchApprovedEntrenamientos({ commit }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetApprovedEntrenamientos();
                commit(Constant.SET_APPROVED_ENTRENAMIENTOS, response.data.entrenamientos);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar entrenamientos aprobados:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar los entrenamientos aprobados');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        
        // Cargar entrenamientos denegados
        async fetchDeniedEntrenamientos({ commit }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetDeniedEntrenamientos();
                commit(Constant.SET_DENIED_ENTRENAMIENTOS, response.data.entrenamientos);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al cargar entrenamientos denegados:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar los entrenamientos denegados');
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        }
    },

    getters: {
        getPistas: state => state.pistas,
        getSelectedPista: state => state.selectedPista,
        getHorariosOcupados: state => state.horariosOcupados,
        isLoading: state => state.loading,
        getError: state => state.error,
        getTecnificaciones: state => state.tecnificaciones,
        getSubtiposTecnificacion: state => state.subtiposTecnificacion,
        getPendingEntrenamientos: state => state.pendingEntrenamientos,
        getApprovedEntrenamientos: state => state.approvedEntrenamientos,
        getDeniedEntrenamientos: state => state.deniedEntrenamientos,
    }
};
