<template>
    <div class="card shadow-sm border-0 h-100">
        <img v-if="pista.images && pista.images.length > 0" :src="'/assets/pistas/' + pista.images[0].image_url"
            alt="Imagen de Pista" class="card-img-top img-fluid" />
        <div class="card-body d-flex flex-column">
            <h5 class="card-title mb-3">{{ pista.nombre }}</h5>
            <p class="card-text text-muted mb-2">{{ pista.descripcion }}</p>
            <div class="mt-2 mb-3">
                <span class="badge bg-primary me-2">{{ pista.tipo }}</span>
                <span class="badge bg-secondary me-2">{{ pista.dimensiones }}</span>
                <span class="badge bg-info">Capacidad: {{ pista.capacidad }}</span>
            </div>
            <div class="mt-auto">
                <button @click="editarPista(pista)" class="btn btn-outline-primary me-2">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button @click="confirmarEliminar(pista.slug)" class="btn btn-outline-danger">
                    <i class="bi bi-trash"></i> Eliminar
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CardPistas',
    props: {
        pista: Object
    },
    methods: {
        editarPista(pista) {
            this.$emit('editarPista', pista);
            this.$router.push({ name: 'adminEditarPista', params: { slug: pista.slug } });
        },
        confirmarEliminar(slug) {
            if (confirm("¿Estás seguro de que deseas eliminar esta pista?")) {
                this.eliminarPista(slug);
            }
        },
        eliminarPista(slug) {
            this.$store.dispatch('adminDashboard/DeleteOnePistaAdmin', slug)
                .then(() => {
                    console.log("Pista eliminada correctamente");
                    this.$emit('pistaEliminada');
                })
                .catch(error => console.error("Error al eliminar la pista:", error));
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
