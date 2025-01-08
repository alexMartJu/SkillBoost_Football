<template>
    <div class="entrenador-dashboard container">
        <div class="row">
            <!-- Barra de navegación interna para cambiar entre Listar y Crear entrenamientos -->
            <div class="col-12 mb-4">
                <div class="btn-group" role="group">
                    <router-link to="/dashboardEntrenador/listar" class="btn btn-primary">
                        Listar Entrenamientos
                    </router-link>
                    <router-link to="/dashboardEntrenador/crear-entrenamiento" class="btn btn-secondary">
                        Crear Entrenamiento
                    </router-link>
                </div>
            </div>

            <!-- Área donde se cargan las vistas de las entrenamientos -->
            <div class="col-12">
                <router-view></router-view> <!-- Aquí se carga ListarEntrenamientos o CreateEntrenamiento dependiendo de la ruta -->
            </div>
        </div>
    </div>
</template>

<script>
import { onMounted} from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
export default {
    components: {
        name: "entrenadorDashboard"
    },
    setup(){
        const store = useStore();
        onMounted(() => {
            console.log("Despachando la acción para cargar el entrenador...");
            store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_ENTRENADOR}`).then(() => {
                console.log("Entrenador cargado con éxito");
            }).catch(error => {
                console.error("Error al cargar el entrenador:", error);
            });
        });
    },
}

</script>

<style scoped>
.entrenador-dashboard {
    margin-top: 130px;
}

.btn-group .btn {
    margin-right: 10px;
}
</style>