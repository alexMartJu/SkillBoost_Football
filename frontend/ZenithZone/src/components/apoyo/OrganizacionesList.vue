<template>
    <div class="organizaciones-list py-5">
        <div class="container">
            <!-- Filtros -->
            <div class="row mb-5">
                <div class="col-12">
                    <div class="d-flex flex-wrap justify-content-center gap-2">
                        <button v-for="tipo in tiposOrganizaciones" :key="tipo.value" @click="filtroActual = tipo.value"
                            class="btn mb-2"
                            :class="filtroActual === tipo.value ? 'btn-primary' : 'btn-outline-primary'">
                            {{ tipo.label }}
                        </button>
                    </div>
                </div>
            </div>

            <!-- Lista de organizaciones -->
            <div class="row g-4">
                <div v-if="organizacionesFiltradas.length === 0" class="col-12 text-center">
                    <p class="fs-5">No se encontraron organizaciones en esta categoría.</p>
                </div>

                <div v-for="organizacion in organizacionesFiltradas" :key="organizacion.slug" class="col-md-6 col-lg-4">
                    <OrganizacionCard :organizacion="organizacion" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
import OrganizacionCard from './OrganizacionCard.vue';

export default {
    name: 'OrganizacionesList',
    components: {
        OrganizacionCard
    },
    setup() {
        const store = useStore();
        const filtroActual = ref('todos');

        const tiposOrganizaciones = [
            { value: 'todos', label: 'Todas' },
            { value: 'fundacion', label: 'Fundaciones' },
            { value: 'ong', label: 'ONGs' },
            { value: 'asociacion', label: 'Asociaciones' },
            { value: 'escuela', label: 'Escuelas' }
        ];

        const organizacionesArray = computed(() => {
            const organizaciones = store.getters['organizaciones/GetOrganizaciones'];
            return organizaciones && organizaciones.organizaciones ? organizaciones.organizaciones : [];
        });

        const organizacionesFiltradas = computed(() => {
            if (filtroActual.value === 'todos') {
                return organizacionesArray.value;
            }

            return organizacionesArray.value.filter(org => {
                if (!org.tipo) return false;
                const tipoLowerCase = org.tipo.toLowerCase();
                switch (filtroActual.value) {
                    case 'fundacion':
                        return tipoLowerCase.includes('fundación') || tipoLowerCase.includes('fundacion');
                    case 'ong':
                        return tipoLowerCase.includes('ong');
                    case 'asociacion':
                        return tipoLowerCase.includes('asociación') || tipoLowerCase.includes('asociacion');
                    case 'escuela':
                        return tipoLowerCase.includes('escuela');
                    default:
                        return false;
                }
            });
            
        });

        return {
            filtroActual,
            tiposOrganizaciones,
            organizacionesFiltradas
        };
    }
}
</script>

<style scoped>
.organizaciones-list {
    background-color: #ffffff;
}

@media (max-width: 576px) {
    .d-flex.flex-wrap button {
        flex: 1 0 calc(50% - 0.5rem);
        max-width: calc(50% - 0.5rem);
    }
}
</style>