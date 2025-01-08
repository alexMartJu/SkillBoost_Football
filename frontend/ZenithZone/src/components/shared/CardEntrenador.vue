<template>
    <div class="row justify-content-center align-items-center text-center">
        <div class="col-md-12">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <img :src="`/assets/entrenadores/${entrenador.images[0].imageUrl}`"  alt="Perfil" class="img-fluid rounded-circle mb-3" style="max-height: 100px;">
                    <h1 class="card-title">{{ entrenador.nombre }} {{ entrenador.apellidos }}</h1>
                    <h4>{{ entrenador.edad }} años</h4>
                    <h4 class="card-subtitle mb-3 text-muted">{{ entrenador.bio }}</h4>
                    <p class="mt-2">
                        <strong>Email de contacto: </strong>{{ entrenador.email }}
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import router from '@/router/router';
import { useRouter } from 'vue-router';

export default {
    props: {
        entrenador: {
            type: Object,
            required: true,
        },
    },

    setup(props) {
        const router = useRouter();

        const redirects = {
            profileEntrenador: () => router.push({ name: 'profileEntrenador', params: {numeroentrenador: props.entrenador.numeroentrenador} }),
        };

        return { redirects }
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