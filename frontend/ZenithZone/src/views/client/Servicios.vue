<template>
    <div class="main-servicios">
            <nav class="btn-group justify-content-center align-items-center shadow bg-light col-12 mb-4" role="group">
                <router-link 
                    to="/servicios/deportes" 
                    class="btn fw-bold p-3" 
                    active-class="active">
                    Deportes
                </router-link>
                <router-link 
                    to="/servicios/entrenamientos" 
                    class="btn fw-bold p-3" 
                    active-class="active">
                    Entrenamientos
                </router-link>
                <router-link 
                    to="/servicios/graficas" 
                    class="btn fw-bold p-3" 
                    active-class="active">
                    Gráficas
                </router-link>
            </nav>

            <router-view />
        </div>
</template>


<script>
import serviciosDeporte from '@/components/servicios/serviciosDeportes.vue';
import serviciosEntrenamientos from '@/components/servicios/serviciosEntrenamientos.vue';
import serviciosGraficas from '@/components/servicios/serviciosGraficas.vue';

export default {
    name: 'Servicios',
    components: { serviciosDeporte, serviciosEntrenamientos, serviciosGraficas },
};
</script>

<style>
nav .btn {
    border: none;
    transition: all 0.3s ease;
}

nav .btn:hover {
    background-color: rgb(20, 20, 20);
    color: white;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

nav .btn.active {
    background-color: #ff6600;
    color: #fff;
}
</style>
