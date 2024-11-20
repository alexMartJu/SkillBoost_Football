<template>
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">{{ entrenamiento.nombre }}</h5>
            <p class="card-text">{{ entrenamiento.descripcion }}</p>
            <p class="card-text">Duración: {{ entrenamiento.duracion }}</p>
            <p class="card-text">Plazas máximas: {{ entrenamiento.max_plazas }}</p>
            <p class="card-text">Precio Entrenamiento: {{ entrenamiento.precio }}</p>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        entrenamiento: {
            type: Object,
            required: true,
        },
    },
};
</script>

<style scoped>
.card {
    cursor: pointer;
}
</style>