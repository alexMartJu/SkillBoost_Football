<template>
    <button class="btn btn-danger" @click="cancelarReserva(reserva)">Cancelar</button>
</template>

<script>
import Constant from '@/Constant';
import profileService from '@/services/client/profile.service';
import Swal from 'sweetalert2';
import { useStore } from 'vuex';

export default {
    props: {
        reserva: {
            type: String,
            Required: true
        },
    },

    setup() {
        const store = useStore();

        const cancelarReserva = async (reserva) => {
            const result = await Swal.fire({
                title: '¿Quieres cancelar tu reserva?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí'
            });

            if (result.isConfirmed) {
                await profileService.CancelarReserva(reserva);

                Swal.fire(
                    'Cancelada',
                    'Tu reserva ha sido cancelada.',
                    'success'
                );
                store.dispatch(`profile/${Constant.INITIALIZE_RESERVA_PROFILE}`);
            }
        }

        return { cancelarReserva }
    }
}
</script>