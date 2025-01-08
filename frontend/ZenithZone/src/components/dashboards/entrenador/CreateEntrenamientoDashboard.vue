<template>
  <div>
    <h2>Crear Entrenamiento</h2>

    <!-- Seleccionar Pista Privada como botones -->
    <div>
      <h3>Selecciona una Pista Privada:</h3>
      <div class="pistas-container">
        <button
          v-for="pista in pistasPrivadasFiltradas"
          :key="pista.pistas_id"
          :class="['pista-button', { selected: selectedPista === pista.pistas_id }]"
          @click="selectPista(pista)"
        >
          {{ pista.nombre }}
        </button>
      </div>
    </div>

    <div v-if="selectedPista">
      <h3>Selecciona un Horario</h3>
      <table class="horarios-table">
        <thead>
          <tr>
            <th>Horario/Día</th>
            <th v-for="(dia, index) in diasSemana" :key="index">{{ dia }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="horario in horariosBase" :key="horario.id">
            <td>{{ horario.hora }}</td>
            <td
              v-for="(dia, index) in diasSemana"
              :key="index"
              :class="['horario-cell', { ocupado: isHorarioOcupado(dia, horario.id) }]"
              @click="selectHorario(dia, horario)"
              :disabled="isHorarioOcupado(dia, horario.id)"
            >
              <button
                :disabled="isHorarioOcupado(dia, horario.id)"
                class="horario-button"
              >
                {{ isHorarioOcupado(dia, horario.id) ? 'Ocupado' : 'Disponible' }}
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Formulario para crear entrenamiento -->
    <div v-if="selectedHorario">
      <h3>Crear Entrenamiento</h3>
      <form @submit.prevent="createEntrenamiento">
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" v-model="nuevoEntrenamiento.nombre" required />
        </div>
        <div>
          <label for="descripcion">Descripción:</label>
          <textarea v-model="nuevoEntrenamiento.descripcion" required></textarea>
        </div>
        <div>
          <label for="duracion">Duración (en minutos):</label>
          <input type="number" v-model="nuevoEntrenamiento.duracion" min="30" max="120" required />
        </div>
        <div>
          <label for="max_plazas">Plazas:</label>
          <input type="number" v-model="nuevoEntrenamiento.plazas" required />
        </div>
        <div>
          <label for="precio">Precio:</label>
          <input type="number" v-model="nuevoEntrenamiento.precio" required />
        </div>
        <button type="submit">Crear Entrenamiento</button>
      </form>
    </div>
  </div>
</template> 
<script>
import { ref ,computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import Constant from '../../../Constant'; // Importamos las constantes

export default {
  setup() {
    const store = useStore();

    // Estado reactivo para almacenar todas las pistas privadas
    const pistasPrivadas = computed(() => store.state.entrenadorDashboard.pistasPrivadas);
    
    // Estado reactivo para las pistas filtradas por deporte
    const pistasPrivadasFiltradas = computed(() => {
      const deporteId = store.state.entrenadorDashboard.entrenador.usuario.deporte_id;
      console.log('Deporte ID del usuario:', deporteId);
      console.log('Pistas privadas:', pistasPrivadas.value);
      if (Array.isArray(pistasPrivadas.value)) {
        return pistasPrivadas.value.filter(pistaPrivada =>
          pistaPrivada.deportes.some(deporte => deporte.id === deporteId)
        );
      } else {
        console.error("pistasPrivadas no es un array:", pistasPrivadas.value);
        return []; // Retorna un array vacío en caso de que no sea un array
      }
    });

    // Estado reactivo para los horarios y el entrenamiento
    const selectedPista = ref(null);
    const horariosBase = ref([
      { id: 1, hora: "Mañana" },
      { id: 2, hora: "Mediodía" },
      { id: 3, hora: "Tarde" },
      { id: 4, hora: "Noche" },
    ]);
    const diasSemana = ref(["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"]);

    const horariosOcupados = reactive([]);
    const selectedHorario = ref(null);
    const nuevoEntrenamiento = reactive({
      nombre: "",
      descripcion: "",
      duracion: 60,
      max_plazas: 20,
      precio: 15,
      deporte_id: store.state.entrenadorDashboard.entrenador.usuario.deporte_id,
      horario_id: null,
      pista_privada_id: null,
      dia: null,
    });

    // Cargar las pistas privadas cuando el componente se monte
    onMounted(() => {
      store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_PISTA_PRIVADA}`);
   
    });

    // Cargar los horarios disponibles para la pista privada seleccionada
    const loadHorarios = async () => {
      if (!selectedPista.value) return;
      console.log("selectedpista"+selectedPista.value);
      await store.dispatch(`entrenadorDashboard/${Constant.FETCH_ENTRENAMIENTOS_OCUPADOS}`, selectedPista.value);
      const ocupados = store.state.entrenadorDashboard.horariosOcupados;
      console.log("ocupados?"+ocupados);
      horariosOcupados.splice(0, horariosOcupados.length, ...ocupados);
    };


    // Verificar si el horario está ocupado

    const isHorarioOcupado = (dia, horarioId) => {
      return horariosOcupados.some(ocupado => ocupado.dia === dia && ocupado.horario_id === horarioId);
    };


    // Seleccionar una pista privada
    const selectPista = (pista) => {
      selectedPista.value = pista.pistas_id;
      nuevoEntrenamiento.pista_privada_id = pista.pistas_id;
      loadHorarios(); 
    };

    // Seleccionar un horario
    const selectHorario = (dia, horario) => {
      if (isHorarioOcupado(dia, horario.id)) return; // Evitar seleccionar horarios ocupados
      selectedHorario.value = { dia, horario: horario.id }; // Asigna un objeto con dia y horario.id
      nuevoEntrenamiento.dia = dia;
      nuevoEntrenamiento.horario_id = horario.id;
    };
    // Función para crear un nuevo entrenamiento
    const createEntrenamiento = async () => {
      try {
        await store.dispatch(`entrenadorDashboard/${Constant.CREATE_ONE_ENTRENAMIENTO}`, nuevoEntrenamiento);
        alert("Entrenamiento creado exitosamente");
        resetFormulario();
       resetFormularioVisibilidad();
      } catch (error) {
        console.error("Error al crear entrenamiento:", error);
        alert("Hubo un problema al crear el entrenamiento.");
      }
    };
    const resetFormulario = () => {
  // Restablecer el objeto nuevoEntrenamiento a su valor inicial
  nuevoEntrenamiento.nombre = "";
  nuevoEntrenamiento.descripcion = "";
  nuevoEntrenamiento.duracion = 60;
  nuevoEntrenamiento.max_plazas = 20;
  nuevoEntrenamiento.precio = 15;
  nuevoEntrenamiento.deporte_id = store.state.entrenadorDashboard.entrenador.usuario.deporte_id;
  nuevoEntrenamiento.horario_id = null;
  nuevoEntrenamiento.pista_privada_id = null;
  nuevoEntrenamiento.dia = null;

  // Restablecer las selecciones
  selectedPista.value = null;
  selectedHorario.value = null;
};

// Función para resetear la visibilidad de las secciones del formulario
const resetFormularioVisibilidad = () => {
  // Ocultar las secciones del formulario que dependen de las selecciones
  selectedPista.value = null; // Pista seleccionada vuelve a estar vacía
  selectedHorario.value = null; // Horario seleccionado vuelve a estar vacío
  nuevoEntrenamiento.horario_id = null;
  nuevoEntrenamiento.dia = null;
}
    return {
      pistasPrivadasFiltradas,
      selectPista,
      selectedPista,
      horariosBase,
      diasSemana,
      isHorarioOcupado,
      selectHorario,
      selectedHorario,
      createEntrenamiento,
      nuevoEntrenamiento,
    };
  }
};
</script>

<style scoped>
.horarios-table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
}

.horarios-table th,
.horarios-table td {
  border: 1px solid #ccc;
  padding: 10px;
}

.horario-cell.ocupado {
  background-color: #808080;
  color: white;
  pointer-events: none;
}

.horario-button {
  background-color: #f0f0f0;
  border: none;
  padding: 10px;
  cursor: pointer;
}

.horario-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.pistas-container {
  display: flex;
  gap: 10px;
}

.pista-button {
  padding: 10px;
  background-color: #e0e0e0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.pista-button.selected {
  background-color: #4caf50;
  color: white;
}
</style>
