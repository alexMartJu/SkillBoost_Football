<template>
    <section class="py-5 bg-white position-relative overflow-hidden">
        <div class="container-xxl">
            <!-- Section Header -->
            <div class="row mb-5">
                <div class="col-lg-8 mx-auto text-center">
                    <span class="badge bg-primary-subtle text-primary mb-3 px-3 py-2 rounded-pill fw-semibold">ANÁLISIS DE RENDIMIENTO</span>
                    <h2 class="display-4 fw-bold mb-0 text-gradient">Datos que Impulsan tu Progreso</h2>
                </div>
            </div>
            <!-- Main Content -->
            <div class="row g-5 justify-content-center">
                <div class="col-lg-10">
                    <div class="row g-4 align-items-center">
                        <!-- Lado Izquierdo: Grafica -->
                        <div class="col-lg-6">
                            <div class="card border-0 shadow-sm graphic-card">
                                <div class="card-body p-4">
                                    <h3 class="h5 text-primary mb-3 text-center">Evolución de Habilidades</h3>
                                    <div class="graphic-container">
                                        <Graphics :sets="sets" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Lado Derecho: Features -->
                        <div class="col-lg-6">
                            <div class="row g-4">
                                <div class="col-12">
                                    <div class="card border-0 shadow-sm analytics-card">
                                        <div class="card-body p-4">
                                            <div class="d-flex align-items-center mb-2">
                                                <div class="icon-circle bg-primary-subtle text-primary me-3">
                                                    <i class="bi bi-graph-up"></i>
                                                </div>
                                                <h3 class="h4 text-primary mb-0">Seguimiento en Tiempo Real</h3>
                                            </div>
                                            <p class="card-text">Monitoriza tu progreso con actualizaciones constantes
                                                tras cada sesión de entrenamiento. Nuestro sistema registra
                                                automáticamente tus avances en diferentes áreas técnicas y físicas.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="card border-0 shadow-sm analytics-card">
                                        <div class="card-body p-4">
                                            <div class="d-flex align-items-center mb-2">
                                                <div class="icon-circle bg-primary-subtle text-primary me-3">
                                                    <i class="bi bi-bullseye"></i>
                                                </div>
                                                <h3 class="h4 text-primary mb-0">Objetivos Personalizados</h3>
                                            </div>
                                            <p class="card-text">Establece metas específicas para cada aspecto de tu
                                                juego. Nuestros entrenadores te ayudarán a definir objetivos realistas y
                                                medibles adaptados a tu nivel y aspiraciones.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="card border-0 shadow-sm analytics-card">
                                        <div class="card-body p-4">
                                            <div class="d-flex align-items-center mb-2">
                                                <div class="icon-circle bg-primary-subtle text-primary me-3">
                                                    <i class="bi bi-bar-chart-line"></i>
                                                </div>
                                                <h3 class="h4 text-primary mb-0">Métricas Avanzadas</h3>
                                            </div>
                                            <p class="card-text">Accede a análisis detallados que van más allá de las
                                                estadísticas básicas. Evaluamos aspectos como precisión técnica, toma de
                                                decisiones, rendimiento bajo presión y consistencia.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center mt-5">
                        <p class="fs-5 fst-italic text-muted">"Convierte tus datos en victorias"</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import Graphics from '@/components/home/Graphics.vue';

export default {
    components: {
        Graphics
    },
    setup() {
        const sets = [
            [90, 70, 90, 43, 56],
            [40, 40, 50, 73, 96]
        ];

        return { sets };
    }
};
</script>

<style scoped>
.text-gradient {
    background: linear-gradient(90deg, var(--bs-primary), var(--bs-dark));
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
}

.graphic-card,
.analytics-card {
    transition: transform 0.3s, box-shadow 0.3s;
    overflow: hidden;
}

.graphic-card:hover,
.analytics-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.graphic-container {
    min-height: 350px;
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
}

.icon-circle {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.25rem;
}

.analytics-card:nth-child(1) {
    animation: fadeInUp 0.5s ease-out;
}

.analytics-card:nth-child(2) {
    animation: fadeInUp 0.7s ease-out;
}

.analytics-card:nth-child(3) {
    animation: fadeInUp 0.9s ease-out;
}

.graphic-card {
    animation: fadeIn 1s ease-out;
}

@keyframes fadeInUp {
    from {
        opacity: 0;
        transform: translateY(20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
</style>