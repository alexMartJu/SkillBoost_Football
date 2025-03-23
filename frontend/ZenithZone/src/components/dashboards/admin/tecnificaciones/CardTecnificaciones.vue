<template>
    <div class="card shadow-sm border-0 h-100">
        <img v-if="tecnificacion.images && tecnificacion.images.length > 0"
            :src="'/assets/tecnificaciones/' + tecnificacion.images[0].image_url" alt="Imagen de Tecnificación"
            class="card-img-top img-fluid" />
        <div class="card-body d-flex flex-column">
            <h5 class="card-title mb-3">{{ tecnificacion.nombre }}</h5>
            <p class="card-text text-muted mb-3">{{ tecnificacion.descripcion }}</p>
            <div class="mt-auto">
                <button @click="editarTecnificacion(tecnificacion)" class="btn btn-outline-primary me-2">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button @click="confirmarEliminar(tecnificacion.slug)" class="btn btn-outline-danger">
                    <i class="bi bi-trash"></i> Eliminar
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CardTecnificaciones',
    props: {
        tecnificacion: Object
    },
    methods: {
        editarTecnificacion(tecnificacion) {
            this.$emit('editarTecnificacion', tecnificacion);
            this.$router.push({ name: 'adminEditarTecnificacion', params: { slug: tecnificacion.slug } });
        },
        confirmarEliminar(slug) {
            if (confirm("¿Estás seguro de que deseas eliminar esta tecnificación?")) {
                this.eliminarTecnificacion(slug);
            }
        },
        eliminarTecnificacion(slug) {
            this.$store.dispatch('adminDashboard/DeleteOneTecnificacionAdmin', slug)
                .then(() => {
                    console.log("Tecnificación eliminada correctamente");
                    this.$emit('tecnificacionEliminada');
                })
                .catch(error => console.error("Error al eliminar la tecnificación:", error));
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
