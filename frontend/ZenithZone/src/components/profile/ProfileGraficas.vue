<template>
    <section class="profile-analytics">
        <div class="analytics-header">
            <h2 class="year-title">Evolución {{ currentYear }}</h2>
            <div class="analytics-badge">Datos en tiempo real</div>
        </div>
        
        <div class="analytics-content">
            <div class="graph-container">
                <Graphics :graficas="state.graficaValues" />
            </div>
            
            <div class="analytics-info">
                <p class="info-text">Para un mayor nivel de detalle, contacta con tus entrenadores</p>
                <div class="stats-grid">
                    <div class="stat-card">
                        <span class="stat-value">{{ state.graficaValues.length }}</span>
                        <span class="stat-label">Meses registrados</span>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import Graphics from '@/components/home/Graphics.vue';
import Constant from '@/Constant';
import { computed, watchEffect } from 'vue';
import { reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

export default {
    components: {
        Graphics
    },

    setup() {
        const store = useStore();
        const currentYear = new Date().getFullYear();

        const state = reactive({
            graficas: store.getters['profile/GetGraficasProfile'],
            graficaValues: []
        });

        const valuesGrafica = () => {
            let data = [];

            state.graficas.forEach(values => {
                data.push(values);
            });

            return data;
        }

        watchEffect(() => {
            state.graficaValues = valuesGrafica();
        });

        return { state, currentYear };
    }
};
</script>

<style scoped>
.profile-analytics {
    padding: 3rem 2rem;
    background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
    border-radius: 20px;
    margin: 2rem;
}

.analytics-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 2rem;
}

.year-title {
    font-size: 2rem;
    color: #2c3e50;
    font-weight: 600;
}

.analytics-badge {
    background: var(--bs-primary);
    color: white;
    padding: 0.5rem 1rem;
    border-radius: 20px;
    font-size: 0.9rem;
}

.analytics-content {
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 2rem;
}

.graph-container {
    background: white;
    border-radius: 15px;
    padding: 2rem;
    box-shadow: 0 10px 30px rgba(0,0,0,0.1);
}

.analytics-info {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.info-text {
    font-size: 1.2rem;
    color: #2c3e50;
    line-height: 1.6;
}

.stats-grid {
    display: grid;
    gap: 1rem;
}

.stat-card {
    background: white;
    padding: 1.5rem;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0 5px 15px rgba(0,0,0,0.05);
}

.stat-value {
    font-size: 2rem;
    font-weight: 700;
    color: var(--bs-primary);
    display: block;
}

.stat-label {
    color: #7f8c8d;
    font-size: 0.9rem;
    margin-top: 0.5rem;
}

@media (max-width: 1024px) {
    .analytics-content {
        grid-template-columns: 1fr;
    }
}
</style>