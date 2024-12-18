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
import { mapActions, mapGetters } from 'vuex';
import TrainerForm from '@/components/dashboards/admin/TrainerForm.vue';
import Constant from '../../../Constant';

export default {
    name: 'RegisterTrainer',
    components: {
        TrainerForm
    },
    data() {
        return {
            isLoading: true, 
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
        }),
        handleFormSubmit(formData) {
            console.log('Form data received:', formData);
           
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

