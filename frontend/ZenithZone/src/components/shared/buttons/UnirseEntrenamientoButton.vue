<template>
    <button class="btn btn-success" @click="unirseEntrenamiento(slug)" :disabled="!state.isLogged">Apuntarse</button>
</template>

<script>
import profileService from '@/services/client/profile.service';
import Swal from 'sweetalert2';
import { computed } from 'vue';
import { reactive } from 'vue';
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
        const unirseEntrenamiento = async (slug) => {
            const result = await Swal.fire({
                title: '¿Quieres apuntarte a este entrenamiento?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí'
            });

            if (result.isConfirmed) {
                try {
                    await profileService.UnirseEntrenamiento(slug);
                    Swal.fire(
                        'Unido',
                        'Te has apuntado al entrenamiento.',
                        'success'
                    );
                } catch (e) {
                    Swal.fire(
                        'Error',
                        'Ya estás apuntado al entrenamiento.',
                        'error'
                    );
                }
            }
        }

        return { unirseEntrenamiento }
    }
}
</script>