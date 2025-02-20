<template>
    <div class="row g-4">
        <div class="col-lg-8">
            <div class="chart-container bg-light rounded p-3">
                <canvas ref="radarChart"></canvas>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="metrics-form">
                <h5 class="mb-4">Actualizar Métricas</h5>
                <div class="d-flex flex-column gap-3">
                    <div v-for="(label, index) in labels" :key="index" class="metric-item">
                        <label class="form-label d-flex align-items-center gap-2">
                            <i class="bi" :class="getIconForMetric(label)"></i>
                            {{ label }}
                        </label>
                        <div class="d-flex align-items-center gap-2">
                            <input type="range" class="form-range" v-model.number="graficaInputs[label]" min="0"
                                max="100" @input="updateGrafica">
                            <span class="badge bg-primary">{{ graficaInputs[label] }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import { ref, onMounted, watch } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import { debounce } from 'lodash';

Chart.register(...registerables);

export default {
    name: 'GraficaAlumno',
    props: {
        profileId: {
            type: Number,
            required: true,
        },
    },
    setup(props) {
        const store = useStore();
        const radarChart = ref(null);
        const graficas = ref([]);
        const graficaInputs = ref({
            'Motivación': 0,
            'Agilidad': 0,
            'Velocidad': 0,
            'Aguante': 0,
            'Fuerza': 0,
        });
        const labels = ['Motivación', 'Agilidad', 'Velocidad', 'Aguante', 'Fuerza'];

        onMounted(async () => {
            if (props.profileId) {
                await store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_GRAFICAS}`, props.profileId);
                renderGrafica();
            }
        });

        watch(() => props.profileId, async (newProfileId) => {
            if (newProfileId) {
                await store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_GRAFICAS}`, newProfileId);
                renderGrafica();
            }
        });

        watch(() => store.state.entrenadorDashboard.graficas, (newGraficas) => {
            graficas.value = newGraficas;
            if (!window.chartInstance) {
                renderGrafica();
            }
            // Only call setGraficaInputs on initial load, not during updates
            if (!graficaInputs.value[labels[0]]) {
                setGraficaInputs();
            }
        }, { deep: true });

        const getIconForMetric = (label) => {
            const icons = {
                'Motivación': 'bi-lightning-charge',
                'Agilidad': 'bi-wind',
                'Velocidad': 'bi-speedometer2',
                'Aguante': 'bi-heart-pulse',
                'Fuerza': 'bi-trophy'
            };
            return icons[label] || 'bi-graph-up';
        };

        const setGraficaInputs = () => {
            const currentMonth = new Date().getMonth() + 1;
            const currentYear = new Date().getFullYear();

            // Filtrar las gráficas para el mes actual y el profileId correspondiente
            const currentGraficas = graficas.value.filter(
                grafica =>
                    grafica.Mes === currentMonth &&
                    grafica.Año === currentYear &&
                    BigInt(grafica.profile_id) === BigInt(props.profileId)
            );

            // Si se encuentran gráficas para el mes actual, actualizar los inputs
            if (currentGraficas.length > 0) {
                labels.forEach(label => {
                    // Buscar la gráfica que corresponde a cada sección
                    const grafica = currentGraficas.find(grafica => grafica.seccion === label);
                    graficaInputs.value[label] = grafica ? grafica.nivel : 0;
                });
            } else {
                // Si no se encuentran gráficas, asegurarse de que los inputs estén vacíos o en 0
                labels.forEach(label => {
                    graficaInputs.value[label] = 0;
                });
            }
        };

        // Función para generar un color aleatorio en formato rgba
        const getRandomColor = () => {
            const r = Math.floor(Math.random() * 256);
            const g = Math.floor(Math.random() * 256);
            const b = Math.floor(Math.random() * 256);
            return `rgba(${r}, ${g}, ${b}, 0.6)`;
        };

        // Función para generar un color de borde aleatorio en formato rgba (con alfa 1)
        const getRandomBorderColor = () => {
            const r = Math.floor(Math.random() * 256);
            const g = Math.floor(Math.random() * 256);
            const b = Math.floor(Math.random() * 256);
            return `rgba(${r}, ${g}, ${b}, 1)`;
        };

        const renderGrafica = () => {
            if (window.chartInstance) {
                window.chartInstance.destroy();
            }

            const filteredGraficas = graficas.value.filter(grafica =>
                BigInt(grafica.profile_id) === BigInt(props.profileId)
            );

            const mesesAnios = [
                ...new Set(filteredGraficas.map(graficas => `${graficas.Mes}-${graficas.Año}`)),
            ];

            const datasets = mesesAnios.map(mesAnio => {
                const [mes, anio] = mesAnio.split('-');
                const data = labels.map(label => {
                    const graficaMesAnio = filteredGraficas.filter(
                        graficas => graficas.Mes === parseInt(mes) &&
                            graficas.Año === parseInt(anio) &&
                            graficas.seccion === label
                    );
                    return graficaMesAnio.length > 0 ? graficaMesAnio[0].nivel : 0;
                });

                return {
                    label: `${mes}-${anio}`,
                    data,
                    borderWidth: 2,
                    tension: 0, // Esta es la propiedad que hace las líneas curvas (0 para líneas rectas hasta 1 para líneas curvas)
                    backgroundColor: getRandomColor(),
                    borderColor: getRandomBorderColor(),
                };
            });

            const data = { labels, datasets };
            const options = {
                responsive: true,
                plugins: { legend: { display: true } },
                scales: {
                    r: {
                        angleLines: { color: 'rgba(0, 0, 0, 0.3)' },
                        suggestedMin: 0,
                        suggestedMax: 100,
                        ticks: { stepSize: 20, font: { size: 12 }, color: '#4b5563' },
                        grid: { color: 'rgba(0, 0, 0, 0.1)' },
                        pointLabels: { font: { size: 14 }, color: '#000' },
                    },
                },
            };

            const ctx = radarChart.value.getContext('2d');
            window.chartInstance = new Chart(ctx, { type: 'radar', data, options });
        };

        const debouncedUpdate = debounce(async () => {
            const currentMonth = new Date().getMonth() + 1;
            const currentYear = new Date().getFullYear();

            const currentValues = { ...graficaInputs.value };

            if (window.chartInstance) {
                window.chartInstance.options.animation = false;
            }

            await Promise.all(
                Object.entries(currentValues)
                    .filter(([_, value]) => value > 0)
                    .map(([section, nivel]) =>
                        store.dispatch(`entrenadorDashboard/${Constant.UPDATE_ONE_GRAFICA}`, {
                            id: props.profileId,
                            graficas: {
                                seccion: section,
                                nivel: nivel,
                                Mes: currentMonth,
                                Año: currentYear,
                            }
                        })
                    )
            );

            if (window.chartInstance) {
                window.chartInstance.options.animation = true;
            }
            renderGrafica();
        }, 300);

        const updateGrafica = () => {
            // Directly trigger the debounced update
            debouncedUpdate();
        };

        return {
            radarChart,
            graficaInputs,
            labels,
            updateGrafica,
            getIconForMetric
        };
    },
};
</script>


<style scoped>
.chart-container {
    min-height: 400px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.metrics-form {
    background-color: #f8f9fa;
    padding: 1.5rem;
    border-radius: 0.5rem;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.metric-item {
    padding: 0.8rem;
    border-radius: 0.5rem;
    background-color: white;
    transition: transform 0.2s;
}

.metric-item:hover {
    transform: translateY(-2px);
}

.form-range::-webkit-slider-thumb {
    background: var(--bs-primary);
}

.form-range::-webkit-slider-runnable-track {
    background: #dee2e6;
}

.bi {
    font-size: 1.2rem;
    color: var(--bs-primary);
}

.badge {
    min-width: 3rem;
    text-align: center;
}
</style>