import Constant from '../../../Constant';
import organizacionesService from '../../../services/client/organizaciones.service';

export const organizaciones = {
    namespaced: true,

    state: {
        organizaciones: [],
        currentOrganizacion: null
    },

    actions: {
        //Obtener todas las organizaciones
        [Constant.INITIALIZE_ORGANIZACION]: async (store) => {
            try {
                const { data } = await organizacionesService.GetOrganizaciones();
                store.commit(Constant.INITIALIZE_ORGANIZACION, data);
            } catch (error) {
                console.error("Error al cargar las organizaciones:", error);
            }
        },
        
        //Obtener una organización por slug
        [Constant.FETCH_ORGANIZACION_BY_SLUG]: async (store, slug) => {
            try {
                const { data } = await organizacionesService.GetOneOrganizacion(slug);
                store.commit(Constant.SET_CURRENT_ORGANIZACION, data);
                return data;
            } catch (error) {
                console.error("Error al cargar la organización por slug:", error);
                return null;
            }
        },
        
        //Crear una organización (solo Admin)
        [Constant.CREATE_ONE_ORGANIZACION]: async (store, organizacion) => {
            try {
                const { data } = await organizacionesService.CreateOrganizacion(organizacion);
                // Actualizar la lista después de crear
                store.dispatch(Constant.INITIALIZE_ORGANIZACION);
                return data;
            } catch (error) {
                console.error("Error al crear la organización:", error);
                throw error;
            }
        }
    },

    mutations: {
        [Constant.INITIALIZE_ORGANIZACION](state, payload) {
            if (payload) {
                state.organizaciones = payload;
            }
        },
        
        [Constant.SET_CURRENT_ORGANIZACION](state, payload) {
            if (payload) {
                state.currentOrganizacion = payload;
            }
        }
    },

    getters: {
        GetOrganizaciones(state) {
            return state.organizaciones;
        },
        
        GetCurrentOrganizacion(state) {
            return state.currentOrganizacion;
        },
        
        GetOrganizacionesByTipo: (state) => (tipo) => {
            return state.organizaciones.filter(org => org.tipo === tipo);
        }
    }
};