import entrenamientosService from '@/services/client/entrenamientos.service';
import { ref } from 'vue';
// import deporteService from '../../services/client/deportes.service';


export const useEntrenamientosFilters = (filters = {}) => {
    // console.log(filters);
    // filters.limit = 9;
    const entrenamientos = ref([]);
    entrenamientosService.GetEntrenamientos(filters)
        .then(res => { entrenamientos.value = res.data.entrenamientos; })
        .catch(err => console.log(err));

    return entrenamientos;
};

export const useEntrenamientosPaginate = (filters = {}) => {
    const totalPages = ref(0)
    entrenamientosService.GetEntrenamientosData()
        .then(res => {
            const limit = filters.limit ?? 4;
            const total = res.data.entrenamientos;
            let totalCount = [...total].length;
            const pages = Math.ceil(totalCount / limit);
            totalPages.value = pages;
        })
        .catch(error => console.error(error))

    return totalPages;
};