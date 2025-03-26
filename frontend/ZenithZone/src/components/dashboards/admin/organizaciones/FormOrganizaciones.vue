<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0 bg-white">
            <div class="card-body p-4">
                <h2 class="display-6 text-center mb-4">Crear Organización</h2>

                <form @submit.prevent="submitForm" class="needs-validation">
                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                            <input v-model="organizacion.nombre" id="nombre" class="form-control form-control-lg"
                                :class="{ 'is-invalid': v$.organizacion.nombre.$error }"
                                placeholder="Nombre de la organización" @blur="v$.organizacion.nombre.$touch()" />
                            <div class="invalid-feedback" v-if="v$.organizacion.nombre.$error">
                                <span v-if="v$.organizacion.nombre.required.$invalid">El nombre es obligatorio.</span>
                                <span v-else-if="v$.organizacion.nombre.minLength.$invalid">El nombre debe tener al
                                    menos 3
                                    caracteres.</span>
                                <span v-else-if="v$.organizacion.nombre.maxLength.$invalid">El nombre no puede exceder
                                    los 100
                                    caracteres.</span>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="tipo" class="form-label">Tipo <span class="text-danger">*</span></label>
                            <select v-model="organizacion.tipo" id="tipo" class="form-select form-select-lg"
                                :class="{ 'is-invalid': v$.organizacion.tipo.$error }"
                                @blur="v$.organizacion.tipo.$touch()">
                                <option value="">Seleccione un tipo</option>
                                <option value="Fundación">Fundación</option>
                                <option value="ONG">ONG</option>
                                <option value="Asociación">Asociación</option>
                                <option value="Escuela">Escuela</option>
                                <option value="Ayuntamiento">Ayuntamiento</option>
                            </select>
                            <div class="invalid-feedback" v-if="v$.organizacion.tipo.$error">
                                <span v-if="v$.organizacion.tipo.required.$invalid">El tipo es obligatorio.</span>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="logo" class="form-label">URL del Logo</label>
                            <input v-model="organizacion.logo" id="logo" type="url" class="form-control form-control-lg"
                                placeholder="https://ejemplo.com/logo.png" />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="sitioWeb" class="form-label">Sitio Web</label>
                            <input v-model="organizacion.sitioWeb" id="sitioWeb" type="url"
                                class="form-control form-control-lg" placeholder="https://ejemplo.com" />
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea v-model="organizacion.descripcion" id="descripcion"
                            class="form-control form-control-lg"
                            :class="{ 'is-invalid': v$.organizacion.descripcion.$error }"
                            placeholder="Descripción de la organización" rows="3"
                            @blur="v$.organizacion.descripcion.$touch()"></textarea>
                        <div class="invalid-feedback" v-if="v$.organizacion.descripcion.$error">
                            <span v-if="v$.organizacion.descripcion.maxLength.$invalid">La descripción no puede exceder
                                los 1000
                                caracteres.</span>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="mision" class="form-label">Misión</label>
                            <textarea v-model="organizacion.mision" id="mision" class="form-control form-control-lg"
                                placeholder="Misión de la organización" rows="3"></textarea>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="vision" class="form-label">Visión</label>
                            <textarea v-model="organizacion.vision" id="vision" class="form-control form-control-lg"
                                placeholder="Visión de la organización" rows="3"></textarea>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="impactoSocial" class="form-label">Impacto Social</label>
                        <textarea v-model="organizacion.impactoSocial" id="impactoSocial"
                            class="form-control form-control-lg" placeholder="Impacto social de la organización"
                            rows="3"></textarea>
                    </div>

                    <div class="mb-4">
                        <h5 class="mb-3">Información de Contacto</h5>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="contactoEmail" class="form-label">Email de Contacto</label>
                                <input v-model="organizacion.contactoEmail" id="contactoEmail" type="email"
                                    class="form-control form-control-lg"
                                    :class="{ 'is-invalid': v$.organizacion.contactoEmail.$error }"
                                    placeholder="contacto@ejemplo.com" @blur="v$.organizacion.contactoEmail.$touch()" />
                                <div class="invalid-feedback" v-if="v$.organizacion.contactoEmail.$error">
                                    <span v-if="v$.organizacion.contactoEmail.email.$invalid">El formato de email es
                                        inválido.</span>
                                </div>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="contactoTlf" class="form-label">Teléfono de Contacto</label>
                                <input v-model="organizacion.contactoTlf" id="contactoTlf"
                                    class="form-control form-control-lg" placeholder="+34 123 456 789" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="direccion" class="form-label">Dirección</label>
                                <input v-model="organizacion.direccion" id="direccion"
                                    class="form-control form-control-lg" placeholder="Calle Ejemplo, 123" />
                            </div>

                            <div class="col-md-4 mb-3">
                                <label for="ciudad" class="form-label">Ciudad</label>
                                <input v-model="organizacion.ciudad" id="ciudad" class="form-control form-control-lg"
                                    placeholder="Madrid" />
                            </div>

                            <div class="col-md-4 mb-3">
                                <label for="pais" class="form-label">País</label>
                                <input v-model="organizacion.pais" id="pais" class="form-control form-control-lg"
                                    placeholder="España" />
                            </div>
                        </div>
                    </div>

                    <div class="mb-4">
                        <h5 class="mb-3">Información de Colaboración</h5>
                        <div class="row">
                            <div class="col-md-8 mb-3">
                                <label for="colaboracionDescripcion" class="form-label">Descripción de la
                                    Colaboración</label>
                                <textarea v-model="organizacion.colaboracionDescripcion" id="colaboracionDescripcion"
                                    class="form-control form-control-lg"
                                    placeholder="Descripción de cómo colaboramos con esta organización" rows="3"
                                    :class="{ 'is-invalid': v$.organizacion.colaboracionDescripcion.$error }"
                                    @blur="v$.organizacion.colaboracionDescripcion.$touch()"></textarea>
                                <div class="invalid-feedback" v-if="v$.organizacion.colaboracionDescripcion.$error">
                                    <span v-if="v$.organizacion.colaboracionDescripcion.maxLength.$invalid">
                                        La descripción de colaboración no puede exceder los 1000 caracteres.
                                    </span>
                                </div>
                            </div>

                            <div class="col-md-4 mb-3">
                                <label for="fechaInicioColaboracion" class="form-label">Fecha de Inicio de
                                    Colaboración</label>
                                <input v-model="organizacion.fechaInicioColaboracion" id="fechaInicioColaboracion"
                                    type="date" class="form-control form-control-lg" />
                            </div>
                        </div>
                    </div>

                    <div class="d-grid gap-2 col-lg-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
                            <i class="bi bi-plus-circle me-1"></i>
                            <span v-if="submitting">
                                <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                                Creando...
                            </span>
                            <span v-else>Crear Organización</span>
                        </button>
                        <button type="button" @click="cancelar" class="btn btn-outline-secondary btn-lg">
                            <i class="bi bi-x-circle me-1"></i> Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength, email } from '@vuelidate/validators';
import Constant from '@/Constant';

export default {
    name: 'FormOrganizaciones',
    setup() {
        const router = useRouter();
        const store = useStore();
        const submitting = ref(false);

        const organizacion = reactive({
            nombre: '',
            tipo: '',
            vision: '',
            logo: '',
            descripcion: '',
            mision: '',
            impactoSocial: '',
            sitioWeb: '',
            contactoEmail: '',
            contactoTlf: '',
            direccion: '',
            ciudad: '',
            pais: '',
            colaboracionDescripcion: '',
            fechaInicioColaboracion: ''
        });

        const rules = {
            organizacion: {
                nombre: {
                    required,
                    minLength: minLength(3),
                    maxLength: maxLength(100)
                },
                tipo: {
                    required
                },
                descripcion: {
                    maxLength: maxLength(1000)
                },
                contactoEmail: {
                    email
                },
                colaboracionDescripcion: {
                    maxLength: maxLength(1000)
                }
            }
        };

        const v$ = useVuelidate(rules, { organizacion });

        const submitForm = async () => {
            const isFormValid = await v$.value.$validate();
            if (!isFormValid) {
                return;
            }

            submitting.value = true;

            try {
                await store.dispatch('adminDashboard/' + Constant.CREATE_ONE_ORGANIZACION_ADMIN, organizacion);
                router.push('/dashboardAdmin/organizaciones');
            } catch (error) {
                console.error('Error al crear la organización:', error);
            } finally {
                submitting.value = false;
            }
        };

        const cancelar = () => {
            router.push('/dashboardAdmin/organizaciones');
        };

        return {
            organizacion,
            v$,
            submitting,
            submitForm,
            cancelar
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