<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0 bg-white">
            <div class="card-body p-4">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="display-6 mb-0">Crear Entrenamiento</h2>
                    <button class="btn btn-outline-secondary" @click="volver">
                        <i class="bi bi-arrow-left me-1"></i> Volver
                    </button>
                </div>

                <form @submit.prevent="submitForm" class="needs-validation">
                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                            <input v-model="entrenamiento.nombre" id="nombre" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.entrenamiento.nombre.$error }"
                                placeholder="Nombre del entrenamiento" @blur="v$.entrenamiento.nombre.$touch()" />
                            <div class="invalid-feedback" v-if="v$.entrenamiento.nombre.$error">
                                <span v-if="v$.entrenamiento.nombre.required.$invalid">El nombre es obligatorio.</span>
                                <span v-else-if="v$.entrenamiento.nombre.minLength.$invalid">El nombre debe tener al
                                    menos 3 caracteres.</span>
                                <span v-else-if="v$.entrenamiento.nombre.maxLength.$invalid">El nombre no puede exceder
                                    los 255 caracteres.</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="nivel" class="form-label">Nivel <span class="text-danger">*</span></label>
                            <select v-model="entrenamiento.nivel" id="nivel" class="form-select form-select-lg"
                                :class="{ 'is-invalid': v$.entrenamiento.nivel.$error }"
                                @blur="v$.entrenamiento.nivel.$touch()">
                                <option value="">Seleccione un nivel</option>
                                <option value="Principiante">Principiante</option>
                                <option value="Intermedio">Intermedio</option>
                                <option value="Avanzado">Avanzado</option>
                                <option value="Profesional">Profesional</option>
                            </select>
                            <div class="invalid-feedback" v-if="v$.entrenamiento.nivel.$error">
                                <span v-if="v$.entrenamiento.nivel.required.$invalid">El nivel es obligatorio.</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea v-model="entrenamiento.descripcion" id="descripcion"
                            class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.entrenamiento.descripcion.$error }"
                            placeholder="Descripción del entrenamiento" rows="3"
                            @blur="v$.entrenamiento.descripcion.$touch()"></textarea>
                        <div class="invalid-feedback" v-if="v$.entrenamiento.descripcion.$error">
                            <span v-if="v$.entrenamiento.descripcion.maxLength.$invalid">La descripción no puede exceder
                                los 1000 caracteres.</span>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="edadMinima" class="form-label">Edad Mínima</label>
                            <input v-model.number="entrenamiento.edadMinima" id="edadMinima" type="number" min="3"
                                max="100" class="form-control form-control-lg" placeholder="Edad mínima (mínimo 3 años)"
                                :class="{ 'is-invalid': v$.entrenamiento.edadMinima.$error }"
                                @blur="v$.entrenamiento.edadMinima.$touch()" @change="validarEdades" />
                            <div class="invalid-feedback" v-if="v$.entrenamiento.edadMinima.$error">
                                <span v-if="v$.entrenamiento.edadMinima.minValue.$invalid">La edad mínima debe ser al
                                    menos 3 años.</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="edadMaxima" class="form-label">Edad Máxima</label>
                            <input v-model.number="entrenamiento.edadMaxima" id="edadMaxima" type="number" min="3"
                                max="100" class="form-control form-control-lg" placeholder="Edad máxima"
                                @change="validarEdades" />
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="tecnificacionSlug" class="form-label">Tecnificación</label>
                            <select v-model="entrenamiento.tecnificacionSlug" id="tecnificacionSlug"
                                class="form-select form-select-lg" @change="onTecnificacionChange">
                                <option value="">Seleccione una tecnificación</option>
                                <option v-for="tecnificacion in tecnificaciones" :key="tecnificacion.id"
                                    :value="tecnificacion.slug">
                                    {{ tecnificacion.nombre }}
                                </option>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="subtipoTecnificacionSlug" class="form-label">Subtipo de Tecnificación</label>
                            <select v-model="entrenamiento.subtipoTecnificacionSlug" id="subtipoTecnificacionSlug"
                                class="form-select form-select-lg" :disabled="!entrenamiento.tecnificacionSlug">
                                <option value="">Seleccione un subtipo</option>
                                <option v-for="subtipo in subtiposTecnificacion" :key="subtipo.id"
                                    :value="subtipo.slug">
                                    {{ subtipo.nombre }}
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="pistaSlug" class="form-label">Pista <span class="text-danger">*</span></label>
                            <select v-model="entrenamiento.pistaSlug" id="pistaSlug" class="form-select form-select-lg"
                                :class="{ 'is-invalid': v$.entrenamiento.pistaSlug.$error }"
                                @blur="v$.entrenamiento.pistaSlug.$touch()">
                                <option value="">Seleccione una pista</option>
                                <option v-for="pista in pistas" :key="pista.id" :value="pista.slug">
                                    {{ pista.nombre }}
                                </option>
                            </select>
                            <div class="invalid-feedback" v-if="v$.entrenamiento.pistaSlug.$error">
                                <span v-if="v$.entrenamiento.pistaSlug.required.$invalid">La pista es
                                    obligatoria.</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="maxPlazas" class="form-label">Plazas Máximas <span
                                    class="text-danger">*</span></label>
                            <input v-model.number="entrenamiento.maxPlazas" id="maxPlazas" type="number" min="1"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.entrenamiento.maxPlazas.$error }"
                                placeholder="Número máximo de plazas" @blur="v$.entrenamiento.maxPlazas.$touch()" />
                            <div class="invalid-feedback" v-if="v$.entrenamiento.maxPlazas.$error">
                                <span v-if="v$.entrenamiento.maxPlazas.required.$invalid">El número máximo de plazas es
                                    obligatorio.</span>
                                <span v-else-if="v$.entrenamiento.maxPlazas.minValue.$invalid">El número máximo de
                                    plazas debe ser al menos 1.</span>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="fechaInicio" class="form-label">Fecha y Hora de Inicio <span
                                    class="text-danger">*</span></label>
                            <input v-model="entrenamiento.fechaInicio" id="fechaInicio" type="datetime-local"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.entrenamiento.fechaInicio.$error }"
                                @blur="v$.entrenamiento.fechaInicio.$touch()" @change="validarFechas" />
                            <div class="invalid-feedback" v-if="v$.entrenamiento.fechaInicio.$error">
                                <span v-if="v$.entrenamiento.fechaInicio.required.$invalid">La fecha y hora de inicio es
                                    obligatoria.</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="fechaFin" class="form-label">Fecha y Hora de Fin <span
                                    class="text-danger">*</span></label>
                            <input v-model="entrenamiento.fechaFin" id="fechaFin" type="datetime-local"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.entrenamiento.fechaFin.$error }"
                                @blur="v$.entrenamiento.fechaFin.$touch()" @change="validarFechas" />
                            <div class="invalid-feedback" v-if="v$.entrenamiento.fechaFin.$error">
                                <span v-if="v$.entrenamiento.fechaFin.required.$invalid">La fecha y hora de fin es
                                    obligatoria.</span>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="duracionMinutos" class="form-label">Duración (minutos)</label>
                            <input v-model.number="entrenamiento.duracionMinutos" id="duracionMinutos" type="number"
                                min="1" class="form-control form-control-lg" placeholder="Duración en minutos" />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="equipamientoNecesario" class="form-label">Equipamiento Necesario</label>
                            <input v-model="entrenamiento.equipamientoNecesario" id="equipamientoNecesario"
                                class="form-control form-control-lg" placeholder="Equipamiento necesario" />
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="objetivos" class="form-label">Objetivos</label>
                        <textarea v-model="entrenamiento.objetivos" id="objetivos" class="form-control form-control-lg"
                            placeholder="Objetivos del entrenamiento" rows="3"></textarea>
                    </div>

                    <div v-if="error" class="alert alert-danger mb-4">
                        {{ error }}
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i class="bi bi-plus-circle me-1"></i>
                            <span v-if="submitting">
                                <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                                Creando...
                            </span>
                            <span v-else>Crear Entrenamiento</span>
                        </button>
                        <button type="button" @click="volver" class="btn btn-outline-secondary btn-lg">
                            <i class="bi bi-x-circle me-1"></i> Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive, computed, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength, minValue } from '@vuelidate/validators';
import Constant from '@/Constant';
import Swal from 'sweetalert2';

export default {
    name: 'CreateEntrenamientoDashboard',
    setup() {
        const router = useRouter();
        const store = useStore();
        const submitting = ref(false);
        const loading = ref(false);
        const error = computed(() => store.getters['entrenadorDashboard/getError']);

        // Estado inicial del formulario
        const initialState = {
            nombre: '',
            descripcion: '',
            nivel: '',
            edadMinima: 3,
            edadMaxima: null,
            tecnificacionSlug: '',
            subtipoTecnificacionSlug: '',
            pistaSlug: '',
            fechaInicio: '',
            fechaFin: '',
            maxPlazas: 10,
            objetivos: '',
            equipamientoNecesario: '',
            duracionMinutos: 60
        };

        // Estado del formulario
        const entrenamiento = reactive({ ...initialState });

        // Reglas de validación
        const rules = {
            entrenamiento: {
                nombre: {
                    required,
                    minLength: minLength(3),
                    maxLength: maxLength(255)
                },
                nivel: {
                    required
                },
                descripcion: {
                    maxLength: maxLength(1000)
                },
                edadMinima: {
                    minValue: minValue(3)
                },
                pistaSlug: {
                    required
                },
                fechaInicio: {
                    required
                },
                fechaFin: {
                    required
                },
                maxPlazas: {
                    required,
                    minValue: minValue(1)
                }
            }
        };

        const v$ = useVuelidate(rules, { entrenamiento });

        //Obtener datos del store
        const pistas = computed(() => store.getters['entrenadorDashboard/getPistas']);
        const tecnificaciones = computed(() => store.getters['entrenadorDashboard/getTecnificaciones']);
        const subtiposTecnificacion = computed(() => store.getters['entrenadorDashboard/getSubtiposTecnificacion']);

        //Función para validar edades
        const validarEdades = () => {
            if (entrenamiento.edadMaxima && entrenamiento.edadMinima && entrenamiento.edadMaxima < entrenamiento.edadMinima) {
                entrenamiento.edadMaxima = entrenamiento.edadMinima;
                Swal.fire({
                    title: 'Aviso',
                    text: 'La edad máxima no puede ser menor que la edad mínima. Se ha ajustado automáticamente.',
                    icon: 'warning',
                    confirmButtonText: 'Entendido',
                    confirmButtonColor: '#0d6efd'
                });
            }
        };

        //Función para validar fechas
        const validarFechas = () => {
            if (entrenamiento.fechaInicio && entrenamiento.fechaFin) {
                const inicio = new Date(entrenamiento.fechaInicio);
                const fin = new Date(entrenamiento.fechaFin);

                if (fin <= inicio) {
                    //Ajustar la fecha de fin para que sea 1 hora después de la de inicio
                    const nuevaFechaFin = new Date(inicio);
                    nuevaFechaFin.setHours(nuevaFechaFin.getHours() + 1);

                    //Formatear la fecha para el input datetime-local
                    const year = nuevaFechaFin.getFullYear();
                    const month = String(nuevaFechaFin.getMonth() + 1).padStart(2, '0');
                    const day = String(nuevaFechaFin.getDate()).padStart(2, '0');
                    const hours = String(nuevaFechaFin.getHours()).padStart(2, '0');
                    const minutes = String(nuevaFechaFin.getMinutes()).padStart(2, '0');

                    entrenamiento.fechaFin = `${year}-${month}-${day}T${hours}:${minutes}`;

                    Swal.fire({
                        title: 'Aviso',
                        text: 'La fecha de fin debe ser posterior a la fecha de inicio. Se ha ajustado automáticamente.',
                        icon: 'warning',
                        confirmButtonText: 'Entendido',
                        confirmButtonColor: '#0d6efd'
                    });
                }

                //Calcular duración en minutos
                const diffMs = new Date(entrenamiento.fechaFin) - new Date(entrenamiento.fechaInicio);
                entrenamiento.duracionMinutos = Math.round(diffMs / 60000); // Convertir ms a minutos
            }
        };

        //Función para verificar si hay errores en las fechas o edades antes de enviar
        const verificarErroresPersonalizados = () => {
            let hayErrores = false;

            //Verificar edades
            if (entrenamiento.edadMaxima && entrenamiento.edadMinima && entrenamiento.edadMaxima < entrenamiento.edadMinima) {
                Swal.fire({
                    title: 'Error de validación',
                    text: 'La edad máxima debe ser mayor o igual que la edad mínima.',
                    icon: 'error',
                    confirmButtonText: 'Entendido',
                    confirmButtonColor: '#0d6efd'
                });
                hayErrores = true;
            }

            //Verificar fechas
            if (entrenamiento.fechaInicio && entrenamiento.fechaFin) {
                const inicio = new Date(entrenamiento.fechaInicio);
                const fin = new Date(entrenamiento.fechaFin);

                if (fin <= inicio) {
                    Swal.fire({
                        title: 'Error de validación',
                        text: 'La fecha de fin debe ser posterior a la fecha de inicio.',
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        confirmButtonColor: '#0d6efd'
                    });
                    hayErrores = true;
                }
            }

            return !hayErrores;
        };

        //Función para reiniciar el formulario
        const resetForm = () => {
            Object.keys(initialState).forEach(key => {
                entrenamiento[key] = initialState[key];
            });
            v$.value.$reset();
        };

        //Cargar datos iniciales
        onMounted(async () => {
            loading.value = true;
            try {
                //Cargar pistas
                await store.dispatch(`entrenadorDashboard/${Constant.FETCH_PISTAS}`);

                //Cargar tecnificaciones
                await store.dispatch(`entrenadorDashboard/${Constant.FETCH_TECNIFICACIONES}`);
            } catch (err) {
                console.error(err);
            } finally {
                loading.value = false;
            }
        });

        //Función para manejar el cambio de tecnificación
        const onTecnificacionChange = async () => {
            // Resetear el subtipo seleccionado
            entrenamiento.subtipoTecnificacionSlug = '';

            //Si se selecciona una tecnificación, cargar sus subtipos
            if (entrenamiento.tecnificacionSlug) {
                try {
                    loading.value = true;
                    await store.dispatch(`entrenadorDashboard/${Constant.FETCH_SUBTIPOS_TECNIFICACION}`, entrenamiento.tecnificacionSlug);
                } catch (err) {
                    console.error(err);
                } finally {
                    loading.value = false;
                }
            }
        };

        const submitForm = async () => {
            const isFormValid = await v$.value.$validate();
            if (!isFormValid) {
                return;
            }

            //Verificar errores personalizados
            if (!verificarErroresPersonalizados()) {
                return;
            }

            submitting.value = true;

            try {
                //Usar el store para crear el entrenamiento
                await store.dispatch(`entrenadorDashboard/${Constant.CREATE_ONE_ENTRENAMIENTO}`, entrenamiento);
                Swal.fire({
                    title: '¡Éxito!',
                    text: 'El entrenamiento ha sido creado correctamente',
                    icon: 'success',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#0d6efd'
                }).then(() => {
                    router.push('/dashboardEntrenador/listar');
                });
            } catch (error) {
                console.error('Error al crear el entrenamiento:', error);

                //Si es error 500, mostrar mensaje específico de solapamiento
                if (error.response && error.response.status === 500) {
                    Swal.fire({
                        title: 'Error',
                        text: 'El horario seleccionado se solapa con otro entrenamiento en esta pista',
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        confirmButtonColor: '#0d6efd'
                    });
                } else {
                    Swal.fire({
                        title: 'Error',
                        text: 'Ha ocurrido un error al crear el entrenamiento',
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        confirmButtonColor: '#0d6efd'
                    });
                }
            } finally {
                submitting.value = false;
            }
        };

        const volver = () => {
            router.push('/dashboardEntrenador/listar');
        };

        return {
            entrenamiento,
            v$,
            submitting,
            loading,
            error,
            pistas,
            tecnificaciones,
            subtiposTecnificacion,
            submitForm,
            volver,
            onTecnificacionChange,
            validarEdades,
            validarFechas,
            resetForm
        };
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