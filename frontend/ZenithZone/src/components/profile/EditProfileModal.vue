<template>
    <div class="modal fade show d-block" tabindex="-1" aria-modal="true" role="dialog">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content shadow-lg border-0 rounded-3">
                <div class="modal-header border-bottom">
                    <h5 class="modal-title fw-bold">Editar Perfil</h5>
                    <button type="button" class="btn-close" @click="$emit('close')" aria-label="Cerrar"></button>
                </div>

                <div class="modal-body p-4">
                    <form @submit.prevent="submitForm">
                        <!-- Campos comunes para ambos tipos de usuario -->
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="nombre" class="form-label fw-medium">Nombre</label>
                                <input type="text" class="form-control" id="nombre" v-model="v$.nombre.$model"
                                    :class="{ 'is-invalid': v$.nombre.$error }">
                                <div class="invalid-feedback" v-if="v$.nombre.$error">
                                    {{ v$.nombre.$errors[0].$message }}
                                </div>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="apellidos" class="form-label fw-medium">Apellidos</label>
                                <input type="text" class="form-control" id="apellidos" v-model="v$.apellidos.$model"
                                    :class="{ 'is-invalid': v$.apellidos.$error }">
                                <div class="invalid-feedback" v-if="v$.apellidos.$error">
                                    {{ v$.apellidos.$errors[0].$message }}
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="bio" class="form-label fw-medium">Biografía</label>
                            <textarea class="form-control" id="bio" rows="3" v-model="v$.bio.$model"
                                :class="{ 'is-invalid': v$.bio.$error }"></textarea>
                            <div class="invalid-feedback" v-if="v$.bio.$error">
                                {{ v$.bio.$errors[0].$message }}
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="image" class="form-label fw-medium">URL de Imagen</label>
                            <input type="text" class="form-control" id="image" v-model="v$.image.$model"
                                :class="{ 'is-invalid': v$.image.$error }">
                            <div class="invalid-feedback" v-if="v$.image.$error">
                                {{ v$.image.$errors[0].$message }}
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label fw-medium">Email</label>
                            <input type="email" class="form-control" id="email" v-model="v$.email.$model"
                                :class="{ 'is-invalid': v$.email.$error }">
                            <div class="invalid-feedback" v-if="v$.email.$error">
                                {{ v$.email.$errors[0].$message }}
                            </div>
                            <div class="form-text text-warning small mt-1"
                                v-if="v$.email.$model !== profile.usuario?.email && v$.email.$model">
                                <i class="bi bi-exclamation-triangle-fill me-1"></i>
                                Si cambias tu email, tendrás que iniciar sesión nuevamente.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label fw-medium">Contraseña (dejar en blanco para mantener la
                                actual)</label>
                            <input type="password" class="form-control" id="password" v-model="v$.password.$model"
                                autocomplete="new-password" :class="{ 'is-invalid': v$.password.$error }">
                            <div class="invalid-feedback" v-if="v$.password.$error">
                                {{ v$.password.$errors[0].$message }}
                            </div>
                            <div class="form-text text-warning small mt-1" v-if="v$.password.$model">
                                <i class="bi bi-exclamation-triangle-fill me-1"></i>
                                Si cambias tu contraseña, tendrás que iniciar sesión nuevamente.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="edad" class="form-label fw-medium">Edad</label>
                            <input type="number" class="form-control" id="edad" v-model.number="v$.edad.$model"
                                :class="{ 'is-invalid': v$.edad.$error }">
                            <div class="invalid-feedback" v-if="v$.edad.$error">
                                {{ v$.edad.$errors[0].$message }}
                            </div>
                        </div>

                        <!-- Campos específicos para jugadores -->
                        <div v-if="!isCoach" class="mb-3">
                            <label for="posicionPreferida" class="form-label fw-medium">Posición Preferida</label>
                            <select class="form-select" id="posicionPreferida" v-model="v$.posicionPreferida.$model"
                                :class="{ 'is-invalid': v$.posicionPreferida.$error }">
                                <option value="">Selecciona una posición</option>
                                <option value="Portero">Portero</option>
                                <option value="Defensa">Defensa</option>
                                <option value="Centrocampista">Centrocampista</option>
                                <option value="Delantero">Delantero</option>
                            </select>
                            <div class="invalid-feedback" v-if="v$.posicionPreferida.$error">
                                {{ v$.posicionPreferida.$errors[0].$message }}
                            </div>
                        </div>

                        <!-- Campos específicos para entrenadores -->
                        <div v-if="isCoach">
                            <div class="mb-3">
                                <label for="especialidad" class="form-label fw-medium">Especialidad</label>
                                <input type="text" class="form-control" id="especialidad" v-model="v$.especialidad.$model"
                                    :class="{ 'is-invalid': v$.especialidad.$error }">
                                <div class="invalid-feedback" v-if="v$.especialidad.$error">
                                    {{ v$.especialidad.$errors[0].$message }}
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="certificaciones" class="form-label fw-medium">Certificaciones</label>
                                <textarea class="form-control" id="certificaciones" rows="2"
                                    v-model="v$.certificaciones.$model"
                                    :class="{ 'is-invalid': v$.certificaciones.$error }"></textarea>
                                <div class="invalid-feedback" v-if="v$.certificaciones.$error">
                                    {{ v$.certificaciones.$errors[0].$message }}
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer border-top-0 px-0 pb-0 pt-4">
                            <button type="button" class="btn btn-secondary" @click="$emit('close')">Cancelar</button>
                            <button type="submit" class="btn btn-primary" :disabled="isSubmitting">
                                <span v-if="isSubmitting" class="spinner-border spinner-border-sm me-2" role="status"
                                    aria-hidden="true"></span>
                                Guardar Cambios
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Backdrop del modal -->
    <div class="modal-backdrop fade show"></div>
</template>

<script>
import { reactive, computed, ref, onMounted } from 'vue';
import { useVuelidate } from '@vuelidate/core';
import { required, email, minLength, helpers } from '@vuelidate/validators';
import { useStore } from 'vuex';
import Constant from '@/Constant';

export default {
    name: 'EditProfileModal',
    props: {
        profile: {
            type: Object,
            required: true
        },
        isCoach: {
            type: Boolean,
            default: false
        }
    },
    emits: ['close', 'profile-updated'],
    setup(props, { emit }) {
        const isSubmitting = ref(false);
        const store = useStore();
        const userEmail = computed(() => store.getters['user/GetCurrentUser']?.email || '');

        // Inicializar el formulario con los datos del perfil
        const formData = reactive({
            nombre: props.profile.nombre || '',
            apellidos: props.profile.apellidos || '',
            bio: props.profile.bio || '',
            image: props.profile.image || '',
            email: userEmail.value,
            password: '',
            edad: props.profile.edad || null,
            // Campos específicos para jugadores
            posicionPreferida: props.profile.posicionPreferida || '',
            // Campos específicos para entrenadores
            especialidad: props.profile.especialidad || '',
            certificaciones: props.profile.certificaciones || ''
        });

        // Reglas de validación
        const rules = computed(() => {
            const baseRules = {
                nombre: { required: helpers.withMessage('El nombre es obligatorio', required) },
                apellidos: { required: helpers.withMessage('Los apellidos son obligatorios', required) },
                bio: {},
                image: {},
                email: {
                    required: helpers.withMessage('El email es obligatorio', required),
                    email: helpers.withMessage('El formato de email es inválido', email)
                },
                password: {
                    minLengthIfNotEmpty: helpers.withMessage(
                        'La contraseña debe tener al menos 6 caracteres',
                        (value) => {
                        // Solo validar si se ha ingresado algo
                        if (value && value.length > 0) {
                            return value.length >= 6;
                        }
                        return true;
                        }
                    )
                },
                edad: { 
                    positive: helpers.withMessage('La edad debe ser un número positivo', (value) => {
                        return !value || value > 0;
                    })
                }
            };

            // Añadir reglas específicas según el tipo de usuario
            if (props.isCoach) {
                return {
                    ...baseRules,
                    especialidad: {},
                    certificaciones: {}
                };
            } else {
                return {
                    ...baseRules,
                    posicionPreferida: {}
                };
            }
        });

        const v$ = useVuelidate(rules, formData);

        // Evitar que el scroll cuando se muestra el modal
        onMounted(() => {
            document.body.classList.add('modal-open');
            document.body.style.overflow = 'hidden';
            document.body.style.paddingRight = '17px'; // Compensar el scrollbar
        });

        // Restaurar el scroll cuando se desmonta el componente
        const beforeUnmount = () => {
            document.body.classList.remove('modal-open');
            document.body.style.overflow = '';
            document.body.style.paddingRight = '';
        };

        // Función para enviar el formulario
        const submitForm = async () => {
            const isFormValid = await v$.value.$validate();

            if (!isFormValid) {
                return;
            }

            isSubmitting.value = true;

            try {
                const formDataToSend = { ...formData };
        const passwordChanged = formDataToSend.password && formDataToSend.password.length > 0;
        const emailChanged = formDataToSend.email !== userEmail.value;

                // Si la contraseña está vacía, no la enviamos
                if (!formDataToSend.password) {
                    delete formDataToSend.password;
                }

                // Usar la acción de Vuex en lugar de llamar directamente al servicio
                const response = await store.dispatch(`user/${Constant.UPDATE_USER}`, formDataToSend);
                
                if (response) {
                    // Emitir evento con los datos actualizados
                    emit('profile-updated', {
                ...response,
                email: formDataToSend.email, // Asegurarnos de incluir el email
                passwordChanged: passwordChanged,
                emailChanged: emailChanged // Añadir flag explícito
            });
                }
            } catch (error) {
                console.error('Error al actualizar el perfil:', error);
                // Aquí podrías manejar los errores, por ejemplo mostrando un mensaje
            } finally {
                isSubmitting.value = false;
            }
        };

        return {
            formData,
            v$,
            isSubmitting,
            submitForm,
            beforeUnmount
        };
    },
    beforeUnmount() {
        this.beforeUnmount();
    }
};
</script>

<style scoped>
/* Estilos adicionales para mejorar la apariencia del modal de Bootstrap */
.modal-content {
    animation: fadeInDown 0.3s ease-out;
}

.btn-primary {
    background-color: #ff6600;
    border-color: #ff6600;
}

.btn-primary:hover,
.btn-primary:focus {
    background-color: #e65c00;
    border-color: #e65c00;
}

@keyframes fadeInDown {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

/* Aseguramos que el modal ocupe toda la pantalla en dispositivos móviles */
@media (max-width: 767.98px) {
    .modal-dialog {
        margin: 0.5rem;
        max-width: calc(100% - 1rem);
    }
}
</style>
