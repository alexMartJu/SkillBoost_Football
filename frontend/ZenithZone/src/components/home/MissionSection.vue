<template>
    <section class="py-5 bg-light position-relative overflow-hidden">
        <div class="container-xxl">
            <!-- Section Header -->
            <div class="row mb-5">
                <div class="col-lg-8 mx-auto text-center">
                    <span class="badge bg-primary-subtle text-primary mb-3 px-3 py-2 rounded-pill fw-semibold">NUESTRA
                        MISIÓN</span>
                    <h2 class="display-4 fw-bold mb-0 text-gradient">Formando Futbolistas, Transformando Vidas</h2>
                </div>
            </div>

            <!-- Main Content -->
            <div class="row g-5 align-items-center">
                <!-- Lado Izquierdo: Mission Text -->
                <div class="col-lg-6">
                    <div class="p-4">
                        <p class="lead mb-4 border-start border-4 border-primary ps-3">
                            En <span class="fw-bold">SkillBoost Football</span>, creemos que el fútbol es más que un
                            deporte; es una
                            herramienta de transformación social.
                        </p>
                        <div class="card border-0 shadow-sm mb-4 mission-card">
                            <div class="card-body p-4">
                                <h3 class="h4 text-primary mb-3">Excelencia Técnica</h3>
                                <p class="card-text">Desarrollamos programas de tecnificación avanzada para jugadores de
                                    todos los
                                    niveles, desde niños que dan sus primeros pasos hasta jugadores de clubes que buscan
                                    perfeccionar sus
                                    habilidades.</p>
                            </div>
                        </div>
                        <div class="card border-0 shadow-sm mb-4 mission-card">
                            <div class="card-body p-4">
                                <h3 class="h4 text-primary mb-3">Inclusión Social</h3>
                                <p class="card-text">Trabajamos activamente con niños y niñas en situación de exclusión
                                    social,
                                    ofreciéndoles no solo formación deportiva de élite, sino también un espacio de
                                    crecimiento personal y
                                    oportunidades de futuro.</p>
                            </div>
                        </div>
                        <div class="card border-0 shadow-sm mission-card">
                            <div class="card-body p-4">
                                <h3 class="h4 text-primary mb-3">Desarrollo Integral</h3>
                                <p class="card-text">Formamos a los jugadores del mañana con un enfoque que abarca
                                    técnica,
                                    táctica, preparación física y, lo más importante, valores humanos que trascienden el
                                    terreno de juego.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Lado Derecho: Gallery -->
                <div class="col-lg-6">
                    <div class="gallery-container">
                        <div class="row g-3">
                            <div class="col-6">
                                <div class="card border-0 overflow-hidden h-100 gallery-item"
                                    @mouseenter="activeImage = 0" :class="{ 'active': activeImage === 0 }">
                                    <div class="ratio ratio-1x1">
                                        <img src="/assets/pistas/arco.jpg" alt="Sesión de entrenamiento técnico"
                                            class="card-img object-fit-cover">
                                    </div>
                                    <div class="card-img-overlay d-flex align-items-end gallery-overlay">
                                        <h5 class="card-title text-white mb-0">Tecnificación Avanzada</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="card border-0 overflow-hidden h-100 gallery-item"
                                    @mouseenter="activeImage = 1" :class="{ 'active': activeImage === 1 }">
                                    <div class="ratio ratio-1x1">
                                        <img src="/assets/pistas/arco.jpg" alt="Programa de inclusión social"
                                            class="card-img object-fit-cover">
                                    </div>
                                    <div class="card-img-overlay d-flex align-items-end gallery-overlay">
                                        <h5 class="card-title text-white mb-0">Inclusión Social</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="card border-0 overflow-hidden h-100 gallery-item"
                                    @mouseenter="activeImage = 2" :class="{ 'active': activeImage === 2 }">
                                    <div class="ratio ratio-1x1">
                                        <img src="/assets/pistas/arco.jpg" alt="Desarrollo juvenil"
                                            class="card-img object-fit-cover">
                                    </div>
                                    <div class="card-img-overlay d-flex align-items-end gallery-overlay">
                                        <h5 class="card-title text-white mb-0">Formación Juvenil</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="card border-0 overflow-hidden h-100 gallery-item"
                                    @mouseenter="activeImage = 3" :class="{ 'active': activeImage === 3 }">
                                    <div class="ratio ratio-1x1">
                                        <img src="/assets/pistas/arco.jpg" alt="Valores de equipo"
                                            class="card-img object-fit-cover">
                                    </div>
                                    <div class="card-img-overlay d-flex align-items-end gallery-overlay">
                                        <h5 class="card-title text-white mb-0">Valores de Equipo</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card border-0 shadow-sm mt-3">
                            <div class="card-body">
                                <div class="row text-center">
                                    <div class="col-md-4 mb-3 mb-md-0">
                                        <h3 class="display-6 fw-bold text-primary mb-0">850+</h3>
                                        <p class="text-secondary mb-0">Jugadores formados</p>
                                    </div>
                                    <div class="col-md-4 mb-3 mb-md-0">
                                        <h3 class="display-6 fw-bold text-primary mb-0">320+</h3>
                                        <p class="text-secondary mb-0">Niños en programas sociales</p>
                                    </div>
                                    <div class="col-md-4">
                                        <h3 class="display-6 fw-bold text-primary mb-0">94%</h3>
                                        <p class="text-secondary mb-0">Tasa de mejora</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    data() {
        return {
            activeImage: 0
        }
    }
}
</script>

<style scoped>
.text-gradient {
    background: linear-gradient(90deg, var(--bs-primary), var(--bs-dark));
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
}

.opacity-10 {
    opacity: 0.1;
}

.mission-card {
    transition: transform 0.3s, box-shadow 0.3s;
}

.mission-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.gallery-item {
    transition: all 0.3s ease;
    filter: grayscale(30%);
    border-radius: 0.5rem;
}

.gallery-item:hover,
.gallery-item.active {
    filter: grayscale(0%);
    z-index: 2;
}

.gallery-overlay {
    background: linear-gradient(to top, rgba(0, 0, 0, 0.7), transparent);
    opacity: 0;
    transition: opacity 0.3s;
}

.gallery-item:hover .gallery-overlay {
    opacity: 1;
}

.mission-card:nth-child(1) {
    animation: fadeInUp 0.5s ease-out;
}

.mission-card:nth-child(2) {
    animation: fadeInUp 0.7s ease-out;
}

.mission-card:nth-child(3) {
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