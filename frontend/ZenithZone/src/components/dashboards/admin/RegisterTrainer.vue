<template>
    <div class="RegisterTrainer">
        <main class="main py-5">
            <div class="container">
                <!-- Form Wrapper -->
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <section class="wrapper">
                            <div class="text-center mb-4">
                                <h1 class="h3 mb-3 fw-normal">Register Entrenador</h1>
                            </div>

                            <!-- El formulario de registro de entrenador -->
                            <TrainerForm @submit="handleFormSubmit" />
                        </section>
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
import TrainerForm from '@/components/dashboards/admin/TrainerForm.vue';

export default {
    name: 'RegisterTrainer',
    components: {
        TrainerForm
    },
    methods: {
        handleFormSubmit(formData) {
           
            console.log('Form data received:', formData);
           
        }
    }
}
</script>

