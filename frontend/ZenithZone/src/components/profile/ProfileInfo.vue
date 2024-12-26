<template>
<div class="container mt-5">
    <div class="row justify-content-center align-items-center">
        <div class="col-md-4 text-center mb-3 mb-md-0">
            <!-- Imagen si es entrenador -->
            <img v-if="profile?.numeroEntrenador" :src="`/assets/entrenadores/${profile.imagenes[0].image_url}`" alt="Perfil" 
                class="img-fluid rounded-circle" style="max-height: 200px;">
            <!-- Imagen si es user -->
            <img v-else :src="profile.image" alt="Perfil" 
            class="img-fluid rounded-circle" style="max-height: 200px;">
        </div>

        <!-- Información del perfil -->
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body row flex d-flex p-3">
                    <div class="col-md-10">
                        <h1 class="card-title">{{ profile.nombre }} {{ profile.apellidos }}</h1>
                        <h4>{{ profile.edad }} años</h4>
                        <h4 class="card-subtitle mb-3 text-muted">{{ profile.bio }}</h4>                    
                        <p class="mt-2">
                            <strong>Numero de afiliación: </strong><span class="fw-bold">{{ numeroUser }}</span>
                        </p>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-center separador">
                        <div v-if="isCurrentUser">
                            <button class="btn btn-edit fw-bold shadow-sm" v-if="profile.numeroSocio" @click="redirects.edit">Editar Perfil</button>
                            <button class="btn btn-edit fw-bold shadow-sm" v-else-if="profile.numeroEntrenador" @click="redirects.editEntrenador">Editar Perfil</button>
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

        const numeroUser = props.profile.numeroSocio ? props.profile.numeroSocio : props.profile.numeroEntrenador;

        return { redirects, numeroUser }
    }
}
</script>

<style>
    .btn-edit {
        background-color: #ff6600;
        border-radius: 10px;
        color: black;
        transition: all 0.3s;

        &:hover {
            color: black;
            background-color: #ff6600;
        }
    }

    .separador {
        border-left: 1px solid rgb(170, 170, 170);
    }
</style>