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

export const usePistasInfinite = (offset, limit) => {
    const pistas = ref([]);
    pistaService.GetPistasInfinite(offset, limit)
        .then(res => pistas.value = res.data.pistas)
        .catch(error => console.error(error));
    return pistas
};