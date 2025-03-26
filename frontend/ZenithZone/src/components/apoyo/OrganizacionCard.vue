<template>
    <div class="card h-100 shadow-sm border-0 organizacion-card">
        <div class="card-img-container">
            <img :src="logoUrl" class="card-img-top" :alt="organizacion.nombre" @error="handleImageError">
        </div>
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-2">
                <h5 class="card-title mb-0">{{ organizacion.nombre }}</h5>
                <span class="badge bg-primary">{{ organizacion.tipo }}</span>
            </div>
            <p class="card-text description">{{ descripcionCorta }}</p>
            <p class="card-text"><small class="text-muted"><i class="bi bi-geo-alt-fill"></i> {{ organizacion.ciudad }},
                    {{ organizacion.pais }}</small></p>
        </div>
        <div class="card-footer bg-white border-top-0">
            <router-link :to="{ name: 'organizacionDetail', params: { slug: organizacion.slug } }"
                class="btn btn-primary w-100">
                Ver más
            </router-link>
        </div>
    </div>
</template>

<script>
import { ref, computed } from 'vue';

export default {
    name: 'OrganizacionCard',
    props: {
        organizacion: {
            type: Object,
            required: true
        }
    },
    setup(props) {
        const imagenError = ref(false);

        const descripcionCorta = computed(() => {
            if (!props.organizacion || !props.organizacion.descripcion) {
                return '';
            }
            return props.organizacion.descripcion.length > 150 ? props.organizacion.descripcion.substring(0, 150) + '...' : props.organizacion.descripcion;
        });

        const logoUrl = computed(() => {
            if (imagenError.value) {
                return '/img/placeholder-logo.png';
            }
            return props.organizacion.logo || '/img/placeholder-logo.png';
        });

        const handleImageError = () => {
            imagenError.value = true;
        };

        return {
            descripcionCorta,
            logoUrl,
            handleImageError
        };
    }
}
</script>

<style scoped>
.organizacion-card {
    transition: transform 0.3s ease;
}

.organizacion-card:hover {
    transform: translateY(-5px);
}

.card-img-container {
    height: 180px;
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #f8f9fa;
}

.card-img-top {
    max-height: 100%;
    object-fit: contain;
    padding: 1rem;
}

.description {
    min-height: 4.5rem;
}
</style>