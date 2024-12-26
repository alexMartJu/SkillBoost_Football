<template>
    <section class="mb-3">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card">
                    <div class="card-body d-flex justify-content-between">
                        <div>
                            <h3 class="mb-3">Información de la reserva:</h3>
                            <!-- <p><strong>Pista:</strong> {{ reservas.pista }}</p>
                            <p><strong>Día:</strong> {{ reservas.dia }}</p>
                            <p><strong>Horario:</strong> {{ reservas.horario }}</p> -->
                            <p><strong>Pista:</strong></p>
                            <p><strong>Día:</strong></p>
                            <p><strong>Horario:</strong></p>
                        </div>
                        <button class="btn btn-danger align-self-end" @click="cancelarReserva">Cancelar Reserva</button>
                    </div>
                </div>
            </section>
        </div>
    </section>
</template>

<script>
import profileService from '@/services/client/profile.service';
import { useRoute, useRouter } from 'vue-router';

export default {
    props: {
        reserva: {
            type: Object,
            required: true
        }
    },

    setup(props) {
        const route = useRoute();
        const data = {
            idReserva: props.reserva.id,
            numeroSocio: route.params.numeroSocio
        }

        const cancelarReserva = async () => {
            // await profileService.CancelarReserva(data);
            console.log(data);
        }

        return { cancelarReserva }
    }
}
</script>