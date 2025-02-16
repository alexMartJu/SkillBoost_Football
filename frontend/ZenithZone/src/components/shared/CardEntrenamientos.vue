<template>
    <section class="mb-4">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card shadow-sm">
                    <img :src="`/assets/pistas/${entrenamiento.pistaPrivada.images[0].imageUrl}`" 
                         class="card-img-top img-fluid object-fit-cover" 
                         :style="isServiciosView ? 'height: 275px' : 'height: 300px'"
                         alt="Entrenamiento imagen">
                    <div class="card-body">
                        <div class="row">
                            <h4 class="card-title fw-bold mb-3 text-truncate" 
                                style="max-width: 100%;" 
                                :title="entrenamiento.nombre">
                                {{ entrenamiento.nombre }}
                            </h4>
                            <div class="col-lg-8">
                                <div class="mb-4">
                                    <p class="card-text text-muted mb-2 text-truncate-container">
                                        <i class="bi bi-card-text me-2"></i>{{ entrenamiento.descripcion }}
                                    </p>
                                    <div class="d-flex flex-wrap gap-3">
                                        <span class="badge bg-primary-subtle text-primary p-3 fs-6">
                                            <i class="bi bi-clock me-1"></i>{{ entrenamiento.duracion }} min
                                        </span>
                                        <span class="badge bg-success-subtle text-success p-3 fs-6">
                                            <i class="bi bi-people me-1"></i>{{ entrenamiento.maxPlazas }} plazas
                                        </span>
                                        <span class="badge bg-info-subtle text-info p-3 fs-6">
                                            <i class="bi bi-tag me-1"></i>{{ entrenamiento.precio }}€
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="d-flex flex-column gap-2 justify-content-end h-100">
                                    <button class="btn btn-outline-primary rounded-pill fw-semibold" 
                                            @click="details(entrenamiento.slug)">
                                        <i class="bi bi-info-circle me-2"></i>Info
                                    </button>
                                    <UnirseEntrenamientoButton 
                                        v-if="!isProfile" 
                                        :slug="entrenamiento.slug"
                                        :disabled="isSuscribed || !state.isLogged" 
                                        class="rounded-pill"
                                    />
                                    <CancelarEntrenamientoButton 
                                        v-if="isProfile" 
                                        :slug="entrenamiento.slug"
                                        class="rounded-pill"
                                    />
                                </div>
                            </div>
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
        },
        isServiciosView: {
            type: Boolean,
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
    border-radius: 1rem;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 1rem 3rem rgba(0,0,0,.175)!important;
}

.text-truncate-container {
    display: -webkit-box;
    overflow: hidden;
    height: 4.5em;
}

.btn {
    letter-spacing: 0.5px;
    padding: 0.5rem 1rem;
}

.btn:hover {
    transform: translateY(-1px);
}
</style>
