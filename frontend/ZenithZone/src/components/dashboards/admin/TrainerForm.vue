<template>
    <form @submit.prevent="submitForm" class="needs-validation">
        <div class="row g-4">
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="text" class="form-control" id="nombre" v-model="state.nombre" required>
                    <label for="nombre">Nombre</label>
                    <div v-if="x$.nombre.$invalid && x$.nombre.$dirty" class="invalid-feedback d-block">
                        El nombre debe tener al menos 2 caracteres
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-floating">
                    <input type="text" class="form-control" id="apellidos" v-model="state.apellidos" required>
                    <label for="apellidos">Apellidos</label>
                    <div v-if="x$.apellidos.$invalid && x$.apellidos.$dirty" class="invalid-feedback d-block">
                        El apellido debe tener al menos 2 caracteres
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div class="form-floating">
                    <input type="email" class="form-control" id="email" v-model="state.email" required>
                    <label for="email">Correo electrónico</label>
                    <div v-if="x$.email.$invalid && x$.email.$dirty" class="invalid-feedback d-block">
                        Por favor ingrese un correo electrónico válido 
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-floating">
                    <input type="password" class="form-control" id="password" v-model="state.password" required>
                    <label for="password">Contraseña</label>
                    <div v-if="x$.password.$invalid && x$.password.$dirty" class="invalid-feedback d-block">
                        La contraseña debe tener al menos 3 caracteres
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-floating">
                    <input type="password" class="form-control" id="password_confirmation" 
                           v-model="state.password_confirmation" required>
                    <label for="password_confirmation">Confirmar Contraseña</label>
                    <div v-if="state.password !== state.password_confirmation" class="invalid-feedback d-block">
                        Las contraseñas no coinciden 
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <label class="form-label">Edad</label>
                <div class="d-flex align-items-center gap-3">
                    <input type="range" class="form-range flex-grow-1" v-model="state.edad" 
                           min="18" max="100" step="1" required>
                    <span class="badge bg-primary">{{ state.edad }}</span>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-floating">
                    <select class="form-select" id="deporte" v-model="state.deporte_id" required>
                        <option value="" disabled selected>Selecciona un deporte</option>
                        <option v-for="deporte in deportes" :key="deporte.id" :value="deporte.id">
                            {{ deporte.nombre }}
                        </option>
                    </select>
                    <label for="deporte">Deporte</label>
                </div>
            </div>

            <div class="col-12 mt-4">
                <button type="submit" class="btn btn-primary w-100 py-3 rounded-3">
                    <i class="bi bi-person-plus me-2"></i>Registrar Entrenador
                </button>
            </div>
        </div>
    </form>
</template>

<script>
import { reactive, computed, defineComponent } from 'vue';
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, email } from '@vuelidate/validators';

export default defineComponent({
    name: 'TrainerForm',
    props: {    
        deportes: Array 
    },
    emits: ['submit'],
    setup(props, { emit }) {
        const state = reactive({
            nombre: '',
            apellidos: '',
            email: '',
            password: '',
            password_confirmation: '',
            deporte_id: null,
            edad:18,
        });

        const rules = computed(() => ({
            nombre: { required, minLength: minLength(2) },
            apellidos: { required, minLength: minLength(2) },
            email: { required, email },
            password: { required, minLength: minLength(3) },
            password_confirmation: { required, minLength: minLength(3) },
            deporte_id: { required },
            edad: { required },
        }));

        const x$ = useVuelidate(rules, state);

        const isFormInvalid = computed(() => {
            return x$.$invalid || state.password !== state.password_confirmation;
        });

        const submitForm = () => {
            if (!x$.$invalid && state.password === state.password_confirmation) {
                console.log('Form is valid, emitting data...'); 
                emit('submit', {
                    nombre: state.nombre,
                    apellidos: state.apellidos,
                    email: state.email,
                    password: state.password,
                    password_confirmation: state.password_confirmation,
                    edad: state.edad,
                    deporte_id: state.deporte_id
                });
            } else {
                console.log('Form is invalid or passwords do not match.');
            }
        };

        return { state, x$, isFormInvalid,submitForm };
    }
});
</script>

<style scoped>
.form-floating {
    margin-bottom: 0.5rem;
}

.form-control, .form-select {
    border-radius: 0.5rem;
    border: 1px solid #dee2e6;
}

.form-control:focus, .form-select:focus {
    border-color: #0d6efd;
    box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
}

.btn-primary {
    transition: all 0.3s ease;
}

.btn-primary:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(13, 110, 253, 0.15);
}
</style>
