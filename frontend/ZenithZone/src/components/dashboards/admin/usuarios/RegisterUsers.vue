<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0 bg-white">
            <div class="card-body p-4">
                <h2 class="display-6 text-center mb-4">Registro de Usuarios</h2>

                <!-- Selector de tipo de usuario -->
                <div class="mb-4">
                    <label class="form-label">Tipo de Usuario a Registrar</label>
                    <div class="d-flex gap-3">
                        <button v-for="type in userTypes" :key="type.value" class="btn flex-grow-1"
                            :class="selectedType === type.value ? 'btn-primary' : 'btn-outline-secondary'"
                            @click="selectUserType(type.value)">
                            <i :class="type.icon"></i> {{ type.label }}
                        </button>
                    </div>
                </div>

                <div v-if="loading" class="text-center py-3">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Cargando...</span>
                    </div>
                </div>

                <!-- Formulario de Entrenador -->
                <form v-else-if="selectedType === 'coach'" @submit.prevent="submitCoachForm" class="needs-validation">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                            <input v-model="coach.email" id="email" type="email" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.coach.email.$error }" placeholder="Email del entrenador"
                                @blur="v$.coach.email.$touch()" />
                            <div class="invalid-feedback" v-if="v$.coach.email.$error">
                                <span v-if="v$.coach.email.required.$invalid">Email es obligatorio</span>
                                <span v-else-if="v$.coach.email.email.$invalid">Formato de email inválido</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label">Contraseña <span
                                    class="text-danger">*</span></label>
                            <input v-model="coach.password" id="password" type="password"
                                class="form-control form-control-lg" :class="{ 'is-invalid': v$.coach.password.$error }"
                                placeholder="Contraseña" @blur="v$.coach.password.$touch()" />
                            <div class="invalid-feedback" v-if="v$.coach.password.$error">
                                <span v-if="v$.coach.password.required.$invalid">Contraseña es obligatoria</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                            <input v-model="coach.nombre" id="nombre" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.coach.nombre.$error }" placeholder="Nombre del entrenador"
                                @blur="v$.coach.nombre.$touch()" />
                            <div class="invalid-feedback" v-if="v$.coach.nombre.$error">
                                <span v-if="v$.coach.nombre.required.$invalid">Nombre es obligatorio</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="apellidos" class="form-label">Apellidos <span
                                    class="text-danger">*</span></label>
                            <input v-model="coach.apellidos" id="apellidos" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.coach.apellidos.$error }"
                                placeholder="Apellidos del entrenador" @blur="v$.coach.apellidos.$touch()" />
                            <div class="invalid-feedback" v-if="v$.coach.apellidos.$error">
                                <span v-if="v$.coach.apellidos.required.$invalid">Apellidos son obligatorios</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="especialidad" class="form-label">Especialidad <span
                                    class="text-danger">*</span></label>
                            <input v-model="coach.especialidad" id="especialidad" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.coach.especialidad.$error }"
                                placeholder="Especialidad del entrenador" @blur="v$.coach.especialidad.$touch()" />
                            <div class="invalid-feedback" v-if="v$.coach.especialidad.$error">
                                <span v-if="v$.coach.especialidad.required.$invalid">Especialidad es obligatoria</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="experienciaAnios" class="form-label">Años de Experiencia <span
                                    class="text-danger">*</span></label>
                            <input v-model.number="coach.experienciaAnios" id="experienciaAnios" type="number" min="0"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.coach.experienciaAnios.$error }"
                                placeholder="Años de experiencia" @blur="v$.coach.experienciaAnios.$touch()" />
                            <div class="invalid-feedback" v-if="v$.coach.experienciaAnios.$error">
                                <span v-if="v$.coach.experienciaAnios.required.$invalid">Experiencia en años es
                                    obligatoria</span>
                                <span v-else-if="v$.coach.experienciaAnios.minValue.$invalid">Experiencia debe ser un
                                    número positivo</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="certificaciones" class="form-label">Certificaciones</label>
                        <textarea v-model="coach.certificaciones" id="certificaciones"
                            class="form-control form-control-lg" placeholder="Certificaciones del entrenador"
                            rows="3"></textarea>
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i class="bi bi-person-plus"></i> Registrar Entrenador
                        </button>
                        <button type="button" @click="resetForm" class="btn btn-outline-secondary btn-lg">
                            <i class="bi bi-x-circle"></i> Cancelar
                        </button>
                    </div>
                </form>

                <!-- Formulario de Jugador de Club -->
                <form v-else-if="selectedType === 'clubPlayer'" @submit.prevent="submitClubPlayerForm"
                    class="needs-validation">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                            <input v-model="clubPlayer.email" id="email" type="email"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.email.$error }" placeholder="Email del jugador"
                                @blur="v$.clubPlayer.email.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.email.$error">
                                <span v-if="v$.clubPlayer.email.required.$invalid">Email es obligatorio</span>
                                <span v-else-if="v$.clubPlayer.email.email.$invalid">Formato de email inválido</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label">Contraseña <span
                                    class="text-danger">*</span></label>
                            <input v-model="clubPlayer.password" id="password" type="password"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.password.$error }" placeholder="Contraseña"
                                @blur="v$.clubPlayer.password.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.password.$error">
                                <span v-if="v$.clubPlayer.password.required.$invalid">Contraseña es obligatoria</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                            <input v-model="clubPlayer.nombre" id="nombre" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.nombre.$error }" placeholder="Nombre del jugador"
                                @blur="v$.clubPlayer.nombre.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.nombre.$error">
                                <span v-if="v$.clubPlayer.nombre.required.$invalid">Nombre es obligatorio</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="apellidos" class="form-label">Apellidos <span
                                    class="text-danger">*</span></label>
                            <input v-model="clubPlayer.apellidos" id="apellidos" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.apellidos.$error }"
                                placeholder="Apellidos del jugador" @blur="v$.clubPlayer.apellidos.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.apellidos.$error">
                                <span v-if="v$.clubPlayer.apellidos.required.$invalid">Apellidos son obligatorios</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label for="edad" class="form-label">Edad <span class="text-danger">*</span></label>
                            <input v-model.number="clubPlayer.edad" id="edad" type="number" min="1"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.edad.$error }" placeholder="Edad del jugador"
                                @blur="v$.clubPlayer.edad.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.edad.$error">
                                <span v-if="v$.clubPlayer.edad.required.$invalid">Edad es obligatoria</span>
                                <span v-else-if="v$.clubPlayer.edad.minValue.$invalid">Edad debe ser mayor a 0</span>
                            </div>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label for="posicionPreferida" class="form-label">Posición Preferida <span
                                    class="text-danger">*</span></label>
                            <input v-model="clubPlayer.posicionPreferida" id="posicionPreferida"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.posicionPreferida.$error }"
                                placeholder="Posición preferida" @blur="v$.clubPlayer.posicionPreferida.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.posicionPreferida.$error">
                                <span v-if="v$.clubPlayer.posicionPreferida.required.$invalid">Posición preferida es
                                    obligatoria</span>
                            </div>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label for="clubOrigen" class="form-label">Club de Origen <span
                                    class="text-danger">*</span></label>
                            <input v-model="clubPlayer.clubOrigen" id="clubOrigen" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.clubPlayer.clubOrigen.$error }" placeholder="Club de origen"
                                @blur="v$.clubPlayer.clubOrigen.$touch()" />
                            <div class="invalid-feedback" v-if="v$.clubPlayer.clubOrigen.$error">
                                <span v-if="v$.clubPlayer.clubOrigen.required.$invalid">Club de origen es
                                    obligatorio</span>
                            </div>
                        </div>
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i class="bi bi-person-plus"></i> Registrar Jugador de Club
                        </button>
                        <button type="button" @click="resetForm" class="btn btn-outline-secondary btn-lg">
                            <i class="bi bi-x-circle"></i> Cancelar
                        </button>
                    </div>
                </form>

                <!-- Formulario de Jugador Social -->
                <form v-else-if="selectedType === 'socialPlayer'" @submit.prevent="submitSocialPlayerForm"
                    class="needs-validation">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                            <input v-model="socialPlayer.email" id="email" type="email"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.socialPlayer.email.$error }" placeholder="Email del jugador"
                                @blur="v$.socialPlayer.email.$touch()" />
                            <div class="invalid-feedback" v-if="v$.socialPlayer.email.$error">
                                <span v-if="v$.socialPlayer.email.required.$invalid">Email es obligatorio</span>
                                <span v-else-if="v$.socialPlayer.email.email.$invalid">Formato de email inválido</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label">Contraseña <span
                                    class="text-danger">*</span></label>
                            <input v-model="socialPlayer.password" id="password" type="password"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.socialPlayer.password.$error }" placeholder="Contraseña"
                                @blur="v$.socialPlayer.password.$touch()" />
                            <div class="invalid-feedback" v-if="v$.socialPlayer.password.$error">
                                <span v-if="v$.socialPlayer.password.required.$invalid">Contraseña es obligatoria</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                            <input v-model="socialPlayer.nombre" id="nombre" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.socialPlayer.nombre.$error }"
                                placeholder="Nombre del jugador" @blur="v$.socialPlayer.nombre.$touch()" />
                            <div class="invalid-feedback" v-if="v$.socialPlayer.nombre.$error">
                                <span v-if="v$.socialPlayer.nombre.required.$invalid">Nombre es obligatorio</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="apellidos" class="form-label">Apellidos <span
                                    class="text-danger">*</span></label>
                            <input v-model="socialPlayer.apellidos" id="apellidos" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.socialPlayer.apellidos.$error }"
                                placeholder="Apellidos del jugador" @blur="v$.socialPlayer.apellidos.$touch()" />
                            <div class="invalid-feedback" v-if="v$.socialPlayer.apellidos.$error">
                                <span v-if="v$.socialPlayer.apellidos.required.$invalid">Apellidos son
                                    obligatorios</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="edad" class="form-label">Edad <span class="text-danger">*</span></label>
                            <input v-model.number="socialPlayer.edad" id="edad" type="number" min="1"
                                class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.socialPlayer.edad.$error }" placeholder="Edad del jugador"
                                @blur="v$.socialPlayer.edad.$touch()" />
                            <div class="invalid-feedback" v-if="v$.socialPlayer.edad.$error">
                                <span v-if="v$.socialPlayer.edad.required.$invalid">Edad es obligatoria</span>
                                <span v-else-if="v$.socialPlayer.edad.minValue.$invalid">Edad debe ser mayor a 0</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="organizacionOrigen" class="form-label">Organización de Origen <span
                                    class="text-danger">*</span></label>
                            <select v-model="socialPlayer.organizacionOrigen" id="organizacionOrigen"
                                class="form-select form-select-lg"
                                :class="{ 'is-invalid': v$.socialPlayer.organizacionOrigen.$error }"
                                @blur="v$.socialPlayer.organizacionOrigen.$touch()">
                                <option value="" disabled selected>
                                    {{ organizaciones.length ? 'Selecciona una organización' : 'Cargando organizaciones...' }}
                                </option>
                                <option v-for="org in organizaciones" :key="org.id" :value="org.nombre">
                                    {{ org.nombre }}
                                </option>
                            </select>
                            <div class="invalid-feedback" v-if="v$.socialPlayer.organizacionOrigen.$error">
                                <span v-if="v$.socialPlayer.organizacionOrigen.required.$invalid">La organización de
                                    origen es obligatoria</span>
                            </div>
                        </div>
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i class="bi bi-person-plus"></i> Registrar Jugador Social
                        </button>
                        <button type="button" @click="resetForm" class="btn btn-outline-secondary btn-lg">
                            <i class="bi bi-x-circle"></i> Cancelar
                        </button>
                    </div>
                </form>

                <!-- Mensaje de selección inicial -->
                <div v-else class="text-center py-5">
                    <i class="bi bi-people-fill display-1 text-muted"></i>
                    <p class="lead mt-3">Selecciona el tipo de usuario que deseas registrar</p>
                </div>

                <!-- Alerta de éxito -->
                <div v-if="successMessage" class="alert alert-success mt-4" role="alert">
                    <i class="bi bi-check-circle-fill me-2"></i> {{ successMessage }}
                </div>

                <!-- Alerta de error -->
                <div v-if="errorMessage" class="alert alert-danger mt-4" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i> {{ errorMessage }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive, computed, onMounted } from 'vue';
import { useVuelidate } from '@vuelidate/core';
import { required, email, minValue } from '@vuelidate/validators';
import { useStore } from 'vuex';
import Constant from '@/Constant';

export default {
    name: 'RegisterUsers',

    setup() {
        const store = useStore();
        const selectedType = ref(null);
        const loading = ref(false);
        const submitting = ref(false);
        const successMessage = ref('');
        const errorMessage = ref('');

        const userTypes = [
            { value: 'coach', label: 'Entrenador', icon: 'bi bi-person-gear' },            
            { value: 'clubPlayer', label: 'Jugador de Club', icon: 'bi bi-trophy' },
            { value: 'socialPlayer', label: 'Jugador Social', icon: 'bi bi-heart' }
        ];

        const coach = reactive({
            email: '',
            password: '',
            nombre: '',
            apellidos: '',
            especialidad: '',
            experienciaAnios: null,
            certificaciones: ''
        });

        const clubPlayer = reactive({
            email: '',
            password: '',
            nombre: '',
            apellidos: '',
            edad: null,
            posicionPreferida: '',
            clubOrigen: ''
        });

        const socialPlayer = reactive({
            email: '',
            password: '',
            nombre: '',
            apellidos: '',
            edad: null,
            organizacionOrigen: ''
        });

        //Reglas de validación
        const rules = {
            coach: {
                email: { required, email },
                password: { required },
                nombre: { required },
                apellidos: { required },
                especialidad: { required },
                experienciaAnios: { required, minValue: minValue(0) }
            },
            clubPlayer: {
                email: { required, email },
                password: { required },
                nombre: { required },
                apellidos: { required },
                edad: { required, minValue: minValue(1) },
                posicionPreferida: { required },
                clubOrigen: { required }
            },
            socialPlayer: {
                email: { required, email },
                password: { required },
                nombre: { required },
                apellidos: { required },
                edad: { required, minValue: minValue(1) },
                organizacionOrigen: { required }
            }
        };

        const v$ = useVuelidate(rules, { coach, clubPlayer, socialPlayer });

        const organizaciones = computed(() => store.getters['adminDashboard/GetOrganizaciones'] || []);

        // Métodos
        const loadOrganizaciones = () => {
            store.dispatch(`adminDashboard/${Constant.INITIALIZE_ORGANIZACIONES_ADMIN}`);
        };

        const selectUserType = (type) => {
            selectedType.value = type;
            clearMessages();

            //Si seleccionamos jugador social, cargar organizaciones
            if (type === 'socialPlayer') {
                loadOrganizaciones();
            }
        };

        const resetForm = () => {
            selectedType.value = null;
            clearData();
            clearMessages();
        };

        const clearData = () => {
            //Reiniciar datos de entrenador
            Object.assign(coach, {
                email: '',
                password: '',
                nombre: '',
                apellidos: '',
                especialidad: '',
                experienciaAnios: null,
                certificaciones: ''
            });

            //Reiniciar datos de jugador de club
            Object.assign(clubPlayer, {
                email: '',
                password: '',
                nombre: '',
                apellidos: '',
                edad: null,
                posicionPreferida: '',
                clubOrigen: ''
            });

            //Reiniciar datos de jugador social
            Object.assign(socialPlayer, {
                email: '',
                password: '',
                nombre: '',
                apellidos: '',
                edad: null,
                organizacionOrigen: ''
            });

            //Reiniciar validaciones
            v$.value.$reset();
        };

        const clearMessages = () => {
            successMessage.value = '';
            errorMessage.value = '';
        };

        const submitCoachForm = async () => {
            clearMessages();

            const isFormValid = await v$.value.coach.$validate();
            if (!isFormValid) {
                return;
            }

            submitting.value = true;

            try {
                await store.dispatch(`adminDashboard/${Constant.REGISTER_COACH}`, { ...coach });
                successMessage.value = `Entrenador ${coach.nombre} ${coach.apellidos} registrado correctamente`;
                clearData();
                selectedType.value = null;
            } catch (error) {
                console.error('Error al registrar entrenador:', error);
                errorMessage.value = error.response?.data?.message || 'Error al registrar el entrenador';
            } finally {
                submitting.value = false;
            }
        };

        const submitClubPlayerForm = async () => {
            clearMessages();

            const isFormValid = await v$.value.clubPlayer.$validate();
            if (!isFormValid) {
                return;
            }

            submitting.value = true;

            try {
                await store.dispatch(`adminDashboard/${Constant.REGISTER_CLUB_PLAYER}`, { ...clubPlayer });
                successMessage.value = `Jugador de club ${clubPlayer.nombre} ${clubPlayer.apellidos} registrado correctamente`;
                clearData();
                selectedType.value = null;
            } catch (error) {
                console.error('Error al registrar jugador de club:', error);
                errorMessage.value = error.response?.data?.message || 'Error al registrar el jugador de club';
            } finally {
                submitting.value = false;
            }
        };

        const submitSocialPlayerForm = async () => {
            clearMessages();

            const isFormValid = await v$.value.socialPlayer.$validate();
            if (!isFormValid) {
                return;
            }

            submitting.value = true;

            try {
                await store.dispatch(`adminDashboard/${Constant.REGISTER_SOCIAL_HELP_PLAYER}`, { ...socialPlayer });
                successMessage.value = `Jugador social ${socialPlayer.nombre} ${socialPlayer.apellidos} registrado correctamente`;
                clearData();
                selectedType.value = null;
            } catch (error) {
                console.error('Error al registrar jugador social:', error);
                errorMessage.value = error.response?.data?.message || 'Error al registrar el jugador social';
            } finally {
                submitting.value = false;
            }
        };

        onMounted(() => {
            loadOrganizaciones();
        });

        return {
            selectedType,
            userTypes,
            coach,
            clubPlayer,
            socialPlayer,
            loading,
            submitting,
            successMessage,
            errorMessage,
            organizaciones,
            v$,
            selectUserType,
            resetForm,
            clearData,
            clearMessages,
            submitCoachForm,
            submitClubPlayerForm,
            submitSocialPlayerForm
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