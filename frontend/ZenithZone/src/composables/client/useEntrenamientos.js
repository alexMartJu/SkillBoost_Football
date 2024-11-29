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

export const useEntrenamientosData = (filters_limit) => {
    const totalPages = ref(0)
    entrenamientosService.GetEntrenamientosData()
        .then(res => {
            const limit = filters_limit ?? 4;
            const { totalEntrenamientos } = res.data;
            const pages = Math.ceil(totalEntrenamientos / limit);
            totalPages.value = pages;
            console.log(`Total pages: ${pages}`);
        })
        .catch(error => console.error(error))

    return totalPages;
};