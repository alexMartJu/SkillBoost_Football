<template>
    <div class="card h-100 border-0 shadow-sm hover-card">
        <div class="position-relative">
            <img :src="profile.image" class="card-img-top user-img" alt="Foto de perfil" @click="navigateToProfile" />
            <div class="position-absolute top-0 end-0 m-2">
                <span class="badge rounded-pill" :class="badgeClass">
                    {{ userTypeLabel }}
                </span>
            </div>
        </div>
        <div class="card-body d-flex flex-column">
            <h5 class="card-title fw-bold mb-1">{{ profile.nombre }} {{ profile.apellidos }}</h5>
            <p class="card-text text-muted small mb-2">
                <i class="bi bi-person-badge me-1"></i> {{ profile.numeroSocio }}
            </p>

            <div class="mt-2">
                <p v-if="profile.edad" class="card-text mb-1 small">
                    <i class="bi bi-calendar3 me-1"></i> {{ profile.edad }} años
                    <span v-if="profile.esMenor" class="ms-2 badge bg-warning text-dark">Menor</span>
                </p>

                <p v-if="profile.posicionPreferida" class="card-text mb-1 small">
                    <i class="bi bi-trophy me-1"></i> {{ profile.posicionPreferida }}
                </p>

                <p v-if="profile.clubOrigen" class="card-text mb-1 small">
                    <i class="bi bi-house me-1"></i> {{ profile.clubOrigen }}
                </p>

                <p v-if="profile.numeroEntrenador" class="card-text mb-1 small">
                    <i class="bi bi-award me-1"></i> Entrenador #{{ profile.numeroEntrenador }}
                </p>

                <p v-if="profile.organizacionOrigen" class="card-text mb-1 small">
                    <i class="bi bi-building me-1"></i> {{ profile.organizacionOrigen }}
                </p>
            </div>

            <div class="mt-auto pt-3">
                <button @click="navigateToProfile" class="btn btn-sm btn-outline-primary w-100">
                    <i class="bi bi-eye me-1"></i> Ver perfil
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import { computed } from 'vue';
import { useRouter } from 'vue-router';

export default {
    name: 'UserCard',
    props: {
        profile: {
            type: Object,
            required: true
        },
        userType: {
            type: String,
            required: true,
            validator: (value) => ['tutor', 'jugadorClub', 'jugador', 'jugadorSocial'].includes(value)
        }
    },
    setup(props) {
        const router = useRouter();
        const badgeClass = computed(() => {
            switch(props.userType) {
                case 'tutor': return 'bg-info';
                case 'jugadorClub': return 'bg-success';
                case 'jugador': return 'bg-primary';
                case 'jugadorSocial': return 'bg-warning text-dark';
                default: return 'bg-secondary';
            }
        });

        const userTypeLabel = computed(() => {
            switch(props.userType) {
                case 'tutor': return 'Tutor';
                case 'jugadorClub': return 'Jugador Club';
                case 'jugador': return 'Jugador';
                case 'jugadorSocial': return 'Jugador Social';
                default: return 'Usuario';
            }
        });

        const navigateToProfile = () => {
            if (props.profile.numeroEntrenador) {
                router.push({
                    name: 'profileEntrenador',
                    params: { numeroentrenador: props.profile.numeroEntrenador }
                });
            } else {
                router.push({
                    name: 'profile',
                    params: { numeroSocio: props.profile.numeroSocio }
                });
            }
        };

        return {
            badgeClass,
            userTypeLabel,
            navigateToProfile
        };
    }
};
</script>

<style scoped>
.user-img {
    height: 200px;
    object-fit: cover;
    cursor: pointer;
    transition: transform 0.3s ease;
}

.user-img:hover {
    transform: scale(1.03);
}

.hover-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.hover-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}
</style>
