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
        inscripciones: [],
        currentEntrenamiento: null,
        currentAlumno: null,
        evaluacion: null,
        graficas: []
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
        [Constant.SET_INSCRIPCIONES](state, inscripciones) {
            state.inscripciones = inscripciones;
        },
        [Constant.SET_CURRENT_ENTRENAMIENTO](state, entrenamiento) {
            state.currentEntrenamiento = entrenamiento;
        },
        [Constant.SET_CURRENT_ALUMNO](state, alumno) {
            state.currentAlumno = alumno;
        },
        [Constant.SET_EVALUACION](state, evaluacion) {
            state.evaluacion = evaluacion;
        },
        [Constant.SET_GRAFICAS](state, graficas) {
            state.graficas = graficas;
        }
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
        },
        async [Constant.FETCH_INSCRIPCIONES_BY_ENTRENAMIENTO]({ commit }, slug) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetInscripcionesByEntrenamiento(slug);
                commit(Constant.SET_INSCRIPCIONES, response.data.inscripciones);
                commit(Constant.SET_ERROR, null);
                return response.data.inscripciones;
            } catch (error) {
                console.error('Error al cargar inscripciones:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar los alumnos inscritos');
                throw error;
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.FETCH_EVALUACION_ALUMNO]({ commit }, { numeroSocio, slug }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.GetEvaluacionAlumno(numeroSocio, slug);
                commit(Constant.SET_EVALUACION, response.data);
                commit(Constant.SET_ERROR, null);
                return response.data;
            } catch (error) {
                console.error('Error al cargar evaluación:', error);
                if (error.response && error.response.status === 404) {
                    // Si no existe evaluación, devolvemos null pero no es un error
                    commit(Constant.SET_EVALUACION, null);
                    return null;
                }
                commit(Constant.SET_ERROR, 'No se pudo cargar la evaluación del alumno');
                throw error;
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.CREATE_EVALUACION_ALUMNO]({ commit }, { numeroSocio, slug, evaluacion }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.CreateEvaluacionAlumno(numeroSocio, slug, evaluacion);
                commit(Constant.SET_EVALUACION, response.data);
                commit(Constant.SET_ERROR, null);
                return response.data;
            } catch (error) {
                console.error('Error al crear evaluación:', error);
                commit(Constant.SET_ERROR, 'No se pudo crear la evaluación del alumno');
                throw error;
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.UPDATE_EVALUACION_ALUMNO]({ commit }, { numeroSocio, slug, evaluacion }) {
            try {
                commit(Constant.SET_LOADING, true);
                const response = await entrenadorDashboardService.UpdateEvaluacionAlumno(numeroSocio, slug, evaluacion);
                commit(Constant.SET_EVALUACION, response.data);
                commit(Constant.SET_ERROR, null);
                return response.data;
            } catch (error) {
                console.error('Error al actualizar evaluación:', error);
                commit(Constant.SET_ERROR, 'No se pudo actualizar la evaluación del alumno');
                throw error;
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.INITIALIZE_GRAFICAS]({ commit }, numeroSocio) {
            try {
                commit(Constant.SET_LOADING, true);
                const currentYear = new Date().getFullYear();
                const response = await entrenadorDashboardService.GetGraficasAlumno(numeroSocio, currentYear);
                commit(Constant.SET_GRAFICAS, response.data.graficas);
                commit(Constant.SET_ERROR, null);
                return response.data.graficas;
            } catch (error) {
                console.error('Error al cargar gráficas:', error);
                commit(Constant.SET_ERROR, 'No se pudieron cargar las gráficas del alumno');
                throw error;
            } finally {
                commit(Constant.SET_LOADING, false);
            }
        },
        async [Constant.UPDATE_ONE_GRAFICA]({ commit, dispatch }, { id, graficas }) {
            try {
                commit(Constant.SET_LOADING, true);
                await entrenadorDashboardService.UpdateGraficasAlumno(id, graficas);
                //Recargar las gráficas después de actualizar
                await dispatch(Constant.INITIALIZE_GRAFICAS, id);
                commit(Constant.SET_ERROR, null);
            } catch (error) {
                console.error('Error al actualizar gráfica:', error);
                commit(Constant.SET_ERROR, 'No se pudo actualizar la gráfica del alumno');
                throw error;
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
        getInscripciones: state => state.inscripciones,
        getCurrentEntrenamiento: state => state.currentEntrenamiento,
        getCurrentAlumno: state => state.currentAlumno,
        getEvaluacion: state => state.evaluacion,
        getGraficas: state => state.graficas
    }
};
