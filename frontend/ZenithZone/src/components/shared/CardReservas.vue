<template>
    <section class="mb-4">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card shadow-sm border-0 rounded-4 hover-shadow transition">
                    <div class="card-body p-4">
                        <div class="row align-items-center">
                            <div class="col-lg-8">
                                <h3 class="fw-bold text-primary mb-4">Información de la reserva</h3>
                                <div class="bg-light p-3 rounded-3">
                                    <div class="row g-3">
                                        <div class="col-md-4">
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-geo-alt-fill text-primary me-2 fs-4"></i>
                                                <div>
                                                    <small class="text-muted d-block">Pista</small>
                                                    <span class="fw-semibold">{{ reserva.pistaNombre }}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-calendar-event text-primary me-2 fs-4"></i>
                                                <div>
                                                    <small class="text-muted d-block">Día</small>
                                                    <span class="fw-semibold">{{ reserva.fecha }}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-clock text-primary me-2 fs-4"></i>
                                                <div>
                                                    <small class="text-muted d-block">Horario</small>
                                                    <span class="fw-semibold">{{ reserva.hora }}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 text-end mt-4 mt-lg-0">
                                <CancelarReservaButton 
                                    v-if="new Date(reserva.fecha) > new Date()" 
                                    :reserva="data"
                                    class="btn btn-danger btn-lg rounded-pill shadow-sm"
                                />
                            </div>
                        </div>
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

        return { data }
    }
}
</script>

<style scoped>
.hover-shadow:hover {
    transform: translateY(-3px);
    box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
}

.transition {
    transition: all .3s ease-in-out;
}
</style>