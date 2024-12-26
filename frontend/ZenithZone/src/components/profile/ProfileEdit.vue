<template>
    <div class="profile-edit-page container col-md-4 mt-5">
        <h2>Editar Perfil</h2>
        <form @submit.prevent="submitForm">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" class="form-control" v-model="state.email" />
                <div v-if="x$.email.$invalid && x$.email.$dirty" class="text-danger small">
                    Invalid email address.
                </div>
            </div>
            <div class="mb-3">
                <label for="bio" class="form-label">Bio</label>
                <textarea id="bio" class="form-control" v-model="state.bio"></textarea>
                <div v-if="x$.bio.$invalid && x$.bio.$dirty" class="text-danger small">
                    Bio is required.
                </div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" class="form-control" v-model="state.password" />
                <div v-if="x$.password.$invalid && x$.password.$dirty" class="text-danger small">
                    Password must be at least 3 characters.
                </div>
            </div>
            <div class="mb-3">
                <label for="password_confirmation" class="form-label">Confirm Password</label>
                <input type="password" id="password_confirmation" class="form-control" v-model="state.password_confirmation" />
                <div v-if="x$.password_confirmation.$invalid && x$.password_confirmation.$dirty" class="text-danger small">
                    Password confirmation must be at least 3 characters.
                </div>
                <div v-if="state.password !== state.password_confirmation" class="text-danger small">
                    Passwords do not match.
                </div>
            </div>
            <button type="submit" class="btn btn-primary" :disabled="!isDirty || state.password !== state.password_confirmation">Actualizar</button>
            <!-- Alertas -->
            <div class="mt-4">
                <div class="alert alert-success" v-if="alertMessage">
                    {{ alertMessage }}
                </div>
                <div class="alert alert-danger" v-if="errorMessage">
                    {{ errorMessage }}
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import useVuelidate from '@vuelidate/core';
import { minLength, email } from '@vuelidate/validators';
import profileService from '@/services/client/profile.service';
import { reactive, computed, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
import Constant from '@/Constant';
import entrenadorService from '@/services/client/entrenador.service';

export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const store = useStore();
        let alertMessage = ref('');
        let errorMessage = ref('');

        const state = reactive({
            email: '',
            bio: '',
            password: '',
            password_confirmation: '',
        });

        const rules = computed(() => ({
            email: { email },
            bio: {},
            password: { minLength: minLength(3) },
            password_confirmation: { minLength: minLength(3) },
        }));

        const getData = () => {
            const data = {};
            if (state.email) data.email = state.email;
            if (state.bio) data.bio = state.bio;
            if (state.password) data.password = state.password;
            if (state.password_confirmation) data.password_confirmation = state.password_confirmation;
            return data;
        }; // solo añadir info a los campos con datos

        const isDirty = computed(() => {
            const data = getData();
            return Object.keys(data).length > 0;
        }); // false si getData no tiene nada

        const x$ = useVuelidate(rules, state);

        const submitForm = async () => {
            alertMessage.value = null;
            errorMessage.value = null;

            try {
                if (route.params.numeroSocio) {
                    await profileService.UpdateProfile(getData());
                } else if (route.params.numeroEntrenador) {
                    await entrenadorService.UpdateEntrenador(getData());
                }

                if (getData().email || getData().password) {
                    const refreshToken = { refreshToken: localStorage.getItem('refreshToken') };
                    refreshToken ? store.dispatch(`user/${Constant.LOGOUT}`, refreshToken) : store.dispatch(`user/${Constant.LOGOUT}`);
                    router.push({ name: 'home' });
                }
                alertMessage.value = "Perfil actualizado correctamente"
            } catch (error) {
                errorMessage.value = 'Error updating profile: ' + error.message;
            }
        };

        return { state, x$, submitForm, isDirty, alertMessage, errorMessage };
    }
};
</script>

<style scoped>
.text-danger {
    font-size: 0.875rem;
}
</style>