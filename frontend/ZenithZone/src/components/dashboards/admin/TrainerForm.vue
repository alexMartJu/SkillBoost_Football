<template>
    <form @submit.prevent="submitForm">
        <!-- Nombre -->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" id="nombre" class="form-control" v-model="state.nombre" required />
            <div v-if="x$.nombre.$invalid && x$.nombre.$dirty" class="text-danger small">
                Invalid nombre (must be at least 2 characters).
            </div>
        </div>

        <!-- Apellidos -->
        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos</label>
            <input type="text" id="apellidos" class="form-control" v-model="state.apellidos" required />
            <div v-if="x$.apellidos.$invalid && x$.apellidos.$dirty" class="text-danger small">
                Invalid apellidos (must be at least 2 characters).
            </div>
        </div>

        <!-- Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Email Address</label>
            <input type="email" id="email" class="form-control" v-model="state.email" required />
            <div v-if="x$.email.$invalid && x$.email.$dirty" class="text-danger small">
                Invalid email (must be a valid email).
            </div>
        </div>

        <!-- Password -->
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" class="form-control" v-model="state.password" required />
            <div v-if="x$.password.$invalid && x$.password.$dirty" class="text-danger small">
                Invalid password (must be at least 3 characters).
            </div>
        </div>

        <!-- Repetir Password -->
        <div class="mb-3">
            <label for="password2" class="form-label">Repeat Password</label>
            <input type="password" id="password2" class="form-control" v-model="state.password2" required />
            <div v-if="state.password !== state.password2" class="text-danger small">
                Passwords do not match.
            </div>
        </div>

        <!-- Deporte (Dropdown) -->
        <div class="mb-3">
            <label for="deporte" class="form-label">Deporte</label>
            <select id="deporte" class="form-control" v-model="state.deporte_id" required>
                <option v-for="deporte in deportes" :key="deporte.id" :value="deporte.id">
                    {{ deporte.nombre }}
                </option>
            </select>
            <div v-if="x$.deporte_id.$invalid && x$.deporte_id.$dirty" class="text-danger small">
                Please select a sport.
            </div>
        </div>

        <!-- Submit Button -->
        <div class="d-grid">
            <button type="submit" class="btn btn-primary" :disabled="isFormInvalid">Register</button>
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
            password2: '',
            deporte_id: null,
        });

        const rules = computed(() => ({
            nombre: { required, minLength: minLength(2) },
            apellidos: { required, minLength: minLength(2) },
            email: { required, email },
            password: { required, minLength: minLength(3) },
            password2: { required, minLength: minLength(3) },
            deporte_id: { required },
        }));

        const x$ = useVuelidate(rules, state);

        const isFormInvalid = computed(() => {
            return x$.$invalid || state.password !== state.password2;
        });

        const submitForm = () => {
            if (!x$.$invalid && state.password === state.password2) {
                emit('submit', {
                    nombre: state.nombre,
                    apellidos: state.apellidos,
                    email: state.email,
                    password: state.password,
                    deporte_id: state.deporte_id
                });
            }
        };

        return { state, x$, isFormInvalid };
    }
});
</script>

<style scoped>
.text-danger {
    font-size: 0.875rem;
}
</style>
