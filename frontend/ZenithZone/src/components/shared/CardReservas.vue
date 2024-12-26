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
                        <button class="btn btn-danger align-self-end">Cancelar Reserva</button>
                    </div>
                </div>
            </section>
        </div>
    </section>
</template>

<script>
export default {
    props: {
        reservas: {
            type: Object,
            required: true
        }
    }
}
</script>