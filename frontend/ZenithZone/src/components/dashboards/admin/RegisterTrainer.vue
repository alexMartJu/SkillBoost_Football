<template>
    <div class="register-trainer bg-light min-vh-100 d-flex align-items-center">
        <main class="w-100 py-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-lg-6">
                        <div class="card border-0 shadow-sm rounded-4">
                            <div class="card-body p-5">
                                <div class="text-center mb-5">
                                    <h1 class="display-6 fw-bold text-primary mb-2">Registrar Entrenador</h1>
                                    <div class="border-bottom border-2 border-primary mx-auto" style="width: 50px;"></div>
                                </div>
                                
                                <div v-if="isLoading" class="text-center">
                                    <div class="spinner-border text-primary" role="status">
                                        <span class="visually-hidden">Cargando...</span>
                                    </div>
                                </div>

                                <div v-if="registrationSuccess" 
                                     class="alert alert-success border-0 rounded-3 shadow-sm text-center fade show">
                                    <i class="bi bi-check-circle me-2"></i>
                                    Entrenador registrado con éxito.
                                </div>

                                <div v-if="registrationError" 
                                     class="alert alert-danger border-0 rounded-3 shadow-sm text-center fade show">
                                    <i class="bi bi-exclamation-circle me-2"></i>
                                    Error al registrar entrenador.
                                </div>

                                <TrainerForm
                                    v-else
                                    :deportes="deportes"
                                    @submit="handleFormSubmit"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>


<script>
import Constant from '@/Constant';
import TrainerForm from '@/components/dashboards/admin/TrainerForm.vue';
import { mapGetters, mapActions } from 'vuex';
export default {
    name: 'RegisterTrainer',
    components: {
        TrainerForm,
    },
    data() {
        return {
            isLoading: true,
            registrationSuccess: false,
            registrationError: false,
        };
    },
    computed: {
        ...mapGetters('adminDashboard', {
            deportes: 'GetDeportes',
        }),
    },
    methods: {
        ...mapActions('adminDashboard', {
            fetchDeportes: Constant.INITIALIZE_DEPORTE,
            registerEntrenador: Constant.REGISTER_ENTRENADOR,
        }),
        handleFormSubmit(formData) {
            this.registerEntrenador(formData)
                .then(() => {
                    this.registrationSuccess = true;
                    this.registrationError = false;
                    setTimeout(() => {
                        this.registrationSuccess = false;
                    }, 3000); 
                })
                .catch((error) => {
                    this.registrationError = true;
                    this.registrationSuccess = false;
                });
        },
    },
    async created() {
        try {
            await this.fetchDeportes();
            this.isLoading = false;
        } catch (error) {
            console.error('Error al cargar los deportes:', error);
        }
    },
};
</script>
