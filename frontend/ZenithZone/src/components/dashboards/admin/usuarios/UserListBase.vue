<template>
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="mb-0">{{ title }}</h2>
                <p class="text-muted">{{ subtitle }}</p>
            </div>
            <router-link to="/dashboardAdmin/usuarios" class="btn btn-outline-secondary">
                <i class="bi bi-arrow-left me-2"></i>Volver a todos los usuarios
            </router-link>
        </div>

        <div class="card shadow-sm border-0 mb-4">
            <div class="card-body">
                <div class="row align-items-center">
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-text bg-light border-0">
                                <i class="bi bi-search"></i>
                            </span>
                            <input type="text" class="form-control border-0 bg-light" :placeholder="searchPlaceholder"
                                v-model="searchTerm">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="d-flex justify-content-md-end mt-3 mt-md-0">
                            <button :class="`btn btn-${buttonColor}`" @click="refreshData">
                                <i class="bi bi-arrow-clockwise me-2"></i>Actualizar
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div :class="`spinner-border text-${buttonColor}`" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2 text-muted">{{ loadingMessage }}</p>
        </div>

        <!-- Mensaje de error -->
        <div v-else-if="error" class="alert alert-danger">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            {{ error }}
        </div>

        <!-- Sin resultados -->
        <div v-else-if="filteredUsers.length === 0" class="text-center py-5">
            <i :class="`bi bi-${emptyIcon} display-1 text-muted`"></i>
            <p class="mt-3 text-muted">{{ emptyMessage }}</p>
        </div>

        <!-- Lista de usuarios -->
        <div v-else>
            <div class="row g-4">
                <div v-for="user in paginatedUsers" :key="user.numeroSocio" class="col-12 col-md-6 col-lg-4 col-xl-3">
                    <UserCard :profile="user" :userType="userType" />
                </div>
            </div>

            <!-- Paginación -->
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

export default {
    name: 'UserListBase',
    components: {
        UserCard,
        Paginate
    },
    props: {
        title: {
            type: String,
            required: true
        },
        subtitle: {
            type: String,
            default: 'Administra los usuarios de la plataforma'
        },
        userType: {
            type: String,
            required: true,
            validator: (value) => ['tutor', 'jugadorClub', 'jugador', 'jugadorSocial'].includes(value)
        },
        getterName: {
            type: String,
            required: true
        },
        actionName: {
            type: String,
            required: true
        },
        searchPlaceholder: {
            type: String,
            default: 'Buscar usuario...'
        },
        buttonColor: {
            type: String,
            default: 'primary'
        },
        loadingMessage: {
            type: String,
            default: 'Cargando usuarios...'
        },
        emptyIcon: {
            type: String,
            default: 'search'
        },
        emptyMessage: {
            type: String,
            default: 'No se encontraron usuarios que coincidan con tu búsqueda.'
        },
        searchFields: {
            type: Array,
            default: () => ['nombre', 'apellidos', 'numeroSocio']
        }
    },
    setup(props) {
        const store = useStore();
        const loading = ref(true);
        const error = ref(null);
        const searchTerm = ref('');
        const currentPage = ref(1);
        const itemsPerPage = 12;
        
        const isLoadingUsers = computed(() => store.getters['adminDashboard/isLoadingUsers']);
        
        const users = computed(() => {
            return store.getters[`adminDashboard/${props.getterName}`] || [];
        });
        
        const filteredUsers = computed(() => {
            if (!searchTerm.value.trim()) {
                return users.value;
            }

            const searchLower = searchTerm.value.toLowerCase();
            return users.value.filter(user => {
                return props.searchFields.some(field => {
                    return user[field] && user[field].toLowerCase().includes(searchLower);
                });
            });
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
        
        watch(isLoadingUsers, (newVal) => {
            loading.value = newVal;
        });
        
        const updatePage = (pageNum) => {
            currentPage.value = pageNum;
        };
        
        const handlePageClick = (pageNum) => {
            currentPage.value = pageNum;
            window.scrollTo(0, 0);
        };
        
        const refreshData = async () => {
            loading.value = true;
            error.value = null;

            try {
                await store.dispatch(`adminDashboard/${props.actionName}`);
            } catch (e) {
                console.error(`Error al cargar ${props.title.toLowerCase()}:`, e);
                error.value = `Ocurrió un error al cargar los ${props.title.toLowerCase()}. Por favor, intenta nuevamente.`;
            } finally {
                loading.value = false;
            }
        };
        
        onMounted(() => {
            refreshData();
        });
        
        return {
            loading,
            error,
            searchTerm,
            currentPage,
            filteredUsers,
            totalPages,
            paginatedUsers,
            updatePage,
            handlePageClick,
            refreshData
        };
    }
};
</script>

<style scoped>
</style>
