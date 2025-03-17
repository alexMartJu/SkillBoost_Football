<template>
    <footer class="bg-dark text-white py-5">
        <div class="container">
            <div class="row g-4 align-items-center">
                <div class="col-lg-4">
                    <div>
                        <h5 class="display-6 fw-bold mb-3 gradient-text">SkillBoost Football</h5>
                        <p class="lead mb-0 opacity-75">
                            Formando el futuro del fútbol: desarrollando talento y creando oportunidades para todos.                        </p>
                    </div>
                </div>

                <div class="col-lg-4">
                    <h5 class="text-uppercase fw-bold mb-3">Síguenos</h5>
                    <div class="d-flex justify-content-center gap-4">
                        <a href="#" class="social-icon" title="Facebook">
                            <i class="bi bi-facebook fs-4"></i>
                        </a>
                        <a href="#" class="social-icon" title="Twitter">
                            <i class="bi bi-twitter fs-4"></i>
                        </a>
                        <a href="#" class="social-icon" title="Instagram">
                            <i class="bi bi-instagram fs-4"></i>
                        </a>
                        <a href="#" class="social-icon" title="LinkedIn">
                            <i class="bi bi-linkedin fs-4"></i>
                        </a>
                    </div>
                </div>

                <div class="col-lg-4">
                    <h5 class="text-uppercase fw-bold mb-3">Contacto</h5>
                    <div class="d-flex flex-column gap-3">
                        <a href="mailto:info@skillboostfootball.com" class="text-white text-decoration-none contact-link">
                            <i class="bi bi-envelope-fill me-2"></i>
                            <span>info@skillboostfootball.com</span>
                        </a>
                        <a href="tel:+1234567890" class="text-white text-decoration-none contact-link">
                            <i class="bi bi-telephone-fill me-2"></i>
                            <span>+1 234 567 890</span>
                        </a>
                    </div>
                </div>
            </div>

            <hr class="my-4 opacity-25">

            <div class="row">
                <div class="col-12 text-center">
                    <p class="opacity-75 small">
                        © 2025 SkillBoost Football. Todos los derechos reservados.
                    </p>
                </div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'Footer'
}
</script>

<style scoped>
.bg-dark {
    background: linear-gradient(145deg, #1a1a1a, #0a0a0a) !important;
}

.gradient-text {
    background: linear-gradient(45deg, #fff, #e6e6e6);
    -webkit-background-clip: text;
    background-clip: text;
    -webkit-text-fill-color: transparent;
}

.social-icon {
    width: 45px;
    height: 45px;
    border: 2px solid rgba(255, 255, 255, 0.2);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    background: rgba(255, 255, 255, 0.05);
    transition: all 0.3s;
}

.social-icon:hover {
    transform: translateY(-5px);
    border-color: white;
    background: rgba(255, 255, 255, 0.1);
    box-shadow: 0 5px 20px rgba(255, 255, 255, 0.15);
    color: white;
}

.contact-link:hover {
    opacity: 1 !important;
    transform: translateX(5px);
}
</style>