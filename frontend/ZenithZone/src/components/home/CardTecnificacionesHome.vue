<template>
    <div class="tecnificacion-card" @click="handleClick">
        <div class="card-inner">
            <div class="card-front">
                <img :src="getImageUrl()" :alt="tecnificacion.nombre" class="card-img">
                <div class="card-overlay">
                    <h3 class="card-title">{{ tecnificacion.nombre }}</h3>
                    <div class="card-badge">
                        <i class="bi bi-lightning-charge-fill"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
    props: {
        tecnificacion: {
            type: Object,
            required: true
        }
    },
    setup(props) {
        const router = useRouter();

        const handleClick = () => {
            router.push({ name: 'TecnificacionSlug', params: { slug: props.tecnificacion.slug } });
        }

        const getImageUrl = () => {
            if (props.tecnificacion.images && props.tecnificacion.images.length > 0) {
                return `/assets/tecnificaciones/${props.tecnificacion.images[0].imageUrl}`;
            }
            return '/assets/tecnificaciones/default.jpg';
        }

        return {
            handleClick,
            getImageUrl
        }
    },
}
</script>

<style scoped>
.tecnificacion-card {
    height: 350px;
    position: relative;
    perspective: 1500px;
    cursor: pointer;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.card-inner {
    position: relative;
    width: 100%;
    height: 100%;
    transition: transform 0.6s cubic-bezier(0.23, 1, 0.32, 1);
    transform-style: preserve-3d;
}

.card-front {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
    border-radius: 12px;
    overflow: hidden;
    z-index: 1;
}

.card-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.8s ease;
}

.card-overlay {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 1.5rem;
    background: linear-gradient(to top, rgba(0, 0, 0, 0.85), transparent);
    transition: all 0.3s ease;
}

.card-title {
    color: white;
    font-weight: 700;
    margin: 0;
    font-size: 1.5rem;
    text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
    transform: translateY(0);
    transition: transform 0.4s ease;
}

.card-badge {
    position: absolute;
    top: -60px;
    right: 20px;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background: var(--bs-primary);
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 1.2rem;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    opacity: 0;
    transform: translateY(20px);
    transition: all 0.4s ease 0.1s;
}

/* Hover effects */
.tecnificacion-card:hover .card-img {
    transform: scale(1.1);
}

.tecnificacion-card:hover .card-overlay {
    background: linear-gradient(to top, var(--bs-primary), rgba(0, 0, 0, 0.4));
}

.tecnificacion-card:hover .card-title {
    transform: translateY(-10px);
}

.tecnificacion-card:hover .card-badge {
    opacity: 1;
    transform: translateY(0);
}

@media (max-width: 768px) {
    .tecnificacion-card {
        height: 300px;
    }

    .card-title {
        font-size: 1.25rem;
    }
}
</style>