<template>
<div class="mt-5">
    <canvas ref="radarChart"></canvas>
</div>
</template>


<script>
import {
    Chart,
    registerables
} from 'chart.js';
import {
    onMounted,
    ref
} from 'vue';

Chart.register(...registerables);

export default {
    name: 'RadarChart',
    setup() {
        const radarChart = ref(null);

        onMounted(() => {
            const ctx = radarChart.value.getContext('2d');

            radarChart.value.width = 600;
            radarChart.value.height = 600;

            new Chart(ctx, {
                type: 'radar',
                data: {
                    labels: ['Motivación', 'Agilidad', 'Velocidad', 'Aguante', 'Fuerza',],
                    datasets: [{
                            data: [90, 70, 90, 43, 56],
                            borderColor: 'rgba(255, 162, 80, 1)', // Azul
                            backgroundColor: 'rgba(255, 162, 80, 0.2)', // Transparencia
                            borderWidth: 2,
                        },
                    ],
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

  
<style>
canvas {
    max-width: 700px;
    height: auto;
    margin: auto;
    /* margin-right: 10; */
}
</style>
