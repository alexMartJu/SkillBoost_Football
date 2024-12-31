<template>
    <div v-if="isEntrenamiento">
        <div class="entrenamiento-info" v-if="state.entrenamiento">
            <h1>{{ state.entrenamiento.nombre }}</h1>
            <p>{{ state.entrenamiento.descripcion }}</p>
            <p>{{ state.entrenamiento.dia }}</p>
            <p>{{ state.entrenamiento.duracion }} minutos</p>
            <p>Plazas máximas: {{ state.entrenamiento.maxPlazas }}</p>
            <p>Precio: {{ state.entrenamiento.precio }}€</p>
            <div>
                <h2>Pistas privadas del entrenamiento</h2>
                <ul v-for="pista in state.entrenamiento.pistaPrivada" :key="pista.id">
                    <li>{{ state.entrenamiento.pistaPrivada.nombre }}</li>
                </ul>
            </div>
            <UnirseEntrenamientoButton :slug="state.entrenamiento.slug" />
        </div>
        <div v-else>
            <p>Cargando información de entrenamiento...</p>
        </div>
    </div>
    <!-- SI ES DETAILS PISTA -->
    <div v-else>
        <div class="pista-info" v-if="state.pista">
            <h1>{{ state.pista.nombre }}</h1>
            <button class="btn btn-primary">Botón para las reservas</button>
        </div>
        <div v-else>
            <p>Cargando información de la pista...</p>
        </div>
    </div>
</template>

<script>
import UnirseEntrenamientoButton from './buttons/UnirseEntrenamientoButton.vue';

export default {
    props: {
        isEntrenamiento: {
            type: Boolean,
            Required: true
        },
        state: {
            type: Object,
            Required: true
        }
    },

    components: {
        UnirseEntrenamientoButton
    }

}
</script>