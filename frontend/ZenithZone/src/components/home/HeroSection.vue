<template>
    <div class="container-fluid position-relative p-0 section-wrapper">
        <!-- Contenedor con gradiente y centrado de la imagen -->
        <div class="img-container position-relative">
            <img class="img-stretch" src="../../assets/home/atletismo.jpg" alt="">
            <!-- Gradiente sobre la parte inferior de la imagen -->
            <div class="gradient-overlay position-absolute w-100"></div>
        </div>

        <!-- Contenedor centrado para logo y eslogan -->
        <div class="position-absolute top-50 start-50 translate-middle text-center w-100">
            <img src="../../assets/logo_3.png" alt="" class="logo">
            <h1 class="eslogan">Alcanza tu Zénit</h1>
        </div>
    </div>
</template>

<script></script>

<style scoped>
.section-wrapper{
    /* box-shadow: 0 0 10px 10px rgb(0, 0, 0, 0.7); */
    z-index: 1;
}

.img-stretch {
    width: 100%;
    object-fit: cover;
}

.img-container {
    position: relative;
}

.gradient-overlay {
    background: linear-gradient(to top, rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0));
    height: 60%;
    bottom: 0;
    z-index: 0;
}

.logo {
    width: 40%;
    padding: 50px;
    border-radius: 10px;
    z-index: 2;
}

.eslogan {
    font-family: 'Roboto', sans-serif;
    font-size: 80px;
    font-style: italic;
    font-weight: 100;
    margin-top: 150px;
    margin-bottom: -100px;
    color: white;
    text-shadow: 5px 5px 1px #ff6600;
    z-index: 5;
}
</style>
