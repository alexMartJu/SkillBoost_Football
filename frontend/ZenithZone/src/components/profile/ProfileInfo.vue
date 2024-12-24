<template>
<div class="container mt-5">
    <div class="row justify-content-center align-items-center">
        <!-- Imagen del perfil -->
        <div class="col-md-4 text-center mb-3 mb-md-0">
            <img :src="profile.image" alt="Perfil" class="img-fluid rounded-circle" style="width: 200px;">
        </div>

        <!-- Información del perfil -->
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body row flex d-flex p-3">
                    <div class="col-md-10">
                        <h1 class="card-title">{{ profile.nombre }} {{ profile.apellidos }}</h1>
                        <h4>{{ profile.edad }} años</h4>
                        <h4 class="card-subtitle mb-3 text-muted">Bio: {{ profile.bio }}</h4>                    
                        <p class="mt-2">
                            <strong>Numero de afiliación: </strong><span class="fw-bold">{{ profile.numeroSocio }}</span>
                        </p>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-center">
                        <div v-if="isCurrentUser">
                            <button class="btn btn-edit fw-bold shadow-sm">Editar Perfil</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
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
</style>