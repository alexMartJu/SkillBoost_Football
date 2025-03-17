<template>
    <main>
        <div class="card mb-4" @click="details(subtipo.slug)">
            <div class="card-image-container">
                <img :src="`/assets/subtipos/${subtipo.images[0]?.imageUrl}`" :alt="subtipo.nombre"
                    class="card-img-top">
            </div>
            <div class="card-img-overlay d-flex flex-column align-items-start justify-content-end text-white">
                <div class="subtipo-name w-100">
                    <h3 class="mb-2">{{ subtipo.nombre }} 🏆</h3>
                    <div class="subtipo-details">
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
    props: {
        subtipo: {
            type: Object,
            required: true
        }
    },

    setup() {
        const router = useRouter();

        const details = (slug) => {
            router.push({ name: 'detailsSubtipoTecnificacion', params: { slug } });
        }

        return { details }
    }
};
</script>

<style scoped>
.card {
    position: relative;
    overflow: hidden;
    border: none;
    border-radius: 15px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
    cursor: pointer;
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2) !important;
}

.card-image-container {
    aspect-ratio: 16/9;
    overflow: hidden;
}

.card-img-top {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.5s ease;
}

.card:hover .card-img-top {
    transform: scale(1.1);
}

.card-img-overlay {
    background: linear-gradient(to bottom, transparent 30%, rgba(0, 0, 0, 0.8) 100%);
    padding: 1rem;
    transition: background 0.3s ease;
}

.card:hover .card-img-overlay {
    background: linear-gradient(to bottom, transparent 20%, rgba(0, 0, 0, 0.9) 100%);
}

.subtipo-name {
    background-color: rgba(0, 0, 0, 0.5);
    backdrop-filter: blur(5px);
    width: 100%;
    padding: 1rem;
    border-radius: 10px;
    transition: all 0.3s ease;
}

.subtipo-name h3 {
    font-size: clamp(1.2rem, 2.5vw, 1.8rem);
    font-weight: 600;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.subtipo-details {
    font-size: 0.9rem;
    opacity: 0.9;
}

.subtipo-details p {
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
}

@media (max-width: 768px) {
    .card {
        margin: 0.5rem;
    }

    .subtipo-name {
        padding: 0.75rem;
    }

    .subtipo-details {
        font-size: 0.8rem;
    }
}

@media (max-width: 480px) {
    .card-image-container {
        aspect-ratio: 4/3;
    }
}
</style>