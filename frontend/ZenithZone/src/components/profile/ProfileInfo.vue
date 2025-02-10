<template>
<div class="container mt-5">
    <div class="row justify-content-center align-items-center">
        <div class="col-md-4 text-center mb-3 mb-md-0">
            <div class="profile-image-container">
                <img v-if="profile?.numeroentrenador" 
                    :src="`/assets/entrenadores/${profile.imagenes[0].image_url}`" 
                    alt="Perfil" 
                    class="profile-image">
                <img v-else 
                    :src="profile.image" 
                    alt="Perfil" 
                    class="profile-image">
            </div>
        </div>

        <div class="col-md-8">
            <div class="profile-card">
                <div class="card-body row flex d-flex p-4">
                    <div class="col-md-10">
                        <h1 class="profile-name">{{ profile.nombre }} {{ profile.apellidos }}</h1>
                        <div class="profile-age">{{ profile.edad }} años</div>
                        <p class="profile-bio">{{ profile.bio }}</p>
                        <div class="profile-affiliation">
                            <span class="label">Numero de afiliación:</span>
                            <span class="value">{{ numeroUser }}</span>
                        </div>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-center profile-divider">
                        <div v-if="isCurrentUser">
                            <button class="edit-button" 
                                v-if="profile.numeroSocio" 
                                @click="redirects.edit">
                                <i class="bi bi-pencil-square me-2"></i>Editar Perfil
                            </button>
                            <button class="edit-button" 
                                v-else-if="profile.numeroentrenador" 
                                @click="redirects.editEntrenador">
                                <i class="bi bi-pencil-square me-2"></i>Editar Perfil
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import router from '@/router/router';

export default {
    props: {
        profile: {
            type: Object,
            required: true,
        },
        isCurrentUser: {
            type: Boolean,
            required: true
        }
    },

    setup(props) {
        const redirects = {
            edit: () => router.push({ name: 'profileEdit' }),
            editEntrenador: () => router.push({ name: 'profileEntrenadorEdit' }),
        };

        const numeroUser = props.profile.numeroSocio ? props.profile.numeroSocio : props.profile.numeroentrenador;

        return { redirects, numeroUser }
    }
}
</script>

<style scoped>
.profile-image-container {
    position: relative;
    display: inline-block;
    padding: 8px;
    background: linear-gradient(45deg, #ff6600, #ff8533);
    border-radius: 50%;
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.profile-image {
    height: 200px;
    width: 200px;
    object-fit: cover;
    border-radius: 50%;
    border: 4px solid white;
    transition: transform 0.3s ease;
}

.profile-image:hover {
    transform: scale(1.05);
}

.profile-card {
    background: white;
    border-radius: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease;
}

.profile-card:hover {
    transform: translateY(-5px);
}

.profile-name {
    font-size: 2.5rem;
    font-weight: 700;
    color: #2c3e50;
    margin-bottom: 0.5rem;
}

.profile-age {
    font-size: 1.2rem;
    color: #ff6600;
    font-weight: 600;
    margin-bottom: 1rem;
}

.profile-bio {
    font-size: 1.1rem;
    color: #666;
    line-height: 1.6;
    margin-bottom: 1.5rem;
}

.profile-affiliation {
    background: #f8f9fa;
    padding: 0.8rem 1.2rem;
    border-radius: 10px;
    display: inline-block;
}

.profile-affiliation .label {
    font-weight: 600;
    color: #2c3e50;
    margin-right: 0.5rem;
}

.profile-affiliation .value {
    color: #ff6600;
    font-weight: 500;
}

.profile-divider {
    border-left: 2px solid #eee;
}

.edit-button {
    background: linear-gradient(45deg, #ff6600, #ff8533);
    color: white;
    border: none;
    padding: 0.8rem 1.5rem;
    border-radius: 12px;
    font-weight: 600;
    transition: all 0.3s ease;
    box-shadow: 0 4px 15px rgba(255, 102, 0, 0.2);
}

.edit-button:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 20px rgba(255, 102, 0, 0.3);
}

.edit-button:active {
    transform: translateY(0);
}
</style>