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
            //Pistas
            pistas: [],
            currentPista: null,
            //Tipos de usuarios
            tutores: [],
            jugadoresClub: [],
            jugadores: [],
            jugadoresSociales: [],
            loadingUsers: false,
            //Suscripciones
            suscripciones: [],
            currentSuscripcion: null,
            //Organizaciones
            organizaciones: [],
            currentOrganizacion: null,
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
            //Pistas
            [Constant.INITIALIZE_PISTA_ADMIN](state, payload) {
                if (payload) {
                    state.pistas = payload;
                }
            },
            [Constant.CREATE_ONE_PISTA_ADMIN](state, newPista) {
                state.pistas.push(newPista);
            },
            [Constant.SET_CURRENT_PISTA_ADMIN](state, pista) {
                state.currentPista = pista;
            },
            [Constant.DELETE_ONE_PISTA_ADMIN](state, slug) {
                state.pistas = state.pistas.filter(pista => pista.slug !== slug);
            },
            //Tipos de usuarios
            [Constant.SET_TUTORES](state, payload) {
                state.tutores = payload;
            },
            [Constant.SET_JUGADORES_CLUB](state, payload) {
                state.jugadoresClub = payload;
            },
            [Constant.SET_JUGADORES](state, payload) {
                state.jugadores = payload;
            },
            [Constant.SET_JUGADORES_SOCIALES](state, payload) {
                state.jugadoresSociales = payload;
            },
            [Constant.SET_LOADING_USERS](state, payload) {
                state.loadingUsers = payload;
            },
            //Suscripciones
            [Constant.SET_SUSCRIPCIONES_ADMIN](state, payload) {
                state.suscripciones = payload;
            },
            [Constant.SET_CURRENT_SUSCRIPCION_ADMIN](state, suscripcion) {
                state.currentSuscripcion = suscripcion;
            },
            //Organizaciones
            [Constant.INITIALIZE_ORGANIZACIONES_ADMIN](state, payload) {
                if (payload) {
                    state.organizaciones = payload;
                }
            },
            [Constant.CREATE_ONE_ORGANIZACION_ADMIN](state, newOrganizacion) {
                state.organizaciones.push(newOrganizacion);
            },
            [Constant.SET_CURRENT_ORGANIZACION_ADMIN](state, organizacion) {
                state.currentOrganizacion = organizacion;
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
                //Pistas
                async [Constant.INITIALIZE_PISTA_ADMIN]({ commit }) {
                    try {
                        const { data } = await adminDashboardService.GetPistas();
                        commit(Constant.INITIALIZE_PISTA_ADMIN, data.pistas);
                    } catch (error) {
                        console.error("Error al cargar las pistas:", error);
                    }
                },
                async [Constant.FETCH_PISTA_BY_SLUG_ADMIN]({ commit }, slug) {
                    try {
                        const { data } = await adminDashboardService.GetPistaBySlug(slug);
                        commit(Constant.SET_CURRENT_PISTA_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al cargar la pista:", error);
                    }
                },
                async [Constant.CREATE_ONE_PISTA_ADMIN]({ commit }, newPista) {
                    try {
                        const { data } = await adminDashboardService.CreatePista(newPista);
                        commit(Constant.CREATE_ONE_PISTA_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al crear la pista:", error);
                        throw error;
                    }
                },
                async [Constant.UPDATE_ONE_PISTA_ADMIN]({ commit }, { slug, data }) {
                    try {
                        const response = await adminDashboardService.UpdatePista(slug, data);
                        commit(Constant.SET_CURRENT_PISTA_ADMIN, response.data);
                        return response.data;
                    } catch (error) {
                        console.error("Error al actualizar la pista:", error);
                        throw error;
                    }
                },
                async [Constant.DELETE_ONE_PISTA_ADMIN]({ commit }, slug) {
                    try {
                        await adminDashboardService.DeletePista(slug);
                        commit(Constant.DELETE_ONE_PISTA_ADMIN, slug);
                    } catch (error) {
                        console.error("Error al eliminar la pista:", error);
                        throw error;
                    }
                },
                //Tipos de usuarios
                async [Constant.FETCH_TUTORES]({ commit }) {
                    try {
                        commit(Constant.SET_LOADING_USERS, true);
                        const { data } = await adminDashboardService.GetTutores();
                        commit(Constant.SET_TUTORES, data.profiles || []);
                        return data.profiles;
                    } catch (error) {
                        console.error("Error al cargar tutores:", error);
                        return [];
                    } finally {
                        commit(Constant.SET_LOADING_USERS, false);
                    }
                },
                async [Constant.FETCH_JUGADORES_CLUB]({ commit }) {
                    try {
                        commit(Constant.SET_LOADING_USERS, true);
                        const { data } = await adminDashboardService.GetJugadoresClub();
                        commit(Constant.SET_JUGADORES_CLUB, data.profiles || []);
                        return data.profiles;
                    } catch (error) {
                        console.error("Error al cargar jugadores de club:", error);
                        return [];
                    } finally {
                        commit(Constant.SET_LOADING_USERS, false);
                    }
                },
                async [Constant.FETCH_JUGADORES]({ commit }) {
                    try {
                        commit(Constant.SET_LOADING_USERS, true);
                        const { data } = await adminDashboardService.GetJugadores();
                        commit(Constant.SET_JUGADORES, data.profiles || []);
                        return data.profiles;
                    } catch (error) {
                        console.error("Error al cargar jugadores:", error);
                        return [];
                    } finally {
                        commit(Constant.SET_LOADING_USERS, false);
                    }
                },
                async [Constant.FETCH_JUGADORES_SOCIALES]({ commit }) {
                    try {
                        commit(Constant.SET_LOADING_USERS, true);
                        const { data } = await adminDashboardService.GetJugadoresSociales();
                        commit(Constant.SET_JUGADORES_SOCIALES, data.profiles || []);
                        return data.profiles;
                    } catch (error) {
                        console.error("Error al cargar jugadores sociales:", error);
                        return [];
                    } finally {
                        commit(Constant.SET_LOADING_USERS, false);
                    }
                },
                //Suscripciones
                async [Constant.INITIALIZE_SUSCRIPCIONES_ADMIN]({ commit }) {
                    try {
                        const { data } = await adminDashboardService.GetSuscripciones();
                        commit(Constant.SET_SUSCRIPCIONES_ADMIN, data.suscripciones);
                        return data.suscripciones;
                    } catch (error) {
                        console.error("Error al cargar las suscripciones:", error);
                        throw error;
                    }
                },
                async [Constant.UPDATE_SUSCRIPCION_PRECIO_ADMIN]({ commit, dispatch }, { slug, precio }) {
                    try {
                        const { data } = await adminDashboardService.UpdateSuscripcionPrecio(slug, { precio });
                        // Actualizar la lista de suscripciones después de la actualización
                        await dispatch(Constant.INITIALIZE_SUSCRIPCIONES_ADMIN);
                        return data;
                    } catch (error) {
                        console.error("Error al actualizar el precio de la suscripción:", error);
                        throw error;
                    }
                },
                //Organizaciones
                async [Constant.INITIALIZE_ORGANIZACIONES_ADMIN]({ commit }) {
                    try {
                        const { data } = await adminDashboardService.GetOrganizaciones();
                        commit(Constant.INITIALIZE_ORGANIZACIONES_ADMIN, data.organizaciones);
                    } catch (error) {
                        console.error("Error al cargar las organizaciones:", error);
                    }
                },
                async [Constant.FETCH_ORGANIZACION_BY_SLUG_ADMIN]({ commit }, slug) {
                    try {
                        const { data } = await adminDashboardService.GetOrganizacionBySlug(slug);
                        commit(Constant.SET_CURRENT_ORGANIZACION_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al cargar la organización:", error);
                    }
                },
                async [Constant.CREATE_ONE_ORGANIZACION_ADMIN]({ commit }, newOrganizacion) {
                    try {
                        const { data } = await adminDashboardService.CreateOrganizacion(newOrganizacion);
                        commit(Constant.CREATE_ONE_ORGANIZACION_ADMIN, data);
                        return data;
                    } catch (error) {
                        console.error("Error al crear la organización:", error);
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
            },
            //Pistas
            GetPistas(state) {
                return state.pistas;
            },
            GetCurrentPista(state) {
                return state.currentPista;
            },
            getTutores: state => state.tutores,
            getJugadoresClub: state => state.jugadoresClub,
            getJugadores: state => state.jugadores,
            getJugadoresSociales: state => state.jugadoresSociales,
            isLoadingUsers: state => state.loadingUsers,
            //Suscripciones
            GetSuscripciones(state) {
                return state.suscripciones;
            },
            GetCurrentSuscripcion(state) {
                return state.currentSuscripcion;
            },
            //Organizaciones
            GetOrganizaciones(state) {
                return state.organizaciones;
            },
            GetCurrentOrganizacion(state) {
                return state.currentOrganizacion;
            },  
            
        }
    };