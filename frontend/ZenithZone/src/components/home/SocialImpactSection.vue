<template>
    <section class="py-5 bg-white position-relative overflow-hidden">
        <div class="container-xxl">
            <!-- Section Header -->
            <div class="row mb-5">
                <div class="col-lg-8 mx-auto text-center">
                    <span class="badge bg-primary-subtle text-primary mb-3 px-3 py-2 rounded-pill fw-semibold">NUESTRO
                        APOYO</span>
                    <h2 class="display-4 fw-bold mb-0 text-gradient">Transformando Comunidades a Través del Fútbol</h2>
                </div>
            </div>

            <!-- Main Content -->
            <div class="row g-5 justify-content-center">
                <div class="col-lg-10">
                    <div class="p-4">
                        <p class="lead mb-4 border-start border-4 border-primary ps-3">
                            En <span class="fw-bold">SkillBoost Football</span>, creemos firmemente que el deporte es
                            una herramienta poderosa para la inclusión social y el desarrollo personal.
                        </p>

                        <div class="row g-4">
                            <div class="col-md-4">
                                <div class="card border-0 shadow-sm h-100 impact-card">
                                    <div class="card-body p-4">
                                        <div class="d-flex align-items-center mb-3">
                                            <div class="icon-circle bg-primary-subtle text-primary me-3">
                                                <i class="bi bi-heart-fill"></i>
                                            </div>
                                            <h3 class="h4 text-primary mb-0">Compromiso Social</h3>
                                        </div>
                                        <p class="card-text">Colaboramos con fundaciones y organizaciones sin ánimo de
                                            lucro para ofrecer programas de tecnificación a niños y niñas en
                                            situación de vulnerabilidad, brindándoles no solo formación deportiva sino
                                            también valores y herramientas para su futuro.</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="card border-0 shadow-sm h-100 impact-card">
                                    <div class="card-body p-4">
                                        <div class="d-flex align-items-center mb-3">
                                            <div class="icon-circle bg-primary-subtle text-primary me-3">
                                                <i class="bi bi-people-fill"></i>
                                            </div>
                                            <h3 class="h4 text-primary mb-0">Becas de Formación</h3>
                                        </div>
                                        <p class="card-text">Nuestro programa de becas garantiza que el talento nunca se
                                            pierda por falta de recursos. Cada año, otorgamos más de 50 becas completas
                                            a jóvenes con potencial deportivo de entornos desfavorecidos, cubriendo su
                                            formación integral.</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="card border-0 shadow-sm h-100 impact-card">
                                    <div class="card-body p-4">
                                        <div class="d-flex align-items-center mb-3">
                                            <div class="icon-circle bg-primary-subtle text-primary me-3">
                                                <i class="bi bi-globe2"></i>
                                            </div>
                                            <h3 class="h4 text-primary mb-0">Proyectos Internacionales</h3>
                                        </div>
                                        <p class="card-text">Extendemos nuestro impacto más allá de nuestras fronteras,
                                            con proyectos de cooperación en Latinoamérica y África, donde formamos a
                                            entrenadores locales.</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="text-center mt-5">
                            <router-link to="/apoyo"
                                class="btn btn-primary btn-lg px-4 py-3 rounded-pill shadow-sm redirect-button">
                                <span class="d-flex align-items-center justify-content-center">
                                    <span>Descubre nuestros aliados</span>
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
    name: 'SocialImpactSection'
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

.impact-card {
    transition: transform 0.3s, box-shadow 0.3s;
}

.impact-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
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

.redirect-button {
    transition: transform 0.3s, box-shadow 0.3s;
}

.redirect-button:hover {
    transform: translateY(-3px);
    box-shadow: 0 0.5rem 1rem rgba(var(--bs-primary-rgb), 0.3) !important;
}

/* Animaciones con clases de Bootstrap */
.impact-card:nth-child(1) {
    animation: fadeInUp 0.5s ease-out;
}

.impact-card:nth-child(2) {
    animation: fadeInUp 0.7s ease-out;
}

.impact-card:nth-child(3) {
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