<template>
  <div class="chart-wrapper" ref="chartContainer">
    <canvas ref="radarChart"></canvas>
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import { onMounted, ref, watch, onBeforeUnmount } from 'vue';

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
    const radarChart = ref(null);
    const chartContainer = ref(null);
    let chartInstance = null;
    let resizeObserver = null;

    // rocesar los datos para el gráfico
    const processData = () => {
      return props.sets.map(grafica => {
        if (grafica.secciones && Array.isArray(grafica.secciones)) {
          const ordenSecciones = ['Técnica', 'Táctica', 'Físico', 'Mental', 'Aplicación en juego'];
          const seccionesOrdenadas = [];

          ordenSecciones.forEach(nombreSeccion => {
            const seccion = grafica.secciones.find(s =>
              s.seccion === nombreSeccion ||
              s.seccion === nombreSeccion.toLowerCase()
            );
            if (seccion) {
              seccionesOrdenadas.push(seccion.nivel);
            } else {
              seccionesOrdenadas.push(0);
            }
          });

          return seccionesOrdenadas;
        } else if (Array.isArray(grafica)) {
          return grafica;
        } else {
          return [0, 0, 0, 0, 0];
        }
      });
    };

    //Crear o actualizar el gráfico
    const updateChart = () => {
      if (!radarChart.value || !chartContainer.value) return;

      const ctx = radarChart.value.getContext('2d');
      const nivelesPorGrafica = processData();

      const parentWidth = chartContainer.value.clientWidth;
      const parentHeight = Math.max(300, parentWidth * 0.8);

      radarChart.value.width = parentWidth;
      radarChart.value.height = parentHeight;
      radarChart.value.style.width = `${parentWidth}px`;
      radarChart.value.style.height = `${parentHeight}px`;

      //Si ya existe un gráfico, destruirlo
      if (chartInstance) {
        chartInstance.destroy();
      }

      // rear un nuevo gráfico
      chartInstance = new Chart(ctx, {
        type: 'radar',
        data: {
          labels: ['Técnica', 'Táctica', 'Físico', 'Mental', 'Aplicación en Juego'],
          datasets: nivelesPorGrafica.map((set, index) => ({
            label: props.sets[index].mes ? meses[props.sets[index].mes - 1] : meses[index],
            data: set,
            borderColor: `rgba(${index * 23}, 162, 80, 1)`,
            backgroundColor: `rgba(${index * 23}, 162, 80, 0.2)`,
            borderWidth: 2,
          })),
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              display: true,
              position: 'top',
            },
          },
          scales: {
            r: {
              angleLines: {
                color: 'rgba(0, 0, 0, 0.3)',
              },
              suggestedMin: 0,
              suggestedMax: 100,
              ticks: {
                stepSize: 20,
                font: {
                  size: 12,
                },
                color: '#4b5563',
              },
              grid: {
                color: 'rgba(0, 0, 0, 0.1)',
              },
              pointLabels: {
                font: {
                  size: 14,
                },
                color: '#000',
              },
            },
          },
        },
      });
    };

    //Observar cambios en los datos
    watch(() => props.sets, () => {
      if (props.sets.length > 0) {
        setTimeout(updateChart, 0);
      }
    }, { deep: true });

    onMounted(() => {
      //Configurar ResizeObserver para detectar cambios en el tamaño
      if ('ResizeObserver' in window) {
        resizeObserver = new ResizeObserver(() => {
          if (props.sets.length > 0) {
            updateChart();
          }
        });

        if (chartContainer.value) {
          resizeObserver.observe(chartContainer.value);
        }
      }

      //Renderizar el gráfico inicialmente con un pequeño retraso
      if (props.sets.length > 0) {
        setTimeout(updateChart, 0);
      }

      document.addEventListener('visibilitychange', () => {
        if (!document.hidden && props.sets.length > 0) {
          updateChart();
        }
      });
    });

    onBeforeUnmount(() => {
      if (resizeObserver) {
        resizeObserver.disconnect();
      }
      document.removeEventListener('visibilitychange', () => { });
      if (chartInstance) {
        chartInstance.destroy();
      }
    });

    return {
      radarChart,
      chartContainer
    };
  },
};
</script>

<style scoped>
.chart-wrapper {
  width: 100%;
  height: 100%;
  min-height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
}

canvas {
  width: 100% !important;
  height: 100% !important;
  max-width: 100%;
  max-height: 100%;
  margin: auto;
  transition: all 0.3s ease;
}

canvas:hover {
  filter: brightness(1.05);
}
</style>