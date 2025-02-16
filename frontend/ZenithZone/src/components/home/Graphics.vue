<template>
    <div class="mt-5">
        <canvas ref="radarChart"></canvas>
    </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import { onMounted, ref } from 'vue';

Chart.register(...registerables);

export default {
    name: 'RadarChart',

    props: {
        graficas: {
            type: Array,
            required: true,
        },
    },

    setup(props) {
        const meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
        let nivelesPorGrafica = [
            [60, 70, 90, 43, 56],
            [69, 79, 93, 50, 45]
        ]; // estos son los datos default que pinta en el home

        if (props.graficas != undefined) {
            nivelesPorGrafica = props.graficas.map((grafica) => {
                return grafica.secciones.map(seccion => seccion.nivel);
            });
        } // aquí es cuando se le asignan los datos de las gráficas del perfil

        const radarChart = ref(null);

        onMounted(() => {
            const ctx = radarChart.value.getContext('2d');

            radarChart.value.width = 600;
            radarChart.value.height = 600;

            new Chart(ctx, {
                type: 'radar',
                data: {
                    labels: ['Motivación', 'Agilidad', 'Velocidad', 'Aguante', 'Fuerza',],
                    datasets: nivelesPorGrafica.map((set, index) => ({
                        label: meses[index],
                        data: set,
                        borderColor: `rgba(${index * 23}, 162, 80, 1)`, // Color del borde
                        backgroundColor: `rgba(${index * 23}, 162, 80, 0.2)`, // Transparencia dinámica
                        borderWidth: 2,
                    })),
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            display: false
                        },
                    },
                    scales: {
                        r: {
                            angleLines: {
                                color: 'rgba(0, 0, 0, 0.3)', // Líneas de los ejes
                            },
                            suggestedMin: 0, // Mínimo sugerido
                            suggestedMax: 100, // Máximo sugerido
                            ticks: {
                                stepSize: 20, // Intervalo de valores
                                font: {
                                    size: 14,
                                },
                                color: '#4b5563', // Color del texto
                            },
                            grid: {
                                color: 'rgba(0, 0, 0, 0.1)', // Líneas de la cuadrícula
                            },
                            pointLabels: {
                                font: {
                                    size: 20,
                                },
                                color: '#000', // Color de las etiquetas (ejes)
                            },
                        },
                    },
                },
            });
        });

        return {
            radarChart,
        };
    },
};
</script>


<style scoped>
canvas {
    max-width: 100%;
    height: auto;
    margin: auto;
    backdrop-filter: blur(5px);
    transition: all 0.3s ease;
}

canvas:hover {
    filter: brightness(1.05);
}

.chart-container {
    position: relative;
    padding: 1rem;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 15px;
    box-shadow: 0 8px 32px rgba(31, 38, 135, 0.1);
}
</style>
