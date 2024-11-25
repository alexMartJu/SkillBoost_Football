<template>
    <main>
        <div class="card-deportes">
            <img :src="`/assets/deportes/${deporte.images[0].imageUrl}`" alt="" class="card-img-top">
            <div class="card-img-overlay d-flex align-items-end justify-content-center text-white">
                <div class="deportes-name">
                    <h3 class="fw-bold">{{ deporte.nombre }}</h3>
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
.card-deportes {
    height: 350px;
    position: relative;
    overflow: hidden;
}

.card-deportes:hover {
    cursor: pointer;
}

.card-img-top {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.3s ease;
}

.card-deportes:hover .card-img-top {
    transform: scale(1.1);
}

.card-img-overlay {
    padding: 0;
    box-shadow: inset 0 0 20px 5px rgba(0, 0, 0, 0.5);
}

.deportes-name {
    background-color: rgba(0, 0, 0, 0.4);
    width: 100%;
    height: 25%;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
}

h3 {
    text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.8);
}
</style>
