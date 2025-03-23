<template>
    <div class="card shadow-sm border-0 h-100">
        <img v-if="subtipo.images && subtipo.images.length > 0"
            :src="'/assets/subtipos/' + subtipo.images[0].image_url" alt="Imagen de Subtipo de Tecnificación"
            class="card-img-top img-fluid" />
        <div class="card-body d-flex flex-column">
            <h5 class="card-title mb-3">{{ subtipo.nombre }}</h5>
            <p class="card-text text-primary mb-2" v-if="subtipo.tecnificacionNombre">
                <i class="bi bi-bookmark-star"></i> {{ subtipo.tecnificacionNombre }}
            </p>
            <p class="card-text text-muted mb-3">{{ subtipo.descripcion }}</p>
            <div class="mt-auto">
                <button @click="editarSubtipo(subtipo)" class="btn btn-outline-primary me-2">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button @click="confirmarEliminar(subtipo.slug)" class="btn btn-outline-danger">
                    <i class="bi bi-trash"></i> Eliminar
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CardSubtiposTecnificaciones',
    props: {
        subtipo: Object
    },
    methods: {
        editarSubtipo(subtipo) {
            this.$emit('editarSubtipo', subtipo);
            this.$router.push({ name: 'adminEditarSubtipoTecnificacion', params: { slug: subtipo.slug } });
        },
        confirmarEliminar(slug) {
            if (confirm("¿Estás seguro de que deseas eliminar este subtipo de tecnificación?")) {
                this.eliminarSubtipo(slug);
            }
        },
        eliminarSubtipo(slug) {
            this.$store.dispatch('adminDashboard/DeleteOneSubtipoTecnificacionAdmin', slug)
                .then(() => {
                    console.log("Subtipo de tecnificación eliminado correctamente");
                    this.$emit('subtipoEliminado');
                })
                .catch(error => console.error("Error al eliminar el subtipo de tecnificación:", error));
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
