<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0 bg-white">
            <div class="card-body p-4">
                <h2 class="display-6 text-center mb-4">{{ isEditing ? 'Editar Pista' : 'Crear Pista' }}</h2>

                <div v-if="loading" class="text-center py-3">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Cargando...</span>
                    </div>
                </div>

                <form v-else @submit.prevent="submitForm" class="needs-validation">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                        <input v-model="pista.nombre" id="nombre" class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.pista.nombre.$error }" placeholder="Nombre de la pista"
                            @blur="v$.pista.nombre.$touch()" />
                        <div class="invalid-feedback" v-if="v$.pista.nombre.$error">
                            <span v-if="v$.pista.nombre.required.$invalid">El nombre es obligatorio.</span>
                            <span v-else-if="v$.pista.nombre.minLength.$invalid">El nombre debe tener al menos 3
                                caracteres.</span>
                            <span v-else-if="v$.pista.nombre.maxLength.$invalid">El nombre no puede exceder los 100
                                caracteres.</span>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea v-model="pista.descripcion" id="descripcion" class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.pista.descripcion.$error }" placeholder="Descripción de la pista"
                            rows="4" @blur="v$.pista.descripcion.$touch()"></textarea>
                        <div class="invalid-feedback" v-if="v$.pista.descripcion.$error">
                            <span v-if="v$.pista.descripcion.maxLength.$invalid">La descripción no puede exceder los
                                1000
                                caracteres.</span>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="tipo" class="form-label">Tipo <span class="text-danger">*</span></label>
                            <input v-model="pista.tipo" id="tipo" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.pista.tipo.$error }" placeholder="Tipo de pista"
                                @blur="v$.pista.tipo.$touch()" />
                            <div class="invalid-feedback" v-if="v$.pista.tipo.$error">
                                <span v-if="v$.pista.tipo.required.$invalid">El tipo es obligatorio.</span>
                                <span v-else-if="v$.pista.tipo.maxLength.$invalid">El tipo no puede exceder los 50
                                    caracteres.</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="dimensiones" class="form-label">Dimensiones</label>
                            <input v-model="pista.dimensiones" id="dimensiones" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.pista.dimensiones.$error }"
                                placeholder="Dimensiones de la pista" @blur="v$.pista.dimensiones.$touch()" />
                            <div class="invalid-feedback" v-if="v$.pista.dimensiones.$error">
                                <span v-if="v$.pista.dimensiones.maxLength.$invalid">Las dimensiones no pueden exceder
                                    los 50
                                    caracteres.</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="capacidad" class="form-label">Capacidad <span class="text-danger">*</span></label>
                        <input v-model.number="pista.capacidad" type="number" id="capacidad"
                            class="form-control form-control-lg" :class="{ 'is-invalid': v$.pista.capacidad.$error }"
                            placeholder="Capacidad de la pista" @blur="v$.pista.capacidad.$touch()" />
                        <div class="invalid-feedback" v-if="v$.pista.capacidad.$error">
                            <span v-if="v$.pista.capacidad.required.$invalid">La capacidad es obligatoria.</span>
                            <span v-else-if="v$.pista.capacidad.minValue.$invalid">La capacidad debe ser mayor que
                                0.</span>
                        </div>
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i :class="isEditing ? 'bi bi-pencil-square' : 'bi bi-plus-circle'"></i>
                            {{ isEditing ? 'Actualizar Pista' : 'Crear Pista' }}
                        </button>
                        <button type="button" @click="cancelar" class="btn btn-outline-secondary btn-lg">
                            <i class="bi bi-x-circle"></i> Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength, minValue } from '@vuelidate/validators';

export default {
    name: 'FormPistas',
    props: {
        slug: {
            type: String,
            required: false
        }
    },
    data() {
        return {
            pista: {
                nombre: '',
                descripcion: '',
                tipo: '',
                dimensiones: '',
                capacidad: null
            },
            loading: false,
            submitting: false,
            v$: useVuelidate()
        };
    },
    validations() {
        return {
            pista: {
                nombre: {
                    required,
                    minLength: minLength(3),
                    maxLength: maxLength(100)
                },
                descripcion: {
                    maxLength: maxLength(1000)
                },
                tipo: {
                    required,
                    maxLength: maxLength(50)
                },
                dimensiones: {
                    maxLength: maxLength(50)
                },
                capacidad: {
                    required,
                    minValue: minValue(1)
                }
            }
        };
    },
    computed: {
        isEditing() {
            return !!this.slug;
        }
    },
    created() {
        if (this.isEditing) {
            this.cargarPista();
        }
    },
    methods: {
        cargarPista() {
            this.loading = true;
            this.$store.dispatch('adminDashboard/FetchPistaBySlugAdmin', this.slug)
                .then(data => {
                    if (data) {
                        this.pista = {
                            nombre: data.nombre,
                            descripcion: data.descripcion,
                            tipo: data.tipo,
                            dimensiones: data.dimensiones,
                            capacidad: data.capacidad
                        };
                    }
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Error al cargar la pista:", error);
                    this.loading = false;
                    this.$router.push('/dashboardAdmin/pistas');
                });
        },
        async submitForm() {
            const isFormValid = await this.v$.$validate();
            if (!isFormValid) {
                return;
            }

            this.submitting = true;

            const action = this.isEditing
                ? 'adminDashboard/UpdateOnePistaAdmin'
                : 'adminDashboard/CreateOnePistaAdmin';

            const payload = this.isEditing
                ? { slug: this.slug, data: this.pista }
                : this.pista;

            this.$store.dispatch(action, payload)
                .then(() => {
                    this.submitting = false;
                    this.$router.push('/dashboardAdmin/pistas');
                })
                .catch(error => {
                    console.error(`Error al ${this.isEditing ? 'actualizar' : 'crear'} la pista:`, error);
                    this.submitting = false;

                    //Manejar errores del servidor
                    if (error.response && error.response.data) {
                        const serverErrors = error.response.data;
                        console.error("Errores del servidor:", serverErrors);
                    }
                });
        },
        cancelar() {
            this.$router.push('/dashboardAdmin/pistas');
        }
    }
};
</script>

<style scoped>
.card {
    transition: all 0.3s ease;
}

.card:hover {
    transform: translateY(-2px);
    box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15) !important;
}

.form-control:focus {
    border-color: var(--bs-primary);
    box-shadow: 0 0 0 0.25rem rgba(var(--bs-primary-rgb), 0.25);
}

.btn-primary {
    transition: all 0.3s ease;
}

.btn-primary:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.invalid-feedback {
    display: block;
}
</style>