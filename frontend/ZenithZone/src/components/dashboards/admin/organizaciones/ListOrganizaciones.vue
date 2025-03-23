<template>
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 mb-0 text-gray-800">Gestión de Organizaciones</h1>
            <router-link to="/dashboardAdmin/organizaciones/crear" class="btn btn-primary">
                <i class="bi bi-plus-circle me-1"></i> Nueva Organización
            </router-link>
        </div>

        <div class="card shadow mb-4">
            <div class="card-header py-3 d-flex justify-content-between align-items-center">
                <h6 class="m-0 font-weight-bold text-primary">Organizaciones Registradas</h6>
            </div>
            <div class="card-body">
                <div v-if="loading" class="text-center py-5">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Cargando...</span>
                    </div>
                </div>
                <div v-else-if="organizaciones.length === 0" class="text-center py-5">
                    <p class="mb-0">No hay organizaciones registradas.</p>
                </div>
                <div v-else class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>Nombre</th>
                                <th>Tipo</th>
                                <th>Ciudad</th>
                                <th>País</th>
                                <th>Contacto</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="organizacion in organizaciones" :key="organizacion.slug">
                                <td>{{ organizacion.nombre }}</td>
                                <td>{{ organizacion.tipo }}</td>
                                <td>{{ organizacion.ciudad || '-' }}</td>
                                <td>{{ organizacion.pais || '-' }}</td>
                                <td>{{ organizacion.contactoEmail || '-' }}</td>
                                <td>
                                    <div class="btn-group btn-group-sm">
                                        <a :href="`/apoyo/${organizacion.slug}`" target="_blank" class="btn btn-info">
                                            <i class="bi bi-eye"></i> Ver
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '@/Constant';

export default {
    name: 'ListOrganizaciones',
    setup() {
        const store = useStore();
        const loading = ref(true);

        const organizaciones = computed(() => {
            return store.getters['adminDashboard/GetOrganizaciones'];
        });

        const cargarOrganizaciones = async () => {
            loading.value = true;
            try {
                await store.dispatch('adminDashboard/' + Constant.INITIALIZE_ORGANIZACIONES_ADMIN);
            } catch (error) {
                console.error('Error al cargar organizaciones:', error);
            } finally {
                loading.value = false;
            }
        };

        onMounted(() => {
            cargarOrganizaciones();
        });

        return {
            organizaciones,
            loading
        };
    }
};
</script>