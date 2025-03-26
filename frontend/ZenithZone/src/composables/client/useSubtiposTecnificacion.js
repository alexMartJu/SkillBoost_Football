import { ref } from 'vue';
import subtiposTecnificacionService from '../../services/client/subtiposTecnificacion.service';

export const useSubtiposTecnificacion = (slug) => {
    const subtiposTecnificacion = ref([]);
    const fetchSubtiposTecnificacion = async () => {
        const { data } = await subtiposTecnificacionService.GetSubtiposByTecnificacion(slug);
        subtiposTecnificacion.value = data.subtipos;
    };

    return { subtiposTecnificacion, fetchSubtiposTecnificacion };
};

export const useSubtiposTecnificacionInfinite = (offset, limit) => {
    const subtiposTecnificacion = ref([]);
    return subtiposTecnificacionService.GetSubtiposTecnificacionInfinite(offset, limit)
        .then(res => {
            subtiposTecnificacion.value = res.data.subtipos;
            return subtiposTecnificacion;
        })
        .catch(error => {
            console.error(error);
            return subtiposTecnificacion;
        });
};