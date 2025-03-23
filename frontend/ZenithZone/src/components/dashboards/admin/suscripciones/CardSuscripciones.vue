<template>
    <div class="card shadow-sm border-0 h-100">
        <div class="card-header bg-primary text-white">
            <h5 class="card-title mb-0">{{ suscripcion.nombre }}</h5>
        </div>
        <div class="card-body d-flex flex-column">
            <div class="mb-3">
                <h4 class="text-primary">{{ formatPrice(suscripcion.precio) }}</h4>
            </div>
            <div class="mb-3">
                <p><strong>Entrenamientos:</strong> {{ suscripcion.entrenamientosTotales }}</p>
                <p><strong>Incluye gráficas:</strong> {{ suscripcion.incluyeGraficas ? 'Sí' : 'No' }}</p>
                <p><strong>Incluye evaluación:</strong> {{ suscripcion.incluyeEvaluacion ? 'Sí' : 'No' }}</p>
            </div>
            <div class="mb-3">
                <h6>Ventajas:</h6>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" v-if="suscripcion.ventaja1">{{ suscripcion.ventaja1 }}</li>
                    <li class="list-group-item" v-if="suscripcion.ventaja2">{{ suscripcion.ventaja2 }}</li>
                    <li class="list-group-item" v-if="suscripcion.ventaja3">{{ suscripcion.ventaja3 }}</li>
                    <li class="list-group-item" v-if="suscripcion.ventaja4">{{ suscripcion.ventaja4 }}</li>
                </ul>
            </div>
            <!-- Formulario de edición de precio (visible solo cuando editando) -->
            <div v-if="editandoPrecio" class="mt-3 mb-3 p-3 border rounded bg-light">
                <h6 class="mb-3">Actualizar precio</h6>
                <div class="mb-3">
                    <label for="nuevoPrecio" class="form-label">Nuevo precio (€)</label>
                    <input type="number" class="form-control" id="nuevoPrecio" v-model.number="nuevoPrecio" step="0.01"
                        min="0">
                </div>
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-secondary me-2" @click="cancelarEdicion">
                        Cancelar
                    </button>
                    <button type="button" class="btn btn-primary" @click="actualizarPrecio" :disabled="!esValido">
                        Guardar
                    </button>
                </div>
            </div>
            <div class="mt-auto">
                <button v-if="!editandoPrecio" @click="iniciarEdicion" class="btn btn-outline-primary w-100">
                    <i class="bi bi-pencil-square"></i> Actualizar Precio
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CardSuscripciones',
    props: {
        suscripcion: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            nuevoPrecio: 0,
            editandoPrecio: false
        };
    },
    computed: {
        esValido() {
            return this.nuevoPrecio > 0;
        }
    },
    methods: {
        formatPrice(price) {
            return new Intl.NumberFormat('es-ES', {
                style: 'currency',
                currency: 'EUR'
            }).format(price);
        },
        iniciarEdicion() {
            this.nuevoPrecio = parseFloat(this.suscripcion.precio);
            this.editandoPrecio = true;
        },
        cancelarEdicion() {
            this.editandoPrecio = false;
            this.nuevoPrecio = 0;
        },
        actualizarPrecio() {
            if (!this.esValido) return;

            this.$store.dispatch('adminDashboard/UpdateSuscripcionPrecioAdmin', {
                slug: this.suscripcion.slug,
                precio: this.nuevoPrecio
            })
                .then(() => {
                    this.editandoPrecio = false;
                    this.$emit('precioActualizado');

                    if (this.$toast) {
                        this.$toast.success('Precio actualizado correctamente');
                    } else {
                        alert('Precio actualizado correctamente');
                    }
                })
                .catch(error => {
                    console.error("Error al actualizar el precio:", error);

                    if (this.$toast) {
                        this.$toast.error('Error al actualizar el precio');
                    } else {
                        alert('Error al actualizar el precio');
                    }
                });
        }
    }
};
</script>

<style scoped>
.card {
    transition: transform 0.2s ease-in-out;
    border-radius: 10px;
    overflow: hidden;
}

.card:hover {
    transform: translateY(-5px);
}

.card-header {
    border-bottom: none;
}

.btn {
    text-transform: uppercase;
    font-size: 0.875rem;
    letter-spacing: 0.5px;
}

.list-group-item {
    border-left: none;
    border-right: none;
    padding: 0.5rem 0;
}

.list-group-item:first-child {
    border-top: none;
}
</style>