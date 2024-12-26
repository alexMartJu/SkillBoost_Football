<template>
    <div class="grafica-container">
      <canvas ref="radarChart"></canvas>
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
  
      // Llamada para cargar las gráficas al montarse el componente
      onMounted(() => {
        store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_GRAFICAS}`, props.profileId);
      });
  
      // Observa las gráficas para actualizar la visualización
      watch(() => store.state.entrenadorDashboard.gráficas, (newGraficas) => {
        graficas.value = newGraficas;
        if (graficas.value.length > 0) {
          renderGrafica();
        }
      });
  
      // Función para renderizar la gráfica
      const renderGrafica = () => {
        const labels = ['Motivación', 'Agilidad', 'Velocidad', 'Aguante', 'Fuerza'];
        const motivacionData = [];
        const agilidadData = [];
        const velocidadData = [];
        const aguanteData = [];
        const fuerzaData = [];
  
        // Agregar los datos de las gráficas actuales
        graficas.value.forEach(grafica => {
          motivacionData.push(grafica.motivacion);
          agilidadData.push(grafica.agilidad);
          velocidadData.push(grafica.velocidad);
          aguanteData.push(grafica.aguante);
          fuerzaData.push(grafica.fuerza);
        });
  
        // Configuración de los datos de la gráfica
        const data = {
          labels: labels,
          datasets: [
            {
              label: 'Motivación',
              borderColor: 'rgba(255, 162, 80, 1)', 
              backgroundColor: 'rgba(255, 162, 80, 0.2)',
              data: motivacionData,
              borderWidth: 2,
              tension: 0.4,
            },
            {
              label: 'Agilidad',
              borderColor: 'rgba(55, 162, 80, 1)', 
              backgroundColor: 'rgba(55, 162, 80, 0.2)',
              data: agilidadData,
              borderWidth: 2,
              tension: 0.4,
            },
            {
              label: 'Velocidad',
              borderColor: 'rgba(255, 206, 86, 1)',
              backgroundColor: 'rgba(255, 206, 86, 0.2)',
              data: velocidadData,
              borderWidth: 2,
              tension: 0.4,
            },
            {
              label: 'Aguante',
              borderColor: 'rgba(75, 192, 192, 1)',
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              data: aguanteData,
              borderWidth: 2,
              tension: 0.4,
            },
            {
              label: 'Fuerza',
              borderColor: 'rgba(153, 102, 255, 1)',
              backgroundColor: 'rgba(153, 102, 255, 0.2)',
              data: fuerzaData,
              borderWidth: 2,
              tension: 0.4,
            },
          ],
        };
  
        // Opciones de la gráfica
        const options = {
          responsive: true,
          plugins: {
            legend: { display: true },
          },
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
        new Chart(ctx, { type: 'radar', data, options });
      };
  
      return { radarChart };
    },
  };
  </script>
  
  <style scoped>
  .grafica-container {
    width: 80%; /* Gráfica más ancha */
    margin: 20px auto;
    padding: 20px;
  }
  
  canvas {
    width: 100% !important;
    height: auto !important;
  }
  </style>
  