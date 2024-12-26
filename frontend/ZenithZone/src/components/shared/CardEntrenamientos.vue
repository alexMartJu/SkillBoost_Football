<template>
    <section class="mb-3">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card">
                    <div class="card-body d-flex justify-content-between">
                        <div>
                            <p><strong>Nombre:</strong> {{ entrenamiento.nombre }}</p>
                            <p><strong>Descripción:</strong> {{ entrenamiento.descripcion }}</p>
                            <p><strong>Duración:</strong> {{ entrenamiento.duracion }} minutos</p>
                            <p><strong>Plazas totales:</strong> {{ entrenamiento.maxPlazas }}</p>
                            <p><strong>Precio:</strong> {{ entrenamiento.precio }}€</p>
                        </div>
                        <button class="btn btn-primary align-self-end" 
                            @click="details(entrenamiento.slug)">
                            Más info
                        </button>
                        <button v-if="isProfile" class="btn btn-danger align-self-end" 
                            @click="cancelarEntrenamiento">
                            Cancelar
                        </button>
                    </div>                    
                </div>
            </section>
        </div>
    </section>
</template>

<script>
import profileService from '@/services/client/profile.service';
import { useRoute, useRouter } from 'vue-router';

export default {
    props: {
        entrenamiento: {
            type: Object,
            required: true
        },
        isProfile: {
            type: Boolean,
            required: true
        }
    },

    setup(props) {
        const router = useRouter();
        const route = useRoute();
        const data = {
            idEntrenamiento: props.entrenamiento.id,
            numeroSocio: route.params.numeroSocio
        }

        const details = (slug) => {
            router.push({ name: 'detailsEntrenamiento', params: { slug } });
        }

        const cancelarEntrenamiento = async () => {
            // await profileService.CancelarEntrenamiento(data);
            console.log(data);
        }

        return { details, cancelarEntrenamiento }
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
    background-color: rgb(249, 250, 250);
}
</style>
