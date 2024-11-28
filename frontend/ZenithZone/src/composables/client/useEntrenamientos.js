import entrenamientosService from '@/services/client/entrenamientos.service';
import { ref } from 'vue';
// import deporteService from '../../services/client/deportes.service';


export const useEntrenamientosFilters = (filters = {}) => {
    filters.limit = 9;
    const entrenamientos = ref([]);
    entrenamientosService.GetEntrenamientos(filters)
        .then(res => { entrenamientos.value = res.data.entrenamientos; })
        .catch(err => console.log(err));

    return deportes;
};

export const useEntrenamientosPaginate = (filters = {}) => {
    const totalPages = ref(0)
    entrenamientosService.GetEntrenamientosPaginate(filters)
        .then(res => {
            const limit = filters.limit ?? 9;
            const total = res.data.deportes;
            const pages = Math.ceil(total / limit);
            totalPages.value = pages;
        })
        .catch(error => console.error(error))

    return totalPages;
};