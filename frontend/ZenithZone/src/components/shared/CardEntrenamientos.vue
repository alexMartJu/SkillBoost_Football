<template>
    <section class="mb-3">
        <!-- <strong>Clase</strong> -->
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card" @click="details(entrenamiento.slug)">
                    <div class="card-body ">
                        <p><strong>Nombre:</strong> {{ entrenamiento.nombre }}</p>
                        <p><strong>Descripción:</strong> {{ entrenamiento.descripcion }}</p>
                        <p><strong>Duración:</strong> {{ entrenamiento.duracion }} minutos</p>
                        <p><strong>Plazas totales:</strong> {{ entrenamiento.maxPlazas }}</p>
                        <p><strong>Precio:</strong> {{ entrenamiento.precio }}€</p>
                    </div>
                </div>
            </section>
        </div>
    </section>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
    props: {
        entrenamiento: {
            type: Object,
            required: true
        }
    },

    setup() {
        const router = useRouter();

        const details = (slug) => {
            router.push({ name: 'detailsEntrenamiento', params: { slug } });
        }

        return { details }

    }
}
</script>

<style scoped>
.card {
    position: relative;
    overflow: hidden;
    /* Evitar que la imagen se desborde */
}

.card:hover {
    cursor: pointer;
    background-color: rgb(237, 242, 244);
}
</style>
