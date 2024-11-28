import { ref } from 'vue';
import pistaService from '../../services/client/pistas.service';

export const usePistas = (slug) => {
    const pistas = ref([]);
    const fetchPistas = async () => {
        const { data } = await pistaService.GetPistasByDeporte(slug);
        pistas.value = data.pistas;
    };

    return { pistas, fetchPistas };
};

export const usePistasInfinite = (page = 1, limit = 3) => {
    const pistas = ref([])
    MesaService.GetMesasInfinite(page, limit)
        .then(res => pistas.value = res.data)
        .catch(error => console.error(error))
    return mesas;
};