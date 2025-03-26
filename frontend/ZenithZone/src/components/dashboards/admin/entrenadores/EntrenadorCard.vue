<template>
    <div class="entrenador-card">
        <div class="card shadow-sm h-100">
            <div class="position-relative">
                <img :src="entrenador.image"
                    class="card-img-top entrenador-img" alt="Foto de perfil" @click="verPerfil">
                <div class="badge-container">
                    <span class="badge bg-primary">{{ entrenador.especialidad }}</span>
                </div>
            </div>
            <div class="card-body d-flex flex-column">
                <h5 class="card-title">{{ entrenador.nombre }} {{ entrenador.apellidos }}</h5>
                <p class="card-text text-muted small">Nº {{ entrenador.numeroEntrenador }}</p>
                <p class="card-text">
                    <small>
                        <i class="bi bi-award me-1"></i>
                        {{ entrenador.experienciaAnios }} años de experiencia
                    </small>
                </p>
                <div class="mt-auto">
                    <button class="btn btn-outline-danger w-100" @click="confirmarEliminar">
                        <i class="bi bi-trash me-1"></i> Eliminar
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import { useRouter } from 'vue-router';
import Swal from 'sweetalert2';

const props = defineProps({
    entrenador: {
        type: Object,
        required: true
    }
});

const emit = defineEmits(['eliminar']);
const router = useRouter();

const verPerfil = () => {
    if (props.entrenador.numeroEntrenador) {
        router.push({
            name: 'profileEntrenador',
            params: { numeroentrenador: props.entrenador.numeroEntrenador }
        });
    }
};

const confirmarEliminar = () => {
    Swal.fire({
        title: '¿Estás seguro?',
        text: `¿Deseas eliminar al entrenador ${props.entrenador.nombre} ${props.entrenador.apellidos}?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            emit('eliminar', props.entrenador.numeroEntrenador);
        }
    });
};
</script>

<style scoped>
.entrenador-card {
    transition: transform 0.3s ease;
}

.entrenador-card:hover {
    transform: translateY(-5px);
}

.entrenador-img {
    height: 200px;
    object-fit: cover;
    cursor: pointer;
}

.badge-container {
    position: absolute;
    bottom: 10px;
    right: 10px;
}

.card-title {
    font-size: 1.1rem;
    margin-bottom: 0.25rem;
}
</style>