<template>
    <div class="entrenamiento-list">
        <!-- Mensaje cuando no hay entrenamientos -->
        <div v-if="entrenamientos.length === 0" class="text-center py-5">
            <i class="bi bi-calendar-x text-muted" style="font-size: 3rem;"></i>
            <p class="mt-3 text-muted">{{ mensajeVacio }}</p>
        </div>

        <!-- Lista de entrenamientos -->
        <div v-else class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <div v-for="entrenamiento in entrenamientos" :key="entrenamiento.id" class="col">
                <entrenamiento-card :entrenamiento="entrenamiento" :estado="estado" />
            </div>
        </div>
    </div>
</template>

<script>
import EntrenamientoCard from './EntrenamientoCard.vue';

export default {
    name: 'EntrenamientoList',
    components: {
        EntrenamientoCard
    },
    props: {
        entrenamientos: {
            type: Array,
            required: true
        },
        estado: {
            type: String,
            required: true,
            validator: value => ['pending', 'approved', 'denied'].includes(value)
        },
        mensajeVacio: {
            type: String,
            default: 'No hay entrenamientos disponibles.'
        }
    }
};
</script>