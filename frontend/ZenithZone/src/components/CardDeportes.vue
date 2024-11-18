<template>
    <main>
        <div class="card mb-4">
            <img src="../assets/placeholder.png" alt="" class="card-img-top">
            <div class="card-img-overlay d-flex align-items-end justify-content-center text-white">
                <div class="deportes-name">
                    <h3>{{ deporte.nombre }}</h3>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
export default {
    props: {
        deporte: {
            type: Object,
            required: true
        }
    }
};
</script>

<style scoped>
.card {
    position: relative;
    overflow: hidden;
    /* Evitar que la imagen se desborde */
}

.card:hover {
    cursor: pointer;
}

.card-img-top {
    transition: transform 0.3s ease;
    /* Suavizar la transición */
}

.card:hover .card-img-top {
    transform: scale(1.1);
    /* Agrandar la imagen */
}

.card-img-overlay {
    padding: 0;
    box-shadow: inset 0 0 10px 2px rgba(0, 0, 0, 0.5);
}

.deportes-name {
    background-color: rgba(255, 255, 255, 0.424);
    width: 100%;
    height: 20%;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
