<template>
    <div class="bg-light min-vh-100 pt-5">
        <div class="container py-5">
            <div v-if="loading" class="text-center my-5">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Cargando...</span>
                </div>
            </div>

            <div v-else-if="error" class="alert alert-danger text-center" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                {{ error }}
            </div>

            <div v-else class="row">
                <!-- Perfil de Usuario -->
                <template v-if="!isCoach">
                    <div class="col-lg-4 mb-4">
                        <div class="card shadow-sm h-100 border-0 rounded-3 overflow-hidden">
                            <div class="d-flex justify-content-between align-items-center bg-light p-3 border-bottom">
                                <div class="badge bg-white text-primary fw-bold shadow-sm px-3 py-2 rounded-3">
                                    {{ profile.numeroSocio }}
                                </div>
                                <div class="badge bg-primary text-white px-3 py-2 rounded-3 text-uppercase">
                                    <span v-if="profile.clubOrigen">Club</span>
                                    <span v-else-if="profile.organizacionOrigen">Social</span>
                                    <span v-else>Jugador</span>
                                </div>
                            </div>
                            <div class="bg-light" style="height: 280px;">
                                <img :src="profile.image || '/assets/default-profile.png'" alt="Foto de perfil"
                                    class="img-fluid w-100 h-100 object-fit-cover">
                            </div>
                            <div class="card-body text-center border-top">
                                <h2 class="card-title fw-bold mb-3">{{ profile.nombre }} {{ profile.apellidos }}</h2>
                                <div class="d-flex flex-wrap justify-content-center gap-2">
                                    <span v-if="profile.posicionPreferida" 
                                        class="badge bg-light text-secondary rounded-pill px-3 py-2">
                                        {{ profile.posicionPreferida }}
                                    </span>
                                    <span v-if="profile.clubOrigen" 
                                        class="badge bg-light text-secondary rounded-pill px-3 py-2">
                                        {{ profile.clubOrigen }}
                                    </span>
                                    <span v-if="profile.organizacionOrigen" 
                                        class="badge bg-success bg-opacity-10 text-success rounded-pill px-3 py-2">
                                        {{ profile.organizacionOrigen }}
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-8">
                        <div class="row h-100">
                            <div class="col-md-6 mb-4">
                                <div class="card shadow-sm h-100 border-0 rounded-3">
                                    <div class="card-header bg-white border-bottom-0 pt-4">
                                        <h3 class="d-flex justify-content-between align-items-center">
                                            <span>
                                                <i class="bi bi-person-fill me-2 text-primary"></i>
                                                Información Personal
                                            </span>
                                            <button v-if="isOwnProfile" @click="showEditModal = true"
                                                class="btn btn-primary btn-sm rounded-circle p-0" 
                                                style="width: 32px; height: 32px;">
                                                <i class="bi bi-pencil-square"></i>
                                            </button>
                                        </h3>
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item d-flex justify-content-between">
                                            <span>Edad:</span>
                                            <span class="fw-bold">{{ profile.edad || 'No especificado' }}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between">
                                            <span>Tipo:</span>
                                            <span class="fw-bold">{{ getPlayerType() }}</span>
                                        </li>
                                        <li class="list-group-item" v-if="profile.bio">
                                            <span class="d-block mb-2">Bio:</span>
                                            <p class="fst-italic text-secondary mb-0">{{ profile.bio }}</p>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="col-md-6 mb-4">
                                <div class="card shadow-sm h-100 border-0 rounded-3">
                                    <div class="card-header bg-white border-bottom-0 pt-4">
                                        <h3>
                                            <i class="bi bi-trophy-fill me-2 text-primary"></i>
                                            Información Deportiva
                                        </h3>
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item d-flex justify-content-between"
                                            v-if="profile.posicionPreferida">
                                            <span>Posición:</span>
                                            <span class="fw-bold">{{ profile.posicionPreferida }}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between"
                                            v-if="profile.clubOrigen">
                                            <span>Club:</span>
                                            <span class="fw-bold">{{ profile.clubOrigen }}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between"
                                            v-if="profile.organizacionOrigen">
                                            <span>Organización:</span>
                                            <span class="fw-bold">{{ profile.organizacionOrigen }}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between">
                                            <span>Entrenamientos disponibles:</span>
                                            <span class="fw-bold">{{ profile.entrenamientosDisponibles || 0 }}</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </template>

                <!-- Perfil de Entrenador -->
                <template v-else>
                    <div class="col-12">
                        <div class="card shadow-sm border-0 rounded-3 mb-4">
                            <div class="card-body p-4">
                                <div class="row">
                                    <div class="col-lg-4 text-center">
                                        <div class="position-relative mx-auto mb-3" style="width: 200px; height: 200px;">
                                            <div class="rounded-circle border border-4 border-primary overflow-hidden" 
                                                style="width: 200px; height: 200px;">
                                                <img :src="profile.image || '/assets/default-profile.png'"
                                                    alt="Foto de entrenador" 
                                                    class="img-fluid w-100 h-100 object-fit-cover">
                                            </div>
                                            <div class="position-absolute bottom-0 start-50 translate-middle-x">
                                                <div class="badge bg-primary text-white px-3 py-2 rounded-pill">
                                                    <i class="bi bi-whistle-fill me-2"></i>Entrenador
                                                </div>
                                            </div>
                                        </div>
                                        <h2 class="fw-bold mt-4">{{ profile.nombre }} {{ profile.apellidos }}</h2>
                                        <p class="text-primary fw-bold">Nº Entrenador: {{ profile.numeroEntrenador }}</p>
                                    </div>

                                    <div class="col-lg-8">
                                        <div class="row h-100">
                                            <div class="col-md-6 mb-4">
                                                <div class="card h-100 shadow-sm border-0 rounded-3">
                                                    <div class="card-header bg-white border-bottom-0 pt-4">
                                                        <h3>
                                                            <i class="bi bi-person-badge-fill me-2 text-primary"></i>
                                                            Información Profesional
                                                        </h3>
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item d-flex justify-content-between">
                                                            <span>Especialidad:</span>
                                                            <span class="fw-bold">{{ profile.especialidad || 'No especificado' }}</span>
                                                        </li>
                                                        <li class="list-group-item d-flex justify-content-between">
                                                            <span>Experiencia:</span>
                                                            <span class="fw-bold">{{ profile.experienciaAnios || 0 }} años</span>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <span class="d-block mb-2">Certificaciones:</span>
                                                            <p class="fst-italic text-secondary mb-0">
                                                                {{ profile.certificaciones || 'No especificado' }}
                                                            </p>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <div class="col-md-6 mb-4">
                                                <div class="card h-100 shadow-sm border-0 rounded-3">
                                                    <div class="card-header bg-white border-bottom-0 pt-4">
                                                        <h3 class="d-flex justify-content-between align-items-center">
                                                            <span>
                                                                <i class="bi bi-info-circle-fill me-2 text-primary"></i>
                                                                Información Personal
                                                            </span>
                                                            <button v-if="isOwnProfile" @click="showEditModal = true"
                                                                class="btn btn-primary btn-sm rounded-circle p-0" 
                                                                style="width: 32px; height: 32px;">
                                                                <i class="bi bi-pencil-square"></i>
                                                            </button>
                                                        </h3>
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item d-flex justify-content-between">
                                                            <span>Edad:</span>
                                                            <span class="fw-bold">{{ profile.edad || 'No especificado' }}</span>
                                                        </li>
                                                        <li class="list-group-item" v-if="profile.bio">
                                                            <span class="d-block mb-2">Bio:</span>
                                                            <p class="fst-italic text-secondary mb-0">{{ profile.bio }}</p>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </template>
                <div v-if="!loading && !error" class="container mb-5">
    <!-- Tabs de perfil solo para el propio usuario -->
    <ProfileTabs v-if="isOwnProfile" :userId="currentUser.id" />
</div>
            </div>
        </div>

        <!-- Toast de Bootstrap -->
        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div id="profileToast" class="toast" :class="{ 'show': showToast }" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header bg-primary text-white">
                    <i class="bi bi-info-circle-fill me-2"></i>
                    <strong class="me-auto">Notificación</strong>
                    <button type="button" class="btn-close btn-close-white" @click="showToast = false" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    {{ toastMessage }}
                </div>
            </div>
        </div>

        <!-- Modal de edición de perfil -->
        <EditProfileModal v-if="showEditModal" :profile="profile" :isCoach="isCoach" @close="showEditModal = false"
            @profile-updated="handleProfileUpdated" />
    </div>
</template>

<script>
import { ref, computed, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import EditProfileModal from '@/components/profile/EditProfileModal.vue';
import Constant from '@/Constant';
import ProfileTabs from '@/components/profile/ProfileTabs.vue';
export default {
    name: 'ProfileView',
    components: {
        EditProfileModal,
        ProfileTabs
    },
    setup() {
        const route = useRoute();
        const store = useStore();
        const profile = ref({});
        const loading = ref(true);
        const error = ref(null);
        const showEditModal = ref(false);
        const showToast = ref(false);
        const toastMessage = ref('');

        const currentUser = computed(() => store.getters['user/GetCurrentUser']);
        
        const isCoach = computed(() => {
            return profile.value.numeroEntrenador !== undefined && profile.value.numeroEntrenador !== null;
        });

        const isOwnProfile = computed(() => {
            if (!currentUser.value || !currentUser.value.profile) return false;

            if (isCoach.value) {
                return currentUser.value.profile.numeroEntrenador === profile.value.numeroEntrenador;
            } else {
                return currentUser.value.profile.numeroSocio === profile.value.numeroSocio;
            }
        });

        const getPlayerType = () => {
            if (profile.value.clubOrigen) return 'Jugador de Club';
            if (profile.value.organizacionOrigen) return 'Jugador Social';
            return 'Jugador';
        };

        // Usar la acción de Vuex para cargar el perfil
        const loadProfile = async () => {
            loading.value = true;
            error.value = null;

            try {
                const payload = {
                    numeroEntrenador: route.params.numeroentrenador,
                    numeroSocio: route.params.numeroSocio
                };
                
                // Dispatch la acción de Vuex para inicializar el perfil
                await store.dispatch(`user/${Constant.INITIALIZE_PROFILE}`, payload);
                
                // Obtener el perfil del estado de Vuex
                const storeProfile = store.getters['user/GetProfile'];
                
                if (!storeProfile || Object.keys(storeProfile).length === 0) {
                    throw new Error('No se pudo cargar el perfil');
                }
                
                profile.value = storeProfile;
            } catch (err) {
                console.error('Error al cargar el perfil:', err);
                error.value = 'No se pudo cargar el perfil. Por favor, inténtalo de nuevo más tarde.';
            } finally {
                loading.value = false;
            }
        };

        // Observar cambios en el perfil del store
        watch(() => store.getters['user/GetProfile'], (newProfile) => {
            if (newProfile && Object.keys(newProfile).length > 0) {
                profile.value = newProfile;
            }
        });

        const handleProfileUpdated = (updatedProfile) => {
            const currentUserEmail = store.getters['user/GetCurrentUser']?.email || '';
  
            // Verificar si se ha cambiado el email o la password
            const emailChanged = updatedProfile.emailChanged === true;
    const passwordChanged = updatedProfile.passwordChanged === true;

            // Actualizar el perfil local con los nuevos datos
            profile.value = { ...profile.value, ...updatedProfile };
            showEditModal.value = false;

            // Si se cambió el email o la password, mostrar toast y hacer logout
            if (emailChanged || passwordChanged) {
                // Mostrar toast en lugar de alert
                toastMessage.value = 'Has cambiado tus credenciales. Por seguridad, deberás iniciar sesión nuevamente.';
                showToast.value = true;
                
                // Hacer logout después de un breve retraso para que el usuario vea el mensaje
                setTimeout(() => {
                    const refreshToken = { refreshToken: localStorage.getItem('refreshToken') };
                    store.dispatch(`user/${Constant.LOGOUT}`, refreshToken);
                }, 3000);
            } else {
                // Mostrar toast de éxito
                toastMessage.value = 'Perfil actualizado correctamente';
                showToast.value = true;
                
                // Ocultar el toast después de 3 segundos
                setTimeout(() => {
                    showToast.value = false;
                }, 3000);
            }
        };

        onMounted(() => {
            loadProfile();
        });

        return {
            profile,
            loading,
            error,
            isCoach,
            isOwnProfile,
            getPlayerType,
            showEditModal,
            handleProfileUpdated,
            showToast,
            toastMessage,
            currentUser
        };
    }
};
</script>

<style scoped>
/* Estilos mínimos necesarios que no se pueden lograr con clases de Bootstrap */
.object-fit-cover {
    object-fit: cover;
}

/* Transiciones suaves para elementos interactivos */
.card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

/* Estilos para el toast */
.toast {
    transition: opacity 0.3s ease;
}

.toast.show {
    opacity: 1;
}

/* Ajustes para dispositivos móviles */
@media (max-width: 767.98px) {
    .card {
        margin-bottom: 1rem;
    }
}
</style>
