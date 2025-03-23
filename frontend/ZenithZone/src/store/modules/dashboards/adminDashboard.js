    import Constant from '../../../Constant';
    import adminDashboardService from '../../../services/dashboards/adminDashboard.service';

    export const adminDashboard = {
        namespaced: true,

        state: {
            //
            tecnificaciones: [],
            currentTecnificacion: null,
        },

        mutations: {
            // Añadir estas mutaciones
            [Constant.INITIALIZE_TECNIFICACION_ADMIN](state, payload) {
                if (payload) {
                    state.tecnificaciones = payload;
                }
            },
            [Constant.CREATE_ONE_TECNIFICACION_ADMIN](state, newTecnificacion) {
                state.tecnificaciones.push(newTecnificacion);
            },
            [Constant.SET_CURRENT_TECNIFICACION_ADMIN](state, tecnificacion) {
                state.currentTecnificacion = tecnificacion;
            },
            [Constant.DELETE_ONE_TECNIFICACION_ADMIN](state, slug) {
                state.tecnificaciones = state.tecnificaciones.filter(tecnificacion => tecnificacion.slug !== slug);
            },
            

        },

        actions: {
                // Añadir estas acciones
                async [Constant.INITIALIZE_TECNIFICACION_ADMIN]({ commit }) {
                    try {
                        const { data } = await adminDashboardService.GetTecnificaciones();
                        commit(Constant.INITIALIZE_TECNIFICACION_ADMIN, data.tecnificaciones);
                    } catch (error) {
                        console.error("Error al cargar las tecnificaciones:", error);
                    }
                },
                async [Constant.FETCH_TECNIFICACION_BY_SLUG_ADMIN]({ commit }, slug) {
                    try {
                        const { data } = await adminDashboardService.GetTecnificacionBySlug(slug);
                        commit(Constant.SET_CURRENT_TECNIFICACION_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al cargar la tecnificación:", error);
                    }
                },
                async [Constant.CREATE_ONE_TECNIFICACION_ADMIN]({ commit }, newTecnificacion) {
                    try {
                        const { data } = await adminDashboardService.CreateTecnificacion(newTecnificacion);
                        commit(Constant.CREATE_ONE_TECNIFICACION_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al crear la tecnificación:", error);
                    }
                },
                async [Constant.UPDATE_ONE_TECNIFICACION_ADMIN]({ commit }, { slug, data }) {
                    try {
                        const response = await adminDashboardService.UpdateTecnificacion(slug, data);
                        commit(Constant.SET_CURRENT_TECNIFICACION_ADMIN, response.data);
                        return response.data;
                    } catch (error) {
                        console.error("Error al actualizar la tecnificación:", error);
                    }
                },
                async [Constant.DELETE_ONE_TECNIFICACION_ADMIN]({ commit }, slug) {
                    try {
                        await adminDashboardService.DeleteTecnificacion(slug);
                        commit(Constant.DELETE_ONE_TECNIFICACION_ADMIN, slug);
                    } catch (error) {
                        console.error("Error al eliminar la tecnificación:", error);
                    }
                },
                  
            
        },

        getters: {
            // Añadir estos getters
            GetTecnificaciones(state) {
                return state.tecnificaciones;
            },
            GetCurrentTecnificacion(state) {
                return state.currentTecnificacion;
            }
        }
    };