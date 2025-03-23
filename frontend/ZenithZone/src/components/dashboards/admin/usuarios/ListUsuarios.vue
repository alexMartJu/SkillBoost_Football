<template>
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="mb-0">Gestión de Usuarios</h2>
                <p class="text-muted">Administra todos los usuarios de la plataforma</p>
            </div>
            <div class="d-flex gap-2">
                <router-link to="/dashboardAdmin/usuarios/tutores" class="btn btn-outline-info">
                    <i class="bi bi-people-fill me-2"></i>Tutores
                </router-link>
                <router-link to="/dashboardAdmin/usuarios/jugadores-club" class="btn btn-outline-success">
                    <i class="bi bi-trophy-fill me-2"></i>Jugadores Club
                </router-link>
                <router-link to="/dashboardAdmin/usuarios/jugadores" class="btn btn-outline-primary">
                    <i class="bi bi-person-fill me-2"></i>Jugadores
                </router-link>
                <router-link to="/dashboardAdmin/usuarios/jugadores-sociales" class="btn btn-outline-warning">
                    <i class="bi bi-heart-fill me-2"></i>Jugadores Sociales
                </router-link>
            </div>
        </div>

        <div class="card shadow-sm border-0 mb-4">
            <div class="card-body">
                <div class="row align-items-center">
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-text bg-light border-0">
                                <i class="bi bi-search"></i>
                            </span>
                            <input type="text" class="form-control border-0 bg-light" placeholder="Buscar usuario..."
                                v-model="searchTerm">
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="d-flex justify-content-md-end mt-3 mt-md-0">
                            <div class="btn-group">
                                <button v-for="tab in tabs" :key="tab.value" class="btn"
                                    :class="activeTab === tab.value ? 'btn-primary' : 'btn-outline-primary'"
                                    @click="activeTab = tab.value">
                                    {{ tab.label }}
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2 text-muted">Cargando usuarios...</p>
        </div>

        <!-- Mensaje de error -->
        <div v-else-if="error" class="alert alert-danger">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            {{ error }}
        </div>

        <!-- Sin resultados -->
        <div v-else-if="filteredUsers.length === 0" class="text-center py-5">
            <i class="bi bi-search display-1 text-muted"></i>
            <p class="mt-3 text-muted">No se encontraron usuarios que coincidan con tu búsqueda.</p>
        </div>

        <!-- Lista de usuarios -->
        <div v-else>
            <div class="row g-4">
                <div v-for="user in paginatedUsers" :key="user.numeroSocio || user.numeroEntrenador"
                    class="col-12 col-md-6 col-lg-4 col-xl-3">
                    <UserCard :profile="user" :userType="getUserType(user)" />
                </div>
            </div>

            <!-- Paginación usando el componente existente -->
            <Paginate v-if="totalPages > 1" :page="currentPage" :page-count="totalPages"
                @update:page="updatePage" @page-click="handlePageClick" class="mt-4" />
        </div>
    </div>
</template>

<script>
import { ref, computed, watch, onMounted } from 'vue';
import { useStore } from 'vuex';
import UserCard from './UserCard.vue';
import Paginate from '@/components/filters/Paginate.vue';
import Constant from '@/Constant';

export default {
    name: 'ListUsuarios',
    components: {
        UserCard,
        Paginate
    },
    setup() {
        const store = useStore();
        //Estado reactivo
        const loading = ref(true);
        const error = ref(null);
        const searchTerm = ref('');
        const activeTab = ref('all');
        const currentPage = ref(1);
        const itemsPerPage = 12;
        
        //Datos estáticos
        const tabs = [
            { label: 'Todos', value: 'all' },
            { label: 'Tutores', value: 'tutor' },
            { label: 'Jugadores Club', value: 'jugadorClub' },
            { label: 'Jugadores', value: 'jugador' },
            { label: 'Jugadores Sociales', value: 'jugadorSocial' }
        ];
        
        const getTutores = computed(() => store.getters['adminDashboard/getTutores'] || []);
        const getJugadoresClub = computed(() => store.getters['adminDashboard/getJugadoresClub'] || []);
        const getJugadores = computed(() => store.getters['adminDashboard/getJugadores'] || []);
        const getJugadoresSociales = computed(() => store.getters['adminDashboard/getJugadoresSociales'] || []);
        const isLoadingUsers = computed(() => store.getters['adminDashboard/isLoadingUsers']);
        
        const allUsers = computed(() => {
            return [
                ...getTutores.value.map(user => ({ ...user, _type: 'tutor' })),
                ...getJugadoresClub.value.map(user => ({ ...user, _type: 'jugadorClub' })),
                ...getJugadores.value.map(user => ({ ...user, _type: 'jugador' })),
                ...getJugadoresSociales.value.map(user => ({ ...user, _type: 'jugadorSocial' }))
            ];
        });
        
        const filteredUsers = computed(() => {
            let users = [];

            //Filtrar por tipo
            if (activeTab.value === 'all') {
                users = allUsers.value;
            } else {
                users = allUsers.value.filter(user => user._type === activeTab.value);
            }

            //Filtrar por término de búsqueda
            if (searchTerm.value.trim()) {
                const searchLower = searchTerm.value.toLowerCase();
                users = users.filter(user =>
                    (user.nombre && user.nombre.toLowerCase().includes(searchLower)) ||
                    (user.apellidos && user.apellidos.toLowerCase().includes(searchLower)) ||
                    (user.numeroSocio && user.numeroSocio.toLowerCase().includes(searchLower)) ||
                    (user.numeroEntrenador && user.numeroEntrenador.toLowerCase().includes(searchLower))
                );
            }

            return users;
        });
        
        const totalPages = computed(() => {
            return Math.ceil(filteredUsers.value.length / itemsPerPage);
        });
        
        const paginatedUsers = computed(() => {
            const start = (currentPage.value - 1) * itemsPerPage;
            const end = start + itemsPerPage;
            return filteredUsers.value.slice(start, end);
        });
        
        watch(searchTerm, () => {
            currentPage.value = 1;
        });
        
        watch(activeTab, () => {
            currentPage.value = 1;
        });
        
        watch(isLoadingUsers, (newVal) => {
            loading.value = newVal;
        });

        const getUserType = (user) => {
            return user._type || 'jugador';
        };
        
        const updatePage = (pageNum) => {
            currentPage.value = pageNum;
        };
        
        const handlePageClick = (pageNum) => {
            currentPage.value = pageNum;
            window.scrollTo(0, 0);
        };
        
        const loadAllUsers = async () => {
            loading.value = true;
            error.value = null;

            try {
                await Promise.all([
                    store.dispatch(`adminDashboard/${Constant.FETCH_TUTORES}`),
                    store.dispatch(`adminDashboard/${Constant.FETCH_JUGADORES_CLUB}`),
                    store.dispatch(`adminDashboard/${Constant.FETCH_JUGADORES}`),
                    store.dispatch(`adminDashboard/${Constant.FETCH_JUGADORES_SOCIALES}`)
                ]);
            } catch (e) {
                console.error('Error al cargar usuarios:', e);
                error.value = 'Ocurrió un error al cargar los usuarios. Por favor, intenta nuevamente.';
            } finally {
                loading.value = false;
            }
        };
        
        onMounted(() => {
            loadAllUsers();
        });
        
        return {
            loading,
            error,
            searchTerm,
            activeTab,
            currentPage,
            tabs,
            filteredUsers,
            totalPages,
            paginatedUsers,
            getUserType,
            updatePage,
            handlePageClick
        };
    }
};
</script>

<style scoped>
.btn-group .btn {
    border-radius: 0;
}

.btn-group .btn:first-child {
    border-top-left-radius: 0.375rem;
    border-bottom-left-radius: 0.375rem;
}

.btn-group .btn:last-child {
    border-top-right-radius: 0.375rem;
    border-bottom-right-radius: 0.375rem;
}
</style>
