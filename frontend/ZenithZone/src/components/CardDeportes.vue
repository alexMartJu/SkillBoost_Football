<template>
    <div class="card">
        <h1>{{ deporte.nombre }}</h1>
        <h3>{{ deporte.slug }}</h3>
        <strong>pistas</strong>
        <p>{{ deporte.pistas }}</p>
        <strong>clases</strong>
        <p>{{ deporte.clases }}</p>
    </div>
</template>

<script>
export default {
    props: {
        deporte: {
            type: Object,
            required: true
        }
    }
};
</script>

<style>
.card {
    border: 1px solid #ccc;
    padding: 10px;
    margin: 5px;
    border-radius: 4px;
}
</style>