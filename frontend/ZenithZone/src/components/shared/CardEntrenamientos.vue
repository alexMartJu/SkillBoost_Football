<template>
    <section class="mb-4">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card shadow-sm hover-lift">
                    <img :src="`/assets/pistas/${entrenamiento.pistaPrivada.images[0].imageUrl}`" class="card-img-top" alt="Entrenamiento imagen">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-lg-8">
                                <h4 class="card-title fw-bold mb-3">{{ entrenamiento.nombre }}</h4>
                                <div class="mb-4">
                                    <p class="card-text text-muted mb-2 description-text">
                                        <i class="bi bi-card-text me-2"></i>{{ entrenamiento.descripcion }}
                                    </p>
                                    <div class="d-flex flex-wrap gap-3">
                                        <span class="badge bg-primary-subtle text-primary">
                                            <i class="bi bi-clock me-1"></i>{{ entrenamiento.duracion }} min
                                        </span>
                                        <span class="badge bg-success-subtle text-success">
                                            <i class="bi bi-people me-1"></i>{{ entrenamiento.maxPlazas }} plazas
                                        </span>
                                        <span class="badge bg-info-subtle text-info">
                                            <i class="bi bi-tag me-1"></i>{{ entrenamiento.precio }}€
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="d-flex flex-column gap-2 justify-content-end h-100">
                                    <button class="btn btn-outline-primary rounded-pill" @click="details(entrenamiento.slug)">
                                        <i class="bi bi-info-circle me-2"></i>Más info
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
    border: none;
    border-radius: 1rem;
    transition: all 0.3s ease;
    height: 100%;
}

.hover-lift:hover {
    transform: translateY(-5px);
    box-shadow: 0 1rem 3rem rgba(0,0,0,.175)!important;
}

.card-img-top {
    height: 300px;
    object-fit: cover;
    border-top-left-radius: 1rem;
    border-top-right-radius: 1rem;
}

.description-text {
    display: -webkit-box;
    overflow: hidden;
    height: 4.5em;
}

.badge {
    padding: 0.75rem 1.25rem;
    font-weight: 500;
    font-size: 0.9rem;
}

.btn {
    text-transform: uppercase;
    letter-spacing: 0.5px;
    font-weight: 600;
    padding: 0.5rem 1rem;
}

.btn-outline-primary:hover {
    transform: translateY(-1px);
}
</style>
