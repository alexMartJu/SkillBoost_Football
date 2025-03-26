<template>
    <div class="unirse-container">
        <button v-if="!isSuscribed" class="btn btn-success w-100" @click="unirseEntrenamiento(slug)">
            <i class="bi bi-check-circle me-2"></i>Inscribirse
        </button>
        <div v-else class="suscribed-badge">
            <span class="badge bg-success">
                <i class="bi bi-check-circle-fill me-1"></i>Ya estás inscrito
            </span>
            <button class="btn btn-outline-danger btn-sm mt-1 w-100" @click="verDetalles">
                <i class="bi bi-info-circle me-1"></i>Ver detalles
            </button>
        </div>
    </div>
</template>

<script>
import profileService from '@/services/client/profile.service';
import Swal from 'sweetalert2';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
import Constant from '@/Constant';

export default {
    props: {
        slug: {
            type: String,
            Required: true
        },
        isSuscribed: {
            type: Boolean,
            default: false
        }
    },

    setup(props) {
        const router = useRouter();
        const store = useStore();
        
        //Obtener el usuario actual
        const currentUser = computed(() => store.getters['user/GetCurrentUser']);
        
        //Función para navegar al perfil
        const navigateToProfile = () => {
            //Verificar si el usuario tiene un perfil con numeroSocio
            if (currentUser.value && currentUser.value.profile && currentUser.value.profile.numeroSocio) {
                router.push({ 
                    name: 'profile', 
                    params: { numeroSocio: currentUser.value.profile.numeroSocio } 
                });
            } else {
                //Si no tenemos el numeroSocio, navegamos a la página principal
                console.warn("No se pudo obtener el numeroSocio, navegando a la página principal");
                router.push({ name: 'home' });
            }
        };
        
        const unirseEntrenamiento = async (slug) => {
            const result = await Swal.fire({
                title: '¿Quieres apuntarte a este entrenamiento?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí',
                cancelButtonText: 'No'
            });

            if (result.isConfirmed) {
                try {
                    await profileService.UnirseEntrenamiento(slug);
                    //Actualizar los entrenamientos suscritos en el store
                    store.dispatch(`entrenamientos/${Constant.FETCH_SUSCRIBED_ENTRENAMIENTOS}`);
                    
                    Swal.fire({
                        title: '¡Inscripción exitosa!',
                        text: 'Te has apuntado al entrenamiento correctamente.',
                        icon: 'success',
                        confirmButtonText: 'Ver mis entrenamientos',
                        showCancelButton: true,
                        cancelButtonText: 'Seguir explorando'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            navigateToProfile();
                        }
                    });
                } catch (e) {
                    console.error("Error al unirse al entrenamiento:", e);
                    Swal.fire(
                        'Error',
                        'No se pudo completar la inscripción. Por favor, inténtalo de nuevo.',
                        'error'
                    );
                }
            }
        }
        
        const verDetalles = () => {
            Swal.fire({
                title: '¡Ya estás inscrito!',
                html: `
                    <div class="text-start">
                        <p>Ya te has inscrito a este entrenamiento. Puedes:</p>
                        <ul>
                            <li>Ver todos tus entrenamientos en tu perfil</li>
                            <li>Cancelar tu inscripción desde tu perfil si lo deseas</li>
                        </ul>
                    </div>
                `,
                icon: 'info',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ver mis entrenamientos',
                showCancelButton: true,
                cancelButtonText: 'Cerrar',
                cancelButtonColor: '#d33',
            }).then((result) => {
                if (result.isConfirmed) {
                    navigateToProfile();
                }
            });
        }

        return { 
            unirseEntrenamiento,
            verDetalles
        }
    }
}
</script>

<style scoped>
.unirse-container {
    width: 100%;
    display: flex;
    flex-direction: column;
}

.suscribed-badge {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}

.badge {
    font-size: 0.9rem;
    padding: 8px 12px !important;
    border-radius: 20px;
    animation: pulse 2s infinite;
    width: 100%;
    text-align: center;
    white-space: normal;
}

@keyframes pulse {
    0% {
        box-shadow: 0 0 0 0 rgba(40, 167, 69, 0.7);
    }
    70% {
        box-shadow: 0 0 0 10px rgba(40, 167, 69, 0);
    }
    100% {
        box-shadow: 0 0 0 0 rgba(40, 167, 69, 0);
    }
}

.btn-outline-danger {
    border-radius: 20px;
    font-size: 0.8rem;
    transition: all 0.3s;
}

.btn-outline-danger:hover {
    transform: translateY(-2px);
}

/* Media queries para mejorar la responsividad */
@media (max-width: 991.98px) {
    .unirse-container {
        flex: 1;
    }
    
    .suscribed-badge {
        height: 100%;
        justify-content: center;
    }
    
    .badge {
        margin-bottom: 0.5rem;
    }
}

@media (max-width: 767.98px) {
    .badge {
        font-size: 0.8rem;
        padding: 6px 10px !important;
    }
    
    .btn-outline-danger {
        font-size: 0.75rem;
        padding: 0.25rem 0.5rem;
    }
}
</style>
