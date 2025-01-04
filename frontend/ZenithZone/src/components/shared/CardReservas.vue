<template>
    <section class="mb-3">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card">
                    <div class="card-body d-flex justify-content-between">
                        <div>
                            <h3 class="mb-3">Información de la reserva:</h3>
                            <p><strong>Pista: </strong>{{ reserva.pistaNombre }}</p>
                            <p><strong>Día: </strong>{{ reserva.fecha }}</p>
                            <p><strong>Horario: </strong>{{ reserva.hora }}</p>
                        </div>
                        <!-- <button class="btn btn-danger align-self-end" @click="cancelarReserva">Cancelar Reserva</button> -->
                        <CancelarReservaButton :reserva="data" />
                    </div>
                </div>
            </section>
        </div>
    </section>
</template>

<script>
import CancelarReservaButton from './buttons/CancelarReservaButton.vue';

export default {
    props: {
        reserva: {
            type: Object,
            required: true
        },
        slug: {
            type: String,
            required: false
        }
    },

    components: {
        CancelarReservaButton
    },

    setup(props) {
        const data = {
            slugPista: props.slug,
            hora: props.reserva.hora,
            fecha: props.reserva.fecha
        }

        console.log(data);

        return { data }
    }
}
</script>