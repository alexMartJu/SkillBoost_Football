<template>
    <div class="monitor-dashboard container">
        <div class="row">
            <!-- Barra de navegación interna para cambiar entre Listar y Crear clases -->
            <div class="col-12 mb-4">
                <div class="btn-group" role="group">
                    <router-link to="/monitorDashboard" class="btn btn-primary">
                        Listar Clases
                    </router-link>
                    <router-link to="/monitorDashboard/crear-clase" class="btn btn-secondary">
                        Crear Clase
                    </router-link>
                </div>
            </div>

            <!-- Área donde se cargan las vistas de las clases -->
            <div class="col-12">
                <router-view></router-view> <!-- Aquí se carga ListarClases o CreateClase dependiendo de la ruta -->
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "MonitorDashboard"
};
</script>

<style scoped>
.monitor-dashboard {
    margin-top: 20px;
}

.btn-group .btn {
    margin-right: 10px;
}
</style>