<template>
    <div class="organizacion-detail py-5">
        <div class="container" v-if="organizacion">
            <div class="row">
                <div class="col-lg-10 mx-auto">
                    <div class="card border-0 shadow">
                        <div class="card-body p-4 p-md-5">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h1 class="card-title mb-0">{{ organizacion.nombre }}</h1>
                                <span class="badge bg-primary fs-6">{{ organizacion.tipo }}</span>
                            </div>

                            <div class="row mb-4">
                                <div class="col-md-4 text-center mb-3 mb-md-0">
                                    <img :src="logoUrl" class="img-fluid rounded shadow-sm" :alt="organizacion.nombre"
                                        @error="handleImageError" style="max-height: 200px;">
                                </div>
                                <div class="col-md-8">
                                    <p class="lead">{{ organizacion.descripcion }}</p>
                                    <p class="text-muted">
                                        <i class="bi bi-geo-alt-fill me-1"></i>
                                        {{ organizacion.direccion }}, {{ organizacion.ciudad }}, {{ organizacion.pais }}
                                    </p>
                                </div>
                            </div>

                            <div class="row g-4 mb-4">
                                <div class="col-md-6">
                                    <div class="card h-100 bg-light border-0">
                                        <div class="card-body">
                                            <h5 class="card-title">Misión</h5>
                                            <p class="card-text">{{ organizacion.mision }}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card h-100 bg-light border-0">
                                        <div class="card-body">
                                            <h5 class="card-title">Visión</h5>
                                            <p class="card-text">{{ organizacion.vision }}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-4">
                                <h4>Impacto Social</h4>
                                <p>{{ organizacion.impactoSocial }}</p>
                            </div>

                            <div class="mb-4">
                                <h4>Nuestra Colaboración</h4>
                                <p>{{ organizacion.colaboracionDescripcion }}</p>
                                <p v-if="organizacion.fechaInicioColaboracion" class="text-muted">
                                    <i class="bi bi-calendar-event me-1"></i>
                                    Colaborando desde: {{ formatDate(organizacion.fechaInicioColaboracion) }}
                                </p>
                            </div>

                            <div class="card bg-light border-0 mb-4">
                                <div class="card-body">
                                    <h4 class="card-title">Información de Contacto</h4>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <p><i class="bi bi-envelope me-2"></i> {{ organizacion.contactoEmail }}</p>
                                            <p><i class="bi bi-telephone me-2"></i> {{ organizacion.contactoTlf }}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <p><i class="bi bi-geo-alt me-2"></i> {{ organizacion.direccion }}</p>
                                            <p v-if="organizacion.sitioWeb">
                                                <i class="bi bi-globe me-2"></i>
                                                <a :href="organizacion.sitioWeb" target="_blank"
                                                    rel="noopener noreferrer">
                                                    {{ organizacion.sitioWeb }}
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between">
                                <button class="btn btn-success" @click="volver">
                                    <i class="bi bi-arrow-left me-1"></i> Volver
                                </button>
                                <a v-if="organizacion.sitioWeb" :href="organizacion.sitioWeb" target="_blank"
                                    class="btn btn-primary">
                                    <i class="bi bi-box-arrow-up-right me-1"></i> Visitar sitio web
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div v-else class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '@/Constant';

export default {
    name: 'OrganizacionDetail',
    props: {
        slug: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const store = useStore();
        const router = useRouter();
        const organizacion = ref(null);
        const imagenError = ref(false);

        const logoUrl = computed(() => {
            if (imagenError.value || !organizacion.value?.logo) {
                return '/img/placeholder-logo.png';
            }
            return organizacion.value.logo;
        });

        const handleImageError = () => {
            imagenError.value = true;
        };

        const volver = () => {
            router.push({ name: 'apoyo' });
        };

        const formatDate = (dateString) => {
            if (!dateString) return '';
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return new Date(dateString).toLocaleDateString('es-ES', options);
        };

        const loadOrganizacion = async () => {
            try {
                organizacion.value = await store.dispatch('organizaciones/' + Constant.FETCH_ORGANIZACION_BY_SLUG, props.slug);
            } catch (error) {
                console.error('Error al cargar la organización:', error);
                router.push({ name: 'apoyo' });
            }
        };

        onMounted(() => {
            loadOrganizacion();
        });

        return {
            organizacion,
            logoUrl,
            handleImageError,
            volver,
            formatDate
        };
    }
}
</script>

<style scoped>
.organizacion-detail {
    background-color: #f8f9fa;
    min-height: 100vh;
}
</style>