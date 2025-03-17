<template>
    <div v-if="isEntrenamiento">
        <div class="container py-4" v-if="state.entrenamiento">
            <h1 class="display-4 mb-4 text-primary">
                <i class="bi bi-trophy-fill me-2"></i>{{ state.entrenamiento.nombre }}
            </h1>

            <div class="accordion" id="entrenamientoAccordion">
                <!-- Detalles Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="detallesHeader">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#detallesCollapse" aria-expanded="true" aria-controls="detallesCollapse">
                            <i class="bi bi-info-circle-fill me-2"></i>Detalles del Entrenamiento
                        </button>
                    </h2>
                    <div id="detallesCollapse" class="accordion-collapse collapse show"
                        aria-labelledby="detallesHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h3 class="card-subtitle mb-3">
                                        <i class="bi bi-dribbble me-2"></i>{{ state.entrenamiento.deporte.nombre }}
                                    </h3>
                                    <p class="card-text">
                                        <i class="bi bi-chat-quote-fill me-2"></i>{{ state.entrenamiento.descripcion }}
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-calendar-event me-2"></i>{{ state.entrenamiento.dia }}
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-clock me-2"></i>{{ state.entrenamiento.duracion }} minutos
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-people-fill me-2"></i>Plazas máximas: {{
                                        state.entrenamiento.maxPlazas }}
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-currency-euro me-2"></i>{{ state.entrenamiento.precio }}€
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-alarm me-2"></i>{{ state.entrenamiento.horario.hora }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Entrenador Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="entrenadorHeader">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#entrenadorCollapse" aria-expanded="false"
                            aria-controls="entrenadorCollapse">
                            <i class="bi bi-person-badge-fill me-2"></i>Información del Entrenador
                        </button>
                    </h2>
                    <div id="entrenadorCollapse" class="accordion-collapse collapse" aria-labelledby="entrenadorHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="card-text">
                                        <i class="bi bi-person-circle me-2"></i>{{ state.entrenamiento.entrenador.nombre
                                        }} {{ state.entrenamiento.entrenador.apellidos }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Ubicación Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ubicacionHeader">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ubicacionCollapse" aria-expanded="false" aria-controls="ubicacionCollapse">
                            <i class="bi bi-geo-alt-fill me-2"></i>Ubicación
                        </button>
                    </h2>
                    <div id="ubicacionCollapse" class="accordion-collapse collapse" aria-labelledby="ubicacionHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="card-text">
                                        <i class="bi bi-building me-2"></i>{{ state.entrenamiento.pistaPrivada.nombre }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-4">
                <UnirseEntrenamientoButton :slug="state.entrenamiento.slug" :disabled="isSubscribed" />
            </div>
        </div>
        <div v-else>
            <div class="text-center py-5">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Cargando...</span>
                </div>
                <p class="h5 mt-3">Cargando información de entrenamiento...</p>
            </div>
        </div>
    </div>
    <!-- SI ES DETAILS PISTA -->
    <div v-else-if="!isSubtipoTecnificacion">
        <div class="container py-4" v-if="state.pista">
            <h1 class="display-4 mb-4 text-primary">
                <i class="bi bi-building-fill me-2"></i>{{ state.pista.nombre }}
            </h1>

            <div class="accordion" id="pistaAccordion">
                <!-- Reserva Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="reservaHeader">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#reservaCollapse" aria-expanded="true" aria-controls="reservaCollapse">
                            <i class="bi bi-calendar-plus-fill me-2"></i>Realizar Reserva
                        </button>
                    </h2>
                    <div id="reservaCollapse" class="accordion-collapse collapse show" aria-labelledby="reservaHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <div class="mb-4">
                                        <label for="datepicker" class="form-label fw-bold">
                                            <i class="bi bi-calendar-date me-2"></i>Selecciona una fecha:
                                        </label>
                                        <input type="date" id="datepicker" v-model="selectedDate"
                                            class="form-control form-control-lg" :min="minDate" />
                                    </div>

                                    <div v-if="selectedDate" class="mb-4">
                                        <label for="horarios" class="form-label fw-bold">
                                            <i class="bi bi-clock-fill me-2"></i>Selecciona un horario:
                                        </label>
                                        <select name="horarios" id="horarios" class="form-select form-select-lg"
                                            v-model="selectedHorario">
                                            <option v-for="horario in horarios" :key="horario" :value="horario"
                                                :disabled="isHorarioReserved(horario)">
                                                {{ horario.charAt(0).toUpperCase() + horario.slice(1) }}
                                            </option>
                                        </select>
                                    </div>

                                    <button @click="logDate" :disabled="!currentUser.isUser"
                                        class="btn btn-primary btn-lg w-100">
                                        <i class="bi bi-calendar-check me-2"></i>
                                        {{ isReserved ? 'Horario reservado' : 'Reservar pista' }}
                                    </button>

                                    <div v-if="reservaSuccess"
                                        class="alert alert-success mt-3 d-flex align-items-center" role="alert">
                                        <i class="bi bi-check-circle-fill me-2"></i>
                                        Reserva realizada con éxito.
                                    </div>
                                    <div v-if="reservaError" class="alert alert-danger mt-3 d-flex align-items-center"
                                        role="alert">
                                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                                        Error al realizar la reserva. Inténtalo de nuevo.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Info Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="infoHeader">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#infoCollapse" aria-expanded="false" aria-controls="infoCollapse">
                            <i class="bi bi-info-circle-fill me-2"></i>Información de la Pista
                        </button>
                    </h2>
                    <div id="infoCollapse" class="accordion-collapse collapse" aria-labelledby="infoHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="lead">
                                        <i class="bi bi-card-text me-2"></i>{{ state.pista.info }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div v-else>
            <div class="text-center py-5">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Cargando...</span>
                </div>
                <p class="h5 mt-3">Cargando información de la pista...</p>
            </div>
        </div>
    </div>
    <div v-else>
        <div class="container py-4" v-if="state.subtipoTecnificacion">
            <h1 class="display-4 mb-4 text-primary">
                <i class="bi bi-trophy-fill me-2"></i>{{ state.subtipoTecnificacion.nombre }}
            </h1>

            <div class="accordion" id="subtipoAccordion">
                <!-- Detalles Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="detallesHeader">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#detallesCollapse" aria-expanded="true" aria-controls="detallesCollapse">
                            <i class="bi bi-info-circle-fill me-2"></i>Detalles del Subtipo de Tecnificación
                        </button>
                    </h2>
                    <div id="detallesCollapse" class="accordion-collapse collapse show"
                        aria-labelledby="detallesHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="card-text">
                                        <i class="bi bi-chat-quote-fill me-2"></i>{{
                                            state.subtipoTecnificacion.descripcion }}
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-lightning-fill me-2"></i><strong>Tipo de Entrenamiento:</strong>
                                        {{ state.subtipoTecnificacion.tipoEntrenamiento }}
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-calendar-check-fill me-2"></i><strong>Frecuencia
                                            Sugerida:</strong> {{ state.subtipoTecnificacion.frecuenciaSugerida }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Objetivos Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="objetivosHeader">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#objetivosCollapse" aria-expanded="false" aria-controls="objetivosCollapse">
                            <i class="bi bi-bullseye me-2"></i>Objetivos
                        </button>
                    </h2>
                    <div id="objetivosCollapse" class="accordion-collapse collapse" aria-labelledby="objetivosHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="card-text">
                                        {{ state.subtipoTecnificacion.objetivos }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Beneficios Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="beneficiosHeader">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#beneficiosCollapse" aria-expanded="false"
                            aria-controls="beneficiosCollapse">
                            <i class="bi bi-star-fill me-2"></i>Beneficios
                        </button>
                    </h2>
                    <div id="beneficiosCollapse" class="accordion-collapse collapse" aria-labelledby="beneficiosHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="card-text">
                                        {{ state.subtipoTecnificacion.beneficios }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Metodología Section -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="metodologiaHeader">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#metodologiaCollapse" aria-expanded="false"
                            aria-controls="metodologiaCollapse">
                            <i class="bi bi-clipboard-check-fill me-2"></i>Metodología
                        </button>
                    </h2>
                    <div id="metodologiaCollapse" class="accordion-collapse collapse"
                        aria-labelledby="metodologiaHeader">
                        <div class="accordion-body">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="card-text">
                                        <i class="bi bi-check-circle-fill me-2"></i><strong>Método de
                                            Evaluación:</strong> {{ state.subtipoTecnificacion.metodoEvaluacion }}
                                    </p>
                                    <p class="card-text">
                                        <i class="bi bi-laptop-fill me-2"></i><strong>Tecnología Utilizada:</strong> {{
                                            state.subtipoTecnificacion.tecnologiaUtilizada }}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div v-else>
            <div class="text-center py-5">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Cargando...</span>
                </div>
                <p class="h5 mt-3">Cargando información del subtipo de tecnificación...</p>
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, watchEffect } from 'vue';
import UnirseEntrenamientoButton from './buttons/UnirseEntrenamientoButton.vue';
import { computed } from 'vue';
import entrenamientosService from '@/services/client/entrenamientos.service';
import { useStore } from 'vuex';
import { ref } from 'vue';
import reservasService from '@/services/client/reservas.service';

export default {
    props: {
        isEntrenamiento: {
            type: Boolean,
            Required: true
        },
        isSubtipoTecnificacion: {
            type: Boolean,
            default: false
        },
        state: {
            type: Object,
            Required: true
        }
    },

    components: {
        UnirseEntrenamientoButton
    },

    setup(props) {
        const store = useStore();
        const currentUser = reactive({
            isUser: computed(() => store.getters['user/GetIsAuth']),
        });
        const horarios = ['mañana', 'mediodia', 'tarde', 'noche'];

        // APUNTARSE ENTRENAMIENTOS
        const suscribedEntrenamientos = reactive(new Set());

        const checkAlreadySuscribed = async () => {
            const { data } = await entrenamientosService.GetSuscribedEntrenamientos();
            suscribedEntrenamientos.clear();
            data.forEach(entrenamiento => suscribedEntrenamientos.add(entrenamiento.slug));
        };

        if (currentUser.isUser) {
            checkAlreadySuscribed();
        }

        const isSubscribed = computed(() => {
            return props.state.entrenamiento?.slug && suscribedEntrenamientos.has(props.state.entrenamiento.slug);
        });


        // RESERVA PISTAS
        const selectedDate = ref('');
        const selectedHorario = ref('');
        const reservaSuccess = ref(false);
        const reservaError = ref(false);

        const logDate = async () => {
            reservaSuccess.value = false;
            reservaError.value = false;
            if (selectedDate.value) {
                const data = {
                    slugPista: props.state.pista.slug,
                    hora: selectedHorario.value,
                    fecha: selectedDate.value
                };

                try {
                    await reservasService.CreateReserva(data);
                    reservaSuccess.value = true;
                } catch (error) {
                    reservaError.value = true;
                }
            } else {
                console.warn('No se ha seleccionado ninguna fecha');
            }
        };

        const suscribedReservas = reactive(new Set());

        const checkAlreadyReserved = async () => {
            const { data } = await reservasService.GetReservas();
            console.log(data);
            suscribedReservas.clear();
            data.forEach(reserva => suscribedReservas.add(`${reserva.slug}-${reserva.fecha}-${reserva.hora}`));
            console.log(suscribedReservas);
        };

        if (currentUser.isUser) {
            checkAlreadyReserved();
        }

        const isReserved = computed(() => {
            if (currentUser.isUser) return false;
            return props.state.pista?.slug && suscribedReservas.has(`${props.state.pista.slug}-${selectedDate.value}-${selectedHorario.value}`);
        });

        const isHorarioReserved = (horario) => {
            if (!props.state.pista?.slug || !selectedDate.value) return false;
            return suscribedReservas.has(`${props.state.pista.slug}-${selectedDate.value}-${horario}`);
        };

        watchEffect(() => { });

        const minDate = computed(() => {
            const today = new Date();
            const year = today.getFullYear();
            const month = String(today.getMonth() + 1).padStart(2, '0'); // Mes en formato MM
            const day = String(today.getDate()).padStart(2, '0'); // Día en formato DD
            return `${year}-${month}-${day}`;
        });

        return {
            horarios, suscribedEntrenamientos, isSubscribed, isReserved, isHorarioReserved, checkAlreadySuscribed,
            checkAlreadyReserved, logDate, selectedDate, selectedHorario, reservaSuccess, reservaError, currentUser,
            minDate
        };
    }
}
</script>

<style scoped>
button {
    margin: 0px;
}
</style>