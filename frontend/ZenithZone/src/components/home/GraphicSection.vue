<template>
    <section class="analytics-section py-5">
        <div class="container-xxl">
            <div class="row align-items-center g-5">
                <div class="col-lg-6">
                    <div class="card graph-card shadow">
                        <div class="card-body p-4">
                            <Graphics :sets="sets"/>
                            <div class="graph-overlay"></div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="p-4">
                        <h2 class="display-4 fw-bold mb-4 analytics-title">ANÁLISIS DE RENDIMIENTO</h2>
                        <div class="analytics-features">
                            <div class="d-flex align-items-center mb-3">
                                <i class="bi bi-graph-up fs-4 text-primary me-3"></i>
                                <span class="fs-5">Seguimiento en tiempo real</span>
                            </div>
                            <div class="d-flex align-items-center mb-3">
                                <i class="bi bi-bullseye fs-4 text-primary me-3"></i>
                                <span class="fs-5">Objetivos personalizados</span>
                            </div>
                            <div class="d-flex align-items-center mb-3">
                                <i class="bi bi-bar-chart-line fs-4 text-primary me-3"></i>
                                <span class="fs-5">Métricas avanzadas</span>
                            </div>
                        </div>
                        <p class="fs-3 fst-italic mt-4 analytics-quote">
                            "Convierte tus datos en victorias"
                        </p>
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

        return {sets};
    }
};
</script>

<style scoped>
.analytics-section {
    background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
    min-height: 80vh;
}

.graph-card {
    border-radius: 20px;
    overflow: hidden;
    transition: transform 0.3s ease;
}

.graph-card:hover {
    transform: translateY(-5px);
}

.graph-overlay {
    position: absolute;
    inset: 0;
    background: linear-gradient(45deg, rgba(255,255,255,0.1) 0%, rgba(255,255,255,0) 100%);
    pointer-events: none;
}

.analytics-title {
    background: linear-gradient(45deg, var(--bs-primary), #141414f2);
    -webkit-background-clip: text;
    background-clip: text;
    -webkit-text-fill-color: transparent;
}

.analytics-quote {
    color: #2c3e50;
    border-left: 4px solid var(--bs-primary);
    padding-left: 1rem;
}
</style>
