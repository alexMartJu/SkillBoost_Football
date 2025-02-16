<template>
    <div class="card shadow-sm border-0 h-100">
        <img v-if="deporte.images && deporte.images.length > 0" :src="'/assets/deportes/' + deporte.images[0].image_url"
            alt="Imagen de Deporte" class="card-img-top img-fluid" />
        <div class="card-body d-flex flex-column">
            <h5 class="card-title mb-3">{{ deporte.nombre }}</h5>
            <div class="mt-auto">
                <button @click="editarDeporte(deporte)" class="btn btn-outline-primary me-2">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button @click="confirmarEliminar(deporte.slug)" class="btn btn-outline-danger">
                    <i class="bi bi-trash"></i> Eliminar
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CardDeportes',
    props: {
        deporte: Object
    },
    methods: {
        editarDeporte(deporte) {
            this.$emit('editarDeporte', deporte);
            this.$router.push({ name: 'adminEditar', params: { slug: deporte.slug, type: 'deporte' } });
        },
        confirmarEliminar(deporteSlug) {
            if (confirm("¿Estás seguro de que deseas eliminar este deporte?")) {
                this.eliminarDeporte(deporteSlug);
            }
        },
        eliminarDeporte(deporteSlug) {
            this.$store.dispatch('adminDashboard/DeteleOneDeporte', deporteSlug)
                .then(() => console.log("Deporte eliminado correctamente"))
                .catch(error => console.error("Error al eliminar el deporte:", error));
        }
    }
};
</script>

<style scoped>
.card {
    transition: transform 0.2s ease-in-out;
}

.card:hover {
    transform: translateY(-5px);
}

.card-img-top {
    height: 200px;
    object-fit: cover;
}

.btn {
    text-transform: uppercase;
    font-size: 0.875rem;
    letter-spacing: 0.5px;
}
</style>