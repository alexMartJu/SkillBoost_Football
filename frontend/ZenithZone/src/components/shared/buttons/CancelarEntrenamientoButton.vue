<template>
    <button class="btn btn-danger" @click="cancelarEntrenamiento(slug)">Cancelar</button>
</template>

<script>
import Constant from '@/Constant';
import profileService from '@/services/client/profile.service';
import Swal from 'sweetalert2';
import { useStore } from 'vuex';

export default {
    props: {
        slug: {
            type: String,
            Required: true
        },
    },

    setup() {
        const store = useStore();

        const cancelarEntrenamiento = async (slug) => {
            const result = await Swal.fire({
                title: '¿Quieres dejar el entrenamiento?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí'
            });

            if (result.isConfirmed) {
                await profileService.CancelarEntrenamiento(slug);
                Swal.fire(
                    'Cancelado',
                    'Tu entrenamiento ha sido cancelado.',
                    'success'
                );
                store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`);
            }
        }

        return { cancelarEntrenamiento }
    }
}
</script>