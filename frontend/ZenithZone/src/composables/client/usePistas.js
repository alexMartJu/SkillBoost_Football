import { ref } from 'vue';
import pistaService from '../../services/client/pistas.service';

export default function usePistas(slug) {
    const pistas = ref([]);

    const fetchPistas = async () => {
        const { data } = await pistaService.GetPistasByDeporte(slug);
        pistas.value = data.pistas;
        // console.log(pistas.value);
    };

    return {
        pistas,
        fetchPistas
    };
}