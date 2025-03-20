<template>
    <div class="chart-wrapper">
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
        sets: {
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

        if (props.sets != undefined) {
            nivelesPorGrafica = props.sets.map((grafica) => {
                return grafica.secciones ? grafica.secciones.map(seccion => seccion.nivel) : grafica;
            });
        } // aquí es cuando se le asignan los datos de las gráficas del perfil

        const radarChart = ref(null);

        onMounted(() => {
            const ctx = radarChart.value.getContext('2d');

            // Ajustar el tamaño del canvas para que se adapte al contenedor
            const parentWidth = radarChart.value.parentElement.clientWidth;
            radarChart.value.width = parentWidth;
            radarChart.value.height = parentWidth * 0.8; // Proporción altura/anchura

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
                    maintainAspectRatio: false,
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
                                    size: 12,
                                },
                                color: '#4b5563', // Color del texto
                            },
                            grid: {
                                color: 'rgba(0, 0, 0, 0.1)', // Líneas de la cuadrícula
                            },
                            pointLabels: {
                                font: {
                                    size: 14,
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
.chart-wrapper {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}

canvas {
    max-width: 100%;
    max-height: 100%;
    margin: auto;
    transition: all 0.3s ease;
}

canvas:hover {
    filter: brightness(1.05);
}
</style>