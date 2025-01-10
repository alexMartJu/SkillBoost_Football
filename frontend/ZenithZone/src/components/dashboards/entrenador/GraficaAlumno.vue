<template>
    <div class="grafica-container">
        <canvas ref="radarChart"></canvas>
        <!-- Formulario para actualizar los datos del mes actual -->
        <div class="update-form">
            <h3>Actualizar datos para el mes actual</h3>
            <form @submit.prevent="updateGrafica">
                <div v-for="(label, index) in labels" :key="index" class="input-group">
                    <label :for="'input-' + label">{{ label }}:</label>
                    <input
                        type="number"
                        :id="'input-' + label"
                        v-model.number="graficaInputs[label]"
                        min="0"
                        max="100"
                        placeholder="0-100"
                        @input="updateGrafica" 
                    />
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import { ref, onMounted, watch } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';

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
        const radarChart = ref(null);
        const store = useStore();
        const graficas = ref([]);
        const graficaInputs = ref({
            'Motivación': 0,
            'Agilidad': 0,
            'Velocidad': 0,
            'Aguante': 0,
            'Fuerza': 0,
        });
        const labels = ['Motivación', 'Agilidad', 'Velocidad', 'Aguante', 'Fuerza'];

        // Llamada para cargar las gráficas al montarse el componente
        onMounted(() => {
            store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_GRAFICAS}`, props.profileId);
            setGraficaInputs();
        });

        // Observa los cambios en las gráficas desde Vuex
        watch(() => store.state.entrenadorDashboard.graficas, (newGraficas) => {
            graficas.value = newGraficas;
            console.log("Datos actualizados en graficas.value:", graficas.value);
            renderGrafica();
            setGraficaInputs();
        });
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
            return `rgba(${r}, ${g}, ${b}, 0.6)`; // Color de fondo con alfa 0.6
        };

        // Función para generar un color de borde aleatorio en formato rgba (con alfa 1)
        const getRandomBorderColor = () => {
            const r = Math.floor(Math.random() * 256);
            const g = Math.floor(Math.random() * 256);
            const b = Math.floor(Math.random() * 256);
            return `rgba(${r}, ${g}, ${b}, 1)`; // Color de borde con alfa 1 (opaco)
        };

        const renderGrafica = () => {
    if (window.chartInstance) {
        window.chartInstance.destroy(); 
    }

    // Filtrar las gráficas por el profileId
    const filteredGraficas = graficas.value.filter(grafica => {
    console.log("Comparando profile_id:", BigInt(grafica.profile_id), "con props.profileId:", BigInt(props.profileId));
    return BigInt(grafica.profile_id) === BigInt(props.profileId);
    });

console.log("Filtered Graficas:", filteredGraficas);
    const mesesAnios = [
        ...new Set(filteredGraficas.map(graficas => `${graficas.Mes}-${graficas.Año}`)),
    ];

    const datasets = mesesAnios.map(mesAnio => {
        const [mes, anio] = mesAnio.split('-');
        const data = labels.map(label => {
            const graficaMesAnio = filteredGraficas.filter(
                graficas => graficas.Mes === parseInt(mes) && graficas.Año === parseInt(anio) && graficas.seccion === label
            );
            return graficaMesAnio.length > 0 ? graficaMesAnio[0].nivel : 0;
        });

        return {
            label: `${mes}-${anio}`,
            data,
            borderWidth: 2,
            tension: 0.4,
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


        // Función para actualizar la gráfica de manera dinámica cuando se cambian los inputs
        const updateGrafica = () => {
            const currentMonth = new Date().getMonth() + 1;
            const currentYear = new Date().getFullYear();
            
            // Verificamos que haya alguna entrada modificada
            const modifiedSections = Object.keys(graficaInputs.value).filter(label => graficaInputs.value[label] > 0);

            if (modifiedSections.length > 0) {
                modifiedSections.forEach(section => {
                    const updatedGraficas = {
                        seccion: section,
                        nivel: graficaInputs.value[section],
                        Mes: currentMonth,
                        Año: currentYear,
                    };

                    console.log("Updated Graph Data:", updatedGraficas); // Verificar que los datos sean correctos

                    // Mandar la gráfica actualizada a Vuex
                    store.dispatch(`entrenadorDashboard/${Constant.UPDATE_ONE_GRAFICA}`, { 
                        id: props.profileId, 
                        graficas: updatedGraficas
                    });
                });
            }
        };

        return {
            radarChart,
            graficaInputs,
            labels,
            updateGrafica,
        };
    },

};
</script>

<style scoped>
.grafica-container {
    width: 80%;
    margin: 20px auto;
    padding: 20px;
}

canvas {
    width: 100% !important;
    height: auto !important;
}

.update-form {
    margin-top: 30px;
}

.input-group {
    margin-bottom: 10px;
}

label {
    display: inline-block;
    width: 100px;
}

input {
    width: 60px;
    padding: 5px;
    text-align: center;
}

button {
    margin-top: 20px;
    padding: 10px 15px;
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}
</style>
