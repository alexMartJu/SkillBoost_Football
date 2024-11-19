<template>
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">{{ clase.nombre }}</h5>
            <p class="card-text">{{ clase.descripcion }}</p>
            <p class="card-text">Duración: {{ clase.duracion }}</p>
            <p class="card-text">Plazas máximas: {{ clase.max_plazas }}</p>
            <p class="card-text">Precio Clase: {{ clase.precio }}</p>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        clase: {
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