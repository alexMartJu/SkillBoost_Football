<template>
    <div class="graficas-container">
        <!-- Cabecera -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="mb-1 fw-bold text-primary">Gráficas de Rendimiento</h2>
                <p class="text-muted mb-0" v-if="alumnoInfo">
                    {{ alumnoInfo.numeroSocio }}
                </p>
            </div>
            <button class="btn btn-outline-secondary" @click="volver">
                <i class="bi bi-arrow-left me-1"></i> Volver
            </button>
        </div>

        <!-- Alertas -->
        <div v-if="error" class="alert alert-danger" role="alert">
            {{ error }}
        </div>
        <div v-if="successMessage" class="alert alert-success" role="alert">
            {{ successMessage }}
        </div>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2">Cargando gráficas...</p>
        </div>

        <!-- Contenido de gráficas -->
        <div v-else class="row g-4">
            <!-- Mensaje cuando no hay gráficas disponibles -->
            <div v-if="noGraficasDisponibles" class="col-12">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center py-5">
                        <i class="bi bi-graph-up text-muted" style="font-size: 3rem;"></i>
                        <h5 class="mt-3 mb-2">No hay gráficas disponibles</h5>
                        <p class="text-muted">Este usuario no ha sido evaluado hasta la fecha.</p>
                        <p class="text-muted">Puedes crear la primera evaluación utilizando los controles de la derecha.
                        </p>
                    </div>
                </div>
            </div>

            <!-- Gráfica cuando hay datos disponibles -->
            <div v-else class="col-lg-8">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h5 class="card-title mb-3">Evolución del Rendimiento</h5>
                        <div class="chart-container">
                            <canvas ref="radarChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Panel de control con sliders -->
            <div class="col-lg-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h5 class="card-title mb-3">Actualizar Métricas</h5>
                        <div class="d-flex flex-column gap-3">
                            <div v-for="(label, index) in labels" :key="index" class="metric-item">
                                <label class="form-label d-flex align-items-center gap-2">
                                    <i class="bi" :class="getIconForMetric(label)"></i>
                                    {{ label }}
                                </label>
                                <div class="d-flex align-items-center gap-2">
                                    <input type="range" class="form-range" v-model.number="graficaInputs[label]" min="0"
                                        max="100">
                                    <span class="badge bg-primary">{{ graficaInputs[label] }}</span>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end mt-3">
                            <button class="btn btn-primary" @click="guardarGraficas" :disabled="loading">
                                <i class="bi bi-save me-1"></i> Guardar Cambios
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive, computed, onMounted, onBeforeUnmount, nextTick } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { Chart, registerables } from 'chart.js';
import Constant from '../../../Constant';

Chart.register(...registerables);

export default {
    name: 'GraficasAlumno',
    props: {
        numeroSocio: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const store = useStore();
        const router = useRouter();
        const radarChart = ref(null);
        const graficaInputs = reactive({
            'Técnica': 0,
            'Táctica': 0,
            'Físico': 0,
            'Mental': 0,
            'Aplicación en juego': 0,
        });

        const labels = ref(['Técnica', 'Táctica', 'Físico', 'Mental', 'Aplicación en juego']);
        const successMessage = ref(null);
        const alumnoInfo = ref(null);
        const chartInstance = ref(null);
        const noGraficasDisponibles = ref(false);

        const loading = computed(() => store.getters['entrenadorDashboard/isLoading']);
        const error = computed(() => store.getters['entrenadorDashboard/getError']);
        const getGraficas = computed(() => store.getters['entrenadorDashboard/getGraficas']);

        const getIconForMetric = (label) => {
            const icons = {
                'Técnica': 'bi-dribbble',
                'Táctica': 'bi-diagram-3',
                'Físico': 'bi-lightning-charge',
                'Mental': 'bi-brain',
                'Aplicación en juego': 'bi-controller'
            };
            return icons[label] || 'bi-graph-up';
        };

        const cargarGraficas = async () => {
            try {
                await store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_GRAFICAS}`, props.numeroSocio);

                //Verificar si hay gráficas disponibles
                const graficas = getGraficas.value || [];
                if (graficas.length === 0) {
                    noGraficasDisponibles.value = true;
                    return;
                }

                noGraficasDisponibles.value = false;
                setGraficaInputs();
                renderGrafica();
            } catch (error) {
                console.error('Error al cargar gráficas:', error);
                noGraficasDisponibles.value = true;
            }
        };

        const setGraficaInputs = () => {
            const currentMonth = new Date().getMonth() + 1;
            const currentYear = new Date().getFullYear();

            //Buscar las gráficas del mes actual
            const graficas = getGraficas.value || [];
            const currentGraficas = graficas.find(grafica => grafica.mes === currentMonth);

            if (currentGraficas && currentGraficas.secciones) {
                //Actualizar los inputs con los valores de las secciones
                labels.value.forEach(label => {
                    const seccion = currentGraficas.secciones.find(s => s.seccion === label);
                    graficaInputs[label] = seccion ? seccion.nivel : 0;
                });
            } else {
                //Si no hay gráficas para el mes actual, inicializar en 0
                labels.value.forEach(label => {
                    graficaInputs[label] = 0;
                });
            }
        };

        const renderGrafica = () => {
            //Si ya existe un gráfico, destruirlo
            if (chartInstance.value) {
                chartInstance.value.destroy();
                chartInstance.value = null;
            }

            //Verificar que el canvas existe
            if (!radarChart.value) {
                console.error('Canvas element not found');
                return;
            }

            const ctx = radarChart.value.getContext('2d');
            if (!ctx) {
                console.error('Could not get 2D context from canvas');
                return;
            }

            //Función para obtener el nombre del mes
            const getNombreMes = (numeroMes) => {
                const meses = [
                    'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                    'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
                ];
                return meses[numeroMes - 1] || 'Desconocido';
            };
            const graficas = Array.isArray(getGraficas.value) ? getGraficas.value : [];

            const datasets = [];
            const colors = [
                { bg: 'rgba(54, 162, 235, 0.2)', border: 'rgba(54, 162, 235, 1)' },
                { bg: 'rgba(255, 99, 132, 0.2)', border: 'rgba(255, 99, 132, 1)' },
                { bg: 'rgba(75, 192, 192, 0.2)', border: 'rgba(75, 192, 192, 1)' },
                { bg: 'rgba(255, 206, 86, 0.2)', border: 'rgba(255, 206, 86, 1)' },
                { bg: 'rgba(153, 102, 255, 0.2)', border: 'rgba(153, 102, 255, 1)' },
                { bg: 'rgba(255, 159, 64, 0.2)', border: 'rgba(255, 159, 64, 1)' }
            ];

            //Crear datasets para cada mes con gráficas
            graficas.forEach((grafica, index) => {
                if (!grafica || !grafica.secciones) return;
                const secciones = Array.isArray(grafica.secciones) ? grafica.secciones : [];

                const data = labels.value.map(label => {
                    const seccion = secciones.find(s => s && s.seccion === label);
                    return seccion && typeof seccion.nivel === 'number' ? seccion.nivel : 0;
                });

                const colorIndex = index % colors.length;

                //Formatear la etiqueta con el nombre del mes y el año
                const mes = grafica.mes || 0;
                const año = grafica.año || new Date().getFullYear();
                const etiqueta = `${getNombreMes(mes)} ${año}`;

                datasets.push({
                    label: etiqueta,
                    data,
                    backgroundColor: colors[colorIndex].bg,
                    borderColor: colors[colorIndex].border,
                    borderWidth: 2,
                    pointBackgroundColor: colors[colorIndex].border,
                    pointBorderColor: '#fff',
                    pointHoverBackgroundColor: '#fff',
                    pointHoverBorderColor: colors[colorIndex].border
                });
            });

            try {
                chartInstance.value = new Chart(ctx, {
                    type: 'radar',
                    data: {
                        labels: labels.value,
                        datasets
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                position: 'top',
                            },
                            tooltip: {
                                callbacks: {
                                    label: function (context) {
                                        return `${context.dataset.label}: ${context.raw}`;
                                    }
                                }
                            }
                        },
                        scales: {
                            r: {
                                angleLines: {
                                    display: true,
                                    color: 'rgba(0, 0, 0, 0.1)'
                                },
                                suggestedMin: 0,
                                suggestedMax: 100,
                                ticks: {
                                    stepSize: 20,
                                    backdropColor: 'transparent'
                                },
                                grid: {
                                    color: 'rgba(0, 0, 0, 0.05)'
                                },
                                pointLabels: {
                                    font: {
                                        size: 12,
                                        weight: 'bold'
                                    }
                                }
                            }
                        }
                    }
                });
            } catch (error) {
                console.error('Error creating chart:', error);
            }
        };

        const updateGrafica = () => {
            if (chartInstance.value) {
                const currentMonth = new Date().getMonth() + 1;

                //Buscar si ya existe un dataset para el mes actual
                let currentDatasetIndex = -1;
                for (let i = 0; i < chartInstance.value.data.datasets.length; i++) {
                    if (chartInstance.value.data.datasets[i].label === `Mes ${currentMonth}`) {
                        currentDatasetIndex = i;
                        break;
                    }
                }

                if (currentDatasetIndex >= 0) {
                    //Actualizar el dataset existente
                    const newData = labels.value.map(label => graficaInputs[label]);
                    chartInstance.value.data.datasets[currentDatasetIndex].data = newData;
                } else {
                    //Creación de un nuevo dataset para el mes actual
                    const newData = labels.value.map(label => graficaInputs[label]);
                    const newColor = {
                        bg: 'rgba(54, 162, 235, 0.2)',
                        border: 'rgba(54, 162, 235, 1)'
                    };

                    chartInstance.value.data.datasets.push({
                        label: `Mes ${currentMonth}`,
                        data: newData,
                        backgroundColor: newColor.bg,
                        borderColor: newColor.border,
                        borderWidth: 2,
                        pointBackgroundColor: newColor.border,
                        pointBorderColor: '#fff',
                        pointHoverBackgroundColor: '#fff',
                        pointHoverBorderColor: newColor.border
                    });
                }

                chartInstance.value.update();
            }
        };

        const guardarGraficas = async () => {
            try {
                // Preparar los datos para enviar
                const secciones = labels.value
                    .filter(label => graficaInputs[label] > 0)
                    .map(label => ({
                        seccion: label,
                        nivel: graficaInputs[label]
                    }));

                if (secciones.length === 0) {
                    store.commit('entrenadorDashboard/SET_ERROR', 'Debes asignar al menos un valor mayor que 0');
                    setTimeout(() => {
                        store.commit('entrenadorDashboard/SET_ERROR', null);
                    }, 3000);
                    return;
                }

                // Enviar la actualización
                await store.dispatch(`entrenadorDashboard/${Constant.UPDATE_ONE_GRAFICA}`, {
                    id: props.numeroSocio,
                    graficas: { secciones }
                });

                successMessage.value = 'Gráficas actualizadas correctamente';
                noGraficasDisponibles.value = false; //Ya no está vacío después de guardar

                //Ocultar el mensaje después de 3 segundos
                setTimeout(() => {
                    successMessage.value = null;
                }, 3000);

                //Recargar las gráficas y actualizar el gráfico
                await cargarGraficas();
            } catch (error) {
                console.error('Error al guardar gráficas:', error);
                store.commit('entrenadorDashboard/SET_ERROR', 'Error al guardar las gráficas');
                setTimeout(() => {
                    store.commit('entrenadorDashboard/SET_ERROR', null);
                }, 3000);
            }
        };

        const cargarInfoAlumno = async () => {
            try {
                alumnoInfo.value = {
                    numeroSocio: props.numeroSocio
                };
            } catch (error) {
                console.error('Error al cargar información del alumno:', error);
            }
        };

        const volver = () => {
            router.go(-1);
        };

        onMounted(() => {
            cargarInfoAlumno();
            nextTick(() => {
                setTimeout(() => {
                    cargarGraficas();
                }, 100);
            });
        });

        onBeforeUnmount(() => {
            if (chartInstance.value) {
                chartInstance.value.destroy();
                chartInstance.value = null;
            }
        });

        return {
            radarChart,
            graficaInputs,
            labels,
            successMessage,
            alumnoInfo,
            noGraficasDisponibles,
            loading,
            error,
            getIconForMetric,
            guardarGraficas,
            updateGrafica,
            volver
        };
    }
};
</script>

<style scoped>
.graficas-container {
    padding: 1rem;
}

.chart-container {
    position: relative;
    height: 400px;
    width: 100%;
}

.metric-item {
    padding: 0.8rem;
    border-radius: 0.5rem;
    background-color: white;
    transition: transform 0.2s;
    border: 1px solid #eee;
}

.metric-item:hover {
    transform: translateY(-2px);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
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