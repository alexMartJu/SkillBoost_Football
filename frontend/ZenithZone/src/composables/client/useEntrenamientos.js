import { ref, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import entrenamientosService from '@/services/client/entrenamientos.service';

export function useEntrenamientos() {
    const route = useRoute();
    const router = useRouter();

    let filters_url = {
        nombre: "",
        nivel: "",
        edadMinima: 1,
        edadMaxima: 99,
        maxPlazasMin: 1,
        maxPlazasMax: 50,
        tecnificacionNombre: "",
        fechaInicio: null,
        fechaFin: null,
        offset: 0,
        limit: 3,
    };

    const state = reactive({
        entrenamientos: [],
        offset: filters_url.offset,
        filters: filters_url,
        totalPages: 0,
        meta: {},
    });

    const fetchEntrenamientos = (filters) => {
        entrenamientosService.GetEntrenamientos(filters)
            .then(res => { state.entrenamientos = res.data.entrenamientos; })
            .catch(err => console.log(err));
    };

    const fetchTotalPages = (filters, filters_limit) => {
        entrenamientosService.GetEntrenamientosTotalFiltered(filters)
            .then(res => {
                const limit = filters_limit ?? 3;
                const { count } = res.data;
                const pages = Math.ceil(count / limit);
                state.totalPages = pages;
            })
            .catch(error => console.error(error));
    };

    const fetchEntrenamientosData = () => {
        entrenamientosService.GetEntrenamientosData()
            .then(res => { 
                state.meta = res.data; 
                // Actualizar los filtros con los valores del meta
                if (state.meta.plazasMaximas !== undefined) {
                    state.filters.maxPlazasMax = state.meta.plazasMaximas;
                }
                if (state.meta.plazasMinimas !== undefined) {
                    state.filters.maxPlazasMin = state.meta.plazasMinimas;
                }
            })
            .catch(error => console.error(error));
    };

    const ApplyFilters = (filters) => {
        const filters_64 = btoa(JSON.stringify(filters));
        router.push({ name: 'entrenaFilter', params: { filters: filters_64 } });
        fetchEntrenamientos(filters);
        fetchTotalPages(filters, filters.limit);
    };

    const resetFilters = () => {
        state.filters = {
            nombre: "",
            nivel: "",
            edadMinima: 1,
            edadMaxima: 99,
            maxPlazasMin: state.meta.plazasMinimas || 1,
            maxPlazasMax: state.meta.plazasMaximas || 50,
            tecnificacionNombre: "",
            fechaInicio: null,
            fechaFin: null,
            offset: 0,
            limit: 3,
        };
        router.push({ name: 'entrena' });
        fetchEntrenamientos(state.filters);
        fetchTotalPages(state.filters, state.filters.limit);
    };

    const clickCallback = (pageNum) => {
        try {
            if (route.params.filters !== '') {
                filters_url = JSON.parse(atob(route.params.filters));
            }
        } catch (error) {
            console.log(error);
        }
        filters_url.offset = pageNum - 1;
        state.offset = pageNum;
        ApplyFilters(filters_url);
    };

    // Initial fetch
    fetchEntrenamientos(filters_url);
    fetchTotalPages(filters_url, filters_url.limit);
    fetchEntrenamientosData();

    return { state, filters_url, ApplyFilters, resetFilters, clickCallback };
}