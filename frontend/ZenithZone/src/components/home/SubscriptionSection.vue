<template>
    <section class="py-5 bg-light position-relative overflow-hidden">

        <div class="container-xxl">
            <!-- Section Header -->
            <div class="row mb-5">
                <div class="col-lg-8 mx-auto text-center">
                    <span class="badge bg-primary-subtle text-primary mb-3 px-3 py-2 rounded-pill fw-semibold">NUESTRAS
                        SUSCRIPCIONES</span>
                    <h2 class="display-4 fw-bold mb-0 text-gradient">Planes Diseñados para tu Progreso</h2>
                </div>
            </div>

            <!-- Main Content -->
            <div class="row g-5 justify-content-center">
                <div class="col-lg-10">
                    <div class="p-4">
                        <p class="lead mb-5 border-start border-4 border-primary ps-3 text-center">
                            En <span class="fw-bold">SkillBoost Football</span>, hemos diseñado planes de suscripción
                            flexibles que se adaptan a tus objetivos, nivel y disponibilidad. Descubre las ventajas que
                            ofrecemos para potenciar tu desarrollo futbolístico.
                        </p>

                        <div class="row g-4">
                            <div class="col-md-4">
                                <div class="card border-0 shadow-sm h-100 subscription-card">
                                    <div class="card-body p-4">
                                        <div class="text-center mb-4">
                                            <div class="icon-circle bg-primary-subtle text-primary mx-auto mb-3">
                                                <i class="bi bi-trophy"></i>
                                            </div>
                                            <h3 class="h4 text-primary">Logros y Recompensas</h3>
                                        </div>
                                        <p class="card-text text-center">Sistema de logros basado en tus reservas de
                                            entrenamientos y progreso. Desbloquea recompensas exclusivas a medida que
                                            avanzas en tu formación y cumples objetivos específicos.</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="card border-0 shadow-sm h-100 subscription-card">
                                    <div class="card-body p-4">
                                        <div class="text-center mb-4">
                                            <div class="icon-circle bg-primary-subtle text-primary mx-auto mb-3">
                                                <i class="bi bi-clipboard-data"></i>
                                            </div>
                                            <h3 class="h4 text-primary">Evaluaciones de Rendimiento</h3>
                                        </div>
                                        <p class="card-text text-center">Evaluaciones personalizadas realizadas por
                                            nuestros entrenadores profesionales. Recibe feedback detallado sobre tus
                                            fortalezas y áreas de mejora tras cada ciclo de entrenamiento.</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="card border-0 shadow-sm h-100 subscription-card">
                                    <div class="card-body p-4">
                                        <div class="text-center mb-4">
                                            <div class="icon-circle bg-primary-subtle text-primary mx-auto mb-3">
                                                <i class="bi bi-graph-up"></i>
                                            </div>
                                            <h3 class="h4 text-primary">Gráficas de Evolución</h3>
                                        </div>
                                        <p class="card-text text-center">Visualiza tu progreso a través de gráficas
                                            detalladas evaluadas por tu entrenador. Analiza tu evolución en diferentes
                                            aspectos técnicos, tácticos y físicos a lo largo del tiempo.</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="text-center mt-5">
                            <p class="text-muted mb-4">Ofrecemos diferentes niveles de suscripción adaptados a tus
                                necesidades y objetivos</p>
                            <router-link to="/planes"
                                class="btn btn-primary btn-lg px-4 py-3 rounded-pill shadow-sm redirect-button">
                                <span class="d-flex align-items-center justify-content-center">
                                    <span>Descubre nuestros planes</span>
                                    <i class="bi bi-arrow-right ms-2"></i>
                                </span>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    name: 'SubscriptionSection'
}
</script>

<style scoped>
/* Mínimo CSS personalizado, aprovechando Bootstrap al máximo */
.text-gradient {
    background: linear-gradient(90deg, var(--bs-primary), var(--bs-dark));
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
}

.opacity-10 {
    opacity: 0.1;
}

.subscription-card {
    transition: transform 0.3s, box-shadow 0.3s;
    position: relative;
    overflow: hidden;
}

.subscription-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.icon-circle {
    width: 64px;
    height: 64px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.75rem;
}

.redirect-button {
    transition: transform 0.3s, box-shadow 0.3s;
}

.redirect-button:hover {
    transform: translateY(-3px);
    box-shadow: 0 0.5rem 1rem rgba(var(--bs-primary-rgb), 0.3) !important;
}

/* Animaciones con clases de Bootstrap */
.subscription-card:nth-child(1) {
    animation: fadeInUp 0.5s ease-out;
}

.subscription-card:nth-child(2) {
    animation: fadeInUp 0.7s ease-out;
}

.subscription-card:nth-child(3) {
    animation: fadeInUp 0.9s ease-out;
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
</style>