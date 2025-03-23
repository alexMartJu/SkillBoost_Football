    import Constant from '../../../Constant';
    import adminDashboardService from '../../../services/dashboards/adminDashboard.service';

    export const adminDashboard = {
        namespaced: true,

        state: {
            //Tecnificaciones
            tecnificaciones: [],
            currentTecnificacion: null,
            //Subtipos de Tecnificación
            subtiposTecnificacion: [],
            currentSubtipoTecnificacion: null,
        },

        mutations: {
            //Tecnificaciones
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
            //Subtipos de Tecnificación
            [Constant.INITIALIZE_SUBTIPO_TECNIFICACION_ADMIN](state, payload) {
                if (payload) {
                    state.subtiposTecnificacion = payload;
                }
            },
            [Constant.CREATE_ONE_SUBTIPO_TECNIFICACION_ADMIN](state, newSubtipo) {
                state.subtiposTecnificacion.push(newSubtipo);
            },
            [Constant.SET_CURRENT_SUBTIPO_TECNIFICACION_ADMIN](state, subtipo) {
                state.currentSubtipoTecnificacion = subtipo;
            },
            [Constant.DELETE_ONE_SUBTIPO_TECNIFICACION_ADMIN](state, slug) {
                state.subtiposTecnificacion = state.subtiposTecnificacion.filter(subtipo => subtipo.slug !== slug);
            },
            

        },

        actions: {
                //Tecnificaciones
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
                //Subtipos de Tecnificación
                async [Constant.INITIALIZE_SUBTIPO_TECNIFICACION_ADMIN]({ commit }) {
                    try {
                        const { data } = await adminDashboardService.GetSubtiposTecnificacion();
                        commit(Constant.INITIALIZE_SUBTIPO_TECNIFICACION_ADMIN, data.subtipos);
                    } catch (error) {
                        console.error("Error al cargar los subtipos de tecnificación:", error);
                    }
                },
                async [Constant.FETCH_SUBTIPO_TECNIFICACION_BY_SLUG_ADMIN]({ commit }, slug) {
                    try {
                        const { data } = await adminDashboardService.GetSubtipoTecnificacionBySlug(slug);
                        commit(Constant.SET_CURRENT_SUBTIPO_TECNIFICACION_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al cargar el subtipo de tecnificación:", error);
                    }
                },
                async [Constant.CREATE_ONE_SUBTIPO_TECNIFICACION_ADMIN]({ commit }, { tecnificacionSlug, subtipo }) {
                    try {
                        const { data } = await adminDashboardService.CreateSubtipoTecnificacion(tecnificacionSlug, subtipo);
                        commit(Constant.CREATE_ONE_SUBTIPO_TECNIFICACION_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al crear el subtipo de tecnificación:", error);
                        throw error;
                    }
                },
                async [Constant.UPDATE_ONE_SUBTIPO_TECNIFICACION_ADMIN]({ commit }, { slug, data }) {
                    try {
                        const response = await adminDashboardService.UpdateSubtipoTecnificacion(slug, data);
                        commit(Constant.SET_CURRENT_SUBTIPO_TECNIFICACION_ADMIN, response.data);
                        return response.data;
                    } catch (error) {
                        console.error("Error al actualizar el subtipo de tecnificación:", error);
                        throw error;
                    }
                },
                async [Constant.DELETE_ONE_SUBTIPO_TECNIFICACION_ADMIN]({ commit }, slug) {
                    try {
                        await adminDashboardService.DeleteSubtipoTecnificacion(slug);
                        commit(Constant.DELETE_ONE_SUBTIPO_TECNIFICACION_ADMIN, slug);
                    } catch (error) {
                        console.error("Error al eliminar el subtipo de tecnificación:", error);
                        throw error;
                    }
                },
                  
            
        },

        getters: {
            //Tecnificaciones
            GetTecnificaciones(state) {
                return state.tecnificaciones;
            },
            GetCurrentTecnificacion(state) {
                return state.currentTecnificacion;
            },
            //Subtipos de Tecnificación
            GetSubtiposTecnificacion(state) {
                return state.subtiposTecnificacion;
            },
            GetCurrentSubtipoTecnificacion(state) {
                return state.currentSubtipoTecnificacion;
            }
        }
    };