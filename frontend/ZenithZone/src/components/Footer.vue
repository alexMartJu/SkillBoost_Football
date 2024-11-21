<template>
<footer class="footer text-white py-5">
    <div class="container text-center">
        <!-- Texto o información de derechos de autor -->
        <p class="mb-0">
            &copy; {{ currentYear }} ZenithZone. Todos los derechos reservados.
        </p>
        <!-- Enlaces del footer -->
        <div class="social-links mt-3">
            <a href="#" class="text-white mx-2">
                <i class="fab fa-facebook"></i> Facebook
            </a>
            <a href="#" class="text-white mx-2">
                <i class="fab fa-twitter"></i> Twitter
            </a>
            <a href="#" class="text-white mx-2">
                <i class="fab fa-instagram"></i> Instagram
            </a>
        </div>
    </div>
</footer>
</template>


<script>
export default {
    name: 'Footer',
};
</script>


<style scoped>
/* Estilos opcionales personalizados */
.social-links i {
    font-size: 20px;
}

.footer {
    margin-top: 50px;
    background-color: rgb(20, 20, 20);
}
</style>
