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
                        <div class="d-flex flex-column align-items-end justify-content-between">
                            <button class="btn btn-primary" @click="details(entrenamiento.slug)">
                                Más info
                            </button>
                            <UnirseEntrenamientoButton v-if="!isProfile" :slug="entrenamiento.slug"
                                :disabled="isSuscribed || !state.isLogged" />
                            <CancelarEntrenamientoButton v-if="isProfile" :slug="entrenamiento.slug" />
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </section>
</template>

<script>
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import UnirseEntrenamientoButton from './buttons/UnirseEntrenamientoButton.vue';
import CancelarEntrenamientoButton from './buttons/CancelarEntrenamientoButton.vue';
import { reactive } from 'vue';
import { computed } from 'vue';

export default {
    props: {
        entrenamiento: {
            type: Object,
            required: true
        },
        isProfile: {
            type: Boolean,
            required: true,
            default: false
        },
        isSuscribed: {
            type: Boolean,
            required: false,
            default: false
        }
    },

    components: {
        UnirseEntrenamientoButton,
        CancelarEntrenamientoButton
    },

    setup() {
        const store = useStore();
        const router = useRouter();

        const state = reactive({
            isLogged: computed(() => store.getters['user/GetIsAuth']),
        });

        const details = (slug) => {
            router.push({ name: 'detailsEntrenamiento', params: { slug } });
        }

        return { details, state}
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
    background-color: rgb(249, 250, 250);
}
</style>
