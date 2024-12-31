<template>
    <div v-if="isEntrenamiento">
        <div class="entrenamiento-info" v-if="state.entrenamiento">
            <h1>{{ state.entrenamiento.nombre }}</h1>
            <h3>{{ state.entrenamiento.deporte.nombre }}</h3>
            <p>{{ state.entrenamiento.descripcion }}</p>
            <p>{{ state.entrenamiento.dia }}</p>
            <p>{{ state.entrenamiento.duracion }} minutos</p>
            <p>Plazas máximas: {{ state.entrenamiento.maxPlazas }}</p>
            <p>Precio: {{ state.entrenamiento.precio }}€</p>
            <p>Horario: {{ state.entrenamiento.horario.hora }}</p>
            <h3>Entrenador:</h3>
            <p>Nombre: {{ state.entrenamiento.entrenador.nombre }} {{ state.entrenamiento.entrenador.apellidos }}</p>
            <div>
                <h3>Lugar: {{ state.entrenamiento.pistaPrivada.nombre }}</h3>
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

<style>
body {
    margin-top: 80px;
    background-color: rgb(245, 245, 245);
    font-family: 'Roboto', sans-serif;
}
</style>