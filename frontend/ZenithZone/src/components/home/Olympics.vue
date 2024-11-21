<template>
<div class="landing-section d-flex">
    <div class="carousel-section col-md-6 p-0">
        <div class="anillos-div">
            <img src="../../assets/anillos.png" alt="" class="anillos">
        </div>        
        <img src="../../assets/home/olympics.webp" alt="" class="d-block w-100 h-100 object-fit-cover">
    </div>

    <div class="text-section col-md-6 d-flex flex-column justify-content-center px-4">
        <div class="text-container">
            <p class="fw-bold text-center">CREAMOS LEYENDAS</p>
        </div>
    </div>
</div>
</template>


<script>
export default {
    name: "MiddleSection",
};
</script>


<style scoped>
.landing-section {
    height: 90vh;
    overflow: hidden;
    box-shadow: 0 0 15px 5px rgba(0, 0, 0, 0.7);
    z-index: 5;
}

.text-section {
    background: linear-gradient(to bottom right, #ff9a1e, #fff4aa);
    color: white;
    width: 35%;
    /* Ancho del texto */
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 5;
}

.text-container {
    background: linear-gradient(to bottom right, #ffef85, #ff9a1e);
    
    color: white;
    height: 400px;
    width: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 40px;
    box-shadow: -20px 20px 0 5px rgba(255, 255, 255, 0.4);
}

.carousel-section {
    position: relative;
    width: 65%;
    z-index: 1;
}

.object-fit-cover {
    object-fit: cover;
}

.logo {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 40%;
    padding: 20px;
    border-radius: 10px;
    z-index: 1;
}

.anillos-div {
    background-color: rgba(255, 255, 255, 0.4);
    padding: 500px 500px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 101%;
    border-radius: 10px;
    z-index: 0;
}

.anillos {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 70%;
    border-radius: 10px;
    z-index: 2;
}
</style>
