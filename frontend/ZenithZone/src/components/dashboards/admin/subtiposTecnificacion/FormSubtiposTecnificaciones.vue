<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0 bg-white">
            <div class="card-body p-4">
                <h2 class="display-6 text-center mb-4">{{ isEditing ? 'Editar Subtipo de Tecnificación' : 'Crear Subtipo de Tecnificación' }}</h2>

                <div v-if="loading" class="text-center py-3">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Cargando...</span>
                    </div>
                </div>

                <form v-else @submit.prevent="submitForm" class="needs-validation">
                    <!-- Selección de Tecnificación (solo para creación) -->
                    <div v-if="!isEditing" class="mb-3">
                        <label for="tecnificacion" class="form-label">Tecnificación <span
                                class="text-danger">*</span></label>
                        <select v-model="selectedTecnificacion" id="tecnificacion" class="form-select form-select-lg"
                            :class="{ 'is-invalid': v$.selectedTecnificacion.$error }"
                            @blur="v$.selectedTecnificacion.$touch()">
                            <option value="" disabled>Selecciona una tecnificación</option>
                            <option v-for="tecnificacion in tecnificaciones" :key="tecnificacion.id"
                                :value="tecnificacion.slug">
                                {{ tecnificacion.nombre }}
                            </option>
                        </select>
                        <div class="invalid-feedback" v-if="v$.selectedTecnificacion.$error">
                            <span v-if="v$.selectedTecnificacion.required.$invalid">Debes seleccionar una
                                tecnificación.</span>
                        </div>
                    </div>

                    <!-- Nombre -->
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                        <input v-model="subtipo.nombre" id="nombre" class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.nombre.$error }"
                            placeholder="Nombre del subtipo de tecnificación" @blur="v$.subtipo.nombre.$touch()" />
                        <div class="invalid-feedback" v-if="v$.subtipo.nombre.$error">
                            <span v-if="v$.subtipo.nombre.required.$invalid">El nombre es obligatorio.</span>
                            <span v-else-if="v$.subtipo.nombre.minLength.$invalid">El nombre debe tener al menos 3
                                caracteres.</span>
                            <span v-else-if="v$.subtipo.nombre.maxLength.$invalid">El nombre no puede exceder los 100
                                caracteres.</span>
                        </div>
                    </div>

                    <!-- Descripción -->
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea v-model="subtipo.descripcion" id="descripcion" class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.descripcion.$error }"
                            placeholder="Descripción del subtipo de tecnificación" rows="3"
                            @blur="v$.subtipo.descripcion.$touch()"></textarea>
                        <div class="invalid-feedback" v-if="v$.subtipo.descripcion.$error">
                            <span v-if="v$.subtipo.descripcion.maxLength.$invalid">La descripción no puede exceder los
                                1000 caracteres.</span>
                        </div>
                    </div>

                    <!-- Tipo de Entrenamiento -->
                    <div class="mb-3">
                        <label for="tipoEntrenamiento" class="form-label">Tipo de Entrenamiento</label>
                        <input v-model="subtipo.tipoEntrenamiento" id="tipoEntrenamiento"
                            class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.tipoEntrenamiento.$error }"
                            placeholder="Tipo de entrenamiento" @blur="v$.subtipo.tipoEntrenamiento.$touch()" />
                        <div class="invalid-feedback" v-if="v$.subtipo.tipoEntrenamiento.$error">
                            <span v-if="v$.subtipo.tipoEntrenamiento.maxLength.$invalid">El tipo de entrenamiento no
                                puede exceder los 100 caracteres.</span>
                        </div>
                    </div>

                    <!-- Objetivos -->
                    <div class="mb-3">
                        <label for="objetivos" class="form-label">Objetivos</label>
                        <textarea v-model="subtipo.objetivos" id="objetivos" class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.objetivos.$error }"
                            placeholder="Objetivos del subtipo de tecnificación" rows="3"
                            @blur="v$.subtipo.objetivos.$touch()"></textarea>
                        <div class="invalid-feedback" v-if="v$.subtipo.objetivos.$error">
                            <span v-if="v$.subtipo.objetivos.maxLength.$invalid">Los objetivos no pueden exceder los
                                1000 caracteres.</span>
                        </div>
                    </div>

                    <!-- Beneficios -->
                    <div class="mb-3">
                        <label for="beneficios" class="form-label">Beneficios</label>
                        <textarea v-model="subtipo.beneficios" id="beneficios" class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.beneficios.$error }"
                            placeholder="Beneficios del subtipo de tecnificación" rows="3"
                            @blur="v$.subtipo.beneficios.$touch()"></textarea>
                        <div class="invalid-feedback" v-if="v$.subtipo.beneficios.$error">
                            <span v-if="v$.subtipo.beneficios.maxLength.$invalid">Los beneficios no pueden exceder los
                                1000 caracteres.</span>
                        </div>
                    </div>

                    <!-- Frecuencia Sugerida -->
                    <div class="mb-3">
                        <label for="frecuenciaSugerida" class="form-label">Frecuencia Sugerida</label>
                        <input v-model="subtipo.frecuenciaSugerida" id="frecuenciaSugerida"
                            class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.frecuenciaSugerida.$error }"
                            placeholder="Frecuencia sugerida" @blur="v$.subtipo.frecuenciaSugerida.$touch()" />
                        <div class="invalid-feedback" v-if="v$.subtipo.frecuenciaSugerida.$error">
                            <span v-if="v$.subtipo.frecuenciaSugerida.maxLength.$invalid">La frecuencia sugerida no
                                puede exceder los 100 caracteres.</span>
                        </div>
                    </div>

                    <!-- Método de Evaluación -->
                    <div class="mb-3">
                        <label for="metodoEvaluacion" class="form-label">Método de Evaluación</label>
                        <input v-model="subtipo.metodoEvaluacion" id="metodoEvaluacion"
                            class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.metodoEvaluacion.$error }"
                            placeholder="Método de evaluación" @blur="v$.subtipo.metodoEvaluacion.$touch()" />
                        <div class="invalid-feedback" v-if="v$.subtipo.metodoEvaluacion.$error">
                            <span v-if="v$.subtipo.metodoEvaluacion.maxLength.$invalid">El método de evaluación no puede
                                exceder los 255 caracteres.</span>
                        </div>
                    </div>

                    <!-- Tecnología Utilizada -->
                    <div class="mb-3">
                        <label for="tecnologiaUtilizada" class="form-label">Tecnología Utilizada</label>
                        <input v-model="subtipo.tecnologiaUtilizada" id="tecnologiaUtilizada"
                            class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.subtipo.tecnologiaUtilizada.$error }"
                            placeholder="Tecnología utilizada" @blur="v$.subtipo.tecnologiaUtilizada.$touch()" />
                        <div class="invalid-feedback" v-if="v$.subtipo.tecnologiaUtilizada.$error">
                            <span v-if="v$.subtipo.tecnologiaUtilizada.maxLength.$invalid">La tecnología utilizada no
                                puede exceder los 255 caracteres.</span>
                        </div>
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto mt-4">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i :class="isEditing ? 'bi bi-pencil-square' : 'bi bi-plus-circle'"></i>
                            {{ isEditing ? 'Actualizar Subtipo' : 'Crear Subtipo' }}
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
import { required, minLength, maxLength } from '@vuelidate/validators';
import { mapGetters } from 'vuex';

export default {
    name: 'FormSubtiposTecnificaciones',
    props: {
        slug: {
            type: String,
            required: false
        }
    },
    data() {
        return {
            subtipo: {
                nombre: '',
                descripcion: '',
                tipoEntrenamiento: '',
                objetivos: '',
                beneficios: '',
                frecuenciaSugerida: '',
                metodoEvaluacion: '',
                tecnologiaUtilizada: ''
            },
            selectedTecnificacion: '',
            loading: false,
            submitting: false,
            v$: useVuelidate()
        };
    },
    validations() {
        const rules = {
            subtipo: {
                nombre: {
                    required,
                    minLength: minLength(3),
                    maxLength: maxLength(100)
                },
                descripcion: {
                    maxLength: maxLength(1000)
                },
                tipoEntrenamiento: {
                    maxLength: maxLength(100)
                },
                objetivos: {
                    maxLength: maxLength(1000)
                },
                beneficios: {
                    maxLength: maxLength(1000)
                },
                frecuenciaSugerida: {
                    maxLength: maxLength(100)
                },
                metodoEvaluacion: {
                    maxLength: maxLength(255)
                },
                tecnologiaUtilizada: {
                    maxLength: maxLength(255)
                }
            }
        };

        // Solo añadir validación para tecnificación si estamos creando un nuevo subtipo
        if (!this.isEditing) {
            rules.selectedTecnificacion = { required };
        }

        return rules;
    },
    computed: {
        ...mapGetters('adminDashboard', ['GetTecnificaciones']),
        isEditing() {
            return !!this.slug;
        },
        tecnificaciones() {
            return this.GetTecnificaciones || [];
        }
    },
    created() {
        // Cargar las tecnificaciones para el selector
        this.$store.dispatch('adminDashboard/InitializeTecnificacionAdmin');

        if (this.isEditing) {
            this.cargarSubtipo();
        }
    },
    methods: {
        cargarSubtipo() {
            this.loading = true;
            this.$store.dispatch('adminDashboard/FetchSubtipoTecnificacionBySlugAdmin', this.slug)
                .then(data => {
                    if (data) {
                        this.subtipo = {
                            nombre: data.nombre,
                            descripcion: data.descripcion,
                            tipoEntrenamiento: data.tipoEntrenamiento,
                            objetivos: data.objetivos,
                            beneficios: data.beneficios,
                            frecuenciaSugerida: data.frecuenciaSugerida,
                            metodoEvaluacion: data.metodoEvaluacion,
                            tecnologiaUtilizada: data.tecnologiaUtilizada
                        };
                    }
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Error al cargar el subtipo de tecnificación:", error);
                    this.loading = false;
                    this.$router.push('/dashboardAdmin/subtipos-tecnificaciones');
                });
        },
        async submitForm() {
            const isFormValid = await this.v$.$validate();
            if (!isFormValid) {
                return;
            }

            this.submitting = true;

            try {
                if (this.isEditing) {
                    // Actualizar subtipo existente
                    await this.$store.dispatch('adminDashboard/UpdateOneSubtipoTecnificacionAdmin', {
                        slug: this.slug,
                        data: this.subtipo
                    });
                } else {
                    // Crear nuevo subtipo
                    await this.$store.dispatch('adminDashboard/CreateOneSubtipoTecnificacionAdmin', {
                        tecnificacionSlug: this.selectedTecnificacion,
                        subtipo: this.subtipo
                    });
                }

                this.submitting = false;
                this.$router.push('/dashboardAdmin/subtipos-tecnificaciones');
            } catch (error) {
                console.error(`Error al ${this.isEditing ? 'actualizar' : 'crear'} el subtipo de tecnificación:`, error);
                this.submitting = false;

                // Manejar errores del servidor
                if (error.response && error.response.data) {
                    const serverErrors = error.response.data;
                    console.error("Errores del servidor:", serverErrors);
                }
            }
        },
        cancelar() {
            this.$router.push('/dashboardAdmin/subtipos-tecnificaciones');
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

.form-control:focus,
.form-select:focus {
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