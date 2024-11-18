<template>
    <div class="card">
        <h1>{{ deporte.nombre }}</h1>
        <h3>{{ deporte.slug }}</h3>
        <strong>pistas</strong>
        <ul>
            <li v-for="pista in deporte.pistas" :key="pista.id">{{ pista.nombre }}</li>
        </ul>
        <strong>clases</strong>
        <ul>
            <li v-for="clase in deporte.clases" :key="clase.id">
                <p><strong>Nombre:</strong> {{ clase.nombre }}</p>
                <p><strong>Descripción:</strong> {{ clase.descripcion }}</p>
                <p><strong>Duración:</strong> {{ clase.duracion }} minutos</p>
                <p><strong>Plazas totales:</strong> {{ clase.maxPlazas }}</p>
                <p><strong>Precio:</strong> {{ clase.precio }}€</p>
            </li>
        </ul>
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