<template>
    <div class="RegisterTrainer">
        <main class="main py-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <section class="wrapper">
                            <div class="text-center mb-4">
                                <h1 class="h3 mb-3 fw-normal">Register Entrenador</h1>
                            </div>
                            <div v-if="isLoading" class="text-center">
                                <p>Cargando deportes...</p>
                            </div>
                            <div v-if="registrationSuccess" class="alert alert-success text-center">
                                Entrenador registrado exitosamente.
                            </div>
                            <div v-if="registrationError" class="alert alert-danger text-center">
                                Ocurrió un error al registrar al entrenador.
                            </div>
                            <TrainerForm
                                v-else
                                :deportes="deportes"
                                @submit="handleFormSubmit"
                            />
                        </section>
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
